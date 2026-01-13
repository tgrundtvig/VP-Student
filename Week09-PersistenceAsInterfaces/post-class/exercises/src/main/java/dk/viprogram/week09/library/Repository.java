package dk.viprogram.week09.library;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for CRUD operations.
 *
 * @param <T>  the entity type
 * @param <ID> the identifier type
 */
public interface Repository<T extends Identifiable<ID>, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void delete(T entity);

    void deleteById(ID id);

    boolean existsById(ID id);

    long count();
}
