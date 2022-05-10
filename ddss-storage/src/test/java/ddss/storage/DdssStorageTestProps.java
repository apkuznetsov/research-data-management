package ddss.storage;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@Configuration
@PropertySource("classpath:test.properties")
public class DdssStorageTestProps {

    @Value("${user.username}")
    private String username;
    @Value("${user.password}")
    private String password;
    @Value("${user.about}")
    private String about;

    @Value("${user2.username}")
    private String username2;
    @Value("${user2.password}")
    private String password2;
    @Value("${user2.about}")
    private String about2;

    @Value("${url.storage.register}")
    private String urlRegister;
    @Value("${url.storage.available-megabytes}")
    private String urlAvailableMegabytes;
    @Value("${url.storage.upload}")
    private String urlUpload;
    @Value("${url.storage.download}")
    private String urlDownload;
}
