package dk.viprogram.week05;

import java.util.NoSuchElementException;

/**
 * An array-backed implementation of SimpleStack.
 *
 * Exercise 3: Implement this class to pass all the tests in StackTest.
 *
 * Implementation hints:
 * - The "top" of the stack is at index (size - 1)
 * - push() adds at the top (end of used portion)
 * - pop() removes from the top
 * - You can reuse ideas from ArraySimpleList
 *
 * @param <T> the type of elements in this stack
 */
public class ArrayStack<T> implements SimpleStack<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    /**
     * Creates an empty ArrayStack with default capacity.
     */
    public ArrayStack() {
        // TODO: Initialize the elements array with DEFAULT_CAPACITY
        // TODO: Initialize size to 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void push(T element) {
        // TODO: Ensure there's capacity
        // TODO: Add element at position 'size' (the new top)
        // TODO: Increment size
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        // TODO: If empty, throw NoSuchElementException with message "Stack is empty"
        // TODO: Decrement size
        // TODO: Get the element at the new size (which was the top)
        // TODO: Set that position to null (help GC)
        // TODO: Return the element
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        // TODO: If empty, throw NoSuchElementException with message "Stack is empty"
        // TODO: Return the element at position (size - 1) without removing it
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

    /**
     * Ensures there is room for at least one more element.
     */
    private void ensureCapacity() {
        // TODO: If array is full, create new array with double capacity
        // TODO: Copy elements to new array
        throw new UnsupportedOperationException("Implement me!");
    }
}
