package ddss.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "catalog_with_storage_record")
@Getter
@Setter
public class CatalogWithStorageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_record_id", nullable = false)
    private CatalogRecord catalogRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_user_id", nullable = false)
    private StorageUser storageUser;

    public CatalogWithStorageRecord() {
    }
}
