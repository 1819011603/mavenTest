package Web;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HTTPServer1 {
    private static Map<String,Servlet> servletCache= new HashMap<>();
    private boolean shutdown = false;
    private final int size = 4096;
    private static String url = "";
    private static final String PATH = System.getProperty("user.dir") + File.separator+ "src\\resource\\index.html";
    private static String path;


    public static void main(String[] args) throws IOException {
        HTTPServer1 server = new HTTPServer1();
        server.await();
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
            return;
        }
        path = PATH;
    }
    public void service(Socket socket) throws Exception {
        System.out.println("service");
        InputStream input = socket.getInputStream();
        Thread.sleep(100);
        System.out.println("input.available(): " + input.available());
        byte[] bytes = new byte[input.available()];
        StringBuilder builder = new StringBuilder();
        int c;
        try {
            System.out.println("input.available(): " + input.available());
            c = input.read(bytes);
        }catch (IOException e){
            e.printStackTrace();
            c = 0;
        }
        while(c>0){
            System.out.println(c);
            for (int i = 0; i < c ;i++){
                builder.append((char) bytes[i]);
            }
            if(input.available() > 0)
            c = input.read(bytes);
            else break;
        }






        String request = builder.toString();

        getUrl(request);
        if(url.equals("/favicon.ico"))return;
        System.out.println(url);
        if(!url.contains("servlet") ){

            System.out.println(path);

            FileInputStream fileInputStream = new FileInputStream(path);

            OutputStream output  = socket.getOutputStream();
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
            byte[] chars =  new byte[2048];
            c = fileInputStream.read(chars);
            while (c!=-1){
                output.write(chars,0,c);
                c = fileInputStream.read(chars);
            }

            return;
        }

        String servletName;

        if(url.contains("?")){
            servletName = url.substring(url.indexOf("servlet/") + 8,url.indexOf("?"));
        }else servletName = url.substring(url.indexOf("servlet/")+8);
        Servlet servlet = servletCache.get(servletName);
        if(servlet == null){
            try {

                servlet = (Servlet)Class.forName("Web." + servletName).getDeclaredConstructor().newInstance();
                servlet.init();
                servletCache.put(servletName,servlet);
            } catch (Exception e){
                e.printStackTrace();
            }


        }
        servlet.service(request.getBytes(StandardCharsets.UTF_8),socket.getOutputStream());



    }

    public void await() throws IOException {
        ServerSocket server = null;

        server = new ServerSocket(8080,1, InetAddress.getByName("127.0.0.1"));
        InputStream input = null;
        OutputStream output = null;
        Socket socket = null;
        while (!shutdown){
            try {
                Thread.sleep(500);
                socket = server.accept();
                System.out.println("建立一个TCP连接，该客户地址为： "+ socket.getInetAddress() + ":" + socket.getPort());
                service(socket);
                shutdown = url.equalsIgnoreCase("/shutdown");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                socket.close();
            }



        }
    }

}
