# The Command Pattern

In class this week, you refactored `Player.moveInMace()` from a long if-else chain into a map of named commands. This isn't a random refactoring trick — it's a well-known design pattern called the **Command pattern**.

This reading gives it a name, explains why it exists, and shows where it appears in real software.

**Reading time: 20 minutes.**

---

## 1. Before and After

### Before (if-else chain)

```java
if ("North".equalsIgnoreCase(move))
{
    if (currentRoom.getNorth() == null)
    {
        IO.println("There is no exit to the north!");
        continue;
    }
    currentRoom = currentRoom.getNorth();
    break;
}
else if ("East".equalsIgnoreCase(move))
{
    // ... same pattern ...
}
else if ("Quit".equalsIgnoreCase(move))
{
    exit = true;
    break;
}
else
{
    IO.println("Unknown command:" + move);
}
```

**Problems:**
- Adding a new command means modifying this method
- Every command is mixed into one big method
- You can't test individual commands in isolation
- The method does too many things: parsing, dispatching, and executing

### After (Command pattern)

```java
Map<String, Command> commands = new HashMap<>();
commands.put("north", new NorthCommand(player));
commands.put("east", new EastCommand(player));
commands.put("quit", new QuitCommand(player));
commands.put("look", new LookCommand(player));

// The game loop:
Command command = commands.get(move.toLowerCase());
if (command != null)
{
    command.execute();
}
else
{
    user.println("Unknown command: " + move);
}
```

**Benefits:**
- Adding a new command = one class + one `put()` line
- Each command is a separate class with a single responsibility
- Commands can be tested individually
- The game loop is simple and never changes

---

## 2. The Pattern Elements

The Command pattern has three parts:

| Element | Role | In Our Game |
|---------|------|-------------|
| **Command** (interface) | Defines the action contract | `Command` with `execute()` |
| **Concrete Commands** | Implement specific actions | `NorthCommand`, `LookCommand`, etc. |
| **Invoker** | Looks up and runs commands | The game loop with `Map<String, Command>` |

The invoker doesn't know what the commands do. It just looks them up by name and calls `execute()`. This separation is the key to extensibility.

---

## 3. Why If-Else Chains Are Painful

The if-else chain in the original `moveInMace()` has a specific problem: it violates the **Open-Closed Principle**.

> **Open-Closed Principle**: Software should be open for extension, but closed for modification.

With the if-else chain, adding a new command requires **modifying** the method. Every change risks breaking something that already works.

With the Command pattern, adding a new command requires **extending** the system — a new class and a new registration. The existing game loop, the existing commands, and the existing interface are untouched.

This isn't just theoretical cleanliness. In a codebase with many developers, modifying a shared method is risky. Adding a new class is safe.

---

## 4. Connection to Wishful Programming

The Command pattern is a natural outcome of wishful programming.

When you wrote the game loop, you wished for a way to look up and execute commands by name. That wish became:

```java
Command command = commands.get(move.toLowerCase());
command.execute();
```

You wrote this code **before** `NorthCommand` or `LookCommand` existed. The interface was designed by the code that uses it — top-down, not bottom-up.

The implementations came last, and they were simple. Each command class is a few lines long. The hard part — the design — was already done.

This is the methodology of this course:
1. Write the high-level code (wishfully)
2. Discover the interfaces you need
3. Define those interfaces
4. Implement the trivial leaf nodes

---

## 5. Where You See This in the Real World

The Command pattern is everywhere:

- **GUI applications**: Buttons, menu items, and keyboard shortcuts all map to command objects. The "Undo" feature is often a stack of command objects with an `undo()` method.
- **Web frameworks**: URL routes map to handler functions or command objects. `/login` → `LoginHandler`, `/search` → `SearchHandler`. Same pattern, different domain.
- **Text editors**: Vim, Emacs, and VS Code all use command maps internally. When you type `:w` in Vim, it looks up and executes the "write" command.
- **Game engines**: Player input (keyboard, controller) is mapped to command objects. This allows key remapping without changing game logic.
- **Task queues**: Background jobs are serialized commands. "Send email to user X" is a command object that gets queued and executed later.

---

## 6. Summary

| Concept | What It Means |
|---------|--------------|
| **Command pattern** | Replace conditional dispatch with a map of command objects |
| **Open-Closed Principle** | Extend by adding new classes, not by modifying existing code |
| **Wishful programming** | Write the high-level code first; let it define the interfaces |
| **Top-down design** | Start with what you need, then fill in the details |

These ideas reinforce each other. Wishful programming leads you to define clean interfaces. The Command pattern uses those interfaces to create extensible systems. The Open-Closed Principle explains why this matters.

---

## Further Reading

If you want to dig deeper:

- **Refactoring Guru — Command Pattern**: https://refactoring.guru/design-patterns/command — visual explanations with Java examples.
- **"Head First Design Patterns"** by Freeman and Robson — Chapter 6 covers the Command pattern with a remote control example.
- **Open-Closed Principle**: https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle — the Wikipedia article is a good starting point.
