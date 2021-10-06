package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/6  19:25
 * @VERSION 1.0
 */
public class L_35 {
    public int searchInsert(int[] nums, int target) {
        int l = 0,r = nums.length-1,mid;
        while(l <= r){
            mid = (l+r)>>1;
            if(nums[mid] > target){
                r = mid-1;
            }else if(nums[mid] < target){
                l = mid+1;
            }else return mid;
        }
        return l;
    }

}
