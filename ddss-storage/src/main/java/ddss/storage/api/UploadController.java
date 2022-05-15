package ddss.storage.api;

import ddss.storage.data.DepositRepository;
import ddss.storage.domain.CatalogUser;
import ddss.storage.domain.Data;
import ddss.storage.domain.Deposit;
import ddss.storage.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/storage")
public class UploadController {

    @Autowired
    DepositRepository depositRepo;

    @PostMapping(value = "/upload/{catalogRecordId}", consumes = "application/json")
    public ResponseEntity<Feedback> upload(
            @PathVariable int catalogRecordId, @RequestBody Data data, @AuthenticationPrincipal CatalogUser user) {

        Feedback feedback = new Feedback(
                "bytes " + data.getBytes() + " with " + data.getBytes().length() + " length uploaded");

        Deposit deposit = new Deposit(catalogRecordId, LocalDateTime.now(), data.getBytes().getBytes());
        depositRepo.save(deposit);

        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }
}
