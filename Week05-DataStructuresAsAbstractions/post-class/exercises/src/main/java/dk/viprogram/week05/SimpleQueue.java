package dk.viprogram.week05;

/**
 * A simplified Queue interface for educational purposes.
 *
 * Exercise 2: This interface is provided for you. Implement LinkedQueue
 * to pass the tests.
 *
 * A queue provides FIFO (First In, First Out) behavior:
 * - Elements are added at the back (enqueue)
 * - Elements are removed from the front (dequeue)
 *
 * Think of it like a line at a store - first person in line gets served first.
 *
 * @param <T> the type of elements in this queue
 */
public interface SimpleQueue<T> {

    /**
     * Adds an element to the back of this queue.
     *
     * @param element the element to add
     */
    void enqueue(T element);

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    T dequeue();

    /**
     * Returns the element at the front of this queue without removing it.
     *
     * @return the element at the front
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    T peek();

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this queue.
     *
     * @return the size of the queue
     */
    int size();
}
