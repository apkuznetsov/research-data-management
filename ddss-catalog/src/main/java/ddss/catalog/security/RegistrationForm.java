package ddss.catalog.security;

import ddss.catalog.domain.CatalogUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String about;
    private String ipAddress;
    private short port;
    private boolean isStorage;

    public RegistrationForm() {
    }

    public RegistrationForm(String username, String password, String about,
                            String ipAddress, short port, boolean isStorage) {
        this.username = username;
        this.password = password;
        this.about = about;
        this.ipAddress = ipAddress;
        this.port = port;
        this.isStorage = isStorage;
    }

    public CatalogUser toDeviceUser(PasswordEncoder passwordEncoder) {
        return new CatalogUser(
                username, passwordEncoder.encode(password), about,
                ipAddress, port, isStorage);
    }
}
