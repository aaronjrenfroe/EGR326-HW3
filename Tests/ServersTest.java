import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AaronR on 2/9/17.
 * for ?
 */
public class ServersTest {

    private static Servers addServerHelper(){
        Servers s = new Servers();
        for (int i = 0; i < 10; i++) {
            s.addServer();
        }
        return s;
    }
    @Test
    public void addServer() throws Exception {
        Servers s = new Servers();
        for (int i = 0; i < 10; i++) {
            s.addServer();
        }
        Assert.assertEquals("Server count should be 10",10,s.numberOfServers());//("Server count should be 10",10==s.numberOfServers(), true);
    }

    @Test
    public void hasServers() throws Exception {
        Servers s1 = addServerHelper();
        Servers s2 = new Servers();
        Assert.assertTrue("Has Servers", s1.hasServers());
        Assert.assertTrue("Does not have Servers", !s2.hasServers());
    }

    @Test
    public void numberOfServers() throws Exception {
        Servers s1 = addServerHelper();
        Assert.assertTrue("Has 10 servers", s1.numberOfServers() == 10);
        s1.addServer();
        Assert.assertTrue("Has 11 servers", s1.numberOfServers() == 11);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getNextNull() throws Exception {
        Servers s2 = new Servers();
        Assert.assertFalse(s2.hasServers());
        s2.getNext();
    }
    @Test
    public void getNext() throws Exception {
        Servers s1 = addServerHelper();
        Assert.assertTrue(s1.hasServers());
        int j = s1.numberOfServers();
        for (int i = 0; i < j; i++) {
            System.out.println(s1.getNext());
        }
    }



    @Test
    public void assignToServer() throws Exception {

    }

    @Test
    public void cashOut() throws Exception {
        Servers s2 = new Servers();
        s2.addServer();
        s2.addServer();
        s2.assignToServer(new Table(1,4));
        s2.assignToServer(new Table(1,4));
        s2.getNext();
        //s2.cashOut();
        System.out.println(s2.cashOut());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cashOutIllegal() throws Exception {
        Servers s2 = new Servers();
        s2.addServer();
        s2.addServer();
        s2.assignToServer(new Table(1,4));
        s2.assignToServer(new Table(1,4));
        s2.cashOut();
        s2.cashOut();
    }
    @Test(expected = NullPointerException.class)
    public void cashOutNull() throws Exception {
        Servers s2 = new Servers();
        s2.cashOut();
    }

    @Test
    public void getServerForTable() throws Exception {
        Table t0 = new Table(1,4);
        Table t1 = new Table(2,5);
        Table t2 = new Table(3,6);
        Table t3 = new Table(4,7);
        Table t4 = new Table(5,3);
        Table t5 = new Table(6,4);
        Table[] tableList = new Table[]{t0,t1,t2,t3,t4,t5};
        Servers s1 = addServerHelper();
        for (int i = 0; i < 6; i++) {
            s1.assignToServer(tableList[i]);
            s1.getServerForTable(tableList[i]);
        }
        Assert.assertTrue(true);
    }

    @Test(expected = NullPointerException.class)
    public void getServerForTableNull() throws Exception {

        Servers s2 = new Servers();
        s2.getServerForTable(new Table(2,4));


    }
}