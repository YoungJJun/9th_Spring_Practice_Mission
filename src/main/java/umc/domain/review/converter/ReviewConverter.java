package umc.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReplyDto;
import umc.domain.review.dto.ReviewReqDto;
import umc.domain.review.dto.ReviewResDto;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

import java.time.LocalDate;
import java.util.List;

public class ReviewConverter {

    // Review → ReviewResponseDto.Detail
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

    // List<Review> → List<ReviewResponseDto.Detail>
    public static List<ReviewResDto.Detail> toReviewResponseDtoListDetail(List<Review> reviews) {
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

    // Page<Review> -> ReviewPreViewListDTO
    public static ReviewResDto.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDto.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDto.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
