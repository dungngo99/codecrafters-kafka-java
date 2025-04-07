package service;

import constants.Constant;
import dto.ApiResponse;
import dto.Field;
import enums.FieldType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class BrokerService {

    private byte[] convert(List<Field<Number>> fieldList) {
        int allocationSize = fieldList.stream().flatMapToInt(e -> IntStream.builder().add(e.getByteSize()).build()).sum();
        ByteBuffer byteBuffer =  ByteBuffer.allocate(allocationSize).order(ByteOrder.BIG_ENDIAN);
        fieldList.forEach(f -> convertField(f, byteBuffer));
        return byteBuffer.array();
    }

    private void convertField(Field<Number> field, ByteBuffer byteBuffer) {
        if (Objects.equals(field.getFieldType(), FieldType.INTEGER)) {
            byteBuffer.putInt(field.getData().intValue());
        } else if (Objects.equals(field.getFieldType(), FieldType.SHORT)) {
            byteBuffer.putShort(field.getData().shortValue());
        }
    }

    public byte[] convertResponseMessageV0(int messageSize, int correlationId, int apiVersion) {
        List<Field<Number>> fieldList = new ArrayList<>();
        boolean isValidApiVersion = Constant.SUPPORTED_API_VERSIONS.contains(apiVersion);
        fieldList.add(new Field<>(messageSize, Constant.MESSAGE_SIZE_BYTE_SIZE, FieldType.INTEGER));
        fieldList.add(new Field<>(correlationId, Constant.CORRELATION_ID_BYTE_SIZE, FieldType.INTEGER));
        if (!isValidApiVersion) {
            fieldList.add(new Field<>(Constant.UNSUPPORTED_VERSION_ERROR_CODE, Constant.ERROR_CODE_BYTE_SIZE, FieldType.SHORT));
        }
        return convert(fieldList);
    }

    public byte[] convertResponseMessageV2(ApiResponse apiResponse) {
        return convertResponseMessageV0(
                apiResponse.getMessageSize(),
                apiResponse.getApiResponseHeader().getCorrelationId(),
                apiResponse.getApiResponseHeader().getApiVersion());
    }

}
