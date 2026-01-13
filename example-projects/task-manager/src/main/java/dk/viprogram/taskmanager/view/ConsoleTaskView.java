package dk.viprogram.taskmanager.view;

import dk.viprogram.taskmanager.model.Category;
import dk.viprogram.taskmanager.model.Priority;
import dk.viprogram.taskmanager.model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based implementation of TaskView.
 *
 * This is a simple text-based UI. The same controller could work
 * with a JavaFX view, a web view, or any other implementation.
 */
public class ConsoleTaskView implements TaskView {

    private final Scanner scanner;

    public ConsoleTaskView() {
        this.scanner = new Scanner(System.in);
    }

    public ConsoleTaskView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showWelcome() {
        System.out.println("========================================");
        System.out.println("       TASK MANAGER");
        System.out.println("========================================");
        System.out.println();
    }

    @Override
    public int showMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View all tasks");
        System.out.println("2. View incomplete tasks");
        System.out.println("3. View overdue tasks");
        System.out.println("4. Add new task");
        System.out.println("5. Complete a task");
        System.out.println("6. Delete a task");
        System.out.println("7. Manage categories");
        System.out.println("8. Exit");
        System.out.print("\nChoice: ");

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public void showTasks(List<Task> tasks, String title) {
        System.out.println("\n--- " + title + " ---");
        if (tasks.isEmpty()) {
            System.out.println("(No tasks)");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.completed() ? "[X]" : "[ ]";
            String overdue = task.isOverdue() ? " (OVERDUE)" : "";
            System.out.printf("%d. %s %s [%s]%s%n",
                    i + 1, status, task.title(), task.priority(), overdue);
        }
    }

    @Override
    public void showTaskDetail(Task task) {
        System.out.println("\n--- Task Details ---");
        System.out.println("Title:       " + task.title());
        System.out.println("Description: " + task.description());
        System.out.println("Priority:    " + task.priority());
        System.out.println("Due Date:    " + (task.dueDate() != null ? task.dueDate() : "None"));
        System.out.println("Status:      " + (task.completed() ? "Completed" : "Incomplete"));
        if (task.isOverdue()) {
            System.out.println("*** OVERDUE ***");
        }
    }

    @Override
    public void showCategories(List<Category> categories) {
        System.out.println("\n--- Categories ---");
        if (categories.isEmpty()) {
            System.out.println("(No categories)");
            return;
        }

        for (int i = 0; i < categories.size(); i++) {
            Category cat = categories.get(i);
            System.out.printf("%d. %s%n", i + 1, cat.name());
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String error) {
        System.out.println("ERROR: " + error);
    }

    @Override
    public void showSuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    @Override
    public Task promptForNewTask(List<Category> categories) {
        System.out.println("\n--- Create New Task ---");

        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            showError("Title cannot be empty");
            return null;
        }

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        System.out.println("Priority (1=LOW, 2=MEDIUM, 3=HIGH, 4=URGENT): ");
        Priority priority;
        try {
            int p = Integer.parseInt(scanner.nextLine().trim());
            priority = switch (p) {
                case 1 -> Priority.LOW;
                case 2 -> Priority.MEDIUM;
                case 3 -> Priority.HIGH;
                case 4 -> Priority.URGENT;
                default -> Priority.MEDIUM;
            };
        } catch (NumberFormatException e) {
            priority = Priority.MEDIUM;
        }

        String categoryId = null;
        if (!categories.isEmpty()) {
            showCategories(categories);
            System.out.print("Select category (0 for none): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice > 0 && choice <= categories.size()) {
                    categoryId = categories.get(choice - 1).id();
                }
            } catch (NumberFormatException e) {
                // No category
            }
        }

        System.out.print("Due date (YYYY-MM-DD, or empty for none): ");
        String dateStr = scanner.nextLine().trim();
        LocalDate dueDate = null;
        if (!dateStr.isEmpty()) {
            try {
                dueDate = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                showError("Invalid date format, skipping due date");
            }
        }

        return Task.create(title, description, priority, categoryId, dueDate);
    }

    @Override
    public Category promptForNewCategory() {
        System.out.println("\n--- Create New Category ---");

        System.out.print("Category name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            showError("Name cannot be empty");
            return null;
        }

        return Category.of(name);
    }

    @Override
    public Task promptSelectTask(List<Task> tasks, String prompt) {
        if (tasks.isEmpty()) {
            showMessage("No tasks available");
            return null;
        }

        showTasks(tasks, "Select a Task");
        System.out.print(prompt + " (0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice > 0 && choice <= tasks.size()) {
                return tasks.get(choice - 1);
            }
        } catch (NumberFormatException e) {
            // Cancelled
        }
        return null;
    }

    @Override
    public boolean promptConfirm(String message) {
        System.out.print(message + " (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }

    @Override
    public String promptText(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }
}
