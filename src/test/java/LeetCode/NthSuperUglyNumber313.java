package LeetCode;

import java.util.Arrays;

public class NthSuperUglyNumber313 {
    public int nthSuperUglyNumber1(int n, int[] primes) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        int[] p = new int[primes.length];
        int[] nex = new int[primes.length];
        int u,v;
        for(int i = 0; i < primes.length; i++)p[i] = 1;
        for(int i = 2; i <= n; i++){
            u =  dp[p[0]] * primes[0];
            v = 0;
            for(int j = 0; j < primes.length; j++){
                nex[j] = dp[p[j]] * primes[j];
                if(nex[j] < u){
                    u = nex[j];
                    v = j;
                }
            }
            dp[i] = u;
            p[v]++;
            if(dp[i] == dp[i-1])i--;


        }

        return dp[n];

    }
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        int[] p = new int[primes.length];
        int[] nex = new int[primes.length];
        int u;
        for(int i = 0; i < primes.length; i++)p[i] = 1;
        for(int i = 2; i <= n; i++){
            u =  dp[p[0]] * primes[0];

            for(int j = 0; j < primes.length; j++){
                nex[j] = dp[p[j]] * primes[j];
                if(nex[j] < u){
                    u = nex[j];
                }
            }
            dp[i] = u;
            for(int j = 0; j < primes.length; j++){

                if(nex[j] == u){
                    p[j]++;
                }
            }


        }

        return dp[n];

    }
    public static void main(String[] args) {
        int n = 12;
        int[] primes = {2,7,13,19};
        System.out.println(new NthSuperUglyNumber313().nthSuperUglyNumber(n,primes));
        System.out.println(new NthSuperUglyNumber313().nthSuperUglyNumber1(n,primes));
    }
}
