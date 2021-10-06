package LeetCode;

import java.util.Collections;

/**
 * @author 18190
 * @Date: 2021/10/5  17:15
 * @VERSION 1.0
 */
public class L_41 {

    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while ( i < nums.length){
            if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i]-1]){
                i++;

            }

            else swap(nums,i,nums[i]-1);

        }




        for (i = 0; i < nums.length; i++){
            if (nums[i]-1 != i){
                return i+1;
            }
        }
        return i+1;


    }
    public void swap(int[] nums,int i, int j){

        int T = nums[i];
        nums[i] = nums[j];
        nums[j] = T;

    }







    public static void main(String[] args) {
        System.out.println(new L_41().firstMissingPositive(new int[]{1,1}));
        System.out.println(new L_41().firstMissingPositive(new int[]{1,2,0}));
        System.out.println(new L_41().firstMissingPositive(new int[]{3,4,-1,1}));
    }
}
