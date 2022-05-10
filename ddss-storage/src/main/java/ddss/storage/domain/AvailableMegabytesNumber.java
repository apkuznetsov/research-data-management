package ddss.storage.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableMegabytesNumber {

    private long value;

    public AvailableMegabytesNumber() {
    }

    public AvailableMegabytesNumber(long value) {
        this.value = value;
    }
}
