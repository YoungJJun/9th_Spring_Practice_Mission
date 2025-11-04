package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.review.dto.ReplyDto;
import umc.domain.review.dto.ReviewResponseDto;
import umc.domain.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDto> getDetailedReviews(Long memberId, Long storeId, Integer ratingFilter) {
        return reviewRepository.searchReview(memberId, storeId, ratingFilter)
                .stream()
                .map(r -> ReviewResponseDto.builder()
                        .reviewId(r.getId())
                        .content(r.getContent())
                        .rating(r.getRating())
                        .storeName(r.getStore().getName())
                        .createdAt(r.getCreatedAt())

                        .imageUrls(r.getImages().stream()
                                .map(img -> img.getImageUrl()) // 예: img.getPath()일 수도 있음
                                .toList())


                        .replies(r.getReplies().stream()
                                .map(reply -> ReplyDto.builder()
                                        .replyId(reply.getId())
                                        .content(reply.getContent())
                                        .createdAt(reply.getCreatedAt())
                                        .build())
                                .toList())

                        .build())
                .toList();
    }
}