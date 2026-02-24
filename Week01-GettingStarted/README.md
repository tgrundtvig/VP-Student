# Week 01: Getting Started

## Learning Objectives

After completing the pre-class work you will be able to:

1. **Describe** what a text adventure game is and how rooms connect to each other
2. **Explain** how objects can reference other objects to form a linked structure
3. **Implement** a simple linked-node pattern (Waypoint/Trail)
4. **Understand** the project-driven approach we use throughout this course

## Pre-Class Work

Complete these before class:

1. **[Reading](pre-class/reading.md)** (~15 minutes)
   - What this course is about and how it works
   - The text adventure game we will build together
   - How objects link to other objects

2. **[Exercises](pre-class/exercises/README.md)** (~30 minutes)
   - Implement `Waypoint` and `Trail` classes
   - Practice the "objects referencing objects" pattern

3. **[Verification](pre-class/verification.md)** (~5 minutes)
   - Confirm you understood the reading
   - Check that your exercises pass

**Estimated time: 45-60 minutes**

## What Happens in Class

In class we will build the first version of our text adventure game together:

- A `Room` class where rooms link to other rooms (just like your Waypoint exercise!)
- A `Mace` (maze) and a factory that builds one
- A `Player` that moves between rooms
- A playable game by the end of the session

## Post-Class Work

**Estimated time: 45-60 minutes**

1. **[Reading: The Patterns Behind What We Built](post-class/reading.md)** (~15 minutes)
   - Names the object graph and reference semantics patterns
   - Explains bidirectional linking and null as "no connection"

2. **[Exercise 1: Bidirectional Waypoints](post-class/exercises/exercise-1-bidirectional-waypoints.md)** (~20 minutes)
   - Extend Waypoint with `prev` links and a bidirectional `connect` method
   - Walk a chain forward and backward

3. **[Exercise 2: Maze Explorer](post-class/exercises/exercise-2-maze-explorer.md)** (~20 minutes)
   - Design and build your own maze of 5+ rooms
   - Navigate it with ScriptedTextIn
