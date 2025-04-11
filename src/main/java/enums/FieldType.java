package enums;

public enum FieldType {
    INTEGER(4),
    SHORT(2),
    BYTE(1),
    STRING(-1);

    private final Integer byteSize;

    FieldType(Integer byteSize) {
        this.byteSize = byteSize;
    }

    public Integer getByteSize() {
        return byteSize;
    }
}
