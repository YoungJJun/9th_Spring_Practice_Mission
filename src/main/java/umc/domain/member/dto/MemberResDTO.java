package umc.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;


public class MemberResDTO {

    @Builder
    public record MyPageMemberDto(
            String nickname,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}


}
