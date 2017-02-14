import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Сабина on 14.02.2017.
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 2020);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("Hello world");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
