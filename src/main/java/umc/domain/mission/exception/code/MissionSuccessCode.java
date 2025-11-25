package umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "MISSION201_1",
            "미션이 성공적으로 등록되었습니다."),

    UPDATED(HttpStatus.OK,
            "MISSION200_1",
            "미션이 성공적으로 수정되었습니다."),

    DELETED(HttpStatus.OK,
            "MISSION200_2",
            "미션이 성공적으로 삭제되었습니다."),

    FOUND(HttpStatus.OK,
            "MISSION200_3",
            "미션 조회 성공입니다."),

    LIST_FOUND(HttpStatus.OK,
            "MISSION200_4",
            "미션 목록 조회 성공입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
