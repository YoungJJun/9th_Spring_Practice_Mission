package umc.domain.store.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.Region.entity.Region;
import umc.global.BaseEntity;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="store")


public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    @Column(name="owner_number", nullable = false, length = 50)
    private String ownerNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name="detail_address", nullable = true, length = 100)
    private String detailAddress;
}
