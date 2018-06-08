package generator;

/**
 * Main bootstraps the program; it starts the writer and initial sieve thread
 *
 * @author Jasper Haasdijk
 */
public class Main {

    private static final int BUFFSIZE = 10;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()); // start time

        Buffer inBuffer = new Buffer(BUFFSIZE);
        Writer inWriter = new Writer(inBuffer);

        Buffer outBuffer = new Buffer(BUFFSIZE);
        Sieve two = new Sieve(1, 2, inBuffer, outBuffer);

        Thread writer = new Thread(inWriter);
        Thread sieve = new Thread(two);
        writer.start();
        sieve.start();

        System.out.println(System.currentTimeMillis()); // stop time
    }

}
