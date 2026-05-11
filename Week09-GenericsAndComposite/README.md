# Week 09: Generics & The Composite Pattern

**Date:** 14 April 2026

## What We Did In Class

Two big topics, both connected by the same idea: **abstracting over types
and shapes**.

### Part 1 — Generics: `StringList` becomes `GenericList<T>`

Last week's `StringList` only held `String`s. This week we reorganised the
project into two packages:

- `homemadecollections/first/` — the string-only versions (kept for comparison)
- `homemadecollections/generic/` — the new generic versions

What we built:
- **`GenericList<T>`** — generic interface
- **`GenericArrayList<T>`** — array-backed (with the `Object[]` cast trick
  that Java's own `ArrayList` uses)
- **`GenericLinkedList<T>`** — linked-node version
- **`GenericListNode<T>`** — generic node

The lesson: **generics are how you write one class that works for many types
without giving up type safety**. The code is almost identical to the string
versions — the compiler enforces the types at use-sites, not inside the list.

### Part 2 — The Composite Pattern: `FileSystem`

We then introduced a brand-new project, [`Projects/FileSystem/`](../Projects/FileSystem/),
to teach the **Composite pattern** — the design pattern for tree-shaped data.

What we built:
- **`Node`** — the common abstraction; everything in the tree is a `Node`
- **`FileNode`** — a *leaf*; has a name and content, no children
- **`DirectoryImpl`** (implements **`Directory` extends `Node`**) — a
  *composite*; has a name **and** contains other `Node`s
- **`NodePrinter`** — a workflow that prints the whole tree recursively

The key Composite insight: `NodePrinter` only depends on the `Node`
abstraction. It switches on what kind of node it sees (file vs directory) but
*never on a concrete class*. Adding a new node type (e.g. a symbolic link)
wouldn't break the printer.

At the end we assigned the **[multilevel menu exercise](../Exercise/exercise.md)**
which combines Composite + Command — your homework for the rest of the course.

## Code From This Session

📂 [`Projects/HomeMadeCollections/`](../Projects/HomeMadeCollections/) —
`generic/` package added; `first/` package preserved for comparison

📂 [`Projects/FileSystem/`](../Projects/FileSystem/) — `Node`, `FileNode`,
`Directory`, `DirectoryImpl`, `NodePrinter`, `Demo`

📄 [`Exercise/exercise.md`](../Exercise/exercise.md) — the multilevel menu
exercise (Composite + Command)

## Material

- **[Extra reading: Generics, Type Erasure, and the Composite Pattern](extra-reading.md)**

## For The Exam

Be able to:
- Rewrite `StringList` as `GenericList<T>` on the spot. Explain *what changes
  and what stays the same*.
- Explain the **Composite pattern** using `FileSystem` as your example. Draw
  the class diagram. Name the *leaf* and the *composite*.
- Walk through `NodePrinter` and explain why it works for *any* tree of `Node`s.
- Sketch how you'd add a new node type (e.g. `SymlinkNode`) and what code you
  would and would not need to change.
- Explain how the **multilevel menu exercise** uses Composite + Command
  together, and why both patterns are needed.
