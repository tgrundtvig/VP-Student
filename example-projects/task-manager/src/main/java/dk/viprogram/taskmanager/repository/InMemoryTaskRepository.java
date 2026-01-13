package dk.viprogram.taskmanager.repository;

import dk.viprogram.taskmanager.model.Priority;
import dk.viprogram.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory implementation of TaskRepository.
 *
 * Perfect for:
 * - Unit testing (fast, no I/O)
 * - Development (no setup required)
 * - Prototyping (quick iteration)
 *
 * Note: Data is lost when the application stops.
 */
public class InMemoryTaskRepository implements TaskRepository {

    private final Map<String, Task> storage = new HashMap<>();

    @Override
    public Task save(Task task) {
        storage.put(task.id(), task);
        return task;
    }

    @Override
    public Optional<Task> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Task> findAll() {
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
    public List<Task> findByCategory(String categoryId) {
        return storage.values().stream()
                .filter(task -> categoryId.equals(task.categoryId()))
                .toList();
    }

    @Override
    public List<Task> findByPriority(Priority priority) {
        return storage.values().stream()
                .filter(task -> task.priority() == priority)
                .toList();
    }

    @Override
    public List<Task> findIncomplete() {
        return storage.values().stream()
                .filter(task -> !task.completed())
                .toList();
    }

    @Override
    public List<Task> findCompleted() {
        return storage.values().stream()
                .filter(Task::completed)
                .toList();
    }

    @Override
    public List<Task> findOverdue() {
        return storage.values().stream()
                .filter(Task::isOverdue)
                .toList();
    }
}
