package dk.viprogram.week05;

/**
 * A simplified List interface for educational purposes.
 *
 * Exercise 1: This interface is already complete. Study it carefully
 * to understand what contract it defines. You will implement this
 * interface in Exercise 2.
 *
 * This interface represents an ordered collection (a list) that:
 * - Maintains insertion order
 * - Allows access by index
 * - Allows duplicates
 * - Is generic (works with any type T)
 */
public interface SimpleList<T> {

    /**
     * Adds an element to the end of this list.
     *
     * @param element the element to add
     */
    void add(T element);

    /**
     * Gets the element at the specified index.
     *
     * @param index the index of the element to retrieve (0-based)
     * @return the element at that index
     * @throws IndexOutOfBoundsException if index is negative or >= size()
     */
    T get(int index);

    /**
     * Removes the element at the specified index.
     * All subsequent elements are shifted left.
     *
     * @param index the index of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if index is negative or >= size()
     */
    T remove(int index);

    /**
     * Returns the number of elements in this list.
     *
     * @return the size of the list
     */
    int size();

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns true if this list contains the specified element.
     * Uses equals() for comparison.
     *
     * @param element the element to search for
     * @return true if the element is found, false otherwise
     */
    boolean contains(T element);

    /**
     * Removes all elements from this list.
     */
    void clear();
}
