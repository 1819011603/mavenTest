package LeetCode.knapsackProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/6/11  10:35
 * @VERSION 1.0
 */
public class MultipleKnapsack {


    public static void main(String[] args) {
        List<KnapsackNum> list = new ArrayList<>();
        list.add(new KnapsackNum(4,2,3));
        list.add(new KnapsackNum(3,3,4));
        list.add(new KnapsackNum(2,4,5));
        list.add(new KnapsackNum(2,2,5));
        for (int i = 2; i <= 100; i++){
            System.out.println(new MultipleKnapsack().multipleKnapsack(i,list));
            System.out.println(new MultipleKnapsack().multipleKnapsack1(i,list));
            System.out.println(new MultipleKnapsack().multipleKnapsack2(i,list));
            System.out.println();
        }


    }

    // 多重背包  这种方法很麻烦 O(V*N) N = sum{knapsack.num}
    public int multipleKnapsack(int V, List<KnapsackNum> knapsacks){
        int[] dp = new int[V+1];
        for (KnapsackNum knapsack:knapsacks){
            for (int i = V; i >= knapsack.weight; i--){
                int k = 1;
                int u = knapsack.num;
                while (u > 0){
                    if(i < knapsack.weight * k){
                        break;
                    }
                    dp[i] = Math.max(dp[i-knapsack.weight * k] +k *  knapsack.value,dp[i]);
                    u--;
                    k++;
                }
            }
        }
        return dp[V];
    }


    //  多重背包  O(V*N)  N = sum{knapsack.num} O(V*Σ Mi)
    public int multipleKnapsack2(int V, List<KnapsackNum> knapsacks){
        int[] dp = new int[V+1];
        // O(V*Σ Mi)
        for (KnapsackNum knapsack:knapsacks){
            int u = knapsack.num;
            // u个包 O( Mi * V)
            while (u-- != 0){
                // 一个包   O(V)
                for (int i = V; i >= knapsack.weight; i--){
                    dp[i] = Math.max(dp[i-knapsack.weight ] + knapsack.value,dp[i]);
                }
            }
        }
        return dp[V];
    }


    //  多重背包  O(V*sum{logN(i)})  N(i) = knapsack.num  O(V*Σ logMi)
    public int multipleKnapsack1(int V, List<KnapsackNum> knapsacks){
        int[] dp = new int[V+1];
        // O(V*Σ logMi)
        int u ;
        for (KnapsackNum  knapsack:knapsacks){
            u = knapsack.num;
            if(knapsack.num * knapsack.weight >= V){
                 u= V/knapsack.weight;
             }
            int k = 1;
            // O(V*logMi)
            while (u > k){
                // O(V)
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

    private static class KnapsackNum{
        int value;
        int weight;
        int num;
        KnapsackNum(int w,int v,int n){
            weight = w;
            value = v;
            num = n;
        }


    }
}
