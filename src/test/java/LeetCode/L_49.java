package LeetCode;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/10/6  17:35
 * @VERSION 1.0
 */
public class L_49 {
    int[] ints = new int[26];
    public int getHash(String o1){
        Arrays.fill(ints,0);
        for (int i = o1.length()-1; i >= 0; i--){
            ints[o1.charAt(i)-'a']++;
        }
        return Arrays.hashCode(ints);
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer,List<String>> hashMap = new HashMap<>();
       for (String s: strs){
           int t = getHash(s);
           System.out.println(t);
           if (hashMap.containsKey(t)){
               List<String> fault = hashMap.get(t);
               fault.add(s);
           }else {
               ArrayList<String> list = new ArrayList<>();
               list.add(s);
               hashMap.put(t,list);
           }

       }
        return new ArrayList<>(hashMap.values());
    }




    public static void main(String[] args) {
        System.out.println(new L_49().groupAnagrams(new String[]{
                "eat", "tea", "tan", "ate", "nat", "bat"
        }));
    }
}
