package service.impl;

import dto.Field;
import dto.Offset;
import dto.request.RequestHeaderV2;
import dto.request.body.RequestApiVersionsBodyV4;
import dto.response.body.ResponseApiVersionsBodyV4;
import enums.ApiKey;
import enums.FieldType;
import service.BaseBrokerService;
import utils.BrokerUtil;
import utils.ByteUtil;
import utils.FieldUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ApiVersionsImpl extends BaseBrokerService<RequestApiVersionsBodyV4, ResponseApiVersionsBodyV4> {
    @Override
    public void registerHandler() {
        STORE.put(ApiKey.API_VERSIONS, this);
    }

    @Override
    public RequestApiVersionsBodyV4 parseRequestBody(byte[] bytes, Offset offset) {
        RequestApiVersionsBodyV4 v4 = new RequestApiVersionsBodyV4();
        Field clientIdLength = BrokerUtil.wrapField(bytes, offset, FieldType.BYTE);
        int clientIdLengthVal = ByteUtil.convertStreamToByte(clientIdLength.getData()) - FieldType.BYTE.getByteSize();
        Field clientId = BrokerUtil.wrapField(bytes, offset, FieldType.STRING, clientIdLengthVal);
        Field clientSoftwareVersionLength = BrokerUtil.wrapField(bytes, offset, FieldType.BYTE);
        byte clientSoftwareVersionLengthVal = ByteUtil.convertStreamToByte(clientSoftwareVersionLength.getData());
        Field clientSoftwareVersion = BrokerUtil.wrapField(bytes, offset, FieldType.STRING, clientSoftwareVersionLengthVal);
        Field tagBuffer = BrokerUtil.wrapField(bytes, offset, FieldType.BYTE);
        v4.setClientIdLength(clientIdLength);
        v4.setClientId(clientId);
        v4.setClientSoftwareVersionLength(clientSoftwareVersionLength);
        v4.setClientSoftwareVersion(clientSoftwareVersion);
        v4.setTagBuffer(tagBuffer);
        return v4;
    }

    @Override
    public ResponseApiVersionsBodyV4 convertToResponseBody(RequestApiVersionsBodyV4 request) {
        ResponseApiVersionsBodyV4 v4 = new ResponseApiVersionsBodyV4();
        v4.setErrorCode(FieldUtil.getNone());
        v4.setApiKeyCounts(FieldUtil.getDefaultApiKeyCount());
        List<ResponseApiVersionsBodyV4.Item> itemList = new ArrayList<>();
        for (Short apiKey: List.of(ApiKey.API_VERSIONS.getKey(), ApiKey.DESCRIBE_TOPIC_PARTITIONS.getKey())) {
            ResponseApiVersionsBodyV4.Item item = new ResponseApiVersionsBodyV4.Item();
            byte[] apiKeyStream = ByteUtil.convertShortToStream(apiKey);
            item.setApiKey(BrokerUtil.wrapField(apiKeyStream, FieldType.SHORT));
            item.setApiMinVersion(FieldUtil.getDefaultApiMinVersion());
            item.setApiMaxVersion(FieldUtil.getDefaultApiMaxVersion());
            item.setTaggedFieldSize(FieldUtil.getDefaultTaggedFieldSize());
            itemList.add(item);
        }
        v4.setApiVersionList(itemList);
        v4.setThrottleTimeMS(FieldUtil.getThrottleTimeMS());
        v4.setTaggedFieldSize(FieldUtil.getDefaultTaggedFieldSize());
        return v4;
    }

    @Override
    public LinkedList<Field> convertResponse(ResponseApiVersionsBodyV4 responseBody, RequestHeaderV2 requestHeader) {
        LinkedList<Field> fieldLinkedList = new LinkedList<>();
        fieldLinkedList.add(requestHeader.getCorrelationId());
        fieldLinkedList.add(responseBody.getErrorCode());
        fieldLinkedList.add(responseBody.getApiKeyCounts());
        for (ResponseApiVersionsBodyV4.Item item: responseBody.getApiVersionList()) {
            fieldLinkedList.add(item.getApiKey());
            fieldLinkedList.add(item.getApiMinVersion());
            fieldLinkedList.add(item.getApiMaxVersion());
            fieldLinkedList.add(item.getTaggedFieldSize());
        }
        fieldLinkedList.add(responseBody.getThrottleTimeMS());
        fieldLinkedList.add(responseBody.getTaggedFieldSize());
        return fieldLinkedList;
    }
}
