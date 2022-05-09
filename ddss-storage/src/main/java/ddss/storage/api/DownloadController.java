package ddss.storage.api;

import ddss.storage.domain.DeviceUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/storage")
public class DownloadController {

    @PostMapping(value = "/download", consumes = "application/json")
    public ResponseEntity<String> download(@AuthenticationPrincipal DeviceUser user) {
        return new ResponseEntity<>("data downloaded", HttpStatus.OK);
    }
}
