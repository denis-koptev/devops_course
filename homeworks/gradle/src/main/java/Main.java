import java.util.Iterator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        AvlTree<Integer> tree = new AvlTree<>();
        Random rnd = new Random();
        for ( int i = 0 ; i < 20; ++i) {
            tree.add(50 - rnd.nextInt(100));
        }
        System.out.println(tree.toString());
        Iterator<Integer> iter = tree.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }

    }
}
