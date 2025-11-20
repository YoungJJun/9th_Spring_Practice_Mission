package umc.domain.member.entity;
import jakarta.persistence.*;
import lombok.*;
import umc.domain.Region.entity.Region;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.SocialType;
import umc.global.BaseEntity;



import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length = 20, nullable = false)
    private String name;

    @Column(name="gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name="birth")
    private LocalDate birth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name="detail_address", length = 100)
    private String detailAddress;

    @Column(name="social_uid")
    private String socialUid;

    @Column(name="social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name="point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name="email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name="phone_number", length = 20, unique = true)
    private String phoneNumber;

    @Column(name="password", length = 255)
    private String password;

    @Column(name="nickname", length = 30, unique = true)
    private String nickname;



}
