import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int maxCount = 5;
    public static volatile int currentCount = 0;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Not enough arguments");
            return;
        }

        int port = Integer.parseInt(args[0]);
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running\n");

            while (true) {
                Socket socket = serverSocket.accept();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                if (currentCount < maxCount){
                    currentCount++;
                    dos.writeUTF("Access is allowed");

                    Thread thread = new Thread(new Session(socket));
                    thread.start();
                }
                else {
                    dos.writeUTF("Access not allowed");
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

 /*serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new Session(socket));
                thread.start();
            }*/


            /*DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String message;

             do {
                 message = dis.readUTF();
                 System.out.println("Received from client: " + message);
                dos.writeUTF("Server response: " + message);
             } while (!"exit".equalsIgnoreCase(message));*/
