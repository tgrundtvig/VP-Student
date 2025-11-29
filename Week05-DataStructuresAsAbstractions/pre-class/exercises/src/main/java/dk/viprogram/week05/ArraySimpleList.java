package dk.viprogram.week05;

/**
 * An array-backed implementation of SimpleList.
 *
 * Exercise 2: Implement this class to pass all the tests in ArraySimpleListTest.
 *
 * Implementation hints:
 * - Use an Object[] array to store elements (Java generics don't allow new T[])
 * - Track the current number of elements with a size field
 * - When the array is full, create a new larger array and copy elements
 * - Use @SuppressWarnings("unchecked") when casting from Object to T
 *
 * @param <T> the type of elements in this list
 */
public class ArraySimpleList<T> implements SimpleList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    /**
     * Creates an empty ArraySimpleList with default capacity.
     */
    public ArraySimpleList() {
        // TODO: Initialize the elements array with DEFAULT_CAPACITY
        // TODO: Initialize size to 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void add(T element) {
        // TODO: If the array is full, grow it (call ensureCapacity())
        // TODO: Add the element at position 'size'
        // TODO: Increment size
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        // TODO: Check if index is valid (call checkIndex())
        // TODO: Return the element at the given index, cast to T
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        // TODO: Check if index is valid
        // TODO: Save the element to return
        // TODO: Shift all elements after index one position left
        // TODO: Set the last position to null (help garbage collection)
        // TODO: Decrement size
        // TODO: Return the removed element
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public int size() {
        // TODO: Return the current size
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean isEmpty() {
        // TODO: Return true if size is 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean contains(T element) {
        // TODO: Loop through elements and check equality with equals()
        // TODO: Handle null elements correctly (use == for null comparison)
        // TODO: Return true if found, false otherwise
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void clear() {
        // TODO: Set all elements to null (help garbage collection)
        // TODO: Reset size to 0
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Ensures there is room for at least one more element.
     * If the array is full, creates a new array with double the capacity.
     */
    private void ensureCapacity() {
        // TODO: If size equals elements.length, grow the array
        // TODO: Create a new array with double the capacity
        // TODO: Copy all elements to the new array
        // TODO: Replace elements with the new array
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Checks if the given index is valid.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    private void checkIndex(int index) {
        // TODO: Throw IndexOutOfBoundsException if index is out of bounds
        // TODO: Include a helpful message with the index and size
        throw new UnsupportedOperationException("Implement me!");
    }
}
