package dk.viprogram.taskmanager;

import dk.viprogram.taskmanager.model.Priority;
import dk.viprogram.taskmanager.model.Task;
import dk.viprogram.taskmanager.repository.InMemoryTaskRepository;
import dk.viprogram.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for TaskRepository implementations.
 *
 * These tests verify the repository contract.
 * The same tests could run against any TaskRepository implementation.
 */
class TaskRepositoryTest {

    private TaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    // ==================== Basic CRUD Tests ====================

    @Test
    @DisplayName("Save and find by ID returns same task")
    void saveAndFindById() {
        Task task = Task.create("Test", "Description", Priority.HIGH, null, null);

        repository.save(task);
        Task found = repository.findById(task.id()).orElseThrow();

        assertEquals(task, found);
    }

    @Test
    @DisplayName("Find by non-existent ID returns empty")
    void findByNonExistentId() {
        assertTrue(repository.findById("non-existent").isEmpty());
    }

    @Test
    @DisplayName("Save updates existing task")
    void saveUpdatesExisting() {
        Task original = Task.create("Original", "", Priority.LOW, null, null);
        repository.save(original);

        Task updated = original.withPriority(Priority.HIGH);
        repository.save(updated);

        assertEquals(1, repository.count());
        Task found = repository.findById(original.id()).orElseThrow();
        assertEquals(Priority.HIGH, found.priority());
    }

    @Test
    @DisplayName("Find all returns all saved tasks")
    void findAllReturnsAll() {
        Task task1 = Task.create("Task 1", "", Priority.LOW, null, null);
        Task task2 = Task.create("Task 2", "", Priority.HIGH, null, null);
        repository.save(task1);
        repository.save(task2);

        List<Task> all = repository.findAll();

        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("Delete by ID removes task")
    void deleteByIdRemovesTask() {
        Task task = Task.create("To Delete", "", Priority.MEDIUM, null, null);
        repository.save(task);

        repository.deleteById(task.id());

        assertTrue(repository.findById(task.id()).isEmpty());
        assertEquals(0, repository.count());
    }

    // ==================== Custom Query Tests ====================

    @Test
    @DisplayName("Find by category returns matching tasks")
    void findByCategoryReturnsMatching() {
        String categoryId = "cat-1";
        Task inCategory = Task.create("In Category", "", Priority.LOW, categoryId, null);
        Task notInCategory = Task.create("Not In Category", "", Priority.LOW, "other", null);
        repository.save(inCategory);
        repository.save(notInCategory);

        List<Task> found = repository.findByCategory(categoryId);

        assertEquals(1, found.size());
        assertEquals("In Category", found.get(0).title());
    }

    @Test
    @DisplayName("Find by priority returns matching tasks")
    void findByPriorityReturnsMatching() {
        Task high = Task.create("High", "", Priority.HIGH, null, null);
        Task low = Task.create("Low", "", Priority.LOW, null, null);
        repository.save(high);
        repository.save(low);

        List<Task> found = repository.findByPriority(Priority.HIGH);

        assertEquals(1, found.size());
        assertEquals("High", found.get(0).title());
    }

    @Test
    @DisplayName("Find incomplete excludes completed tasks")
    void findIncompleteExcludesCompleted() {
        Task incomplete = Task.create("Incomplete", "", Priority.MEDIUM, null, null);
        Task completed = Task.create("Completed", "", Priority.MEDIUM, null, null).complete();
        repository.save(incomplete);
        repository.save(completed);

        List<Task> found = repository.findIncomplete();

        assertEquals(1, found.size());
        assertFalse(found.get(0).completed());
    }

    @Test
    @DisplayName("Find completed only returns completed tasks")
    void findCompletedOnlyReturnsCompleted() {
        Task incomplete = Task.create("Incomplete", "", Priority.MEDIUM, null, null);
        Task completed = Task.create("Completed", "", Priority.MEDIUM, null, null).complete();
        repository.save(incomplete);
        repository.save(completed);

        List<Task> found = repository.findCompleted();

        assertEquals(1, found.size());
        assertTrue(found.get(0).completed());
    }

    @Test
    @DisplayName("Find overdue returns only overdue incomplete tasks")
    void findOverdueReturnsCorrectTasks() {
        Task overdue = Task.create("Overdue", "", Priority.HIGH, null,
                LocalDate.now().minusDays(1));
        Task future = Task.create("Future", "", Priority.MEDIUM, null,
                LocalDate.now().plusDays(1));
        Task completedOverdue = Task.create("Completed Overdue", "", Priority.LOW, null,
                LocalDate.now().minusDays(1)).complete();
        repository.save(overdue);
        repository.save(future);
        repository.save(completedOverdue);

        List<Task> found = repository.findOverdue();

        assertEquals(1, found.size());
        assertEquals("Overdue", found.get(0).title());
    }

    // ==================== Count Tests ====================

    @Test
    @DisplayName("Count returns correct number")
    void countReturnsCorrectNumber() {
        assertEquals(0, repository.count());

        repository.save(Task.create("1", "", Priority.LOW, null, null));
        assertEquals(1, repository.count());

        repository.save(Task.create("2", "", Priority.LOW, null, null));
        assertEquals(2, repository.count());
    }
}
