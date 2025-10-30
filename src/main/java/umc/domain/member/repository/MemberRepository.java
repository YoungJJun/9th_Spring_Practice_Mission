package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.dto.MyPageMemberDto;
import umc.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    //마이 페이지 화면 쿼리 (MyPage기준 필요한 정보들만 Dto를 이용해 조회)
    @Query("SELECT new umc.domain.member.dto.MyPageMemberDto(m.nickname, m.email, m.phone_number, m.point) FROM Member m WHERE m.id = :memberId")
    Optional<MyPageMemberDto> findMyPageInfoByMemberId(@Param("memberId") Long memberId);


}
