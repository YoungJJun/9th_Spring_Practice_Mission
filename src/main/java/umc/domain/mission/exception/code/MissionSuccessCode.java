package umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
