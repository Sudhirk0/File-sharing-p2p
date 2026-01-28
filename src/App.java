public class App {
    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Usage: java App <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        PeerServer server = new PeerServer(port);
        server.start();

        PeerClient client = new PeerClient(server);
        client.start();
    }
}
