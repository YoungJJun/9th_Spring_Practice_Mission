package umc.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    DUPLICATE_STORE_NAME(HttpStatus.CONFLICT,
            "STORE409_1",
            "이미 동일한 이름의 가게가 존재합니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "존재하지 않는 가게입니다.");



    private final HttpStatus status;
    private final String code;
    private final String message;
}
