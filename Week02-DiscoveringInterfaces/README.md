# Week 02: Discovering Interfaces

**Date:** 17 February 2026

## What We Did In Class

We built the first proper version of our shared class project — the
**text adventure** in `VP_Project/`. This is where the course really starts.

What we built:
- A `Room` class where rooms link to other rooms via direction fields
- A `Mace` (maze) class that bundles a set of rooms together
- `MyMaceFactory` that constructs a sample maze
- A `Player` class that walks between rooms
- A `Main` class that wires it all up

Then the key moment: `Player` originally created a `Scanner` directly to read
input — which made it impossible to test. We extracted a **`TextIn` interface**
with two implementations:
- `ScannerTextIn` — for real gameplay (reads stdin)
- `ScriptedTextIn` — for tests (returns predetermined inputs)

That single extraction is what the rest of the course builds on. Once you have
an interface between you and a hard-to-test dependency, you can swap it for a
test double, a fake, an alternative implementation — without changing any of
the code that uses it.

## Code From This Session

📂 [`VP_Project/`](../VP_Project/) — our central class project.

Key files in `src/main/java/dk/ek/evu/vpf26/txtadv/`:
- `Room.java`, `Mace.java`, `MyMaceFactory.java` — the world
- `Player.java`, `Main.java` — the game loop
- **`TextIn.java`** — our first interface
- `ScannerTextIn.java`, `ScriptedTextIn.java` — two implementations

## Material

- **[Pre-class reading](pre-class/reading.md)** — full recap of Week 01 code; the
  testing problem; the interface pattern that solves it
- **[Pre-class exercises](pre-class/exercises/README.md)** — Greeting / Receptionist
  warm-up (interface with two implementations)
- **[Post-class reading: The principles behind what we built](post-class/reading.md)**
  — dependency injection, programming to an interface, seams, DIP
- **[Post-class exercise 1: A Third TextIn](post-class/exercises/exercise-1-third-textin.md)**
- **[Post-class exercise 2: Spot the Dependency](post-class/exercises/exercise-2-spot-the-dependency.md)**

## For The Exam

Be able to:
- Define what an **interface** is in Java and what problem it solves.
- Walk through the `Scanner` → `TextIn` extraction: show the broken version,
  explain why it's broken, show the fixed version, explain why it's better.
- Name and explain **dependency injection** and **programming to an interface,
  not an implementation** using `Player` + `TextIn` as your example.
- Explain *why* `ScriptedTextIn` makes tests possible.
