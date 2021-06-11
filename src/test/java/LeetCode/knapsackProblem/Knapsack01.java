package LeetCode.knapsackProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/6/11  10:01
 * @VERSION 1.0
 */


public class Knapsack01 {
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
        list.add(new Knapsack(1,1));
        list.add(new Knapsack(2,1));
        list.add(new Knapsack(5,1));
        System.out.println(new Knapsack01().knapsack(10,list));
        System.out.println(new Knapsack01().knapsack1(10,list));
        System.out.println(new Knapsack01().knapsack(8,list));
        System.out.println(new Knapsack01().knapsack1(8,list));
    }
    /*
    01背包问题：
    n件物品、总价值为V。 一件物品有价值Vi、重量Wi
    每件物品只有一件。

    如果并没有要求必须把背包装满，而是只希望价格尽量大，初始化时应该将 F[0..V ]全部设为 0。
     */
    public int knapsack(int V, List<Knapsack> knapsacks){
        int[] dp = new int[V+1];
        for (Knapsack knapsack:knapsacks){
            for (int i = V; i >= knapsack.weight; i--){
                dp[i] = dp[i-knapsack.weight] + knapsack.value;
            }
        }
        return dp[V];
    }
    /*
    要求恰好装满背包，那么在初始化时除了 F[0] 为 0，其它F[1..V ] 均设为 −∞，这样就可以保证最终得到的 F[V ] 是一种恰好装满背包的最优解。
     */
    public int knapsack1(int V, List<Knapsack> knapsacks){
        int[] dp = new int[V+1];
        Arrays.fill(dp,Integer.MIN_VALUE);
        dp[0] = 0;
        for (Knapsack knapsack:knapsacks){
            for (int i = V; i >= knapsack.weight; i--){
                if(dp[i-knapsack.weight] != Integer.MIN_VALUE)
                dp[i] = dp[i-knapsack.weight] + knapsack.value;
            }
        }
        return dp[V]== Integer.MIN_VALUE?-1:dp[V];
    }
}
