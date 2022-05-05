package ddss.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "storage_user")
@Getter
@Setter
public class StorageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "about")
    private String about;
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;
    @Column(name = "port", nullable = false)
    private int port;
    @Column(name = "available_megabytes_number", nullable = false)
    private long availableMegabytesNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storageUser")
    @ToString.Exclude
    @JsonIgnore
    private List<CatalogWithStorageRecord> catalogWithStorageRecords;

    public StorageUser() {
    }
}
