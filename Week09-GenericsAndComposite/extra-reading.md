# Extra Reading: Generics & The Composite Pattern

> Companion to [Week 09's README](README.md).

## Part 1 — Generics

### Why generics exist

Before Java 5, this was a typical list:

```java
List list = new ArrayList();
list.add("hello");
list.add(42);                 // compiler doesn't complain
String s = (String) list.get(0);  // explicit cast required
String t = (String) list.get(1);  // CRASH at runtime
```

A `List` could hold anything. Every `get()` needed a cast. Type errors only
showed up at runtime.

Generics solve this:

```java
List<String> list = new ArrayList<>();
list.add("hello");
list.add(42);                 // compile error
String s = list.get(0);       // no cast needed
```

The `<String>` parameter tells the compiler what's inside. Adding the wrong
type is caught at compile time. Reading doesn't need a cast.

### Type parameters

```java
public class GenericArrayList<T> implements GenericList<T> {
    private Object[] items;        // see "type erasure" below
    private int size;

    public void add(T item) {
        items[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int i) {
        return (T) items[i];
    }
}
```

`T` is a placeholder. At each use site, `T` becomes the type the caller
chose. `GenericArrayList<String>` makes `T` mean `String`; `GenericArrayList<Integer>`
makes it mean `Integer`. The compiler enforces this everywhere.

### Type erasure

Java generics are *erased* at runtime: `GenericArrayList<String>` and
`GenericArrayList<Integer>` are the **same class** in the running JVM. The
type info exists only at compile time.

Consequences:
- You can't `new T[10]` — the JVM has no idea what `T` is. You use
  `new Object[10]` and cast on read. (That's why our `GenericArrayList`
  has `Object[] items`.)
- You can't `instanceof GenericList<String>` — only `instanceof GenericList<?>`.
- You can't have two overloads `foo(List<String>)` and `foo(List<Integer>)`
  — they erase to the same signature.

For our course these limitations rarely bite. Just know they exist.

### Bounded type parameters

Sometimes you need `T` to be more than `Object`:

```java
public class SortedList<T extends Comparable<T>> {
    public void add(T item) {
        // can call item.compareTo(other) because we know T extends Comparable
    }
}
```

You'll see `extends`-bounded generics often in real Java code. For our
exam-relevant work, plain `<T>` is usually enough.

### Wildcards (`?`)

`List<?>` means "a list of *something*, I don't care what". Useful when
you only need to read:

```java
void printAll(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}
```

For most exam-project code you won't need wildcards. They're a corner-case
tool for library writers.

## Part 2 — The Composite Pattern

### The shape

Composite is the pattern for *tree-shaped data where leaves and branches
share an interface*.

```
        Node                    ←─ common abstraction
       /    \
   FileNode  Directory          ←─ Directory contains [Node, Node, …]
                |
              [Node, Node, …]
```

Three roles:
- **Component** (the interface): what every node has in common.
- **Leaf**: a node with no children.
- **Composite**: a node that contains other nodes.

Our example:
- `Node` is the component.
- `FileNode` is the leaf.
- `DirectoryImpl` (which implements `Directory extends Node`) is the
  composite.

### Why the pattern exists

Without Composite, walking a tree means switching on type at every level:

```java
void process(Object o) {
    if (o instanceof FileNode f) {
        // do file thing
    } else if (o instanceof Directory d) {
        for (Object child : d.children()) {
            process(child);                   // recurse
        }
    } else {
        throw new IllegalStateException();
    }
}
```

That `instanceof` ladder is exactly what we want to *avoid*. Every time
you add a node type you have to update every traversal.

With Composite, you put the recursion *on the interface*:

```java
public interface Node {
    String name();
    void accept(NodeVisitor visitor);   // or whatever your traversal needs
}
```

Each concrete node knows how to traverse itself. Adding a new node type
means writing one class. No existing code breaks.

### Why our `NodePrinter` still works

`NodePrinter` looks at *what kind of node it has* — but it does it via the
`Node` interface, not via concrete classes. It doesn't care if a directory
is a `DirectoryImpl` or a `NetworkDirectory` or anything else, as long as
the thing satisfies `Directory`.

### Composite + Command (the homework)

The multilevel menu exercise combines Composite with Command:

- A menu is a *tree* of menu items → Composite.
  - `MenuItem` is the component.
  - `ActionItem` is the leaf — has a `Command` to run when chosen.
  - `Submenu` is the composite — has a list of `MenuItem`s.
- The action a menu item runs is a `Command` → Command pattern.

Two patterns, two responsibilities:
- Composite handles the **shape** (tree of items).
- Command handles the **behaviour** (what to do when an item is chosen).

Separating them lets you have menus *without commands* (pure navigation
trees), commands *without menus* (called from anywhere else), and easily
move commands around the tree.

## For The Exam

- Define generics. Show a small generic class on the whiteboard.
- Explain type erasure and its consequences (no `new T[]`, etc.).
- Define Composite. Draw the three-role diagram.
- Walk through `FileSystem` and identify Component / Leaf / Composite.
- Explain why the multilevel menu exercise needs *both* Composite and
  Command.
