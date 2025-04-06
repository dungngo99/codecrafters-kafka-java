package utils;

import constants.Constant;
import dto.ApiRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ByteUtil {
    public static ApiRequest parseApiRequest(Socket socket) throws IOException {
        ApiRequest ans = new ApiRequest();
        InputStream is = socket.getInputStream();

        byte[] messageSize = is.readNBytes(Constant.MESSAGE_SIZE_BYTE_SIZE);
        byte[] requestApiKey = is.readNBytes(Constant.REQUEST_API_KEY_BYTE_SIZE);
        byte[] requestApiVersion = is.readNBytes(Constant.REQUEST_API_VERSION_BYTE_SIZE);
        byte[] correlationId = is.readNBytes(Constant.CORRELATION_ID_BYTE_SIZE);

        ans.setMessageSize(ByteUtil.convertToIntFromByteArray(messageSize));
        ans.setRequestApiKey(ByteUtil.convertToShortFromByteArray(requestApiKey));
        ans.setRequestApiVersion(ByteUtil.convertToShortFromByteArray(requestApiVersion));
        ans.setCorrelationId((ByteUtil.convertToIntFromByteArray(correlationId)));
        ans.setClientId(Constant.EMPTY_STRING);
        ans.setTagBuffer(Constant.EMPTY_ARRAY);
        return ans;
    }

    public static int convertToIntFromByteArray(byte[] input) {
        assert (input.length == Constant.INT_BYTE_SIZE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(input.length);
        byteBuffer.put(input);
        byteBuffer.clear();
        return byteBuffer.getInt();
    }

    public static int convertToShortFromByteArray(byte[] input) {
        assert (input.length == Constant.SHORT_BYTE_SIZE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(input.length);
        byteBuffer.put(input);
        byteBuffer.clear();
        return byteBuffer.getShort();
    }
}
