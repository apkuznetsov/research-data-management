package ddss.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CatalogRecordWithStorage")
@Getter
@Setter
public class CatalogRecordWithStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;
    @Column(name = "storageUserId", nullable = false)
    private int storageUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogRecordId", nullable = false)
    private CatalogRecord catalogRecord;

    public CatalogRecordWithStorage() {
    }
}
