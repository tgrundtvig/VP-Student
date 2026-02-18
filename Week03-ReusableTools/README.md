# Week 03: Reusable Tools

## Learning Objectives

After completing the pre-class material and attending class, you will be able to:

1. **Recognize** how interfaces make code reusable across different contexts
2. **Identify** pain points in code that uses hardcoded dependencies (like `IO.println`)
3. **Implement** a simple interface with multiple implementations
4. **Understand** how small abstractions can be composed into powerful tools

## Pre-Class Work

**Estimated time: 45-60 minutes**

Complete these before class, in order:

1. **[Reading: From Specific to Reusable](pre-class/reading.md)** (~20 minutes)
   - Revisit what `TextIn` taught you
   - Discover the missing half: output
   - See how small interfaces enable big tools

2. **[Exercises: TextOut Warm-Up](pre-class/exercises/README.md)** (~25 minutes)
   - Implement a `TextOut` interface (mirrors `TextIn`)
   - Two implementations: console output and list-based output
   - Run the provided tests to verify your work

3. **[Verification Checklist](pre-class/verification.md)** (~5 minutes)
   - Confirm you understood the reading
   - Confirm your exercises pass

## What Happens in Class

In class, we will build reusable tools for ANY text-based application:
- A `UserInterface` abstraction that combines input and output
- Higher-level tools: menus, validated integer input, a command system
- Apply these tools to our text adventure

The pre-class exercise gives you a head start by building the output half of the puzzle.
