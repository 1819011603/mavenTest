package LeetCode;

import java.util.Arrays;

/**
 * @author 18190
 * @Date: 2021/10/4  18:22
 * @VERSION 1.0
 */
public class L_36 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] vis = new boolean[2][10];
        boolean[][] v = new boolean[9][10];
        int s;

        for (int i = 0; i < 9; i++){
            Arrays.fill(vis[0],false);
            Arrays.fill(vis[1],false);
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.'){
                    boolean[] vi = v[i  / 3 * 3 + j  / 3];
                    if (vis[0][s = board[i][j]-'0'] || vi[s])return false;
                    vis[0][s] = true;
                    vi[s] = true;
                }

                if (board[j][i] != '.'){
                    if (vis[1][s = board[j][i]-'0'])return false;
                    vis[1][s] = true;
                }


            }

        }




        return true;


    }


    public static void main(String[] args) {
        System.out.println(new L_36().isValidSudoku(new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}}));
    }
}
