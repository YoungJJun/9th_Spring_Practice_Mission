package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findByMember_IdAndMission_Id(Long memberId, Long missionId);
}
