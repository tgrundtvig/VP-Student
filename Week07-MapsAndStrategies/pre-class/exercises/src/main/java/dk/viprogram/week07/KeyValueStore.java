package dk.viprogram.week07;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * A simplified key-value store interface.
 * This is a teaching version of java.util.Map, using Optional for safer null handling.
 *
 * A KeyValueStore associates unique keys with values. Each key can map to at most one value.
 * If you put a value with an existing key, the old value is replaced.
 *
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public interface KeyValueStore<K, V> {

    /**
     * Associates the key with the value.
     * If the key already exists, the old value is replaced with the new value.
     *
     * @param key   the key (must not be null)
     * @param value the value to associate with the key
     * @return the previous value associated with key, or empty if this is a new key
     */
    Optional<V> put(K key, V value);

    /**
     * Gets the value associated with the key.
     *
     * @param key the key to look up
     * @return the value associated with the key, or empty if key doesn't exist
     */
    Optional<V> get(K key);

    /**
     * Checks if the store contains the specified key.
     *
     * @param key the key to check
     * @return true if the key exists in the store
     */
    boolean containsKey(K key);

    /**
     * Removes the key-value pair for the specified key.
     *
     * @param key the key to remove
     * @return the value that was associated with the key, or empty if key didn't exist
     */
    Optional<V> remove(K key);

    /**
     * Returns the number of key-value pairs in the store.
     *
     * @return the number of entries
     */
    int size();

    /**
     * Returns true if the store contains no key-value pairs.
     *
     * @return true if empty
     */
    boolean isEmpty();

    /**
     * Returns a set of all keys in the store.
     * The returned set should not be modifiable.
     *
     * @return all keys
     */
    Set<K> keys();

    /**
     * Returns a collection of all values in the store.
     * The returned collection should not be modifiable.
     *
     * @return all values
     */
    Collection<V> values();

    /**
     * Removes all key-value pairs from the store.
     */
    void clear();
}
