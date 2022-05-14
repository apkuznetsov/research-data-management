package ddss.catalog.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpClient {

    public static RestTemplate client = new RestTemplate();

    public static HttpHeaders createHeaders(String username, String password) {
        String notEncoded = username + ":" + password;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", encodedAuth);
        return headers;
    }
}
