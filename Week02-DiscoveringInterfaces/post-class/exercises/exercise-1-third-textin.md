# Exercise 1: A Third TextIn

## Goal

Create a `RandomTextIn` that implements `TextIn` and returns random directions. Run the game with it and watch the player wander aimlessly through the maze.

**Estimated time: 20 minutes.**

---

## Background

In class you saw two `TextIn` implementations:

- `ScannerTextIn` — reads from the keyboard (for real gameplay)
- `ScriptedTextIn` — plays back a fixed list of commands (for testing)

You're about to create a third. The point: **you don't need to change `Player` at all.** Same interface, new implementation, zero modifications to existing code.

---

## Tasks

### 1. Create `RandomTextIn implements TextIn`

Write a class that:

- Implements the `TextIn` interface
- Ignores the prompt parameter
- Returns a random direction each time `nextLine` is called: one of `"north"`, `"south"`, `"east"`, or `"west"`

Use `java.util.Random` to pick a direction:

```java
Random random = new Random();
String[] directions = {"north", "south", "east", "west"};
return directions[random.nextInt(directions.length)];
```

### 2. Run the game with RandomTextIn

In a `main` method:

1. Create the maze using `MyMaceFactory`
2. Create a `RandomTextIn`
3. Create a `Player` with the random input
4. Run the game

### 3. Observe

The player will wander randomly, sometimes hitting walls ("There is no exit to the north!"), sometimes moving to new rooms. Let it run for a bit, then stop the program manually (Ctrl+C or stop button in IntelliJ).

---

## Reflection

- You added a completely new behavior to the game **without touching `Player`, `Room`, `Mace`, or `Main`**. The only new code is `RandomTextIn` and a `main` method that wires it in.
- This is the power of programming to an interface. As long as the new class implements `TextIn`, it plugs in and works.
- How would you make the random player eventually quit? (Hint: add a counter and return `"quit"` after N moves.)

---

## Self-Check

- `RandomTextIn` compiles and implements `TextIn`.
- The game runs without errors (wall-bump messages are expected).
- You did not modify `Player` or any existing class.
