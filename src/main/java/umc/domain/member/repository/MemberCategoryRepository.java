package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.mapping.MemberCategory;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {
}
