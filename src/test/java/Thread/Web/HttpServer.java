package Thread.Web;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//  http://localhost:8080/aaaa
public class HttpServer {
    private static final String SHUTDOWN_COMMAND="/shutdown";
    private static boolean shutdown = false;
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "src\\test\\java\\Thread\\Web"+File.separator +"webRoot";
    public static void main(String[] args) throws IOException {
        shutdown = false;
        HttpServer server = new HttpServer();
        server.await();
    }
    public void await() throws IOException {
        ServerSocket server = new ServerSocket(8080,1, InetAddress.getByName("127.0.0.1"));
        Socket socket = null;
        InputStream input = null;
        OutputStream output = null;
        try {
            while (!shutdown){
            socket = server.accept();
            input = socket.getInputStream();
            output = socket.getOutputStream();
            Request request = new Request(input);
            request.parse();
            Response response = new Response(output);
            response.setRequest(request);
            response.sendStaticResource();
            socket.close();
            shutdown = request.getUrl().equalsIgnoreCase(SHUTDOWN_COMMAND);
         }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            server.close();
            assert socket != null;
            socket.close();
            assert input != null;
            input.close();
            assert output != null;
            output.close();
        }

    }
}
