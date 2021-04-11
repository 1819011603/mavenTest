package LeetCode;

import java.util.Arrays;
import java.util.Scanner;

public class LongestPalindrome5 {

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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(new LongestPalindrome5().longestPalindrome(s));
        System.out.println(new LongestPalindrome5().longestPalindrome1(s));

    }
}
