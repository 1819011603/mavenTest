package LeetCode;

public class FindMin154 {
    public int findMin(int[] nums){
        int l = 0,r = nums.length-1,mid ;
        int[] a = new int[4];
        while (l < r){
            mid = (l+r) >> 1;
            if(nums[mid] > nums[r] ){
                    l = mid+1;

            }else if(nums[mid] < nums[r]){
                r = mid;
            }else{
              r--;

            }
        }
        return nums[l];

    }

    public int findMin1(int[] nums){
        int l = 0,r = nums.length-1,mid ;
        int[] a = new int[4];
        while (l < r){
            mid = (l+r) >> 1;
            if(nums[l] < nums[r])return nums[l];
            if(nums[mid] > nums[l] ){
                l = mid+1;

            }else if(nums[mid] < nums[l]){
                r = mid;
            }else{
                l++;

            }
        }
        return nums[l];

    }


    public static void main(String[] args) {
        int[] nums = {3,3,3,3,3,3,3,3,1,3};
        System.out.println(new FindMin154().findMin(nums));
        System.out.println(new FindMin154().findMin1(nums));
    }
}
