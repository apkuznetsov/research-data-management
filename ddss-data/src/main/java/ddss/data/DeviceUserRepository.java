package ddss.data;

import ddss.domain.DeviceUser;
import org.springframework.data.repository.CrudRepository;

public interface DeviceUserRepository extends CrudRepository<DeviceUser, Long> {

    DeviceUser findByUsername(String username);

}