package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/4  11:02
 * @VERSION 1.0
 */
public class L_33 {
    public int search(int[] nums, int target) {
        return binarySearch(nums,0,nums.length-1,target);
    }
    public int binarySearch1(int[] nums,int l, int r, int target){
        int mid;
        while (l <= r){
            mid = (l + r) >> 1;
            if (nums[mid] > target){
                r = mid-1;
            }else if (nums[mid] < target){
                l = mid + 1;
            }else return mid;
        }
        return -1;
    }
    public int binarySearch(int[] nums,int l, int r, int target){
        int mid;
        while (l <=r ){
            mid = (l + r) >> 1;
            if (nums[mid] < nums[l]){
                if (nums[mid] > target){
                  r = mid-1;
                }
                else if(nums[mid] < target){
                    int s = binarySearch1(nums,mid+1,r,target);
                    if (s!=-1)return s;
                    return binarySearch(nums,l,mid-1,target);
                }
                else return mid;
            }else {
                if (nums[mid] < target){
                    l = mid+1;
                }
                else if(nums[mid] > target){
                    int s = binarySearch1(nums,l,mid-1,target);
                    if (s!=-1)return s;
                    return binarySearch(nums,mid+1,r,target);
                }
                else return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10;i ++)
        System.out.println(new L_33().search(new int[]{4,5,6,7,0,1,2},i));
    }
}
