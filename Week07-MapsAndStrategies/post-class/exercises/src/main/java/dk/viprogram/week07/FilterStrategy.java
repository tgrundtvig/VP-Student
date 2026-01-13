package dk.viprogram.week07;

/**
 * A strategy for filtering items based on criteria.
 *
 * Unlike SearchStrategy (which matches against a query string),
 * FilterStrategy determines if an item passes certain criteria.
 *
 * Examples:
 * - Filter by price range
 * - Filter by category
 * - Filter by availability
 *
 * @param <T> the type of items being filtered
 */
@FunctionalInterface
public interface FilterStrategy<T> {

    /**
     * Determines if an item passes the filter.
     *
     * @param item the item to check
     * @return true if the item passes the filter
     */
    boolean accept(T item);

    /**
     * Returns a filter that accepts if BOTH this filter and the other accept.
     *
     * @param other another filter
     * @return a combined AND filter
     */
    default FilterStrategy<T> and(FilterStrategy<T> other) {
        return item -> this.accept(item) && other.accept(item);
    }

    /**
     * Returns a filter that accepts if EITHER this filter or the other accept.
     *
     * @param other another filter
     * @return a combined OR filter
     */
    default FilterStrategy<T> or(FilterStrategy<T> other) {
        return item -> this.accept(item) || other.accept(item);
    }

    /**
     * Returns a filter that accepts when this filter does NOT accept.
     *
     * @return a negated filter
     */
    default FilterStrategy<T> not() {
        return item -> !this.accept(item);
    }

    /**
     * Returns a filter that accepts all items.
     *
     * @param <T> the type of items
     * @return an accept-all filter
     */
    static <T> FilterStrategy<T> acceptAll() {
        return item -> true;
    }

    /**
     * Returns a filter that rejects all items.
     *
     * @param <T> the type of items
     * @return a reject-all filter
     */
    static <T> FilterStrategy<T> rejectAll() {
        return item -> false;
    }
}
