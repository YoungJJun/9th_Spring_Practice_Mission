package umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PagingErrorCode implements BaseErrorCode {

    INVALID_PAGE(HttpStatus.BAD_REQUEST, "E-PAGING-400", "page는 1 이상이어야 합니다."),
    INVALID_PAGE_FORMAT(HttpStatus.BAD_REQUEST, "E-PAGING-401", "page 파라미터 형식이 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
