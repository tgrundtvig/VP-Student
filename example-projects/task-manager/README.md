# Task Manager - Example Project

A complete task management application demonstrating MVC architecture with the Repository pattern.

## Purpose

This project serves as a reference implementation showing:
- **Interface-first design** - All contracts defined before implementation
- **MVC pattern** - Clean separation of Model, View, and Controller
- **Repository pattern** - Abstracted data persistence
- **Dependency injection** - Components receive their dependencies
- **Testable architecture** - Mock implementations enable thorough testing

## Features

- **Task Management**: Create, complete, and delete tasks
- **Categories**: Organize tasks into categories (Work, Personal, Shopping, etc.)
- **Priority Levels**: LOW, MEDIUM, HIGH, URGENT
- **Due Dates**: Track deadlines with overdue detection
- **Multiple Views**: Console and JavaFX implementations
- **Filtering**: View all tasks, incomplete only, overdue only, or by category

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                        TaskManagerApp                        │
│                    (Wires everything together)               │
└─────────────────────────────────────────────────────────────┘
                              │
                              │ creates & injects
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                       TaskController                         │
│                    (Coordinates everything)                  │
└─────────────────────────────────────────────────────────────┘
         │                              │
         │ uses                         │ uses
         ▼                              ▼
┌─────────────────┐          ┌─────────────────────────────────┐
│    TaskView     │          │        TaskRepository           │
│   (interface)   │          │          (interface)            │
└─────────────────┘          └─────────────────────────────────┘
         │                              │
         │ implements                   │ implements
         ▼                              ▼
┌─────────────────┐          ┌─────────────────────────────────┐
│ ConsoleTaskView │          │    InMemoryTaskRepository       │
│ JavaFXTaskView  │          │     (or JsonFileRepository)     │
│   (or MockView) │          │                                 │
└─────────────────┘          └─────────────────────────────────┘
```

## Package Structure

```
dk.viprogram.taskmanager/
├── model/                 # Domain entities (records)
│   ├── Task.java         # Main entity
│   ├── Category.java     # Task categorization
│   └── Priority.java     # Priority enum
│
├── repository/           # Data access layer
│   ├── Repository.java   # Generic interface
│   ├── TaskRepository.java       # Task-specific interface
│   ├── CategoryRepository.java   # Category-specific interface
│   ├── InMemoryTaskRepository.java      # In-memory implementation
│   └── InMemoryCategoryRepository.java  # In-memory implementation
│
├── view/                 # User interface layer
│   ├── TaskView.java         # View interface
│   ├── ConsoleTaskView.java  # Console implementation
│   ├── JavaFXTaskView.java   # JavaFX implementation
│   └── MockTaskView.java     # Test mock
│
├── controller/           # Business logic
│   └── TaskController.java   # Main controller
│
├── TaskManagerApp.java   # Console entry point
└── TaskManagerGUI.java   # JavaFX entry point
```

## Key Design Decisions

### 1. Entities as Records

```java
public record Task(
    String id,
    String title,
    String description,
    Priority priority,
    String categoryId,
    LocalDate dueDate,
    boolean completed
) { ... }
```

**Why records?**
- Immutable by default (thread-safe, predictable)
- Automatic `equals()`, `hashCode()`, `toString()`
- Clear semantic: "This is data"
- Modification returns new instances (e.g., `task.complete()`)

### 2. Repository Interface Hierarchy

```java
interface Repository<T, ID>           // Generic CRUD
    ↑
interface TaskRepository              // Task-specific queries
    ↑
class InMemoryTaskRepository          // In-memory implementation
```

**Why this hierarchy?**
- `Repository<T, ID>` can be reused across projects
- `TaskRepository` adds domain-specific queries
- Implementations can be swapped without changing business logic

### 3. View as Interface

```java
interface TaskView {
    void showTasks(List<Task> tasks, String title);
    Task promptForNewTask(List<Category> categories);
    boolean promptConfirm(String message);
    // ...
}
```

**Why interface?**
- Controller doesn't know if it's console or GUI
- `MockTaskView` enables testing without I/O
- Easy to add new UI implementations

### 4. Dependency Injection

```java
public TaskController(
    TaskView view,
    TaskRepository taskRepository,
    CategoryRepository categoryRepository
) {
    this.view = view;
    this.taskRepository = taskRepository;
    this.categoryRepository = categoryRepository;
}
```

**Why inject dependencies?**
- Controller is testable (inject mocks)
- Easy to swap implementations
- Clear declaration of what the controller needs

## Running the Application

```bash
# Build
mvn clean compile

# Run tests
mvn test

# Run the console application
mvn exec:java -Dexec.mainClass="dk.viprogram.taskmanager.TaskManagerApp"

# Run the JavaFX GUI application
mvn javafx:run
```

### Menu Options

1. **View all tasks** - Show all tasks with status and priority
2. **View incomplete tasks** - Filter to show only incomplete tasks
3. **View overdue tasks** - Filter to show only overdue tasks
4. **View tasks by category** - Select a category to filter by
5. **Add new task** - Create a task with title, description, priority, category, and due date
6. **Complete a task** - Mark a task as done
7. **Delete a task** - Remove a task
8. **Manage categories** - View and create categories
9. **Exit** - Close the application

### Sample Data

The application starts with sample data for demonstration:
- **Categories**: Work, Personal, Shopping
- **Tasks**: 5 sample tasks across different categories, including one overdue task

## Testing Strategy

### Unit Tests Use Mocks

```java
@BeforeEach
void setUp() {
    view = new MockTaskView();                    // Mock view
    taskRepository = new InMemoryTaskRepository(); // In-memory storage
    controller = new TaskController(view, taskRepository, categoryRepository);
}

@Test
void completeTaskUpdatesRepository() {
    Task task = Task.create("Test", "", Priority.MEDIUM, null, null);
    taskRepository.save(task);
    view.queueTaskSelection(task);  // Program mock to return this task

    controller.completeTask();

    assertTrue(taskRepository.findById(task.id()).get().completed());
}
```

**Key testing patterns:**
- `MockTaskView` records all displayed messages
- `MockTaskView` returns queued values for input methods
- `InMemoryRepository` provides fast, isolated storage
- Tests verify behavior, not implementation

## Extending the Project

### Adding File Persistence

Create `JsonFileTaskRepository` implementing `TaskRepository`:

```java
public class JsonFileTaskRepository implements TaskRepository {
    private final Path filePath;
    private final Gson gson;

    // Implement all TaskRepository methods...
}
```

Then change `TaskManagerApp`:

```java
var taskRepository = new JsonFileTaskRepository("tasks.json");
// Everything else stays the same!
```

### Running with JavaFX GUI

The project includes a JavaFX implementation. Run it with:

```bash
mvn javafx:run
```

Or use `TaskManagerGUI.java` as the entry point. The JavaFX view implements the same `TaskView` interface as the console version - demonstrating that the controller works identically with either UI.

## Lessons for Your Project

1. **Define interfaces first** - Know what components need to do before implementing
2. **Use records for data** - Clean, immutable entities
3. **Inject dependencies** - Makes everything testable
4. **Test against interfaces** - Tests work with any implementation
5. **Keep controller focused** - It coordinates, doesn't do I/O or storage directly
