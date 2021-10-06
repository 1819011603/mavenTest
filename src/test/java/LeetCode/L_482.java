package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/4  8:56
 * @VERSION 1.0
 */
public class L_482 {
    public String licenseKeyFormatting(String s, int k) {
        int j = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--){
            if (s.charAt(i) != '-'){
                if (s.charAt(i) >= 'a' &&s.charAt(i) <= 'z')
                ans.append((char) (s.charAt(i)-32));
                else ans.append(s.charAt(i));
                if (++j == k){
                    ans.append('-');
                    j = 0;
                }
            }
        }
        if (ans.length() > 0 && ans.charAt(ans.length()-1) == '-'){
            ans.deleteCharAt(ans.length()-1);
        }
        return ans.reverse().toString();

    }
    public static void main(String[] args) {
        System.out.println(new L_482().licenseKeyFormatting("5F3Z-2e-9-w",4));
        System.out.println(new L_482().licenseKeyFormatting("2-5g-3-J",2));
        System.out.println(new L_482().licenseKeyFormatting("2-5g-3-JazAZ",2));
        System.out.println(new L_482().licenseKeyFormatting("2-5g-3-JazAZ",3));
        System.out.println(new L_482().licenseKeyFormatting("--a-",3));
    }
}
