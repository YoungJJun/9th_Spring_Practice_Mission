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

    //미션 완료시 response dto
    @Builder
    public record complete(
            Long memberMissionId,
            Long missionId,
            MissionStatus status,
            int addedPoint
    ){}
}
