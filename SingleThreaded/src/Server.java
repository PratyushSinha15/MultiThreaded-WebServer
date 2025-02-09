import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8080;
        //ServerSocket is a class that implements server sockets. A server socket waits for requests to come in over the network.
        ServerSocket socket= new ServerSocket(port);
        //set the timeout for the server socket
        socket.setSoTimeout(1000);
        //infinite loop to keep the server running
        while(true){
            try{
                System.out.println("Server is running on port " + port);
                //this is a blocking call that waits for a connection to be made to this socket and then returns a new socket object representing the connection
                Socket acceptedConnection= socket.accept();
                //print the remote socket address WHICH IS THE CLIENT'S IP ADDRESS
                System.out.println("Connection accepted from " + acceptedConnection.getRemoteSocketAddress());
                // printWriter is a class that writes text to a character-output stream, buffering characters so as to
                // provide for the efficient writing of single characters, arrays, and strings.
                //getoutputstream returns an output stream for this socket output stream means data is sent from the server to the client
                PrintWriter toClient= new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient= new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println( "Hello, you are connected to the server");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args){
        Server server= new Server();
        try{
            server.run();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}