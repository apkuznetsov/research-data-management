package ddss.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "deviceUserId", nullable = false)
    private Integer deviceUserId;

    public CatalogRecord() {
    }
}
