package umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰가 성공적으로 등록되었습니다."),

    UPDATED(HttpStatus.OK,
            "REVIEW200_1",
            "리뷰가 성공적으로 수정되었습니다."),

    DELETED(HttpStatus.OK,
            "REVIEW200_2",
            "리뷰가 성공적으로 삭제되었습니다."),

    FOUND(HttpStatus.OK,
            "REVIEW200_3",
            "리뷰 조회 성공입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}