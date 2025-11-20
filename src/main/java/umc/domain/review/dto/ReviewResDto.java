package umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
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

}