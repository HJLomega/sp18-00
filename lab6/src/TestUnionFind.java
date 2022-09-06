import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testValidate(){
        UnionFind uf = new UnionFind(8);
        uf.validate(5);
        //uf.validate(-1);
        //uf.validate(9);

    }
    @Test
    public void testSizeOf(){
        UnionFind uf = new UnionFind(8);
        assertEquals(1, uf.sizeOf(1));
    }
    @Test
    public void testUnoin(){
        UnionFind uf = new UnionFind(8);
        assertEquals(false, uf.connected(5,0));
        uf.union(5,0);
        assertEquals(true, uf.connected(5,0));
        uf.union(5,6);
        assertEquals(true, uf.connected(5,0));
        assertEquals(true, uf.connected(5,6));
        assertEquals(true, uf.connected(0,6));
    }

}
