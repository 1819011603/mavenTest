package LeetCode;

/**
 * @author 18190
 * @Date: 2021/6/7  9:33
 * @VERSION 1.0
 */
public class MaxProfit309 {
    public static void main(String[] args) {
        System.out.println(new MaxProfit309().maxProfit(new int[]{1,2,3,0,2}));
    }

    /**
     * 给定一个整数数组，其中第i个元素代表了第i天的股票价格 。
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * f0 买入 f1 卖出 f2冷冻期
     *
     * f0 = max(f`0,f`2-prices[i]);
     * f1 = max(f`1,f`0+prices[i]);
     * f2 = max(f`1,f`2);
     *
     *
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)return 0;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        int f4;
        for(int i = 1; i < prices.length; i++){
            /*
            int newf0 = max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
            调了一下顺序，优化了一下
             */
            f4 = f1;
            f1 = Math.max(f4,f0+prices[i]);
            f0 = Math.max(f0,f2-prices[i]);
            f2 = Math.max(f4,f2);

        }// f1 >= f2
        return f1;
    }
}
