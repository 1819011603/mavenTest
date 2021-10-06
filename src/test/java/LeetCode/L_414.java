package LeetCode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 18190
 * @Date: 2021/10/6  10:20
 * @VERSION 1.0
 */
public class L_414 {



    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int i = 1;
        for (int j = nums.length-2; j >= 0; j--){
            if (nums[j] != nums[j+1]){
                if (++i == 3){
                    return nums[j];
                }
            }
        }
        return nums[nums.length-1];
    }

    public static void main(String[] args) {
        System.out.println(new L_414().thirdMax(new int[]{3,1,1,2,2}));
        System.out.println(new L_414().thirdMax(new int[]{3,1}));

    }

}
