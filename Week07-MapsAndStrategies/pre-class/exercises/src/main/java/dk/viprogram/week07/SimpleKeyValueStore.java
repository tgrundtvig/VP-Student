package dk.viprogram.week07;

import java.util.*;

/**
 * A simple implementation of KeyValueStore using a HashMap internally.
 *
 * Your task: Implement all the methods in this class.
 * The tests will guide you - run them to see what's expected.
 *
 * Hint: Use a HashMap<K, V> as your internal storage.
 *
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class SimpleKeyValueStore<K, V> implements KeyValueStore<K, V> {

    // TODO: Add a private field to store the key-value pairs
    // Hint: private Map<K, V> storage = new HashMap<>();

    @Override
    public Optional<V> put(K key, V value) {
        // TODO: Implement this method
        // 1. Store the value with the key
        // 2. Return the previous value if there was one, or empty if this is a new key
        // Hint: HashMap.put() returns the old value (or null)
        throw new UnsupportedOperationException("Implement put()");
    }

    @Override
    public Optional<V> get(K key) {
        // TODO: Implement this method
        // Return the value for the key, or empty if key doesn't exist
        // Hint: Use Optional.ofNullable()
        throw new UnsupportedOperationException("Implement get()");
    }

    @Override
    public boolean containsKey(K key) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement containsKey()");
    }

    @Override
    public Optional<V> remove(K key) {
        // TODO: Implement this method
        // Remove the key and return its value, or empty if key didn't exist
        throw new UnsupportedOperationException("Implement remove()");
    }

    @Override
    public int size() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement size()");
    }

    @Override
    public boolean isEmpty() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement isEmpty()");
    }

    @Override
    public Set<K> keys() {
        // TODO: Implement this method
        // Return an unmodifiable set of keys
        // Hint: Use Collections.unmodifiableSet()
        throw new UnsupportedOperationException("Implement keys()");
    }

    @Override
    public Collection<V> values() {
        // TODO: Implement this method
        // Return an unmodifiable collection of values
        // Hint: Use Collections.unmodifiableCollection()
        throw new UnsupportedOperationException("Implement values()");
    }

    @Override
    public void clear() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement clear()");
    }
}
