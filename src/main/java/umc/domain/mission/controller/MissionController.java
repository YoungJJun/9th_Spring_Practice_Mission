package umc.domain.mission.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    //memberId 전달받는 부분 수정필요
    @GetMapping("/my/completed")
    public ApiResponse<List<MissionResDto.SimpleMissionDto>> getCompletedMissions(@RequestParam Long memberId){
        List<MissionResDto.SimpleMissionDto> result = missionService.getCompletedMissionsByMemberId(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
    //memberId 전달받는 부분 수정필요
    @GetMapping("/my/in-progress")
    public ApiResponse<List<MissionResDto.SimpleMissionDto>> getInProgressMissions(@RequestParam Long memberId){
        List<MissionResDto.SimpleMissionDto> result = missionService.getInProgressMissionsByMemberId(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

}
