package ddss.catalog.data;

import ddss.catalog.domain.CatalogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceUserRepository extends JpaRepository<CatalogUser, Integer> {

    CatalogUser findByUsername(String username);

}