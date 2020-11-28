import java.io.BufferedReader;
import java.net.Socket;

import javax.swing.JOptionPane;
import java.io.InputStreamReader;

public class client {
    

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    
	public static void main(String[] args) {

        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        
        BufferedReader input = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        Stirng serverRenponser = input.readLine();

        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(socket.getInputStream());

        Deserializer deserializer = new Deserializer();
        Object object = deserializer.deserialize(document);

        Visualizer visualizer = new Visualizer();
        visualizer.visualize(object, true);

        socket.close();
        System.exit(0);
    }

}
