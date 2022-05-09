package ddss.device.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    String bytes;

    public Data() {
    }

    public Data(byte[] bytes) {
        this.bytes = new String(bytes);
    }
}
