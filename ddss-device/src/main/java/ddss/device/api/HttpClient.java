package ddss.device.api;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HttpClient {

    public static final RestTemplate httpClient;

    static {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(
                Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);

        httpClient = new RestTemplate();
        httpClient.setMessageConverters(messageConverters);
    }
}
