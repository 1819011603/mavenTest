package LeetCode;

import javafx.beans.binding.ObjectExpression;

import java.util.*;

/**
 * @author 18190
 * @Date: 2021/6/27  20:52
 * dfs degrees[]
 * dfs 访问数组
 */
public class FindOrder210 {
    int[] ans;
    int t;
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindOrder210().findOrder(4,new int[][]{{1,0},{2,0},{3,1},{3,2}})));
        System.out.println(Arrays.toString(new FindOrder210().findOrder1(4,new int[][]{{1,0},{2,0},{3,1},{3,2}})));
    }
    public void dfs(Map<Integer, Set<Integer>> map,int i,int[] time,int [] ans,boolean[] booleans){
        if(time[i] != 0 || booleans[i]){
            return;
        }
        ans[t++] = i;
        booleans[i] = true;
        Set<Integer> set = map.get(i);
        if(set == null)return;
        for (Integer s: set){
            time[s]--;
            dfs(map,s,time,ans,booleans);
        }
    }


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        t = 0;
        int []time = new int[numCourses];
        boolean[] booleans = new boolean[numCourses];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] i:prerequisites){
            time[i[0]]++;
            map.computeIfAbsent(i[1], k -> new HashSet<>());
            map.get(i[1]).add(i[0]);
        }
        System.out.println(map);
        for (int i = 0; i < numCourses; i++){
            dfs(map,i,time,ans,booleans);
        }
        if(t >= numCourses)return ans;
        else return new int[0];
    }
    boolean valid = true;
    public void dfs1(ArrayList<Integer>[] list,int i,int[] booleans){
        /*
            booleans[i] == 1:搜索中
            booleans[i] == 0:未搜索
            booleans[i] == 2:搜索完成
         */
        booleans[i] = 1;
        List<Integer> set = list[i];
        if(set!= null)
        for (int s: set){
            if(booleans[s] == 0){
                dfs1(list,s,booleans);
                if(!valid)return;
            }else if(booleans[s] == 1){
                valid = false;
                return;
            }

        }
        booleans[i] = 2;
        ans[t--] = i;
    }
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        ans = new int[numCourses];
        t = numCourses-1;
        valid = true;

        int[] booleans = new int[numCourses];
        ArrayList<Integer>[] list = new ArrayList [numCourses];

        for (int[] i:prerequisites){
            if(list[i[1]] == null)list[i[1]] = new ArrayList<>();
            list[i[1]].add(i[0]);
        }

        for (int i = 0; i < numCourses && valid; i++){
            if(booleans[i] == 0)
            dfs1(list,i,booleans);
        }

        if(valid)return ans;
        else return new int[0];

    }
}
