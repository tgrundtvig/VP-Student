# Week 08: Home-Made Collections — Build Your Own List

**Date:** 7 April 2026

> No session on 31 March — Easter break (påskeferie).

## What We Did In Class

You use `ArrayList` and `LinkedList` every day. This week we **built our own**
from scratch, so you understand what's actually inside.

What we built (in `Projects/HomeMadeCollections/`):
- **`StringList`** — our interface: `add`, `addLast`, `get`, `size`, `remove`,
  `contains`, …
- **`StringArrayList`** — array-backed implementation (with manual resizing)
- **`StringLinkedList`** — linked-node implementation
- **`StringListNode`** — the node class behind `StringLinkedList`

The interesting bit was watching the same operations behave differently
depending on the backing data structure: `get(i)` is fast for the array, slow
for the linked list. `addFirst` is the opposite. The interface hides this —
client code doesn't care — but **you should care** when choosing one.

We started the GuessANumber project properly the same evening (the bulk
of its code was committed this session, after the small-group prototype
from Week 7).

## Code From This Session

📂 [`Projects/HomeMadeCollections/`](../Projects/HomeMadeCollections/)

Key files in `src/main/java/dk/ek/vp/homemadecollections/first/`:
- `StringList.java` — the interface
- `impl/StringArrayList.java`, `impl/StringLinkedList.java`,
  `impl/StringListNode.java`
- `Demo.java` — runs both implementations through the same interface

## Material

- **[Extra reading: Lists Under the Hood — Big-O, Memory, Trade-offs](extra-reading.md)**

## For The Exam

Be able to:
- Implement a small `List<T>` interface from scratch on a whiteboard, with
  both an array-backed and a linked implementation.
- State the **Big-O complexity** of `get(i)`, `add`, `addFirst`, `addLast`,
  `contains`, `remove` for both implementations and explain *why*.
- Recommend which implementation to use given a usage pattern (lots of
  random access? lots of insertions at the start? etc.).
- Explain why writing `StringList` as an interface (rather than a class)
  matters — what does it buy the user of the list?
