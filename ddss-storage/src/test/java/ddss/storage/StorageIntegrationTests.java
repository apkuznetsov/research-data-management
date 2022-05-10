package ddss.storage;

import com.google.protobuf.InvalidProtocolBufferException;
import ddss.device.proto.SensorsData;
import ddss.storage.domain.Data;
import ddss.storage.domain.Feedback;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageIntegrationTests extends IntegrationTests {

    private static final String UPLOAD_ADDRESS = "/storage/upload";

    private static final SensorsData SENSORS_DATA_PROTO = SensorsData.newBuilder()
            .setDegreesCelsius(20)
            .setPascals(100)
            .setMetersPerSecond(4).build();
    private static final byte[] SENSORS_DATA_BYTES = SENSORS_DATA_PROTO.toByteArray();
    private static final Data SENSORS_DATA_TO_SEND = new Data(SENSORS_DATA_BYTES);

    @Test
    @FlywayTest
    public void post_upload_with_status_created() throws InvalidProtocolBufferException {
        // arrange
        HttpEntity<Data> request = new HttpEntity<>(SENSORS_DATA_TO_SEND);

        // act
        ResponseEntity<Feedback> response = restTemplate.exchange(
                UPLOAD_ADDRESS, HttpMethod.POST, request, Feedback.class);
        String result = Objects.requireNonNull(
                response.getBody()).getBytes();
        SensorsData parsedSensorsData = SensorsData.parseFrom(result.getBytes());

        // assert
        assertEquals(parsedSensorsData.getDegreesCelsius(), SENSORS_DATA_PROTO.getDegreesCelsius());
        assertEquals(parsedSensorsData.getPascals(), SENSORS_DATA_PROTO.getPascals());
        assertEquals(parsedSensorsData.getMetersPerSecond(), SENSORS_DATA_PROTO.getMetersPerSecond());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
