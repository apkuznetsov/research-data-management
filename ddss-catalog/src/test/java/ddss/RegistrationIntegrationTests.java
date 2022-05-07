package ddss;

import ddss.security.RegistrationForm;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationIntegrationTests extends IntegrationTests {

    private static final String CAT_REGISTER = "/cat/register";

    private static final RegistrationForm registrationForm =
            new RegistrationForm("kuznetsov2", "qwerty2", "test device 2");

    @Test
    @FlywayTest
    public void post_register_with_status_created() throws IOException {
        // arrange
        HttpEntity<RegistrationForm> request = new HttpEntity<>(registrationForm);

        // act
        ResponseEntity<RegistrationForm> response = restTemplate.exchange(
                CAT_REGISTER, HttpMethod.POST, request, RegistrationForm.class);
        RegistrationForm result = response.getBody();

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
