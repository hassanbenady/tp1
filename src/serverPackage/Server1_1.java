package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1_1 {
    public static void main(String[] args) throws IOException{
        ServerSocket socketServeur = new ServerSocket(1234);
        System.out.println("SERVER WAITING FOR CLIENT");
        Socket socket = socketServeur.accept();
        System.out.println("CLIENT CONNECTED");
    }
}
