package dk.viprogram.week05;

/**
 * A linked-list implementation of SimpleList.
 *
 * Exercise 1: Implement this class to pass all the tests in LinkedSimpleListTest.
 *
 * This implementation uses Node objects to form a chain of elements.
 * Unlike ArraySimpleList, there's no underlying array - each element
 * is wrapped in a Node that points to the next Node.
 *
 * Implementation hints:
 * - Keep a reference to the first node (head)
 * - Keep track of size separately
 * - To traverse the list, start at head and follow next pointers
 * - Be careful with edge cases (empty list, single element, etc.)
 *
 * @param <T> the type of elements in this list
 */
public class LinkedSimpleList<T> implements SimpleList<T> {

    private Node<T> head;
    private int size;

    /**
     * Creates an empty LinkedSimpleList.
     */
    public LinkedSimpleList() {
        // TODO: Initialize head to null
        // TODO: Initialize size to 0
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void add(T element) {
        // TODO: Create a new node with the element
        // TODO: If the list is empty (head is null), make the new node the head
        // TODO: Otherwise, traverse to the last node and set its next to the new node
        // TODO: Increment size
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public T get(int index) {
        // TODO: Check if index is valid
        // TODO: Traverse to the node at the given index
        // TODO: Return its element
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public T remove(int index) {
        // TODO: Check if index is valid
        // TODO: Special case: removing head (index 0)
        // TODO: Otherwise, traverse to the node BEFORE the one to remove
        // TODO: Update its next pointer to skip the removed node
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
        // TODO: Return true if size is 0 (or head is null)
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean contains(T element) {
        // TODO: Traverse the list checking each element with equals()
        // TODO: Handle null elements correctly
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public void clear() {
        // TODO: Set head to null
        // TODO: Set size to 0
        // (Java's garbage collector will clean up the nodes)
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Helper method to get the node at a given index.
     */
    private Node<T> getNode(int index) {
        // TODO: Start at head and traverse index times
        // TODO: Return the node at that position
        throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Checks if the given index is valid.
     */
    private void checkIndex(int index) {
        // TODO: Throw IndexOutOfBoundsException if index < 0 or index >= size
        throw new UnsupportedOperationException("Implement me!");
    }
}
