package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/6  19:44
 * @VERSION 1.0
 */
public class L_14 {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder ans = new StringBuilder();
        try {
            int i = 0;
            char c;
            while (i < 200){
                c = strs[0].charAt(i);
                for (int j = strs.length-1; j>0; j--){
                    if (c!= strs[j].charAt(i))return ans.toString();
                }
                ans.append(c);
                i++;
            }
            return ans.toString();
        }catch (Exception e){
            return ans.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new L_14().longestCommonPrefix(new String[]{
                "flower","flow","flight"
        }));
    }
}
