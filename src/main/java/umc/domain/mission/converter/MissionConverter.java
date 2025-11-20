package umc.domain.mission.converter;

import lombok.Builder;
import umc.domain.mission.dto.MissionReqDto;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Store;

public class MissionConverter {

    //ResSimple -> mission
    public static MissionResDto.SimpleMissionDto toSimpleMissionDto(Mission mission){
        return MissionResDto.SimpleMissionDto.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .reward(mission.getReward())
                .build();
    }

    //MissionReqDto.create -> mission
    public static Mission toMission(MissionReqDto.create dto, Store store){
        return Mission.builder()
                .deadline(dto.deadline())
                .content(dto.content())
                .reward(dto.reward())
                .store(store)
                .build();
    }
}
