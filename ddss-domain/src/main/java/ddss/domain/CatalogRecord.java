package ddss.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CatalogRecord")
@Getter
@Setter
public class CatalogRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "about")
    private String about;
    @Column(name = "protoScheme", nullable = false)
    private String protoScheme;
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deviceUserId", nullable = false)
    private DeviceUser deviceUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogRecord")
    private List<CatalogRecordWithStorage> catalogWithStorageRecords;

    public CatalogRecord() {
    }
}
