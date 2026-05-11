# Week 04: Wishful Programming — The Engine Interfaces

**Date:** 3 March 2026

## What We Did In Class

This was the most important conceptual session of the course so far.

We sat down and **designed the entire game engine using only interfaces** —
not a single implementation, not even a `class` keyword in sight. The technique
is called **wishful programming**: write the code you *wish* you had, name the
types you *wish* existed, and only later figure out how to build them.

What we designed (in `VP_Project/.../engine/`):
- **`Game`** — the top-level game object
- **`GameLoop`** — drives turns
- **`Location`** — what a player stands in (renamed from `Room`)
- **`LocationMap`** — the world
- **`Direction`** — N/S/E/W (enum)
- **`Actor`** — anything that takes turns (players, NPCs)
- **`Player`**, **`NPC`** — specialised actors
- **`Item`** — things you can pick up

We also seeded the **Command pattern** infrastructure in `user/command/`:
- **`Command`** — the unit of action
- **`CommandRegistry`** — looks commands up by name
- A demo set in `command/demo/` (`WalkCommand`, `JumpCommand`, `RunCommand`)

Everything is interfaces. The implementations come later.

The lesson: when you design top-down, the interface design *guides* the
implementation. Implementation becomes "fill in the blanks" rather than
"figure out the architecture".

## Code From This Session

📂 [`VP_Project/.../engine/`](../VP_Project/src/main/java/dk/ek/evu/vpf26/txtadv/engine/)
— all the engine interfaces (no `impl/` content yet at the end of this session)

📂 [`VP_Project/.../user/command/`](../VP_Project/src/main/java/dk/ek/evu/vpf26/txtadv/user/command/)
— `Command`, `CommandRegistry`, and demo commands

## Material

- **[Pre-class reading: Wishful Programming](pre-class/reading.md)** — the
  methodology in detail
- **[Pre-class exercises: Notification System](pre-class/exercises/README.md)** —
  experience "implement last"
- **[In-class material](in-class/)** — what we walked through together
- **[Post-class reading: The Command Pattern](post-class/reading.md)**
- **[Post-class exercise 1: Design Task Tracker Interfaces](post-class/exercises/exercise-1-design-task-tracker-interfaces.md)**
- **[Post-class exercise 2: Wishful Workflow](post-class/exercises/exercise-2-wishful-workflow.md)**
- **[Extra reading: Wishful Programming as Methodology](extra-reading.md)**

## For The Exam

Be able to:
- Define **wishful programming** in your own words.
- Walk through the engine interfaces and explain *why* each one exists —
  what would break if you collapsed two of them into one, or split one in two.
- Explain the **Command pattern** using `Command` + `CommandRegistry` as your
  example. What problem does it solve compared to a giant `if/else` chain?
- Argue for **interface-first design** with a concrete example from your
  exam project.
