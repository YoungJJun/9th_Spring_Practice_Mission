package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public List<MissionResDto.SimpleMissionDto> getCompletedMissionsByMemberId(Long memberId) {
        return missionRepository.findCompletedMissionByMemberId(memberId)
                .stream()
                .map(MissionConverter::toSimpleMissionDto)
                .toList();
    }

    public List<MissionResDto.SimpleMissionDto> getInProgressMissionsByMemberId(Long memberId) {
        return missionRepository.findInProgressMissionByMemberId(memberId)
                .stream()
                .map(MissionConverter::toSimpleMissionDto)
                .toList();
    }

}