package umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ReviewReplyDto {

    private Long replyId;          // 답글 ID
    private String content;        // 답글 내용
    private LocalDateTime createdAt; // 작성 시간
}