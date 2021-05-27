package Web;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class uploadForm implements Servlet{
    @Override
    public void init() throws Exception {
        System.out.println("uploadForm init");
    }

    @Override
    public void service(byte[] requestBuffer, OutputStream out) throws Exception {
        String request = new String(requestBuffer);
        System.out.println(request);
        String header =  request.substring(request.indexOf("\r\n")+2,request.indexOf("\r\n\r\n"));
        BufferedReader reader = new BufferedReader(new StringReader(header));
        String boundary = null,data = null;
        while ((data = reader.readLine()) != null){
            if(data.contains("Content-Type")){
                boundary = "--" + data.substring(data.indexOf("boundary=")+9);
                break;
            }
        }
        if(boundary == null){
            out.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
            out.write("Content-Type:text/html\r\n\r\n".getBytes(StandardCharsets.UTF_8));
            out.write("upload is Failed".getBytes(StandardCharsets.UTF_8));
            return;
        }
        int index3 = request.lastIndexOf(boundary);
        int index2 = request.lastIndexOf(boundary,index3-1);
        int index1 = request.lastIndexOf(boundary,index2-1);
        String massage = request.substring(index1+boundary.length(),index2);
        String from = massage.substring(massage.indexOf("filename=")+10,massage.indexOf("\r\n",massage.indexOf("filename")));
        from = from.substring(0,from.length()-1);
        String main = massage.split("\r\n\r\n")[1];
        main = main.substring(0,main.length()-2);
        int mainLength = main.length();
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + File.separator + "src\\resource\\download\\"+from) ;
        if(from.contains("jpg") || from.contains("png") || from.contains("jpeg")){
            byte[] result = main.getBytes(StandardCharsets.UTF_8);
            BASE64Encoder encoder = new BASE64Encoder();
            String str = encoder.encode(result).trim();
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] imgbyte = decoder.decodeBuffer(str);
            fileOutputStream.write(imgbyte, 0, imgbyte.length);
            fileOutputStream.flush();


        }
        else {
            fileOutputStream.write(main.getBytes(StandardCharsets.UTF_8));
        }
        fileOutputStream.close();
        out.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
        out.write("Content-Type:text/html\r\n\r\n".getBytes(StandardCharsets.UTF_8));
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>uploadSucess</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Uploading is finished.</h1>\n" +
                "<h1>FileName:"+from +"</h1>\n" +
                "<h1>FileSize:"+mainLength+ "</h1>\n" +
                "</body>\n" +
                "</html>";
        out.write(content.getBytes(StandardCharsets.UTF_8));
    }
}
