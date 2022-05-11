package ddss.catalog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CatalogUser catalogUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogRecord")
    @JsonIgnore
    private List<CatalogWithStorageRecord> catalogWithStorageRecords;

    public CatalogRecord() {
    }

    public CatalogRecord(String about, String protoScheme, String createdAt) {
        this.about = about;
        this.protoScheme = protoScheme;
        this.createdAt = LocalDateTime.parse(createdAt);
    }

    public CatalogRecord(int id, String about, String protoScheme, String createdAt) {
        this(about, protoScheme, createdAt);
        this.id = id;
    }

    public CatalogRecord(String about, String protoScheme, LocalDateTime createdAt) {
        this.about = about;
        this.protoScheme = protoScheme;
        this.createdAt = createdAt;
    }

    public CatalogRecord(int id, String about, String protoScheme, LocalDateTime createdAt) {
        this(about, protoScheme, createdAt);
        this.id = id;
    }

    public void setUser(CatalogUser catalogUser) {
        this.catalogUser = catalogUser;
    }
}
