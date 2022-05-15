package ddss.catalog.api;

import ddss.catalog.DdssCatalogProps;
import ddss.catalog.data.CatalogUserRepository;
import ddss.catalog.domain.AvailableMegabytesNumber;
import ddss.catalog.domain.CatalogStorage;
import ddss.catalog.domain.CatalogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static ddss.catalog.api.HttpClient.client;
import static ddss.catalog.api.HttpClient.createHeaders;

@Controller
@RequestMapping("/cat/storage")
public class StorageInteractionController {

    private final DdssCatalogProps props;
    private final CatalogUserRepository userRepo;

    private final HttpEntity<AvailableMegabytesNumber> request;

    @Autowired
    public StorageInteractionController(DdssCatalogProps props, CatalogUserRepository userRepo) {
        this.props = props;
        this.userRepo = userRepo;

        HttpHeaders headers = createHeaders(props.getAdminUsername(), props.getAdminPassword());
        request = new HttpEntity<>(new AvailableMegabytesNumber(), headers);
    }

    @GetMapping(path = "/available")
    public ResponseEntity<CatalogStorage> getAvailableStorage(@AuthenticationPrincipal CatalogUser user) {

        List<CatalogUser> storageUsers = userRepo.findAllByIsStorage(true);
        return findAvailableStorage(storageUsers);
    }

    private ResponseEntity<CatalogStorage> findAvailableStorage(List<CatalogUser> storageUsers) {
        AvailableMegabytesNumber result;
        for (CatalogUser storageUser : storageUsers) {

            ResponseEntity<AvailableMegabytesNumber> response = client
                    .exchange(storageUser + props.getAvailableMegabytesUrl(),
                            HttpMethod.GET, request, AvailableMegabytesNumber.class);
            result = response.getBody();
            if (result == null) {
                continue;
            }

            if (result.getValue() >= props.getMinAvailableMegabytes()) {
                return new ResponseEntity<>(
                        new CatalogStorage(storageUser.getId(), storageUser.getAbout(),
                                storageUser.getIpAddress(), storageUser.getPort()),
                        HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
