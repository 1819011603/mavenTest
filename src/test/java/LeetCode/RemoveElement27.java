package LeetCode;

public class RemoveElement27 {
    public int removeElement(int []nums,int val){
        int k = 0;
        int i = 0;
        while (i< nums.length ){
            if(nums[i] == val)k++;
            else nums[i-k] = nums[i];
            i++;
        }
        return nums.length-k;

    }
}
