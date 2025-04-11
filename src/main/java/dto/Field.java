package dto;

import enums.FieldType;

import java.util.Arrays;

public class Field {
    private byte[] data;
    private FieldType fieldType;
    private int size;

    public Field(byte[] data, FieldType fieldType, int size) {
        this.data = data;
        this.fieldType = fieldType;
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Field{" +
                "data=" + Arrays.toString(data) +
                ", fieldType=" + fieldType +
                ", size=" + size +
                '}';
    }
}
