package ddss.storage;

import com.google.protobuf.InvalidProtocolBufferException;
import ddss.device.proto.Person;
import ddss.device.proto.SensorsData;
import ddss.storage.domain.Data;
import ddss.storage.domain.Feedback;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageIntegrationTests extends IntegrationTests {

    private static final SensorsData SENSORS_DATA_PROTO = SensorsData.newBuilder()
            .setDegreesCelsius(20)
            .setPascals(100)
            .setMetersPerSecond(4).build();
    private static final byte[] SENSORS_DATA_BYTES = SENSORS_DATA_PROTO.toByteArray();
    private static final Data SENSORS_DATA_TO_SEND = new Data(SENSORS_DATA_BYTES);
    private static final Person PERSON_PROTO = Person.newBuilder()
            .setId(1)
            .setName("Andrey")
            .setSurname("Kuznetsov")
            .setEmail("example@example").build();
    private static final byte[] PERSON_BYTES = PERSON_PROTO.toByteArray();
    private static final Data PERSON_TO_SEND = new Data(PERSON_BYTES);
    @Autowired
    private DdssStorageTestProps tprops;

    @Test
    @FlywayTest
    public void post_upload_with_status_created() throws InvalidProtocolBufferException {
        // arrange
        HttpEntity<Data> request = new HttpEntity<>(SENSORS_DATA_TO_SEND);

        // act
        ResponseEntity<Feedback> response = restTemplate
                .withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlUpload() + "/" + tprops.getCatRecIdUploadSensorsData(),
                        HttpMethod.POST, request, Feedback.class);
        String result = Objects.requireNonNull(
                response.getBody()).getBytes();
        SensorsData parsedSensorsData = SensorsData.parseFrom(result.getBytes());

        // assert
        assertEquals(parsedSensorsData.getDegreesCelsius(), SENSORS_DATA_PROTO.getDegreesCelsius());
        assertEquals(parsedSensorsData.getPascals(), SENSORS_DATA_PROTO.getPascals());
        assertEquals(parsedSensorsData.getMetersPerSecond(), SENSORS_DATA_PROTO.getMetersPerSecond());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void get_download_with_status_ok() throws InvalidProtocolBufferException {
        // arrange
        HttpEntity<Data> request = new HttpEntity<>(SENSORS_DATA_TO_SEND);
        restTemplate
                .withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlUpload() + "/" + tprops.getCatRecIdUploadPerson(),
                        HttpMethod.POST, request, Feedback.class);

        // act
        ResponseEntity<Data> response = restTemplate
                .withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlDownload() + "/" + tprops.getCatRecIdUploadPerson(), HttpMethod.GET, request, Data.class);
        String result = Objects.requireNonNull(
                response.getBody()).getBytes();
        SensorsData parsedSensorsData = SensorsData.parseFrom(result.getBytes());

        // assert
        assertEquals(parsedSensorsData.getDegreesCelsius(), SENSORS_DATA_PROTO.getDegreesCelsius());
        assertEquals(parsedSensorsData.getPascals(), SENSORS_DATA_PROTO.getPascals());
        assertEquals(parsedSensorsData.getMetersPerSecond(), SENSORS_DATA_PROTO.getMetersPerSecond());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void get_download_with_status_not_found() {
        // arrange
        HttpEntity<Data> request = new HttpEntity<>(SENSORS_DATA_TO_SEND);

        // act
        ResponseEntity<Data> response = restTemplate
                .withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlDownload() + "/" + tprops.getCatRecIdDownloadOk(),
                        HttpMethod.GET, request, Data.class);

        // assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void upload_two_diff_classes_objs_and_download_them() throws InvalidProtocolBufferException {
        // arrange
        HttpEntity<Data> requestSensorsData = new HttpEntity<>(SENSORS_DATA_TO_SEND);
        HttpEntity<Data> requestPerson = new HttpEntity<>(PERSON_TO_SEND);

        // act
        restTemplate.withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlUpload() + "/" + tprops.getCatRecIdUploadSensorsData(),
                        HttpMethod.POST, requestSensorsData, Feedback.class);

        restTemplate.withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlUpload() + "/" + tprops.getCatRecIdDownloadNotFound(),
                        HttpMethod.POST, requestPerson, Feedback.class);

        ResponseEntity<Data> responseSensorData = restTemplate
                .withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlDownload() + "/" + tprops.getCatRecIdUploadSensorsData(),
                        HttpMethod.GET, requestSensorsData, Data.class);
        String resultSensorData = Objects.requireNonNull(
                responseSensorData.getBody()).getBytes();
        SensorsData parsedSensorsData = SensorsData.parseFrom(resultSensorData.getBytes());

        ResponseEntity<Data> responsePerson = restTemplate
                .withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlDownload() + "/" + tprops.getCatRecIdDownloadNotFound(),
                        HttpMethod.GET, requestPerson, Data.class);
        String resultPerson = Objects.requireNonNull(
                responsePerson.getBody()).getBytes();
        Person parsedPerson = Person.parseFrom(resultPerson.getBytes());

        // assert
        assertEquals(parsedSensorsData.getDegreesCelsius(), SENSORS_DATA_PROTO.getDegreesCelsius());
        assertEquals(parsedSensorsData.getPascals(), SENSORS_DATA_PROTO.getPascals());
        assertEquals(parsedSensorsData.getMetersPerSecond(), SENSORS_DATA_PROTO.getMetersPerSecond());
        assertEquals(HttpStatus.OK, responseSensorData.getStatusCode());

        assertEquals(parsedPerson.getId(), PERSON_PROTO.getId());
        assertEquals(parsedPerson.getName(), PERSON_PROTO.getName());
        assertEquals(parsedPerson.getSurname(), PERSON_PROTO.getSurname());
        assertEquals(parsedPerson.getEmail(), PERSON_PROTO.getEmail());
        assertEquals(HttpStatus.OK, responsePerson.getStatusCode());
    }
}
