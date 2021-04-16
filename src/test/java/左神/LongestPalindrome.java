package 左神;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LongestPalindrome {
    // 动态规划
    public String longestPalindrome(String s){
        int n = s.length();
        boolean[][] dp = new boolean [n][n];
        int maxn = 1,start = 0;
        for (int i = 1; i <= n ; i++) {
            int j = n - i+1;
            for(int k = 0; k < j; k++){
                if(i == 1){
                    dp[k][k] = true;
                }else if(i == 2){
                    if(s.charAt(k) == s.charAt(k+1)){
                        dp[k][k+1] = true;
                        if(maxn < i){
                            maxn = i;
                            start = k;
                        }
                    }
                }else{
                    if(s.charAt(k) == s.charAt(k+i-1) && dp[k+1][k+i-2]){
                        dp[k][k+i-1] = true;
                        if(maxn < i){
                            maxn = i;
                            start = k;
                        }
                    }
                }
            }

        }
        return s.substring(start,start+maxn);
    }

    // 经典算法O(n^2)
    public String longestPalindrome1(String s){
        int n = s.length(),i,j,k;
        int l = 0,len = 1;
        for(i = 0; i < n; i++){
            if(len/2 >= n-i){
                break;
            }
            j = i+1;
            while (j < n && s.charAt(i) == s.charAt(j) ){
                j++;
            }
            k = i-1;
            while (k >= 0 && j < n && s.charAt(k) == s.charAt(j)){
                k--;
                j++;
            }
            k++;
            if(j - k > len){
                l = k;
                len = j-k;
            }
        }
        return s.substring(l,l+len);
    }
    // 马拉车算法   可以利用前面的信息就不暴力  利用不了只能暴力
    public String Manacher(String u){

        // #a#b#a#
        StringBuilder t = new StringBuilder("#");
        for (int i = 0; i < u.length(); i++) {
            t.append(u.charAt(i)).append("#");

        }
        String s = t.toString();
        // 记录当前i的回文半径
        int[] radius = new int[s.length()];

        // mid的回文右边界是right-1
        int right = 1, mid = 0;
//        radius[0] = 1;  // 可以省去

        // 最大半径  最大半径对应的中心
        int radiusMax = 1,radiusMaxCenter = 0;


        // 第一个# 和最后一个# 没必要计算
        // # 是虚轴   数字是实轴  总是虚轴对虚轴 实轴对实轴  所以#可以替换为任何东西
        // 加#是为了 奇偶回文串统一处理
        for (int i = 1; i < s.length()-1; i++) {
            // mid的回文右边界是 right-1
            if(right > i){
                // 两种情况
                // 因为回文的逆序还是回文 所以i位置的回文等价于 i关于mid对称位置的j  (i+j == 2 * mid ==> j = 2mid - i);
                // 1.j的回文半径为radius[j]  如果(j回文左边界)j-radius[j]+1 > (mid回文左边界)mid-radius[mid]+1
                // 也就是说mid的回文直径 真包含 于 j的回文直径 此时radius[i] = radius[2*mid-i]
                // 2. j的回文半径为radius[j]  如果(j回文左边界)j-radius[j]+1 < (mid回文左边界) mid-radius[mid]+1
                // 也就是说mid的回文直径 不包含 于 j的回文直径
                // 又因为mid的回文半径为radius[mid] 则 chars[mid-radius[mid]] != chars[mid+radius[mid]]
                // 所以此时 radius[i] = right - i ;
                // 1.  radius[i] = radius[2*mid-i]   O(1)
                // mid的回文直径 真包含 于 j = 2*mid-i 的回文直径 所以 radius[2*mid-i] <= right-i
                // 2. radius[i] = right - i mid的回文直径 不包含 于 j的回文直径 radius[2*mid-i] >= right-i   O(1)
                // 3. j的回文串左边界刚好等于mid回文串的左边界 即j-radius[j]+1 == mid-radius[mid]+1 此时 radius[2*mid-i] == right-i
                // 再去暴力递归 看radius[i]能否++   不确定
                // 综上所述  right > i  有radius[i] = Math.min(radius[2*mid-i],right-i);

                radius[i] = Math.min(radius[2*mid-i],right-i);

            }else{
                // right <= i 暴力递归  right == i 说明i位置肯定没有被暴力过  被暴力过的话right位置至少为i+1
                radius[i] = 1;
            }
            // right <= i 和   j的回文串左边界刚好等于mid回文串的左边界 共用  其他两种情况就是O(1)
            while ( i-radius[i] >= 0 && i + radius[i]<s.length() && s.charAt(i+radius[i]) == s.charAt(i-radius[i]))++radius[i];
            if(right < i + radius[i]){
                // right = mid 的右边界+1
                right = i+radius[i];
                mid = i;
            }
            if(radius[i] > radiusMax){
                radiusMax = radius[i];
                radiusMaxCenter = i;
            }

        }


        // 如果中心为# 说明回文半径为偶数
        if(s.charAt(radiusMaxCenter) == '#'){
            radiusMaxCenter >>= 1;
            radiusMax >>=1;
            return u.substring(radiusMaxCenter-radiusMax,radiusMaxCenter+radiusMax);
        }else {
            // 如果中心不为# 说明回文半径为奇数
            radiusMaxCenter >>= 1;
            radiusMax >>=1;
            return u.substring(radiusMaxCenter-radiusMax+1,radiusMaxCenter+radiusMax);
        }



    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        System.out.println(new LongestPalindrome().longestPalindrome1(s));
        System.out.println(new LongestPalindrome().Manacher(s));

    }
}
