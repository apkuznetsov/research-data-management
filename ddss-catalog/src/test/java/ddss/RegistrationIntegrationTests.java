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

    @Test
    @FlywayTest
    public void post_register_with_status_created() throws IOException {
        // arrange
        RegistrationForm entity = new RegistrationForm("kuznetsov", "qwerty", "test device 1");
        HttpEntity<RegistrationForm> request = new HttpEntity<>(entity);

        // act
        ResponseEntity<RegistrationForm> response = restTemplate.exchange(
                CAT_REGISTER, HttpMethod.POST, request, RegistrationForm.class);
        RegistrationForm result = response.getBody();

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
