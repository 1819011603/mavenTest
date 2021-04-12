package Thread.Design.Strategy;

import java.util.Comparator;

public class CatHeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        if(o1.height < o2.height)return -1;
        else if( o1.height > o2.height)return 1;
        return 0;
    }
}
