import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFind {
    private int [] items;
    //private int size;
    /**
     * Creates a UnionFind data structure holding n vertices.
     * Initially, all vertices are in disjoint sets
     */
    public UnionFind(int n) {
        if(n <=0 ){
            throw new IllegalArgumentException("n must be positive");
        }
        items = new int[n];
        for (int i = 0;i < items.length; i += 1){
            items[i] = -1;
        }
        //size = n;
    }

    /**
     * Throws an exception if v1 is not a valid index
     */
    public void validate(int v1) {
        if(v1 < 0 || v1 >= items.length){
            throw new IllegalArgumentException("v1 is not a valid index");
        }
    }

    /**
     * Returns the size of the set v1 belongs to
     */
    public int sizeOf(int v1) {
        validate(v1);
        int hold = items[v1];
        if (hold < 0){
            return - hold;
        }
        return sizeOf(hold);
    }

    /**
     * Returns the parent ?parent's index? of v1.
     * If v1 is the root of a tree, returns the negative size of the tree for which v1 is the root
     */
    public int parent(int v1) {
        validate(v1);
        if (items[v1] < 0){
            return - v1;
        }
        return items[v1];
    }

    /**
     * Returns true if nodes v1 and v2 are connected
     */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);

        return find(v1) == find(v2);
    }

    /** Connects two elements v1 and v2 together
     *
     */
    public void union(int v1, int v2){
        validate(v1);
        validate(v2);

        if (v1 == v2){
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        if(sizeOf(root1) < sizeOf(root2)){
            items[root1] = root2;
        }
        items[root2] = root1;
    }

    /**
     * Returns the root of the set v1 belongs to
     */
    public int find(int v1){
        // TODO:Path-compression
        validate(v1);
        int hold = items[v1];
        if (hold < 0){
            return v1;
        }
        items[v1] = find(hold);
        return items[v1];
    }

}
