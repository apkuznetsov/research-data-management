package ddss.catalog.data;

import ddss.catalog.domain.CatalogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRecordRepository extends JpaRepository<CatalogRecord, Integer> {
}
