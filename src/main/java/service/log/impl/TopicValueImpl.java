package service.log.impl;

import constants.Constant;
import dto.Field;
import dto.metadata.record.TopicValue;
import enums.FieldType;
import enums.ValueType;
import service.log.LogValueService;
import utils.BrokerUtil;
import utils.ByteUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class TopicValueImpl extends LogValueService<TopicValue> {
    @Override
    protected TopicValue createValue() {
        return new TopicValue();
    }

    @Override
    protected void load(FileInputStream is, TopicValue value) throws IOException {
        value.setNameLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        int nameLength = ByteUtil.convertStreamToByte(value.getNameLength().getData()) - FieldType.BYTE.getByteSize();
        value.setTopicName(BrokerUtil.wrapField(is, FieldType.STRING, nameLength));
        value.setTopicUUID(BrokerUtil.wrapField(is, FieldType.STRING, Constant.TOPIC_ID_LENGTH));
    }

    @Override
    protected void map(TopicValue value) {
        TopicValue copiedTopicValue = ByteUtil.deepCopy(value);
        if (Objects.isNull(copiedTopicValue)) {
            return;
        }
        byte[] topicIdStream = copiedTopicValue.getTopicUUID().getData();
        Field topicId = BrokerUtil.wrapField(topicIdStream, FieldType.STRING, Constant.TOPIC_ID_LENGTH);
        METADATA_CLUSTER_TOPIC_VALUE_MAP.put(topicId, copiedTopicValue);
    }

    @Override
    public void register() {
        LogValueService.STORE.put(ValueType.TOPIC, this);
    }
}
