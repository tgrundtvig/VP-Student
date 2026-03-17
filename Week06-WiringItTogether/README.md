# Week 06: Wiring It Together

## Learning Objectives

After completing the pre-class material and attending class, you will be able to:

1. **Connect** separate subsystems (user I/O, commands, engine) into a working whole
2. **Implement** the `Actor.takeTurn()` method for a human player using `TextAppUser` and `CommandRegistry`
3. **Reason** about where dependencies belong — what each object needs to know and what it shouldn't
4. **Run** the game loop with a real `Game` implementation and see the engine come to life

## Pre-Class Work

**Estimated time: 45-60 minutes**

Complete these before class, in order:

1. **[Reading: The Wiring Problem](pre-class/reading.md)** (~20 minutes)
   - We have all the pieces. How do they connect?
   - Who creates what, and who passes what to whom?
   - Thinking about dependency flow in a top-down design

2. **[The Pieces We Have](pre-class/the-pieces-we-have.md)** (~25 minutes)
   - Inventory of every interface and implementation built so far
   - A concrete exercise: sketch how a player's turn works, step by step
   - Design questions to prepare for class

3. **[Verification Checklist](pre-class/verification.md)** (~5 minutes)
   - Confirm you understand how the pieces relate
   - Confirm you have a sketch of a player's turn

## What Happens in Class

In class, we will work through the Factory and Builder patterns together:

1. **[Reading: The Factory Pattern](in-class/reading.md)** (~15 minutes)
   - What factories are and why they matter
   - Static factory methods vs factory interfaces
   - How Factory and Builder patterns work together

2. **[Exercises: Factory and Builder Patterns](in-class/exercises.md)** (~60 minutes)
   - Five graduated exercises that build on each other
   - Start by creating items manually, end with Factory + Builder composition
   - Code from scratch — no starter code

## Post-Class Work

To be announced after class.
