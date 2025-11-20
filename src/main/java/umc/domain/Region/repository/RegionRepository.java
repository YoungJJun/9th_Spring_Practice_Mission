package umc.domain.Region.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.Region.entity.Region;

public interface RegionRepository extends JpaRepository<Region,Long> {
}
