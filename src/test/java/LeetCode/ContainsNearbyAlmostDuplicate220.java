package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ContainsNearbyAlmostDuplicate220 {
    // TreeSet 有序的
    public boolean containsNearbyAlmostDuplicate(int [] nums,int k, int t){
        int n = nums.length;

        // set 存储的是 nums[i]
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            // ceiling(E e)
            //返回此集合中最小元素大于或等于给定元素，如果没有此元素，则返回 null 。
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            //  不为空 大于等于该元素的 最小元素 <= (long) nums[i] + (long) t   返回true
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }

            // 都比(long) nums[i] - (long) t小 或者  ceiling >   (long) nums[i] + (long) t 则当前的元素加入
            set.add((long) nums[i]);
            // 为什么可以直接删 因为前面已经检查过了 [0,i-1]肯定不满足题意
            // 因为满足的话已经返回true  窗口里不可能有相等元素了(因为t>=0)  跟插入排序差不多
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public long getId(int num, long w){
        //  [0,t] id均为0（因为w = t + 1）  [-t-1,-1] 均为-1 所以num<0 时 要+1/w -1
        return num < 0? (num + 1L)/w-1:num/w;
    }

    public boolean containsNearbyAlmostDuplicate2(int [] nums,int k, int t){
        Map<Long,Long> map = new HashMap<>();
        long w = (long) t + 1;
        for(int i = 0; i < nums.length; i++){
            long id = getId(nums[i],w);
            // 一个桶里两个元素 那必然 abs() <= t;  id不可能有相等的 相等上返回true了
            if(map.containsKey(id) ){
                return true;
            }
            // 相邻桶也存在元素的话  比比看是不是小于w  小于为true 不小于判断下一个
            if(map.containsKey(id-1) && Math.abs(nums[i]-map.get(id-1)) <w){
                return true;
            }
            // 相邻桶也存在元素的话  比比看是不是小于w  小于为true 不小于判断下一个
            if(map.containsKey(id+1) && Math.abs(nums[i]-map.get(id+1)) <w){
                return true;
            }

            //不是相邻桶 或者相邻桶的两个元素也不满足 Math.abs(nums[i]-map.get(id-1)) < w   则把当前元素加入set
            map.put(id,(long)nums[i]);
            // 滑动窗口k i向右移 则set当中也要删id（id不可能有相等的 相等上面就返回true了）
            if(i >= k){
                map.remove(getId(nums[i-k],w));
            }
        }
        return false;
    }


    // 暴力算法
    public boolean containsNearbyAlmostDuplicate1(int [] nums,int k, int t){
        for(int i = 0; i < nums.length-1; i++){
            for(int j = i+1; j <= i+k && j < nums.length; j++){
                if(Math.abs((long) nums[j]-nums[i]) <= t){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(new ContainsNearbyAlmostDuplicate220().containsNearbyAlmostDuplicate(nums,3,0));
        System.out.println(new ContainsNearbyAlmostDuplicate220().containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1},1,2));
        System.out.println(new ContainsNearbyAlmostDuplicate220().containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9},2,3));
        System.out.println(new ContainsNearbyAlmostDuplicate220().containsNearbyAlmostDuplicate(new int[]{-3,3,6},2,3));
    }
}
