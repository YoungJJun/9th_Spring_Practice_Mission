package umc.domain.mission.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.*;
import org.springframework.stereotype.Repository;
import umc.domain.member.enums.MissionStatus;
import umc.domain.mission.entity.Mission;

import java.util.List;

import static umc.domain.member.entity.mapping.QMemberMission.memberMission;
import static umc.domain.mission.entity.QMission.mission;


@Repository
@RequiredArgsConstructor
public class MissionQueryDslImpl implements MissionQueryDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public int countCompletedMissionMod10(Long memberId, Long regionId) {
        Long count = queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .join(memberMission.mission, mission)
                .join(mission.store, mission.store) // store join
                .join(mission.store.region, mission.store.region)
                .where(
                        memberMission.member.id.eq(memberId),
                        mission.store.region.id.eq(regionId),
                        memberMission.status.eq(MissionStatus.COMPLETED)
                )
                .fetchOne();

        return count != null ? (int) (count % 10) : 0;
    }

    @Override
    public List<Mission> findAvailableMissionByMemberAndRegion(Long memberId, Long regionId) {
        return queryFactory
                .selectFrom(mission)
                .join(mission.store).fetchJoin()
                .join(mission.store.region).fetchJoin()
                .leftJoin(memberMission)
                .on(
                        memberMission.mission.eq(mission),
                        memberMission.member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.COMPLETED)
                )
                .where(
                        mission.store.region.id.eq(regionId),
                        memberMission.id.isNull() // 완료된 적 없는 미션만
                )
                .fetch();
    }
}
