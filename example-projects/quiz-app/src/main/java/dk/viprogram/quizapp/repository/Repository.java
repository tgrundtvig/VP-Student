package dk.viprogram.quizapp.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface.
 */
public interface Repository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    long count();
}
