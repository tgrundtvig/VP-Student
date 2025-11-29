package dk.viprogram.week05;

/**
 * A simplified Stack interface for educational purposes.
 *
 * Exercise 3: This interface is already complete. Study it to understand
 * the Stack contract (LIFO - Last In, First Out behavior).
 *
 * A stack provides:
 * - push: add an element to the top
 * - pop: remove and return the top element
 * - peek: look at the top element without removing it
 *
 * Think of it like a stack of plates - you can only add or remove from the top.
 *
 * @param <T> the type of elements in this stack
 */
public interface SimpleStack<T> {

    /**
     * Pushes an element onto the top of this stack.
     *
     * @param element the element to push
     */
    void push(T element);

    /**
     * Removes and returns the element at the top of this stack.
     *
     * @return the element at the top
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    T pop();

    /**
     * Returns the element at the top of this stack without removing it.
     *
     * @return the element at the top
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    T peek();

    /**
     * Returns true if this stack contains no elements.
     *
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this stack.
     *
     * @return the size of the stack
     */
    int size();
}
