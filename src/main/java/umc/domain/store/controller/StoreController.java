package umc.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDto;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.service.MissionService;
import umc.domain.review.dto.ReviewReqDto;
import umc.domain.review.dto.ReviewResDto;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.domain.store.dto.StoreReqDto;
import umc.domain.store.dto.StoreResDto;
import umc.domain.store.service.command.StoreCommandService;
import umc.global.annotation.ExistStore;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final ReviewService reviewService;
    private final StoreCommandService storeCommandService;
    private final MissionService missionService;

    //리뷰추가
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDto.Detail> createReview(
            @ExistStore @PathVariable ("storeId") Long storeId,
            @RequestBody ReviewReqDto.Create dto
    ) {

        ReviewResDto.Detail result = reviewService.createReview(dto, storeId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


    //가게추가
    @PostMapping
    public ApiResponse<StoreResDto.Create> createStore(
            @RequestBody StoreReqDto.Create dto
    ) {

        StoreResDto.Create result = storeCommandService.createStore(dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


    //미션추가
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDto.SimpleMissionDto> createMission(
            @PathVariable @ExistStore Long storeId,
            @RequestBody MissionReqDto.create dto
    ){
        MissionResDto.SimpleMissionDto result = missionService.createMission(storeId, dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //가게의 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDto.ReviewPreViewListDTO> getReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewService.findStoreReview(null,storeId, null, page, size));
    }
}
