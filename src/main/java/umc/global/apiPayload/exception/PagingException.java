package umc.global.apiPayload.exception;

import umc.global.apiPayload.code.BaseErrorCode;

public class PagingException extends GeneralException {
    public PagingException(BaseErrorCode code) {super(code);}
}
