package ddss.storage.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    byte[] bytes;

    public Data() {
    }

    public Data(byte[] bytes) {
        this.bytes = bytes;
    }
}
