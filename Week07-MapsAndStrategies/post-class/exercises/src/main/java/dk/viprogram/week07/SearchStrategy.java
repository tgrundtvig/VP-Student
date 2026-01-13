package dk.viprogram.week07;

/**
 * A strategy for matching items against a search query.
 *
 * Different implementations provide different search behaviors:
 * - Exact match: item must match query exactly
 * - Prefix match: item must start with query
 * - Contains match: item must contain query
 * - Fuzzy match: item is similar to query
 *
 * The Strategy pattern allows the search engine to use different
 * matching algorithms without changing its code.
 *
 * @param <T> the type of items being searched
 */
@FunctionalInterface
public interface SearchStrategy<T> {

    /**
     * Determines if an item matches the search query.
     *
     * @param item  the item to check
     * @param query the search query
     * @return true if the item matches the query according to this strategy
     */
    boolean matches(T item, String query);

    /**
     * Returns a strategy that matches if BOTH this strategy and the other match.
     *
     * @param other another strategy
     * @return a combined AND strategy
     */
    default SearchStrategy<T> and(SearchStrategy<T> other) {
        return (item, query) -> this.matches(item, query) && other.matches(item, query);
    }

    /**
     * Returns a strategy that matches if EITHER this strategy or the other match.
     *
     * @param other another strategy
     * @return a combined OR strategy
     */
    default SearchStrategy<T> or(SearchStrategy<T> other) {
        return (item, query) -> this.matches(item, query) || other.matches(item, query);
    }

    /**
     * Returns a strategy that matches when this strategy does NOT match.
     *
     * @return a negated strategy
     */
    default SearchStrategy<T> not() {
        return (item, query) -> !this.matches(item, query);
    }
}
