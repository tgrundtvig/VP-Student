package dk.viprogram.taskmanager;

import dk.viprogram.taskmanager.controller.TaskController;
import dk.viprogram.taskmanager.model.Category;
import dk.viprogram.taskmanager.model.Priority;
import dk.viprogram.taskmanager.model.Task;
import dk.viprogram.taskmanager.repository.InMemoryCategoryRepository;
import dk.viprogram.taskmanager.repository.InMemoryTaskRepository;
import dk.viprogram.taskmanager.view.MockTaskView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for TaskController.
 *
 * These tests demonstrate the power of interface-based design:
 * - We use MockTaskView instead of real console I/O
 * - We use InMemoryRepository instead of file storage
 * - Tests run instantly with no I/O
 * - Tests are isolated and repeatable
 */
class TaskControllerTest {

    private MockTaskView view;
    private InMemoryTaskRepository taskRepository;
    private InMemoryCategoryRepository categoryRepository;
    private TaskController controller;

    @BeforeEach
    void setUp() {
        view = new MockTaskView();
        taskRepository = new InMemoryTaskRepository();
        categoryRepository = new InMemoryCategoryRepository();
        controller = new TaskController(view, taskRepository, categoryRepository);
    }

    // ==================== Task Display Tests ====================

    @Test
    @DisplayName("Show all tasks displays empty list when no tasks exist")
    void showAllTasksWhenEmpty() {
        controller.showAllTasks();

        assertTrue(view.hasMessage("All Tasks (0)"));
        assertEquals(1, view.getDisplayedTaskLists().size());
        assertTrue(view.getDisplayedTaskLists().get(0).isEmpty());
    }

    @Test
    @DisplayName("Show all tasks displays existing tasks")
    void showAllTasksWithTasks() {
        // Arrange
        Task task1 = Task.create("Task 1", "Description", Priority.HIGH, null, null);
        Task task2 = Task.create("Task 2", "Description", Priority.LOW, null, null);
        taskRepository.save(task1);
        taskRepository.save(task2);

        // Act
        controller.showAllTasks();

        // Assert
        assertTrue(view.hasMessage("All Tasks (2)"));
        assertEquals(2, view.getDisplayedTaskLists().get(0).size());
    }

    @Test
    @DisplayName("Show incomplete tasks filters out completed tasks")
    void showIncompleteTasksFiltersCompleted() {
        // Arrange
        Task incomplete = Task.create("Incomplete", "", Priority.MEDIUM, null, null);
        Task completed = Task.create("Completed", "", Priority.MEDIUM, null, null).complete();
        taskRepository.save(incomplete);
        taskRepository.save(completed);

        // Act
        controller.showIncompleteTasks();

        // Assert
        var displayedTasks = view.getDisplayedTaskLists().get(0);
        assertEquals(1, displayedTasks.size());
        assertEquals("Incomplete", displayedTasks.get(0).title());
    }

    @Test
    @DisplayName("Show overdue tasks only shows overdue tasks")
    void showOverdueTasksFiltersCorrectly() {
        // Arrange
        Task overdue = Task.create("Overdue", "", Priority.HIGH, null,
                LocalDate.now().minusDays(1));
        Task future = Task.create("Future", "", Priority.MEDIUM, null,
                LocalDate.now().plusDays(1));
        Task noDueDate = Task.create("No date", "", Priority.LOW, null, null);
        taskRepository.save(overdue);
        taskRepository.save(future);
        taskRepository.save(noDueDate);

        // Act
        controller.showOverdueTasks();

        // Assert
        var displayedTasks = view.getDisplayedTaskLists().get(0);
        assertEquals(1, displayedTasks.size());
        assertEquals("Overdue", displayedTasks.get(0).title());
    }

    // ==================== Task Creation Tests ====================

    @Test
    @DisplayName("Add new task saves to repository")
    void addNewTaskSavesToRepository() {
        // Arrange
        Task newTask = Task.create("New Task", "Description", Priority.HIGH, null, null);
        view.queueTaskToCreate(newTask);

        // Act
        controller.addNewTask();

        // Assert
        assertEquals(1, taskRepository.count());
        assertTrue(view.hasMessage("SUCCESS: Task created"));
    }

    @Test
    @DisplayName("Add new task with null does not save")
    void addNewTaskWithNullDoesNotSave() {
        // Arrange - view returns null (user cancelled)
        view.queueTaskToCreate(null);

        // Act
        controller.addNewTask();

        // Assert
        assertEquals(0, taskRepository.count());
        assertFalse(view.hasMessage("SUCCESS"));
    }

    // ==================== Task Completion Tests ====================

    @Test
    @DisplayName("Complete task updates repository")
    void completeTaskUpdatesRepository() {
        // Arrange
        Task task = Task.create("To Complete", "", Priority.MEDIUM, null, null);
        taskRepository.save(task);
        view.queueTaskSelection(task);

        // Act
        controller.completeTask();

        // Assert
        Task updated = taskRepository.findById(task.id()).orElseThrow();
        assertTrue(updated.completed());
        assertTrue(view.hasMessage("SUCCESS: Task completed"));
    }

    @Test
    @DisplayName("Complete task with no selection does nothing")
    void completeTaskWithNoSelectionDoesNothing() {
        // Arrange
        Task task = Task.create("Task", "", Priority.MEDIUM, null, null);
        taskRepository.save(task);
        view.queueTaskSelection(null); // User cancels selection

        // Act
        controller.completeTask();

        // Assert
        Task unchanged = taskRepository.findById(task.id()).orElseThrow();
        assertFalse(unchanged.completed());
    }

    // ==================== Task Deletion Tests ====================

    @Test
    @DisplayName("Delete task with confirmation removes from repository")
    void deleteTaskWithConfirmationRemoves() {
        // Arrange
        Task task = Task.create("To Delete", "", Priority.LOW, null, null);
        taskRepository.save(task);
        view.queueTaskSelection(task);
        view.queueConfirmation(true);

        // Act
        controller.deleteTask();

        // Assert
        assertEquals(0, taskRepository.count());
        assertTrue(view.hasMessage("SUCCESS: Task deleted"));
    }

    @Test
    @DisplayName("Delete task without confirmation keeps task")
    void deleteTaskWithoutConfirmationKeepsTask() {
        // Arrange
        Task task = Task.create("Keep Me", "", Priority.LOW, null, null);
        taskRepository.save(task);
        view.queueTaskSelection(task);
        view.queueConfirmation(false);

        // Act
        controller.deleteTask();

        // Assert
        assertEquals(1, taskRepository.count());
        assertFalse(view.hasMessage("SUCCESS: Task deleted"));
    }

    // ==================== Category Tests ====================

    @Test
    @DisplayName("Add category saves to repository")
    void addCategorySavesToRepository() {
        // Arrange
        Category category = Category.of("Work");
        view.queueConfirmation(true);
        view.queueCategoryToCreate(category);

        // Act
        controller.manageCategories();

        // Assert
        assertEquals(1, categoryRepository.count());
        assertTrue(view.hasMessage("SUCCESS: Category created"));
    }

    // ==================== Menu Tests ====================

    @Test
    @DisplayName("Invalid menu choice shows error")
    void invalidMenuChoiceShowsError() {
        // Act
        controller.handleMenuChoice(99);

        // Assert
        assertTrue(view.hasMessage("ERROR: Invalid choice"));
    }

    @Test
    @DisplayName("Menu choice 8 stops controller")
    void menuChoice8StopsController() {
        // Act
        controller.handleMenuChoice(8);

        // Assert
        assertFalse(controller.isRunning());
    }
}
