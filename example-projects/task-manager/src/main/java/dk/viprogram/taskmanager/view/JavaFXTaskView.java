package dk.viprogram.taskmanager.view;

import dk.viprogram.taskmanager.model.Category;
import dk.viprogram.taskmanager.model.Priority;
import dk.viprogram.taskmanager.model.Task;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * JavaFX implementation of TaskView.
 *
 * This demonstrates that the SAME controller can work with completely
 * different UI implementations - console or GUI.
 *
 * Note: GUI views are more complex because they're event-driven rather
 * than sequential like console views.
 */
public class JavaFXTaskView implements TaskView {

    private final Stage stage;
    private final VBox root;
    private final ListView<Task> taskListView;
    private final Label statusLabel;
    private final TextArea messageArea;

    // For synchronizing with the controller (which expects blocking calls)
    private final AtomicInteger menuChoice = new AtomicInteger(-1);
    private final AtomicReference<Task> selectedTask = new AtomicReference<>();
    private final AtomicReference<Task> createdTask = new AtomicReference<>();
    private final AtomicReference<Category> createdCategory = new AtomicReference<>();
    private final AtomicReference<Boolean> confirmation = new AtomicReference<>();
    private CountDownLatch menuLatch;
    private CountDownLatch taskSelectLatch;
    private CountDownLatch confirmLatch;

    public JavaFXTaskView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(10);
        this.root.setPadding(new Insets(15));

        // Title
        Label titleLabel = new Label("Task Manager");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Task list
        taskListView = new ListView<>();
        taskListView.setPrefHeight(300);
        taskListView.setCellFactory(lv -> new TaskListCell());

        // Status label
        statusLabel = new Label("Ready");
        statusLabel.setStyle("-fx-font-style: italic;");

        // Message area
        messageArea = new TextArea();
        messageArea.setPrefRowCount(3);
        messageArea.setEditable(false);

        // Buttons
        HBox buttonBox = createButtonBox();

        root.getChildren().addAll(titleLabel, taskListView, buttonBox, statusLabel, messageArea);
    }

    private HBox createButtonBox() {
        Button viewAllBtn = new Button("View All");
        viewAllBtn.setOnAction(e -> setMenuChoice(1));

        Button viewIncompleteBtn = new Button("Incomplete");
        viewIncompleteBtn.setOnAction(e -> setMenuChoice(2));

        Button viewOverdueBtn = new Button("Overdue");
        viewOverdueBtn.setOnAction(e -> setMenuChoice(3));

        Button viewByCategoryBtn = new Button("By Category");
        viewByCategoryBtn.setOnAction(e -> setMenuChoice(4));

        Button addBtn = new Button("Add Task");
        addBtn.setOnAction(e -> setMenuChoice(5));

        Button completeBtn = new Button("Complete");
        completeBtn.setOnAction(e -> setMenuChoice(6));

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> setMenuChoice(7));

        Button categoriesBtn = new Button("Categories");
        categoriesBtn.setOnAction(e -> setMenuChoice(8));

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> setMenuChoice(9));

        HBox box = new HBox(10);
        box.getChildren().addAll(viewAllBtn, viewIncompleteBtn, viewOverdueBtn, viewByCategoryBtn,
                addBtn, completeBtn, deleteBtn, categoriesBtn, exitBtn);
        return box;
    }

    public VBox getRoot() {
        return root;
    }

    private void setMenuChoice(int choice) {
        menuChoice.set(choice);
        if (menuLatch != null) {
            menuLatch.countDown();
        }
    }

    // ==================== TaskView Implementation ====================

    @Override
    public void showWelcome() {
        Platform.runLater(() -> {
            messageArea.setText("Welcome to Task Manager!\nSelect an action from the buttons above.");
        });
    }

    @Override
    public int showMainMenu() {
        menuChoice.set(-1);
        menuLatch = new CountDownLatch(1);
        Platform.runLater(() -> statusLabel.setText("Select an action..."));

        try {
            menuLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 9; // Exit
        }
        return menuChoice.get();
    }

    @Override
    public void showTasks(List<Task> tasks, String title) {
        Platform.runLater(() -> {
            taskListView.getItems().clear();
            taskListView.getItems().addAll(tasks);
            statusLabel.setText(title);
        });
    }

    @Override
    public void showTaskDetail(Task task) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Task Details");
            alert.setHeaderText(task.title());
            alert.setContentText(String.format(
                    "Description: %s\nPriority: %s\nDue: %s\nStatus: %s",
                    task.description(),
                    task.priority(),
                    task.dueDate() != null ? task.dueDate().toString() : "None",
                    task.completed() ? "Completed" : "Incomplete"
            ));
            alert.showAndWait();
        });
    }

    @Override
    public void showCategories(List<Category> categories) {
        Platform.runLater(() -> {
            StringBuilder sb = new StringBuilder("Categories:\n");
            for (Category cat : categories) {
                sb.append("- ").append(cat.name()).append("\n");
            }
            messageArea.setText(sb.toString());
        });
    }

    @Override
    public void showMessage(String message) {
        Platform.runLater(() -> messageArea.appendText(message + "\n"));
    }

    @Override
    public void showError(String error) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(error);
            alert.showAndWait();
        });
    }

    @Override
    public void showSuccess(String message) {
        Platform.runLater(() -> {
            statusLabel.setText("SUCCESS: " + message);
            messageArea.appendText("SUCCESS: " + message + "\n");
        });
    }

    @Override
    public Task promptForNewTask(List<Category> categories) {
        CompletableFuture<Task> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            Dialog<Task> dialog = new Dialog<>();
            dialog.setTitle("New Task");
            dialog.setHeaderText("Create a new task");

            ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField titleField = new TextField();
            titleField.setPromptText("Title");
            TextField descField = new TextField();
            descField.setPromptText("Description");
            ComboBox<Priority> priorityBox = new ComboBox<>();
            priorityBox.getItems().addAll(Priority.values());
            priorityBox.setValue(Priority.MEDIUM);
            ComboBox<Category> categoryBox = new ComboBox<>();
            categoryBox.getItems().add(null); // Option for no category
            categoryBox.getItems().addAll(categories);
            DatePicker datePicker = new DatePicker();

            grid.add(new Label("Title:"), 0, 0);
            grid.add(titleField, 1, 0);
            grid.add(new Label("Description:"), 0, 1);
            grid.add(descField, 1, 1);
            grid.add(new Label("Priority:"), 0, 2);
            grid.add(priorityBox, 1, 2);
            grid.add(new Label("Category:"), 0, 3);
            grid.add(categoryBox, 1, 3);
            grid.add(new Label("Due Date:"), 0, 4);
            grid.add(datePicker, 1, 4);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == createButtonType) {
                    String title = titleField.getText().trim();
                    if (title.isEmpty()) {
                        return null;
                    }
                    Category selectedCategory = categoryBox.getValue();
                    return Task.create(
                            title,
                            descField.getText().trim(),
                            priorityBox.getValue(),
                            selectedCategory != null ? selectedCategory.id() : null,
                            datePicker.getValue()
                    );
                }
                return null;
            });

            Optional<Task> result = dialog.showAndWait();
            future.complete(result.orElse(null));
        });

        try {
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Category promptForNewCategory() {
        CompletableFuture<Category> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("New Category");
            dialog.setHeaderText("Create a new category");
            dialog.setContentText("Category name:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && !result.get().trim().isEmpty()) {
                future.complete(Category.of(result.get().trim()));
            } else {
                future.complete(null);
            }
        });

        try {
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Category promptSelectCategory(List<Category> categories, String prompt) {
        if (categories.isEmpty()) {
            showMessage("No categories available");
            return null;
        }

        CompletableFuture<Category> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            ChoiceDialog<Category> dialog = new ChoiceDialog<>(categories.get(0), categories);
            dialog.setTitle("Select Category");
            dialog.setHeaderText(prompt);
            dialog.setContentText("Category:");

            Optional<Category> result = dialog.showAndWait();
            future.complete(result.orElse(null));
        });

        try {
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Task promptSelectTask(List<Task> tasks, String prompt) {
        if (tasks.isEmpty()) {
            showMessage("No tasks available");
            return null;
        }

        CompletableFuture<Task> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            ChoiceDialog<Task> dialog = new ChoiceDialog<>(tasks.get(0), tasks);
            dialog.setTitle("Select Task");
            dialog.setHeaderText(prompt);
            dialog.setContentText("Task:");

            Optional<Task> result = dialog.showAndWait();
            future.complete(result.orElse(null));
        });

        try {
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean promptConfirm(String message) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText(null);
            alert.setContentText(message);

            Optional<ButtonType> result = alert.showAndWait();
            future.complete(result.isPresent() && result.get() == ButtonType.OK);
        });

        try {
            return future.get();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String promptText(String prompt) {
        CompletableFuture<String> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Input");
            dialog.setHeaderText(null);
            dialog.setContentText(prompt);

            Optional<String> result = dialog.showAndWait();
            future.complete(result.orElse(""));
        });

        try {
            return future.get();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Custom cell for displaying tasks in the list.
     */
    private static class TaskListCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);
            if (empty || task == null) {
                setText(null);
                setGraphic(null);
            } else {
                String status = task.completed() ? "[X]" : "[ ]";
                String overdue = task.isOverdue() ? " (OVERDUE)" : "";
                setText(String.format("%s %s [%s]%s",
                        status, task.title(), task.priority(), overdue));

                if (task.isOverdue()) {
                    setStyle("-fx-text-fill: red;");
                } else if (task.completed()) {
                    setStyle("-fx-text-fill: green;");
                } else {
                    setStyle("");
                }
            }
        }
    }
}
