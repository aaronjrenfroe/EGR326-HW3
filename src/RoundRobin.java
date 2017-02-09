
import java.util.*;

/**
 * Created by Aaron Renfroe on 1/24/17.
 * EGR 326
 * Assignment 3 Restaurant
 */
public final class RoundRobin<T> extends LinkedList {//implements Iterable<T> {


    public RoundRobin() {
        super();

    }

    public void add(Iterator <T> i, T e){
        if (super.size() >0) {
            int index = super.indexOf(i.next());
            super.add(index, e);
            if (super.size() >= 1) {
                Iterator newIterator = this.iterator();

                for (int j = 0; j < index; j++) {
                    newIterator.next();
                }
                i = newIterator;
            }

        }else{
            super.add(e);
            i = this.iterator();
        }

    }
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