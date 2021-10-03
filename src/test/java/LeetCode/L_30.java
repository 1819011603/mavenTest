package LeetCode;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 18190
 * @Date: 2021/10/2  16:42
 * @VERSION 1.0
 */
public class L_30 {


    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int num = words.length;
        int len = words[0].length();
        int length = num*len;
        int last = s.length()-length;
        if(s.length() < length) return ans;

        Map<String, Integer> map = new HashMap<>();
        for(String word : words)
            map.putIfAbsent(word, map.size());
        int[] count = new int[map.size()];
        for(String word : words)
            count[map.get(word)]++;
        int[] curcount = new int[count.length];

        //  i  [0,子串的长度-1]  希尔排序思想
        for(int i=0, start; (start = i) < len; i++){

            // start [0,s.length()-子串长度*子串数)
            // end  [子串长度*子串数, s.length() )
            while  (start<=last){

                int curnum = 0;
                Arrays.fill(curcount,0);
                for(int end = start+length; end>start; end-=len) {
                    // [end-len,end) 子串
                    String check = s.substring(end-len, end);
                    Integer which = map.get(check);

                    // 没有  从当前end位置开始遍历
                    if(which == null){
                        start = end;
                        break;
                    }
                    // 个数超了 从当前end位置开始遍历
                    if(++curcount[which] > count[which]){
                        start = end;
                        break;
                    }
                    // 刚刚好
                    if(++curnum == num){
                        ans.add(start);
                        start = end;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L_30().findSubstring("fbfbfbfb",new String[]{"f","b"}));
        System.out.println(new L_30().findSubstring("barfoothefoobarman",new String[]{"bar","foo"}));
        System.out.println(new L_30().findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"}));
        System.out.println(new L_30().findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake",new String[]{"fooo","barr","wing","ding","wing"}));
        System.out.println(new L_30().findSubstring("ababab",new String[]{"aba","bab"}));
    }
}
