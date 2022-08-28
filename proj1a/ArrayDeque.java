public class ArrayDeque<T> {
    /** invariants
     * the next one's inedx of items[i] is items[getNextIndex(i)]
     * the prev one's inedx of items[i] is items[getPrevIndex(i)]
     */
    /**
     * 'index' is the index of items Array
     * 'dequeIndex is the index of ArrayDeque
     */
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;


    /**
     * constructor
     * */
    public ArrayDeque(T item) {
        items = (T[]) new Object[8];
        items[4] = item;
        nextFirst = 3;
        nextLast = 5;
        size = 1;
    }

    /**
     * empty constructor
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];

        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    /**
     * get next index(right direction) in a circular Array
     */
    private int getNextIndex(int index) {
        return (index + 1 + items.length) % items.length;
    }

    /**
     * get prev index(left direction) in a circular Array
     */
    private int getPrevIndex(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** resize the Deque ,the Factor is 2 or 0.5 */
    private void resize(double factor){
       T[] temp = (T[]) new Object[(int) (items.length * factor)];
        for(int i = 0; i < size; i += 1){
            temp[temp.length / 4 + i] = get(i);
        }
        nextFirst = temp.length / 4 - 1 ;
        nextLast = temp.length / 4 + size ;
        items = temp;
    }

    /** check if Array needs to expand , expand when needed */
    private void expandCheck(){
        if (size == items.length){
            resize(2);
        }
    }
    /** check if Array needs to expand .
     * For arrays of length 16 or more,
     * usage factor should always be at least 25%.
     * For smaller arrays, usage factor can be arbitrarily low  */
    private void cutCheck(){
        if (items.length < 16) {
            return;
        }
        while (size * 4 <= items.length){
            resize(0.5);
        }
    }
    /**
     * Adds an item of type T to the front of the deque
     */
    public void addFirst(T item) {
        expandCheck();
        items[nextFirst] = item;
        nextFirst = getPrevIndex(nextFirst);
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque
     */
    public void addLast(T item) {
        expandCheck();
        items[nextLast] = item;
        nextLast = getNextIndex(nextLast);
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
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space
     */
    public void printDeque() {
        if (isEmpty()){
            System.out.print('\n');
            return;
        }
        System.out.print(get(0));
        for (int i = 1; i < size; i+=1){
            System.out.print(' ');
            System.out.print(get(i));
        }
        System.out.print('\n');
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     */
    public T removeFirst() {
        int first = getNextIndex(nextFirst);
        T rv = items[first];
        items[first] = null;
        size -= 1;
        nextFirst = first;
        cutCheck();
        return rv;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null
     */
    public T removeLast() {
        int last = getPrevIndex(nextLast);
        T rv = items[last];
        items[last] = null;
        size -= 1;
        nextLast = last;
        cutCheck();
        return rv;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * get use iteration, not recursion
     */
    public T get(int dequeIndex) {
        if (dequeIndex < 0 || dequeIndex >= size) { //range check
            return null;
        }
        return items[(nextFirst + dequeIndex + 1) % items.length];
    }
}