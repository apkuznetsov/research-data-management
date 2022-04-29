package ddss.security;

import ddss.data.DeviceUserRepository;
import ddss.domain.DeviceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeviceUserRepositoryUserDetailsService
        implements UserDetailsService {

    private final DeviceUserRepository repo;

    @Autowired
    public DeviceUserRepositoryUserDetailsService(DeviceUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        DeviceUser user = repo.findByUsername(username);
        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException(
                "device user '" + username + "' not found");
    }
}
