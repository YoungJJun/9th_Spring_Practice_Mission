package umc.domain.mission.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;
import umc.global.annotation.ExistStore;

import java.time.LocalDateTime;

public class MissionReqDto {

    @Builder
    public record create(
            @NotBlank
            String content,

            @PositiveOrZero
            int reward,

            @FutureOrPresent
            LocalDateTime deadline
    ) {}
}
