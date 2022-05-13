package ddss.catalog.data;

import ddss.catalog.domain.CatalogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogUserRepository extends JpaRepository<CatalogUser, Integer> {

    CatalogUser findByUsername(String username);

    List<CatalogUser> findAllByIsStorage(boolean isStorage);

}