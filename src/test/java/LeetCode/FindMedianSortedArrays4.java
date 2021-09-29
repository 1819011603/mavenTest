package LeetCode;

import java.util.Arrays;

/**
 * @author 18190
 * @Date: 2021/9/24  10:17
 * @VERSION 1.0
 */
public class FindMedianSortedArrays4 {
    public double findMedianSortedArrays(int[] num1, int[] num2){
        if(num1.length * num2.length  == 0){
            if(num2.length == 0){
                num2 = num1;
            }
            if(num2.length % 2 == 0){
                return ((double) num2[num2.length/2] + num2[num2.length/2-1])/2;
            }else return num2[num2.length/2];

        }
        int[] temp;
        if(num2.length > num1.length){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }

        int total = num1.length + num2.length;
        if( (total % 2) == 1){

        }

        return 0.0;
    }
    public double findMedianSortedArrays2(int[] num1, int[] num2){
        int i1 = 0,i2 = 0;
        int total = num1.length + num2.length;
        int mid = (total >> 1)-1;
        int t =2;
        if(total % 2 == 1){
            t = 1;
            mid++;
        }
        while (i1 < num1.length && i2 < num2.length && i1 + i2 < mid){

            if(num1[i1] < num2[i2]){

                 i1++;

            }

            else {
                 i2++;

            }
        }
        while (i1 < num1.length&&  i1 + i2 < mid){

            i1++;
        }
        while (i2 < num2.length&&  i1 + i2 < mid){

            i2++;
        }
        double ans = 0;
        int a,b;
        int u = t;
        while ((i1 < num1.length || i2 < num2.length) && t -- != 0){
            a = i1 == num1.length?Integer.MAX_VALUE:num1[i1];
            b = i2 == num2.length?Integer.MAX_VALUE:num2[i2];
            if(a < b){
                ans+= a;
                i1 ++;
            }else {
                ans+=b;
                i2 ++;
            }
        }
        return ans/u;






    }

    public double findMedianSortedArrays1(int[] num1, int[] num2){
        int[] c = new int[num1.length + num2.length];
        int i1 = 0,i2 = 0;
        int tol = 0;
        int t =  c.length/2 ;
        while (i1 < num1.length && i2 < num2.length && tol <= t){
            if (num1[i1] < num2[i2]){
                c[tol++] = num1[i1++];
            }else c[tol++] = num2[i2++];
        }

        while (i1 < num1.length&& tol <= t){
            c[tol++] = num1[i1++];
        }
        while (i2 < num2.length && tol<=t){
            c[tol++] = num2[i2++];
        }
        System.out.println(Arrays.toString(c));
        if (c.length % 2 == 0){
            return ((double) c[t] + c[t-1])/2;
        }
        return  c[t];
    }

    public static void main(String[] args) {
        System.out.println(new FindMedianSortedArrays4().findMedianSortedArrays2(new int[]{2},new int[]{1,3,4}));
    }
}
