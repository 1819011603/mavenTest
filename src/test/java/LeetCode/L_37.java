package LeetCode;

import java.util.Arrays;

/**
 * @author 18190
 * @Date: 2021/10/4  19:37
 * @VERSION 1.0
 */
public class L_37 {
    boolean[][][] vis  =new  boolean[3][9][10];


    public boolean setBooleanTrue(int i, int j,int k){
        int s = i/3*3+j/3;
        if (vis[0][i][k] | vis[1][j][k] | vis[2][s][k])return false;
        vis[0][i][k] = true;
        vis[1][j][k] = true;
        vis[2][s][k] = true;
        return true;
    }

    public void setBooleanFalse(int i, int j,int k){
        int s = i/3*3+j/3;
        vis[0][i][k] = false;
        vis[1][j][k] = false;
        vis[2][s][k] = false;

    }
    boolean flag = true;
    public void solveSudoku(char[][] board) {

        for (int i = 0; i < 9; i++){

            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.'){
                 setBooleanTrue(i,j,board[i][j]-'0');
                }

            }

        }
        dfs(board,0,0);


    }
    public void dfs(char[][] board,int i,int j){
            if (j == 9){
                dfs(board,i+1,0);
                return;
            }
            if (i == 9){
                flag = false;
                return;
            }
            if ( board[i][j] != '.'){
                dfs(board,i,j+1);
                return;
            }

            for (int k = 1; k < 10; k++){
                if ( setBooleanTrue(i,j,k)){
                    board[i][j] = (char) ('0'+k);
                    dfs(board,i,j+1);
                    if (flag){
                        board[i][j] = '.';
                        setBooleanFalse(i,j,k);
                    }else break;

                }
            }

    }

    public static void main(String[] args) {
        char[][] chars = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new L_37().solveSudoku(chars);
        System.out.println(Arrays.deepToString(chars));
    }

}
