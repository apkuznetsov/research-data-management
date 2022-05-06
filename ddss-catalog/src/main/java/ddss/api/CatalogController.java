package ddss.api;

import ddss.data.CatalogRecordRepository;
import ddss.domain.CatalogRecord;
import ddss.domain.DeviceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cat")
public class CatalogController {

    @Autowired
    private CatalogRecordRepository catalogRepo;

    @GetMapping(path = "/record/{id}")
    public ResponseEntity<CatalogRecord> getRecordById(@PathVariable int id, @AuthenticationPrincipal DeviceUser user) {
        CatalogRecord record = catalogRepo.findById(id).orElse(null);

        return record == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(record, HttpStatus.OK);
    }
}
