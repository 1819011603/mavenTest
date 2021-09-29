package LeetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author 18190
 * @Date: 2021/6/10  17:06
 * @VERSION 1.0
 */
public class Change518 {

    public static void main(String[] args) {
        System.out.println(new Change518().change(5,new int[]{1,2}));
        System.out.println(new Change518().change1(5,new int[]{1,2}));
    }

    /**
     * 组合数：无顺序
     *
     * amount = 5; coins = [1,2,5];
     * coins[0] = 1;
     * 1 = 1; dp[1] = 1;
     * 2 = 1 + 1; dp[2] = 1;
     * 3 = 1 + 1 + 1; dp[3] = 1;
     * 4 = 1 + 1 + 1 + 1;dp[4] = 1;
     * 5 = 1 + 1 + 1 + 1; dp[5] = 1;
     *
     *
     * coins[1] = 2;
     * 2 = 2; dp[2] += 1; dp[2] = 2;
     * 3 = 1 + 2; dp[3] = 2;
     * 4 = dp[2] + 2;dp[4] = 3;
     * 5 = dp[3] + 2; dp[5] = 3;
     *
     * coins[2] = 5;
     * 5 = 5; dp[5] = 4;
     *
     *
     */
    public int change(int amount,int []coins){

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[ i - coin ];
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[amount];

    }

    //拍楼梯的升级版  排列数
    public int change1(int amount,int []coins){

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if(i >= coin)
                dp[i] += dp[ i - coin ];
        }
            System.out.println(Arrays.toString(dp));
        }
        return dp[amount];

    }
}
