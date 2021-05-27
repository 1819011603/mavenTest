package Web;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class HTTPServer {
    private boolean shutdown = false;
    private final int size = 2048;
    private String url = null;
    private static String path = System.getProperty("user.dir") + File.separator + "src\\resource\\index.html";
    public static void main(String[] args)throws IOException {
        HTTPServer server = new HTTPServer();
        server.await();
    }

    public String getRequest(InputStream input)  {

        byte[] bytes = new byte[size];
        int c;
        try {
            System.out.println("input.available(): " + input.available());
            c = input.read(bytes);
        }catch (IOException e){
            e.printStackTrace();
            c = -1;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < c ;i++){
            builder.append((char) bytes[i]);
        }

        return builder.toString();
    }
    public void getUrl(String req){
        int index1,index2;
        index1 = req.indexOf(" ");
        if(index1 != -1){
            index2 = req.indexOf(" ",index1+1);
            if(index2 > index1){
                url = req.substring(index1+1,index2);
                if(!url.equals("/"))
                path = System.getProperty("user.dir") + File.separator + "src\\resource\\" + url.substring(1);
                else {
                    path = System.getProperty("user.dir") + File.separator + "src\\resource\\index.html";
                    url = "/index.html";
                }
            }
        }
    }

    public void await() throws IOException {
        ServerSocket server = null;

        server = new ServerSocket(8080,1, InetAddress.getByName("127.0.0.1"));
        InputStream input = null;
        OutputStream output = null;
        Socket socket = null;
        while (!shutdown){
            try {

                socket = server.accept();
                input = socket.getInputStream();

                output = socket.getOutputStream();
                String request = getRequest(input);
                getUrl(request);
                System.out.println(request);
                input = new FileInputStream(path);
                System.out.println(path);
                String contentType;
                if(url.contains("htm"))contentType = "text/html";
                else if(url.contains("jpg") || url.contains("jpeg") || url.contains("png"))
                    contentType = "image/jpeg";
                else if(url.contains("gif")){
                    contentType = "image/gif";
                }else contentType = "application/octet-stream";
                String Message = "Http/1.1 200 OK\r\n" +
                        "Content-Type:" + contentType + ";charset=utf-8 \r\n" +
                        "\r\n";
                output.write(Message.getBytes(StandardCharsets.UTF_8));
                byte[] bytes = new byte[2048];
                int c = input.read(bytes);
                while (c!=-1){
                    output.write(bytes,0,c);
                    c = input.read(bytes);
                }
                shutdown = url.equalsIgnoreCase("/shutdown");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                socket.close();
            }



        }
    }
}
