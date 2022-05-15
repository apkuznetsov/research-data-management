package ddss.catalog.data;

import ddss.catalog.domain.CatalogWithStorageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogWithStorageRecordRepository extends JpaRepository<CatalogWithStorageRecord, Integer> {

    CatalogWithStorageRecord findByCatalogRecordId(int catalogRecordId);

}
