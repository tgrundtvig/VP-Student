# Exercise 1: Refactor the Text Adventure to Use TextAppUser

## Goal

The `Player` class currently depends on two things for I/O:
- `TextIn` for input (the `ask()` method)
- `IO.println()` for output (a static utility method)

This is messy. Input goes through an interface, but output is hardcoded to a static call. You cannot swap out the output, you cannot test it, and `Player` is secretly coupled to `IO`.

Your job: **refactor `Player` to use `TextAppUser` for all I/O**. One interface. One dependency. Clean.

## Time Estimate

45-60 minutes.

## What You Will Learn

- **Refactoring**: Changing internal structure without changing behavior
- **Dependency reduction**: Going from two I/O dependencies to one
- **The payoff of reusable tools**: The `TextAppUser` you built in class is immediately useful

## Before You Start

Look at the current `Player` class and identify every line that does I/O:
- `textIn.ask(...)` — input through an interface (good)
- `IO.println(...)` — output through a static method (not good)

Count them. There should be quite a few `IO.println()` calls.

## Instructions

### Step 1: Change the constructor

Currently:
```java
public Player(TextIn textIn, Room start)
```

Change it to accept a `TextAppUser` instead of `TextIn`:
```java
public Player(TextAppUser user, Room start)
```

Store it in a field. You can name it `user` or `appUser` — your choice.

### Step 2: Replace all IO.println() calls

Every `IO.println(someText)` becomes `user.println(someText)`.

Go through the `moveInMace()` method line by line. There are calls for:
- Printing the room description
- Printing error messages ("There is no exit to the north!")
- Printing "Unknown command"
- Printing "Goodbye!"

Replace them all.

### Step 3: Replace the textIn.ask() call

The line:
```java
String move = textIn.ask("Where do you want to go?");
```

becomes:
```java
String move = user.readLine("Where do you want to go? ");
```

Note: `ask()` on the old `TextIn` interface printed the prompt and read input. `readLine()` on `TextAppUser` does the same thing.

### Step 4: Update Main

In `Main.java`, you now need to create a `TextAppUser` instead of a `TextIn`:

```java
TextUser textUser = new TerminalUser();
TextAppUser appUser = new TextAppUserImpl(textUser);
Player me = new Player(appUser, start);
```

You will need the right imports for `TextUser`, `TextAppUser`, `TerminalUser`, and `TextAppUserImpl`.

### Step 5: Run and test

Run `Main` and play the game. It should behave exactly as before. Walk through a few rooms, try an invalid direction, try quitting.

If it works identically — congratulations, you just refactored without breaking anything.

## Think About This

After your refactoring:

- `Player` has **one** I/O dependency: `TextAppUser`. No more `IO.println()`, no more `TextIn`.
- You could now test `Player` using the `ScriptedTextUser` from the in-class exercise — script a sequence of moves and verify the game works without touching the keyboard.
- You could log every interaction by wrapping with `LoggingTextUser`.
- If someone builds a graphical interface that implements `TextAppUser`, the game works in a GUI without changing `Player` at all.

That is the power of depending on an interface instead of concrete I/O.

## Bonus Challenge

The `Player.moveInMace()` method uses a long chain of `if/else if` for directions. Now that you have `TextAppUser`, you could replace the free-text input with `choose()`:

```java
String[] directions = {"North", "South", "East", "West", "Quit"};
int choice = user.choose(directions, "Where do you want to go? ");
```

Think about the trade-offs: what do you gain? What do you lose? (Hint: think about what happens when a direction is not available.)
