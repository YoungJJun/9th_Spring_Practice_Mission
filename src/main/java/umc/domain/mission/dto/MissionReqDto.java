package umc.domain.mission.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionReqDto {

    @Builder
    public record CreateMission(
            @NotBlank
            String content,

            @PositiveOrZero
            int reward,

            @FutureOrPresent
            LocalDateTime deadline
    ) {}
}
