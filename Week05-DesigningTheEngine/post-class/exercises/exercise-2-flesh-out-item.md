# Exercise 2: Flesh Out Item

## Goal

The `Item` interface is currently empty. Add the minimum methods needed for items to be usable in the game, then implement a `SimpleItem`.

## Time Estimate

20-25 minutes.

## What You Will Learn

- **Discovering interface methods from usage**: Instead of guessing what Item needs, you'll figure it out by thinking about the code that uses items
- **The "minimum viable interface"**: Adding only what's needed now, not everything you might need someday
- **Record vs. interface decision**: Is Item data or behavior?

## Context

Here's the current `Item` interface:

```java
public interface Item
{
}
```

Completely empty. But the game already works with items in several places:

- `Location.getItems()` — items lying on the ground
- `Actor.getItems()` — items in an actor's inventory
- `Actor.addItem()` / `Actor.removeItem()` / `Actor.hasItem()` — inventory management

Soon we'll need commands like "look" (shows items in a location), "take" (picks up an item by name), and "inventory" (lists what you're carrying). What does `Item` need so those commands can work?

## Instructions

### Step 1: Think about what's needed

Imagine writing a "look" command that prints:

```
The Rusty Anchor Tavern
A dimly lit tavern that smells of salt and rum.
Items here: rusty sword, gold coin
```

And an "inventory" command that prints:

```
You are carrying: rusty sword, gold coin
```

And a "take" command where the player types `take sword` and the game needs to find the matching item.

What information must an `Item` provide?

### Step 2: Add methods to the interface

Add the methods you identified. Keep it minimal — only what's needed for the scenarios above.

Hint: Look at `Location` for inspiration. It has `name()` and `description()`. Does `Item` need the same? Does it need both, or just one?

### Step 3: Interface or record?

Think about whether `Item` should stay an interface or become a record.

Arguments for **record**:
- Items might just be data — a name and a description
- Records are simple and immutable

Arguments for **interface**:
- Some items might have behavior (a potion heals, a key unlocks a door)
- Different item types might describe themselves differently
- The game might have items that change state (a torch that burns out)

For this exercise, keep `Item` as an interface. The game is still evolving, and interfaces give us more flexibility. But it's worth thinking about — not everything with data needs to be an interface, and not everything with potential behavior needs one either.

### Step 4: Implement SimpleItem

Create `SimpleItem` in the `engine.impl` package. It should:
- Implement your updated `Item` interface
- Store the necessary data as fields
- Have a constructor that takes the required parameters

This should be a very small class — if it's more than 20 lines, you might be overcomplicating it.

### Step 5: Test it

Update your test code (or `LocationMapTest`) to:
1. Create a couple of `SimpleItem` instances
2. Add them to a location using `location.addItem()`
3. Verify they appear in `location.getItems()`
4. Create an actor, add an item to their inventory, verify with `hasItem()`

## Think About This

- You added maybe 1-2 methods to `Item`. That's not much. But it's enough to make "look", "take", and "inventory" possible. **The minimum viable interface is often smaller than you expect.**

- The `Item` interface will almost certainly grow as the game evolves. Maybe items will need a `use()` method, or a weight, or a type category. But we don't add those until we have code that needs them. This is the opposite of "design everything upfront" — we design what we need, when we need it.

- Notice that `SimpleItem` is probably almost identical to what a record would look like. That's fine. If we later decide items are pure data, converting to a record is easy. Starting with an interface keeps our options open.
