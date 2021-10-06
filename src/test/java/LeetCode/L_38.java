package LeetCode;

import com.sun.xml.internal.ws.server.ServerRtException;

/**
 * @author 18190
 * @Date: 2021/10/4  21:49
 * @VERSION 1.0
 */
public class L_38 {

    public String countAndSay(int n) {
        if (n == 1)return "1";

        StringBuilder ans = new StringBuilder("1");
        StringBuilder temp = null;
        int time = 1;
        for (int i = 2; i <=n ;i++){
            ans.append(" ");
            temp = new StringBuilder();
            for (int j = 1; j < ans.length(); j++){
                if (ans.charAt(j) == ans.charAt(j-1)){
                    time++;
                }else {

                    temp.append(time);
                    temp.append(ans.charAt(j-1));
                    time = 1;

                }
            }

            ans = temp;
            time = 1;
        }

        return ans.toString();
    }
    public static void main(String[] args) {
        System.out.println(new L_38().countAndSay(6));
    }
}
