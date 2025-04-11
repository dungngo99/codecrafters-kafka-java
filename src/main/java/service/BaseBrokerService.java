package service;

import constants.Constant;
import dto.Field;
import dto.Offset;
import dto.request.RequestHeaderV2;
import dto.request.body.RequestBaseBody;
import dto.response.body.ResponseBaseBody;
import enums.FieldType;
import utils.BrokerUtil;
import utils.ByteUtil;
import utils.SocketUtil;
import utils.VersionUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.Duration;
import java.util.LinkedList;

public abstract class BaseBrokerService<T extends RequestBaseBody, R extends ResponseBaseBody> implements BrokerService<T, R> {

    public static void handle(Socket clientSocket) {
        try {
            while (!clientSocket.isClosed()) {
                handle0(clientSocket);
            }
            Thread.sleep(Duration.ofMillis(Constant.GRACE_PERIOD_BETWEEN_REQUEST_HANDLING));
        } catch (Exception e) {
            System.out.println("Failed to handle client socket, error=" + e);
        } finally {
            try {
                SocketUtil.closeSocket(clientSocket);
            } catch (IOException e) {
                System.out.println("Failed to close client socket, error=" + e);
            }
        }
    }

    private static void handle0(Socket clientSocket) throws IOException {
        InputStream is = clientSocket.getInputStream();
        byte[] messageSizeArr = is.readNBytes(FieldType.INTEGER.getByteSize());
        int messageSize = ByteUtil.convertStreamToInt(messageSizeArr);
        byte[] data = is.readNBytes(messageSize);

        Offset offset = new Offset();
        Field requestApiKey = BrokerUtil.wrapField(data, offset, FieldType.SHORT);
        Field requestApiVersion = BrokerUtil.wrapField(data, offset, FieldType.SHORT);
        Field correlationId = BrokerUtil.wrapField(data, offset, FieldType.INTEGER);
        Field clientIdLength = BrokerUtil.wrapField(data, offset, FieldType.SHORT);
        short clientIdLengthVal = ByteUtil.convertStreamToShort(clientIdLength.getData());
        Field clientId = BrokerUtil.wrapField(data, offset, FieldType.STRING, clientIdLengthVal);
        Field tagBuffer = BrokerUtil.wrapField(data, offset, FieldType.BYTE);

        RequestHeaderV2 requestHeader = new RequestHeaderV2();
        requestHeader.setRequestApiKey(requestApiKey);
        requestHeader.setRequestApiVersion(requestApiVersion);
        requestHeader.setCorrelationId(correlationId);
        requestHeader.setClientIdLength(clientIdLength);
        requestHeader.setClientId(clientId);
        requestHeader.setTagBuffer(tagBuffer);

        short apiVersionVal = ByteUtil.convertStreamToShort(requestApiVersion.getData());
        if (!VersionUtil.isValidApiVersion(apiVersionVal)) {
            BrokerUtil.handleError(clientSocket, requestHeader);
            return;
        }

        short requestApiKeyVal = ByteUtil.convertStreamToShort(requestApiKey.getData());
        BaseBrokerService handler = BrokerUtil.getInstance(requestApiKeyVal);
        RequestBaseBody requestBody = handler.parseRequestBody(data, offset);
        ResponseBaseBody responseBody = handler.convertToResponseBody(requestBody);
        LinkedList<Field> fieldLinkedList = handler.convertResponse(responseBody, requestHeader);
        BrokerUtil.handle(clientSocket, fieldLinkedList);
    }
}
