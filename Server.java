import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


import java.util.Date;
import java.util.Scanner;

import javax.swing.text.Document;

public class server{

    private static Scanner sc = new Scanner(System.in);

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException{

        ObjectCreator objectCreator = new ObjectCreator();
        Serializer serializer = new Serializer();
        
        while (true) {
            String userinput = sc.nextLine();
            if (userinput == "Exit"){
                break;
            }
            else {
            //establish connection
            ServerSocket listener = new ServerSocket(PORT);
            System.out.println("Server side: Waiting for connection...");
            Socket client = listener.accept();
            System.out.println("Server side: Connnection established!");

            Socket socket = new Socket(127.0.0.1, 8080);

            Object objtoSer = ObjectCreator.main(userinput);
            Document doc = serializer.serialize(objtoSer);

            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
            System.out.println(new String(bytes, 0, len));

            //close connection
            listener.close();
            System.out.println("Server side: Connection closed.");

            socket.close();
            }

        }
    }



}