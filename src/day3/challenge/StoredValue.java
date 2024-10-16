package day3.challenge;

import java.io.Serializable;

public class StoredValue implements Serializable {
    private final String value;
    private final long timestamp;

    public String getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public StoredValue(String value, long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
}
