package tester;
import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

public class TestArrayDequeEC {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        int testNum = 100;
        StringBuilder operations = new StringBuilder();
        for (int i = 0; i < testNum; i += 1) {
            double addVsRemove = StdRandom.uniform();
            if (addVsRemove < 0.5) {
                double firstVsLast = StdRandom.uniform();
                int item = StdRandom.uniform(10);
                if (firstVsLast < 0.5) {
                    sad1.addFirst(item);
                    ads1.addFirst(item);
                    operations.append("addFirst(" + item + ")\n");
                } else {
                    sad1.addLast(item);
                    ads1.addLast(item);
                    operations.append("addLast(" + item + ")\n");
                }
            } else {
                if (sad1.size() == 0) {
                    continue;
                }
                double firstVsLast = StdRandom.uniform();
                Integer expected;
                Integer actual;
                if (firstVsLast < 0.5) {
                    expected = ads1.removeFirst();
                    actual = sad1.removeFirst();
                    operations.append("removeFirst()\n");
                } else {
                    expected = ads1.removeLast();
                    actual = sad1.removeLast();
                    operations.append("removeLast()\n");
                }
                assertEquals(operations.toString(), expected, actual);
            }
        }
    }

}
