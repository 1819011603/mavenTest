package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/2  10:14
 * @VERSION 1.0
 */
public class L_29 {

    public int divide(int dividend, int divisor) {
        boolean fuhao = (dividend ^ divisor) >= 0;
        long d1 = Math.abs((long) dividend);
        long d2 = Math.abs((long) divisor);

        long ans = 0;
        for (int i = 31; i >= 0; i--){
            if ((d1>>i) >= d2){
                ans += 1L << i;
                d1 -= d2 << i;
            }

        }
        if (fuhao && ans > Integer.MAX_VALUE){
            ans = Integer.MAX_VALUE;
        }


        return (int) (fuhao?ans:-ans);

    }

    public static void main(String[] args) {
//        System.out.println(new L_29().divide(17,3));
//        System.out.println(new L_29().divide(1,1));
//        System.out.println(new L_29().divide(145745,3));
//        System.out.println(new L_29().divide(1,-1));
//        System.out.println(new L_29().divide(-145745,3));
        System.out.println(new L_29().divide(-2147483648,-1));

        // 10000000 00000000 00000000 00000000   == -2147483648
        System.out.println(1<<31);
        int temp = 0;
        for (int i = 0; i < 31; i++){
            temp |= 1<<i;
        }
        // 10000000 00000000 00000000 00000001   == -2147483647
        System.out.println(1<<31 | 1);
        // 01111111 11111111 11111111 11111111   ==  2147483647
        System.out.println(temp);


    }
}