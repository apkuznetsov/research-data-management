package ddss.device.security;

import lombok.Data;

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
}
