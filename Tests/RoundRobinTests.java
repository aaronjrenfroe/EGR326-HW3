import org.junit.Test;

import java.util.Iterator;

/**
 * Created by AaronR on 2/9/17.
 * for ?
 */
public class RoundRobinTests {
    @Test
    public void addAndRemoveTests(){
        RoundRobin<Integer> r = new RoundRobin();
        Iterator d = r.iterator();
        for (int i = 0; i < 10; i++) {
            r.add(d,i+1);

        }
        for (int i = 0; i < r.size(); i++) {
            System.out.println(d.next().toString());
        }
        r.remove();
        d = r.iterator();
        r.remove();
        d = r.iterator();

    }
}
