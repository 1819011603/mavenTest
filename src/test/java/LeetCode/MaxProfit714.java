package LeetCode;

/**
 * @author 18190
 * @Date: 2021/6/7  10:17
 * @VERSION 1.0
 */
public class MaxProfit714 {
    public static void main(String[] args) {
        System.out.println(new MaxProfit714().maxProfit(new int[]{1, 3, 2, 8, 4, 9},2));
    }


    public int maxProfit(int[] prices,int fee) {
        if(prices == null || prices.length < 2)return 0;
        int f0 = -prices[0];
        int f1 = 0;
        int f2;
        for(int i = 1; i < prices.length; i++){
            f2 = f0;
            f0 = Math.max(f0,f1-prices[i]);
            f1 = Math.max(f1,f2+prices[i]-fee);
        }
        return f1;
    }
}
