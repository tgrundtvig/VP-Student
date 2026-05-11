# Week 10: Trees & Recursion — Depth-First Search

**Date:** 21 April 2026

## What We Did In Class

We extended the `FileSystem` project from Week 9 with a **Depth-First Search**
(DFS). The goal was twofold:

1. **Recursion on trees** — show how a few lines of recursive code can walk
   an arbitrarily large tree.
2. **Search as an abstraction** — separate *how you search* from *what you do
   with the results*.

What we built:
- **`Search`** — interface with a `find(...)` method
- **`DepthFirstSearch`** — concrete DFS implementation that walks the
  `FileSystem` tree

The DFS is one recursive method on the `Node` abstraction. It works for any
tree of `Node`s, just like `NodePrinter` did — that's the Composite pattern
paying off again.

We talked about **DFS vs BFS** and when each is appropriate:
- DFS: when you want to go *deep* first (find any match, traverse a known
  structure). Implemented recursively (or with an explicit stack).
- BFS: when you want the *shortest path* first. Implemented with a queue.

Both are at most a dozen lines of code on top of the right abstraction.

## Code From This Session

📂 [`Projects/FileSystem/`](../Projects/FileSystem/)

New files:
- `src/main/java/dk/ek/vp/Search.java` — the interface
- `src/main/java/dk/ek/vp/DepthFirstSearch.java` — recursive DFS

## Material

- **[Extra reading: DFS, BFS, and Recursion vs Iteration](extra-reading.md)**

## For The Exam

Be able to:
- Write a depth-first search on a generic tree of `Node`s on the whiteboard.
- Write a breadth-first search on the same tree, and explain the structural
  difference (recursion / explicit stack vs queue).
- State the **time and space complexity** of DFS and BFS on a tree with N
  nodes.
- Recognise problems that are naturally **recursive on a tree** (find a file,
  count items, sum sizes, list paths …) and frame them as a `Search`.
- Explain why `Search` works on *the `Node` abstraction*, not on
  `DirectoryImpl` or `FileNode` specifically.
