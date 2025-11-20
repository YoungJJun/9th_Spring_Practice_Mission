package umc.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.Region.entity.Region;
import umc.domain.Region.repository.RegionRepository;
import umc.domain.category.entity.Category;
import umc.domain.category.exception.CategoryException;
import umc.domain.category.exception.code.CategoryErrorCode;
import umc.domain.category.repository.CategoryRepository;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberCategory;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberCategoryRepository;
import umc.domain.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final MemberCategoryRepository memberCategoryRepository;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO register(
            MemberReqDTO.JoinDTO dto
    ){
        Region region = regionRepository.findById(dto.regionId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.REGION_NOT_FOUND));

        //멤버 생성 및 저장
        Member member = MemberConverter.toMember(region,dto);
        memberRepository.save(member);


        // 선호 음식 존재 여부 확인 및 매핑 테이블에 저장
        if (!dto.preferCategory().isEmpty()) {
            List<MemberCategory> memberCategoryList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()) {

                // 음식 존재 여부 검증
                Category category = categoryRepository.getReferenceById(id);

                // MemberCategory 생성
                MemberCategory memberCategory = MemberCategory.builder()
                        .member(member)
                        .category(category)
                        .build();

                // 리스트에 추가
                memberCategoryList.add(memberCategory);
            }

            memberCategoryRepository.saveAll(memberCategoryList);

        }

        return MemberConverter.toJoinDTO(member);
    }


}
