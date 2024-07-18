import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

public class main {
 
   public static void main(String[] args) throws IOException, URISyntaxException {
       ServerSocket serverSocket = new ServerSocket(8080);

       while(true) {
           listenToClientConnections(serverSocket);
       }
   }

   private static void listenToClientConnections(ServerSocket serverSocket) throws IOException {
       Socket clientSocket = serverSocket.accept();

       BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

       String requestedResource = "";
       String incomingLineFromClient;
       while ((incomingLineFromClient = in.readLine()) != null) {
           System.out.println(incomingLineFromClient);

           if(incomingLineFromClient.contains("HTTP/1.1")) {
               requestedResource = incomingLineFromClient;
           }

           if (incomingLineFromClient.equals(""))
               break;
       }

       PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
       
       String response = "Hello World ";
    //    String response = "Hello World " + requestedResource;

       out.print("HTTP/1.1 200 OK\n");
       out.print("Content-Length: " + response.length() + "\n");
       out.print("Content-Type: text/html; charset=utf-8\n");
       out.print("Date: Tue, 25 Oct 2016 08:17:59 GMT\n");
       out.print("\n");
       out.print(response);
       out.flush();
   }
}