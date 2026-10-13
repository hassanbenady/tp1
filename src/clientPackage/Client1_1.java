package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client1_1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("im connected");
    }
}
