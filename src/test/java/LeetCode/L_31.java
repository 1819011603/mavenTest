package LeetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author 18190
 * @Date: 2021/10/2  11:23
 * @VERSION 1.0
 */
public class L_31 {
    public void swap(int[] nums,int l, int r){
        int T = nums[l];
        nums[l] = nums[r];
        nums[r] = T;
    }

    public int binarySearch(int[] nums, int l, int r, int value){
        int mid;
        while (l <= r){
            mid = (l+r) >> 1;
            if (nums[mid] > value){
                l = mid + 1;
            }else // 相等的话会往前找 保证比它大
                r = mid-1;
        }
        return r;
    }

    public void  sort(int[] nums, int l, int r){
        for (int i = l; i < r; i++){
            swap(nums,i,r--);
        }
    }

    public void nextPermutation(int[] nums) {

        int len = nums.length-1;
        if (len<1)return;
        while (len >= 1){

            // 从后往前找顺序
            if (nums[len] > nums[len-1]){

                // 从逆序中找到一个比nums[len-1]大的最小数（）不能等于
                int index = binarySearch(nums,len,nums.length-1,nums[len-1]);
                swap(nums,index,len-1);
                // 交换之后逆序还是逆序  将右边逆序排序即可
                sort(nums,len,nums.length-1);
                return;
            }
            len--;
        }

        // 逆序
        sort(nums,len,nums.length-1);

    }
    public static void main(String[] args) {
        int[] ints;
//        ints = new int[]{1, 2, 3};
//        new L_31().nextPermutation(ints);
//        System.out.println(Arrays.toString(ints));
//
//        ints = new int[]{3,2,1};
//        new L_31().nextPermutation(ints);
//        System.out.println(Arrays.toString(ints));
//
//        ints = new int[]{1,1,5};
//        new L_31().nextPermutation(ints);
//        System.out.println(Arrays.toString(ints));

        ints = new int[]{1,6,7,5};
        new L_31().nextPermutation(ints);
        System.out.println(Arrays.toString(ints));
    }
}
