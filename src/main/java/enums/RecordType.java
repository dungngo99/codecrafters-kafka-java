package enums;

public enum RecordType {
    TOPIC((byte) 2),
    PARTITION((byte) 3),
    FEATURE_LEVEL((byte) 12);

    private final byte type;

    public static RecordType ofType(byte b) {
        for (RecordType v : values()) {
            if (b == v.type) {
                return v;
            }
        }
        throw new RuntimeException("invalid record type");
    }

    RecordType(byte type) {
        this.type = type;
    }

    public byte getType() {
        return type;
    }
}
