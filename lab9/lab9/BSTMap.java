package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author ljh
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }

        /* Return true if a Node is a leaf */
        private boolean isLeaf() {
            return left == null && right == null;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cp = key.compareTo(p.key);
        if (cp > 0) {
            return getHelper(key, p.right);
        } else if (cp < 0) {
            return getHelper(key, p.left);
        }
        return p.value;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        int cp = key.compareTo(p.key);
        if (cp > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (cp < 0) {
            p.left = putHelper(key, value, p.left);
        }
        return p;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new java.util.TreeSet<>();
        keySet_helper(root, keys);
        return keys;
    }

    private void keySet_helper(Node p, Set<K> keys) {
        if (p == null) {
            return;
        }
        if (p.isLeaf()) {
            keys.add(p.key);
        } else {
            keySet_helper(p.left, keys);
            keys.add(p.key);
            keySet_helper(p.right, keys);
        }
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        V[] rv = (V[]) new Object[1];
        rv[0] = null;
        root = remove_helper(key, root, rv);
        return rv[0];
    }

    /* Hibbard deletion
       rvContainer is a 1-size array which contains the removed value
     */
    private Node remove_helper(K key, Node p, V[] rvContainer) {
        if (p == null) {
            return null;
        }

        int cp = key.compareTo(p.key);
        if (cp > 0) {
            p.right = remove_helper(key, p.right, rvContainer);
            return p;
        } else if (cp < 0) {
            p.left = remove_helper(key, p.left, rvContainer);
            return p;
        }
        // delete p
        rvContainer[0] = p.value;
        size -= 1;
        int child_count = 0;
        /* child_count = 0 : 0 child
           child_count = 1 : left child only
           child_count = 10 : right child only
           child_count = 11 : two children only
         */
        if (p.left != null) {
            child_count += 10;
        }
        if (p.right != null) {
            child_count += 1;
        }

        if (child_count == 0) {
            return null;
        } else if (child_count == 11) {
            Node predecessor = findPredecessor(p);
            p.key = predecessor.key;
            p.value = predecessor.value;
            V[] temp = (V[]) new Object[1];
            remove_helper(predecessor.key, predecessor , temp);
            return p;
        } else {
            if (child_count == 10) {
                return p.left;
            } else {
                return p.right;
            }
        }
    }

    private Node findPredecessor(Node p){
        p = p.left;
        while (p.right != null){
            p = p.right;
        }
        return p;
    }
    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
