package utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketUtil {
    public static void writeThenFlushSocket(Socket socket, byte[] bytes) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
    }

    public static void closeSocket(Socket socket) throws IOException {
        if (socket == null || socket.isClosed()) {
            return;
        }
        socket.close();
    }
}
