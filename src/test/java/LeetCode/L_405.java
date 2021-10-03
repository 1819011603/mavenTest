package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/2  16:09
 * @VERSION 1.0
 */
public class L_405 {
    public String toHex(int num) {
        if(num == 0)return "0";
        StringBuilder ans = new StringBuilder();
        int temp;
        while(num!=0){
            temp = num & 15;
            if (temp < 10){
                ans.append(temp);
            }else {
                ans.append((char) (87 + temp));
            }
            num >>>= 4;

        }

        return ans.reverse().toString();

    }

    public static void main(String[] args) {
        System.out.println(new L_405().toHex(-1));
        System.out.println(new L_405().toHex(165));
        System.out.println(new L_405().toHex(26));
    }
}
