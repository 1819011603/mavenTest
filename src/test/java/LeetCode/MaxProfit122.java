package LeetCode;

/**
 * @author 18190
 * @Date: 2021/5/27  20:39
 * @VERSION 1.0
 */
public class MaxProfit122 {

    public static void main(String[] args) {
        int[] price = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(price)); // 自己想的
        System.out.println(maxProfit1(price)); //动态规划
        System.out.println(maxProfit2(price));
    }


    /**
     * 贪心思想：连续下降交易日： 则不买卖收益最大，即不会亏钱。
     * 连续上涨交易日： 设此上涨交易日股票价格分别为 p1,p2,...,pn 可以一次性pn-p1,等价于每天都买卖,
     * pn-p1=(p2-p1) + (p3-p2) + ... +(pn-pn-1)
     *
     * @param prices 股票价格
     * @return 多次买卖的最大值
     */
    public static int maxProfit2(int[] prices){
        if(prices == null || prices.length <= 1)return 0;
        int sum = 0,tmp;
        for (int i = 1; i < prices.length; i++){
            tmp = prices[i] - prices[i-1];
            if (tmp > 0)sum+= tmp; //可以简化为： sum += Math.max(prices[i] - prices[i-1],0);
        }
        return sum;
    }


    /**
     * 贪心思想：股价一要下降或者将要下降 我就抛售之前的股票（自己想的算法）  找到连续上涨的序列 一次相减
     *
     *  这个算法的中心思想根据下滑找递增序列。最后一个到末尾的上升序列需要单独处理
     *
     * 因为 1 2 3 4 一次买卖最大为3 两次买卖最大为2 所以像这种递增的 买卖次数只有一次效果最佳
     *[7,1,5,3,6,4] 最大为7  1 5 是一个递增序列 3 6是一个递增序列 递增就直接相减则为最大
     * 本次算法的思想就是 假设 min = prices[0] 从1开始从左向右开始遍历
     * 假设当前的 price[i] <= price[i-1]（开始下滑） 那么我们就知道从 [min,price[i-1]]是一个递增序列
     * sum+= price[i-1]-min;并且更新最小值min = price[i].
     * [7,1] 此时也可以看做特殊的递增序列 [min=7,price[i-1]=7] sum+=0;并不影响结果。
     *
     * 最后的特殊处理就是末尾  没有一个 递减的趋势了， 需要加上最后这个[min,prices[prices.length-1]]这个递增序列即可
     *
     * @param prices 股票价格
     * @return 多次买卖的最大值
     */
    public  static int maxProfit(int[] prices){
        if(prices == null || prices.length <= 1)return 0;
        int minn = prices[0],sum = 0;
        for (int i = 1; i < prices.length; i++){
            if(prices[i] <= prices[i-1]){
                sum+=prices[i-1] - minn;
                minn = prices[i];
            }
        }
        if(prices[prices.length-1] > prices[prices.length-2]){
            sum+= prices[prices.length-1] - minn;
        }
        return sum;

    }









    /**
     *
     * 算法的思想：利用动态规划的思想解决问题  转化为动态规划这个思想很重要
     *
     * 对于当前这一股股票有买、卖、什么都不做三个动作  但是题目隐含了
     * 卖要发生在手里有股票的情况下，  买要发生在手里没股票的情况下
     *
     *
     * 两种状态： 当前是买入状态的最大值  当前是卖出状态的最大值
     *

     *
     * 买卖一套动作  是 prices[i] - prices[j]
     * 可以分解为
     * 买是  -prices[j]  卖是+prices[i]
     *
     *
     * last_in: 上一天是买入的这个状态的最大效益（但是上一天的动作可以是买入、什么都不做）
     * last_out:上一天是卖出的这个状态的最大效益（但是上一天的动作可以是卖出、什么都不做）
     *
     * now_in  当前这一天是买入状态（买入、什么都不做）的最大效益
     * now_out 当前这一天是买入状态（卖出、什么都不做）的最大效益
     *
     * now_in = max(last_out-price[i], last_in);
     * now_out = max(last_in + price[i], last_out);
     *
     *买入状态的最大值  =  昨天买入状态的最大值（延续）、卖出状态当天买入的最大值
     *
     *卖出状态的最大值  =  昨天卖出状态的最大值（延续）、买入状态当天卖出的最大值
     * @param prices 股票价格
     * @return 多次买卖的最大值  卖出状态的最大值   买入状态的最大值不可能是最大值（因为买入之前的卖出状态肯定大于现在的效益）
     */
    public static int maxProfit1(int[] prices){
        int last_in = -prices[0],last_out = 0;
        int now_in = 0,now_out = 0;
        for (int i = 1; i  < prices.length; i ++){
            now_out = Math.max(last_in+prices[i],last_out);
            now_in = Math.max(last_in,last_out-prices[i]);
            last_in = now_in;
            last_out = now_out;

        }
        return now_out;
    }
}
