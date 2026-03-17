# The Factory Pattern

In class this week, you wired together the pieces of the game — creating objects, passing them to constructors, connecting subsystems. You might have noticed that some code exists purely to *create things*. This creation code knows about concrete classes, while the rest of the system works only with interfaces.

This is the **Factory pattern** — and you've already been using it.

**Reading time: 15 minutes.**

---

## 1. A Problem You Already Know

Think about how `MyMaceFactory` works in our project. It creates nine rooms, connects them, and returns a `Mace` object wrapping the starting room. The caller just says:

```java
Mace mace = new MyMaceFactory().createMyMace();
```

Without the factory, the caller would need to create each `Room`, call `connectNorth()` and `connectEast()` on each one, and remember which room is the starting room. That's a lot of knowledge that the caller doesn't need.

Now look at `LocationMapBuilder.build()` — it creates a `LocationMap` from the locations you've added. Who creates the `SimpleLocation` objects? Who wires up the neighbours? The builder does. The caller never sees any of that.

Both of these centralize the creation of complex objects so the rest of the system doesn't have to know how things are made. The Builder pattern (which you read about last week) is one way to do this. The Factory pattern is another — and they solve different problems.

---

## 2. Why Factories Matter

Imagine you're writing a method that stocks a shop with potions:

```java
void stockShop(Shop shop) {
    shop.addItem(new HealthPotion(50, "red", 10));
    shop.addItem(new StrengthPotion(30, "blue", 15));
    shop.addItem(new SpeedPotion(20, "green", 8));
}
```

This works. But notice:

- **The method knows every concrete class**: `HealthPotion`, `StrengthPotion`, `SpeedPotion`.
- **Changing potion types means changing this method**: Want "rare" potions? You have to rewrite it.
- **Every place that creates potions has the same problem**: If potions appear in shops, treasure chests, and quest rewards, all three places know about all concrete potion classes.

The Factory pattern solves this by putting creation behind a method — or better, behind an interface.

---

## 3. Static Factory Method — The Simplest Form

The simplest factory is a class with static methods that create objects for you:

```java
public class Potions {
    public static Item healing()  { return new HealthPotion(50, "red", 10); }
    public static Item strength() { return new StrengthPotion(30, "blue", 15); }
    public static Item speed()    { return new SpeedPotion(20, "green", 8); }
}
```

Now the shop code becomes:

```java
void stockShop(Shop shop) {
    shop.addItem(Potions.healing());
    shop.addItem(Potions.strength());
    shop.addItem(Potions.speed());
}
```

Better! Creation is centralized. If `HealthPotion` changes its constructor, you fix it in one place. The caller only knows it gets an `Item` back — it doesn't care which concrete class that is.

But there's a limitation: `Potions` is a concrete class. You can't swap it for a different potion system without changing the code that calls it.

---

## 4. Factory Interface — The Key Step

This is where interface-first design makes factories truly powerful. Instead of a concrete class with static methods, define a factory *interface*:

```java
public interface PotionFactory {
    Item createHealing();
    Item createStrength();
    Item createSpeed();
}
```

Now you can have multiple implementations:

```java
public class BasicPotionFactory implements PotionFactory {
    public Item createHealing()  { return new HealthPotion(50, "red", 10); }
    public Item createStrength() { return new StrengthPotion(30, "blue", 15); }
    public Item createSpeed()    { return new SpeedPotion(20, "green", 8); }
}

public class RarePotionFactory implements PotionFactory {
    public Item createHealing()  { return new HealthPotion(150, "golden", 30); }
    public Item createStrength() { return new StrengthPotion(100, "purple", 45); }
    public Item createSpeed()    { return new SpeedPotion(80, "silver", 25); }
}
```

The shop code now takes the factory as a parameter:

```java
void stockShop(Shop shop, PotionFactory factory) {
    shop.addItem(factory.createHealing());
    shop.addItem(factory.createStrength());
    shop.addItem(factory.createSpeed());
}
```

Pass `new BasicPotionFactory()` for a beginner shop. Pass `new RarePotionFactory()` for an endgame shop. **The shop method doesn't change at all.** It works with any factory that implements the interface.

This is the same principle we've applied everywhere in this course: design the interface from the caller's perspective, implement later.

---

## 5. Factory vs Builder — When to Use Which

You now know two creation patterns. They solve different problems:

| | Factory | Builder |
|---|---------|---------|
| **Problem it solves** | *Which* object to create | *How* to construct a complex object |
| **How you use it** | Call one method, get a finished object | Call multiple methods, then `build()` |
| **Example** | `factory.createHealing()` | `builder.name("...").value(50).build()` |
| **What it hides** | Which concrete class is created | The multi-step construction process |
| **Best when** | You have families of related objects | An object has many optional attributes |

They're not competitors — they solve different problems. And they work beautifully together.

---

## 6. Factory + Builder: A Powerful Combination

What happens when a factory uses a builder internally?

```java
public class RarePotionFactory implements PotionFactory {
    public Item createHealing() {
        return new ItemBuilder()
            .name("Rare Healing Potion")
            .description("A shimmering golden potion")
            .value(150)
            .weight(1)
            .healAmount(100)
            .build();
    }
}
```

The factory decides *what* to create — a rare healing potion with specific attributes. The builder handles *how* to construct it — step by step, with sensible defaults for attributes you don't specify.

This separation means:

- **Adding a new factory** is easy — just pick different attribute values
- **Adding a new attribute** to items is easy — update the builder
- **Neither change affects the other**

You'll practice this combination in the exercises.

---

## 7. Where You See This in the Real World

Factories are everywhere in professional software:

- **Java Collections**: `List.of(1, 2, 3)` is a factory method — you get a `List`, but you don't know (or care) which concrete class implements it.
- **JDBC**: `DriverManager.getConnection(url)` returns a `Connection`. The actual class depends on which database driver is loaded — MySQL, PostgreSQL, SQLite — but your code doesn't care.
- **GUI frameworks**: A button factory might return a Windows-style button, macOS-style button, or Linux-style button depending on the platform. The code that uses the button only knows the `Button` interface.
- **Game engines**: An `EnemyFactory` creates enemies appropriate for the current level — goblins in the forest, skeletons in the dungeon — without the level code knowing the difference.
- **Testing**: A `TestDataFactory` creates realistic test objects so tests don't need to know the concrete details of every class.

---

## 8. Summary

| Concept | What It Means |
|---------|--------------|
| **Factory pattern** | Centralize object creation behind a method or interface |
| **Static factory method** | Simplest form — a class with creation methods |
| **Factory interface** | The powerful form — creation strategy is swappable |
| **Factory + Builder** | Factory decides *what* to create, builder handles *how* |
| **Interface-first** | Design the factory interface from the caller's needs |

The Factory pattern is one of the most common patterns in professional software. Combined with the Builder pattern you learned last week, you now have two tools for managing how objects are created — while keeping the rest of your code clean and flexible.

---

## Further Reading

If you want to dig deeper:

- **Refactoring Guru — Factory Method**: https://refactoring.guru/design-patterns/factory-method — visual explanations with Java examples.
- **Refactoring Guru — Abstract Factory**: https://refactoring.guru/design-patterns/abstract-factory — the "family of related objects" variant.
- **"Effective Java" by Joshua Bloch** — Item 1 covers static factory methods as a best practice.
- **Java's own factories**: Look at `List.of()`, `Map.of()`, `Optional.of()`, and `Path.of()` in the Java standard library.
