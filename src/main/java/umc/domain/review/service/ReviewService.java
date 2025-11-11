package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewResDto;
import umc.domain.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResDto> getDetailedReviews(Long memberId, Long storeId, Integer ratingFilter) {
        return ReviewConverter.toReviewResponseDtoList(
                reviewRepository.searchReview(memberId, storeId, ratingFilter)
        );
    }
}