package umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.global.validator.CategoryExistValidator;
import umc.global.validator.MissionExistValidator;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = MissionExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMission {
    String message() default "해당 미션이 존재하지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
