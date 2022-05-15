package ddss.device.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogStorage {

    private int id;
    private String about;
    private String ipAddress;
    private short port;

    public CatalogStorage() {
    }

    public CatalogStorage(int id, String about, String ipAddress, short port) {
        this.id = id;
        this.about = about;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String toString() {
        return ipAddress + ":" + port;
    }
}
