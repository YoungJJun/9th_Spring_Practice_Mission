package umc.domain.store.service.command;


import umc.domain.store.dto.StoreReqDto;
import umc.domain.store.dto.StoreResDto;

public interface StoreCommandService {
    public StoreResDto.Create createStore(
            StoreReqDto.StoreCreate dto
    );
}
