package ddss.storage.data;

import ddss.storage.domain.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    Deposit findByCatalogRecordId(int catalogRecordId);

    List<Deposit> findAllByCatalogRecordId(int catalogRecordId);

}
