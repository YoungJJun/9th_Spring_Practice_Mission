package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum MemberMissionErrorCode implements BaseErrorCode {
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_MISSION_404_1", "존재하지 않는 멤버미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
