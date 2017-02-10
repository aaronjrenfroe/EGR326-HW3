import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AaronR on 2/9/17.
 * for ?
 */
public class PartyTest {
    @Test
    public void getName() throws Exception {
        Party bob = new Party("Bob".toUpperCase(), 4);
        assertTrue(bob.getName().equals("Bob".toUpperCase()));
    }

    @Test
    public void getSize() throws Exception {

    }

    @Test
    public void toStringTest() throws Exception {

    }

    @Test
    public void hashCodeTest() throws Exception {

    }

    @Test
    public void equalsTest() throws Exception {

    }

}