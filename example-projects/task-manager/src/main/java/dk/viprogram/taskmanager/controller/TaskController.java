package dk.viprogram.taskmanager.controller;

import dk.viprogram.taskmanager.model.Category;
import dk.viprogram.taskmanager.model.Task;
import dk.viprogram.taskmanager.repository.CategoryRepository;
import dk.viprogram.taskmanager.repository.TaskRepository;
import dk.viprogram.taskmanager.view.TaskView;

import java.util.List;

/**
 * Controller for the Task Manager application.
 *
 * The controller:
 * - Receives input through the View interface
 * - Processes business logic
 * - Persists data through Repository interfaces
 * - Updates the View with results
 *
 * Note: The controller doesn't know if it's using:
 * - A console or GUI view
 * - In-memory or file-based storage
 *
 * This makes it highly testable and flexible.
 */
public class TaskController {

    private final TaskView view;
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private boolean running;

    /**
     * Creates a new TaskController.
     *
     * All dependencies are injected - the controller doesn't create them.
     * This is Dependency Injection in action!
     */
    public TaskController(TaskView view,
                          TaskRepository taskRepository,
                          CategoryRepository categoryRepository) {
        this.view = view;
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.running = false;
    }

    /**
     * Starts the main application loop.
     */
    public void run() {
        running = true;
        view.showWelcome();

        while (running) {
            int choice = view.showMainMenu();
            handleMenuChoice(choice);
        }

        view.showMessage("Goodbye!");
    }

    /**
     * Handles a menu choice.
     * Public for testing purposes.
     */
    public void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> showAllTasks();
            case 2 -> showIncompleteTasks();
            case 3 -> showOverdueTasks();
            case 4 -> showTasksByCategory();
            case 5 -> addNewTask();
            case 6 -> completeTask();
            case 7 -> deleteTask();
            case 8 -> manageCategories();
            case 9 -> running = false;
            default -> view.showError("Invalid choice. Please try again.");
        }
    }

    /**
     * Displays all tasks.
     */
    public void showAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        view.showTasks(tasks, "All Tasks (" + tasks.size() + ")");
    }

    /**
     * Displays incomplete tasks.
     */
    public void showIncompleteTasks() {
        List<Task> tasks = taskRepository.findIncomplete();
        view.showTasks(tasks, "Incomplete Tasks (" + tasks.size() + ")");
    }

    /**
     * Displays overdue tasks.
     */
    public void showOverdueTasks() {
        List<Task> tasks = taskRepository.findOverdue();
        view.showTasks(tasks, "Overdue Tasks (" + tasks.size() + ")");
    }

    /**
     * Displays tasks filtered by category.
     */
    public void showTasksByCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            view.showMessage("No categories available. Create some first!");
            return;
        }

        Category selected = view.promptSelectCategory(categories, "Select category to filter by");
        if (selected != null) {
            List<Task> tasks = taskRepository.findByCategory(selected.id());
            view.showTasks(tasks, "Tasks in '" + selected.name() + "' (" + tasks.size() + ")");
        }
    }

    /**
     * Adds a new task.
     */
    public void addNewTask() {
        List<Category> categories = categoryRepository.findAll();
        Task task = view.promptForNewTask(categories);

        if (task != null) {
            taskRepository.save(task);
            view.showSuccess("Task created: " + task.title());
            showAllTasks(); // Refresh the task list
        }
    }

    /**
     * Marks a task as complete.
     */
    public void completeTask() {
        List<Task> incomplete = taskRepository.findIncomplete();
        Task selected = view.promptSelectTask(incomplete, "Select task to complete");

        if (selected != null) {
            Task completed = selected.complete();
            taskRepository.save(completed);
            view.showSuccess("Task completed: " + completed.title());
            showAllTasks(); // Refresh the task list
        }
    }

    /**
     * Deletes a task.
     */
    public void deleteTask() {
        List<Task> all = taskRepository.findAll();
        Task selected = view.promptSelectTask(all, "Select task to delete");

        if (selected != null) {
            if (view.promptConfirm("Delete '" + selected.title() + "'?")) {
                taskRepository.deleteById(selected.id());
                view.showSuccess("Task deleted");
                showAllTasks(); // Refresh the task list
            }
        }
    }

    /**
     * Manages categories (sub-menu).
     */
    public void manageCategories() {
        List<Category> categories = categoryRepository.findAll();
        view.showCategories(categories);

        if (view.promptConfirm("Add new category?")) {
            Category category = view.promptForNewCategory();
            if (category != null) {
                categoryRepository.save(category);
                view.showSuccess("Category created: " + category.name());
            }
        }
    }

    /**
     * Stops the application loop.
     * Useful for testing.
     */
    public void stop() {
        running = false;
    }

    /**
     * Checks if the controller is running.
     */
    public boolean isRunning() {
        return running;
    }
}
