package LeetCode;

import java.util.Arrays;

/**
 * @author 18190
 * @Date: 2021/6/2  20:29
 * @VERSION 1.0
 */
public class MaxProfit188 {
    public static void main(String[] args) {
        System.out.println(new MaxProfit188().maxProfit(2, new int[]{2,4,1}));
        System.out.println(new MaxProfit188().maxProfit1(2, new int[]{2,4,1}));
    }
    // 理解了123题 本题跟123题思路一模一样
    public int maxProfit(int k,int[] prices){
        if(prices == null || prices.length < 2)return 0;
        // buy[i]、sail[2] 分别代表第i次买卖股票
        // i属于[1,k]  buy[i] = max(buy[i], sail[i-1] - prices[j]); sail[i] = max(sail[i], buys[i]+prices[j])
        int[] buys = new int[k+1];
        int[] sails = new int[k+1];
        for (int i = 1; i < buys.length; i++){
            buys[i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++){
            for (int j = 1; j < sails.length; j++){
                buys[j] = Math.max(buys[j], sails[j-1] - prices[i]);
                sails[j] = Math.max(sails[j],buys[j] + prices[i]);
            }
        }
        // 最大值就是sails[k]
        return sails[k];
    }
    // 稍微优化了一下
    public int maxProfit1(int k,int[] prices){
        if(prices == null || prices.length < 2)return 0;
        int[] buys = new int[k];
        int[] sails = new int[k+1];
        Arrays.fill(buys, -prices[0]);
        for (int i = 1; i < prices.length; i++){
            for (int j = 1; j < sails.length; j++){
                buys[j-1] = Math.max(buys[j-1], sails[j-1] - prices[i]);
                sails[j] = Math.max(sails[j],buys[j-1] + prices[i]);
            }
        }
        return sails[k];
    }
}
