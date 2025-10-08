package umc.domain.store.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.global.BaseEntity;
import umc.global.enums.Address;

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

    @Column(name="address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name="detail_address", nullable = true, length = 100)
    private String detailAddress;
}
