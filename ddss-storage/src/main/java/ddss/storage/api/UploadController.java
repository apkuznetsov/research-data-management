package ddss.storage.api;

import ddss.storage.domain.Data;
import ddss.storage.domain.DeviceUser;
import ddss.storage.domain.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/storage")
public class UploadController {

    @PostMapping(value = "/upload", consumes = "application/json")
    public ResponseEntity<Feedback> upload(@RequestBody Data data, @AuthenticationPrincipal DeviceUser user) {
        Feedback feedback = new Feedback(
                "bytes " + data.getBytes() + " with " + data.getBytes().length() + " length uploaded");
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
}
