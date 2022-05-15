package ddss.device.api;

import ddss.device.domain.Data;
import ddss.device.domain.Feedback;
import ddss.device.proto.SensorsData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static ddss.device.DdssDeviceProps.STORAGE_UPLOAD_URL;
import static ddss.device.api.HttpClient.createHeaders;
import static ddss.device.api.HttpClient.httpClient;

public class StorageInteractionController {

    private static final SensorsData protoSensData = SensorsData.newBuilder()
            .setDegreesCelsius(44)
            .setPascals(12)
            .setMetersPerSecond(13).build();
    private static final Data dataSensData = new Data(protoSensData.toByteArray());

    public static boolean upload(String ipAddressWithPort, int catalogRecordId, String username, String password) {

        HttpEntity<Data> request = new HttpEntity<>(dataSensData, createHeaders(username, password));

        ResponseEntity<Feedback> response = httpClient
                .exchange("http://" + ipAddressWithPort + STORAGE_UPLOAD_URL + "/" + catalogRecordId,
                        HttpMethod.POST, request, Feedback.class);

        return response.getStatusCode() == HttpStatus.CREATED;
    }
}
