package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Collections;

public class Server1_3 {
    static final int PORT = 1234;

    // Finds the machine's real LAN IPv4 (getLocalHost() often returns 127.0.1.1 on Linux)
    static InetAddress findLocalIp() throws SocketException {
        for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) continue;
            for (InetAddress a : Collections.list(ni.getInetAddresses())) {
                if (a instanceof Inet4Address && a.isSiteLocalAddress()) return a;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            // Optional: pass the IP as an argument, otherwise it is detected automatically
            InetAddress ip = (args.length > 0) ? InetAddress.getByName(args[0]) : findLocalIp();
            if (ip == null) {
                System.out.println("Aucune adresse IP réseau trouvée. Passez l'IP en argument.");
                return;
            }

            try (ServerSocket serverSocket = new ServerSocket()) {
                serverSocket.bind(new InetSocketAddress(ip, PORT));
                System.out.println("Serveur en attente sur " + ip.getHostAddress() + ":" + PORT);

                try (Socket socket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    System.out.println("Client connecté : " + socket.getInetAddress().getHostAddress());

                    int choice;
                    while ((choice = in.readInt()) != 0) {
                        int x = in.readInt();
                        double result;
                        switch (choice) {
                            case 1: result = x + 5; break;
                            case 2: result = x - 5; break;
                            case 3: result = x * 5; break;
                            case 4: result = x / 5.0; break;
                            default: result = Double.NaN;
                        }
                        System.out.println("Choix=" + choice + ", x=" + x + " -> " + result);
                        out.writeDouble(result);
                        out.flush();
                    }
                    System.out.println("Le client a quitté, fermeture de la connexion.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}