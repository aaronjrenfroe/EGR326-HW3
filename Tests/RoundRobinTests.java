import org.junit.Test;
import org.junit.Assert;


import java.util.Iterator;


/**
 * Created by AaronR on 2/9/17.
 * for ?
 */
public class RoundRobinTests {
    @Test
    public void addAndRemoveTests(){
        RoundRobin rr = new RoundRobin();
        Iterator ir = rr.iterator();
        for (int i = 0; i < 10; i++) {
            rr.add(i);
        }

        for (int i = 0; i < 2*rr.size(); i++) {
            Assert.assertTrue(ir.hasNext());
            ir.next();
        }
    }
}
