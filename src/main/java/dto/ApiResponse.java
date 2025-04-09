package dto;

public class ApiResponse {
    private Integer messageSize;
    private ApiResponseHeader apiResponseHeader;
    private ApiResponseBody body;

    public static class Builder {
        private final ApiResponse apiResponse;

        public Builder() {
            this.apiResponse = new ApiResponse();
            this.apiResponse.apiResponseHeader = new ApiResponseHeader();
            this.apiResponse.body = new ApiResponseBody();
        }

        public Builder addCorrelationId(Integer correlationId) {
            this.apiResponse.apiResponseHeader.setCorrelationId(correlationId);
            return this;
        }

        public Builder addApiKey(Short apiKey) {
            this.apiResponse.body.setApiKey(apiKey);
            return this;
        }

        public Builder addApiVersion(Short apiVersion) {
            this.apiResponse.body.setApiVersionKey(apiVersion);
            return this;
        }

        public ApiResponse build() {
            return this.apiResponse;
        }
    }

    public static ApiResponse fromApiRequest(ApiRequest apiRequest) {
        return new ApiResponse.Builder()
                .addCorrelationId(apiRequest.getCorrelationId())
                .addApiKey(apiRequest.getRequestApiKey().shortValue())
                .addApiVersion(apiRequest.getRequestApiVersion().shortValue())
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

    public ApiResponseBody getBody() {
        return body;
    }

    public void setBody(ApiResponseBody body) {
        this.body = body;
    }
}
