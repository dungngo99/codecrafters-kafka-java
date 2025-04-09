package dto;

public class ApiResponseBody {
    private Short apiKey;
    private Short apiVersionKey;
    private short describeTopicPartitionKey;
    private Short errorCode;
    private Byte apiKeyCounts;
    private Short apiMinVersion;
    private Short apiMaxVersion;
    private Integer throttleTimeMS;
    private Byte taggedFieldSize;

    public Short getApiKey() {
        return apiKey;
    }

    public void setApiKey(Short apiKey) {
        this.apiKey = apiKey;
    }

    public Short getApiVersionKey() {
        return apiVersionKey;
    }

    public void setApiVersionKey(Short apiVersionKey) {
        this.apiVersionKey = apiVersionKey;
    }

    public short getDescribeTopicPartitionKey() {
        return describeTopicPartitionKey;
    }

    public void setDescribeTopicPartitionKey(short describeTopicPartitionKey) {
        this.describeTopicPartitionKey = describeTopicPartitionKey;
    }

    public Short getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Short errorCode) {
        this.errorCode = errorCode;
    }

    public Byte getApiKeyCounts() {
        return apiKeyCounts;
    }

    public void setApiKeyCounts(Byte apiKeyCounts) {
        this.apiKeyCounts = apiKeyCounts;
    }

    public Short getApiMinVersion() {
        return apiMinVersion;
    }

    public void setApiMinVersion(Short apiMinVersion) {
        this.apiMinVersion = apiMinVersion;
    }

    public Short getApiMaxVersion() {
        return apiMaxVersion;
    }

    public void setApiMaxVersion(Short apiMaxVersion) {
        this.apiMaxVersion = apiMaxVersion;
    }

    public Integer getThrottleTimeMS() {
        return throttleTimeMS;
    }

    public void setThrottleTimeMS(Integer throttleTimeMS) {
        this.throttleTimeMS = throttleTimeMS;
    }

    public Byte getTaggedFieldSize() {
        return taggedFieldSize;
    }

    public void setTaggedFieldSize(Byte taggedFieldSize) {
        this.taggedFieldSize = taggedFieldSize;
    }
}
