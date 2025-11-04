package umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import umc.domain.review.entity.Review;

import java.util.List;


public interface ReviewQueryDsl {

    List<Review> searchReview(Long memberId, Long storeId, Integer ratingFilter);
}
