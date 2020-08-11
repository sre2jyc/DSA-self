import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Permutation
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // In fn = new In(args[1]);
        RandomizedQueue <String> rq = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        Iterator <String> it = rq.iterator();
        for(int i =0 ;i < k && it.hasNext();i++){
            StdOut.println(it.next());
        }

    }
}