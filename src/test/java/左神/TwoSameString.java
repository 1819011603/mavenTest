package 左神;

import java.util.Arrays;

public class TwoSameString {
    public String twoSameString(String s){
        int [] next = getNext(s+"1");
        System.out.println(Arrays.toString(next));
        if(next[s.length()] > 0)
        return s + s.substring(next[s.length()]);
        return s + s;
    }
    public int[] getNext(String s){
        int[] next = new int[s.length()];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < s.length()-1){
            if(j == -1 || s.charAt(i) == s.charAt(j)){
                next[++i] = ++j;
            }else {
                j = next[j];
            }
        }
        return next;
    }
    public static void main(String[] args) {
        System.out.println(new TwoSameString().twoSameString("abacabac"));

    }
}
