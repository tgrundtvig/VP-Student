# Exercise 3: Number Guessing Game — Programming Against an Interface

## Goal

Build a number guessing game where the player tries to guess a random number between 1 and 100. The game must be written **entirely against the `TextAppUser` interface** — no `Scanner`, no `System.out.println`, no concrete classes. Just the interface.

## What You Will Learn

- **Programming against interfaces**: Your game logic depends only on `TextAppUser`, not on how input/output actually works
- **Reusable code**: The same game method works with a terminal user, a scripted test user, or any future implementation
- **Using the `TextAppUser` API**: Practice with `println()`, `readInt()` with range validation, and `readLine()`

## Context

You have seen how `TextAppUser` provides high-level input/output methods:
- `println(String text)` — display a message
- `readInt(String prompt, int min, int max)` — read an integer with range validation and automatic retry
- `readLine(String prompt)` — read a line of text
- `choose(String[] choices, String prompt)` — present a menu

Your game will use these methods to interact with the player. Because it only depends on the interface, it is completely decoupled from the actual I/O mechanism.

## Instructions

### Step 1: Create the game class

Create a new class `NumberGuessingGame` in the package `dk.ek.evu.vpf26.txtadv.user.demo` (or another package if you prefer).

### Step 2: Write the game method

Create a static method with this exact signature:

```java
public static void play(TextAppUser user)
```

This is the key design decision: the method receives a `TextAppUser` — it does **not** create one. This means the caller decides which implementation to use.

### Step 3: Implement the game logic

Inside `play()`:

1. Generate a random number between 1 and 100 (use `java.util.Random` or `Math.random()`)
2. Print a welcome message
3. In a loop:
   - Ask the player to guess using `readInt()` with min=1 and max=100
   - If the guess is too low, print "Too low! Try again."
   - If the guess is too high, print "Too high! Try again."
   - If the guess is correct, print a congratulation message with the number of attempts and break out of the loop
4. After the loop, ask if they want to play again (you can use `readLine()` or `choose()`)

### Step 4: Write a `main` method to run it

```java
public static void main(String[] args) {
    TextUser textUser = new TerminalUser();
    TextAppUser appUser = new TextAppUserImpl(textUser);
    play(appUser);
}
```

Notice the three layers:
1. `TerminalUser` — handles raw console I/O
2. `TextAppUserImpl` — adds validation and formatting
3. `play()` — the game logic, depends only on the `TextAppUser` interface

### Step 5: Run and play the game

Run your `main` method and play a few rounds. Verify that:
- Invalid input (letters, numbers outside 1-100) is handled automatically by `readInt()`
- The "too high" / "too low" hints work correctly
- The attempt counter is accurate

## Example Session

```
=== Number Guessing Game ===
I'm thinking of a number between 1 and 100.

Enter your guess (1-100): 50
Too high! Try again.
Enter your guess (1-100): 25
Too low! Try again.
Enter your guess (1-100): 37
Too low! Try again.
Enter your guess (1-100): 43
Correct! You got it in 4 attempts!

Play again? (yes/no): no
Thanks for playing!
```

## Think About This

- Your `play()` method has **zero dependencies** on console I/O. It works purely through the `TextAppUser` interface.
- If someone built a graphical `TextAppUser` implementation (e.g. with a text field and a display area), your game would work in a GUI — without changing a single line of game code.
- If you completed Exercise 2 (`ScriptedTextUser`), you could test your game automatically by scripting the guesses. Think about what script you would need to feed it to guarantee a correct game.

## Bonus Challenge

If you finish early, try one or both of these:

**A) Difficulty levels**: Before the game starts, use `choose()` to let the player pick a difficulty:
- Easy: 1-50, unlimited guesses
- Medium: 1-100, 10 guesses max
- Hard: 1-200, 7 guesses max

**B) Connect it to Exercise 2**: Write a second `main` method that runs the game with a `ScriptedTextUser`. You will need to know the random number in advance — so add an overloaded method `play(TextAppUser user, int secretNumber)` that lets you control the number for testing.
