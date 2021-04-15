package Thread.Design.Strategy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

// 只能适合单个比较器  可以传入各种类型
public class SorterStrategy<T extends Comparable<T>> {
    public  void sort(T[] arr){
        quickSort(arr,0,arr.length-1);
    }

    public void quickSort(T[] arr,int L, int R){
        if(L >= R)return;
        int l = L,r = R;
        // [0,1] ->  [L,R+1) -> [L,R]
        int index = (int)((R - L+1 ) * Math.random() + L);
        swap(arr,l,index);
        T temp = arr[l];
        while (l < r){
            while (l < r && arr[r].compareTo(temp) >= 0)r--;
//            if(l == r)break;
            arr[l] = arr[r];
            while (l < r && arr[l].compareTo(temp) <= 0 )l++;
            arr[r] = arr[l];

        }
        arr[l] = temp;
        quickSort(arr,L,l-1);
        quickSort(arr,l+1,R);
    }

    private void swap(T[] arr, int i,int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Cat[] cats = {new Cat(3,3),new Cat(4,4),new Cat(2,2)};
        Dog[] dogs = {new Dog(4),new Dog(7),new Dog(5),new Dog(1)};
        new SorterStrategy<Cat>().sort(cats);

        System.out.println(Arrays.toString(cats));
        new SorterStrategy<Dog>().sort(dogs);
        System.out.println(Arrays.toString(dogs));
    }

}
