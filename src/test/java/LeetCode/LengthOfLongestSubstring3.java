package LeetCode;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 18190
 * @Date: 2021/9/23  11:33
 * @VERSION 1.0
 */
public class LengthOfLongestSubstring3 {
    public int lengthOfLongestSubstring(String s){

        if(s.length() <= 1)return s.length();
        char[] chars = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        int l = -1,ans = 0;
        Integer temp;
        for (int i = 0; i < chars.length; i++){
            if((temp = map.get(chars[i]))!=null){
                if(temp < l){
                ans = Math.max(ans, i - l);
                }else {
                    ans = Math.max(ans,i-temp);
                    l = temp;
                }
            }else {
                    ans = Math.max(ans, i - l);

            }
            map.put(chars[i],i);
        }
        return ans;


    }


//    aaaa
//    abb
//    abab

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring3().lengthOfLongestSubstring("aaa"));
        System.out.println(new LengthOfLongestSubstring3().lengthOfLongestSubstring("abb"));
        System.out.println(new LengthOfLongestSubstring3().lengthOfLongestSubstring("bba"));
        System.out.println(new LengthOfLongestSubstring3().lengthOfLongestSubstring("tmmzuxt"));
    }

}
