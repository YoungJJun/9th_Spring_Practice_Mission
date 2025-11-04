package umc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.review.entity.Review;
import umc.global.BaseEntity;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    //리뷰 작성하는 쿼리 -> save()를 통해 저장
    default Review saveReview(Review review){
        return save(review);
    }

}
