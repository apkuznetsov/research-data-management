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

    @Value("${user2.username}")
    private String username2;
    @Value("${user2.password}")
    private String password2;
    @Value("${user2.about}")
    private String about2;

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
