package umc.domain.store.converter;

import umc.domain.Region.entity.Region;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.entity.Mission;
import umc.domain.store.dto.StoreReqDto;
import umc.domain.store.dto.StoreResDto;
import umc.domain.store.entity.Store;

public class StoreConverter {

    //StoreReqDto.Create -> Store
    public static Store toStore(StoreReqDto.Create dto, Region region) {
        return Store.builder()
                .name(dto.name())
                .ownerNumber(dto.ownerNumber())
                .detailAddress(dto.detailAddress())
                .region(region)
                .build();
    }

    //Store -> StoreResDto.Create
    public static StoreResDto.Create toStoreResDto(Store store) {
        return StoreResDto.Create.builder()
                .name(store.getName())
                .ownerNumber(store.getOwnerNumber())
                .detailAddress(store.getDetailAddress())
                .regionId(store.getRegion().getId())
                .build();
    }

}
