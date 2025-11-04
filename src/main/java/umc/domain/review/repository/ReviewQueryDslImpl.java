package umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

   private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchReview(Long memberId, Long storeId, Integer ratingFilter){

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        //멤버 필터 (이후에 다른 멤버 리뷰 조회에 재사용하기 위해서)
        builder.and(review.member.id.eq(memberId));

        //가게 필터
        if(storeId!=null){
            builder.and(review.store.id.eq(storeId));
        }

        //별점 필터
        if(ratingFilter!=null){
            switch (ratingFilter) {
                case 5 -> builder.and(review.rating.eq(5.0));
                case 4 -> builder.and(review.rating.between(4.0, 4.9));
                case 3 -> builder.and(review.rating.between(3.0, 3.9));
                case 2 -> builder.and(review.rating.between(2.0, 2.9));
                case 1 -> builder.and(review.rating.between(1.0, 1.9));
                case 0 -> builder.and(review.rating.between(0, 0.9));
            }
        }

        return queryFactory
                .selectFrom(review)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
