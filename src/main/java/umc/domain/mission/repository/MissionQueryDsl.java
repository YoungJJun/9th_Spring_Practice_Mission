package umc.domain.mission.repository;

import umc.domain.mission.entity.Mission;

import java.util.List;

public interface MissionQueryDsl {
    int countCompletedMissionMod10(Long memberId, Long regionId);
    List<Mission> findAvailableMissionByMemberAndRegion(Long memberId, Long regionId);
}
