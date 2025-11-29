package dk.viprogram.week05;

import java.util.NoSuchElementException;

/**
 * An array-backed implementation of SortedSimpleList.
 *
 * Exercise 3: Implement this class to pass all the tests in SortedListTest.
 *
 * This list maintains elements in sorted order (smallest to largest).
 * When adding an element, it's inserted at the correct position to
 * maintain the sorted order.
 *
 * Implementation hints:
 * - Use binary search to find the insertion point quickly
 * - When adding, shift elements to make room at the correct position
 * - getMin() returns element at index 0
 * - getMax() returns element at index (size - 1)
 *
 * @param <T> the type of elements (must be Comparable)
 */
public class ArraySortedList<T extends Comparable<T>> implements SortedSimpleList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    /**
     * Creates an empty ArraySortedList with default capacity.
     */
    public ArraySortedList() {
        // TODO: Initialize the elements array
        // TODO: Initialize size to 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void add(T element) {
        // TODO: Ensure capacity
        // TODO: Find the correct position using binary search or linear scan
        // TODO: Shift elements to the right to make room
        // TODO: Insert the element at the correct position
        // TODO: Increment size
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        // TODO: Check index bounds
        // TODO: Return element at index
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        // TODO: Check index bounds
        // TODO: Save element to return
        // TODO: Shift elements left
        // TODO: Decrement size
        // TODO: Return removed element
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public int size() {
        // TODO: Return size
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isEmpty() {
        // TODO: Return true if size is 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean contains(T element) {
        // TODO: Use indexOf to check if element exists
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void clear() {
        // TODO: Set all elements to null
        // TODO: Reset size to 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getMin() {
        // TODO: If empty, throw NoSuchElementException
        // TODO: Return element at index 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getMax() {
        // TODO: If empty, throw NoSuchElementException
        // TODO: Return element at index (size - 1)
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public int indexOf(T element) {
        // TODO: Use binary search to find the element
        // TODO: Return its index, or -1 if not found
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Finds the index where an element should be inserted to maintain order.
     */
    @SuppressWarnings("unchecked")
    private int findInsertionPoint(T element) {
        // TODO: Use binary search to find where element belongs
        // TODO: Return the index where it should be inserted
        throw new UnsupportedOperationException("Implement me!");
    }

    private void ensureCapacity() {
        // TODO: If array is full, create new array with double capacity
        // TODO: Copy elements to new array
        throw new UnsupportedOperationException("Implement me!");
    }

    private void checkIndex(int index) {
        // TODO: Throw IndexOutOfBoundsException if invalid
        throw new UnsupportedOperationException("Implement me!");
    }
}
