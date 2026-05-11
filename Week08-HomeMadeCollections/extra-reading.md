# Extra Reading: Lists Under the Hood

> Companion to [Week 08's README](README.md).

## The Two Shapes Of A List

Both `StringArrayList` and `StringLinkedList` satisfy the `StringList`
interface, but they store data in fundamentally different ways.

### Array-backed (`StringArrayList`)

```
items: ["a", "b", "c", "d", null, null, null]
size:  4
capacity: 7
```

A single contiguous block of memory. `items[i]` is one memory access — the
CPU calculates the address from the start of the array plus `i * cellSize`.

Adding at the end is constant time *if there's room*. When there isn't,
you allocate a new bigger array (typically 1.5× or 2×) and copy everything
over.

Adding at the start (`add(0, "x")`) requires shifting every element right
by one. Slow.

### Linked-backed (`StringLinkedList`)

```
head → [a|·] → [b|·] → [c|·] → [d|null]
```

A chain of nodes, each holding a value and a pointer to the next node.
`get(i)` requires walking from the head, following pointers, until you've
counted i steps. Slow for large `i`.

But adding at the head is just creating a new node and pointing it at the
old head. Constant time, no shifting, no resizing.

## Big-O Complexity

| Operation        | ArrayList         | LinkedList        |
|------------------|-------------------|-------------------|
| `get(i)`         | **O(1)**          | O(n)              |
| `set(i, x)`      | **O(1)**          | O(n)              |
| `addLast(x)`     | **O(1) amortised**| **O(1)** *        |
| `addFirst(x)`    | O(n)              | **O(1)**          |
| `add(i, x)`      | O(n)              | O(n)              |
| `remove(i)`      | O(n)              | O(n)              |
| `contains(x)`    | O(n)              | O(n)              |
| `size()`         | **O(1)**          | **O(1)** **       |

\* Only if you keep a tail pointer. Our `StringLinkedList` had one.
\** Only if you keep a size counter. Ours did.

"Amortised O(1)" for `addLast` on `ArrayList`: most adds are O(1) but
every so often you hit a resize that's O(n). Average over many adds: O(1).

## Memory Layout Differences

ArrayList:
- One big allocation. Contiguous in memory.
- Cache-friendly: walking the list reads adjacent memory.
- Wasted space: capacity > size means unused slots.

LinkedList:
- One allocation per element, plus the node object itself.
- Not cache-friendly: each `next` pointer might jump anywhere in memory.
- No wasted slots, but each element costs an extra ~16 bytes (the node
  wrapper + reference).

For small lists this doesn't matter. For lists of millions, ArrayList
usually wins on memory and speed *even when* the theoretical complexity
favours LinkedList.

## When To Pick Which

Default: **ArrayList**. It's faster in practice for most use cases.

Pick LinkedList when:
- You're constantly adding/removing at the *front* (queue).
- You're constantly adding/removing in the *middle* via an iterator
  (you already have the node — no walk needed).
- You can't afford the cost of array resizing (real-time systems).

`java.util.ArrayDeque` is usually better than `LinkedList` for queues
these days. But you implemented `LinkedList` for the conceptual lesson.

## The Interface Hides The Choice

The whole point of writing `StringList` as an interface: the rest of your
code doesn't care. You can swap implementations based on profiling, with
no code changes anywhere else.

```java
StringList list = new StringArrayList();   // change to StringLinkedList freely
list.add("hello");
list.add("world");
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}
```

If you used the concrete class everywhere, you'd be locked in.

## For The Exam

- Implement `add`, `get`, `size` for both ArrayList and LinkedList on the
  whiteboard.
- State the Big-O complexity of each operation for both implementations,
  and *justify* it (don't just memorise the table).
- Given a usage pattern, recommend an implementation and defend the choice.
- Explain why writing `StringList` as an interface matters.
