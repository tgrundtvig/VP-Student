# Exercise 1: Implement SimpleActor

## Goal

Implement the `Actor` interface as a `SimpleActor` class. This is a straightforward implementation exercise — the interface tells you exactly what to build.

## Time Estimate

25-30 minutes.

## What You Will Learn

- **Implementing an interface with state**: The actor needs fields to back the interface methods
- **Choosing the right data structures**: What collection type makes sense for an inventory?
- **Defensive returns**: Protecting internal state from outside modification

## Context

In class, we expanded the `Actor` interface with inventory and location methods:

```java
public interface Actor
{
    void takeTurn();

    Location getCurrentLocation();

    void setCurrentLocation(Location location);

    void addItem(Item item);

    void removeItem(Item item);

    boolean hasItem(Item item);

    List<Item> getItems();
}
```

We also implemented `SimpleLocation`, which has similar patterns (collections, unmodifiable returns). Use that as a reference.

## Instructions

### Step 1: Create the class

Create `SimpleActor` in the `dk.ek.evu.vpf26.txtadv.engine.impl` package. It should implement `Actor`.

Think about what fields you need:
- A name (every actor needs to be identifiable — we'll likely add `name()` to the interface later)
- A current location
- A collection of items (the inventory)

### Step 2: Implement the methods

Most methods are one-liners. A few things to consider:

- **`getItems()`** should return an unmodifiable view, just like `SimpleLocation.getActors()` does. You don't want outside code adding items to the list without going through `addItem()`.
- **`takeTurn()`** is the interesting one. For now, leave it empty or print a placeholder message. We don't yet know how human players and NPCs will differ in their turn behavior — that's a design question for next week.
- **What collection for items?** `Actor.getItems()` returns `List<Item>`. Think about whether the internal storage should also be a `List`, or whether a different collection might work better internally. (Hint: look at what `SimpleLocation` uses for its items, and think about whether order matters for an inventory.)

### Step 3: Constructor

Decide what the constructor needs. At minimum, a starting location seems reasonable. Should the actor be automatically added to that location's actor set? Think about who should be responsible for that.

### Step 4: Test it manually

Write a quick `main` method (or add to `LocationMapTest`) that:
1. Builds a map with a couple of locations
2. Creates a `SimpleActor` at one of the locations
3. Verifies `getCurrentLocation()` returns the right location
4. Adds an item, checks `hasItem()` returns true
5. Moves the actor using `LocationMap.moveActor()`, checks the new location

## Think About This

- `takeTurn()` is empty right now. That's fine — it's a placeholder. But think ahead: a human player's turn involves reading commands from `TextAppUser`. An NPC's turn involves AI logic. These are very different. How might we handle that difference without putting both behaviors in `SimpleActor`?

- The `Actor` interface might change as the game evolves. That's expected — we're discovering the design as we build. If you find yourself wanting a method that doesn't exist yet, write it down. That's valuable input for class discussions.

- Notice how `SimpleLocation` and `SimpleActor` have similar patterns: a collection field, add/remove methods, an unmodifiable getter. This isn't a coincidence — it's a common pattern for "a thing that contains other things."
