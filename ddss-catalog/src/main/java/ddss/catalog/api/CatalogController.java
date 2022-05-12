package ddss.catalog.api;

import ddss.catalog.data.CatalogRecordRepository;
import ddss.catalog.domain.CatalogRecord;
import ddss.catalog.domain.CatalogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/cat")
public class CatalogController {

    @Autowired
    private CatalogRecordRepository catalogRepo;

    @PostMapping(value = "/record/create", consumes = "application/json")
    public ResponseEntity<CatalogRecord> createRecord(
            @Valid @RequestBody CatalogRecord record, @AuthenticationPrincipal CatalogUser user) {

        record.setUser(user);
        record.setCreatedAt(LocalDateTime.now());
        return new ResponseEntity<>(catalogRepo.save(record), HttpStatus.CREATED);
    }

    @GetMapping(path = "/record/{id}")
    public ResponseEntity<CatalogRecord> getRecordById(
            @PathVariable int id, @AuthenticationPrincipal CatalogUser user) {

        CatalogRecord record = catalogRepo.findById(id).orElse(null);
        return record == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(record, HttpStatus.OK);
    }

    @DeleteMapping("/record/{id}")
    public ResponseEntity deleteRecordById(
            @PathVariable int id, @AuthenticationPrincipal CatalogUser user) {

        ResponseEntity response;

        CatalogRecord record = catalogRepo.findById(id).orElse(null);
        if (record != null) {
            if (record.getCatalogUser().getUsername().equals(user.getUsername())) {
                catalogRepo.deleteById(id);
                response = new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        } else {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return response;
    }
}
