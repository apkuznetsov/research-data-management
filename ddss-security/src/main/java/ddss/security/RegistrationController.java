package ddss.security;

import ddss.data.DeviceUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cat/register")
public class RegistrationController {

    private final DeviceUserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(
            DeviceUserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }
}