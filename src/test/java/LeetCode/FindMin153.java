package LeetCode;

public class FindMin153 {
    public int findMin(int []nums){

        return dfs2(nums,0,nums.length-1);
    }


    public int dfs(int[] nums,int l,int r) {
        if(l >= r)return nums[l];

        if(nums[l] < nums[r])return nums[l];
        int mid = (l + r)/2;
        if(nums[mid] > nums[l])return dfs(nums,mid+1,r);
        else return dfs(nums,l+1,mid);

    }
    public int dfs1(int[] nums,int l,int r) {
        int mid;
        while (l < r && nums[l] < nums[r]){

            mid = (l+r) >>1;
            if(nums[mid] > nums[l]){
                l = mid+1;
            }else {
                l ++;
                r = mid;
            }

        }
        return nums[l];

    }

    public int dfs2(int[] nums,int l,int r) {
        int mid;
        while (l < r){

            mid = (l+r) >>1;
            //  {4,5}  {5,4}  nums[mid] > nums[l] 会出现问题 因为 l == mid 导致num[l] == num[mid]   但  mid != r 恒成立
            if(nums[mid] > nums[r]){
                l = mid+1;
            }else {

                r = mid;
            }

        }
        return nums[l];

    }
    public static void main(String[] args) {
        int[] nums = {4,5,6,0,1,2,3};
        System.out.println(new FindMin153().findMin(nums));
    }
}
