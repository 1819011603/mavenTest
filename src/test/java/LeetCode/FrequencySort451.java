package LeetCode;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/7/3  21:46
 * @VERSION 1.0
 */
public class FrequencySort451 {
    public static void main(String[] args) {
        System.out.println(new FrequencySort451().frequencySort("tree"));
        System.out.println(new FrequencySort451().frequencySort("audghqaoid,Aabb"));
    }

    public  String frequencySort(String s) {
        char[] chars = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        List<Node> list = new ArrayList<>();
        for (char aChar : chars) {
            if (!map.containsKey(aChar)) {
                list.add(new Node(aChar, 1));
                map.put(aChar, list.size() - 1);
            } else {
                Node node = list.get(map.get(aChar));
                node.time++;
            }
        }
        list.sort((o1, o2) -> o2.time - o1.time);
        StringBuilder builder = new StringBuilder();
        for (Node node:list){
            while (node.time-- != 0){
                builder.append(node.c);
            }
        }
        return builder.toString();


    }
    private static class Node{
        char c;
        int time;

        public Node(char c, int time) {
            this.c = c;
            this.time = time;
        }

    }

}
