package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/10/6  10:07
 * @VERSION 1.0
 */
public class L_46 {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] vis  = new boolean[nums.length];
        dfs(nums,vis,0,new ArrayList<>());
        return ans;
    }

    public void dfs(int[] nums,boolean[] vis, int i, List<Integer> cur){
        if ( i == nums.length){
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int j = 0; j < nums.length; j++){
            if (vis[j])continue;
            vis[j] = true;
            cur.add(nums[j]);
            dfs(nums,vis,i+1,cur);
            vis[j] = false;
            cur.remove(cur.size()-1);
        }
    }



    public static void main(String[] args) {
        System.out.println(new L_46().permute(new int[]{1,2,3}));
    }
}
