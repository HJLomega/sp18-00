public class LinkedListDeque<T> implements Deque<T> {
    /**
     * invariants:
     * the first one is sentinel.next
     * the last one is sentinel.prev
     */
    private class ItemNode {
        private T item;
        private ItemNode next;
        public ItemNode prev;

        public ItemNode(T i, ItemNode n, ItemNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private int size;
    private final ItemNode sentinel;


    /**
     * empty constructor
     */
    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel.next;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque
     */
    @Override
    public void addFirst(T item) {
        sentinel.next.prev = new ItemNode(item, sentinel.next, sentinel);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque
     */
    @Override
    public void addLast(T item) {
        sentinel.prev.next = new ItemNode(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space
     */
    @Override
    public void printDeque() {
        /* can we don't use if ? */
        if (isEmpty()) {
            return;
        }
        System.out.print(sentinel.next.item);
        ItemNode p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(' ');
            System.out.print(p.next.item);
            p = p.next;
        }
        System.out.print('\n');
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T rv = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return rv;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T rv = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return rv;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * get use iteration, not recursion
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) { //range check
            return null;
        }
        if (index < size / 2) {
            return forwardGet(index);
        } else {
            return backwardGet(size - 1 - index);
        }
    }

    /**
     * get the item at the index-th position
     */
    private T forwardGet(int index) {
        ItemNode p = sentinel;
        int i = 0;
        while (p.next != sentinel) {
            if (i == index) {
                return p.next.item;
            }
            p = p.next;
            i += 1;
        }
        return null;
    }

    /**
     * get the item at the last index-th position
     */
    private T backwardGet(int index) {
        ItemNode p = sentinel;
        int i = 0;
        while (p.prev != sentinel) {
            if (i == index) {
                return p.prev.item;
            }
            p = p.prev;
            i += 1;
        }
        return null;
    }

    /**
     * Same as get, but uses recursion
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) { //range check
            return null;
        }
        if (index < size / 2) {
            return forwardGetRecursive(index, sentinel);
        } else {
            return backwardGetRecursive(size - 1 - index, sentinel);
        }
    }

    /**
     * Same as forwardGet, but uses recursion
     */
    private T forwardGetRecursive(int index, ItemNode p) {
        if (index == 0) {
            return p.next.item;
        }
        return forwardGetRecursive(index - 1, p.next);
    }

    /**
     * Same as backwardGet, but uses recursion
     */
    private T backwardGetRecursive(int index, ItemNode p) {
        if (index == 0) {
            return p.prev.item;
        }
        return backwardGetRecursive(index - 1, p.prev);
    }
}
