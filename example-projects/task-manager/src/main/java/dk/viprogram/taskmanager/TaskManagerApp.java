package dk.viprogram.taskmanager;

import dk.viprogram.taskmanager.controller.TaskController;
import dk.viprogram.taskmanager.repository.InMemoryCategoryRepository;
import dk.viprogram.taskmanager.repository.InMemoryTaskRepository;
import dk.viprogram.taskmanager.view.ConsoleTaskView;

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

        // Create view (could swap for GUI version)
        var view = new ConsoleTaskView();

        // Create controller with injected dependencies
        var controller = new TaskController(view, taskRepository, categoryRepository);

        // Run the application
        controller.run();
    }
}
