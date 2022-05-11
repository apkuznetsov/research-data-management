package ddss.catalog.security;

import ddss.catalog.domain.CatalogUser;
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

    public CatalogUser toDeviceUser(PasswordEncoder passwordEncoder) {
        return new CatalogUser(
                username, passwordEncoder.encode(password),
                about);
    }
}