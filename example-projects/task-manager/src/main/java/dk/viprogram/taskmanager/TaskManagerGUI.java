package dk.viprogram.taskmanager;

import dk.viprogram.taskmanager.controller.TaskController;
import dk.viprogram.taskmanager.model.Category;
import dk.viprogram.taskmanager.model.Priority;
import dk.viprogram.taskmanager.model.Task;
import dk.viprogram.taskmanager.repository.InMemoryCategoryRepository;
import dk.viprogram.taskmanager.repository.InMemoryTaskRepository;
import dk.viprogram.taskmanager.view.JavaFXTaskView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * JavaFX GUI launcher for the Task Manager.
 *
 * This demonstrates the power of interface-based design:
 * - Same TaskController
 * - Same Repositories
 * - Different View implementation (JavaFX instead of Console)
 *
 * Run with: mvn javafx:run
 */
public class TaskManagerGUI extends Application {

    private TaskController controller;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Manager");

        // Create repositories (same as console version)
        var taskRepository = new InMemoryTaskRepository();
        var categoryRepository = new InMemoryCategoryRepository();

        // Seed sample data for demonstration
        seedSampleData(categoryRepository, taskRepository);

        // Create JavaFX view instead of Console view
        var view = new JavaFXTaskView(primaryStage);

        // Create controller (same as console version)
        controller = new TaskController(view, taskRepository, categoryRepository);

        // Set up the scene
        Scene scene = new Scene(view.getRoot(), 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> {
            controller.stop();
            Platform.exit();
        });

        primaryStage.show();

        // Run controller in background thread (it has blocking calls)
        Thread controllerThread = new Thread(() -> {
            controller.run();
            Platform.exit();
        });
        controllerThread.setDaemon(true);
        controllerThread.start();
    }

    public static void main(String[] args) {
        launch(args);
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
