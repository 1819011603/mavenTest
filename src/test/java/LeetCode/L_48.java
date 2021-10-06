package LeetCode;

import java.util.Arrays;

/**
 * @author 18190
 * @Date: 2021/10/6  16:54
 * @VERSION 1.0
 */
public class L_48 {


    public void rotateFrame(int[][] matrix,int i){
        if (i == matrix.length/2) {
            return;
        }
        int s = matrix.length-1;
        int temp;
        int start = matrix.length-i-1;
        for (int j = start; j> i; j--){
            temp = matrix[i][s-j];
            matrix[i][s-j] = matrix[j][i];
            matrix[j][i] = matrix[s-i][j];
            matrix[s-i][j] = matrix[s-j][s-i];
            matrix[s-j][s-i] = temp;
        }
        rotateFrame(matrix,i+1);

    }
    public void rotate(int[][] matrix) {

        rotateFrame(matrix,0);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        new L_48().rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
