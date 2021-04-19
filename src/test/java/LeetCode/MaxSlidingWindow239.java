package LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
// 滑动窗口求最大值
public class MaxSlidingWindow239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < k)return nums;
        int []ans = new int[nums.length-k+1];
        LinkedList<Integer> list = new LinkedList<>();
        int r;
        for(r = 0; r < nums.length; r++){
            while (!list.isEmpty() && nums[list.getLast()] <= nums[r])list.pollLast();
            list.addLast(r);

            if(r-k == list.getFirst()){
                list.pollFirst();
            }
            if(r-k+1 >= 0){
                ans[r-k+1] = nums[list.getFirst()];
            }
            System.out.println(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxSlidingWindow239().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
