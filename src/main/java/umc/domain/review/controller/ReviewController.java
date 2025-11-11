package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewResDto;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/me")
    public ApiResponse<List<ReviewResDto>> getMyDetailedReviews(
            //@AuthenticationPrincipal MemberDetails member (이후에 토큰에서 멤버 받아올 때 사용)
            //Long memberId = member.getMember().getId(); (받아온 멤버로부터 id 받아오기)
            @RequestParam(required = true)  Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer ratingFilter) {

        List<ReviewResDto> reviews =
                reviewService.getDetailedReviews(memberId, storeId, ratingFilter);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }
}
