package ddss.device;

import com.google.protobuf.InvalidProtocolBufferException;
import ddss.device.domain.Data;
import ddss.device.domain.Feedback;
import ddss.device.proto.SensorsData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DdssDeviceApp {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        String address = "http://localhost:8081/storage/upload";

        SensorsData sensorsData = SensorsData.newBuilder()
                .setDegreesCelsius(20)
                .setPascals(100)
                .setMetersPerSecond(4).build();
        byte[] sensorsDataBytes = sensorsData.toByteArray();
        Data dataToSend = new Data(sensorsDataBytes);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(
                Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        HttpEntity<Data> request = new HttpEntity<>(dataToSend);

        ResponseEntity<Feedback> response = restTemplate
                .exchange(address, HttpMethod.POST, request, Feedback.class);

        String result = Objects.requireNonNull(response.getBody())
                .getBytes();
        System.out.println(result);
        System.out.println("bytes " + dataToSend.getBytes() + " with " + dataToSend.getBytes().length() + " length uploaded");
        System.out.println();

        SensorsData parsedSensorsData = SensorsData.parseFrom(result.getBytes());
        System.out.println("degrees ............. " + parsedSensorsData.getDegreesCelsius());
        System.out.println("pascals ............. " + parsedSensorsData.getPascals());
        System.out.println("meters per second ... " + parsedSensorsData.getMetersPerSecond());
    }
}
