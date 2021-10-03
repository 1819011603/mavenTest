package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/9/29  18:18
 * @VERSION 1.0
 */
public class L_17 {
    List<String> ans = new ArrayList<>();
    StringBuilder cur = new StringBuilder();
    String[] strings = new String[]{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)return ans;
        dfs(digits,0);
        return ans;
    }
    public void  dfs(String digits,int l){
        if (l == digits.length()){
            ans.add(cur.toString());
            return;
        }

        String c = strings[digits.charAt(l)-'2'];
        for (int i = 0; i < c.length(); i++){
            cur.append(c.charAt(i));
            dfs(digits,l+1);
            cur.deleteCharAt(cur.length()-1);
        }
    }


    public static void main(String[] args) {

        System.out.println(new L_17().letterCombinations("23"));
        System.out.println(new L_17().letterCombinations("23223232"));
        System.out.println(new L_17().letterCombinations(""));
    }
}
