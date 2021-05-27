package Web;

import java.io.File;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HelloServlet implements Servlet {
    @Override
    public void init() throws Exception {
        System.out.println("HTTPServlet is init");
    }
    public String getUrl(String req){
        int index1,index2;
        String url;
        index1 = req.indexOf(" ");
        if(index1 != -1){
            index2 = req.indexOf(" ",index1+1);
            if(index2 > index1){
                url = req.substring(index1+1,index2);
                return url;
            }
        }
        return null;
    }
    @Override
    public void service(byte[] requestBuffer, OutputStream out) throws Exception {
        String request = new String(requestBuffer);
        if(request.length() < 10) return;
        System.out.println("hello");
        System.out.println(request);
        String method = request.substring(0,request.indexOf(" "));
        String url = getUrl(request);
        String username = null;
        if(method.equalsIgnoreCase("get" ) && url.contains("username")){
            username = url.split("username=")[1];
            int l = username.indexOf("&");
            if(l != -1) username = username.substring(0,l);

        }
        if(method.equalsIgnoreCase("post")){
//            System.out.println("\n\n hhhh\n");
//            for (String s: request.split("\r\n")){
//                System.out.println(s +' ' + request.split("\r\n").length + " " + request.split("\r\n\r\n").length);
//            }
//            System.out.println("ssss " + request.split("\r\n\r\n")[1]);
            username = request.split("\r\n\r\n")[1];
            username = username.substring(username.indexOf("username=")+9, !username.contains("&") ?username.length():username.indexOf("&"));


        }
        out.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
        out.write("Content-Type:text/html\r\n\r\n".getBytes(StandardCharsets.UTF_8));
        String content = "<html>\n" +
                "<head>\n" +
                "    <title>index.html</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <p>username: "+username +  "</p>\n" +

                "</body>\n" +
                "\n" +
                "</html>";
        out.write(content.getBytes(StandardCharsets.UTF_8));
    }
}
