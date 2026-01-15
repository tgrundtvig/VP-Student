package dk.viprogram.taskmanager.view;

import dk.viprogram.taskmanager.model.Category;
import dk.viprogram.taskmanager.model.Task;

import java.util.List;

/**
 * View interface for the Task Manager.
 *
 * This interface defines WHAT the view can display, not HOW it displays it.
 * This allows us to:
 * - Swap between Console, GUI, or Web views
 * - Test controllers without any UI
 * - Develop business logic before the UI exists
 */
public interface TaskView {

    // ==================== Display Methods ====================

    /**
     * Displays a welcome message.
     */
    void showWelcome();

    /**
     * Displays the main menu and returns the user's choice.
     *
     * @return the menu option selected (1-based)
     */
    int showMainMenu();

    /**
     * Displays a list of tasks.
     *
     * @param tasks the tasks to display
     * @param title a title for the list (e.g., "All Tasks", "Overdue Tasks")
     */
    void showTasks(List<Task> tasks, String title);

    /**
     * Displays a single task in detail.
     *
     * @param task the task to display
     */
    void showTaskDetail(Task task);

    /**
     * Displays a list of categories.
     *
     * @param categories the categories to display
     */
    void showCategories(List<Category> categories);

    /**
     * Displays a message to the user.
     *
     * @param message the message to display
     */
    void showMessage(String message);

    /**
     * Displays an error message.
     *
     * @param error the error message
     */
    void showError(String error);

    /**
     * Displays a success message.
     *
     * @param message the success message
     */
    void showSuccess(String message);

    // ==================== Input Methods ====================

    /**
     * Prompts for and returns a new task's details.
     *
     * @param categories available categories for selection
     * @return the created Task, or null if cancelled
     */
    Task promptForNewTask(List<Category> categories);

    /**
     * Prompts for and returns a new category.
     *
     * @return the created Category, or null if cancelled
     */
    Category promptForNewCategory();

    /**
     * Prompts user to select a category from a list.
     *
     * @param categories the categories to choose from
     * @param prompt the prompt to display
     * @return the selected category, or null if cancelled
     */
    Category promptSelectCategory(List<Category> categories, String prompt);

    /**
     * Prompts user to select a task from a list.
     *
     * @param tasks the tasks to choose from
     * @param prompt the prompt to display
     * @return the selected task, or null if cancelled
     */
    Task promptSelectTask(List<Task> tasks, String prompt);

    /**
     * Prompts for confirmation.
     *
     * @param message the confirmation message
     * @return true if confirmed, false otherwise
     */
    boolean promptConfirm(String message);

    /**
     * Prompts for a text input.
     *
     * @param prompt the prompt to display
     * @return the entered text
     */
    String promptText(String prompt);
}
