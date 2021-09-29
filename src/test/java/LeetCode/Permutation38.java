package LeetCode;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/6/22  15:26
 * @VERSION 1.0
 */
public class Permutation38 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Permutation38().permutation("abc")));
    }
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Set<String> strings = new HashSet<>();
        Map<Character,Integer> map = new HashMap<>();
        for (char c : chars){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        StringBuilder builder = new StringBuilder();
        dfs(chars,map,builder,strings);
        String[] strings1 = new String[strings.size()];
        int i = 0;
        for (String s1: strings){
            strings1[i++] =s1;
        }

        return strings1;

    }
    public void dfs(char[] chars, Map<Character,Integer> map,StringBuilder builder, Set<String> strings){
        int t;
        String s;
        for (char aChar : chars) {
            if ((t = map.get(aChar)) != 0) {
                builder.append(aChar);
                map.put(aChar, t - 1);
                if (builder.length() == chars.length && !strings.contains(s = builder.toString())) {
                    strings.add(s);
                }
                if (builder.length() < chars.length) {
                    dfs(chars, map, builder, strings);
                }
                map.put(aChar, t);
                builder.deleteCharAt(builder.length() - 1);

            }

        }

    }
}
