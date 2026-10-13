package serverPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1_2 {
    public static void main(String[] args)  throws IOException {
        System.out.println("Je suis un serveur en attente de la connexion d'un client");
        try (ServerSocket serverSocket = new ServerSocket(1234);
             Socket socket = serverSocket.accept();
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("Un client est connecté");
            int x;
            while ((x = in.readInt()) != 0) {
                int result = x * 5;
                System.out.println("Reçu : " + x + " -> envoyé : " + result);
                out.writeInt(result);
                out.flush();
            }
            System.out.println("Le client a envoyé 0, fermeture de la connexion.");
        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
