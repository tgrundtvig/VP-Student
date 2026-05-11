# Extra Reading: Records and Why They Matter

> Companion to [Week 06's README](README.md).

## Records in One Sentence

A **record** is a Java class that declares only its data. The compiler
generates the rest.

```java
public record LocationCoordinate(int x, int y) { }
```

That one line gives you:
- A constructor `new LocationCoordinate(3, 5)`
- Accessors `coord.x()` and `coord.y()`
- A correct `equals()` based on `x` and `y`
- A correct `hashCode()` based on `x` and `y`
- A useful `toString()`: `LocationCoordinate[x=3, y=5]`
- *Immutable* fields (you can't change `x` or `y` after creation)

The equivalent regular class is ~40 lines of boilerplate. Records compress
it to one line because data classes are *that common*.

## Why Records Matter For Our Methodology

The course rule is: **records for data, interfaces for behaviour**.

Records make data classes cheap to create. That removes the temptation to
shove data fields onto unrelated classes "because writing a class is
annoying". Need a coordinate? `record Point(int x, int y)`. Need a result
from a method? `record SearchResult(Path path, double score)`. Need to
return two things? `record Pair<A, B>(A first, B second)`.

If declaring a data class costs one line, you'll do it. The code stays
shapely.

## When NOT To Use A Record

Records aren't for everything.

- **Behaviour-heavy classes.** A class with significant logic isn't a
  record — write an interface and an implementation.
- **Mutable state.** Records are final and immutable. If you need to
  change fields after construction, you need a regular class.
- **Inheritance.** Records can't extend other classes (they implicitly
  extend `Record`). They *can* implement interfaces.
- **JPA entities.** Most ORMs need no-arg constructors and setters,
  which records can't provide.

The decision tree:
- Just data, no behaviour? → **record**
- Behaviour, no/few fields? → **interface + class** (or just a class)
- Both? → **class with the behaviour, holding records inside**

## Compact Constructors

If you need validation in a record, use a *compact constructor*:

```java
public record LocationCoordinate(int x, int y) {
    public LocationCoordinate {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be non-negative");
        }
    }
}
```

Note: no parameter list, no field assignments. The compiler still
generates the field assignments after your validation runs. You just put
your checks in the body.

## Records + Interfaces — A Useful Combo

You can have a record *implement* an interface. This is occasionally very
clean:

```java
public interface Feedback {
    boolean isCorrect();
    String message();
}

public record HigherFeedback() implements Feedback {
    public boolean isCorrect() { return false; }
    public String message() { return "Higher!"; }
}

public record LowerFeedback() implements Feedback {
    public boolean isCorrect() { return false; }
    public String message() { return "Lower!"; }
}

public record CorrectFeedback(int guess) implements Feedback {
    public boolean isCorrect() { return true; }
    public String message() { return "Correct! It was " + guess; }
}
```

Polymorphism with zero ceremony. Each record carries exactly the data it
needs and nothing else.

## Patterns Recap From This Week

### Factory pattern variants

- **Static factory method.** `LocationMap.empty()`,
  `List.of(1, 2, 3)`. Just a static method that returns a new object.
- **Factory class.** A separate `LocationMapFactory` class with
  `create()` methods. Useful when creation has its own state or
  dependencies.
- **Factory interface.** A `PlayerFactory` interface with multiple
  implementations. Lets you swap creation logic at runtime — exactly
  what we did in the `GuessANumber` project to switch between human and
  computer players.

The factory pattern is about **hiding "which concrete class do I make?"**
behind a method, so client code never says `new ConcreteThing()`.

### Builder vs Factory

| Builder                       | Factory                       |
|-------------------------------|-------------------------------|
| Multi-step assembly           | One-shot creation             |
| Carries state between calls   | Stateless (usually)           |
| `build()` finishes the object | `create()` returns the object |

You can combine them: a builder that takes a factory to make the parts.

## For The Exam

- Define a record in one sentence. List what the compiler generates.
- State and defend "records for data, interfaces for behaviour".
- Show three factory variants and explain when to use each.
- Combine: walk through a hypothetical `RoomBuilder` that uses a
  `LocationFactory` internally.
