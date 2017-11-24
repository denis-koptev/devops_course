import org.junit.Assert;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import static org.junit.Assert.*;

public class AvlTreeTest {
    @Test
    public void iterator() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        final int size = 20;

        Random rnd = new Random();
        for ( int i = 0 ; i < size; ++i) {
            avlTree.add(50 - rnd.nextInt(100));
        }

        boolean success = true;

        Iterator<Integer> iter = avlTree.iterator();

        if (!iter.hasNext()) {
            success = false;
        } else {
            int element_was = iter.next();
            while (iter.hasNext()) {
                int element_is = iter.next();
                if (element_is <= element_was) {
                    success = false;
                }
                element_was = element_is;
            }
        }

        Assert.assertFalse("Test for 'iterator()' failed", !success);
    }

    @Test
    public void equals() throws Exception {
        AvlTree<Integer> avlTree_1 = new AvlTree<>();
        AvlTree<Integer> avlTree_2 = new AvlTree<>();
        final int size = 20;

        Random rnd = new Random();
        for ( int i = 0 ; i < size; ++i) {
            Integer curr = 50 - rnd.nextInt(100);
            avlTree_1.add(curr);
            avlTree_2.add(curr);
        }

        Assert.assertFalse("Test for 'equals() failed: not equal'",
                !avlTree_1.equals(avlTree_2));

        avlTree_1.remove(avlTree_1.iterator().next());

        Assert.assertFalse("Test for 'equals() failed: equal'",
                avlTree_1.equals(avlTree_2));
    }

    @Test
    public void isEmpty() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        Assert.assertFalse("Test for 'isEmpty()' failed: not empty", !avlTree.isEmpty());

        avlTree.add(5);
        Assert.assertFalse("Test for 'isEmpty()' failed: empty", avlTree.isEmpty());

        avlTree.remove(5);
        Assert.assertFalse("Test for 'isEmpty()' failed: not empty after remove", !avlTree.isEmpty());
    }

    @Test
    public void toArray() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        final int size = 20;

        Vector<Integer> array_in = new Vector<>();

        Random rnd = new Random();
        for ( int i = 0 ; i < size; ++i) {
            Integer curr = 50 - rnd.nextInt(100);
            if (avlTree.add(curr)) {
                array_in.add(curr);
            }
        }

        array_in.sort(null);

        Object[] array_out = avlTree.toArray();
        Assert.assertFalse("Test for 'toArray()' failed",
                !Arrays.equals(array_in.toArray(), array_out));
    }

    @Test
    public void add_contains() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.add(5);

        Assert.assertFalse("Test for 'add_contains()' failed", !avlTree.contains(5));
        Assert.assertTrue("Test for 'add_contains()' failed", !avlTree.contains(7));
        Assert.assertTrue("Test for 'add_contains()' failed", !avlTree.add(5));
    }

    @Test
    public void remove() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.add(5);
        Assert.assertFalse("Test for 'remove()' failed: removed non-existing",
                avlTree.remove(7));
        Assert.assertFalse("Test for 'remove()' failed: not removed",
                !avlTree.remove(5));
    }

    @Test
    public void clear() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.add(5);
        avlTree.clear();

        assertFalse("Test for 'clear()' failed", !avlTree.isEmpty());
    }

    @Test
    public void size() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        Assert.assertFalse("Test for 'size()' failed: not 0", avlTree.size() != 0);

        avlTree.add(5);
        Assert.assertFalse("Test for 'size()' failed: not 1", avlTree.size() != 1);

        avlTree.remove(5);
        Assert.assertFalse("Test for 'size()' failed: not 0 after remove", avlTree.size() != 0);
    }

}