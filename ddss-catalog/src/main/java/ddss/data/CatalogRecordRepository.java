package ddss.data;

import ddss.domain.CatalogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRecordRepository extends JpaRepository<CatalogRecord, Integer> {
}
