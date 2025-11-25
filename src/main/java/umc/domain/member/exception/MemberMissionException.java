package umc.domain.member.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.GeneralException;

public class MemberMissionException extends GeneralException {
    public MemberMissionException(BaseErrorCode code) {
        super(code);
    }
}
