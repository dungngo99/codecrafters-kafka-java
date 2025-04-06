package dto;

public class ApiRequest {
    private Integer messageSize;
    private Integer requestApiKey;
    private Integer requestApiVersion;
    private Integer correlationId;
    private String clientId;
    private int[] tagBuffer;

    public Integer getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(Integer messageSize) {
        this.messageSize = messageSize;
    }

    public Integer getRequestApiKey() {
        return requestApiKey;
    }

    public void setRequestApiKey(Integer requestApiKey) {
        this.requestApiKey = requestApiKey;
    }

    public Integer getRequestApiVersion() {
        return requestApiVersion;
    }

    public void setRequestApiVersion(Integer requestApiVersion) {
        this.requestApiVersion = requestApiVersion;
    }

    public Integer getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(Integer correlationId) {
        this.correlationId = correlationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int[] getTagBuffer() {
        return tagBuffer;
    }

    public void setTagBuffer(int[] tagBuffer) {
        this.tagBuffer = tagBuffer;
    }
}
