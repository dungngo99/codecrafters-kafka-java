package service.load.impl;

import constants.Constant;
import dto.Field;
import dto.metadata.record.PartitionRecord;
import enums.FieldType;
import enums.RecordType;
import service.load.ClusterMetadataLoadService;
import utils.BrokerUtil;
import utils.ByteUtil;
import utils.FieldUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class PartitionValueImpl extends ClusterMetadataLoadService<PartitionRecord> {
    @Override
    protected PartitionRecord createValue() {
        return new PartitionRecord();
    }

    @Override
    protected void load(FileInputStream is, PartitionRecord value) throws IOException {
        value.setPartitionId(BrokerUtil.wrapField(is, FieldType.INTEGER));
        value.setTopicUUID(BrokerUtil.wrapField(is, FieldType.STRING, Constant.TOPIC_ID_LENGTH));
        value.setReplicaArrayLength(BrokerUtil.wrapField(is, FieldType.BYTE));

        int replicaArrayLength = ByteUtil.convertStreamToByte(value.getReplicaArrayLength().getData()) - FieldType.BYTE.getByteSize();
        if (replicaArrayLength == -1) {
            value.setReplicaArray(null);
        } else {
            LinkedList<Field> replicaArray = new LinkedList<>();
            for (int i=0; i<replicaArrayLength; i++) {
                replicaArray.add(BrokerUtil.wrapField(is, FieldType.INTEGER));
            }
            value.setReplicaArray(replicaArray);
        }

        value.setInSyncReplicaArrayLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        int inSyncReplicaArrayLength = ByteUtil.convertStreamToByte(value.getInSyncReplicaArrayLength().getData()) - FieldType.BYTE.getByteSize();
        if (inSyncReplicaArrayLength == -1) {
            value.setInSyncReplicaArray(null);
        } else {
            LinkedList<Field> inSyncReplicaArray = new LinkedList<>();
            for (int i=0; i<inSyncReplicaArrayLength; i++) {
                inSyncReplicaArray.add(BrokerUtil.wrapField(is, FieldType.INTEGER));
            }
            value.setInSyncReplicaArray(inSyncReplicaArray);
        }

        value.setRemoveReplicaArrayLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        value.setAddReplicaArrayLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        value.setLeader(BrokerUtil.wrapField(is, FieldType.INTEGER));
        value.setLeaderEpoch(BrokerUtil.wrapField(is, FieldType.INTEGER));
        value.setPartitionEpoch(BrokerUtil.wrapField(is, FieldType.INTEGER));

        value.setDirectoryArrayLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        int directoryArrayLength = ByteUtil.convertStreamToByte(value.getDirectoryArrayLength().getData()) - FieldType.BYTE.getByteSize();
        if (directoryArrayLength == -1) {
            value.setDirectoryArray(null);
        } else {
            LinkedList<Field> directoryArray = new LinkedList<>();
            for (int i=0; i<directoryArrayLength; i++) {
                directoryArray.add(BrokerUtil.wrapField(is, FieldType.STRING, Constant.TOPIC_ID_LENGTH));
            }
            value.setDirectoryArray(directoryArray);
        }
    }

    @Override
    protected void map(PartitionRecord value) {
        PartitionRecord copiedPartitionRecord = ByteUtil.deepCopy(value);
        if (Objects.isNull(copiedPartitionRecord)) {
            return;
        }
        byte[] partitionIdStream = copiedPartitionRecord.getPartitionId().getData();
        byte[] topicIdStream = copiedPartitionRecord.getTopicUUID().getData();
        Field recordId = FieldUtil.getRecordId(partitionIdStream, topicIdStream);
        value.setRecordId(recordId);
        PARTITION_RECORD_MAP.put(recordId, copiedPartitionRecord);
    }

    @Override
    public void register() {
        ClusterMetadataLoadService.STORE.put(RecordType.PARTITION, this);
    }
}
