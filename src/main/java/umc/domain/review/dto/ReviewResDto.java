package umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResDto {

    private Long reviewId;
    private String content;
    private Double rating;
    private String storeName;
    private LocalDateTime createdAt;

    private List<String> imageUrls;
    private List<ReplyDto> replies; //답글 id, content, createdAt 포함
}