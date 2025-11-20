package umc.domain.category.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.GeneralException;

public class CategoryException extends GeneralException {
    public CategoryException(BaseErrorCode code) {
        super(code);
    }
}
