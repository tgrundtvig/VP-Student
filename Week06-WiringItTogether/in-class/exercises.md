# Exercises: Factory and Builder Patterns

These exercises build on each other — each one extends the code you wrote in the previous exercise. You never throw anything away; you keep building.

Start from scratch. No starter code needed.

**Total time: ~60 minutes**

---

## Java Records — A Quick Introduction

In these exercises you'll use **records**, a Java feature for creating simple data classes. A record gives you a class with fields, a constructor, getters, `equals()`, `hashCode()`, and `toString()` — all generated automatically.

Here's a regular class compared to the equivalent record:

```java
// As a class — you write all of this yourself:
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() { return x; }
    public int y() { return y; }

    // plus equals(), hashCode(), toString()...
}

// As a record — one line, same result:
public record Point(int x, int y) {}
```

The parameters in the record declaration (`int x, int y`) become:
- **Private final fields** (immutable — cannot be changed after creation)
- **A constructor**: `new Point(3, 5)`
- **Accessor methods** named after the fields: `point.x()`, `point.y()`
- **`equals()`**, **`hashCode()`**, and **`toString()`** — generated automatically

Records can also implement interfaces. When they do, you may need to provide the method bodies yourself:

```java
public interface Named {
    String name();
    String description();
}

// The record parameter 'name' automatically satisfies the name() method.
// But description() needs an explicit implementation:
public record Pet(String name, String species) implements Named {
    public String description() {
        return name + " the " + species;
    }
}
```

If a record parameter has the **same name** as an interface method, the generated accessor satisfies it automatically. For methods that don't match a parameter, you write the body yourself.

**When to use records**: When you need a simple, immutable data carrier. Think of them as "a named tuple with types."

**When NOT to use records**: When you need mutable state, inheritance (records can't extend other classes), or when you need to assemble a complex object from many parts — that's where classes and builders come in (Exercise 4).

---

## Exercise 1: Creating Items by Hand (~10 minutes)

### Goal

Define an item system using interface-first design, then create items manually. Experience the limitations.

### What to build

**Step 1** — Design the interface. Create an `Item` interface with three methods:

```java
public interface Item {
    String name();
    String description();
    int value();
}
```

**Step 2** — Create three records that implement `Item`:

- `GoldCoin(int amount)` — name: `"Gold Coin"`, description: includes the amount, value: the amount
- `HealthPotion(int healAmount)` — name: `"Health Potion"`, description: mentions the heal amount, value: `healAmount * 2`
- `IronSword()` — name: `"Iron Sword"`, description: `"A sturdy iron sword"`, value: `50`

**Hint:** `GoldCoin` has one parameter (`amount`) but three interface methods to satisfy. The `amount` parameter doesn't match any interface method name, so you'll need to write all three method bodies. Think about which value each method should return.

**Step 3** — In a `main` method, create a `List<Item>` containing:

- 3 gold coins with different amounts
- 2 health potions with different heal amounts
- 1 iron sword

Print each item's name, description, and value.

### Verify

Your program runs and prints 6 items with their details.

### Think about

- How many places in your code mention `GoldCoin`, `HealthPotion`, or `IronSword` by name?
- What would you need to change if `HealthPotion` gained a second constructor parameter (say, `duration`)?

---

## Exercise 2: A Simple Factory (~10 minutes)

### Goal

Centralize item creation so the rest of your code doesn't need to know about concrete classes.

### What to change

**Step 1** — Create an `ItemFactory` class with static factory methods:

```java
public class ItemFactory {
    public static Item goldCoin(int amount) { ... }
    public static Item healthPotion(int healAmount) { ... }
    public static Item ironSword() { ... }
}
```

Each method creates and returns the appropriate record.

**Step 2** — Update your `main` method to use the factory instead of calling `new` directly:

```java
items.add(ItemFactory.goldCoin(10));
items.add(ItemFactory.healthPotion(25));
items.add(ItemFactory.ironSword());
```

### Verify

The output is identical to Exercise 1.

### Think about

- How many places in your code now mention `GoldCoin`, `HealthPotion`, or `IronSword`? Compare with Exercise 1.
- If `HealthPotion` gains a `duration` parameter, how many places need to change now?
- The factory returns `Item`, not the concrete type. The caller doesn't know (or care) what it gets back. Sound familiar?
- Can you have different "tiers" of items — basic items for a beginner area and rare items for an endgame area — with static methods alone?

---

## Exercise 3: Factory Interface (~15 minutes)

### Goal

Make the factory an interface so you can swap creation strategies without changing the code that uses it. This is where the pattern becomes powerful.

### What to build

**Step 1** — Define a `TreasureFactory` interface:

```java
public interface TreasureFactory {
    Item createCoin();
    Item createPotion();
    Item createWeapon();
}
```

**Step 2** — Create two implementations:

- `BasicTreasureFactory` — creates basic items: small gold coins, weak potions, iron swords
- `RareTreasureFactory` — creates enhanced items: large gold amounts, powerful potions, diamond swords with high value

Both implementations use your existing records (or create new records for the rare variants if needed).

**Step 3** — Write a method that fills a chest using any factory:

```java
static List<Item> fillChest(TreasureFactory factory) {
    List<Item> chest = new ArrayList<>();
    chest.add(factory.createCoin());
    chest.add(factory.createPotion());
    chest.add(factory.createWeapon());
    return chest;
}
```

**Step 4** — In `main`, call `fillChest` with both factories and print the results:

```java
System.out.println("--- Basic Chest ---");
List<Item> basicChest = fillChest(new BasicTreasureFactory());
basicChest.forEach(item -> System.out.println(item.name() + ": " + item.description()));

System.out.println("--- Rare Chest ---");
List<Item> rareChest = fillChest(new RareTreasureFactory());
rareChest.forEach(item -> System.out.println(item.name() + ": " + item.description()));
```

### Verify

You see two different sets of items — basic and rare — printed from the same `fillChest` method.

### Think about

- The `fillChest` method doesn't know which factory it received. Why is that powerful?
- How would you add a third tier (e.g., "cursed" items)? What code needs to change, and what stays the same?
- Where have you seen this same principle before in the course? (Hint: think about `TextUser`, `LocationMapBuilder`...)

---

## Exercise 4: Builder for Composite Objects (~15 minutes)

### Goal

Use the Builder pattern to assemble a complex object from simpler parts — the same way `LocationMapBuilder` assembles a map from locations.

### The pattern

The Builder pattern is about **composing complex objects from simpler pieces**. You've already seen this: `LocationMapBuilder` takes locations one at a time via `addLocation()`, then `build()` computes all the neighbour relationships and returns a finished, fully-wired `LocationMap`. The caller never touches the wiring logic.

The key is that `build()` does real work — it doesn't just store what you gave it, it *assembles, computes, and validates*.

### What to build

You're going to build treasure chests for the game. A chest is a composite object: it contains items, may have a trap, and can be locked. Instead of constructing all of this by hand, a builder will assemble it step by step.

**Step 1** — Define a `TreasureChest` interface:

```java
public interface TreasureChest {
    List<Item> items();
    int totalValue();
    boolean isTrapped();
    String trapType();
    boolean isLocked();
    int lockDifficulty();
}
```

**Step 2** — Create a `TreasureChestBuilder` class:

```java
public class TreasureChestBuilder {
    // fields to accumulate state

    public TreasureChestBuilder addItem(Item item) { ... }
    public TreasureChestBuilder trap(String trapType) { ... }
    public TreasureChestBuilder lock(int difficulty) { ... }
    public TreasureChest build() { ... }
}
```

The `build()` method should:
- **Compute** `totalValue` by summing the value of all added items
- **Validate** that at least one item was added (throw `IllegalStateException` if not — an empty chest makes no sense)
- **Create** an immutable `TreasureChest` with all the assembled state

You will need a concrete class that implements `TreasureChest` to hold the built result. The builder creates this internally — the caller never sees it.

**Step 3** — Build several chests in `main` using your `ItemFactory` from Exercise 2:

```java
TreasureChest simpleChest = new TreasureChestBuilder()
    .addItem(ItemFactory.goldCoin(50))
    .addItem(ItemFactory.healthPotion(25))
    .build();

TreasureChest trappedChest = new TreasureChestBuilder()
    .addItem(ItemFactory.goldCoin(200))
    .addItem(ItemFactory.ironSword())
    .addItem(ItemFactory.healthPotion(50))
    .trap("Poison Dart")
    .lock(5)
    .build();
```

Print each chest: how many items, total value, trapped/locked status.

### Verify

Chests print with correct item counts, computed total values, and trap/lock information. The `totalValue` is calculated by `build()`, not passed in by the caller.

### Think about

- Compare this to building a `LocationMap`: you add locations, then `build()` wires up neighbours. Here you add items, then `build()` computes total value and validates. In both cases, `build()` does the heavy lifting.
- The caller never computes the total value or checks if the chest is valid — the builder handles it. What would go wrong if the caller had to do this themselves?
- What other computed properties could `build()` derive? (e.g., total weight, the most valuable item, a danger rating based on trap + lock)

---

## Exercise 5: Factory + Builder (~10 minutes)

### Goal

Combine both patterns: the factory decides *what* goes in the chest, the builder handles *how* to assemble it.

### What to change

**Step 1** — Add a `createChest()` method to your `TreasureFactory` interface:

```java
public interface TreasureFactory {
    Item createCoin();
    Item createPotion();
    Item createWeapon();
    TreasureChest createChest();
}
```

**Step 2** — In `BasicTreasureFactory`, implement `createChest()` using the builder:

```java
public TreasureChest createChest() {
    return new TreasureChestBuilder()
        .addItem(createCoin())
        .addItem(createPotion())
        .build();
}
```

Notice how `createChest()` reuses the factory's own `createCoin()` and `createPotion()` methods — the factory is consistent with itself.

**Step 3** — In `RareTreasureFactory`, build a more elaborate chest:

```java
public TreasureChest createChest() {
    return new TreasureChestBuilder()
        .addItem(createCoin())
        .addItem(createWeapon())
        .addItem(createPotion())
        .trap("Poison Dart")
        .lock(8)
        .build();
}
```

**Step 4** — In `main`, create chests from both factories and print their contents.

### Verify

Both factories produce fully assembled chests. A basic chest has simple items and no trap. A rare chest has better items, a trap, and a lock. The code in `main` doesn't know which factory it's using or how the chest is assembled.

### Think about

- The factory decides *what* items go in the chest. The builder handles *how* to assemble it (computing total value, validating, etc.). Neither needs to change when the other changes.
- To add a new tier of chests (e.g., "cursed"), you write a new factory class. The builder stays the same.
- To add new chest features (e.g., a "mimic" flag), you update the builder and the `TreasureChest` interface. Existing factories don't break — they just don't use the new feature yet.
- **This is the same composition everywhere**: `LocationMapBuilder` builds maps from locations. `TreasureChestBuilder` builds chests from items. The factory decides which items and configuration to use.

---

## Bonus Exercises

If you finish early, try one or more of these extensions.

### Bonus A: Richer Computation in `build()`

Add more computed properties to `TreasureChest`: the name of the most valuable item, the average item value, or a "danger rating" (e.g., 0 = untrapped/unlocked, higher = more dangerous). These are all derived from the parts — the caller never sets them directly.

### Bonus B: Builder Interface

Extract a `TreasureChestBuilder` interface from your concrete builder class. Now someone could write a `DebugTreasureChestBuilder` that logs every item added, or a `LimitedTreasureChestBuilder` that caps the number of items. The factory works with the builder interface, not the concrete class.

### Bonus C: Nested Builders

What if a chest can contain other chests? Add an `addChest(TreasureChest innerChest)` method to the builder. The `build()` method now computes `totalValue` recursively — including the value of items inside nested chests. This is composition at its deepest.
