package ddss.security;

import ddss.domain.DeviceUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String about;

    public DeviceUser toDeviceUser(PasswordEncoder passwordEncoder) {
        return new DeviceUser(
                username, passwordEncoder.encode(password),
                about);
    }
}