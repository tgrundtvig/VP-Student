# Week 01: Getting Started — The Naive Approach

**Date:** 3 February 2026

## What We Did In Class

This was our "naive coding" session. The goal was to *feel* what happens when you
build software without thinking about design first.

We built a small combat simulator from scratch — a `Player`, a `Monster`, and a
`CombatPlayerVSMonster` class that drives the fight. No interfaces, no separation
of concerns, just code that works.

By the end you had a working program — and a sneaking suspicion that adding
*one more thing* (a second kind of monster, a different player, a saved game,
a test) would be painful. That suspicion is the whole point. We come back to it
next week.

## Code From This Session

📂 [`Projects/TextAdventure/`](../Projects/TextAdventure/) — the naive combat code.

Key files to read:
- `src/main/java/dk/ek/evu/vpf26/textadventure/Player.java`
- `src/main/java/dk/ek/evu/vpf26/textadventure/Monster.java`
- `src/main/java/dk/ek/evu/vpf26/textadventure/CombatPlayerVSMonster.java`
- `src/test/java/...` — JUnit 5 tests we added afterwards

## Catch-Up & Reinforcement Material

Created after class to give a softer on-ramp for the "objects referencing objects"
idea that the room/maze structure (built next week) relies on. Optional, but
recommended if you felt the code went fast.

- **[Reading](pre-class/reading.md)** — what a text adventure is, how rooms link
- **[Exercises](pre-class/exercises/README.md)** — implement `Waypoint` and `Trail`
- **[Post-class reading: The Patterns Behind What We Built](post-class/reading.md)**
- **[Post-class exercise 1: Bidirectional Waypoints](post-class/exercises/exercise-1-bidirectional-waypoints.md)**
- **[Post-class exercise 2: Maze Explorer](post-class/exercises/exercise-2-maze-explorer.md)**

## For The Exam

Be able to:
- Explain *why* the naive approach feels fine for tiny programs but breaks down
  as soon as you need to change, test, or reuse the code.
- Point at concrete examples in `Projects/TextAdventure` of code that is hard
  to test or extend, and say why.
- Argue that "it works" is not the same as "it is good code".
