# Week 07: Bringing the Game to Life

## Learning Objectives

After completing the pre-class material and attending class, you will be able to:

1. **Implement** the missing pieces of the game engine (Game, Player, commands)
2. **Connect** the subsystems (user I/O, commands, engine) into a running game
3. **Apply** design patterns naturally (Command, Factory, DI, Template Method) as they emerge from implementation
4. **Run** and play the text adventure for the first time

## Pre-Class Work

**Estimated time: 45-60 minutes**

Complete these before class, in order:

1. **[Reading: From Design to Implementation](pre-class/reading.md)** (~15 minutes)
   - We have all the interfaces. Now it's time to make the game run.
   - Implementation strategy: vertical slices, not bottom-up
   - Who creates what? The "factory floor" concept
   - What's needed for the minimum playable game

2. **[Exercise: The Implementation Inventory](pre-class/inventory.md)** (~10 minutes)
   - Go through every interface in the project and check its status
   - For each missing implementation, write one sentence about what it needs to do
   - Star the ones needed for the minimum playable game

3. **[Exercise: Mapping Dependencies](pre-class/dependencies.md)** (~15 minutes)
   - For each missing piece, figure out what it depends on
   - What does it receive through its constructor? What does it need to do?
   - Draw the creation order: what must exist before what?

4. **[Exercise: Planning the First Playable](pre-class/first-playable.md)** (~10 minutes)
   - List the minimum commands needed for a functional (not fun) game
   - Sketch a small world on paper
   - Write the creation order for main() or initialize()
   - Trace one complete turn through the interfaces

## What Happens in Class

We implement the game together — starting with the minimum playable version and adding commands one at a time. By the end of the session, you will have a running text adventure that you can actually play.

## Post-Class Work

To be announced after class.
