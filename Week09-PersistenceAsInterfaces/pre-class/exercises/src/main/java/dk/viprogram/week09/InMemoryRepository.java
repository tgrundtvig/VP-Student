package dk.viprogram.week09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An in-memory implementation of the Repository interface.
 *
 * This implementation stores entities in a HashMap, making all operations
 * very fast (O(1) for most operations). Data is lost when the program ends,
 * which makes this perfect for:
 * - Unit testing (fast, no cleanup needed)
 * - Development (quick iteration)
 * - Prototyping (get something working fast)
 *
 * @param <T>  the entity type
 * @param <ID> the type of the entity's identifier
 */
public class InMemoryRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {

    // TODO: Add a private field to store entities
    // Hint: Use a Map<ID, T> to store entities by their ID
    // Example: private final Map<ID, T> storage = new HashMap<>();

    @Override
    public T save(T entity) {
        // TODO: Implement this method
        //
        // Store the entity in your map using its ID as the key.
        // If an entity with that ID already exists, it will be replaced.
        //
        // Steps:
        // 1. Get the entity's ID using entity.getId()
        // 2. Put the entity in the storage map
        // 3. Return the entity
        //
        // Hint: storage.put(entity.getId(), entity);

        throw new UnsupportedOperationException("TODO: Implement save()");
    }

    @Override
    public Optional<T> findById(ID id) {
        // TODO: Implement this method
        //
        // Look up the entity by ID and wrap in Optional.
        //
        // Steps:
        // 1. Get the entity from the storage map
        // 2. Wrap it in Optional (handles null case automatically)
        //
        // Hint: return Optional.ofNullable(storage.get(id));

        throw new UnsupportedOperationException("TODO: Implement findById()");
    }

    @Override
    public List<T> findAll() {
        // TODO: Implement this method
        //
        // Return all entities as a new list.
        //
        // Steps:
        // 1. Get all values from the storage map
        // 2. Return them as a new ArrayList (to prevent external modification)
        //
        // Hint: return new ArrayList<>(storage.values());

        throw new UnsupportedOperationException("TODO: Implement findAll()");
    }

    @Override
    public void delete(T entity) {
        // TODO: Implement this method
        //
        // Remove the entity from storage using its ID.
        //
        // Hint: storage.remove(entity.getId());

        throw new UnsupportedOperationException("TODO: Implement delete()");
    }

    @Override
    public void deleteById(ID id) {
        // TODO: Implement this method
        //
        // Remove the entity with the given ID from storage.
        //
        // Hint: storage.remove(id);

        throw new UnsupportedOperationException("TODO: Implement deleteById()");
    }

    @Override
    public boolean existsById(ID id) {
        // TODO: Implement this method
        //
        // Check if an entity with the given ID exists.
        //
        // Hint: return storage.containsKey(id);

        throw new UnsupportedOperationException("TODO: Implement existsById()");
    }

    @Override
    public long count() {
        // TODO: Implement this method
        //
        // Return the number of entities in storage.
        //
        // Hint: return storage.size();

        throw new UnsupportedOperationException("TODO: Implement count()");
    }
}
