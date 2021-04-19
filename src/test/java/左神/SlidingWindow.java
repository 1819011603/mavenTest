package 左神;

import java.util.Arrays;
import java.util.LinkedList;

// 滑动窗口求最大最小值
// 两次滑动就可以求窗口的最大值 最小值
public class SlidingWindow {
    public int[][] slidingWindow(int[] nums,int k){
        int[] minn = new int[nums.length];
        int[] maxn = new int[nums.length];
        LinkedList<Integer> min = new LinkedList<>();
        LinkedList<Integer> max = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!min.isEmpty() && nums[min.getLast()] >= nums[i]){
                min.pollLast();
            }
            min.add(i);
            while (!max.isEmpty() && nums[max.getLast()] <= nums[i]){
                max.pollLast();
            }
            max.add(i);
            if(i-k==min.getFirst()){
                min.pollFirst();
            }

            minn[i] = nums[min.getFirst()];

            if(i-k==max.getFirst()){
                max.pollFirst();
            }
            maxn[i] = nums[max.getFirst()];


        }
        return new int[][]{minn,maxn};
    }

    public static void main(String[] args) {
        int k = 3;
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int [][] ans = new SlidingWindow().slidingWindow(nums,k);
        System.out.println(Arrays.deepToString(ans));
        System.out.println(Arrays.toString(nums));
        System.out.print("min: ");
        for (int i= k-1; i < nums.length;i++){
            System.out.print( ans[0][i]+" ");

        }
        System.out.println();
        System.out.print("max: ");
        for (int i= k-1; i < nums.length;i++){

            System.out.print( ans[1][i]+" ");
        }
        System.out.println();
    }

}
