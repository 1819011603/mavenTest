package LeetCode;


import Thread.Design.Strategy.Sorter;
import Thread.Design.Strategy.SorterStrategyComparator;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber179 {
    private static String a1;
    private static String a2;
    public String largestNumber(int[] nums){
        StringCompare[] stringCompares = new StringCompare[nums.length];

        for (int i = 0; i < nums.length; i++) {
            stringCompares[i] = new StringCompare(nums[i]);
        }

        Arrays.sort(stringCompares);
        StringBuilder s = new StringBuilder();
        for (StringCompare stringCompare:stringCompares){
            s.append(stringCompare.getI());
        }
        if(stringCompares[nums.length-1].getI().equals("0") && stringCompares[0].getI().equals("0") )return "0";
        return new String(s);
    }
    public static void quickSort(long[] arr, int L, int R, Comparator<Long> comparator){
        if(L >= R)return;
        int l = L,r = R;
        int index = (int)((R - L +1) * Math.random() + L);
        swap(arr,l,index);
        long temp = arr[l];
        while (l < r){

            while (l < r && comparator.compare(temp,arr[r]) <= 0){
                r--;
            }
//            if(l == r)break;
            arr[l] = arr[r];
            while (l < r && comparator.compare(temp,arr[l]) >= 0)l++;
            arr[r] = arr[l];

        }
        arr[l] = temp;

        quickSort(arr,L,l-1,comparator);
        quickSort(arr,l+1,R,comparator);
    }

    static void swap(long[] arr, int i,int j){
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public String largestNumber1(int[] num){
        long[] nums = new long[num.length];
        for (int i = 0; i < num.length; i++){
            nums[i] = num[i];
        }

        quickSort(nums,0,nums.length-1,(x, y)->{
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return Long.compare(sx * y + x,sy * x + y) ;

        });
        if(nums[0] == 0)return "0";
        StringBuilder s = new StringBuilder();
        for (long l: nums){
            s.append(l);
        }
        return new String(s);
    }

    public static void main(String[] args) {
        int[] nums = {99991,9,7,5,6,98};
        System.out.println("999919".compareTo("999991"));
        System.out.println(new LargestNumber179().largestNumber(nums));
        System.out.println(new LargestNumber179().largestNumber1(nums));
    }
    static class StringCompare implements Comparable<StringCompare>{
        String i;

        public StringCompare(int i) {
            this.i = String.valueOf(i);
        }

        public String getI() {
            return i;
        }

        @Override
        public int compareTo(StringCompare o) {
            a1 = this.i+ o.i;
            a2 =  o.i + this.i;
            return a2.compareTo(a1);
        }

        @Override
        public String toString() {
            return "StringCompare{" +
                    "i=" + i +
                    '}';
        }
    }
}
