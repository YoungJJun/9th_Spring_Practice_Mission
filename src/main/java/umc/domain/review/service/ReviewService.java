package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewReqDto;
import umc.domain.review.dto.ReviewResDto;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public List<ReviewResDto.Detail> getDetailedReviews(Long memberId, Long storeId, Integer ratingFilter) {
        return ReviewConverter.toReviewResponseDtoListDetail(
                reviewRepository.searchReview(memberId, storeId, ratingFilter)
        );
    }

    public ReviewResDto.ReviewPreViewListDTO findStoreReview(Long memberId, Long storeId, Integer ratingFilter,
                                                             int page, int size) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Review> result = reviewRepository.findAllByStore(store,pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);

    }

    public ReviewResDto.Detail createReview(ReviewReqDto.ReviewCreate dto, Long storeId) {

        // !!!! 이후에 멤버ID 꺼내와서 수행, validation 검증 책임 분리 필요 !!!!
        Long userId = 1L;
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // Store 찾기 (컨트롤러 계층에서 validation check 완료)
        Store store = storeRepository.getReferenceById(storeId);

        //리뷰 생성
        Review review = ReviewConverter.toReview(dto, store, member);
        //저장
        reviewRepository.save(review);


        //!!!! dto.imageUrls()를 통해 모든 url에 대해 reviewImage 객체 생성 및 연결과정 필요 !!!!

        return ReviewConverter.toReviewResponseDto(review);
    }

    public ReviewResDto.ReviewPreViewListDTO findMyReviews(Long memberId, Long storeId, Integer ratingFilter, Pageable pageable) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Page<Review> result = reviewRepository.findAllByMember(member, pageable);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }


}