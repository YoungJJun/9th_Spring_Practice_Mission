package umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDto {

    @Builder
    public record SimpleMissionDto(
            Long missionId,
            Long storeId,       //리뷰 남기기로 바로 연결
            String storeName,   //가게이름
            String content,     //미션내용
            int reward          //미션 보상
    ) {}

    //미션정보 Page 사용
    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            Long storeId,
            String storeName,
            String content,
            int reward,
            LocalDateTime deadline
    ){}

    @Builder
    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MissionComplete(
            Long memberId,
            Long missionId,
            Long memberMissionId,
            int reward
    ){}
}
