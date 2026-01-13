package dk.viprogram.taskmanager.repository;

import dk.viprogram.taskmanager.model.Priority;
import dk.viprogram.taskmanager.model.Task;

import java.util.List;

/**
 * Repository interface specifically for Tasks.
 *
 * Extends the generic Repository with Task-specific query methods.
 * This is an example of interface specialization - we add domain-specific
 * operations while inheriting the basic CRUD operations.
 */
public interface TaskRepository extends Repository<Task, String> {

    /**
     * Finds all tasks in a specific category.
     *
     * @param categoryId the category ID
     * @return list of tasks in that category
     */
    List<Task> findByCategory(String categoryId);

    /**
     * Finds all tasks with a specific priority.
     *
     * @param priority the priority level
     * @return list of tasks with that priority
     */
    List<Task> findByPriority(Priority priority);

    /**
     * Finds all incomplete tasks.
     *
     * @return list of incomplete tasks
     */
    List<Task> findIncomplete();

    /**
     * Finds all completed tasks.
     *
     * @return list of completed tasks
     */
    List<Task> findCompleted();

    /**
     * Finds all overdue tasks.
     *
     * @return list of overdue tasks
     */
    List<Task> findOverdue();
}
