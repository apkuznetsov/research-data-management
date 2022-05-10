package ddss.storage.data;

import ddss.storage.domain.Deposit;
import ddss.storage.domain.DeviceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    Deposit findDepositByCatalogRecordId(int catalogRecordId);

}
