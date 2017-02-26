/**
 * @author Radovan Masaryk
 * 
 *         <i>Randomized Subset</i>. Takes a command-line integer k, reads in a
 *         sequence of N strings from standard input using StdIn.readString(),
 *         and prints out exactly k of them, uniformly at random.
 */

public class Subset {

    /**
     * Takes a command-line integer k, reads in a sequence of N strings from
     * standard input using StdIn.readString(), and prints out exactly k of
     * them, uniformly at random.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int k = Integer.parseInt(args[0]);
        if (k <= 0)
            return;
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        for (; k > 0; k--) {
            System.out.println(rq.dequeue());
        }
    }

}
