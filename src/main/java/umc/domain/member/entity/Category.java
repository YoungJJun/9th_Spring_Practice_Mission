package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.enums.FoodCategory;
import umc.global.BaseEntity;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name="category")


public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodCategory name;

}
