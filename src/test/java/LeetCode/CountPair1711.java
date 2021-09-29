package LeetCode;

import java.util.HashMap;
import java.util.Set;

/**
 * @author 18190
 * @Date: 2021/7/7  21:01
 * @VERSION 1.0
 */
public class CountPair1711 {
    public static void main(String[] args) {
        System.out.println(new CountPair1711().countPairs(new int[]{0,0,1,1,1,3,3,3,7}));
    }

    private static final long p = 1000000007;

    public static int getMinPower(int cap){
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n+1;
    }
    public int countPairs(int[] deliciousness){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int it:deliciousness){
            if(map.containsKey(it)){
                map.put(it,map.get(it)+1);
            }else {
                map.put(it,1);
            }
        }

        long ans = 0;
        Set<Integer> set = map.keySet();
        Integer i0 = map.get(0);
        if(i0 == null) i0 = 0;
        else {
            set.remove(0);
        }
       int minPower;
        Integer time,time1;
        for (int i: set){

            minPower = getMinPower(i);
            if(i == minPower){
                time = map.get(minPower);
                ans+= (long) time *(time-1)/2 + (long) i0 *time;

            }else {
                time = map.get(minPower - i);
                if (time == null)continue;
                time1 = map.get(i);
                ans += (long) time *time1;
            }

        }

        return (int) (ans%(p));
    }
}
