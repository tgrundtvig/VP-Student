# Exercise 2: Wishful Workflow

## Goal

Write a `TaskTrackerApp.run()` method that uses the interfaces you designed in Exercise 1 — plus `TextAppUser` from the class project. The code may not compile (there are no implementations yet). That's the point.

## Time Estimate

30-40 minutes.

## What You Will Learn

- **The full methodology end-to-end**: Design interfaces → write workflows → implement later
- **Wishful programming in practice**: Writing real code against contracts that have no implementation
- **How good interfaces guide code**: If the interfaces are well-designed, the workflow writes itself

## Prerequisites

You must have completed Exercise 1 (the four interface/record files).

## Instructions

### Step 1: Create `TaskTrackerApp`

Create a class with these dependencies:

```java
public class TaskTrackerApp
{
    private final TextAppUser user;
    private final TaskStore store;
    private final TaskFormatter formatter;

    public TaskTrackerApp(TextAppUser user, TaskStore store, TaskFormatter formatter)
    {
        this.user = user;
        this.store = store;
        this.formatter = formatter;
    }
}
```

Notice: three interfaces, no concrete classes. The app doesn't know how tasks are stored, how they're displayed, or how user I/O works.

### Step 2: Write the `run()` method

Write a command loop similar to the text adventure. The user can:

1. **"add"** — Ask for a title and description, add a task
2. **"list"** — Show all tasks using the formatter
3. **"complete"** — Ask for a task ID, mark it complete
4. **"remove"** — Ask for a task ID, remove it
5. **"quit"** — Exit the loop

Use `TextAppUser` methods for all I/O:
- `user.readLine("prompt")` for text input
- `user.readInt("prompt")` for numeric input
- `user.println(text)` for output

Use `TaskStore` methods for all data operations.

Use `TaskFormatter` to display each task.

Here's a sketch to get you started:

```java
public void run()
{
    boolean running = true;
    while (running)
    {
        String command = user.readLine("Enter command (add/list/complete/remove/quit): ");
        switch (command.toLowerCase())
        {
            case "add" ->
            {
                String title = user.readLine("Title: ");
                String description = user.readLine("Description: ");
                Task task = store.add(title, description);
                user.println("Created: " + formatter.format(task));
            }
            case "list" ->
            {
                List<Task> tasks = store.getAll();
                if (tasks.isEmpty())
                {
                    user.println("No tasks.");
                }
                else
                {
                    for (Task task : tasks)
                    {
                        user.println(formatter.format(task));
                    }
                }
            }
            // ... complete, remove, quit ...
        }
    }
}
```

### Step 3: Add filtering (optional stretch)

Add a "filter" command that only shows incomplete tasks:

```java
case "filter" ->
{
    TaskFilter incomplete = task -> !task.completed();
    List<Task> all = store.getAll();
    for (Task task : all)
    {
        if (incomplete.matches(task))
        {
            user.println(formatter.format(task));
        }
    }
}
```

This demonstrates how the `TaskFilter` interface is used.

### Step 4: Reflect on what you wrote

Look at your `run()` method. It's real code. It compiles against the interfaces. It expresses the complete workflow of a task tracker.

But there's no `TaskStore` implementation. No `TaskFormatter` implementation. No database, no file, no list of tasks.

**The code is complete, but the system doesn't run yet.** And that's fine — because the design is done. The implementations are trivial leaf nodes that can be written later.

## Think About This

- You just wrote a complete application workflow without any implementation classes. Every method call goes through an interface.
- If you wanted to test this app, you could create fake implementations: a `ListTaskStore` that stores tasks in a `List`, a `SimpleFormatter` that returns `task.title()`. The test would verify the workflow without touching any real system.
- This is exactly what we did with the text adventure: `TextIn` → `ScriptedTextIn` for testing. The same principle applies everywhere.
- The implementations you'll eventually write will be short and focused. `ListTaskStore.add()` creates a `Task` and adds it to a list. `SimpleFormatter.format()` returns a formatted string. The hard work — the design — is already done.

## Bonus Challenge

If you finish early: write a `Main` class that creates concrete implementations and runs the app. You'll need to implement `TaskStore` (use a `List<Task>` internally) and `TaskFormatter` (format as `[X] title` or `[ ] title`). Then run it and see your design come to life.
