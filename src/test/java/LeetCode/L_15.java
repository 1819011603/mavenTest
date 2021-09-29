package LeetCode;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/9/29  9:54
 * @VERSION 1.0
 */
public class L_15 {


    public List<List<Integer>> threeSum(int[] nums) {

        if (nums.length < 3)return  new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();


        int left = 0,second,right,temp;
        while (left < nums.length && nums[left] <= 0){
            second = left + 1;
            right = nums.length-1;
            while (second < right){
                temp = nums[left] + nums[second] + nums[right];
                if (temp > 0){
                    while (second < right && nums[right] == nums[right-1])right--;
                    right--;
                }else if (temp < 0){
                    while (second < right && nums[second] == nums[second+1])second++;
                    second++;
                }else {

                        List<Integer> list = new ArrayList<>();
                        list.add(nums[left]);
                        list.add(nums[second]);
                        list.add(nums[right]);
                        ans.add(list);
                        while (second < right && nums[second] == nums[second+1])second++;
                        second++;

                }

            }
            while (left < nums.length-1 && nums[left] == nums[left+1])
            left++;
            left++;

        }

        return ans;

    }



    public static void main(String[] args) {
        System.out.println(new L_15().threeSum(new int[]{-1,0,1,2,-1,-4,2,5}));
        System.out.println(new L_15().threeSum(new int[]{0,0,0}));
        System.out.println(new L_15().threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
    }
}
