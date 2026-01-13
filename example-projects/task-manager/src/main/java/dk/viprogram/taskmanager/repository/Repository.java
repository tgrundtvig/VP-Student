package dk.viprogram.taskmanager.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for data persistence.
 *
 * This interface abstracts away HOW data is stored.
 * The same code works whether data is in memory, in a file, or in a database.
 *
 * @param <T>  the entity type
 * @param <ID> the identifier type
 */
public interface Repository<T, ID> {

    /**
     * Saves an entity. If it exists, updates it. If not, creates it.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID to search for
     * @return Optional containing the entity if found, empty otherwise
     */
    Optional<T> findById(ID id);

    /**
     * Returns all entities.
     *
     * @return list of all entities
     */
    List<T> findAll();

    /**
     * Deletes an entity by ID.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);

    /**
     * Returns the count of all entities.
     *
     * @return number of entities
     */
    long count();
}
