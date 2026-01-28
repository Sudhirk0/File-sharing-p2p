import java.io.*;
import java.net.*;
import java.util.Scanner;


public class PeerClient {
    private PeerServer server;
    public PeerClient(PeerServer server){
        this.server = server;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Command (LIST / GET file / EXIT): ");
                String command = sc.nextLine();


                if (command.equalsIgnoreCase("EXIT")) {
                    System.out.println("Stopping peer...");
                    server.stopServer();
                    System.exit(0);
                }

                System.out.print("Enter peer port: ");
                int port = Integer.parseInt(sc.nextLine());

                Socket socket = new Socket("localhost", port);

                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                dos.writeUTF(command);

                String response = dis.readUTF();

                if (response.equals("FILE")) {
                    receiveFile(dis);
                } else {
                    System.out.println("Response: " + response);
                }
                socket.close();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void receiveFile(DataInputStream dis) throws Exception {

        String filename = dis.readUTF();
        long size = dis.readLong();

        FileOutputStream fos = new FileOutputStream("downloads/" + filename);

        byte[] buffer = new byte[4096];
        long remaining = size;

        while (remaining > 0) {
            int read = dis.read(buffer, 0,
                    (int) Math.min(buffer.length, remaining));
            if (read == -1) break;

            fos.write(buffer, 0, read);
            remaining -= read;
        }

        fos.close();
        System.out.println("Downloaded Successfully: " + filename);
    }
}
