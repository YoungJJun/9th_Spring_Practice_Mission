package umc.domain.home.converter;



import umc.domain.home.dto.HomeResDto;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberResDto;
import umc.domain.member.entity.Member;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.entity.Mission;

import java.util.List;

public class HomeConverter {

    public static HomeResDto.HomeInfoDto toHomeInfoDto(
            Member member,
            int completedMissionRate,
            List<Mission> availableMissions
    ) {

        //Member -> MyPageMemberDto
        MemberResDto.MyPageMemberDto memberInfo = MemberConverter.toMyPageMemberDto(member);

        //Mission 리스트 -> SimpleMissionDto 리스트
        List<MissionResDto.SimpleMissionDto> missionDtos = availableMissions.stream()
                .map(MissionConverter::toSimpleMissionDto)
                .toList();

        //HomeInfoDto
        return HomeResDto.HomeInfoDto.builder()
                .memberInfo(memberInfo)
                .completedMissionRate(completedMissionRate)
                .availableMissions(missionDtos)
                .build();
    }
}