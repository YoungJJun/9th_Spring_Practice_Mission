package umc.domain.member.converter;

import umc.domain.member.dto.MemberResDto;
import umc.domain.member.entity.Member;

public class MemberConverter {

    // member -> MemberResDto.MyPageMemberDto
    public static MemberResDto.MyPageMemberDto toMyPageMemberDto(Member member) {
        return MemberResDto.MyPageMemberDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}
