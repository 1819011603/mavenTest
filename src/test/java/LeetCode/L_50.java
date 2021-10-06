package LeetCode;

import java.util.HashMap;

/**
 * @author 18190
 * @Date: 2021/10/6  15:12
 * @VERSION 1.0
 */
public class L_50 {
    public double myPow2(double x, int n) {
        if ( x == 1.0 || n == 0) {
            return 1.0;
        }

        double result = 1.0;
        long nn = n; // 很重要的一步，防止负数取反后超出范围

        if (nn < 0) {
            nn = -nn;
            x = 1 / x;
        }

/*
        1	 0	 0	  1	  1	  0	  1
      x^64 x^32	x^16 x^8 x^4 x^2 x^1

        最终结果就是所有二进制位为1的权值之积：x^1 * x^4 * x^8 * x^64 = x^77
 */

        while(nn > 0) {
            if (nn % 2 == 1) {
                result *= x;
            }
            x *= x;
            nn /= 2;
        }

        return result;
    }
    public double myPow(double x, int v){
        if (v == 0 || x == 1)return 1;
        if (x == 0)return 0;
        if (v > 0){
            x = 1/x;
            v = -v;
        }
        return dfs(x,v);
    }
    public double dfs(double x, int v){

        if (v == -1)return 1/x;
        double a = myPow(x,v>>1);

        if ((v & 1) == 1){
            return a *a * x;
        }else {
            return a *a;
        }
    }


    public double myPow1(double x, int v) {
        long n = v;
        if (n < 0 && x != 0){
            n = -n;
            x = 1/x;
        }

        long i = 1;
        double res = x;
        int t = 1;
        double[] ints = new double[33];
        while (i <= n){

            ints[t] = res;
            res = res*res;
            i<<=1;
            t++;
        }
        double ans = 1;
        while (n != 0  ){
            if (n >= i){
                n-=i;
                ans*= ints[t];
            }
            t--;
            i>>=1;
        }


        return ans;

    }

    public static void main(String[] args) {
        System.out.println(new L_50().myPow(2,-2147483648));
        System.out.println(new L_50().myPow(1,2147483647));
        System.out.println(new L_50().myPow(-1,2147483647));
        System.out.println(new L_50().myPow(2,7));
        System.out.println(new L_50().myPow(2,-7));
    }
}
