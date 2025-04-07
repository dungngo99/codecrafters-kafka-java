package service;

import constants.Constant;
import dto.ApiResponse;
import dto.ApiResponseBody;
import dto.Field;
import enums.FieldType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class BrokerService {

    private byte[] convert(List<Field<Number>> fieldList) {
        int allocationSize = getMessageSizeFromFieldList(fieldList);
        ByteBuffer byteBuffer =  ByteBuffer.allocate(allocationSize).order(ByteOrder.BIG_ENDIAN);
        fieldList.forEach(f -> convertField(f, byteBuffer));
        return byteBuffer.array();
    }

    private int getMessageSizeFromFieldList(List<Field<Number>> fieldList) {
        return fieldList.stream()
                .flatMapToInt(e -> IntStream.builder().add(e.getFieldType().getByteSize()).build())
                .sum();
    }

    private void convertField(Field<Number> field, ByteBuffer byteBuffer) {
        if (Objects.equals(field.getFieldType(), FieldType.INTEGER)) {
            byteBuffer.putInt(field.getData().intValue());
        } else if (Objects.equals(field.getFieldType(), FieldType.SHORT)) {
            byteBuffer.putShort(field.getData().shortValue());
        } else if (Objects.equals(field.getFieldType(), FieldType.BYTE)) {
            byteBuffer.put(field.getData().byteValue());
        }
    }

    public byte[] convertResponseMessageV0(ApiResponse apiResponse) {
        int correlationId = apiResponse.getApiResponseHeader().getCorrelationId();
        ApiResponseBody body = apiResponse.getBody();

        LinkedList<Field<Number>> fieldList = new LinkedList<>();
        boolean isValidApiVersion = Constant.SUPPORTED_API_VERSIONS.contains(body.getApiVersion());
        fieldList.add(new Field<>(correlationId, FieldType.INTEGER));
        if (!isValidApiVersion) {
            fieldList.add(new Field<>(Constant.UNSUPPORTED_VERSION_ERROR_CODE, FieldType.SHORT));
        } else {
            fieldList.add(new Field<>(Constant.NO_ERROR_CODE, FieldType.SHORT));
            fieldList.add(new Field<>(body.getApiKeyCounts(), FieldType.BYTE));
            fieldList.add(new Field<>(body.getApiKey(), FieldType.SHORT));
            fieldList.add(new Field<>(body.getApiMinVersion(), FieldType.SHORT));
            fieldList.add(new Field<>(body.getApiMaxVersion(), FieldType.SHORT));
            fieldList.add(new Field<>(body.getTaggedFieldSize(), FieldType.BYTE));
            fieldList.add(new Field<>(body.getThrottleTimeMS(), FieldType.INTEGER));
            fieldList.add(new Field<>(body.getTaggedFieldSize(), FieldType.BYTE));
        }
        int messageSize = getMessageSizeFromFieldList(fieldList);
        fieldList.addFirst(new Field<>(messageSize, FieldType.INTEGER));
        return convert(fieldList);
    }

    public byte[] convertResponseMessageV2(ApiResponse apiResponse) {
        return convertResponseMessageV0(apiResponse);
    }

    public void fillDefaultValues(ApiResponse apiResponse) {
        apiResponse.getBody().setApiKeyCounts(Constant.API_KEY_COUNTS);
        apiResponse.getBody().setApiMinVersion(Constant.API_MIN_VERSION);
        apiResponse.getBody().setApiMaxVersion(Constant.API_MAX_VERSION);
        apiResponse.getBody().setThrottleTimeMS(Constant.THROTTLE_TIME_MS);
        apiResponse.getBody().setTaggedFieldSize(Constant.TAGGED_FIELD_SIZE);
    }

}
