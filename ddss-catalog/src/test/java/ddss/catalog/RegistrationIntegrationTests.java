package ddss.catalog;

import ddss.catalog.security.RegistrationForm;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationIntegrationTests extends IntegrationTests {

    private static final String CAT_REGISTER = "/cat/register";

    private static final RegistrationForm REG_FORM_ALREADY_CREATED_USER =
            new RegistrationForm("kuznetsov", "qwerty", "test device 1");
    private static final RegistrationForm REG_FORM_NEW_USER =
            new RegistrationForm("kuznetsov2", "qwerty2", "test device 2");

    @Test
    @FlywayTest
    public void post_register_with_status_created() {
        // arrange
        HttpEntity<RegistrationForm> request = new HttpEntity<>(REG_FORM_NEW_USER);

        // act
        ResponseEntity<RegistrationForm> response = restTemplate.exchange(
                CAT_REGISTER, HttpMethod.POST, request, RegistrationForm.class);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void post_register_with_status_bad_request() {
        // arrange
        HttpEntity<RegistrationForm> request = new HttpEntity<>(REG_FORM_ALREADY_CREATED_USER);

        // act
        ResponseEntity<RegistrationForm> response = restTemplate.exchange(
                CAT_REGISTER, HttpMethod.POST, request, RegistrationForm.class);

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
