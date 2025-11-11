package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> ,MissionQueryDsl{

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


}
