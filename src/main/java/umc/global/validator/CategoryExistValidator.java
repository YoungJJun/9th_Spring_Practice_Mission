package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.domain.category.exception.code.CategoryErrorCode;
import umc.domain.category.repository.CategoryRepository;
import umc.global.annotation.ExistCategory;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryExistValidator implements ConstraintValidator<ExistCategory, List<Long>> {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> categoryRepository.existsById(value));

        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(CategoryErrorCode.CATEGORY_NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}
