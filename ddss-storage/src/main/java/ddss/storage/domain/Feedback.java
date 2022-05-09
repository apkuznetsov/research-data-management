package ddss.storage.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feedback {

    private String message;

    public Feedback() {
    }

    public Feedback(String message) {
        this.message = message;
    }
}