package umc.domain.store.dto;

import lombok.Builder;

public class StoreResDto {

    @Builder
    public record Create(
            String name,
            String ownerNumber,
            Long regionId,
            String detailAddress
    ){}
}
