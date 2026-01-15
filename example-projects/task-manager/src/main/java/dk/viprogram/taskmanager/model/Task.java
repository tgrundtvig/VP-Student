package dk.viprogram.taskmanager.model;

import java.time.LocalDate;

/**
 * A task in the task manager.
 * Implemented as a record since it's immutable data.
 *
 * Note: Records are perfect for entities because:
 * - They're immutable (thread-safe, no unexpected changes)
 * - They provide equals/hashCode automatically
 * - They clearly express "this is data"
 */
public record Task(
        String id,
        String title,
        String description,
        Priority priority,
        String categoryId,
        LocalDate dueDate,
        boolean completed
) {
    /**
     * Creates a new incomplete task with the given details.
     */
    public static Task create(String title, String description, Priority priority,
                              String categoryId, LocalDate dueDate) {
        return new Task(
                java.util.UUID.randomUUID().toString().substring(0, 8),
                title,
                description,
                priority,
                categoryId,
                dueDate,
                false
        );
    }

    /**
     * Returns a new Task with completed status toggled.
     */
    public Task toggleComplete() {
        return new Task(id, title, description, priority, categoryId, dueDate, !completed);
    }

    /**
     * Returns a new Task marked as completed.
     */
    public Task complete() {
        return new Task(id, title, description, priority, categoryId, dueDate, true);
    }

    /**
     * Returns a new Task with updated priority.
     */
    public Task withPriority(Priority newPriority) {
        return new Task(id, title, description, newPriority, categoryId, dueDate, completed);
    }

    /**
     * Returns a new Task with updated due date.
     */
    public Task withDueDate(LocalDate newDueDate) {
        return new Task(id, title, description, priority, categoryId, newDueDate, completed);
    }

    /**
     * Checks if the task is overdue.
     */
    public boolean isOverdue() {
        return !completed && dueDate != null && dueDate.isBefore(LocalDate.now());
    }

    /**
     * Returns a short display string for use in dropdowns.
     */
    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return String.format("%s %s [%s]", status, title, priority);
    }
}
