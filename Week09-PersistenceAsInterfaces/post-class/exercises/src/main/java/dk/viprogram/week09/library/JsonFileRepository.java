package dk.viprogram.week09.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A file-based repository that stores entities as JSON.
 *
 * This implementation persists data to a JSON file, so data survives
 * program restarts. It reads the entire file on each operation, which
 * is fine for small datasets but not suitable for large-scale use.
 *
 * Your task: Implement the TODO methods to make this repository work!
 *
 * @param <T>  the entity type
 * @param <ID> the identifier type
 */
public class JsonFileRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {

    private final Path filePath;
    private final Class<T> entityClass;
    private final Gson gson;
    private final Type listType;

    /**
     * Creates a new JSON file repository.
     *
     * @param filename    the name of the JSON file to use
     * @param entityClass the class of the entity (needed for JSON deserialization)
     */
    public JsonFileRepository(String filename, Class<T> entityClass) {
        this.filePath = Path.of(filename);
        this.entityClass = entityClass;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        // TypeToken is needed for Gson to handle generic List<T>
        this.listType = TypeToken.getParameterized(List.class, entityClass).getType();
    }

    @Override
    public T save(T entity) {
        // TODO: Implement this method
        //
        // Steps:
        // 1. Read all existing entities using readFromFile()
        // 2. Remove any existing entity with the same ID (update case)
        //    Hint: all.removeIf(e -> e.getId().equals(entity.getId()));
        // 3. Add the new entity to the list
        // 4. Write the list back to file using writeToFile()
        // 5. Return the saved entity
        //
        // Example:
        //   List<T> all = new ArrayList<>(readFromFile());
        //   all.removeIf(e -> e.getId().equals(entity.getId()));
        //   all.add(entity);
        //   writeToFile(all);
        //   return entity;

        throw new UnsupportedOperationException("TODO: Implement save()");
    }

    @Override
    public Optional<T> findById(ID id) {
        // TODO: Implement this method
        //
        // Steps:
        // 1. Read all entities from file
        // 2. Find the one with matching ID using stream
        //
        // Hint:
        //   return readFromFile().stream()
        //       .filter(e -> e.getId().equals(id))
        //       .findFirst();

        throw new UnsupportedOperationException("TODO: Implement findById()");
    }

    @Override
    public List<T> findAll() {
        // TODO: Implement this method
        //
        // Simply return all entities from the file.
        //
        // Hint: return readFromFile();

        throw new UnsupportedOperationException("TODO: Implement findAll()");
    }

    @Override
    public void delete(T entity) {
        // TODO: Implement this method
        //
        // Steps:
        // 1. Read all entities
        // 2. Remove the one with matching ID
        // 3. Write back to file
        //
        // Hint:
        //   List<T> all = new ArrayList<>(readFromFile());
        //   all.removeIf(e -> e.getId().equals(entity.getId()));
        //   writeToFile(all);

        throw new UnsupportedOperationException("TODO: Implement delete()");
    }

    @Override
    public void deleteById(ID id) {
        // TODO: Implement this method
        //
        // Similar to delete(), but uses the ID directly.

        throw new UnsupportedOperationException("TODO: Implement deleteById()");
    }

    @Override
    public boolean existsById(ID id) {
        // TODO: Implement this method
        //
        // Check if any entity has the given ID.
        //
        // Hint:
        //   return readFromFile().stream()
        //       .anyMatch(e -> e.getId().equals(id));

        throw new UnsupportedOperationException("TODO: Implement existsById()");
    }

    @Override
    public long count() {
        // TODO: Implement this method
        //
        // Return the number of entities.
        //
        // Hint: return readFromFile().size();

        throw new UnsupportedOperationException("TODO: Implement count()");
    }

    // ==================== Helper Methods (Provided) ====================

    /**
     * Reads all entities from the JSON file.
     *
     * @return list of entities (empty list if file doesn't exist)
     */
    protected List<T> readFromFile() {
        try {
            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }
            String json = Files.readString(filePath);
            if (json.isBlank()) {
                return new ArrayList<>();
            }
            List<T> result = gson.fromJson(json, listType);
            return result != null ? result : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from " + filePath, e);
        }
    }

    /**
     * Writes all entities to the JSON file.
     *
     * @param entities the list of entities to write
     */
    protected void writeToFile(List<T> entities) {
        try {
            String json = gson.toJson(entities);
            Files.writeString(filePath, json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to " + filePath, e);
        }
    }

    /**
     * Deletes the underlying file.
     * Useful for testing cleanup.
     */
    public void deleteFile() {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete " + filePath, e);
        }
    }
}
