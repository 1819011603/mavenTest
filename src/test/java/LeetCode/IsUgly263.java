package LeetCode;

import java.util.Scanner;

public class IsUgly263 {
    public boolean isUgly(int n){
        if( n <= 5 && n >= 1 && n!=4)return true;
        if(n < 1)return false;
        if(n % 5 == 0)return isUgly(n/5);
        if(n % 3 == 0)return isUgly(n/3);
        if(n % 2 == 0)return isUgly(n/2);
        return false;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            System.out.println(new IsUgly263().isUgly(n));

        }
    }
}
