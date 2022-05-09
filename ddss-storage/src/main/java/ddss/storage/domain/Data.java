package ddss.storage.domain;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class Data {

    String bytes;

    public Data() {
    }

    public Data(byte[] bytes) {
        this.bytes = new String(bytes, StandardCharsets.UTF_8);
    }
}
