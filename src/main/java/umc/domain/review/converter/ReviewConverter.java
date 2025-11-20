package umc.domain.review.converter;

import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReplyDto;
import umc.domain.review.dto.ReviewReqDto;
import umc.domain.review.dto.ReviewResDto;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

import java.util.List;

public class ReviewConverter {

    // Review → ReviewResponseDto
    public static ReviewResDto.Detail toReviewResponseDto(Review review) {
        return ReviewResDto.Detail.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .imageUrls(review.getImages().stream()
                        .map(img -> img.getImageUrl())
                        .toList())
                .replies(review.getReplies().stream()
                        .map(reply -> ReviewReplyDto.builder()
                                .replyId(reply.getId())
                                .content(reply.getContent())
                                .createdAt(reply.getCreatedAt())
                                .build())
                        .toList())
                .build();
    }

    // List<Review> → List<ReviewResponseDto>
    public static List<ReviewResDto.Detail> toReviewResponseDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toReviewResponseDto)
                .toList();
    }

    //ReviewReqDto.Create -> Review
    public static Review toReview(ReviewReqDto.Create dto, Store store, Member member) {
        return Review.builder()
                .content(dto.content())
                .rating(dto.rating())

                .store(store)
                .member(member)
                .build();
    }
}
