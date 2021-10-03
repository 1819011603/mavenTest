package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/10/1  20:59
 * @VERSION 1.0
 */
public class L_1436 {
    public static void main(String[] args) {

        List<List<String>>  s = new ArrayList<>();
        List<String> list ;
        list = new ArrayList<>();
        list.add("C");
        list.add("D");s.add(list);
        list = new ArrayList<>();

        list.add("B");
        list.add("C");s.add(list);
        list = new ArrayList<>();
        list.add("D");
        list.add("A");s.add(list);
        System.out.println(
                new L_1436().destCity(s)
        );
    }
    public String destCity(List<List<String>> paths) {
        HashMap<String,String> outerDegree = new HashMap<>();
        for (List<String> cur:paths){
            outerDegree.put(cur.get(0),cur.get(1));
        }

        String ans = null;
        String temp = outerDegree.getOrDefault(paths.get(0).get(0),null);

        while (temp!=null){
            ans = temp;
            temp = outerDegree.get(temp);

        }
        return ans;
    }
}
