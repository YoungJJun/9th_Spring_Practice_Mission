package umc.domain.mission.dto;

import lombok.Builder;

public class MissionResDto {

    @Builder
    public record SimpleMissionDto(
            Long storeId,       //리뷰 남기기로 바로 연결
            String storeName,   //가게이름
            String content,     //미션내용
            int reward          //미션 보상
    ) {}
}
