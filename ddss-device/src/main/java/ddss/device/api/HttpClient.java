package ddss.device.api;

import org.springframework.boot.test.web.client.TestRestTemplate;

public class HttpClient {

    public static final TestRestTemplate httpClient;

    static {
        httpClient = new TestRestTemplate();
    }
}
