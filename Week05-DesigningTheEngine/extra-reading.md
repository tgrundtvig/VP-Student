# Extra Reading: Builder Pattern Variants

> Companion to [Week 05's README](README.md).

## Why Builders Exist

You can't always pass everything a constructor needs at once. Reasons:

1. **Too many parameters.** A constructor with 12 arguments is unreadable
   and error-prone (positional confusion: which one is the int that means
   "max size"?).
2. **Optional parameters.** Java doesn't have default arguments. You end
   up with N constructors of every length.
3. **Multi-step assembly.** You need to add things one at a time
   (locations to a map, items to a room) — that's not what constructors are
   for.
4. **Cross-cutting wiring.** When connecting object A north→B, you want
   B south→A automatically. A constructor can't do that across two
   objects.

## GoF Builder vs Bloch Builder

There are two named patterns called "Builder". Don't confuse them.

### GoF Builder (compositional assembly)

This is the one we used for `LocationMapBuilder`. The builder is a *separate
object* that collects state in multiple calls and produces the final
object on `build()`:

```java
LocationMapBuilder builder = new SimpleLocationMapBuilder();
builder.addLocation("hall", new SimpleLocation("a great hall"));
builder.addLocation("kitchen", new SimpleLocation("a smoky kitchen"));
builder.connect("hall", Direction.EAST, "kitchen");   // auto-wires kitchen WEST→hall
LocationMap map = builder.build();
```

Key features:
- Builder is a separate type (often an interface).
- Each method does work — wires neighbours, validates, etc.
- `build()` produces the finished, possibly immutable, product.
- You can have multiple builders for the same product (different strategies,
  different formats).

### Bloch Builder (fluent constructor)

This one is just about giving names to constructor arguments:

```java
Pizza p = new Pizza.Builder()
    .size(Size.LARGE)
    .topping("mushrooms")
    .topping("olives")
    .extraCheese(true)
    .build();
```

Features:
- Builder is usually a nested static class inside the thing it builds.
- Methods just store values into fields.
- `build()` does almost nothing — just calls the real constructor.
- Mostly a workaround for Java not having default/named arguments.

### When to use which

| Situation                                    | Use            |
|---------------------------------------------|----------------|
| Multi-step assembly, cross-object wiring     | GoF Builder    |
| Validation logic between steps               | GoF Builder    |
| Many optional parameters, one-shot creation  | Bloch Builder  |
| You want default values + clarity            | Bloch Builder  |

For records (Java 14+), Bloch Builder is often unnecessary — a record's
canonical constructor is already named-parameter-ish in modern IDEs, and
factory methods cover most "default value" needs.

## The Bidirectional Wiring Trick

Look at how `SimpleLocationMapBuilder.connect` works:

```java
public void connect(String fromId, Direction d, String toId) {
    SimpleLocation from = locations.get(fromId);
    SimpleLocation to = locations.get(toId);
    from.setNeighbour(d, to);
    to.setNeighbour(d.opposite(), from);   // <-- automatic
}
```

That second line is the win. Client code never has to remember to wire
the back-edge. **The builder enforces an invariant** (every neighbour
relation is bidirectional) that the client would otherwise have to
maintain by hand.

This is a common builder pattern: hide invariant maintenance in the
builder so the client can't forget.

## Immutable Build Result

A common GoF Builder convention: `build()` returns an *immutable* object,
even though the builder itself was mutable. The pattern:

```java
public LocationMap build() {
    return new SimpleLocationMap(Collections.unmodifiableMap(locations));
}
```

Once `build()` returns, the client can't mutate the resulting map. Why
this matters: the rest of the program can pass `LocationMap` around with
no fear that someone else will change it.

If you want to *keep mutating*, you keep the builder. Once you're done,
you `build()` and throw the builder away.

## For The Exam

- Define the Builder pattern (the GoF flavour, not the Bloch one — but
  know both exist).
- Walk through `SimpleLocationMapBuilder`: state, methods, `build()`.
- Explain the bidirectional wiring example as an "invariant in the
  builder".
- Distinguish Builder from Factory: a Builder accumulates state across
  multiple calls; a Factory makes an object in one shot.
