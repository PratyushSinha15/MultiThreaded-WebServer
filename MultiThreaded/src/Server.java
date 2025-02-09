import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try (Socket socket = clientSocket;
                 PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println("Client connected: " + socket.getRemoteSocketAddress());
                toClient.println("Hello from server");

            } catch (IOException e) {
                System.err.println("Client handling error: " + e.getMessage());
            }
        };
    }

    public static void main(String[] args) {
        Server server = new Server();
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is running on port " + port);

            while (true) {
                try {
                    Socket acceptedSocket = serverSocket.accept();
                    new Thread(() -> server.getConsumer().accept(acceptedSocket)).start();
                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage());
        }

    }
}