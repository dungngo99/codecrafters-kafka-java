package service.load.impl;

import constants.Constant;
import dto.Field;
import dto.metadata.record.TopicRecord;
import enums.FieldType;
import enums.RecordType;
import service.load.ClusterMetadataLoadService;
import utils.BrokerUtil;
import utils.ByteUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class TopicValueImpl extends ClusterMetadataLoadService<TopicRecord> {
    @Override
    protected TopicRecord createValue() {
        return new TopicRecord();
    }

    @Override
    protected void load(FileInputStream is, TopicRecord value) throws IOException {
        value.setNameLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        int nameLength = ByteUtil.convertStreamToByte(value.getNameLength().getData()) - FieldType.BYTE.getByteSize();
        value.setTopicName(BrokerUtil.wrapField(is, FieldType.STRING, nameLength));
        value.setTopicUUID(BrokerUtil.wrapField(is, FieldType.STRING, Constant.TOPIC_ID_LENGTH));
    }

    @Override
    protected void map(TopicRecord value) {
        TopicRecord copiedTopicRecord = ByteUtil.deepCopy(value);
        if (Objects.isNull(copiedTopicRecord)) {
            return;
        }
        byte[] topicIdStream = copiedTopicRecord.getTopicUUID().getData();
        Field topicId = BrokerUtil.wrapField(topicIdStream, FieldType.STRING, Constant.TOPIC_ID_LENGTH);
        TOPIC_RECORD_MAP.put(topicId, copiedTopicRecord);
    }

    @Override
    public void register() {
        ClusterMetadataLoadService.STORE.put(RecordType.TOPIC, this);
    }
}
