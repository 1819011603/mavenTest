package LeetCode;

import org.junit.Test;

/**
 * @author 18190
 * @Date: 2021/5/27  20:39
 * @VERSION 1.0
 */
public class MaxProfit63 {

    public static void main(String[] args) {
        int[] price = new int[]{7,6,4,3,1};
        System.out.println(maxProfit(price));
    }

    /**
     * 思路：
     * 假设当前的最小值为price[0]
     * if max < cur- minn: max = cur- minn;
     * if minn > cur: minn = cur;
     * @param prices 股票价格
     * @return 买卖一次的最大值
     */
    public  static int maxProfit(int[] prices){
        if(prices == null || prices.length == 0)return 0;
        int minn = prices[0],max = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++){
            if(max < prices[i] - minn){
                max = prices[i] - minn;
            }

            if(minn > prices[i]) minn = prices[i];
        }

        return Math.max(max, 0);
    }
}
