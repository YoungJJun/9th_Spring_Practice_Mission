package umc.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
