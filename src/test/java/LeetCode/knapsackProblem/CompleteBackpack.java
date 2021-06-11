package LeetCode.knapsackProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/6/11  10:30
 * @VERSION 1.0
 */
public class CompleteBackpack {
    private static class Knapsack{
        int value;
        int weight;
        Knapsack(int w,int v){
            weight = w;
            value = v;
        }
    }
    public static void main(String[] args) {
        List<Knapsack> list = new ArrayList<>();
        list.add(new Knapsack(4,2));
        list.add(new Knapsack(3,3));
        list.add(new Knapsack(2,4));
        boolean ans = true;
        for (int i = 10; i <= 100; i++){
            int ans1 = new CompleteBackpack().completeBackpack(i,list);
            int ans2 = new CompleteBackpack().completeBackpack1(i,list);
            ans = ans && (ans1 == ans2);
//            System.out.println(ans1+ " " + ans2);


        }
        System.out.println("ans: "+ ans);
    }

    // O(VN)  N = knapsacks.size()
    private int completeBackpack(int V, List<Knapsack> knapsacks) {
        int[] dp = new int[V+1];

        for (Knapsack knapsack:knapsacks){
            // i <= V
            for (int i = knapsack.weight; i <= V; i++){

                dp[i] = Math.max(dp[i-knapsack.weight ] + knapsack.value,dp[i]);

            }



        }
        return dp[V];
    }
    // O(N*log(V))  N = knapsacks.size()
    private int completeBackpack1(int V, List<Knapsack> knapsacks) {

        int[] dp = new int[V+1];

        for (Knapsack knapsack:knapsacks){

            int u = V/knapsack.weight;
            int k = 1;
            while (u > k){
                // i >= knapsack.weight * k
                for (int i = V; i >= knapsack.weight * k; i--){
                    dp[i] = Math.max(dp[i-knapsack.weight*k ] + knapsack.value * k,dp[i]);
                }
                u-=k;
                k<<=1;
            }
            k = u;
            for (int i = V; i >= knapsack.weight * k; i--){
                dp[i] = Math.max(dp[i-knapsack.weight*k ] + knapsack.value * k,dp[i]);
            }


        }
        return dp[V];
    }



}
