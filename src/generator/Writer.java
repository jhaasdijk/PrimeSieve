package generator;

/**
 * Writer class which fills the initial buffer with numbers 2, 3, 4, ...
 *
 * @author Jasper Haasdijk
 */
public class Writer implements Runnable {

    private final Buffer output;

    public Writer(Buffer output) {
        this.output = output;
    }

    @Override
    public void run() {
        int element = 2;
        while (true) {
            output.put(element);
            element++;
        }
    }

}
