package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberQueryDsl {

    /* refactor - findById로 대체하고 converter 추가해서 변환로직 분리
    @Query("SELECT new umc.domain.member.dto.MyPageMemberDto(m.nickname, m.email, m.phone_number, m.point) FROM Member m WHERE m.id = :memberId")
    Optional<MyPageMemberDto> findMyPageInfoByMemberId(@Param("memberId") Long memberId);
    */

}
