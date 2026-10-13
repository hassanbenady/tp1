package clientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Clinet1_2 {
    public static void main(String[] args)  throws IOException {
        System.out.println("Je suis un client pas encore connecté...");
        try (Socket socket = new Socket("localhost", 1234);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner sc = new Scanner(System.in)) {
            System.out.println("Je suis un client connecté");
            int x;
            do {
                System.out.print("Entrez un entier (0 pour quitter) : ");
                x = sc.nextInt();
                out.writeInt(x);   // send x to the server
                out.flush();
                if (x != 0) {
                    int result = in.readInt();   // receive x * 5
                    System.out.println("Résultat (x * 5) = " + result);
                }
            } while (x != 0);
            System.out.println("Communication terminée, socket fermé.");
        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
