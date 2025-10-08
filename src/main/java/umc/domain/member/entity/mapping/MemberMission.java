package umc.domain.member.entity.mapping;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.Member;
import umc.domain.member.enums.MissionStatus;
import umc.domain.mission.entity.Mission;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="member_mission")
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mission_id")
    private Mission mission;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private MissionStatus status;
}
