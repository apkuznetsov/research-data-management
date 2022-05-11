package ddss.storage.api;

import ddss.storage.data.DepositRepository;
import ddss.storage.domain.Data;
import ddss.storage.domain.Deposit;
import ddss.storage.domain.CatalogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/storage")
public class DownloadController {

    @Autowired
    DepositRepository depositRepo;

    @GetMapping(value = "/download/{catalogRecordId}", consumes = "application/json")
    public ResponseEntity<Data> download(
            @PathVariable int catalogRecordId, @AuthenticationPrincipal CatalogUser user) {

        Deposit deposit = depositRepo.findByCatalogRecordId(catalogRecordId);

        if (deposit != null) {
            Data data = new Data(deposit.getData());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/download/all/{catalogRecordId}", consumes = "application/json")
    public ResponseEntity<List<Data>> downloadAll(
            @PathVariable int catalogRecordId, @AuthenticationPrincipal CatalogUser user) {

        List<Deposit> depositList = depositRepo.findAllByCatalogRecordId(catalogRecordId);

        if (!depositList.isEmpty()) {
            List<Data> dataList = depositList.stream()
                    .map(deposit -> new Data(deposit.getData()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(dataList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
