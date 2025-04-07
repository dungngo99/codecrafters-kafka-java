package dto;

import enums.FieldType;

public class Field<T extends Number> {
    private T data;
    private Integer byteSize;
    private FieldType fieldType;

    public Field(T data, Integer byteSize, FieldType fieldType) {
        this.data = data;
        this.byteSize = byteSize;
        this.fieldType = fieldType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getByteSize() {
        return byteSize;
    }

    public void setByteSize(Integer byteSize) {
        this.byteSize = byteSize;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
}
