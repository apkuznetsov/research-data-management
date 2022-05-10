package ddss.storage;

import com.google.protobuf.InvalidProtocolBufferException;
import ddss.storage.domain.AvailableMegabytesNumber;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = DdssStorageApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public class CatalogInteractionIntegrationTests {

    private static final String STORAGE_AVAILABLE_MEGABYTES = "/storage/admin/available-megabytes";

    @Autowired
    public TestRestTemplate restTemplate;

    @Test
    public void get_available_megabytes_with_status_ok() {
        // arrange
        HttpEntity<AvailableMegabytesNumber> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        ResponseEntity<AvailableMegabytesNumber> response = restTemplate
                .exchange(STORAGE_AVAILABLE_MEGABYTES, HttpMethod.GET, request, AvailableMegabytesNumber.class);
        long result = Objects.requireNonNull(
                response.getBody()).getValue();

        // assert
        assertTrue(result >= 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
