# The Builder Pattern

In class this week, you used `LocationMapBuilder` to construct a `LocationMap`. You called `addLocation()` several times to define locations, then called `build()` to produce the finished map with all neighbours wired up. This isn't a one-off trick — it's a well-known design pattern called the **Builder pattern**.

This reading gives it a name, explains why it exists, and shows where it appears in real software.

**Reading time: 15 minutes.**

---

## 1. Before and After

### Without a Builder

Imagine constructing a `SimpleLocationMap` directly. You'd need to:

1. Create each `SimpleLocation` by hand
2. Calculate neighbour coordinates for every direction
3. Wire up each location's neighbour array
4. Put them all into a `Map<LocationCoordinate, SimpleLocation>`
5. Pass that map to `SimpleLocationMap`

That's a lot of fiddly, error-prone setup — and the caller needs to know about `SimpleLocation`, `LocationCoordinate`, `Direction`, and the internal map structure. The caller is doing the builder's job.

### With a Builder

```java
LocationMapBuilder builder = new SimpleLocationMapBuilder();
builder.addLocation(1, 0, 0, "Tavern", "A dimly lit tavern.");
builder.addLocation(1, 1, 0, "Dock", "A busy dock.");
builder.addLocation(0, 1, 0, "Market", "A noisy market.");
LocationMap map = builder.build();
```

The caller says *what* it wants (three locations at these coordinates with these names). The builder handles *how* — creating the internal objects, calculating neighbours, wiring everything up. The `build()` call does the heavy lifting and returns a finished, ready-to-use object.

---

## 2. The Pattern Elements

The Builder pattern has three parts:

| Element | Role | In Our Game |
|---------|------|-------------|
| **Builder** (interface) | Defines the construction steps | `LocationMapBuilder` with `addLocation()` and `build()` |
| **Concrete Builder** | Implements the construction logic | `SimpleLocationMapBuilder` |
| **Product** | The object being built | `LocationMap` (specifically `SimpleLocationMap`) |

The caller works with the builder interface. It doesn't know or care which concrete builder is being used, or what the internal structure of the product looks like.

---

## 3. Why Not Just Use a Constructor?

You might think: "Why not just pass everything to a constructor?"

```java
// Hypothetical: pass all locations to the constructor
LocationMap map = new SimpleLocationMap(location1, location2, location3);
```

This works for simple cases. But builders shine when:

- **Construction is multi-step**: You add locations one at a time. A constructor would need everything at once.
- **The product needs post-processing**: After all locations are added, `build()` wires up neighbours. A constructor can't do "add things, then finalize."
- **You want to hide internals**: The caller never sees `SimpleLocation`, `LocationCoordinate`, or the neighbour array. The builder encapsulates all of that.
- **Validation can happen at build time**: `build()` could check that all locations are reachable, or that coordinates don't overlap.

The Builder pattern separates *accumulating configuration* from *constructing the result*.

---

## 4. Connection to Interface-First Design

Notice something important: the caller only uses `LocationMapBuilder` (the interface), not `SimpleLocationMapBuilder` (the implementation). This means:

- Someone could write a `FileLocationMapBuilder` that reads locations from a file
- Someone could write a `RandomLocationMapBuilder` that generates a random dungeon
- The calling code wouldn't change at all

The builder interface was designed by thinking about what the caller needs: "I want to add locations and then get a finished map." The implementation details — `HashMap`, coordinate math, neighbour wiring — are hidden behind that interface.

This is the same principle we've applied everywhere: design the interface from the caller's perspective, implement later.

---

## 5. Where You See This in the Real World

The Builder pattern is extremely common:

- **StringBuilder**: Java's `StringBuilder` lets you `append()` pieces and then call `toString()` to get the final string. Same idea: accumulate, then build.
- **HTTP requests**: Libraries like `HttpRequest.newBuilder().uri(...).header(...).build()` construct complex request objects step by step.
- **GUI layouts**: UI frameworks often use builders for dialogs, menus, and layouts — add components one at a time, then finalize.
- **Game level editors**: Any tool where you place objects on a map and then "export" the level is using the builder concept.
- **Test data**: Test frameworks often use builders to create test objects: `new UserBuilder().withName("Alice").withAge(30).build()`.

---

## 6. Our Builder Is Still Evolving

The current `LocationMapBuilder` interface is simple:

```java
public interface LocationMapBuilder
{
    void addLocation(LocationCoordinate coordinate, String name, String description);
    void addLocation(int x, int y, int z, String name, String description);
    LocationMap build();
}
```

As the game evolves, this interface might change. Maybe we'll need to add items to locations during building. Maybe we'll want to define one-way passages or locked doors. The builder gives us a natural place to add these capabilities without changing how the rest of the system works.

This is a theme you'll see throughout the course: interfaces evolve as we discover what we need. A good interface isn't one that's "finished" — it's one that can grow without breaking what already works.

---

## 7. Summary

| Concept | What It Means |
|---------|--------------|
| **Builder pattern** | Separate object construction from object representation |
| **Multi-step construction** | Add pieces one at a time, then finalize with `build()` |
| **Encapsulation** | The caller doesn't know how the product is assembled internally |
| **Interface-first** | Design the builder interface from the caller's needs |

---

## Further Reading

If you want to dig deeper:

- **Refactoring Guru — Builder Pattern**: https://refactoring.guru/design-patterns/builder — visual explanations with Java examples.
- **"Effective Java" by Joshua Bloch** — Item 2 covers the Builder pattern as a best practice for constructing complex objects.
- **Java's own builders**: Look at `StringBuilder`, `HttpRequest.newBuilder()`, or `Stream.builder()` in the Java standard library.
