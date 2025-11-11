package umc.domain.home.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.home.converter.HomeConverter;
import umc.domain.home.dto.HomeResDto;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public HomeResDto.HomeInfoDto getHomeInfo(Long memberId, Long regionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

        int completedMissionRate = missionRepository.countCompletedMissionMod10(memberId, regionId);

        List<Mission> availableMissions =
                missionRepository.findAvailableMissionByMemberAndRegion(memberId, regionId);

        return HomeConverter.toHomeInfoDto(member, completedMissionRate, availableMissions);
    }
}
