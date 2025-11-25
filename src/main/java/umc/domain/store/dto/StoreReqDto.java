package umc.domain.store.dto;

public class StoreReqDto {

    public record StoreCreate(
        String name,
        String ownerNumber,
        Long regionId,
        String detailAddress
    ){}
}
