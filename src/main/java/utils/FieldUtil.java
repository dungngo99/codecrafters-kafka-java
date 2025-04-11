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

    public static Field getDefaultApiKeyCount() {
        byte[] apiKeyCountStream = ByteUtil.convertByteToStream(Constant.API_KEY_COUNTS);
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
}
