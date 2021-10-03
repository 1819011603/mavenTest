package LeetCode;

import java.util.HashMap;

/**
 * @author 18190
 * @Date: 2021/10/3  20:47
 * @VERSION 1.0
 */
public class L_166 {


    public String fractionToDecimal(int numerator, int denominators) {
        boolean fuhao = (numerator ^ denominators) < 0;

        if (numerator == 0 ||  numerator %  denominators  == 0){

            return String.valueOf((long) numerator / denominators);
        }else {
            StringBuilder ans = new StringBuilder();
            long denominator = Math.abs((long) denominators);
            long n1 =  Math.abs((long) numerator) % denominator;

            long temp = n1 * 10L;



            int i = 1;
            HashMap<Long,Integer> hashMap = new HashMap<>();

            hashMap.put(temp,i);
            while (true){
                while (temp < denominator){
                    ans.append(0);
                    i++;
                    temp *= 10;
                }
                ans.append(temp/denominator);
                temp %= denominator;

                temp *= 10;
                if (hashMap.containsKey(temp) || temp == 0){
                    break;
                }else hashMap.put(temp, ++i);



            }
            if (temp != 0)
            ans.insert(hashMap.get(temp)-1,"(");
            ans.insert(0,".");
            ans.insert(0,Math.abs(numerator/denominator));
            ans.insert(0,fuhao?"-":"" );
            if (temp != 0)
            ans.append(")");
            return ans.toString();

        }



    }

    public static void main(String[] args) {
//        System.out.println(new L_166().fractionToDecimal(-1,1<<31));
        System.out.println(new L_166().fractionToDecimal(-1546431,545337730));
        System.out.println(new L_166().fractionToDecimal(1<<31,1<<31));
        System.out.println(new L_166().fractionToDecimal(1,600));
        System.out.println(new L_166().fractionToDecimal(4,33333333));
        System.out.println(new L_166().fractionToDecimal(4,333));
        System.out.println(new L_166().fractionToDecimal(1,6));
        System.out.println(new L_166().fractionToDecimal(7,12));
        System.out.println(new L_166().fractionToDecimal(7,-12));
        System.out.println(new L_166().fractionToDecimal(72,-12));
        System.out.println(new L_166().fractionToDecimal(72,12));
        System.out.println(new L_166().fractionToDecimal(6,12));
    }
}
