# Week 2 Reading: The Principles Behind What We Built

In class you refactored `Player` so it no longer creates a `Scanner` internally. Instead, it receives a `TextIn` through its constructor. That one change — moving the dependency from inside to outside — is the most important design idea in this course.

This reading gives precise names to what you did. These names appear in textbooks, job interviews, and exam questions. Understand them well.

**Reading time: 20-25 minutes.**

---

## 1. Programming to an Interface

Look at the new `Player` constructor:

```java
public class Player {
    private Room currentRoom;
    private TextIn textIn;

    public Player(Room start, TextIn textIn) {
        this.currentRoom = start;
        this.textIn = textIn;
    }
}
```

`Player` depends on `TextIn`, which is an **interface** — not a concrete class. It doesn't know whether it's talking to a keyboard, a script, a file, or a network socket. It doesn't care. It just calls `textIn.nextLine(...)` and gets a string.

This is called **programming to an interface**: your code depends on what something *can do* (the interface), not on what it *is* (a specific class).

### Why it matters

When `Player` depended on `Scanner`, it was locked to one input source. Now:

| Want to... | Just provide... |
|---|---|
| Play with keyboard | `new ScannerTextIn()` |
| Test with scripted input | `new ScriptedTextIn("north", "east", "quit")` |
| Read from a file | A `TextIn` that reads lines from a file |
| Get input over a network | A `TextIn` that reads from a socket |

None of these require changing `Player`. You change the behavior by changing what you pass in — not by modifying the class.

### The rule

> Depend on abstractions (interfaces), not on concrete implementations (classes like Scanner).

---

## 2. Dependency Injection

The old `Player` created its own `Scanner`:

```java
// Old — Player creates its own dependency
public void moveInMace() {
    Scanner scanner = new Scanner(System.in);   // hardcoded!
    // ...
}
```

The new `Player` receives its dependency from the outside:

```java
// New — Player receives its dependency
public Player(Room start, TextIn textIn) {
    this.textIn = textIn;   // injected!
}
```

This is called **dependency injection**: instead of creating your dependencies yourself, you receive them from the caller. The word "injection" just means "passed in from outside."

There are different ways to inject dependencies:

- **Constructor injection** (what we did): pass the dependency through the constructor
- **Method injection**: pass it as a method parameter
- **Setter injection**: provide a setter method (less common, less safe)

Constructor injection is the most common and the safest — the object can't exist without its dependencies.

### Before and after

| | Old (hardcoded) | New (injected) |
|---|---|---|
| `Player` creates `Scanner` | Yes | No |
| `Player` knows about `Scanner` | Yes | No — only knows `TextIn` |
| Can test without keyboard | No | Yes |
| Can reuse with different input | No | Yes |
| Can swap behavior without changing code | No | Yes |

---

## 3. Seams

A **seam** is a point in your code where you can change behavior without editing the code itself. The `TextIn` parameter is a seam — you control what the `Player` does by choosing which `TextIn` to pass in.

Before the refactoring, `Player` had no seam for input. The `Scanner` was created inside the method, locked in, impossible to change without editing `Player`'s source code.

After the refactoring, the constructor parameter is a seam. You can pass in any `TextIn` implementation, and `Player` works differently — without any changes to its own code.

### Spotting seams

Seams are wherever you see an interface used as a parameter or field:

```java
public Player(Room start, TextIn textIn)     // textIn is a seam
public Receptionist(Greeting greeting)        // greeting is a seam
```

Code that has no seams — everything is hardcoded inside — is rigid. Code with well-placed seams is flexible and testable.

---

## 4. Testability

The whole reason we refactored `Player` was testability. Look at what becomes possible:

```java
// Arrange: set up a small maze and a scripted input
Room start = new Room("Start", "A corridor.");
Room north = new Room("North Room", "The north room.");
start.connectNorth(north);

ScriptedTextIn script = new ScriptedTextIn("north", "quit");
Player player = new Player(start, script);

// Act: run the game
player.moveInMace();

// Assert: the player should have moved north
// (In a real test, you'd also capture output to verify it)
```

No human typing. No hanging tests. No flaky behavior. The test runs in milliseconds, every time, with the same result.

This is the payoff of dependency injection: **code that's designed for injection is automatically testable**, because tests can inject fakes.

---

## 5. The Dependency Inversion Principle

Everything above is a specific instance of a broader principle:

> **High-level modules should not depend on low-level modules. Both should depend on abstractions.**

In our case:

- **High-level module**: `Player` — the game logic
- **Low-level module**: `ScannerTextIn` — the keyboard input mechanism
- **Abstraction**: `TextIn` — the interface

Before: `Player` → `Scanner` (high depends on low). Rigid.

After: `Player` → `TextIn` ← `ScannerTextIn` (both depend on the abstraction). Flexible.

The arrow flipped. `Player` no longer reaches down to a specific low-level class. Instead, both `Player` and `ScannerTextIn` point toward the interface in the middle. This is why it's called "inversion" — the direction of the dependency is reversed.

```
Before:     Player ──────→ Scanner

After:      Player ──→ TextIn ←── ScannerTextIn
                         ↑
                   ScriptedTextIn
```

---

## 6. Connecting It All

These concepts — programming to an interface, dependency injection, seams, testability, the Dependency Inversion Principle — are all facets of the same idea. They reinforce each other:

1. **Define an interface** for what you need (programming to an interface)
2. **Accept it as a parameter** instead of creating it yourself (dependency injection)
3. This creates a **seam** where behavior can be swapped
4. Tests exploit the seam by injecting fakes (**testability**)
5. The result follows the **Dependency Inversion Principle** — high and low both depend on the abstraction

This is the core design methodology of the course. Every week builds on it.

---

## Further Reading

- **"Clean Code"** by Robert C. Martin — Chapter 11 covers dependency injection and inversion in depth.
- **"Head First Design Patterns"** by Freeman and Robson — accessible introduction to interface-based design.
- **Martin Fowler on Dependency Injection**: https://martinfowler.com/articles/injection.html — the definitive article that named the pattern.
- **The SOLID Principles** — the "D" in SOLID stands for Dependency Inversion. Search for "SOLID principles Java" for many tutorials.
