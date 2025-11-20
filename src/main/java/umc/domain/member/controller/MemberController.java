package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.command.MemberCommandService;
import umc.domain.member.service.query.MemberQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    // 마이페이지 정보 조회
    @GetMapping("/mypage")
    public ApiResponse<MemberResDTO.MyPageMemberDto> getMyPage(@RequestParam Long memberId){
        MemberResDTO.MyPageMemberDto response = memberQueryService.getMyPageInfo(memberId);
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, response);
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @Valid @RequestBody MemberReqDTO.JoinDTO dto
    ){

        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.register(dto));
    }


}
