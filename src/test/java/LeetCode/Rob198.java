package LeetCode;

public class Rob198 {
    public int rob(int[] nums){

        if(nums.length == 1)return nums[0];
        else if(nums.length == 2)return Math.max(nums[0],nums[1]);
        int last_do = nums[0],last_undo = 0,now_do = 0,now_undo = 0;
        for(int i = 1; i < nums.length; i++){
            now_undo = Math.max(last_undo,last_do);
            now_do = Math.max(last_do,last_undo+nums[i]);
            last_undo = now_undo;
            last_do = now_do;
        }
        return Math.max(now_do,now_undo);

    }

    public int rob1(int[] nums){

        if(nums.length == 1)return nums[0];
        else if(nums.length == 2)return Math.max(nums[0],nums[1]);
        int first = nums[0],second = Math.max(nums[0],nums[1]),temp;
        for(int i = 2; i < nums.length; i++){
            temp = second;
            second = Math.max(first+nums[i],second);
            first = temp;
        }
        return Math.max(first,second);

    }

    public static void main(String[] args) {
        int[] nums = {2,3,2};
        System.out.println(new Rob198().rob(nums));
        System.out.println(new Rob198().rob1(nums));

    }
}
