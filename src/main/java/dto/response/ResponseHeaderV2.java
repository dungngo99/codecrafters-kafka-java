package dto.response;

import dto.Field;

public class ResponseHeaderV2 {
    private Field correlationId;

    public Field getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(Field correlationId) {
        this.correlationId = correlationId;
    }
}
