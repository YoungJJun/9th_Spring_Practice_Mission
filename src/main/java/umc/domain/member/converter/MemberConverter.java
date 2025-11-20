package umc.domain.member.converter;

import umc.domain.Region.entity.Region;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;

public class MemberConverter {

    // member -> MemberResDto.MyPageMemberDto
    public static MemberResDTO.MyPageMemberDto toMyPageMemberDto(Member member) {
        return MemberResDTO.MyPageMemberDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    // Member -> JoinDTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // JoinDTO -> Member
    public static Member toMember(
            Region region,
            MemberReqDTO.JoinDTO dto
    ){

        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .birth(dto.birth())
                .region(region)
                .detailAddress(dto.detailAddress())
                .gender(dto.gender())
                .build();
    }
}
