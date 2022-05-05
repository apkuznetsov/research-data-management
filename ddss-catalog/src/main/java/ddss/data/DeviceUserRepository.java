package ddss.data;

import ddss.domain.DeviceUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceUserRepository extends JpaRepository<DeviceUser, Integer> {

    DeviceUser findByUsername(String username);

}