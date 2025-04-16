package utils;

import constants.Constant;
import dto.Field;
import enums.ErrorCode;
import enums.FieldType;

public class FieldUtil {

    public static Field getNone() {
        byte[] errorCodeStream = ByteUtil.convertShortToStream(ErrorCode.NONE.getCode().shortValue());
        return BrokerUtil.wrapField(errorCodeStream, FieldType.SHORT);
    }

    public static Field getApiKeyCount(Byte apiKeyCount) {
        byte[] apiKeyCountStream = ByteUtil.convertByteToStream(apiKeyCount);
        return BrokerUtil.wrapField(apiKeyCountStream, FieldType.BYTE);
    }

    public static Field getDefaultApiMinVersion() {
        byte[] apiMinVersionStream = ByteUtil.convertShortToStream(Constant.API_MIN_VERSION);
        return BrokerUtil.wrapField(apiMinVersionStream, FieldType.SHORT);
    }

    public static Field getDefaultApiMaxVersion() {
        byte[] apiMaxVersionStream = ByteUtil.convertShortToStream(Constant.API_MAX_VERSION);
        return BrokerUtil.wrapField(apiMaxVersionStream, FieldType.SHORT);
    }

    public static Field getThrottleTimeMS() {
        byte[] throttleTimeMSStream = ByteUtil.convertIntToStream(Constant.THROTTLE_TIME_MS);
        return BrokerUtil.wrapField(throttleTimeMSStream, FieldType.INTEGER);
    }

    public static Field getDefaultTaggedFieldSize() {
        byte[] taggedFieldSizeStream = ByteUtil.convertByteToStream(Constant.TAGGED_FIELD_SIZE);
        return BrokerUtil.wrapField(taggedFieldSizeStream, FieldType.BYTE);
    }

    public static Field getDefaultUnknownTopicOrPartition() {
        byte[] errorCodeStream = ByteUtil.convertShortToStream(ErrorCode.UNKNOWN_TOPIC_OR_PARTITION.getCode().shortValue());
        return BrokerUtil.wrapField(errorCodeStream, FieldType.SHORT);
    }

    public static Field getDefaultIsInternal() {
        byte[] isInternalStream = ByteUtil.convertByteToStream(Constant.DEFAULT_IS_INTERNAL);
        return BrokerUtil.wrapField(isInternalStream, FieldType.BYTE);
    }

    public static Field getDefaultPartitionArrayLength() {
        byte[] partitionArrayLengthStream = ByteUtil.convertByteToStream(Constant.DEFAULT_PARTITION_ARRAY_LENGTH);
        return BrokerUtil.wrapField(partitionArrayLengthStream, FieldType.BYTE);
    }

    public static Field getDefaultTopicAuthorizedOperations() {
        byte[] topicAuthorizedOperationsStream = ByteUtil.convertIntToStream(Constant.DEFAULT_TOPIC_AUTHORIZED_OPERATIONS);
        return BrokerUtil.wrapField(topicAuthorizedOperationsStream, FieldType.INTEGER);
    }

    public static Field getUnSupportedVersion() {
        byte[] errorCodeStream = ByteUtil.convertShortToStream(ErrorCode.UNSUPPORTED_VERSION.getCode().shortValue());
        return BrokerUtil.wrapField(errorCodeStream, FieldType.SHORT);
    }

    public static Field getRecordId(byte[] partitionIdStream, byte[] topicIdStream) {
        int partitionIdStreamLength = partitionIdStream.length;
        int topicIdStreamLength = topicIdStream.length;
        int recordIdStreamLength = partitionIdStreamLength+topicIdStreamLength;
        byte[] recordIdStream = new byte[recordIdStreamLength];
        System.arraycopy(partitionIdStream, 0, recordIdStream, 0, partitionIdStreamLength);
        System.arraycopy(topicIdStream, 0, recordIdStream, partitionIdStreamLength, topicIdStreamLength);
        return BrokerUtil.wrapField(recordIdStream, FieldType.STRING, recordIdStreamLength);
    }

    public static Field getDefaultEligibleLeaderReplicasLength() {
        byte[] eligibleLeaderReplicasLength = ByteUtil.convertByteToStream(Constant.DEFAULT_ELIGIBLE_LEADER_REPLICAS_LENGTH);
        return BrokerUtil.wrapField(eligibleLeaderReplicasLength, FieldType.BYTE);
    }

    public static Field getDefaultLastKnownELRLength() {
        byte[] lastKnownELRLength = ByteUtil.convertByteToStream(Constant.DEFAULT_LAST_KNOWN_ELR_LENGTH);
        return BrokerUtil.wrapField(lastKnownELRLength, FieldType.BYTE);
    }

    public static Field getDefaultOfflineReplicasLength() {
        byte[] offlineReplicasLength = ByteUtil.convertByteToStream(Constant.DEFAULT_OFFLINE_REPLICAS_LENGTH);
        return BrokerUtil.wrapField(offlineReplicasLength, FieldType.BYTE);
    }
}
