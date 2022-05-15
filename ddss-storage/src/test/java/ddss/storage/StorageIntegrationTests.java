package ddss.storage;

import com.google.protobuf.InvalidProtocolBufferException;
import ddss.device.proto.Person;
import ddss.device.proto.SensorsData;
import ddss.storage.domain.Data;
import ddss.storage.domain.Feedback;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageIntegrationTests extends IntegrationTests {

    @Autowired
    private DdssStorageTestProps tprops;

    private SensorsData protoSensData;
    private Data dataSensData;
    private Person protoPerson;
    private Data dataPerson;

    @BeforeEach
    public void init() {
        protoSensData = SensorsData.newBuilder()
                .setDegreesCelsius(tprops.getSensDataDegrees())
                .setPascals(tprops.getSensDataPascals())
                .setMetersPerSecond(tprops.getSensDataMetersPerSecond()).build();
        protoPerson = Person.newBuilder()
                .setId(tprops.getPersonId())
                .setName(tprops.getPersonName())
                .setSurname(tprops.getPersonSurname())
                .setEmail(tprops.getPersonEmail()).build();
        dataSensData = new Data(
                protoSensData.toByteArray());
        dataPerson = new Data(
                protoPerson.toByteArray());
    }

    @Test
    @FlywayTest
    public void post_upload_with_status_created() throws InvalidProtocolBufferException {
        // arrange
        HttpEntity<Data> request = new HttpEntity<>(dataSensData);

        // act
        ResponseEntity<Feedback> response = restTemplate
                .withBasicAuth(tprops.getUsername(), tprops.getPassword())
                .exchange(tprops.getUrlUpload() + "/" + tprops.getCatRecIdUploadSensorsData(),
                        HttpMethod.POST, request, Feedback.class);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void get_download_with_status_ok() throws InvalidProtocolBufferException {
        // arrange
        HttpEntity<Data> request = new HttpEntity<>(dataSensData);
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
        assertEquals(parsedSensorsData.getDegreesCelsius(), protoSensData.getDegreesCelsius());
        assertEquals(parsedSensorsData.getPascals(), protoSensData.getPascals());
        assertEquals(parsedSensorsData.getMetersPerSecond(), protoSensData.getMetersPerSecond());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @FlywayTest
    public void get_download_with_status_not_found() {
        // arrange
        HttpEntity<Data> request = new HttpEntity<>(dataSensData);

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
        HttpEntity<Data> requestSensorsData = new HttpEntity<>(dataSensData);
        HttpEntity<Data> requestPerson = new HttpEntity<>(dataPerson);

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
        assertEquals(parsedSensorsData.getDegreesCelsius(), protoSensData.getDegreesCelsius());
        assertEquals(parsedSensorsData.getPascals(), protoSensData.getPascals());
        assertEquals(parsedSensorsData.getMetersPerSecond(), protoSensData.getMetersPerSecond());
        assertEquals(HttpStatus.OK, responseSensorData.getStatusCode());

        assertEquals(parsedPerson.getId(), protoPerson.getId());
        assertEquals(parsedPerson.getName(), protoPerson.getName());
        assertEquals(parsedPerson.getSurname(), protoPerson.getSurname());
        assertEquals(parsedPerson.getEmail(), protoPerson.getEmail());
        assertEquals(HttpStatus.OK, responsePerson.getStatusCode());
    }
}
