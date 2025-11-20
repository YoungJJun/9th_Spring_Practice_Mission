package umc.domain.member.service.command;

import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;

public interface MemberCommandService {

    MemberResDTO.JoinDTO register(MemberReqDTO.JoinDTO dto);
}
