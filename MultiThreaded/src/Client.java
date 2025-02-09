import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();

        for (int i = 0; i < 100; i++) {
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
                thread.join(); // Ensures orderly execution
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                System.err.println("Thread interrupted: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error creating thread: " + e.getMessage());
            }
        }
    }

    private Runnable getRunnable() {
        return () -> {
            int port = 8080;
            try (Socket socket = new Socket(InetAddress.getByName("localhost"), port);
                 PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader fromSocket = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()))) {

                System.out.println("Client started: " + Thread.currentThread().getName());

                toSocket.println("Hello, I am a client");
                String line = fromSocket.readLine();
                System.out.println("Server says: " + line);

            } catch (Exception e) {
                System.err.println("Error in client thread: " + e.getMessage());
            }
        };
    }
}
