package umc.domain.member.converter;

import umc.domain.member.dto.MemberMissionResDto;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.enums.MissionStatus;

public class MemberMissionConverter {

    public static MemberMissionResDto.complete toCompleteDto(MemberMission mm) {
        return MemberMissionResDto.complete.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .status(mm.getStatus())
                .addedPoint(mm.getMission().getReward())
                .build();
    }
}

