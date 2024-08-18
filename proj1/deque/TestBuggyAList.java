package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  

  @Test
  public void randomizedtest() {
    LinkedListDeque<Integer> L = new LinkedListDeque<>();
    ArrayDeque<Integer> B = new ArrayDeque<>();

    int N = 10000;
    for (int i = 0; i < N; i += 1) {
        int operationNumber = StdRandom.uniform(0, 4);
        if (operationNumber == 0) {
            // addLast
            int randVal = StdRandom.uniform(0, 100);
            L.addLast(randVal);
            B.addLast(randVal);
            System.out.println("addLast(" + randVal + ")");
            
        } else if (operationNumber == 1) {
            // add or remove first
            int randVal = StdRandom.uniform(0, 1);
            if (randVal == 0) {
                // addFirst
                int randVal2 = StdRandom.uniform(0, 100);
                L.addFirst(randVal2);
                B.addFirst(randVal2);
                System.out.println("addFirst(" + randVal2 + ")");
            } else {
                // removeFirst
                if (L.size() == 0) {
                    continue;
                }
                int first = L.removeFirst();
                int firstB = B.removeFirst();
                assertEquals(first, firstB);
                System.out.println("removeFirst: " + first);
            }
        } else if (operationNumber == 2) {
            // getRandom
            if (L.size() == 0) {
                continue;
            }
            int index = StdRandom.uniform(0, L.size());
            int get = L.get(index);
            int getB = B.get(index);
            assertEquals(get, getB);
            System.out.println("get(" + index + "): " + get);
        } else {
            // removeLast
            if (L.size() == 0) {
                continue;
            }
            int last = L.removeLast();
            int lastB = B.removeLast();
            assertEquals(last, lastB);
            System.out.println("removeLast: " + last);
        }
    
    }
  }
}
