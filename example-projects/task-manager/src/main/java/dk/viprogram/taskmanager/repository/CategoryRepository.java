package dk.viprogram.taskmanager.repository;

import dk.viprogram.taskmanager.model.Category;

import java.util.Optional;

/**
 * Repository interface for Categories.
 */
public interface CategoryRepository extends Repository<Category, String> {

    /**
     * Finds a category by its name.
     *
     * @param name the category name
     * @return Optional containing the category if found
     */
    Optional<Category> findByName(String name);
}
