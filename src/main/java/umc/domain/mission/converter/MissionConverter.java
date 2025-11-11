package umc.domain.mission.converter;

import lombok.Builder;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.entity.Mission;

public class MissionConverter {

    public static MissionResDto.SimpleMissionDto toSimpleMissionDto(Mission mission){
        return MissionResDto.SimpleMissionDto.builder()
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .reward(mission.getReward())
                .build();
    }
}
