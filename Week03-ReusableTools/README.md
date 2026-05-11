# Week 03: Reusable Tools — TextUser & The Adapter Pattern

**Date:** 24 February 2026

## What We Did In Class

We took the `TextIn` lesson from last week and applied it more deliberately:
instead of writing one interface at a time as problems arose, we **designed a
small interface hierarchy** for user I/O.

What we built (in `VP_Project/.../user/`):
- **`TextUser`** — the minimal text I/O interface (`print`, `println`, `readLine`)
- **`TextAppUser`** — a richer interface for text applications, with menus and
  validated input, that *uses a* `TextUser` underneath
- **`TerminalUser`** — a `TextUser` implementation backed by `Scanner` + `IO`
- **`TextAppUserImpl`** — a `TextAppUser` implementation that wraps any `TextUser`

That last one is the **Adapter pattern** in disguise: `TextAppUserImpl` adapts
a low-level `TextUser` into a higher-level `TextAppUser` by composition. The
caller doesn't care which `TextUser` it's adapting — terminal, scripted, GUI —
the high-level tools work the same.

This is the core idea: build *small, focused* interfaces, then compose them
into bigger tools. The same `TextUser` will later power both the terminal
adventure and the JavaFX one.

## Code From This Session

📂 [`VP_Project/src/main/java/dk/ek/evu/vpf26/txtadv/user/`](../VP_Project/src/main/java/dk/ek/evu/vpf26/txtadv/user/)

Key files:
- `TextUser.java`, `TextAppUser.java` — the two interfaces
- `impl/TerminalUser.java`, `impl/TextAppUserImpl.java` — implementations
- `demo/TextUserDemo.java`, `demo/TextAppUserDemo.java` — how to use them

## Material

- **[Pre-class reading: From Specific to Reusable](pre-class/reading.md)** —
  small interfaces, big tools, why
- **[Pre-class exercises: TextOut Warm-Up](pre-class/exercises/README.md)** —
  mirror `TextIn` with a `TextOut`
- **[In-class exercises](in-class/)** — the `TextUser`/`TextAppUser` walkthrough
- **[Post-class material](post-class/)** — extension exercises
- **[Extra reading: Adapter and Composition over Inheritance](extra-reading.md)**

## For The Exam

Be able to:
- Define the **Adapter pattern** and identify it in `TextAppUserImpl`.
- Explain why we wrote *two* interfaces (`TextUser` and `TextAppUser`) instead
  of one big one.
- Argue for **composition over inheritance**: why `TextAppUserImpl` *has a*
  `TextUser` instead of *being a* `TextUser`.
- Sketch how you'd add a third implementation (e.g. a GUI-backed `TextUser`)
  and show that no existing code needs to change.
