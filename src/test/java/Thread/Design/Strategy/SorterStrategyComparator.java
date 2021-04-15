package Thread.Design.Strategy;

import java.util.Arrays;
import java.util.Comparator;

// 可以传入多个比较器进行比较 也可以传入不同的类型
/*
    策略模式的重心

　　策略模式的重心不是如何实现算法，而是如何组织、调用这些算法，从而让程序结构更灵活，具有更好的维护性和扩展性。

    策略模式一个很大的特点就是各个策略算法的平等性。对于一系列具体的策略算法，大家的地位是完全一样的，正因为这个平等性，
    才能实现算法之间可以相互替换。所有的策略算法在实现上也是相互独立的，相互之间是没有依赖的。
    所以可以这样描述这一系列策略算法：策略算法是相同行为的不同实现。

    运行时策略的唯一性：运行期间，策略模式在每一个时刻只能使用一个具体的策略实现对象，虽然可以动态地在不同的策略实现中切换，但是同时只能使用一个。

    策略模式的优点
　　（1）策略模式提供了管理相关的算法族的办法。策略类的等级结构定义了一个算法或行为族。恰当使用继承可以把公共的代码移到父类里面，从而避免代码重复。

　　（2）使用策略模式可以避免使用多重条件(if-else)语句。多重条件语句不易维护，它把采取哪一种算法或采取哪一种行为的逻辑与算法或行为的逻辑混合在一起，
        统统列在一个多重条件语句里面，比使用继承的办法还要原始和落后。

    策略模式的缺点
　　（1）客户端必须知道所有的策略类，并自行决定使用哪一个策略类。这就意味着客户端必须理解这些算法的区别，以便适时选择恰当的算法类。
        换言之，策略模式只适用于客户端知道算法或行为的情况。

　　（2）由于策略模式把每个具体的策略实现都单独封装成为类，如果备选的策略很多的话，那么对象的数目就会很可观。
* */
public class SorterStrategyComparator<T> {
    // comparator接口是一种策略模式  而comparable接口不是一种策略模式

    public  void sort(T[] arr,Comparator<T> comparator){
        quickSort(arr,0,arr.length-1,comparator);
    }

    public  void quickSort(T[] arr,int L, int R,Comparator<T> comparator){
        if(L >= R)return;
        int l = L,r = R;
        // [0,1] ->  [L,R+1) -> [L,R]
        int index = (int)((R - L +1) * Math.random() + L);
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
