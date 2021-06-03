package LeetCode;

/**
 * @author 18190
 * @Date: 2021/5/27  20:39
 * @VERSION 1.0
 */
public class MaxProfit123 {

    public static void main(String[] args) {
        int[] prices = new int[]{3,2,5,0,0,3,1,4};
        System.out.println(maxProfit(prices));//  自己做的
        System.out.println(maxProfit1(prices)); // 官方优化的
        System.out.println(maxProfit2(prices)); // 官方优化的
    }

    /**
     *
     * 动态规划的思想
     * 思路：买卖两次的状态 共分为四个阶段 第一次买入buy1 第一次卖出sail1 第二次买入buy2 第二次卖出sail2  结果就是第一次卖出和第二次卖出的最大值
     *
     * 1. 状态找出来了，状态之间是怎么转移的。
     *  buy1 = max(-prices[j](j == 1,2,...,n))
     *  sail1 = max(sail1, buy1+prices[j]);
     *  buy2 = max(buy2, sail1-price[j]);
     *  sai2 = max(sail2, buy2+ price[j]);
     *
     *  2.初始状态设置：
     *  buy1  =  -price[0]  第一个邮票肯定是买入最大的
     *  sail1 = 0  出售的话  因为现在没有出售  而且不可能会亏  最小值就是0 现在在0 这个位置不可能会出售，所以直接设为0
     *  buy2 0位置不可能第二次买入  能不能像上面一样设为0呢？  当然是不可以！！！ 为什么？ 假设现在为3 4 2 4 现在buy1 = -2, sail1 = 1,buy2 = max(0,1-2) = 0,
     *  本来buy2应该是-1,初始化为buy2为0，肯定不行，因为买入会减，buy2可以小于0，但是sail是大于等于0。 buy2 = Integer.MIN_VALUE;
     *
     *  sail2 = 0, sail2这个状态在0位置不可能存在，又因为sail2>=0
     *
     * @param prices 股票价格
     * @return 买卖两次的最大值
     */
    public  static int maxProfit(int[] prices){
        if(prices == null || prices.length == 0)return 0;
        int buy1 = -prices[0],buy2 = Integer.MIN_VALUE ,sail1 = 0,sail2 = 0,a,b,c,d;
        for (int i = 1; i < prices.length; i++){
            a = Math.max(-prices[i],buy1);
            b = Math.max(buy1+prices[i],sail1);
            c = Math.max(sail1 -prices[i],buy2);
            d = Math.max(buy2+prices[i],sail2);
            buy1 = a;
            sail1 = b;
            buy2 = c;
            sail2 = d;
        }
        return Math.max(sail1,sail2);

    }

    /**
     * 简单优化 下一个要用到上一个的 逆着来就可以省去中间变量
     */
    public  static int maxProfit1(int[] prices){
        if(prices == null || prices.length == 0)return 0;
        int buy1 = -prices[0],buy2 = Integer.MIN_VALUE ,
                sail1 = 0,sail2 = 0; // buy2 = -prices[0] 也可 可以看做当天买入卖出再买入 并不影响结果
        for (int i = 1; i < prices.length; i++){
            sail2 = Math.max(buy2+prices[i],sail2);
            buy2 = Math.max(sail1 -prices[i],buy2);
            sail1 = Math.max(buy1+prices[i],sail1);
            buy1 = Math.max(-prices[i],buy1);

        }
        return Math.max(sail1,sail2);

    }

    /**
     * 同一天买入卖出不影响结果 并且不计入买卖次数
     * 无论题目中是否允许「在同一天买入并且卖出」这一操作，最终的答案都不会受到影响，这是因为这一操作带来的收益为零。
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-iii-by-wrnt/
     */
    public  static int maxProfit2(int[] prices){
        if(prices == null || prices.length == 0)return 0;
        int buy1 = -prices[0],buy2 = Integer.MIN_VALUE ,sail1 = 0,sail2 = 0;
        for (int i = 1; i < prices.length; i++){
            // 1-i天第一次买入的最大值 buy1 = max{-prices[0],-prices[1],...,-prices[i]};
            buy1 = Math.max(-prices[i],buy1);
            /*
              右边的sail1 是第1-(i-1)的最大值
              buy1 + price[i] = {-price[0]+prices[i],-price[1]+prices[i],...,-price[i-1] + price[i],-price[i]+price[i] = 0};
              这样也能保证 sail1>=0,sail1 代表 (0-(i-1))的最大值
             */
            sail1 = Math.max(buy1+prices[i],sail1);


            buy2 = Math.max(sail1 -prices[i],buy2);

            /*
            sail2 = max{sail2(0,i-1), sail1 - price[i] + price[i] = sail1} =max {sail2(0,n-1),sail1}
            sail2>=0
             */
            sail2 = Math.max(buy2+prices[i],sail2);

        }
        // sell1 会转向 sell2 可同在一天买入卖出 sail1的最大值会转向sail2
        return sail2;

    }
}
