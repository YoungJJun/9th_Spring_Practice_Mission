package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
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
    public static Mission toMission(MissionReqDto.CreateMission dto, Store store){
        return Mission.builder()
                .deadline(dto.deadline())
                .content(dto.content())
                .reward(dto.reward())
                .store(store)
                .build();
    }

    // Mission -> Res.MissionPreviewDTO
    public static MissionResDto.MissionPreviewDTO toMissionPreviewDTO(Mission m) {
        return MissionResDto.MissionPreviewDTO.builder()
                .missionId(m.getId())
                .storeId(m.getStore().getId())
                .storeName(m.getStore().getName())
                .content(m.getContent())
                .reward(m.getReward())
                .deadline(m.getDeadline())
                .build();
    }
    // Page<Mission> -> Res.MissionPreviewListDTO
    public static MissionResDto.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> page) {
        return MissionResDto.MissionPreviewListDTO.builder()
                .missionList(page.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList())
                .listSize(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

}
