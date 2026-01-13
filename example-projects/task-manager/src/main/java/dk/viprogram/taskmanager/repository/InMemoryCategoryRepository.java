package dk.viprogram.taskmanager.repository;

import dk.viprogram.taskmanager.model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory implementation of CategoryRepository.
 */
public class InMemoryCategoryRepository implements CategoryRepository {

    private final Map<String, Category> storage = new HashMap<>();

    @Override
    public Category save(Category category) {
        storage.put(category.id(), category);
        return category;
    }

    @Override
    public Optional<Category> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }

    @Override
    public long count() {
        return storage.size();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return storage.values().stream()
                .filter(cat -> cat.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
