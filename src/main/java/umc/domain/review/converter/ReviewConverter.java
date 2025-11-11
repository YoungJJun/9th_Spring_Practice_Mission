package umc.domain.review.converter;

import umc.domain.review.dto.ReplyDto;
import umc.domain.review.dto.ReviewResDto;
import umc.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    // Review → ReviewResponseDto
    public static ReviewResDto toReviewResponseDto(Review review) {
        return ReviewResDto.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .imageUrls(review.getImages().stream()
                        .map(img -> img.getImageUrl())
                        .toList())
                .replies(review.getReplies().stream()
                        .map(reply -> ReplyDto.builder()
                                .replyId(reply.getId())
                                .content(reply.getContent())
                                .createdAt(reply.getCreatedAt())
                                .build())
                        .toList())
                .build();
    }

    // List<Review> → List<ReviewResponseDto>
    public static List<ReviewResDto> toReviewResponseDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toReviewResponseDto)
                .toList();
    }
}
