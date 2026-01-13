package dk.viprogram.week09;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for CRUD operations on entities.
 *
 * This interface abstracts away the storage mechanism. Implementations
 * could store data in memory, files, databases, or remote services.
 *
 * The Repository pattern allows:
 * - Testing with fast in-memory implementations
 * - Swapping storage backends without changing business logic
 * - Clean separation between domain logic and persistence
 *
 * @param <T>  the entity type (must implement Identifiable)
 * @param <ID> the type of the entity's identifier
 */
public interface Repository<T extends Identifiable<ID>, ID> {

    /**
     * Saves an entity to the repository.
     *
     * If an entity with the same ID already exists, it is replaced (update).
     * If no entity with that ID exists, a new one is created.
     *
     * @param entity the entity to save (must not be null)
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Finds an entity by its unique identifier.
     *
     * @param id the ID to search for (must not be null)
     * @return an Optional containing the entity if found, or empty if not found
     */
    Optional<T> findById(ID id);

    /**
     * Returns all entities in the repository.
     *
     * @return a list of all entities (may be empty, never null)
     */
    List<T> findAll();

    /**
     * Deletes the given entity from the repository.
     *
     * If the entity doesn't exist, this method does nothing.
     *
     * @param entity the entity to delete
     */
    void delete(T entity);

    /**
     * Deletes an entity by its ID.
     *
     * If no entity with that ID exists, this method does nothing.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);

    /**
     * Checks if an entity with the given ID exists.
     *
     * @param id the ID to check
     * @return true if an entity with that ID exists, false otherwise
     */
    boolean existsById(ID id);

    /**
     * Returns the total number of entities in the repository.
     *
     * @return the count of entities
     */
    long count();
}
