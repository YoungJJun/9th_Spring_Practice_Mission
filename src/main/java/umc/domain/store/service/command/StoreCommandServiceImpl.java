package umc.domain.store.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.Region.entity.Region;
import umc.domain.Region.repository.RegionRepository;
import umc.domain.store.converter.StoreConverter;
import umc.domain.store.dto.StoreReqDto;
import umc.domain.store.dto.StoreResDto;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;


@RequiredArgsConstructor
@Service
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional
    @Override
    public StoreResDto.Create createStore(
            StoreReqDto.Create dto
    ) {
        //지역 찾기 (store에 연결필요) !!!! valid로 구현 분리해야 함 !!!!!
        Region region = regionRepository.findById(dto.regionId())
                .orElseThrow(()-> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        //dto를 이용해 store 생성
        Store store = StoreConverter.toStore(dto,region);
        //저장
        storeRepository.save(store);

        return StoreConverter.toStoreResDto(store);
    }
}
