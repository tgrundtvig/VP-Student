package dk.viprogram.week05;

/**
 * A list that maintains elements in sorted order.
 *
 * Exercise 3: This interface extends SimpleList with the constraint
 * that elements are always kept sorted. Implement ArraySortedList
 * to pass the tests.
 *
 * Unlike a regular list where elements stay in insertion order,
 * a sorted list automatically places elements in their correct position.
 *
 * @param <T> the type of elements (must be Comparable)
 */
public interface SortedSimpleList<T extends Comparable<T>> extends SimpleList<T> {

    /**
     * Adds an element in sorted order.
     * After this operation, the list remains sorted.
     *
     * @param element the element to add
     */
    @Override
    void add(T element);

    /**
     * Gets the smallest element in the list.
     *
     * @return the minimum element
     * @throws java.util.NoSuchElementException if the list is empty
     */
    T getMin();

    /**
     * Gets the largest element in the list.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the list is empty
     */
    T getMax();

    /**
     * Finds the index of an element using binary search.
     * Only works correctly because the list is sorted.
     *
     * @param element the element to find
     * @return the index of the element, or -1 if not found
     */
    int indexOf(T element);
}
