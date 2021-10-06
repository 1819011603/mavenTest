package LeetCode;

import com.sun.javafx.collections.ListListenerHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/10/5  10:12
 * @VERSION 1.0
 */
public class L_39 {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(new ArrayList<>(),0,0,candidates,target);
        return ans;
    }

    public void dfs(List<Integer> cur,int i,int sum,int[] candidates,int target)
    {
        if (target == sum){

            ans.add(new ArrayList<>(cur));
            return;
        }
        if (i == candidates.length || target < sum)return;
        int t = (target-sum)/candidates[i];
        int j = 1;
        while (j <= t){
                cur.add(candidates[i]);
                sum+= candidates[i];
                dfs(cur,i+1,sum,candidates,target);
                j++;
        }
        sum-=candidates[i]*t;
        while (t-- != 0)
        cur.remove(cur.size()-1);
        dfs(cur,i+1,sum,candidates,target);
    }

    public static void main(String[] args) {
        System.out.println(new L_39().combinationSum(new int[]{2,3,6,7,4,1,5},12));
        System.out.println(new L_39().combinationSum(new int[]{2,3,5},8));
        System.out.println(new L_39().combinationSum(new int[]{2},1));
        System.out.println(new L_39().combinationSum(new int[]{1},1));
        System.out.println(new L_39().combinationSum(new int[]{1},2));
    }
}
