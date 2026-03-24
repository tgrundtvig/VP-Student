# Exercise: Guess a Number

## Goal

Design and implement a complete "Guess a Number" game using interface-first design. This is the exercise we did in class this week — if you were not present, work through it on your own.

## Time Estimate

60-90 minutes.

## What You Will Learn

- **Interface-first design from scratch**: Start with interfaces, then implement
- **Asymmetric roles**: Two players with completely different behaviors behind a shared concept
- **Separating game logic from player interaction**: The game orchestrates, the players play

## The Game

Guess a Number is an asymmetric two-player game:

1. The game is given an interval (e.g., 1 to 100)
2. The **Thinker** is told the interval and asked to think of a number within it
3. The **Guesser** is told the interval, then repeatedly:
   - The Guesser guesses a number
   - The Thinker gives feedback: **too high**, **too low**, or **correct**
   - The Guesser sees the feedback and guesses again (unless correct)
4. When the Guesser guesses correctly, the game ends and reports the number of guesses

## Instructions

### Step 1: Design the Feedback type

The Thinker's response to a guess is one of three things: too high, too low, or correct.

**Think about**: Is this data or behavior? What Java construct best represents a fixed set of values?

### Step 2: Design the Player interfaces

There are two roles in this game, and they have **different behaviors**:

**Thinker** — someone who:
- Is told the interval and asked to think of a number
- Is given a guess and responds with feedback (too high, too low, correct)

**Guesser** — someone who:
- Is told the interval
- Makes a guess (returns a number)
- Is given feedback about their guess

**Think about**: These are clearly behavior, not data — so they should be interfaces. What methods does each one need?

### Step 3: Design the Game

The game orchestrates the interaction between a Thinker and a Guesser. It:
- Receives the interval, a Thinker, and a Guesser
- Runs the game loop (guess, feedback, repeat)
- Counts and reports the number of guesses

**Think about**: What does the game need in order to run? What does it return when finished?

### Step 4: Implement

Now implement everything:

- **Feedback type**: Implement your design from Step 1
- **Human players**: A Thinker and a Guesser that interact with a real person via the console. The human Thinker is asked to think of a number (the game trusts them — it does not need to know the number) and then gives feedback on each guess. The human Guesser types their guesses.
- **Game**: Implement the game loop that connects a Thinker and a Guesser

### Step 5: Run it

Write a `main` method that creates a game with an interval of 1 to 100 and two human players, then runs it. Play it with a friend, or play both roles yourself.

### Step 6: Add a computer player

Implement a computer Guesser that plays optimally. **Think about**: What is the best strategy for guessing a number in an interval when you get "too high" or "too low" feedback?

Then implement a computer Thinker that picks a random number in the interval and gives honest feedback.

Now you can run any combination: human vs. human, human vs. computer, or computer vs. computer.

## Think About This

- The `Game` class does not know whether it is running with humans or computers. It only talks to the Thinker and Guesser interfaces. This is the power of interface-first design.
- You could add a network player, a player that cheats, or a player that logs every guess to a file — all without changing the Game class.
- The feedback type is a design decision. Whatever you chose, ask yourself: could I have used something else? What are the trade-offs?
- How many guesses does the computer Guesser need for an interval of 1 to 100? Why?
