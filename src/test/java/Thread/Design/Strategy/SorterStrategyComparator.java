package Thread.Design.Strategy;

import java.util.Arrays;
import java.util.Comparator;

// 可以传入多个比较器进行比较 也可以传入不同的类型
public class SorterStrategyComparator<T> {
    public  void sort(T[] arr,Comparator<T> comparator){
        quickSort(arr,0,arr.length-1,comparator);
    }

    public  void quickSort(T[] arr,int L, int R,Comparator<T> comparator){
        if(L >= R)return;
        int l = L,r = R;
        int index = (int)((R - L ) * Math.random() + L);
        swap(arr,l,index);
        T temp = arr[l];
        while (l < r){
            while (l < r && comparator.compare(temp,arr[r]) <= 0)r--;
//            if(l == r)break;
            arr[l] = arr[r];
            while (l < r && comparator.compare(temp,arr[l]) >= 0 )l++;
            arr[r] = arr[l];

        }
        arr[l] = temp;
        quickSort(arr,L,l-1,comparator);
        quickSort(arr,l+1,R,comparator);
    }

    private void swap(T[] arr, int i,int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        System.out.println("Cat Comparator");
        System.out.println("Height sort");
        Cat[] cats = {new Cat(1,4),new Cat(3,2),new Cat(2,1),new Cat(-1,9),new Cat(7,2),new Cat(1,2),new Cat(1,3)};

        SorterStrategyComparator<Cat> comparable = new SorterStrategyComparator<>();


        comparable.sort(cats,new CatHeightComparator());
        System.out.println(Arrays.toString(cats));
        comparable.sort(cats,new CatWeightComparator());
        System.out.println("Weight sort");
        System.out.println(Arrays.toString(cats));

        comparable.sort(cats,new CatMixComparator());
        System.out.println("高度从小到大  高度相等的按重量从大到小排序");
        System.out.println(Arrays.toString(cats));

        System.out.println();
        System.out.println("Dog Comparator");
        System.out.println("food 从小到大");
        Dog[] dogs = {new Dog(5),new Dog(6),new Dog(5),new Dog(3),new Dog(7)};


        SorterStrategyComparator<Dog> comparator = new SorterStrategyComparator<>();


        comparator.sort(dogs,new DogFoodComparator());
        System.out.println(Arrays.toString(dogs));

        System.out.println();
        System.out.println("lambda 表达式 实现Dog food 逆序" );
        comparator.sort(dogs,(o1,o2)-> Integer.compare(o2.food,o1.food));
        System.out.println(Arrays.toString(dogs));

        System.out.println("lambda 表达式 实现Dog food 顺序" );
        comparator.sort(dogs, Comparator.comparingInt(o -> o.food));
//        comparator.sort(dogs,(o1,o2)-> Integer.compare(o1.food,o2.food));
        System.out.println(Arrays.toString(dogs));

    }
}
