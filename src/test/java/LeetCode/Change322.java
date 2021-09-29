package LeetCode;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author 18190
 * @Date: 2021/6/10  19:41
 * @VERSION 1.0
 */
public class Change322 {
    public static void main(String[] args) {
        new Change322().coinChange(new int[]{2},11);
    }




    public int coinChange(int[] coins,int amount){
        int []dp = new int[amount+1];

        Arrays.fill(dp,Integer.MAX_VALUE);

        dp[0] = 0;
        /*
        dp[i] = min(dp[i-coins[j]] + 1, j = 1,2...,coins.length); i = 1,2...,amount.
        coins.length * amount

       (1,2,...,n)
        2 2 2 2 2
        3 3 3 3 3
        5 5 5 5 5
         */
        for (int i = 1; i <= amount; i++){
            for (int j: coins){
                if(i-j >= 0  && dp[i-j] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i-j] + 1,dp[i]);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 :dp[amount];
    }
}
