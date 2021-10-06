package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/10/6  21:18
 * @VERSION 1.0
 */
public class L_51 {
    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] chars = new char[n][n];
        for (int i = 0; i < n; i++){

            Arrays.fill(chars[i],'.');
        }
        dfs(chars,0);
        return ans;
    }

    public boolean isValid(char[][] cur,int i, int j){
        int t = j-i,u;
        int s = i+j,v;
        for (int k = 0; k < i; k++){
            u = t+k;
            v = s-k;
            if (cur[k][j] == 'Q' || (u >= 0 && cur[k][u] == 'Q') || (v < cur.length &&  cur[k][v] == 'Q'))
                return false;
        }
        return true;
    }

    public void dfs(char[][] cur,int len){
        if (len == cur.length){
            List<String> e = new ArrayList<>();
            for (char[] chars : cur) {
                e.add(new String(chars));
            }
            ans.add(e);
            return;
        }
        for (int i =  0;i < cur.length; i++){
            if (isValid(cur,len,i)){
                cur[len][i] = 'Q';
                dfs(cur,len+1);
                cur[len][i] = '.';
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L_51().solveNQueens(4));
    }
}
