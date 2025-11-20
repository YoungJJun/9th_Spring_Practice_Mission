package umc.domain.member.dto;

import umc.domain.member.enums.Gender;
import umc.global.annotation.ExistCategory;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            String email,
            Gender gender,
            LocalDate birth,
            Long regionId,
            String detailAddress,
            @ExistCategory
            List<Long> preferCategory
    ){}
}
