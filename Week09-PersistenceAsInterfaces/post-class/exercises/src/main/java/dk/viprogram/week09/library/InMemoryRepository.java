package dk.viprogram.week09.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory implementation of Repository.
 *
 * This implementation is provided complete - you implemented this in pre-class!
 * Data is stored in a HashMap and lost when the program ends.
 */
public class InMemoryRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {

    private final Map<ID, T> storage = new HashMap<>();

    @Override
    public T save(T entity) {
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(T entity) {
        storage.remove(entity.getId());
    }

    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(ID id) {
        return storage.containsKey(id);
    }

    @Override
    public long count() {
        return storage.size();
    }

    /**
     * Clears all entities from the repository.
     * Useful for testing.
     */
    public void clear() {
        storage.clear();
    }
}
