package dk.viprogram.taskmanager;

import dk.viprogram.taskmanager.controller.TaskController;
import dk.viprogram.taskmanager.model.Category;
import dk.viprogram.taskmanager.model.Priority;
import dk.viprogram.taskmanager.model.Task;
import dk.viprogram.taskmanager.repository.InMemoryCategoryRepository;
import dk.viprogram.taskmanager.repository.InMemoryTaskRepository;
import dk.viprogram.taskmanager.view.ConsoleTaskView;

import java.time.LocalDate;

/**
 * Main entry point for the Task Manager application.
 *
 * This class demonstrates Dependency Injection:
 * - We create the concrete implementations here
 * - We inject them into the controller
 * - The controller only knows about interfaces
 *
 * To switch to file-based storage, we would only change this class:
 * - Replace InMemoryTaskRepository with JsonFileTaskRepository
 * - The rest of the application stays the same!
 */
public class TaskManagerApp {

    public static void main(String[] args) {
        // Create repositories (could swap these for file-based versions)
        var taskRepository = new InMemoryTaskRepository();
        var categoryRepository = new InMemoryCategoryRepository();

        // Seed sample data for demonstration
        seedSampleData(categoryRepository, taskRepository);

        // Create view (could swap for GUI version)
        var view = new ConsoleTaskView();

        // Create controller with injected dependencies
        var controller = new TaskController(view, taskRepository, categoryRepository);

        // Run the application
        controller.run();
    }

    /**
     * Creates sample categories and tasks for demonstration.
     */
    private static void seedSampleData(InMemoryCategoryRepository categoryRepository,
                                        InMemoryTaskRepository taskRepository) {
        // Create categories
        Category work = Category.of("Work");
        Category personal = Category.of("Personal");
        Category shopping = Category.of("Shopping");

        categoryRepository.save(work);
        categoryRepository.save(personal);
        categoryRepository.save(shopping);

        // Create tasks with categories
        taskRepository.save(Task.create(
                "Finish project report",
                "Complete the quarterly report for management",
                Priority.HIGH,
                work.id(),
                LocalDate.now().plusDays(3)
        ));

        taskRepository.save(Task.create(
                "Review pull requests",
                "Check pending PRs on GitHub",
                Priority.MEDIUM,
                work.id(),
                LocalDate.now().plusDays(1)
        ));

        taskRepository.save(Task.create(
                "Schedule dentist appointment",
                "Annual checkup",
                Priority.LOW,
                personal.id(),
                LocalDate.now().plusWeeks(2)
        ));

        taskRepository.save(Task.create(
                "Buy groceries",
                "Milk, eggs, bread, vegetables",
                Priority.MEDIUM,
                shopping.id(),
                LocalDate.now()
        ));

        taskRepository.save(Task.create(
                "Call mom",
                "Weekly catch-up call",
                Priority.MEDIUM,
                personal.id(),
                LocalDate.now().minusDays(1)  // Overdue!
        ));
    }
}
