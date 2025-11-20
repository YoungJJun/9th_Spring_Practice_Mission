package umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import umc.domain.store.entity.Store;


public interface StoreRepository extends JpaRepository<Store,Long> {

}
