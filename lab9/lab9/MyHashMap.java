package lab9;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 *
 * @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    // TODO : ?maybe loadFctor shuld be double?
    public int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /*  resize the array of buckets anytime the load factor exceeds MAX_LF */
    private void resize(int newSize) {
        ArrayMap<K, V>[] old = buckets;
        buckets = new ArrayMap[newSize];
        this.clear();
        for (ArrayMap<K,V> map : old){
            for (K key : map){
                put(key, map.get(key));
            }
        }
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /**
     * Computes the hash function of the given key. Consists of
     * computing the hashcode, followed by modding by the number of buckets.
     * To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Map61B<K, V> map = buckets[hash(key)];
        return map.get(key);
    }

    /* Associates the specified value with the specified key in this map.
     * putting with existing key updates the value
     */
    @Override
    public void put(K key, V value) {
        Map61B<K, V> map = buckets[hash(key)];
        if (!map.containsKey(key)) {
            size += 1;
        }
        map.put(key, value);

        if (loadFactor() > MAX_LF){
            resize(2 * size);
        }
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
        Set<K> set = new HashSet<K>();
        for (K key : this) {
            set.add(key);
        }
        return set;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        Map61B<K, V> map = buckets[hash(key)];
        return map.remove(key);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        Map61B<K, V> map = buckets[hash(key)];
        return map.remove(key, value);
    }

    @Override
    public Iterator<K> iterator() {
        return new hashSetIterator();
    }

    private class hashSetIterator implements Iterator<K> {

        private int presentIndex;
        private List<K> cacheList;

        public hashSetIterator() {
            presentIndex = -1;
            cacheList = new LinkedList<>();
        }

        @Override
        public K next() {
            Iterator<K> iterator = cacheList.iterator();
            if (iterator.hasNext()) {
                K rv = iterator.next();
                cacheList.remove(rv);
                return rv;
            }
            presentIndex = searchNextBucket();
            for (K key : buckets[presentIndex]) { // copy
                cacheList.add(key);
            }
            return next();
        }


        /* search next bucket , return its index .
           if it doesn't find the next bucket,throw java.util.NoSuchElementException
         */
        private int searchNextBucket() {
            for (int i = presentIndex + 1; i < buckets.length; i += 1) {
                if (buckets[i].size != 0) {
                    return i;
                }
            }
            throw new java.util.NoSuchElementException("HashMap overflow");
        }

        @Override
        public boolean hasNext() {
            if (cacheList.size() != 0) {
                return true;
            }
            try {
                searchNextBucket();
            } catch (java.util.NoSuchElementException e) {
                return false;
            }
            return true;
        }
    }
}
