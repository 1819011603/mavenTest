package Thread.Web;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    PrintWriter output;

    public Response(OutputStream output) {
        this.output = new PrintWriter(output) ;
    }
    public void setRequest(Request request){
        //   request.getUrl().substring(1) 主要是为了路径名才需要这个request
        this.request = request;
    }
    public void sendStaticResource() throws IOException {
        char[] bytes = new char[BUFFER_SIZE];
        BufferedReader file = null;

        try {
            File file1 = new File(HttpServer.WEB_ROOT, request.getUrl().substring(1));
            System.out.println(file1.exists());
            if(file1.exists()){
                file = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));

                int ch = file.read(bytes,0,BUFFER_SIZE);
                String errorMessage = "Http/1.1 200 OK\r\n" +
                        "Content-Type:text/html;charset=utf-8 \r\n" +

                        "\r\n" +
                        "<p>" ;
                output.write(errorMessage);
                while (ch!=-1){
                    output.write(bytes,0,ch);
                    ch = file.read();
                }
                // 这个p标签不能少
                output.write("</p>");
                file.close();
            }else {
                String errorMessage = "Http/1.1 404 File Not Found \r\n" +
                        "Content-Type:text/html;charset=utf-8 \r\n" +
                        "Content-Length:23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found </h1>";
                output.write(errorMessage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(output != null){
                output.close();
            }
        }
    }
}
