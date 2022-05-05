package ddss;

import com.fasterxml.jackson.databind.ObjectMapper;
import ddss.data.DeviceUserRepository;
import ddss.domain.DeviceUser;
import ddss.security.RegistrationController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
public class RegistrationUnitTests {
    
    @MockBean
    private DeviceUserRepository userRepo;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private MockitoSession session;

    @BeforeEach
    public void beforeMethod() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @AfterEach
    public void afterMethod() {
        session.finishMocking();
    }

    @Test
    public void post_register_with_status_created() throws Exception {
        DeviceUser u = new DeviceUser("kuznetsov", "qwerty", "test device");
        Mockito.when(userRepo.findByUsername(u.getUsername())).thenReturn(null);

        mockMvc.perform(post("/cat/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void post_register_with_status_bad_request() throws Exception {
        DeviceUser u = new DeviceUser("kuznetsov", "qwerty", "test device");
        Mockito.when(userRepo.findByUsername(u.getUsername())).thenReturn(u);

        mockMvc.perform(post("/cat/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
