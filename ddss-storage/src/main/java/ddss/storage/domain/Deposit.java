package ddss.storage.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deposit")
@Getter
@Setter
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "catalog_record_id", nullable = false)
    private int catalogRecordId;
    @Column(name = "saved_at")
    private LocalDateTime savedAt;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "data", nullable = false)
    private byte[] data;

    public Deposit() {
    }
}
