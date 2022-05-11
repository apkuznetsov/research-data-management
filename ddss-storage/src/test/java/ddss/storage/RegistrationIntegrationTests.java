package ddss.storage;

import ddss.storage.security.RegistrationForm;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationIntegrationTests extends IntegrationTests {

    @Autowired
    private DdssStorageTestProps tprops;
    
    private RegistrationForm regFormAlreadyCreatedUser;
    private RegistrationForm regFormNewUser;

    @BeforeEach
    public void init() {
        regFormAlreadyCreatedUser =
                new RegistrationForm(tprops.getUsername(), tprops.getPassword(), tprops.getAbout());
        regFormNewUser =
                new RegistrationForm(tprops.getUsername2(), tprops.getUsername2(), tprops.getAbout2());
    }

    @Test
    @FlywayTest
    public void post_register_with_status_created() {
        // arrange
        HttpEntity<RegistrationForm> request = new HttpEntity<>(regFormNewUser);

        // act
        ResponseEntity<RegistrationForm> response = restTemplate.exchange(
                tprops.getUrlRegister(), HttpMethod.POST, request, RegistrationForm.class);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void post_register_with_status_bad_request() {
        // arrange
        HttpEntity<RegistrationForm> request = new HttpEntity<>(regFormAlreadyCreatedUser);

        // act
        ResponseEntity<RegistrationForm> response = restTemplate.exchange(
                tprops.getUrlRegister(), HttpMethod.POST, request, RegistrationForm.class);

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
