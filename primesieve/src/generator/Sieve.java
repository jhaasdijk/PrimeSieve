package generator;

/**
 *
 * @author Jasper Haasdijk
 */
public class Sieve implements Runnable {

    private final int BUFFSIZE = 10;

    private final int count;
    private final int prime;
    private final Buffer predecessor;
    private final Buffer successor;

    public Sieve(int count, int prime, Buffer predecessor, Buffer successor) {
        this.count = count;
        this.prime = prime;
        this.predecessor = predecessor;
        this.successor = successor;

        System.out.println(count + ": " + prime);
    }

    @Override
    public void run() {
        boolean hasSuccessor = false;
        while (true) {
            int element = (int) predecessor.get();

            if (!hasSuccessor) {
                if (element % prime != 0) {
                    /*
                    The next sieve has not yet been created
                    We have found the next prime
                    -> Create the next sieve and start its thread
                     */

                    hasSuccessor = true;
                    Buffer out = new Buffer(BUFFSIZE);
                    Sieve next = new Sieve(count + 1, element, successor, out);
                    Thread thread = new Thread(next);
                    thread.start();
                }
            } else {
                /*
                The next sieve has been created
                -> Put the unfiltered numbers into the next buffer
                 */

                if (element % prime != 0) {
                    successor.put(element);
                }
            }
        }
    }

}
