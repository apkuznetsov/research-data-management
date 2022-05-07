package ddss;

import ddss.domain.CatalogRecord;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogIntegrationTests extends IntegrationTests {

    private static final String CAT_RECORD = "/cat/record";
    private static final String TEST_USERNAME = "kuznetsov";
    private static final String TEST_PASSWORD = "qwerty";
    private static final int TEST_RECORD_ID = 11;
    private static final int TEST_RECORD_ID_FOR_FORBIDDEN = 12;
    private static final int TEST_RECORD_ID_FOR_NOT_FOUND = 13;

    @Test
    @FlywayTest
    public void get_record_by_id() {
        // arrange
        String testUrl = CAT_RECORD + "/" + TEST_RECORD_ID;
        HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        ResponseEntity<CatalogRecord> response = restTemplate.withBasicAuth(TEST_USERNAME, TEST_PASSWORD)
                .exchange(testUrl, HttpMethod.GET, request, CatalogRecord.class);
        CatalogRecord result = response.getBody();

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void delete_record_with_status_no_content() {
        // arrange
        String testUrl = CAT_RECORD + "/" + TEST_RECORD_ID;
        HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        ResponseEntity<CatalogRecord> response = restTemplate.withBasicAuth(TEST_USERNAME, TEST_PASSWORD)
                .exchange(testUrl, HttpMethod.DELETE, request, CatalogRecord.class);
        CatalogRecord result = response.getBody();

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void delete_record_with_status_forbidden() {
        // arrange
        String testUrl = CAT_RECORD + "/" + TEST_RECORD_ID_FOR_FORBIDDEN;
        HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        ResponseEntity<CatalogRecord> response = restTemplate.withBasicAuth(TEST_USERNAME, TEST_PASSWORD)
                .exchange(testUrl, HttpMethod.DELETE, request, CatalogRecord.class);
        CatalogRecord result = response.getBody();

        // assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void delete_record_with_status_not_found() {
        // arrange
        String testUrl = CAT_RECORD + "/" + TEST_RECORD_ID_FOR_NOT_FOUND;
        HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        ResponseEntity<CatalogRecord> response = restTemplate.withBasicAuth(TEST_USERNAME, TEST_PASSWORD)
                .exchange(testUrl, HttpMethod.DELETE, request, CatalogRecord.class);
        CatalogRecord result = response.getBody();

        // assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
