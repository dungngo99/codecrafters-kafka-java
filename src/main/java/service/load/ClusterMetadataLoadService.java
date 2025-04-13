package service.load;

import dto.Field;
import dto.metadata.Value;
import dto.metadata.record.PartitionRecord;
import dto.metadata.record.TopicRecord;
import enums.FieldType;
import enums.RecordType;
import utils.BrokerUtil;
import utils.ByteUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public abstract class ClusterMetadataLoadService<T extends Value> {
    public static final Map<Field, TopicRecord> TOPIC_RECORD_MAP = new HashMap<>();
    public static final Map<Field, PartitionRecord> PARTITION_RECORD_MAP = new HashMap<>();
    protected static final Map<RecordType, ClusterMetadataLoadService> STORE = new HashMap<>();

    private static LinkedList<Field> fillPreCommonValues(FileInputStream is) throws IOException {
        LinkedList<Field> preFields = new LinkedList<>();
        preFields.add(BrokerUtil.wrapField(is, FieldType.BYTE)); // frameVersion
        preFields.add(BrokerUtil.wrapField(is, FieldType.BYTE)); // type
        preFields.add(BrokerUtil.wrapField(is, FieldType.BYTE)); // version
        return preFields;
    }

    private static LinkedList<Field> fillPostCommonValues(FileInputStream is) throws IOException {
        LinkedList<Field> postFields = new LinkedList<>();
        postFields.add(BrokerUtil.wrapField(is, FieldType.BYTE));
        return postFields;
    }

    public static Value getClusterMetadataValue(FileInputStream is) throws IOException {
        LinkedList<Field> preFields = fillPreCommonValues(is);

        Field type = preFields.get(1);
        byte b = ByteUtil.convertStreamToByte(type.getData());
        RecordType recordType = RecordType.ofType(b);
        ClusterMetadataLoadService handler = STORE.get(recordType);
        if (Objects.isNull(handler)) {
            throw new RuntimeException("not found handler to load cluster metadata value");
        }

        Value value = handler.createValue();
        handler.load(is, value);
        handler.map(value);

        LinkedList<Field> postFields = fillPostCommonValues(is);

        value.setFrameVersion(preFields.get(0));
        value.setType(preFields.get(1));
        value.setVersion(preFields.get(2));
        value.setTaggedFieldCount(postFields.get(0));

        return value;
    }

    protected abstract T createValue();

    protected abstract void load(FileInputStream is, T value) throws IOException;

    protected abstract void map(T value);

    public abstract void register();
}
