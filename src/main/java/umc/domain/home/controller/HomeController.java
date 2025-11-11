package umc.domain.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.home.dto.HomeResDto;
import umc.domain.home.service.HomeService;
import umc.domain.member.repository.MemberRepository;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public ApiResponse<HomeResDto.HomeInfoDto> getHomeInfo(
            @RequestParam Long memberId,
            @RequestParam Long regionId
    ) {
        HomeResDto.HomeInfoDto homeInfo = homeService.getHomeInfo(memberId, regionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, homeInfo);
    }


}
