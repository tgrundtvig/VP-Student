package dk.viprogram.week07;

import java.util.*;

/**
 * A flexible search engine that uses strategy patterns for searching and filtering.
 *
 * This class combines:
 * - A Map to store items by ID
 * - A SearchStrategy for matching items to queries
 * - A FilterStrategy for filtering results
 * - A sorting strategy (Comparator) for ordering results
 *
 * Your task: Implement all the methods in this class.
 *
 * @param <K> the type of keys (item IDs)
 * @param <T> the type of items being searched
 */
public class SearchEngine<K, T> {

    // TODO: Add private fields for:
    // - Map<K, T> items - the searchable items
    // - SearchStrategy<T> searchStrategy - how to match items
    // - FilterStrategy<T> filterStrategy - how to filter items
    // - Comparator<T> sortingStrategy - how to sort results

    /**
     * Creates a new SearchEngine with the given items and default strategies.
     *
     * Default strategies:
     * - Search: match all items (always returns true)
     * - Filter: accept all items (always returns true)
     * - Sort: no sorting (natural order)
     *
     * @param items the initial items, mapped by their IDs
     */
    public SearchEngine(Map<K, T> items) {
        // TODO: Initialize fields
        // Use defensive copy: new HashMap<>(items)
        // Set default strategies that accept/match everything
        throw new UnsupportedOperationException("Implement constructor");
    }

    /**
     * Adds an item to the search engine.
     *
     * @param id   the item's unique ID
     * @param item the item to add
     */
    public void addItem(K id, T item) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement addItem()");
    }

    /**
     * Removes an item from the search engine.
     *
     * @param id the ID of the item to remove
     * @return the removed item, or empty if not found
     */
    public Optional<T> removeItem(K id) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement removeItem()");
    }

    /**
     * Sets the search strategy used to match items to queries.
     *
     * @param strategy the new search strategy
     */
    public void setSearchStrategy(SearchStrategy<T> strategy) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement setSearchStrategy()");
    }

    /**
     * Sets the filter strategy used to filter search results.
     *
     * @param strategy the new filter strategy
     */
    public void setFilterStrategy(FilterStrategy<T> strategy) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement setFilterStrategy()");
    }

    /**
     * Sets the sorting strategy used to order search results.
     *
     * @param strategy the new sorting strategy (Comparator)
     */
    public void setSortingStrategy(Comparator<T> strategy) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement setSortingStrategy()");
    }

    /**
     * Searches for items matching the query.
     *
     * The search process:
     * 1. Find all items that match the query (using searchStrategy)
     * 2. Filter the matches (using filterStrategy)
     * 3. Sort the filtered results (using sortingStrategy)
     * 4. Return the sorted list
     *
     * @param query the search query
     * @return a list of matching items, filtered and sorted
     */
    public List<T> search(String query) {
        // TODO: Implement this method
        // Steps:
        // 1. Stream/iterate over items.values()
        // 2. Filter using searchStrategy.matches(item, query)
        // 3. Filter using filterStrategy.accept(item)
        // 4. Sort using sortingStrategy (if not null)
        // 5. Collect to a new list
        throw new UnsupportedOperationException("Implement search()");
    }

    /**
     * Returns all items without any search query.
     * Still applies filter and sorting.
     *
     * @return all items, filtered and sorted
     */
    public List<T> getAllItems() {
        // TODO: Implement this method
        // Similar to search(), but skip the search matching step
        throw new UnsupportedOperationException("Implement getAllItems()");
    }

    /**
     * Returns the number of items in the search engine.
     *
     * @return the item count
     */
    public int size() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement size()");
    }
}
