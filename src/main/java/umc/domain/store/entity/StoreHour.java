package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.store.enums.DayOfWeek;
import umc.global.BaseEntity;
import umc.global.enums.Address;

import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="store_hour")
public class StoreHour extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name="open_time", nullable = false)
    private LocalTime openTime;

    @Column(name="close_time", nullable = false)
    private LocalTime closeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id", nullable = false)
    private Store store;
}
