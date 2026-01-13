package dk.viprogram.week07;

/**
 * A strategy for comparing two elements of type T.
 * This is a simplified version of java.util.Comparator.
 *
 * The Strategy pattern encapsulates an algorithm (in this case, comparison)
 * as an object, making it easy to swap different algorithms at runtime.
 *
 * @param <T> the type of elements to compare
 */
@FunctionalInterface
public interface SortingStrategy<T> {

    /**
     * Compares two elements and determines their relative order.
     *
     * @param first  the first element to compare
     * @param second the second element to compare
     * @return a negative integer if first should come before second,
     *         zero if they are equal in terms of ordering,
     *         a positive integer if first should come after second
     */
    int compare(T first, T second);

    /**
     * Returns a strategy that reverses the order of this strategy.
     *
     * @return a reversed strategy
     */
    default SortingStrategy<T> reversed() {
        return (first, second) -> this.compare(second, first);
    }

    /**
     * Returns a strategy that first uses this strategy, then uses the other
     * strategy as a tiebreaker when this strategy considers elements equal.
     *
     * @param other the tiebreaker strategy
     * @return a combined strategy
     */
    default SortingStrategy<T> thenComparing(SortingStrategy<T> other) {
        return (first, second) -> {
            int result = this.compare(first, second);
            return result != 0 ? result : other.compare(first, second);
        };
    }
}
