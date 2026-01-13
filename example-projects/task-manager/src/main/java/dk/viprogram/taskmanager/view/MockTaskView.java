package dk.viprogram.taskmanager.view;

import dk.viprogram.taskmanager.model.Category;
import dk.viprogram.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Mock implementation of TaskView for testing.
 *
 * This class records all method calls and can be programmed
 * to return specific values. This enables testing the controller
 * without any real user interface.
 *
 * Key testing features:
 * - Queue up menu choices with queueMenuChoice()
 * - Queue up confirmations with queueConfirmation()
 * - Check what was displayed with getDisplayedMessages()
 */
public class MockTaskView implements TaskView {

    private final List<String> displayedMessages = new ArrayList<>();
    private final List<List<Task>> displayedTaskLists = new ArrayList<>();
    private final Queue<Integer> menuChoices = new LinkedList<>();
    private final Queue<Boolean> confirmations = new LinkedList<>();
    private final Queue<Task> tasksToCreate = new LinkedList<>();
    private final Queue<Category> categoriesToCreate = new LinkedList<>();
    private final Queue<Task> taskSelections = new LinkedList<>();
    private final Queue<String> textInputs = new LinkedList<>();

    // ==================== Programming Methods ====================

    public void queueMenuChoice(int choice) {
        menuChoices.add(choice);
    }

    public void queueConfirmation(boolean confirm) {
        confirmations.add(confirm);
    }

    public void queueTaskToCreate(Task task) {
        tasksToCreate.add(task);
    }

    public void queueCategoryToCreate(Category category) {
        categoriesToCreate.add(category);
    }

    public void queueTaskSelection(Task task) {
        taskSelections.add(task);
    }

    public void queueTextInput(String text) {
        textInputs.add(text);
    }

    // ==================== Inspection Methods ====================

    public List<String> getDisplayedMessages() {
        return new ArrayList<>(displayedMessages);
    }

    public List<List<Task>> getDisplayedTaskLists() {
        return new ArrayList<>(displayedTaskLists);
    }

    public boolean hasMessage(String message) {
        return displayedMessages.stream()
                .anyMatch(m -> m.contains(message));
    }

    // ==================== TaskView Implementation ====================

    @Override
    public void showWelcome() {
        displayedMessages.add("WELCOME");
    }

    @Override
    public int showMainMenu() {
        displayedMessages.add("MENU");
        return menuChoices.isEmpty() ? 8 : menuChoices.poll(); // Default to exit
    }

    @Override
    public void showTasks(List<Task> tasks, String title) {
        displayedMessages.add("TASKS: " + title);
        displayedTaskLists.add(new ArrayList<>(tasks));
    }

    @Override
    public void showTaskDetail(Task task) {
        displayedMessages.add("DETAIL: " + task.title());
    }

    @Override
    public void showCategories(List<Category> categories) {
        displayedMessages.add("CATEGORIES: " + categories.size());
    }

    @Override
    public void showMessage(String message) {
        displayedMessages.add("MSG: " + message);
    }

    @Override
    public void showError(String error) {
        displayedMessages.add("ERROR: " + error);
    }

    @Override
    public void showSuccess(String message) {
        displayedMessages.add("SUCCESS: " + message);
    }

    @Override
    public Task promptForNewTask(List<Category> categories) {
        displayedMessages.add("PROMPT_NEW_TASK");
        return tasksToCreate.poll();
    }

    @Override
    public Category promptForNewCategory() {
        displayedMessages.add("PROMPT_NEW_CATEGORY");
        return categoriesToCreate.poll();
    }

    @Override
    public Task promptSelectTask(List<Task> tasks, String prompt) {
        displayedMessages.add("PROMPT_SELECT: " + prompt);
        return taskSelections.poll();
    }

    @Override
    public boolean promptConfirm(String message) {
        displayedMessages.add("CONFIRM: " + message);
        return confirmations.isEmpty() ? false : confirmations.poll();
    }

    @Override
    public String promptText(String prompt) {
        displayedMessages.add("TEXT: " + prompt);
        return textInputs.isEmpty() ? "" : textInputs.poll();
    }
}
