import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Session  implements Runnable {
    private Socket socket;
    private Server server;

    public Session(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String message;

            System.out.println("Client " + "(" + Thread.currentThread().getName() + ") connected");

            do {
                message = dis.readUTF();
                System.out.println("Received from client (" + Thread.currentThread().getName() + ") : " + message);
                dos.writeUTF("Server response: " + message);
            } while (!"exit".equalsIgnoreCase(message));

            socket.close();
            server.closeSession();

            System.out.println("Client ("+ Thread.currentThread().getName() + ") disconnected");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
