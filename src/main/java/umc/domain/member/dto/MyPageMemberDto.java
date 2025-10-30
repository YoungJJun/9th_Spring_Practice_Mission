package umc.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//MyPage에 필요한 정보만을 담을 때 사용하는 Dto

public class MyPageMemberDto {
    private String nickname;
    private String email;
    private String phoneNumber;
    private Integer point;
}
