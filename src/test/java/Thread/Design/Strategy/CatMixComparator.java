package Thread.Design.Strategy;

import java.util.Comparator;

public class CatMixComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        if(o1.height != o2.height){
            return o1.height < o2.height?-1:1;
        }
        if(o1.weight == o2.weight)return 0;
        return o1.weight < o2.weight?1:-1;
    }
}
