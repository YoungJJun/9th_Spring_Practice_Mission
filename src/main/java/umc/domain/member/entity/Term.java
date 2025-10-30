package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.enums.TermName;
import umc.global.BaseEntity;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="term")

public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TermName name;

}
