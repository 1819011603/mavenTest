package Web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HTTPClient {
    public static void main(String[] args) throws IOException {
        String url = "/uploadFile.html";

        doGet("127.0.0.1",8080,url);
    }
    public static void doGet(String host, int port, String url) throws IOException {
        Socket socket = null;


        try {
            socket = new Socket(host,port);
            StringBuffer req = new StringBuffer("GET " + url + " HTTP/1.1\r\n");
            req.append("Accept: */*\r\n");
            req.append("Accept-language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\r\n");
            req.append("accept-encoding: gzip, deflate, br");
            req.append("User-Agent: HTTPClient\r\n");
            req.append("Host: localhost:8080\r\n");
            req.append("Connection: Keep-Alive\r\n\r\n");
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(req.toString().getBytes(StandardCharsets.UTF_8));
            Thread.sleep(2000);
            InputStream in = socket.getInputStream();
            byte[] bytes = new byte[in.available()];
            int c = in.read(bytes);
            System.out.println(new String(bytes));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }
}
