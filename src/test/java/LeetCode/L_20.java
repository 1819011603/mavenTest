package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 18190
 * @Date: 2021/10/6  19:32
 * @VERSION 1.0
 */
public class L_20 {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        int l = s.length();
        char c;
        for (int i = 0; i < l; i++){
            if (chars[i] == ')' || chars[i]==']' || chars[i] == '}'){
                if (stack.isEmpty() || stack.pollLast() != chars[i])return false;
            }else {
                if (chars[i] == '('){
                    c = ')';
                }else if(chars[i] == '['){
                    c = ']';
                }else c = '}';
                stack.addLast(c);
            }
        }
        return stack.isEmpty();
    }
}
