package ddss.api;

import ddss.domain.DeviceUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/storage")
public class UploadController {

    @PostMapping(value = "/upload", consumes = "application/json")
    public ResponseEntity<String> upload(@AuthenticationPrincipal DeviceUser user) {
        return new ResponseEntity<>("data uploaded", HttpStatus.OK);
    }
}
