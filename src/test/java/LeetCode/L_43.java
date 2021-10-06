package LeetCode;

import javafx.beans.binding.StringBinding;

import java.util.HashMap;

/**
 * @author 18190
 * @Date: 2021/10/5  18:53
 * @VERSION 1.0
 */
public class L_43 {
    HashMap<Integer,int[]> hashMap = new HashMap<>();
    public String multiply(String num1, String num2) {

        if ((num1.length() == 1 || num2.length() == 1) && (num1.charAt(0) == '0' || num2.charAt(0) == '0') )return "0";
        StringBuilder ans = new StringBuilder();

        int l1 = num1.length()-1;
        int l2 = num2.length()-1;
        int[][] retain = new int[10][l1 + 2];
        hashMap.put(0,retain[0]);
        int[] n1 = new int[num1.length()];
        int[] n2 = new int[num2.length()];
        int[] res = new int[num1.length()+num2.length()];

        for (int i = 0;i <= l1; i++){
            n1[l1-i] = num1.charAt(i)-'0';

        }
        for (int i = 0;i <= l2; i++){
            n2[l2-i] = num2.charAt(i)-'0';
        }
        add(n1,n2,retain,res,0);
        for (int i = 1; i < res.length; i++){
            res[i] += res[i-1]/10;
            res[i-1]%=10;
        }
        if (res[res.length-1] != 0){
            ans.append(res[res.length-1]);
        }
        for (int i = res.length-2; i >= 0; i--){
            ans.append(res[i]);
        }
        return ans.toString();
    }


    public void add(int[] n1,int[] n2,int[][] retain,int[] res,int i){
        if (i == n2.length)return;
        int s= i;
        i = n2[i];
        if (hashMap.containsKey(i)) {
            int[] ints = hashMap.get(i);
            for (int j = 0; j < ints.length; j++){
                res[j+s] += ints[j];
            }

        }else {
            int j;
            for (j = 0; j < n1.length; j++){
                retain[i][j] = i*n1[j];
            }

            hashMap.put(i,retain[i]);
            int[] ints =retain[i];
            for (j = 0; j < ints.length; j++){
                res[j+s]+= ints[j];
            }
        }
        add(n1,n2,retain,res,s+1);

    }


    public static void main(String[] args) {
        System.out.println(new L_43().multiply("182","344"));
        System.out.println(new L_43().multiply("456","123"));
        System.out.println(new L_43().multiply("0","123"));
        System.out.println(new L_43().multiply("456","0"));

    }
}
