# Week 05: Designing the Engine — Navigation & The Builder

**Date:** 10 March 2026

## What We Did In Class

With the engine interfaces from Week 04 in place, we started filling in the
**navigation layer**. This was our first real encounter with the Builder
pattern.

What we built (in `VP_Project/.../engine/`):
- **`LocationMap`** — the world, with a coordinate system
- **`LocationCoordinate`** — a record for (x, y, …) addresses
- **`LocationMapBuilder`** — interface for *constructing* maps step by step
- **`SimpleLocationMap`** — a concrete implementation
- **`SimpleLocation`** — a concrete `Location`
- **`SimpleLocationMapBuilder`** — concrete builder that wires up neighbours
  between locations

The key insight: constructing a location map is a multi-step affair (add
location, then another, then connect them). You can't pass that to a
constructor — that's what the **Builder** is for. The builder accumulates
state and produces a finished, immutable-ish object when you call `build()`.

We hit a subtle problem: when you connect location A north→B, you usually want
B south→A automatically. The builder handles that bidirectional wiring so
client code doesn't have to remember.

## Code From This Session

📂 [`VP_Project/.../engine/`](../VP_Project/src/main/java/dk/ek/evu/vpf26/txtadv/engine/)
— `LocationMap`, `LocationCoordinate`, `Direction`, `LocationMapBuilder` (interface)

📂 [`VP_Project/.../engine/impl/`](../VP_Project/src/main/java/dk/ek/evu/vpf26/txtadv/engine/impl/)
— `SimpleLocation`, `SimpleLocationMap`, `SimpleLocationMapBuilder`, `LocationMapTest`

## Material

- **[Pre-class reading: Letting the Code Tell You What's Missing](pre-class/reading.md)**
- **[Pre-class: The Engine So Far](pre-class/the-engine-so-far.md)** — walkthrough
  with 7 design questions
- **[Post-class reading: The Builder Pattern](post-class/reading.md)**
- **[Post-class exercise 1: Implement SimpleActor](post-class/exercises/exercise-1-simple-actor.md)**
- **[Post-class exercise 2: Flesh Out Item](post-class/exercises/exercise-2-flesh-out-item.md)**
- **[Post-class exercise 3 (stretch): Design a LookCommand](post-class/exercises/exercise-3-look-command.md)**
- **[Extra reading: Builder Pattern Variants](extra-reading.md)**

## For The Exam

Be able to:
- Define the **Builder pattern** and explain when you'd use it instead of a
  constructor (or a static factory method).
- Walk through `SimpleLocationMapBuilder`: what state does it hold, what does
  each method do, what does `build()` produce?
- Explain how the builder handles **bidirectional wiring** and why callers
  benefit from that.
- Compare **Builder** to **Factory** (covered in Week 06): when do you need
  each?
