import java.io.*;
import java.net.*;

public class PeerServer extends Thread {
    private volatile boolean running = true;
    private int port;

    public PeerServer(int port) {
        this.port = port;
    }


    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Peer running on port " + port);

            while (running) {
                Socket socket = serverSocket.accept();
                handleClient(socket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopServer() {
        running = false;
    }


    private void handleClient(Socket socket) throws Exception {

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        String command = dis.readUTF();

        if (command.equals("LIST")) {
            dos.writeUTF(String.join(",", FileManager.listFiles()));
        }

        else if (command.startsWith("GET")) {
            String filename = command.split(" ")[1];
            sendFile(filename, dos);
        }

        socket.close();
    }

    private void sendFile(String filename, DataOutputStream dos) throws Exception {

        File file = FileManager.getFile(filename);

        if (!file.exists()) {
            dos.writeUTF("ERROR");
            return;
        }

        dos.writeUTF("FILE");
        dos.writeUTF(file.getName());
        dos.writeLong(file.length());

        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int read;

        while ((read = fis.read(buffer)) != -1) {
            dos.write(buffer, 0, read);
        }

        fis.close();
    }
}
