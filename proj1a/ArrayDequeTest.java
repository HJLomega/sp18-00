import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDequeTest {
    public static void main(String[] args) {
        System.out.println((0 + 1 + 8) % 8);
    }
    @Test
    public void resizeTest(){
        System.out.println("expand test:");
        ArrayDeque<String> A = new ArrayDeque();
        for (int i = 0; i < 33 ; i += 1){
            if( i % 2 == 0){
                A.addFirst(i + "");
            }else {
                A.addLast(i + "");
            }
        }
        A.printDeque();
        System.out.println("cut test:");
        for (int i = 0; i < 30 ; i += 1){
            A.removeFirst();
        }
    }
}
