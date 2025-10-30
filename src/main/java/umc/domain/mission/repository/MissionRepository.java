package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //내가 완료한 미션 모아서 보기위한 쿼리
    @Query("""
        SELECT mm.mission FROM MemberMission mm
        WHERE mm.member.id=:memberId
          AND mm.status = 'COMPLETED'
    """)
    List<Mission> findCompletedMissionByMemberId(@Param("memberId") Long memberId);

    //내가 진행중인 미션 모아서 보기위한 쿼리
    @Query("""
        SELECT mm.mission FROM MemberMission mm
        WHERE mm.member.id=:memberId
          AND mm.status = 'IN_PROGRESS'
    """)
    List<Mission> findInProgressMissionByMemberId(@Param("memberId") Long memberId);

    //홈화면1 - 현재지역에서 완료한 미션 수 MOD 10
    @Query("""
        SELECT MOD(COUNT(mm),10)
        FROM MemberMission mm
        JOIN mm.mission m
        JOIN m.store s
        JOIN s.region r
        WHERE mm.member.id = :memberId
        AND r.id=:regionId
        AND mm.status = 'COMPLETED'
""")
    int CntCompletedMissionMod10(@Param("memberId") Long memberId, @Param("regionId") Long regionId);

    //홈화면2 - 현재지역에서 도전할 수 있는 미션 리스트
    //mission에 대한 완료, 포기, 기간만료 등의 다양한 상태가 아직 정의되지 않았기 때문에 COMPLETED가 아닌 모든 Mission은 도전 가능하다고 가정
    @Query("""
        SELECT m
        FROM Mission m
        JOIN m.store s
        JOIN s.region r
        LEFT JOIN MemberMission mm
        ON mm.mission = m
        AND mm.member.id=:memberId
        AND mm.status='COMPLETED'
        WHERE r.id=:regionId
        AND mm.id is NULL

""")
    List<Mission> findAvailableMissionByMemberAndRegion(@Param("memberId") Long memberId, @Param("regionId") Long regionId);

}
