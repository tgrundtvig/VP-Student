# Week 04: Wishful Programming & The Command Pattern

## Learning Objectives

After completing the pre-class material and attending class, you will be able to:

1. **Apply** wishful programming: write code that uses interfaces before any implementation exists
2. **Recognize** the Command pattern as a way to replace if-else chains with a map of named actions
3. **Design** top-down: start with what you need, then fill in the details
4. **Extend** a command-based system by adding new commands without modifying existing code

## Pre-Class Work

**Estimated time: 45-60 minutes**

Complete these before class, in order:

1. **[Reading: Wishful Programming](pre-class/reading.md)** (~20 minutes)
   - What it means to write code against interfaces that don't exist yet
   - How top-down design makes implementation "fall out" naturally
   - Why this is the core methodology of this course

2. **[Exercises: Notification System](pre-class/exercises/README.md)** (~25 minutes)
   - Experience the "implement last" step
   - The service code is already written — you make it work by implementing the leaf nodes
   - Run the provided tests to verify your work

3. **[Verification Checklist](pre-class/verification.md)** (~5 minutes)
   - Confirm you understood the reading
   - Confirm your exercises pass

## What Happens in Class

In class, we will refactor `Player.moveInMace()` from a long if-else chain into a Command pattern:

- Define a `Command` interface
- Create individual command classes (NorthCommand, QuitCommand, etc.)
- Replace the if-else chain with a `Map<String, Command>`
- See how adding new commands becomes trivial

The pre-class exercise gives you a head start by practicing the "implement last" workflow.

## Post-Class Work

**Estimated time: 60-90 minutes**

1. **[Reading: The Command Pattern](post-class/reading.md)** (~20 minutes)
   - Names the pattern we built in class
   - Connects it to wishful programming and the Open-Closed Principle

2. **[Exercise 1: Design Task Tracker Interfaces](post-class/exercises/exercise-1-design-task-tracker-interfaces.md)** (~35 minutes)
   - Pure interface design — no implementation
   - Practice the "design first" step on a fresh domain

3. **[Exercise 2: Wishful Workflow](post-class/exercises/exercise-2-wishful-workflow.md)** (~35 minutes)
   - Write working code against the interfaces you just designed
   - Experience the full methodology end-to-end
