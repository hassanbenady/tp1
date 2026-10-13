package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client1_3 {
    static final int PORT = 1234;

    public static void main(String[] args) {
        // Pass the server IP as an argument, or edit this default
        String serverIp = (args.length > 0) ? args[0] : "192.168.1.10";

        try (Socket socket = new Socket();
             Scanner sc = new Scanner(System.in)) {

            socket.connect(new InetSocketAddress(serverIp, PORT), 5000);
            System.out.println("Connecté au serveur " + serverIp + ":" + PORT);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            while (true) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Addition (x + 5)");
                System.out.println("2. Soustraction (x - 5)");
                System.out.println("3. Multiplication (x * 5)");
                System.out.println("4. Division (x / 5)");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                int choice = sc.nextInt();

                if (choice == 0) {
                    out.writeInt(0);
                    out.flush();
                    break;
                }
                if (choice < 1 || choice > 4) {
                    System.out.println("Choix invalide.");
                    continue;
                }

                System.out.print("Entrez la valeur de x : ");
                int x = sc.nextInt();

                out.writeInt(choice);
                out.writeInt(x);
                out.flush();

                double result = in.readDouble();
                System.out.println("Résultat = " + result);
            }
            System.out.println("Communication terminée, socket fermé.");

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}