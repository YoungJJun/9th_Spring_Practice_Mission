package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.dto.MemberResDto;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/mypage")
    public ApiResponse<MemberResDto.MyPageMemberDto> getMyPage(@RequestParam Long memberId){
        MemberResDto.MyPageMemberDto response = memberService.getMyPageInfo(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
