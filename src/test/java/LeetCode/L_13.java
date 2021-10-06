package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 18190
 * @Date: 2021/10/6  19:55
 * @VERSION 1.0
 */
public class L_13 {
    static Map<Character,Integer> characterIntegerMap = new HashMap<>();
    static {
        characterIntegerMap.put('I',1);
        characterIntegerMap.put('V',5);
        characterIntegerMap.put('X',10);
        characterIntegerMap.put('L',50);
        characterIntegerMap.put('C',100);
        characterIntegerMap.put('D',500);
        characterIntegerMap.put('M',1000);
    }

    public int romanToInt(String s) {
        int i = 0;
        int l = s.length()-1;

        int ans = 0,a = 0,b;
        while (i <s.length()){
            if (i<l &&  (a = characterIntegerMap.get(s.charAt(i))) < ( b = characterIntegerMap.get(s.charAt(i+1)))){
                ans += b-a;
                i+=2;
            }else {
                if (i == l){
                    a =  characterIntegerMap.get(s.charAt(i));
                }
                ans+=a;

                i++;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.println(new L_13().romanToInt("DCXXI"));
        System.out.println(new L_13().romanToInt("LVIII"));
        System.out.println(new L_13().romanToInt("MCMXCIV"));
    }
}
