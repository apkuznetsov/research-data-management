package ddss.security;

import ddss.data.DeviceUserRepository;
import ddss.domain.DeviceUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cat/register")
public class RegistrationController {

    private final DeviceUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(
            DeviceUserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<RegistrationForm> processRegistration(RegistrationForm form) {
        DeviceUser foundUser = userRepo.findByUsername(form.getUsername());
        if (foundUser == null) {
            userRepo.save(form.toDeviceUser(passwordEncoder));
            return new ResponseEntity<>(form, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(form, HttpStatus.BAD_REQUEST);
        }
    }
}