public class LinkedListDeque<T> {
    /** invariants:
     *  the first one is sentinel.next
     *  the last one is sentinel.prev
     */
    private class ItemNode{
        public T item;
        public ItemNode next;
        public ItemNode prev;
        public ItemNode(T i, ItemNode n, ItemNode p){
            item = i;
            next = n;
            prev = p;
        }
    }
    private int size;
    private ItemNode sentinel;

    /** constructor */
    public LinkedListDeque(T item){
        sentinel = new ItemNode(null, null, null);
        sentinel.next = new ItemNode(item,sentinel,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    /** empty constructor */
    public LinkedListDeque(){
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel.next;
        size = 0;
    }
    /**  Adds an item of type T to the front of the deque */
    public void addFirst(T item){
        sentinel.next.prev = new ItemNode(item, sentinel.next, sentinel);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }
    /** Adds an item of type T to the back of the deque */
    public void addLast(T item){
        sentinel.prev.next = new ItemNode(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty(){
        if (sentinel.next == sentinel){
            return true;
        }
        return false;
    }
    /** Returns the number of items in the deque */
    public int size(){
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space */
    public void printDeque(){
        /* can we don't use if ? */
        if (isEmpty()){
            return;
        }
        ItemNode p = sentinel;
        while (p.next != sentinel){
            System.out.print(p.next.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
    }
    /** Removes and returns the item at the front of the deque. If no such item exists, returns null */
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T rv = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return rv;
    }
    /** Removes and returns the item at the back of the deque. If no such item exists, returns null */
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T rv = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return rv;
    }
    /** Gets the item at the given index, where 0 is the front, 1 is the next item,and so forth.
     *  If no such item exists, returns null. Must not alter the deque!
     *  get use iteration, not recursion */
    public T get(int index){
        ItemNode p = sentinel;
        /* can be optimized using both forward and backward iteration */
        int i = 0;
        while (p.next != sentinel){
            if (i == index){
                return p.next.item;
            }
            p = p.next;
            i += 1;
        }
        return null;
    }


    /* not implemented yet */
    /** get the item at the index-th position */
    private T forwardGet(int index){
        return null;
    }
    /** get the item at the last index-th position */
    private T backwardGet(int index){
        return null;
    }
    /** Same as get, but uses recursion */
    public T getRecursive(int index){
        return null;
    }


}
