package ddss.catalog.api;

import ddss.catalog.DdssCatalogProps;
import ddss.catalog.data.CatalogRecordRepository;
import ddss.catalog.data.CatalogUserRepository;
import ddss.catalog.data.CatalogWithStorageRecordRepository;
import ddss.catalog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static ddss.catalog.api.HttpClient.client;
import static ddss.catalog.api.HttpClient.createHeaders;

@Controller
@RequestMapping("/cat/storage")
public class StorageInteractionController {

    private final DdssCatalogProps props;
    private final CatalogUserRepository userRepo;
    private final CatalogRecordRepository recordRepo;
    private final CatalogWithStorageRecordRepository withStorageRecordRepo;

    private final HttpEntity<AvailableMegabytesNumber> request;

    @Autowired
    public StorageInteractionController(DdssCatalogProps props,
                                        CatalogUserRepository userRepo,
                                        CatalogRecordRepository recordRepo,
                                        CatalogWithStorageRecordRepository withStorageRecordRepo) {

        this.props = props;
        this.userRepo = userRepo;
        this.recordRepo = recordRepo;
        this.withStorageRecordRepo = withStorageRecordRepo;

        HttpHeaders headers = createHeaders(props.getAdminUsername(), props.getAdminPassword());
        request = new HttpEntity<>(new AvailableMegabytesNumber(), headers);
    }

    @GetMapping(path = "/available/{id}")
    public ResponseEntity<CatalogStorage> getAvailableStorageAndPrepareUploading(
            @PathVariable int id, @AuthenticationPrincipal CatalogUser user) {

        List<CatalogUser> storageUsers = userRepo.findAllByIsStorage(true);
        ResponseEntity<CatalogUser> availableStorageUserResponse = findAvailableStorage(storageUsers);

        if (availableStorageUserResponse.getStatusCode() == HttpStatus.OK) {
            CatalogRecord recordWithSuchId = recordRepo.findById(id).orElse(null);
            if (recordWithSuchId == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            CatalogWithStorageRecord recordWithStorage = new CatalogWithStorageRecord(true);
            recordWithStorage.setCatalogUser(availableStorageUserResponse.getBody());
            recordWithStorage.setCatalogRecord(recordWithSuchId);

            withStorageRecordRepo.save(recordWithStorage);
        }

        CatalogUser availableStorageUser = availableStorageUserResponse.getBody();
        CatalogStorage availableStorage = null;
        if (availableStorageUser != null) {
            availableStorage = new CatalogStorage(availableStorageUser.getId(), availableStorageUser.getAbout(),
                    availableStorageUser.getIpAddress(), availableStorageUser.getPort());
        }

        return new ResponseEntity<>(availableStorage, availableStorageUserResponse.getStatusCode());
    }

    private ResponseEntity<CatalogUser> findAvailableStorage(List<CatalogUser> storageUsers) {
        AvailableMegabytesNumber result;
        for (CatalogUser storage : storageUsers) {

            ResponseEntity<AvailableMegabytesNumber> response = client
                    .exchange(storage + props.getAvailableMegabytesUrl(),
                            HttpMethod.GET, request, AvailableMegabytesNumber.class);
            result = response.getBody();
            if (result == null) {
                continue;
            }

            if (result.getValue() >= props.getMinAvailableMegabytes()) {
                return new ResponseEntity<>(storage, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/record/{id}")
    public ResponseEntity<CatalogStorage> getStorageByCatalogRecordId(
            @PathVariable int id, @AuthenticationPrincipal CatalogUser user) {

        CatalogWithStorageRecord withStorageRecord = withStorageRecordRepo
                .findByCatalogRecordId(id);
        if (withStorageRecord == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CatalogUser storage = userRepo
                .findById(withStorageRecord.getCatalogUser().getId())
                .orElse(null);
        if (storage == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                new CatalogStorage(storage.getId(), storage.getAbout(),
                        storage.getIpAddress(), storage.getPort()),
                HttpStatus.OK);
    }
}
