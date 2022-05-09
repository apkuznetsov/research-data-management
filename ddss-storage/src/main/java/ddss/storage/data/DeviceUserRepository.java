package ddss.storage.data;

import ddss.storage.domain.DeviceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceUserRepository extends JpaRepository<DeviceUser, Integer> {

    DeviceUser findByUsername(String username);

}