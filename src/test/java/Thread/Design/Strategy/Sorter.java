package Thread.Design.Strategy;

import java.util.Arrays;

// 原始的
public class Sorter {
    public static void sort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr,int L, int R){
        if(L >= R)return;
        int l = L,r = R;
        int index = (int)((R - L ) * Math.random() + L);
        swap(arr,l,index);
        int temp = arr[l];
        while (l < r){
            while (l < r && arr[r] >= temp)r--;
//            if(l == r)break;
            arr[l] = arr[r];
            while (l < r && arr[l] <= temp)l++;
            arr[r] = arr[l];

        }
        arr[l] = temp;
        quickSort(arr,L,l-1);
        quickSort(arr,l+1,R);
     }

    static void swap(int[] arr, int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {9,4,5,67,56,6,7,8,4};

        Sorter.sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
