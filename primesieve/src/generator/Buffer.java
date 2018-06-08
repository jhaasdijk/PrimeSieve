package generator;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Buffer is the class which represents the bounded buffer(s)
 *
 * @author Jasper Haasdijk
 */
public class Buffer<E> {

    private final int SIZE;
    private final ArrayList<E> list;
    private final Lock lock;
    private final Condition bufferFull;
    private final Condition bufferEmpty;

    /**
     * Constructor for the Buffer class. The parameter size defines the fixed
     * size of the buffer
     *
     * @param size - the fixed size of the buffer
     */
    public Buffer(int size) {
        this.list = new ArrayList<>();
        this.SIZE = size;
        lock = new ReentrantLock();
        bufferFull = lock.newCondition();
        bufferEmpty = lock.newCondition();
    }

    /**
     * method to get the first item in the buffer. This is allowed as long as
     * the buffer is not empty. Getting the first item is important as this
     * ensures proper ordering
     *
     * @return - the element that is removed from the buffer
     */
    public E get() {
        lock.lock();

        try {
            while (list.size() <= 0) {
                bufferEmpty.await();
            }

            E element = list.get(0);
            list.remove(0);
            bufferFull.signalAll();
            return element;

        } catch (InterruptedException e) {
            System.err.print(e);
            return null;

        } finally {
            lock.unlock();
        }
    }

    /**
     * method to put an item in the buffer. This is allowed as long as the
     * buffer is not full. New elements should be appended to the list as this
     * ensures proper ordering
     *
     * @param element - the element that is added to the buffer
     */
    public void put(E element) {
        lock.lock();

        try {
            while (list.size() >= SIZE) {
                bufferFull.await();
            }
            list.add(element);
            bufferEmpty.signalAll();

        } catch (InterruptedException e) {
            System.err.print(e);

        } finally {
            lock.unlock();
        }
    }
}
