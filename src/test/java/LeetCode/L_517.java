package LeetCode;

import java.util.Arrays;

/**
 * @author 18190
 * @Date: 2021/9/29  20:29
 * @VERSION 1.0
 */
public class L_517 {


        public int findMinMoves(int[] machines) {
            int tot = Arrays.stream(machines).sum();
            int n = machines.length;
            if (tot % n != 0) {
                return -1;
            }
            int avg = tot / n;
            int ans = 0, sum = 0;
            for (int num : machines) {
                num -= avg;
                sum += num;
                ans = Math.max(ans, Math.max(Math.abs(sum), num));
            }
            return ans;
        }



    public static void main(String[] args) {
        System.out.println(new L_517().findMinMoves(new int[]{2,0,3,0,0}));
    }
}
