package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.global.annotation.ValidPage;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    private int min;

    @Override
    public void initialize(ValidPage annotation) {
        this.min = annotation.value(); // 기본 1
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 값이 아예 없으면(미전달) 통과, 값이 있으면 min 이상이어야 함
        if (value == null) return true;
        return value >= min;
    }
}
