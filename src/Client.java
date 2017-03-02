import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Not enough arguments");
            return;
        }

        InetAddress address = null;

        try {
            address = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        int port = Integer.parseInt(args[1]);
        Socket socket;
        try {
            socket = new Socket(address, port);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String message;

            System.out.println(dis.readUTF());

            do {
                message = bufferedReader.readLine();
                dos.writeUTF(message);
                System.out.println(dis.readUTF());
            } while (!"exit".equalsIgnoreCase(message));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
