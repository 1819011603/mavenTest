package LeetCode;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/10/5  13:15
 * @VERSION 1.0
 */
public class L_40 {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        boolean[] vis = new boolean[candidates.length];
        dfs(candidates,target,0,new LinkedList<>(),vis);
        return ans;
    }

    public void dfs(int[] candidates, int target, int i, Deque<Integer> cur, boolean[] vis){
        if (target == 0){
            ans.add(new ArrayList<>(cur));
            return;
        }
        if (target < 0 || i == candidates.length)return;

        if (i > 0 && candidates[i] == candidates[i-1] && !vis[i-1]){
            dfs(candidates,target,i+1,cur,vis);
            return;
        }


        if (target >= candidates[i]){
            cur.addLast(candidates[i]);
            vis[i] = true;
            dfs(candidates,target-candidates[i],i+1,cur,vis);
            vis[i] = false;
            cur.removeLast();
        }

        dfs(candidates,target,i+1,cur,vis);

    }



    public static void main(String[] args) {
        System.out.println(new L_40().combinationSum2(new int[]{1,2,2,2,5},5));
        System.out.println(new L_40().combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }
}
