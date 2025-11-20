package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.dto.MemberMissionResDto;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.enums.MissionStatus;
import umc.domain.member.repository.MemberMissionRepository;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionReqDto;
import umc.domain.mission.dto.MissionResDto;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.exception.MissionException;
import umc.domain.mission.exception.code.MissionErrorCode;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public List<MissionResDto.SimpleMissionDto> getCompletedMissionsByMemberId(Long memberId) {
        return missionRepository.findCompletedMissionByMemberId(memberId)
                .stream()
                .map(MissionConverter::toSimpleMissionDto)
                .toList();
    }

    public List<MissionResDto.SimpleMissionDto> getInProgressMissionsByMemberId(Long memberId) {
        return missionRepository.findInProgressMissionByMemberId(memberId)
                .stream()
                .map(MissionConverter::toSimpleMissionDto)
                .toList();
    }

    public MissionResDto.SimpleMissionDto createMission(
            Long storeId,
            MissionReqDto.create dto
    ){
        // !!!!! 여기서는 getRef 성능이득 없어보여서 수정해도 될 것 같음 !!!!!
        Store store = storeRepository.getReferenceById(storeId);

        //dto -> mission
        Mission mission = MissionConverter.toMission(dto,store);

        missionRepository.save(mission);

        return MissionConverter.toSimpleMissionDto(mission);
    }

    //미션 도전 -> 리팩토링 요소 많음
    //converter 추가해서 변환로직 분리 해야함
    //각 케이스에 대해서도 제대로 정리필요
    @Transactional
    public MemberMissionResDto.challenge challengeMission(
            Long memberId,
            Long missionId
    ){
        //미션 존재 valid 체크 완료, memberId는 토큰에서 꺼냈으니 문제없는 상황

        Mission mission = missionRepository.getReferenceById(missionId);
        Member member = memberRepository.getReferenceById(memberId);

        //기존 상태체크
        Optional<MemberMission> mappingOpt =
                memberMissionRepository.findByMember_IdAndMission_Id(memberId,missionId);

        if(mappingOpt.isEmpty()){ //최초 도전 상황 !!!! 컨버터 적용 필요 !!!!
            MemberMission newChallenge = MemberMission.builder()
                    .member(member)
                    .mission(mission)
                    .status(MissionStatus.IN_PROGRESS)  // 도전 시작
                    .build();

            memberMissionRepository.save(newChallenge);

            return MemberMissionResDto.challenge.builder()
                    .memberMissionId(newChallenge.getId())
                    .missionId(newChallenge.getMission().getId())
                    .memberId(newChallenge.getMember().getId())
                    .status(newChallenge.getStatus())
                    .build();
        }

        MemberMission mapping = mappingOpt.get();
        switch (mapping.getStatus()) {
            case IN_PROGRESS -> {
                throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
            }
            case COMPLETED -> {
                throw new MissionException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
            }
            case ABANDONED -> {
                // 포기 → 다시 도전 가능 → 상태 변경 !!!! 컨버터 적용필요 !!!!
                mapping.updateStatus(MissionStatus.IN_PROGRESS);
                return MemberMissionResDto.challenge.builder()
                        .memberMissionId(mapping.getId())
                        .missionId(mapping.getMission().getId())
                        .memberId(memberId)
                        .status(MissionStatus.IN_PROGRESS)
                        .build();
            }
        }
        throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
    }

}