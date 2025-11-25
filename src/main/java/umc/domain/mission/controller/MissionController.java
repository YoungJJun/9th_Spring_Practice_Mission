package umc.domain.mission.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberMissionResDto;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.annotation.ExistMission;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @Operation(
            summary = "자신이 완료한 미션 목록 조회 API",
            description = "자신이 완료한 모든 미션을 조회합니다. 페이지네이션으로 제공"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/my/completed")
    public ApiResponse<MissionResDto.MissionPreviewListDTO> getMyCompletedMissions(
            @ValidPage @RequestParam(defaultValue = "1") Integer page,
            @Positive @RequestParam (defaultValue = "10") Integer size
    ){
        Long memberId = 1L;
        MissionResDto.MissionPreviewListDTO result = missionService.getMyCompletedMissions(memberId, page, size);
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, result);
    }


    @Operation(
            summary = "자신이 진행중인 미션 목록 조회 API",
            description = "자신이 진행중인 모든 미션을 조회합니다. 페이지네이션으로 제공"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/my/in-progress")
    public ApiResponse<MissionResDto.MissionPreviewListDTO> getMyInProgressMissions(
            @ValidPage @RequestParam(defaultValue = "1") Integer page,
            @Positive @RequestParam (defaultValue = "10") Integer size
    )
    {
        Long memberId = 1L;
        MissionResDto.MissionPreviewListDTO result = missionService.getMyInProgressMissions(memberId, page, size);
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, result);
    }

    //미션 완료
    @Operation(
            summary = "진행중 미션 완료하기 API",
            description = "자신이 진행중인 미션을 완료하고, 보상으로 포인트 획득합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/{missionId}/complete")
    public ApiResponse<MemberMissionResDto.complete> completeMyMission(
            @PathVariable Long missionId
    ) {
        Long memberId = 1L;
        MemberMissionResDto.complete result = missionService.completeMyMission(memberId, missionId);
        return ApiResponse.onSuccess(MissionSuccessCode.UPDATED, result);
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
