package dto;

public class ApiResponse {
    private Integer messageSize;
    private ApiResponseHeader apiResponseHeader;
    private String body;

    public static class Builder {
        private final ApiResponse apiResponse;

        public Builder() {
            this.apiResponse = new ApiResponse();
            this.apiResponse.apiResponseHeader = new ApiResponseHeader();
        }

        public Builder addMessageSize(Integer messageSize) {
            this.apiResponse.messageSize = messageSize;
            this.apiResponse.apiResponseHeader.setMessageSize(messageSize);
            return this;
        }

        public Builder addCorrelationId(Integer correlationId) {
            this.apiResponse.apiResponseHeader.setCorrelationId(correlationId);
            return this;
        }

        public ApiResponse build() {
            return this.apiResponse;
        }
    }

    public static ApiResponse fromApiRequest(ApiRequest apiRequest) {
        return new ApiResponse.Builder()
                .addMessageSize(apiRequest.getMessageSize())
                .addCorrelationId(apiRequest.getCorrelationId())
                .build();
    }

    public Integer getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(Integer messageSize) {
        this.messageSize = messageSize;
    }

    public ApiResponseHeader getApiResponseHeader() {
        return apiResponseHeader;
    }

    public void setApiResponseHeader(ApiResponseHeader apiResponseHeader) {
        this.apiResponseHeader = apiResponseHeader;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
