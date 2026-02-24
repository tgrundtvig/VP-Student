# Exercise 1: Design Task Tracker Interfaces

## Goal

Design the interfaces and records for a simple task tracker application. You will **not implement anything** — only define contracts. This is pure interface-first design.

## Time Estimate

30-40 minutes.

## What You Will Learn

- **Designing interfaces before implementation**: The hardest step, and the most important
- **Records for data, interfaces for behavior**: The decision rule in practice
- **JavaDoc as design documentation**: Writing method contracts that tell implementers exactly what to do

## The Domain

A task tracker lets users:
- Create tasks with a title and description
- Mark tasks as complete
- List all tasks, or filter by status (e.g., only incomplete tasks)
- Display tasks in different formats (short summary, detailed view)

You are designing the contracts for this system. Someone else (you, next exercise) will write code against these contracts.

## Instructions

### Step 1: Design the `Task` record

A task has:
- An `id` (int)
- A `title` (String)
- A `description` (String)
- A `completed` status (boolean)

Create the record. Think about: is a record the right choice here? (Hint: a task is just data — an id, a title, a description, a status. Records are for data.)

```java
/**
 * An immutable snapshot of a task.
 */
public record Task(int id, String title, String description, boolean completed)
{
}
```

### Step 2: Design the `TaskStore` interface

A task store is responsible for CRUD operations (Create, Read, Update, Delete). Design these methods:

- `Task add(String title, String description)` — create a new task, assign it an ID, return it
- `List<Task> getAll()` — return all tasks
- `Task getById(int id)` — return a specific task, or null if not found
- `Task markComplete(int id)` — mark a task as complete, return the updated task
- `boolean remove(int id)` — remove a task, return true if it existed

Write JavaDoc for each method. The JavaDoc should be precise enough that someone could implement the interface from the documentation alone.

### Step 3: Design the `TaskFilter` interface

A task filter decides whether a task should be included in a result:

```java
/**
 * A predicate that decides whether a task matches some criteria.
 */
public interface TaskFilter
{
    /**
     * Test whether the given task matches this filter.
     *
     * @param task the task to test
     * @return true if the task matches, false otherwise
     */
    boolean matches(Task task);
}
```

Think about what implementations would be useful: `CompletedFilter`, `IncompleteFilter`, `TitleContainsFilter`. You don't need to implement them — just think about what they'd do.

### Step 4: Design the `TaskFormatter` interface

A task formatter converts a task into a string for display:

```java
public interface TaskFormatter
{
    String format(Task task);
}
```

Write JavaDoc. Think about implementations: a short format (`[X] Buy milk`), a detailed format (multi-line with all fields), a CSV format. Again, no implementation — just the contract.

## Deliverable

Four Java files, all in the same package:
- `Task.java` — the record
- `TaskStore.java` — the CRUD interface
- `TaskFilter.java` — the filter/predicate interface
- `TaskFormatter.java` — the formatter interface

Each interface should have complete JavaDoc. No implementation code.

## Think About This

- You just designed a complete system without writing any logic. Every decision was about **what**, not **how**.
- The `TaskStore` interface hides all storage details. It could be backed by a list, a database, a file, or a web API. The interface doesn't care.
- `TaskFilter` and `TaskFormatter` are single-method interfaces. In Java, these can be used as lambda expressions — but you don't need to know that yet.
- In the next exercise, you'll write code that *uses* these interfaces. The interfaces will guide the implementation, not the other way around.
