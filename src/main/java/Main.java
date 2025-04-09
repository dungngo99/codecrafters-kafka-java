import dto.ApiRequest;
import dto.ApiResponse;
import service.BrokerService;
import utils.ByteUtil;
import utils.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        // You can use print statements as follows for debugging, they'll be visible when running tests.
        // Uncomment this block to pass the first stage

        ServerSocket serverSocket;
        Socket clientSocket = null;
        int port = 9092;
        try {
            serverSocket = new ServerSocket(port);
            BrokerService brokerService = new BrokerService();
            // Since the tester restarts your program quite often, setting SO_REUSEADDR
            // ensures that we don't run into 'Address already in use' errors
            serverSocket.setReuseAddress(true);
            // Wait for connection from client.
            while (!serverSocket.isClosed()) {
                clientSocket = serverSocket.accept(); // wait for connection from client
                Socket finalClientSocket = clientSocket;
                new Thread(() -> brokerService.handleClientSocket(finalClientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }
}
