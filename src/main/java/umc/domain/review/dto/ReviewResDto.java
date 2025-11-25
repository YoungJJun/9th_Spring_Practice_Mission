package umc.domain.review.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class ReviewResDto {

    @Builder
    public record Detail(
            Long reviewId,
            Long storeId,
            String content,
            Double rating,
            LocalDateTime createdAt,

            List<String> imageUrls,
            List<ReviewReplyDto> replies //답글 id, content, createdAt 포함
    ){}

    //워크북 - 리뷰 리스트 (페이징 공부)
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
    //워크북 - 리뷰 (페이징 공부)
    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Double score,
            String body,
            LocalDate createdAt
    ){}
}