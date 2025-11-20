package umc.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.store.entity.Store;
import umc.global.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name="content", nullable=false, length=500)
    private String content;

    @Column(name="reward", nullable=false) //point
    private int reward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;
}
