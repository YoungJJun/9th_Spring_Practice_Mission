package umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
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


    QReview review = QReview.review;

    // 멤버 필터링
    private BooleanExpression memberEq(Long memberId) {
        return review.member.id.eq(memberId);
    }
    // 가게 필터링
    private BooleanExpression storeEq(Long storeId) {
        return storeId != null ? review.store.id.eq(storeId) : null;
    }
    // 별점 필터링
    private BooleanExpression ratingCondition(Integer ratingFilter) {
        if (ratingFilter == null) return null;

        return switch (ratingFilter) {
            case 5 -> review.rating.eq(5.0);
            case 4 -> review.rating.between(4.0, 4.9);
            case 3 -> review.rating.between(3.0, 3.9);
            case 2 -> review.rating.between(2.0, 2.9);
            case 1 -> review.rating.between(1.0, 1.9);
            case 0 -> review.rating.between(0.0, 0.9);
            default -> null;
        };
    }

    @Override
    public List<Review> searchReview(Long memberId, Long storeId, Integer ratingFilter){
        return queryFactory
                .selectFrom(review)
                .where(memberEq(memberId),
                        storeEq(storeId),
                        ratingCondition(ratingFilter))
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
