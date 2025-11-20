package umc.domain.member.service.query;

import umc.domain.member.dto.MemberResDTO;

public interface MemberQueryService {

    MemberResDTO.MyPageMemberDto getMyPageInfo(Long memberId);
}
