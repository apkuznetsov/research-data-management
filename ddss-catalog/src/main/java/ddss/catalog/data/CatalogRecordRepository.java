package ddss.catalog.data;

import ddss.catalog.domain.CatalogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRecordRepository extends JpaRepository<CatalogRecord, Integer> {

    Optional<CatalogRecord> findById(Integer id);

}
