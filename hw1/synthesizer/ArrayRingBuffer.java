// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

import org.junit.Test;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /**
     * The ArrayRingBuffer will improve runtime substantially
     * by using the ‘ring buffer’ data structure,
     * similar to the circular array from Project 1A
     */
    /**
     * variants
     * first : stores the index of the least recently inserted item
     * last : stores the index one beyond the most recently inserted item
     */
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private final T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = getNextIndex(last);
        fillCount += 1;
    }

    private int getNextIndex(int index) {
        if (index == capacity - 1) {
            return 0;
        }
        return index + 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T rv = rb[first];
        rb[first] = null;
        first = getNextIndex(first);
        fillCount -= 1;
        return rv;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        T rv = rb[first];
        return rv;
    }

    private class ARBIterator implements Iterator<T> {
        private int nextItem;

        public ARBIterator() {
            nextItem = first;
        }

        @Override
        public boolean hasNext() {
            return nextItem != last;
        }

        @Override
        public T next() {
            T rv = rb[nextItem];
            nextItem = getNextIndex(nextItem);
            return rv;
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }


}
