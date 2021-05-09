package Thread.Web;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    private InputStream input;
    private String url;

    public Request(InputStream input) {
        this.input = input;
    }
    public void parse(){
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] bytes = new byte[2048];
        try {
            i = input.read(bytes);
        }catch (IOException e){
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++){
            request.append((char) bytes[j]);
        }
        // 请求的数据
        System.out.println(request.toString());
        url = parseUrl(request.toString());
    }
    public String  parseUrl(String request){
        int index1, index2;
        index1 = request.indexOf(' ');
        if (index1 != -1){
            index2 = request.indexOf(' ',index1+1);
            if(index1 < index2){
                return request.substring(index1+1,index2);
            }
        }
        return null;
    }

    public InputStream getInput() {
        return input;
    }

    public String getUrl() {
        return url;
    }
}
