package ddss.storage.api;

import ddss.storage.domain.AvailableMegabytesNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
@RequestMapping("/storage/admin")
public class CatalogInteractionController {

    private static long calcAvailableMegabytes() {
        long availableBytes = 0;

        File[] roots = File.listRoots();
        for (File root : roots) {
            availableBytes += root.getFreeSpace();
        }

        return (long) ((double) availableBytes / 1024.0);
    }

    @GetMapping(value = "/available-megabytes")
    public ResponseEntity<AvailableMegabytesNumber> getAvailableMegabytes() {
        return new ResponseEntity<>(
                new AvailableMegabytesNumber(calcAvailableMegabytes()),
                HttpStatus.OK);
    }
}
