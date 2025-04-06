package utils;

import constants.Constant;
import dto.ApiResponse;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FormatUtil {
    public static byte[] convertResponseMessageV0(int messageSize, int correlationId) {
        ByteBuffer byteBuffer = ByteBuffer
                .allocate(Constant.MESSAGE_SIZE_BYTE_SIZE + Constant.CORRELATION_ID_BYTE_SIZE)
                .order(ByteOrder.BIG_ENDIAN);
        byteBuffer.putInt(messageSize);
        byteBuffer.putInt(correlationId);
        return byteBuffer.array();
    }

    public static byte[] convertResponseMessageV2(ApiResponse apiResponse) {
        return convertResponseMessageV0(apiResponse.getMessageSize(), apiResponse.getApiResponseHeader().getCorrelationId());
    }
}
