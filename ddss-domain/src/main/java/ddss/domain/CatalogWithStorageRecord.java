package ddss.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CatalogWithStorageRecord")
@Getter
@Setter
public class CatalogWithStorageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogRecordId", nullable = false)
    private CatalogRecord catalogRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storageUserId", nullable = false)
    private StorageUser storageUser;

    public CatalogWithStorageRecord() {
    }
}
