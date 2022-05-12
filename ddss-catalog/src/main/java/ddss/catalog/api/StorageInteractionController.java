package ddss.catalog.api;

import ddss.catalog.data.CatalogUserRepository;
import ddss.catalog.domain.CatalogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cat/storage")
public class StorageInteractionController {

    @Autowired
    private CatalogUserRepository userRepo;

    @GetMapping(path = "/available")
    public ResponseEntity<List<CatalogUser>> getStorages(@AuthenticationPrincipal CatalogUser user) {

        List<CatalogUser> storages = userRepo.findAllByIsStorage(true);
        return (storages == null || storages.isEmpty())
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(storages, HttpStatus.OK);
    }
}
