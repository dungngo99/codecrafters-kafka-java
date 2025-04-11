package utils;

import dto.Offset;
import enums.FieldType;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteUtil {

    public static byte convertStreamToByte(byte[] input) {
        return getByteBuffer(input, FieldType.BYTE.getByteSize()).get();
    }

    public static short convertStreamToShort(byte[] input) {
        return getByteBuffer(input, FieldType.SHORT.getByteSize()).getShort();
    }

    public static int convertStreamToInt(byte[] input) {
        return getByteBuffer(input, FieldType.INTEGER.getByteSize()).getInt();
    }

    public static byte[] convertIntToStream(int input) {
        return getByteBuffer(FieldType.INTEGER.getByteSize()).putInt(input).clear().array();
    }

    public static byte[] convertShortToStream(short input) {
        return getByteBuffer(FieldType.SHORT.getByteSize()).putShort(input).clear().array();
    }

    public static byte[] convertByteToStream(byte input) {
        return getByteBuffer(FieldType.BYTE.getByteSize()).put(input).clear().array();
    }

    public static byte[] copyOfRangeByOffset(byte[] input, Offset offset, int length) {
        return Arrays.copyOfRange(input, offset.getOffset(), offset.addByThenReturn(length));
    }

    private static ByteBuffer getByteBuffer(byte[] input, int length) {
        assert (input.length == length);
        ByteBuffer byteBuffer = ByteBuffer.allocate(length);
        byteBuffer.put(input);
        byteBuffer.clear();
        return byteBuffer;
    }

    private static ByteBuffer getByteBuffer(int length) {
        return ByteBuffer.allocate(length);
    }
}
