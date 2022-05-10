package ddss.storage;

import ddss.storage.domain.AvailableMegabytesNumber;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogInteractionIntegrationTests extends IntegrationTests {

    @Autowired
    private DdssStorageProps props;
    @Autowired
    private DdssStorageTestProps tprops;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @FlywayTest
    public void get_available_megabytes_with_status_ok() {
        // arrange
        HttpEntity<AvailableMegabytesNumber> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        ResponseEntity<AvailableMegabytesNumber> response = restTemplate
                .withBasicAuth(props.getAdminUsername(), props.getAdminPassword())
                .exchange(tprops.getUrlAvailableMegabytes(), HttpMethod.GET, request, AvailableMegabytesNumber.class);
        long result = Objects.requireNonNull(
                response.getBody()).getValue();

        // assert
        assertTrue(result >= 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void get_available_megabytes_with_status_unauthorized() {
        // arrange
        HttpEntity<AvailableMegabytesNumber> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        ResponseEntity<AvailableMegabytesNumber> response = restTemplate
                .withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlAvailableMegabytes(), HttpMethod.GET, request, AvailableMegabytesNumber.class);

        // assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
