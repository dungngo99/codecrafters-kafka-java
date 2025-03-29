package utils;

import constants.Constant;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FormatUtil {
    public static byte[] convertResponseMessageV0(int messageSize, int correlationId) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Constant.INT_BYTE_SIZE*2);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.putInt(messageSize);
        byteBuffer.putInt(correlationId);
        return byteBuffer.array();
    }
}
