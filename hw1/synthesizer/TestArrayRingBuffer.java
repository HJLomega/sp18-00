package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        assertEquals(10, arb.capacity());
        assertEquals(true, arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(3, arb.fillCount());
        assertEquals(1, (int) arb.dequeue());
        assertEquals(2, arb.fillCount());
        assertEquals(2, (int) arb.peek());
        assertEquals(2, arb.fillCount());

    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        int i = 0;
        int[] nums = {1, 2, 3};
        for (int num : arb) {
            assertEquals(nums[i], num);
            i += 1;
        }

        for (int x : arb) {
            for (int y : arb) {
                System.out.println("x: " + x + ", y:" + y);
            }
        }


    }


    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
