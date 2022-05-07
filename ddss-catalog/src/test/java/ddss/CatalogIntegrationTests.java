package ddss;

import ddss.domain.CatalogRecord;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogIntegrationTests extends IntegrationTests {

    private static final String CAT_RECORD = "/cat/record";

    @Test
    @FlywayTest
    public void get_record_by_id() throws IOException {
        // arrange
        String testUrl = CAT_RECORD + "/" + 11;

        HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        ResponseEntity<CatalogRecord> response = restTemplate.withBasicAuth("kuznetsov", "qwerty")
                .exchange(testUrl, HttpMethod.GET, request, CatalogRecord.class);
        CatalogRecord result = response.getBody();

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
