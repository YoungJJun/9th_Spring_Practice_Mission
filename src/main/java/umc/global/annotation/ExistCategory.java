package umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.global.validator.CategoryExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)

public @interface ExistCategory {
    //여기서 디폴트 메시지를 설정합니다.
    String message() default "해당 카테고리가 존재하지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}