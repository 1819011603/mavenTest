package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/9/29  9:54
 * @VERSION 1.0
 */
public class L_16 {


    public int threeSumClosest(int[] nums,int target) {

        Arrays.sort(nums);

        int ans = 100000;
        int left = 0,second,right,temp;
        while (left < nums.length){
            second = left + 1;
            right = nums.length-1;
            while (second < right){
                temp = nums[left] + nums[second] + nums[right];
                if (Math.abs(temp-target) < Math.abs(ans-target)){
                    ans = temp;
                }
                if (temp > target){

                    while (second < right && nums[right] == nums[right-1])right--;
                    right--;
                }else if (temp < target){
                    while (second < right && nums[second] == nums[second+1])second++;
                    second++;
                }else {
                    return ans;
                }

            }
            while (left < nums.length-1 && nums[left] == nums[left+1])
            left++;
            left++;

        }

        return ans;

    }



    public static void main(String[] args) {
        System.out.println(new L_16().threeSumClosest(new int[]{-1,2,1,-4},1));

    }
}
