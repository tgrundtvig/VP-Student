# Week 06: Wiring It Together — Factory, Builder & Records

**Date:** 17 March 2026

## What We Did In Class

This was a "patterns" session built around the engine code we already had.
Three things landed:

1. **Factory pattern** — when an object's *creation* depends on choices the
   caller shouldn't have to know about, hide it behind a factory. We went
   through static factory methods, factory interfaces, and the situations
   each one fits.

2. **GoF Builder vs Bloch Builder** — we deliberately picked the
   *Gang-of-Four* (compositional assembly) style, where the builder builds
   one part at a time and returns a finished object, over Bloch's fluent
   builder (which is mainly about constructor parameter names). The
   `LocationMapBuilder` from Week 5 is GoF-style.

3. **Java records** — Java's modern way to write immutable data carriers.
   We used them for value-types like `LocationCoordinate` and contrasted
   them with classic POJOs. The rule of thumb from this session:
   **records for data, interfaces for behaviour**.

After class, a comprehensive design-patterns reference was added to
`post-class/` covering all 23 GoF patterns plus MVC, Repository, and DI.
Don't try to read it all at once — it's a long-term reference.

## Code From This Session

The Factory/Builder/records material was largely conceptual and exercise-driven
rather than landing as a single new project. The patterns are visible in the
existing engine code:

- 📂 [`VP_Project/.../engine/`](../VP_Project/src/main/java/dk/ek/evu/vpf26/txtadv/engine/)
  — `LocationMapBuilder` (interface), `LocationCoordinate` (record)
- 📂 [`VP_Project/.../engine/impl/`](../VP_Project/src/main/java/dk/ek/evu/vpf26/txtadv/engine/impl/)
  — `SimpleLocationMapBuilder` (concrete builder)

You will see another factory in Week 7's `GuessANumber` project
(`PlayerFactory` / `PlayerFactoryImpl`).

## Material

- **[Pre-class reading: The Wiring Problem](pre-class/reading.md)**
- **[Pre-class: The Pieces We Have](pre-class/the-pieces-we-have.md)** —
  inventory + design sketch
- **[In-class reading: The Factory Pattern](in-class/reading.md)**
- **[In-class exercises: Factory & Builder](in-class/exercises.md)** — five
  graduated exercises
- **[Post-class reading: Design Patterns — A Comprehensive Guide](post-class/reading.md)**
  — long-term reference; all 23 GoF patterns + MVC, Repository, DI
- **[Post-class exercises: One per pattern](post-class/exercises.md)**
- **[Extra reading: Records and Why They Matter](extra-reading.md)**

## For The Exam

Be able to:
- Define the **Factory pattern**. Distinguish static factory method, factory
  class, and factory interface — give an example of when you'd pick each.
- Define **records**: what they are, what they give you for free
  (`equals`, `hashCode`, `toString`, accessor methods), and when to use them
  over a regular class.
- State the rule **"records for data, interfaces for behaviour"** and defend
  it with examples.
- Compare **Builder** (Week 05) to **Factory** (Week 06): which problem does
  each solve? When would you combine them?
