package umc.global.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.domain.mission.exception.code.MissionErrorCode;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.global.annotation.ExistMission;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {

    private final MissionRepository missionRepository;
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;

        boolean exists = missionRepository.existsById(value);

        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MissionErrorCode.MISSION_NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }

        return exists;
    }
}
