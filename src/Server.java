import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Not enough arguments");
            return;
        }

        int port = Integer.parseInt(args[0]);
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String message;

             do {
                 message = dis.readUTF();
                 System.out.println("Received from client: " + message);
                 //¬еро€тно, здесь будет выполнение запроса клиента
                 //ѕока что - просто пересылка сообщений
                dos.writeUTF("Server response: " + message);
             } while (!"exit".equalsIgnoreCase(message));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
