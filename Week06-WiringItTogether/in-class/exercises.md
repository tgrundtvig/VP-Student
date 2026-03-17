# Exercises: Factory and Builder Patterns

These exercises build on each other — each one extends the code you wrote in the previous exercise. You never throw anything away; you keep building.

Start from scratch. No starter code needed.

**Total time: ~60 minutes**

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

## Exercise 4: Builder for Complex Items (~15 minutes)

### Goal

When items grow beyond a few fields, record constructors become painful. Use the Builder pattern to handle complex construction.

### The problem

Your items have evolved. They now need more attributes: **name, description, value, weight, damage, armor, durability**. A record with seven parameters is hard to read and easy to get wrong:

```java
// What is 5? What is 20? What is that 0?
new DetailedItem("Iron Sword", "A sturdy sword", 50, 5, 20, 0, 100);
```

### What to build

**Step 1** — Extend your `Item` interface with new methods:

```java
int weight();
int damage();
int armor();
int durability();
```

**Step 2** — Create an `ItemBuilder` class with a fluent API. The builder should:

- Store each attribute as a private field
- Have sensible defaults: `weight = 1`, `damage = 0`, `armor = 0`, `durability = 100`
- Return `this` from each setter method (enabling method chaining)
- Have a `build()` method that returns an `Item`

You will need a concrete class (not a record) to hold the built item, since records require all fields in the constructor.

**Step 3** — Build at least three items using the builder, each with a different combination of attributes:

```java
Item sword = new ItemBuilder()
    .name("Iron Sword")
    .description("A sturdy iron sword")
    .value(50)
    .weight(5)
    .damage(20)
    .durability(100)
    .build();

Item shield = new ItemBuilder()
    .name("Wooden Shield")
    .description("A basic wooden shield")
    .value(30)
    .armor(10)
    .build();  // uses defaults for weight, damage, durability

Item potion = new ItemBuilder()
    .name("Health Potion")
    .description("Restores 50 health")
    .value(25)
    .build();  // a potion has no damage, armor, etc.
```

Print each item with all its attributes.

### Verify

Each item prints with the correct attributes. Items where you didn't specify damage/armor/etc. show the default values.

### Think about

- Compare the seven-argument constructor with the builder version. Which one can you read without checking the parameter order?
- What would you need to change to add a new attribute (e.g., `rarity`)? How many files need updating?
- How does this compare to `LocationMapBuilder` from Week 05? What's similar? What's different?

---

## Exercise 5: Factory + Builder (~10 minutes)

### Goal

Combine both patterns: the factory decides *what* to create, the builder handles *how*.

### What to change

**Step 1** — Update your `TreasureFactory` implementations (from Exercise 3) to use `ItemBuilder` internally:

```java
public class BasicTreasureFactory implements TreasureFactory {
    public Item createWeapon() {
        return new ItemBuilder()
            .name("Iron Sword")
            .description("A sturdy iron sword")
            .value(50)
            .weight(5)
            .damage(20)
            .durability(100)
            .build();
    }
    // ... createCoin() and createPotion() similarly
}
```

**Step 2** — Do the same for `RareTreasureFactory`, with better attribute values (higher damage, better armor, etc.).

**Step 3** — Run your `fillChest` method from Exercise 3 with both factories. The output should now show detailed items with all their attributes.

### Verify

Both factories produce items through the builder. The `fillChest` method still works without any changes — it doesn't know or care that the factories now use a builder internally.

### Think about

- The factory decides *which* attributes to use. The builder handles *how* to construct the item. Neither needs to change when the other changes.
- To add a new item tier, you write a new factory class. The builder and `fillChest` stay the same.
- To add a new attribute to all items, you update the builder and the `Item` interface. The factories add one line each. `fillChest` doesn't change.
- **This is the power of separation of concerns.** Each piece of code has one job, and changes are localized.

---

## Bonus Exercises

If you finish early, try one or more of these extensions.

### Bonus A: Validation

Add validation to your `ItemBuilder`. The `build()` method should throw an `IllegalStateException` if:

- `name` is null or empty
- `value` is negative
- `durability` is zero or negative

Try building an item without a name. Does it fail with a clear error message?

### Bonus B: Builder Interface

Extract an `ItemBuilder` interface from your concrete builder class. Then create an alternative implementation — perhaps a `DebugItemBuilder` that prints each attribute as it's set (useful for troubleshooting). Your factories should work with the builder interface, not the concrete class.

### Bonus C: Parameterized Factory

Add a `createWeapon(String material)` method to your `TreasureFactory` interface. The factory uses the material parameter to look up appropriate damage, weight, and durability values. For example, `"wood"` gives low damage but high durability, while `"glass"` gives high damage but low durability.
