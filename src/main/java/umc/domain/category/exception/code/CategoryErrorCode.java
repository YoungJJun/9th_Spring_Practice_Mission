package umc.domain.category.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum CategoryErrorCode implements BaseErrorCode {

    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY404_1", "존재하지 않는 카테고리입니다."),
    CATEGORY_NAME_DUPLICATED(HttpStatus.CONFLICT, "CATEGORY409_1", "이미 존재하는 카테고리 이름입니다."),
    CATEGORY_CANNOT_DELETE(HttpStatus.BAD_REQUEST, "CATEGORY400_1", "해당 카테고리는 삭제할 수 없습니다."),
    INVALID_CATEGORY_ID(HttpStatus.BAD_REQUEST, "CATEGORY400_2", "잘못된 카테고리 ID 형식입니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
