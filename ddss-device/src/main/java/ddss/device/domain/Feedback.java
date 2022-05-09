package ddss.device.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feedback {

    private String message;
    private String bytes;

    public Feedback() {
    }

    public Feedback(String message, String bytes) {
        this.message = message;
        this.bytes = bytes;
    }
}