package ddss.device.api;

import ddss.device.domain.CatalogRecord;
import ddss.device.security.RegistrationForm;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static ddss.device.DdssDeviceProps.*;
import static ddss.device.api.HttpClient.createHeaders;
import static ddss.device.api.HttpClient.httpClient;

public class CatalogInteractionController {

    public static boolean register(String username, String password, String about,
                                   String ipAddress, short port, boolean isStorage) {

        RegistrationForm regForm = new RegistrationForm(
                username, password, about, ipAddress, port, isStorage);
        HttpEntity<RegistrationForm> request = new HttpEntity<>(regForm);
        ResponseEntity<RegistrationForm> response = httpClient.exchange(
                CAT_REG_URL, HttpMethod.POST, request, RegistrationForm.class);

        return response.getStatusCode() == HttpStatus.CREATED;
    }

    public static int createRecord(String about, String protoScheme,
                                   String username, String password) {

        CatalogRecord catRec = new CatalogRecord(about, protoScheme);
        HttpEntity<CatalogRecord> request = new HttpEntity<>(catRec, createHeaders(username, password));
        ResponseEntity<CatalogRecord> response = httpClient
                .exchange(CAT_CREATE_URL, HttpMethod.POST, request, CatalogRecord.class);

        return Objects.requireNonNull(response.getBody()).getId();
    }

    public static CatalogRecord getRecordById(int id,
                                              String username, String password) {

        HttpEntity<CatalogRecord> request = new HttpEntity<>(new CatalogRecord(), createHeaders(username, password));
        ResponseEntity<CatalogRecord> response = httpClient
                .exchange(CAT_RECORD_URL + "/" + id, HttpMethod.GET, request, CatalogRecord.class);

        return response.getBody();
    }
}
