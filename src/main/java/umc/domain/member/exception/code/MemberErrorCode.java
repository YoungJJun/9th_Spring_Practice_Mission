package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "존재하지 않는 회원입니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "입력한 지역이 존재하지 않습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
