package umc.domain.Region.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum RegionErrorCode implements BaseErrorCode {

    REGION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REGION404_1",
            "존재하지 않는 지역입니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
