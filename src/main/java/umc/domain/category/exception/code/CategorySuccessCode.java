package umc.domain.category.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum CategorySuccessCode implements BaseSuccessCode {

    CATEGORY_CREATED(HttpStatus.CREATED, "CATEGORY201_1", "카테고리가 성공적으로 생성되었습니다."),
    CATEGORY_FOUND(HttpStatus.OK, "CATEGORY200_1", "카테고리가 성공적으로 조회되었습니다."),
    CATEGORY_UPDATED(HttpStatus.OK, "CATEGORY200_2", "카테고리가 성공적으로 수정되었습니다."),
    CATEGORY_DELETED(HttpStatus.OK, "CATEGORY200_3", "카테고리가 성공적으로 삭제되었습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
