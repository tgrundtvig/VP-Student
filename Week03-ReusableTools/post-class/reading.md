# Week 3 Reading: The Patterns Behind What We Built

In class this week, you built and used several things: a low-level `TextUser` interface, a high-level `TextAppUser` interface, an adapter connecting them, and decorators wrapping them. These aren't random ideas — they are well-known design patterns that professional developers use every day.

This reading gives names to what you did, explains why these patterns exist, and shows you where they appear in real software.

**Reading time: 20-25 minutes.**

---

## 1. Programming to an Interface

The single most important idea in this course is:

> **Depend on interfaces, not on concrete classes.**

When `Player` depends on `TextAppUser` (the interface), it doesn't know or care whether the user is typing on a keyboard, reading from a file, or running from a test script. That ignorance is powerful — it means `Player` works in all those situations without any changes.

When code depends on a concrete class like `Scanner` or calls `System.out.println()` directly, it is locked to one way of doing things. You can't swap it, you can't test it easily, and every change to the concrete class ripples through your code.

### The Rule

When you design a method that needs to interact with something:

1. Define an **interface** that describes what you need (not how it works)
2. Make your method accept that interface as a parameter
3. Let the caller decide which implementation to provide

You saw this in the `NumberGuessingGame.play(TextAppUser user)` method. The game doesn't create its own I/O — it receives it. This makes the game reusable, testable, and flexible.

This principle has a formal name: the **Dependency Inversion Principle**. "High-level code should not depend on low-level details. Both should depend on abstractions." In practice, it means: depend on interfaces.

---

## 2. The Adapter Pattern

### What You Built

`TextAppUserImpl` takes a `TextUser` (which has simple `get()` and `put()` methods) and makes it look like a `TextAppUser` (which has `readInt()`, `choose()`, `println()`, and other rich methods).

It **adapts** one interface into another.

### The Pattern

The Adapter pattern converts the interface of a class into a different interface that clients expect. It lets classes work together that otherwise couldn't because their interfaces are incompatible.

```
What the client wants:         What you have:
  TextAppUser                    TextUser
  - readInt()                    - get()
  - readFloat()                  - put()
  - choose()
  - println()

The adapter bridges the gap:
  TextAppUserImpl implements TextAppUser, wraps TextUser
```

### Where You See This in the Real World

- Java's `InputStreamReader` adapts a byte stream (`InputStream`) into a character stream (`Reader`)
- `Arrays.asList()` adapts an array into a `List`
- Database drivers adapt a generic JDBC interface to a specific database protocol
- Power plug adapters (the physical kind!) adapt one plug shape to a different socket shape — same idea

### When to Use It

Use an adapter when:
- You have an existing interface that almost does what you need, but not quite
- You want to create a richer interface on top of a simpler one
- You need to make two incompatible interfaces work together

---

## 3. The Decorator Pattern

### What You Built (In-Class Exercise)

`LoggingTextUser` wraps a `TextUser` and adds logging — without changing the wrapped object. It implements the same interface, so the rest of the code doesn't notice the difference.

### The Pattern

The Decorator pattern attaches additional responsibilities to an object dynamically. It provides a flexible alternative to subclassing for extending functionality.

The key properties:
1. The decorator implements the **same interface** as the object it wraps
2. It **forwards** all calls to the wrapped object
3. It **adds behavior** before or after forwarding (logging, validation, caching, etc.)
4. Multiple decorators can be **stacked** because they all share the same interface

```
TextAppUserImpl → LoggingTextUser → TerminalUser
                  (adds logging)    (does actual I/O)
```

### Where You See This in the Real World

- Java's `BufferedReader` wraps a `Reader` and adds buffering
- `Collections.unmodifiableList()` wraps a `List` and makes it read-only
- Web middleware: each layer wraps the next handler and adds behavior (authentication, logging, compression)
- A gift wrapped in paper, wrapped in a box, wrapped in more paper — each layer adds something without changing what's inside

### When to Use It

Use a decorator when:
- You want to add behavior to an object without modifying its class
- You want to combine behaviors flexibly (logging + caching + validation, in any order)
- Subclassing would lead to too many class combinations

---

## 4. Test Doubles: Fakes, Stubs, and Mocks

### What You Built (In-Class Exercise)

`ScriptedTextUser` is a fake implementation of `TextUser`. It doesn't do real I/O — it plays back scripted responses and captures output. This lets you test code that uses `TextUser` without touching the keyboard.

### The Vocabulary

The testing community uses specific terms for different kinds of fake objects:

- **Fake**: A working implementation that takes a shortcut. Your `ScriptedTextUser` is a fake — it actually implements `TextUser`, just with scripted data instead of real input.
- **Stub**: An object that returns predetermined data. Similar to a fake, but typically simpler and configured per-test.
- **Mock**: An object that records how it was called, so you can verify interactions. Your `LoggingTextUser` is close to a mock — it records calls for later inspection.

In practice, people often use these terms loosely. The important thing is the idea: **interfaces let you swap in fake implementations for testing**.

### Why This Matters

Without interfaces, testing interactive code is painful:
- You have to run the program and type input manually
- You can't repeat the same test reliably
- You can't test edge cases easily (what if the user types 10,000 characters?)

With interfaces, you script the input, capture the output, and verify everything programmatically. This is the foundation of automated testing, which you will work with more later in the course.

---

## 5. Records vs Interfaces: The Design Decision

Throughout this course, you will repeatedly face this question: "Should this be a record or an interface?"

The rule is simple:

| Question | Answer |
|----------|--------|
| Does it just hold data? | **Record** |
| Does it do things (have behavior)? | **Interface** |

### Records: Immutable Data Carriers

```java
public record PlayerCharacter(String name, String characterClass, int strength) {}
```

A record is transparent — you can see exactly what's inside. It's immutable — once created, it never changes. It's equal by value — two records with the same data are equal.

Use records for: coordinates, configuration, results, messages, events, anything that is "just data."

### Interfaces: Behavior Contracts

```java
public interface TextUser {
    void put(String text);
    String get();
}
```

An interface hides what's inside — you don't know (or care) how it works. It can have many implementations. It defines a contract: "anything that implements this interface can do these things."

Use interfaces for: I/O, services, strategies, anything that "does something" and might need to be swapped.

### The Combination

The most powerful designs combine both. A method takes an interface (for behavior) and returns a record (for data):

```java
public static PlayerCharacter create(TextAppUser user) { ... }
```

This says: "Give me something that can interact with a user (interface), and I'll give you back a character sheet (record)." Clean, testable, and clear about what goes in and what comes out.

---

## Summary

| Pattern | What It Does | You Built |
|---------|-------------|-----------|
| Program to an Interface | Depend on abstractions, not concrete classes | `play(TextAppUser user)` |
| Adapter | Convert one interface into another | `TextAppUserImpl` wraps `TextUser` into `TextAppUser` |
| Decorator | Add behavior without changing the wrapped object | `LoggingTextUser` adds logging to any `TextUser` |
| Test Doubles | Fake implementations for testing | `ScriptedTextUser` replaces real keyboard input |
| Records | Immutable data objects | `PlayerCharacter` holds character data |

These patterns will reappear throughout the course. You don't need to memorize the names — what matters is that you can recognize the situations where they are useful and apply them.

---

## Further Reading

If you want to dig deeper into any of these topics:

- **"Design Patterns: Elements of Reusable Object-Oriented Software"** by Gamma, Helm, Johnson, and Vlissides (the "Gang of Four" book) — the original source for Adapter, Decorator, and many other patterns. Dense but definitive.
- **"Head First Design Patterns"** by Freeman and Robson — a more approachable introduction to the same patterns, with visual explanations and humor.
- **Java Records** (official documentation): https://docs.oracle.com/en/java/javase/21/language/records.html
- **Refactoring Guru — Adapter Pattern**: https://refactoring.guru/design-patterns/adapter — visual explanations with Java examples.
- **Refactoring Guru — Decorator Pattern**: https://refactoring.guru/design-patterns/decorator — same, for the Decorator.
- **Martin Fowler on Test Doubles**: https://martinfowler.com/bliki/TestDouble.html — short article that defines fakes, stubs, mocks, and spies.
