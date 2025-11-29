package dk.viprogram.week05;

import java.util.NoSuchElementException;

/**
 * A linked-list implementation of SimpleQueue.
 *
 * Exercise 2: Implement this class to pass all the tests in QueueTest.
 *
 * Implementation hints:
 * - Keep references to both the front and back of the queue
 * - enqueue adds to the back
 * - dequeue removes from the front
 * - A linked implementation is efficient for queues because both
 *   operations are O(1) if you maintain front and back references
 *
 * @param <T> the type of elements in this queue
 */
public class LinkedQueue<T> implements SimpleQueue<T> {

    private Node<T> front;
    private Node<T> back;
    private int size;

    /**
     * Creates an empty LinkedQueue.
     */
    public LinkedQueue() {
        // TODO: Initialize front and back to null
        // TODO: Initialize size to 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void enqueue(T element) {
        // TODO: Create a new node
        // TODO: If queue is empty, set both front and back to the new node
        // TODO: Otherwise, link the current back to the new node and update back
        // TODO: Increment size
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public T dequeue() {
        // TODO: If empty, throw NoSuchElementException with message "Queue is empty"
        // TODO: Save the front element
        // TODO: Move front to front.next
        // TODO: If front is now null, also set back to null (queue is empty)
        // TODO: Decrement size
        // TODO: Return the saved element
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public T peek() {
        // TODO: If empty, throw NoSuchElementException with message "Queue is empty"
        // TODO: Return front's element without removing it
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isEmpty() {
        // TODO: Return true if size is 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public int size() {
        // TODO: Return the current size
        throw new UnsupportedOperationException("Implement me!");
    }
}
