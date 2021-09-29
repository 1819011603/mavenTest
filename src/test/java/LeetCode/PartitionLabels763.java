package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/6/18  19:09
 * @VERSION 1.0
 */
public class PartitionLabels763 {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(new PartitionLabels763().partitionLabels(s));
    }
    public List<Integer> partitionLabels(String s) {
        int[] location = new int[26];


        for (int i  = 0; i < s.length(); i++){
            location[s.charAt(i)-'a'] = i;
        }

        int end = location[s.charAt(0)-'a'];
        List<Integer> list = new ArrayList<>();
        int cur_end;
        int start = 0;
        for (int i = 1; i < s.length(); i++){
            cur_end = location[s.charAt(i) - 'a'];
            if(i > end){
                list.add(i-start);
                end = cur_end;
                start = i;
            }
            if(cur_end > end){
                if(cur_end == s.length()-1){
                    list.add(s.length()-start);
                    return list;
                }
                end = cur_end;
            }
        }
        list.add(s.length()-start);
        return list;
    }

    public List<Integer> partitionLabels1(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}
