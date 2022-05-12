package ddss.device.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CatalogRecord {

    private Integer id;
    private String about;
    private String protoScheme;
    private LocalDateTime createdAt;

    public CatalogRecord() {
    }

    public CatalogRecord(String about, String protoScheme, LocalDateTime createdAt) {
        this.about = about;
        this.protoScheme = protoScheme;
        this.createdAt = createdAt;
    }

    public CatalogRecord(String about, String protoScheme, String createdAt) {
        this.about = about;
        this.protoScheme = protoScheme;
        this.createdAt = LocalDateTime.parse(createdAt);
    }

    public CatalogRecord(int id, String about, String protoScheme, LocalDateTime createdAt) {
        this(about, protoScheme, createdAt);
        this.id = id;
    }

    public CatalogRecord(int id, String about, String protoScheme, String createdAt) {
        this(about, protoScheme, createdAt);
        this.id = id;
    }
}
