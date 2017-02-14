import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Сабина on 14.02.2017.
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2020);
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String message = dataInputStream.readUTF();
            System.out.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
