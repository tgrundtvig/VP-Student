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

**Important: This is a long-term reference, not a one-week assignment.** The reading and exercises below cover all the major design patterns in software engineering. You are **not** expected to finish everything before next week. Read through the guide at your own pace, and work on the exercises that interest you over the coming weeks. There is no deadline — this material is here for you to learn from whenever you're ready.

**For next week**, the only preparation is the [Week 07 pre-class work](../Week07-BringingTheGameToLife/README.md). The material below is independent of that.

1. **[Reading: Design Patterns — A Comprehensive Guide](post-class/reading.md)** (~90-120 minutes)
   - What design patterns are and where they come from (Gang of Four)
   - All 23 classic patterns: Creational, Structural, and Behavioral
   - Architectural patterns (MVC, Repository) and Dependency Injection
   - Each pattern: the problem it solves, how it works, Java code examples, and where you've seen it
   - This is a reference document — read it through once, then come back to individual patterns when you need them

2. **[Exercises: Design Pattern Exercises](post-class/exercises.md)** (~20-30 minutes per exercise)
   - One hands-on exercise for each of the 23 patterns from the reading
   - Each exercise is self-contained — build a small system from scratch
   - Every exercise follows the interface-first approach: define interfaces, write client code, then implement
   - Pick the patterns that interest you most, or work through them in order — there is no expectation to do them all
