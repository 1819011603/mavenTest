package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/9/29  9:54
 * @VERSION 1.0
 */
public class L_18 {


    public List<List<Integer>> fourSum(int[] nums, int target) {

        if (nums.length <4 )return  new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();


        int left = 0,second,three,right = nums.length-1,temp;
        while (left < nums.length-3 ){
            second = left+1;
            while ( second < nums.length-2 ){
                three = second + 1;
                right = nums.length-1;
                while (three < right){

                    temp = nums[left] + nums[second]+ nums[three] + nums[right];
                    if (temp > target){
                        while (three < right && nums[right] == nums[right-1])right--;
                        right--;
                    }else if(temp < target){
                        while (three < right && nums[three] == nums[three+1])three++;

                        three++;
                    }
                    else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[left]);
                        list.add(nums[second]);
                        list.add(nums[three]);
                        list.add(nums[right]);
                        ans.add(list);
                        while (three < right && nums[three] == nums[three+1])three++;
                        three++;
                        while (three < right && nums[right] == nums[right-1])right--;
                        right--;

                    }

                }
                while (second < nums.length-2 && nums[second] == nums[second+1])second++;
                second++;
            }

            while (left < nums.length-3 && nums[left] == nums[left+1])
            left++;
            left++;

        }

        return ans;

    }



    public static void main(String[] args) {
        System.out.println(new L_18().fourSum(new int[]{1,0,-1,0,-2,2},0));
        System.out.println(new L_18().fourSum(new int[]{2,2,2,2},8));

    }
}
