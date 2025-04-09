package utils;

import constants.Constant;
import dto.ApiRequest;
import enums.FieldType;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ByteUtil {
    public static ApiRequest parseApiRequest(Socket socket) throws IOException {
        ApiRequest ans = new ApiRequest();
        InputStream is = socket.getInputStream();

        byte[] messageSizeArr = is.readNBytes(FieldType.INTEGER.getByteSize());
        int messageSize = ByteUtil.convertToIntFromByteArray(messageSizeArr);
        byte[] buffer = is.readNBytes(messageSize);
        int offset = 0;
        byte[] requestApiKeyArr = Arrays.copyOfRange(buffer, offset, offset+=FieldType.SHORT.getByteSize());
        byte[] requestApiVersionArr = Arrays.copyOfRange(buffer, offset, offset+=FieldType.SHORT.getByteSize());
        byte[] correlationIdArr = Arrays.copyOfRange(buffer, offset, offset+=FieldType.INTEGER.getByteSize());
        byte[] somethingArr = Arrays.copyOfRange(buffer, offset, offset+=FieldType.SHORT.getByteSize());
        byte[] clientId = Arrays.copyOfRange(buffer, offset, offset+=Constant.CLIENT_ID_BYTE_SIZE);
        byte[] tagBufferArr = Arrays.copyOfRange(buffer, offset, offset+FieldType.BYTE.getByteSize());

        ans.setMessageSize(ByteUtil.convertToIntFromByteArray(messageSizeArr));
        ans.setRequestApiKey(ByteUtil.convertToShortFromByteArray(requestApiKeyArr));
        ans.setRequestApiVersion(ByteUtil.convertToShortFromByteArray(requestApiVersionArr));
        ans.setCorrelationId((ByteUtil.convertToIntFromByteArray(correlationIdArr)));
        ans.setClientId(new String(clientId, StandardCharsets.US_ASCII));
        ans.setTagBuffer(Constant.EMPTY_ARRAY);
        return ans;
    }

    public static int convertToIntFromByteArray(byte[] input) {
        assert (input.length == FieldType.INTEGER.getByteSize());
        ByteBuffer byteBuffer = ByteBuffer.allocate(input.length);
        byteBuffer.put(input);
        byteBuffer.clear();
        return byteBuffer.getInt();
    }

    public static int convertToShortFromByteArray(byte[] input) {
        assert (input.length == FieldType.SHORT.getByteSize());
        ByteBuffer byteBuffer = ByteBuffer.allocate(input.length);
        byteBuffer.put(input);
        byteBuffer.clear();
        return byteBuffer.getShort();
    }
}
