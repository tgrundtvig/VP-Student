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

In class, we will wire the game together:

- Implement `takeTurn()` for a human player using `TextAppUser` and `CommandRegistry`
- Implement basic commands (look, move, take, drop, inventory) against the engine interfaces
- Build a `Game` implementation that sets up a small world and runs it
- Run the game loop and play the text adventure for the first time

This is where all the design work pays off. The interfaces guide the implementation — if the design is good, the code writes itself.

## Post-Class Work

To be announced after class.
