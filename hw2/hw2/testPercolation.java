package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class testPercolation {
    @Test
    public void testGeneral(){
        Percolation p =new Percolation(3);
        assertEquals(0,p.numberOfOpenSites());
        assertEquals(false, p.isFull(0,0));
        assertEquals(false, p.isOpen(0,0));
        assertEquals(false, p.percolates());
        p.open(0,0);
        assertEquals(true, p.isOpen(0,0));
        p.open(1,0);
        assertEquals(true, p.isOpen(1,0));
        p.open(2,0);
        assertEquals(true, p.isOpen(2,0));
        assertEquals(true, p.percolates());
    }
    @Test
    public void testPercolates(){
        Percolation p3 =new Percolation(3);
        assertEquals(false, p3.percolates());
        p3.open(0,0);
        p3.open(1,0);
        p3.open(1,1);
        p3.open(2,1);
        p3.open(2,2);
        assertEquals(true, p3.percolates());
    }
}
