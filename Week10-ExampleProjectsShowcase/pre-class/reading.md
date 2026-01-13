# Week 10 Pre-Class Reading: Analyzing Project Architecture

## Introduction

This week you'll study complete example projects to prepare for designing your own exam project. Instead of learning a new pattern, you'll see how all the patterns you've learned work together in real applications.

**This reading teaches you HOW to analyze projects** - skills you'll use when studying the example projects and when reviewing your peers' designs in later weeks.

## The Architecture Analysis Framework

When analyzing any software project, use this systematic approach:

### 1. Start with the Entry Point

Every application has a composition root - where everything is wired together:

```java
public class TaskManagerApp {
    public static void main(String[] args) {
        // 1. Create repositories
        var taskRepository = new InMemoryTaskRepository();
        var categoryRepository = new InMemoryCategoryRepository();

        // 2. Create view
        var view = new ConsoleTaskView();

        // 3. Wire everything to controller
        var controller = new TaskController(view, taskRepository, categoryRepository);

        // 4. Start
        controller.run();
    }
}
```

**Questions to ask:**
- What concrete implementations are created?
- What interfaces do they implement?
- What would you change to swap implementations?

### 2. Map the Interface Hierarchy

Identify all interfaces and their implementations:

```
Repository<T, ID>  (generic interface)
    ├── TaskRepository (extends with domain methods)
    │       └── InMemoryTaskRepository
    └── CategoryRepository
            └── InMemoryCategoryRepository

TaskView (interface)
    ├── ConsoleTaskView
    └── MockTaskView (for testing)
```

**Questions to ask:**
- Which interfaces are generic vs domain-specific?
- How do interfaces enable different implementations?
- Which implementations exist for testing?

### 3. Trace the Data Flow

Follow a user action through the layers:

```
User Action: "Complete a task"
    │
    ▼
[View] promptSelectTask() → returns selected Task
    │
    ▼
[Controller] receives Task, calls task.complete()
    │
    ▼
[Model] Task.complete() returns new completed Task
    │
    ▼
[Repository] save(completedTask)
    │
    ▼
[View] showSuccess("Task completed")
```

**Questions to ask:**
- How does data enter the system?
- Which layer transforms the data?
- Where is state persisted?
- How is feedback provided to the user?

### 4. Examine the Model Layer

Look at how domain entities are designed:

```java
public record Task(
    String id,
    String title,
    String description,
    Priority priority,
    String categoryId,
    LocalDate dueDate,
    boolean completed
) {
    // Factory method for creation
    public static Task create(String title, ...) { ... }

    // Methods that return new instances
    public Task complete() {
        return new Task(id, title, description, priority, categoryId, dueDate, true);
    }
}
```

**Key patterns to notice:**
- Records for immutable data
- Factory methods for creation
- Methods return new instances (immutability)
- Related data linked by ID, not object reference

### 5. Study the Test Structure

Tests reveal what the developers consider important:

```java
@BeforeEach
void setUp() {
    view = new MockTaskView();           // Mock for testing
    taskRepository = new InMemoryTaskRepository();  // Fast in-memory
    controller = new TaskController(view, taskRepository, categoryRepository);
}

@Test
void completeTaskUpdatesRepository() {
    // Arrange
    Task task = Task.create("Test", "", Priority.MEDIUM, null, null);
    taskRepository.save(task);
    view.queueTaskSelection(task);  // Program mock

    // Act
    controller.completeTask();

    // Assert
    assertTrue(taskRepository.findById(task.id()).get().completed());
}
```

**Questions to ask:**
- What is mocked and why?
- What is verified in each test?
- How are tests isolated from each other?

## Recognizing Design Patterns

As you analyze projects, look for these patterns:

### MVC (Model-View-Controller)

```
Signs of MVC:
✓ View interface with display and input methods
✓ Controller that coordinates but doesn't do I/O
✓ Model (entities) that are just data with behavior
✓ Clear separation - View doesn't know about Repository
```

### Repository Pattern

```
Signs of Repository:
✓ Generic Repository<T, ID> interface
✓ save(), findById(), findAll(), delete() methods
✓ In-memory implementation for testing
✓ File/database implementation for production
✓ Services don't know HOW data is stored
```

### Strategy Pattern

```
Signs of Strategy:
✓ Interface for an algorithm (e.g., ScoringStrategy)
✓ Multiple implementations with different behavior
✓ Strategy injected into the class that uses it
✓ Runtime flexibility in behavior
```

### Factory Pattern

```
Signs of Factory:
✓ Static create() methods on records/classes
✓ Construction logic encapsulated
✓ Clients don't use constructors directly
```

## Architecture Quality Indicators

### Signs of Good Architecture

1. **Low coupling:** Classes depend on interfaces, not implementations
2. **High cohesion:** Each class has a single responsibility
3. **Testability:** Can test with mocks, no real I/O needed
4. **Flexibility:** Can swap implementations without changing code
5. **Clear layers:** Model → Repository → Controller → View

### Signs of Poor Architecture

1. **Direct dependencies:** `new ConcreteClass()` everywhere
2. **God classes:** One class doing everything
3. **Untestable:** Must use real files/database to test
4. **Rigid:** Changing one thing requires changing many things
5. **Tangled layers:** View calling Repository directly

## Preparing for Your Project

As you study the examples, think about your own project:

### Questions to Answer

1. **What entities will you have?**
   - What data does your application manage?
   - What are the key properties?
   - How are entities related?

2. **What operations will you support?**
   - What can users do?
   - What CRUD operations are needed?
   - What queries/filters are needed?

3. **What interfaces will you need?**
   - View interface (what can be displayed/input?)
   - Repository interface (what data operations?)
   - Any strategy interfaces (configurable behavior?)

4. **How will you test?**
   - What mock view do you need?
   - What in-memory repository do you need?
   - What scenarios should tests cover?

## Analysis Exercise

When you study each example project:

1. **Draw the interface hierarchy** - boxes for interfaces, arrows for implementations
2. **Trace one user action** - from input through all layers to output
3. **Count the interfaces** - how many enable flexibility?
4. **Read one test** - understand what it verifies and how
5. **Identify what to steal** - which patterns would work in your project?

## Summary

Analyzing architecture is a skill that improves with practice. Use this framework:

1. Start at the entry point (composition root)
2. Map interfaces and implementations
3. Trace data flow through layers
4. Examine model design
5. Study test structure

**Remember:** Good architecture isn't about following rules - it's about making the code **testable, flexible, and understandable**.

---

**Next step:** Apply this framework to the Task Manager and Quiz App example projects!
