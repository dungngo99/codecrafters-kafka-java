package service.impl;

import constants.Constant;
import dto.Offset;
import dto.request.RequestHeaderV2;
import dto.request.body.RequestDescribeTopicPartitionsBodyV0;
import dto.response.body.ResponseDescribeTopicPartitionsBodyV0;
import dto.Field;
import enums.ApiKey;
import enums.FieldType;
import service.BaseBrokerService;
import service.BrokerService;
import utils.BrokerUtil;
import utils.ByteUtil;
import utils.FieldUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DescribeTopicPartitionsImpl extends BaseBrokerService<RequestDescribeTopicPartitionsBodyV0, ResponseDescribeTopicPartitionsBodyV0> {

    @Override
    public void registerHandler() {
        BrokerService.STORE.put(ApiKey.DESCRIBE_TOPIC_PARTITIONS, this);
    }

    @Override
    public RequestDescribeTopicPartitionsBodyV0 parseRequestBody(byte[] bytes, Offset offset) {
        RequestDescribeTopicPartitionsBodyV0 v0 = new RequestDescribeTopicPartitionsBodyV0();
        Field topicLength = BrokerUtil.wrapField(bytes, offset, FieldType.BYTE);
        v0.setTopicListLength(topicLength);
        byte topicLengthVal = ByteUtil.convertStreamToByte(topicLength.getData());
        if (topicLengthVal == 0) {
            v0.setTopicList(null);
        } else if (topicLengthVal == 1) {
            v0.setTopicList(new ArrayList<>());
        } else {
            List<RequestDescribeTopicPartitionsBodyV0.Item> itemList = new ArrayList<>();
            for (int i=0; i<topicLengthVal-1; i++) {
                RequestDescribeTopicPartitionsBodyV0.Item item = new RequestDescribeTopicPartitionsBodyV0.Item();
                Field topicNameLength = BrokerUtil.wrapField(bytes, offset, FieldType.BYTE);
                item.setTopicNameLength(topicNameLength);
                int topicNameLengthVal = ByteUtil.convertStreamToByte(topicNameLength.getData()) - FieldType.BYTE.getByteSize();
                Field topicName = BrokerUtil.wrapField(bytes, offset, FieldType.STRING, topicNameLengthVal);
                item.setTopicName(topicName);
                Field topicTagBuffer = BrokerUtil.wrapField(bytes, offset, FieldType.BYTE);
                item.setTagBuffer(topicTagBuffer);
                itemList.add(item);
            }
            v0.setTopicList(itemList);
        }
        Field responsePartitionLimit = BrokerUtil.wrapField(bytes, offset, FieldType.INTEGER);
        v0.setResponsePartitionLimit(responsePartitionLimit);
        Field cursor = BrokerUtil.wrapField(bytes, offset, FieldType.BYTE);
        v0.setCursor(cursor);
        Field tagBuffer = BrokerUtil.wrapField(bytes, offset, FieldType.BYTE);
        v0.setTagBuffer(tagBuffer);
        return v0;
    }

    @Override
    public ResponseDescribeTopicPartitionsBodyV0 convertToResponseBody(RequestDescribeTopicPartitionsBodyV0 request) {
        ResponseDescribeTopicPartitionsBodyV0 v0 = new ResponseDescribeTopicPartitionsBodyV0();
        v0.setThrottleTimeMS(FieldUtil.getThrottleTimeMS());
        v0.setTopicArrayLength(request.getTopicListLength());
        List<ResponseDescribeTopicPartitionsBodyV0.Item> itemList = new ArrayList<>();
        for (RequestDescribeTopicPartitionsBodyV0.Item requestItem: request.getTopicList()) {
            ResponseDescribeTopicPartitionsBodyV0.Item responseItem = new ResponseDescribeTopicPartitionsBodyV0.Item();
            responseItem.setErrorCode(FieldUtil.getDefaultUnknownTopicOrPartition());
            responseItem.setTopicNameLength(requestItem.getTopicNameLength());
            responseItem.setTopicName(requestItem.getTopicName());
            responseItem.setTopicId(BrokerUtil.wrapField(Constant.DEFAULT_TOPIC_ID, FieldType.STRING, Constant.TOPIC_ID_LENGTH));
            responseItem.setIsInternal(FieldUtil.getDefaultIsInternal());
            responseItem.setPartitionArrayLength(FieldUtil.getDefaultPartitionArrayLength());
            responseItem.setTopicAuthorizedOperations(FieldUtil.getDefaultTopicAuthorizedOperations());
            responseItem.setTaggedFieldSize(FieldUtil.getDefaultTaggedFieldSize());
            itemList.add(responseItem);
        }
        v0.setTopicList(itemList);
        v0.setNextCursor(request.getCursor());
        v0.setTaggedFieldSize(FieldUtil.getDefaultTaggedFieldSize());
        return v0;
    }

    @Override
    public LinkedList<Field> convertResponse(ResponseDescribeTopicPartitionsBodyV0 responseBody, RequestHeaderV2 requestHeader) {
        LinkedList<Field> fieldLinkedList = new LinkedList<>();
        fieldLinkedList.add(requestHeader.getCorrelationId());
        fieldLinkedList.add(FieldUtil.getDefaultTaggedFieldSize());
        fieldLinkedList.add(responseBody.getThrottleTimeMS());
        fieldLinkedList.add(responseBody.getTopicArrayLength());
        for (ResponseDescribeTopicPartitionsBodyV0.Item item: responseBody.getTopicList()) {
            fieldLinkedList.add(item.getErrorCode());
            fieldLinkedList.add(item.getTopicNameLength());
            fieldLinkedList.add(item.getTopicName());
            fieldLinkedList.add(item.getTopicId());
            fieldLinkedList.add(item.getIsInternal());
            fieldLinkedList.add(item.getPartitionArrayLength());
            fieldLinkedList.add(item.getTopicAuthorizedOperations());
            fieldLinkedList.add(FieldUtil.getDefaultTaggedFieldSize());
        }
        fieldLinkedList.add(responseBody.getNextCursor());
        fieldLinkedList.add(FieldUtil.getDefaultTaggedFieldSize());
        return fieldLinkedList;
    }
}
