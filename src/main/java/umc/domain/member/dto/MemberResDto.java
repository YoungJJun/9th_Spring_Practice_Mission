package umc.domain.member.dto;

import lombok.Builder;



public class MemberResDto {

    @Builder
    public record MyPageMemberDto(
            String nickname,
            String email,
            String phoneNumber,
            Integer point
    ) {}



}
