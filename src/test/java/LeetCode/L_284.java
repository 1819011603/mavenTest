package LeetCode;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author 18190
 * @Date: 2021/10/5  9:52
 * @VERSION 1.0
 */
public class L_284 {

    public static void main(String[] args) {

    }
}
class PeekingIterator implements Iterator<Integer> {

    Deque<Integer> deque = new LinkedList<>();

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        while (iterator.hasNext()){
            deque.addLast(iterator.next());
        }
        System.out.println(deque);

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return deque.getFirst();
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return deque.pollFirst();
    }

    @Override
    public boolean hasNext() {
        return deque.size() != 0;
    }
}