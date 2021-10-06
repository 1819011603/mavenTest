package LeetCode;

import java.util.Arrays;

/**
 * @author 18190
 * @Date: 2021/10/4  11:39
 * @VERSION 1.0
 */
public class L_34 {

    public int binaryLSearch(int[] nums,int l, int r, int target){
        int mid;
        while (l <= r){
            mid = (l + r) >> 1;
            if (nums[mid] >= target){

                r = mid-1;

            }else {
                l = mid + 1;
            }
        }
        if (l == nums.length || nums[l]!=target)return -1;
        return l;
    }

    public int binaryRSearch(int[] nums,int l, int r, int target){
        int mid;
        while (l <= r){
            mid = (l + r) >> 1;
            if (nums[mid] > target){

                r = mid-1;

            }else {
                l = mid + 1;
            }
        }
        return r;
    }
    public int[] searchRange(int[] nums, int target) {
        int lSearch = binaryLSearch(nums, 0, nums.length - 1, target);
        if (lSearch == -1)return new int[]{-1,-1};
        return new int[]{lSearch,binaryRSearch(nums,lSearch,nums.length-1,target)};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L_34().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 11)));
        System.out.println(Arrays.toString(new L_34().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 5)));
    }
}
