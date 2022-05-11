package ddss.catalog.security;

import ddss.catalog.data.DeviceUserRepository;
import ddss.catalog.domain.CatalogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {

    @Autowired
    private DeviceUserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/cat/register", consumes = "application/json")
    public ResponseEntity<RegistrationForm> register(@RequestBody RegistrationForm form) {
        CatalogUser foundUser = userRepo.findByUsername(form.getUsername());
        if (foundUser == null) {
            userRepo.save(form.toDeviceUser(bCryptPasswordEncoder));
            return new ResponseEntity<>(form, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(form, HttpStatus.BAD_REQUEST);
        }
    }
}