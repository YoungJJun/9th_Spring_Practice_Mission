package umc.domain.mission.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberMissionResDto;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.service.MissionService;
import umc.global.annotation.ExistMission;
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

    //미션도전
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MemberMissionResDto.challenge> challengeMission(
            @PathVariable @ExistMission Long missionId
    ){
        Long memberId = 1L;  // !!!! 이후 수정 -> id는 토큰에서 꺼낸다. !!!!

        MemberMissionResDto.challenge result = missionService.challengeMission(memberId,missionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

}
