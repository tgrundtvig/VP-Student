package dk.viprogram.taskmanager;

import dk.viprogram.taskmanager.controller.TaskController;
import dk.viprogram.taskmanager.repository.InMemoryCategoryRepository;
import dk.viprogram.taskmanager.repository.InMemoryTaskRepository;
import dk.viprogram.taskmanager.view.JavaFXTaskView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
}
