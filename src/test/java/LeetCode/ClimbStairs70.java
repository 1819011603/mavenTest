package LeetCode;

/**
 * @author 18190
 * @Date: 2021/6/10  21:24
 * @VERSION 1.0
 */
public class ClimbStairs70 {
    public static void main(String[] args) {
        // 排列数和组合数
        System.out.println(new ClimbStairs70().climbStairs(5));
        System.out.println(new ClimbStairs70().climbStairs1(5));
    }
    /*
    排列数：可重复

    5 = 1 + 1 + 1 + 1 + 1;
    5 = 1 + 1 + 1 + 2;
    5 = 1 + 2 + 2;
    5 = 2 + 1 + 2;
    5 = 2 + 2 + 1;
    5 = 1 + 1 + 2 + 1;
    5 = 1 + 2 + 1 + 1;
    5 = 2 + 1 + 1 + 1;


     */
    public int climbStairs(int n) {
        if(n < 3)return n;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <=n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    //
    public int climbStairs1(int n) {
        if(n < 3)return n;
        int[] dp = new int[n+1];
        int[] coins = new int[]{1,2};
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <=n ; i++){
            for (int coin:coins){
                dp[i] += dp[i-coin];
            }
        }
        return dp[n];
    }
}
