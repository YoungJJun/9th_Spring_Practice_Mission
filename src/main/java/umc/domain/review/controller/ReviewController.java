package umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewResDto;
import umc.domain.review.service.ReviewService;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Validated
public class ReviewController {

    private final ReviewService reviewService;


    @Operation(
            summary = "자신이 작성한 리뷰 목록 조회 API",
            description = "자신이 작성한 모든 리뷰를 조회합니다. 페이지네이션으로 제공"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })

    @GetMapping("/me")
    public ApiResponse<ReviewResDto.ReviewPreViewListDTO> getMyReviews(
            @RequestParam(required = false) Long storeId,
            @Min(1) @Max(5) @RequestParam(required = false) Integer ratingFilter,
            @ValidPage @RequestParam(defaultValue = "1") Integer page,
            @Positive @RequestParam (defaultValue = "5") Integer size
    )
    {
        Long memberId = 1L; //스프링 시큐리티로 수정 필요

        Pageable pageable = PageRequest.of(page-1, size);

        ReviewResDto.ReviewPreViewListDTO result =
                reviewService.findMyReviews(memberId, storeId, ratingFilter, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


}
