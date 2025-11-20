package umc.domain.member.dto;

import lombok.Builder;
import umc.domain.member.enums.MissionStatus;

public class MemberMissionResDto {

    //미션 도전시 response dto
    @Builder
    public record challenge(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            MissionStatus status
    ){}
}
