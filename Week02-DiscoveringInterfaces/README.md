# Week 02: Discovering Interfaces

## Learning Objectives

After completing the pre-class work you will be able to:

1. **Recall** the code we built in Week 01 (Room, Mace, Player) even if you missed class
2. **Identify** the testing problem caused by hardcoded dependencies like Scanner
3. **Explain** what an interface is and how it separates "what" from "how"
4. **Implement** an interface with two implementations and a class that uses it

## Pre-Class Work

Complete these before class:

1. **[Reading](pre-class/reading.md)** (~20 minutes)
   - Full recap of the Week 01 code (catch up if you missed class)
   - Why the Player class is impossible to test
   - The interface pattern that solves the problem

2. **[Exercises](pre-class/exercises/README.md)** (~30 minutes)
   - Implement `FormalGreeting` and `CasualGreeting` from a `Greeting` interface
   - Implement a `Receptionist` that works with any `Greeting`

3. **[Verification](pre-class/verification.md)** (~5 minutes)
   - Confirm you understood the reading
   - Check that your exercises pass

**Estimated time: 50-60 minutes**

## What Happens in Class

In class we will apply the interface pattern to our text adventure:

- Extract a `TextIn` interface from the hardcoded Scanner
- Build `ScannerTextIn` for real gameplay and `ScriptedTextIn` for testing
- Refactor `Player` to accept any `TextIn` — just like your Receptionist exercise!
- Run the game with scripted input to prove it works without a human

## Post-Class Work

**Estimated time: 60-75 minutes**

1. **[Reading: The Principles Behind What We Built](post-class/reading.md)** (~20 minutes)
   - Names dependency injection, programming to an interface, seams
   - Explains the Dependency Inversion Principle
   - Key exam reference for the course's core concept

2. **[Exercise 1: A Third TextIn](post-class/exercises/exercise-1-third-textin.md)** (~20 minutes)
   - Create `RandomTextIn implements TextIn` — random direction input
   - Demonstrates: same interface, new implementation, zero changes to Player

3. **[Exercise 2: Spot the Dependency](post-class/exercises/exercise-2-spot-the-dependency.md)** (~25 minutes)
   - Identify hardcoded dependencies in code snippets
   - Sketch interfaces that would fix them
   - Pure design thinking — no implementation required
