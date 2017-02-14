
import java.util.*;

/**@author Aaron Renfroe
 * Created by Aaron Renfroe on 1/24/17.
 * EGR 326
 * Assignment 3 Restaurant
 */
public final class RoundRobin<T> extends LinkedList {//implements Iterable<T> {


    public RoundRobin() {
        super();

    }
    /**
     * Iterator override hasNext() to always return true if the list size is greater than 0
     *  and to return the first element when the end is reached after calling next()
     * @return the Overrided Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (RoundRobin.this.size() > 0) {
                    return true;
                }else return false;
            }
            @Override
            public T next() {
                if (RoundRobin.this.size() > 1) {
                    T res = (T) RoundRobin.this.get(index);
                    index = (index + 1) % RoundRobin.this.size();
                    return (T) res;
                }else return (T) RoundRobin.this.get(0);
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}