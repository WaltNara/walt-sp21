package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {
    AListNoResizing<Integer> L = new AListNoResizing<>();
    L.addLast(4);
    L.addLast(5);
    L.addLast(6);
    BuggyAList<Integer> B = new BuggyAList<>();
    B.addLast(4);
    B.addLast(5);
    B.addLast(6);
    assertEquals(L.size(), B.size());
    assertEquals(L.removeLast(), B.removeLast());
    assertEquals(L.removeLast(), B.removeLast());
    assertEquals(L.removeLast(), B.removeLast());
  }

  @Test
  public void randomizedtest() {
    AListNoResizing<Integer> L = new AListNoResizing<>();
    BuggyAList<Integer> B = new BuggyAList<>();

    int N = 20000;
    for (int i = 0; i < N; i += 1) {
        int operationNumber = StdRandom.uniform(0, 4);
        if (operationNumber == 0) {
            // addLast
            int randVal = StdRandom.uniform(0, 100);
            L.addLast(randVal);
            B.addLast(randVal);
            System.out.println("addLast(" + randVal + ")");
            
        } else if (operationNumber == 1) {
            // size
            int size = L.size();
            int sizeB = B.size();
            assertEquals(size, sizeB);
            System.out.println("size: " + size);
        } else if (operationNumber == 2) {
            // getLast
            if (L.size() == 0) {
                continue;
            }
            int last = L.getLast();
            int lastB = B.getLast();
            assertEquals(last, lastB);
            System.out.println("getLast: " + last);
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
