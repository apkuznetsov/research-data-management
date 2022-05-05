package ddss.security;

import ddss.domain.DeviceUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String about;

    public RegistrationForm() {
    }

    public RegistrationForm(String username, String password, String about) {
        this.username = username;
        this.password = password;
        this.about = about;
    }

    public DeviceUser toDeviceUser(PasswordEncoder passwordEncoder) {
        return new DeviceUser(
                username, passwordEncoder.encode(password),
                about);
    }
}