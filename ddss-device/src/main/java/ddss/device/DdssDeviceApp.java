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
        String uploadAddress = "http://localhost:8081/storage/upload";
        String downloadAddress = "http://localhost:8081/storage/download";

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

        ResponseEntity<Feedback> response1 = restTemplate
                .exchange(uploadAddress, HttpMethod.POST, request, Feedback.class);

        String result1 = Objects.requireNonNull(response1.getBody())
                .getBytes();
        System.out.println(result1);
        System.out.println("bytes " + dataToSend.getBytes() + " with " + dataToSend.getBytes().length() + " length uploaded");
        System.out.println();

        SensorsData parsedSensorsData1 = SensorsData.parseFrom(result1.getBytes());
        System.out.println("degrees ............. " + parsedSensorsData1.getDegreesCelsius());
        System.out.println("pascals ............. " + parsedSensorsData1.getPascals());
        System.out.println("meters per second ... " + parsedSensorsData1.getMetersPerSecond());
        System.out.println();

        ResponseEntity<Data> response2 = restTemplate
                .exchange(downloadAddress, HttpMethod.GET, request, Data.class);

        Data result2 = response2.getBody();
        SensorsData parsedSensorsData2 = SensorsData.parseFrom(result2.getBytes().getBytes());
        System.out.println("degrees ............. " + parsedSensorsData2.getDegreesCelsius());
        System.out.println("pascals ............. " + parsedSensorsData2.getPascals());
        System.out.println("meters per second ... " + parsedSensorsData2.getMetersPerSecond());
    }
}
