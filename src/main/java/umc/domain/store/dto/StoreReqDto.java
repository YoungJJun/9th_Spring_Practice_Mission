package umc.domain.store.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import umc.domain.Region.entity.Region;

public class StoreReqDto {

    public record Create(
        String name,
        String ownerNumber,
        Long regionId,
        String detailAddress
    ){}
}
