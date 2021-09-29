package LeetCode;

/**
 * @author 18190
 * @Date: 2021/9/24  21:03
 * @VERSION 1.0
 */
public class Convert6 {
    public String convert(String s,int numRows){
        if(numRows == 1 || numRows >= s.length()){
            return s;
        }
        int len = s.length();
        char[] chars = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        int u = 1;
        int i = 0,t = 0,temp = 2 * numRows-2;
        while (i < numRows ){
            if(i == 0 || i == numRows-1){
                ans.append(chars[t]);
                t += temp;
                if (t >= len ){
                    i++;
                    u = i;
                    t = i;
                }
            }else {
                u = (numRows-1) - u;
                ans.append(chars[t ]);
                t += u * 2;
                if (t >= len){
                    i++;
                    t=i;
                    u = i;
                }
            }

        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Convert6().convert("PAYPALISHIRING",4));
    }
}
