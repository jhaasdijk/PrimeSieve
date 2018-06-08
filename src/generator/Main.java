package generator;

/**
 * Main bootstraps the program; it starts the writer and initial sieve thread
 *
 * @author Jasper Haasdijk
 */
public class Main {

    private static final int BUFFSIZE = 10;
    private static final int LIMIT = 1000;

    public static void main(String[] args) {
        System.out.println("start: " + System.currentTimeMillis());

        Buffer inBuffer = new Buffer(BUFFSIZE);
        Writer inWriter = new Writer(inBuffer);

        Buffer outBuffer = new Buffer(BUFFSIZE);
        Sieve two = new Sieve(1, LIMIT, 2, inBuffer, outBuffer);

        Thread writer = new Thread(inWriter);
        Thread sieve = new Thread(two);
        writer.start();
        sieve.start();
    }

}
