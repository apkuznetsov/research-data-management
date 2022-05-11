package ddss.catalog;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@Configuration
@PropertySource("classpath:test.properties")
public class DdssCatalogTestProps {

    @Value("${user.username}")
    private String username;
    @Value("${user.password}")
    private String password;
    @Value("${user.about}")
    private String about;
    @Value("${user.ip_address}")
    private String ipAddress;
    @Value("${user.port}")
    private short port;
    @Value("${user.is_storage}")
    private boolean isStorage;

    @Value("${user2.username}")
    private String username2;
    @Value("${user2.password}")
    private String password2;
    @Value("${user2.about}")
    private String about2;
    @Value("${user2.ip_address}")
    private String ipAddress2;
    @Value("${user2.port}")
    private short port2;
    @Value("${user2.is_storage}")
    private boolean isStorage2;

    @Value("${url.catalog.register}")
    private String urlRegister;
    @Value("${url.catalog.record}")
    private String urlRecord;
    @Value("${url.catalog.create}")
    private String urlCreate;

    @Value("${url.storage.available_megabytes}")
    private String urlAvailableMegabytes;
    @Value("${url.storage.upload}")
    private String urlUpload;
    @Value("${url.storage.download}")
    private String urlDownload;

    @Value("${record.id}")
    private int recId;
    @Value("${record.about}")
    private String recAbout;
    @Value("${record.proto_scheme}")
    private String recProtoScheme;

    @Value("${record.id.for_create}")
    private int recIdForCreate;
    @Value("${record.about.for_create}")
    private String recAboutForCreate;
    @Value("${record.proto_scheme.for_create}")
    private String recProtoSchemeForCreate;

    @Value("${record.id.for_forbidden}")
    private int recIdForForbidden;
    @Value("${record.id.for_not_found}")
    private int recIdForNotFound;

    @Value("${cat_rec_id.upload_sensors_data}")
    private int catRecIdUploadSensorsData;
    @Value("${cat_rec_id.upload_person}")
    private int catRecIdUploadPerson;
    @Value("${cat_rec_id.download_ok}")
    private int catRecIdDownloadOk;
    @Value("${cat_rec_id.download_not_found}")
    private int catRecIdDownloadNotFound;

    @Value("${sensors_data.degrees_celsius}")
    private int sensDataDegrees;
    @Value("${sensors_data.pascals}")
    private int sensDataPascals;
    @Value("${sensors_data.meters_per_second}")
    private int sensDataMetersPerSecond;

    @Value("${person.id}")
    private int personId;
    @Value("${person.name}")
    private String personName;
    @Value("${person.surname}")
    private String personSurname;
    @Value("${person.email}")
    private String personEmail;
}
