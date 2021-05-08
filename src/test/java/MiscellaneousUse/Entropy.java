package MiscellaneousUse;

import java.util.Arrays;
import java.util.Scanner;

public class Entropy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()){
            double ans = 0;
            String s= in.nextLine();
            String[] strings = s.split(" ");
            int[] ints = new int[strings.length];
            int all = 0;
            for (int i = 0; i < ints.length; i++) {
                int l = strings[i].length();

                int k = 0;
                while (k < l){

                    ints[i] *=  10;
                    ints[i] += strings[i].charAt(k)-'0';
                    k++;
                }


                all+=ints[i];
            }
            for (int i = 0; i < ints.length; i++) {
                ans+= (double) ints[i]/all * Math.log((double) ints[i]/all)/Math.log(2);

            }

            System.out.printf("%.3f\n",-ans);

        }
    }
}
