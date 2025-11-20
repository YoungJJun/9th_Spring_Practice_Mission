package umc.domain.home.dto;

import lombok.Builder;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.mission.dto.MissionResDto;

import java.util.List;

public class HomeResDto {

    @Builder
    public record HomeInfoDto(
            MemberResDTO.MyPageMemberDto memberInfo,    //멤버의 포인트가 필요 -> 이후 확장 고려해서 myPage Dto 재사용
            int completedMissionRate,
            List<MissionResDto.SimpleMissionDto> availableMissions
    ) {}
}
