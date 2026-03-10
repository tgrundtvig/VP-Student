# Week 05: Designing the Engine

## Learning Objectives

After completing the pre-class material and attending class, you will be able to:

1. **Read** a set of interfaces and understand how they relate to each other
2. **Identify** gaps in an interface design by trying to write code that uses it
3. **Propose** interface changes with reasoning — not just what to add, but why
4. **Evaluate** whether a concept deserves its own interface or belongs on an existing one

## Pre-Class Work

**Estimated time: 45-60 minutes**

Complete these before class, in order:

1. **[Reading: Letting the Code Tell You What's Missing](pre-class/reading.md)** (~15 minutes)
   - How to evaluate an interface design by looking at the code that uses it
   - How to find missing methods without guessing
   - When something needs a new interface vs. extending an existing one

2. **[The Engine So Far](pre-class/the-engine-so-far.md)** (~30 minutes)
   - Walk through the engine interfaces we built in class
   - Understand how they fit together
   - **7 open design questions** for you to think about and write your proposals

3. **[Verification Checklist](pre-class/verification.md)** (~5 minutes)
   - Confirm you understand the current design
   - Confirm you have written proposals for the design questions

## What Happens in Class

In class, we will discuss your design proposals:

- Compare different approaches to the same design question
- Debate trade-offs: simplicity vs. flexibility, engine vs. theme responsibility
- Converge on a shared design we all agree on
- Start implementing the updated interfaces

Come prepared with opinions. There are no wrong answers, but you need to be able to explain *why* you made your choices.

## Post-Class Work

**Estimated time: 60-90 minutes**

1. **[Reading: The Builder Pattern](post-class/reading.md)** (~15 minutes)
   - Names the pattern we used with `LocationMapBuilder`
   - Why builders exist and where you see them in real software
   - Connection to interface-first design

2. **[Exercise 1: Implement SimpleActor](post-class/exercises/exercise-1-simple-actor.md)** (~30 minutes)
   - Implement the `Actor` interface as a concrete class
   - Practice the patterns from `SimpleLocation` (collections, unmodifiable returns)
   - You'll need this for next week

3. **[Exercise 2: Flesh Out Item](post-class/exercises/exercise-2-flesh-out-item.md)** (~20 minutes)
   - The `Item` interface is empty — figure out what it needs
   - Discover methods from usage, not guessing
   - Implement a `SimpleItem`

4. **[Exercise 3: Design a LookCommand](post-class/exercises/exercise-3-look-command.md)** (~25 minutes, stretch)
   - Connect Week 04's Command pattern to Week 05's engine
   - Describe the player's current location, items, actors, and exits
   - Preview the "wiring problem" we'll solve next week
