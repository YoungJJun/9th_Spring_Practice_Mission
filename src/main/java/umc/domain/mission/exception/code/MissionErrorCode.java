package umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    // 404
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION_404_1",
            "존재하지 않는 미션입니다."),
    MISSION_ALREADY_CHALLENGED(HttpStatus.CONFLICT,
            "MISSION_409_1",
            "이미 도전 중인 미션입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.CONFLICT,
            "MISSION_409_2",
            "이미 완료한 미션입니다."),
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST,
            "MISSION_400_3",
            "유효하지 않은 미션 상태입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
