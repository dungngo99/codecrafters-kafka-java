package dto;

import enums.FieldType;

public class Field<T extends Number> {
    private T data;
    private FieldType fieldType;

    public Field(T data, FieldType fieldType) {
        this.data = data;
        this.fieldType = fieldType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
}
