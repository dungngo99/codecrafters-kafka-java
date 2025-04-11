package service;

import dto.Field;
import dto.Offset;
import dto.request.RequestHeaderV2;
import dto.request.body.RequestBaseBody;
import dto.response.body.ResponseBaseBody;
import enums.ApiKey;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public interface BrokerService<T extends RequestBaseBody, R extends ResponseBaseBody> {
    Map<ApiKey, BaseBrokerService<?, ?>> STORE = new HashMap<>();

    /**
     * register a handler to local map
     */
    void registerHandler();

    /**
     * parse the byte stream into request body obj
     * @param bytes request byte stream
     * @param offset current offset
     * @return request body
     */
    T parseRequestBody(byte[] bytes, Offset offset);

    /**
     * load info from request obj to response obj
     * @param request request body
     * @return response body
     */
    R convertToResponseBody(T request);

    /**
     * convert response obj to list of fields
     * @param responseBody response body
     * @param requestHeader request header V2
     * @return list of field items
     */
    LinkedList<Field> convertResponse(R responseBody, RequestHeaderV2 requestHeader);
}
