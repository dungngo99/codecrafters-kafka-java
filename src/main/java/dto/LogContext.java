package dto;

import enums.ValueType;

import java.io.FileInputStream;

public class LogContext {
    private String filePath;
    private FileInputStream is;
    private ValueType valueType;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileInputStream getIs() {
        return is;
    }

    public void setIs(FileInputStream is) {
        this.is = is;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }
}
