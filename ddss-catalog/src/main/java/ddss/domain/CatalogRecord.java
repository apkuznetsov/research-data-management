package ddss.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "catalog_record")
@Getter
@Setter
public class CatalogRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "about")
    private String about;
    @Column(name = "proto_scheme", nullable = false)
    private String protoScheme;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_user_id", nullable = false)
    private DeviceUser deviceUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogRecord")
    @JsonIgnore
    private List<CatalogWithStorageRecord> catalogWithStorageRecords;

    public CatalogRecord() {
    }
}
