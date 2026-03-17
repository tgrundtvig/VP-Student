# Design Patterns: A Comprehensive Guide

You've already used several design patterns in this course — Factory, Builder, Command — even before knowing their names. This reading gives you the full picture: the 23 classic patterns from the Gang of Four book, plus a few modern ones that matter in practice.

This isn't something you need to memorize. It's a reference document. Read it through once to get the lay of the land, then come back to individual patterns when you need them.

**Estimated reading time: 90-120 minutes.**

---

## Table of Contents

1. [What Are Design Patterns?](#1-what-are-design-patterns)
2. [Creational Patterns](#2-creational-patterns) — Factory Method, Abstract Factory, Builder, Singleton, Prototype
3. [Structural Patterns](#3-structural-patterns) — Adapter, Decorator, Facade, Composite, Proxy, Bridge, Null Object
4. [Behavioral Patterns](#4-behavioral-patterns) — Strategy, Command, Observer, Iterator, Template Method, State, Chain of Responsibility, Visitor, Memento
5. [Architectural Patterns](#5-architectural-patterns) — MVC, Repository
6. [Dependency Injection](#6-dependency-injection)
7. [Other Patterns Worth Exploring](#7-other-patterns-worth-exploring) — Flyweight, Mediator, Interpreter, Object Pool
8. [Summary and Further Reading](#8-summary-and-further-reading)

---

## 1. What Are Design Patterns?

In 1994, four authors — Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides — published *Design Patterns: Elements of Reusable Object-Oriented Software*. The book catalogued 23 recurring solutions to common software design problems. The authors became known as the "Gang of Four" (GoF), and their book became one of the most influential programming books ever written.

The key insight wasn't that these patterns were new. Experienced programmers had been using them for years. The book gave them **names**. And names matter enormously. When a team agrees on what "Factory" or "Strategy" or "Observer" means, they can communicate complex design ideas in a single word. Instead of saying "we need a thing that creates objects behind an interface so we can swap out which concrete class gets created," you say "we need a factory." Everyone knows what you mean.

### Why Patterns Exist

Design patterns exist because the same problems keep showing up in different projects:

- **How do I create objects without the caller knowing which concrete class is used?** (Creational patterns)
- **How do I compose objects into larger structures without tight coupling?** (Structural patterns)
- **How do I define communication between objects without them knowing about each other directly?** (Behavioral patterns)

Each pattern is a proven solution to one of these recurring problems. They're not rules — they're tools. You pick the right tool for the problem at hand.

### Patterns and Interface-First Design

Many patterns achieve their power by separating concerns through interfaces. The Factory pattern works because the caller depends on an interface, not a concrete class. The Strategy pattern works because the algorithm is behind an interface that can be swapped. The Command pattern works because each command implements a common interface.

This isn't a coincidence. Interface-first design and design patterns share the same underlying principle: **depend on abstractions, not on concrete implementations**. When you design interfaces from the caller's perspective and defer implementation, you naturally arrive at patterns. You've already experienced this — the Command pattern emerged from wishful programming in Week 4, and the Builder pattern fell out of the LocationMap design in Week 5.

Not every pattern is about interfaces, though. Some patterns (like Memento or Prototype) are more about object mechanics than abstraction layers. We'll note the connection to interfaces where it naturally fits, and skip it where it doesn't.

### The Three Categories

The GoF organized their 23 patterns into three groups:

| Category | Purpose | Count |
|----------|---------|-------|
| **Creational** | How objects are created | 5 |
| **Structural** | How objects are composed into larger structures | 7 |
| **Behavioral** | How objects communicate and divide responsibilities | 11 |

We'll cover 21 of the 23 in detail, plus a few modern patterns (Null Object, Repository, Dependency Injection) that the original book didn't include. The remaining patterns get brief mentions at the end.

---

## 2. Creational Patterns

Creational patterns deal with the problem of object creation. The core tension: your code needs objects, but if every piece of code creates its own objects using `new`, you end up with tight coupling to concrete classes everywhere.

Creational patterns solve this by putting object creation behind methods or interfaces, so the caller says *what* it needs without knowing *how* it's made.

---

### 2.1 Factory Method

**The problem:** You need to create objects, but you don't want the calling code to know which concrete class to instantiate. Or you want subclasses to be able to change which objects get created.

**How it works:** Define a method that returns an interface type. The method's implementation decides which concrete class to create. The caller only sees the interface.

**Java example:**

```java
public interface Item {
    String name();
    int value();
}

public interface ItemFactory {
    Item createWeapon();
    Item createArmor();
}

public class BasicItemFactory implements ItemFactory {
    public Item createWeapon() { return new WoodenSword("Wooden Sword", 5); }
    public Item createArmor()  { return new LeatherArmor("Leather Armor", 8); }
}

public class EliteItemFactory implements ItemFactory {
    public Item createWeapon() { return new FlameSword("Flame Sword", 50); }
    public Item createArmor()  { return new DragonArmor("Dragon Armor", 80); }
}
```

**Where you've seen it:** `MyMaceFactory.createMyMace()` in our text adventure creates nine rooms and returns a `Mace`. The caller doesn't know about the internal room structure. Java's `List.of()` and `Map.of()` are factory methods — you get a `List`, but you don't know (or care) which concrete class implements it.

**Connection to interface-first design:** The factory method returns an interface type, not a concrete type. This means the caller depends only on the abstraction. You can swap out the factory without changing any code that uses the products.

---

### 2.2 Abstract Factory

**The problem:** You need to create *families* of related objects that must be used together. A "basic" sword should come with "basic" armor and "basic" potions — not mixed with "elite" items.

**How it works:** Define a factory interface with creation methods for each product in the family. Each concrete factory creates a consistent set of products.

**Java example:**

```java
public interface TreasureFactory {
    TreasureChest createChest();
    Item createCoin();
    Item createGem();
}

public class BasicTreasureFactory implements TreasureFactory {
    public TreasureChest createChest() { return new WoodenChest(); }
    public Item createCoin()           { return new CopperCoin(); }
    public Item createGem()            { return new Quartz(); }
}

public class RareTreasureFactory implements TreasureFactory {
    public TreasureChest createChest() { return new GoldChest(); }
    public Item createCoin()           { return new GoldCoin(); }
    public Item createGem()            { return new Diamond(); }
}
```

**Where you've seen it:** The `BasicTreasureFactory` vs `RareTreasureFactory` from this week's exercises is exactly this pattern. In real software, GUI toolkits use abstract factories to create platform-consistent widgets — a Windows factory creates Windows-style buttons, scrollbars, and dialogs; a macOS factory creates macOS-style ones.

**How it differs from Factory Method:** Factory Method is about one creation method. Abstract Factory is about a *family* of creation methods that produce objects designed to work together. The Abstract Factory ensures consistency across the family.

---

### 2.3 Builder

**The problem:** You need to construct a complex object step by step, and the construction process should be separate from the object's representation. A constructor with many parameters becomes unreadable, and the object may need post-processing after all parts are added.

**How it works:** Create a builder with methods for adding parts one at a time, and a `build()` method that assembles and returns the finished product.

**Java example:**

```java
public interface LocationMapBuilder {
    void addLocation(int x, int y, int z, String name, String description);
    LocationMap build();
}

// Usage:
LocationMapBuilder builder = new SimpleLocationMapBuilder();
builder.addLocation(0, 0, 0, "Tavern", "A dimly lit tavern.");
builder.addLocation(1, 0, 0, "Market", "A noisy market.");
builder.addLocation(0, 1, 0, "Dock", "A busy dock.");
LocationMap map = builder.build(); // Wires up all neighbours automatically
```

**Where you've seen it:** `SimpleLocationMapBuilder` in our game accumulates locations, then `build()` computes neighbours and wires everything up. `TreasureChestBuilder` from the exercises works the same way — add items, set traps, call `build()`. Java's `StringBuilder`, `HttpRequest.newBuilder()`, and `Stream.builder()` all use this pattern.

**Why not a constructor?** Builders shine when construction is multi-step, when the product needs post-processing (like wiring neighbours), or when you want to hide internals from the caller. The Builder separates *accumulating configuration* from *constructing the result*.

---

### 2.4 Singleton

**The problem:** You need exactly one instance of a class, and you need a global point of access to it.

**How it works:** Make the constructor private. Provide a static method that returns the single instance, creating it on first access.

**Java example:**

```java
public class GameConfig {
    private static GameConfig instance;

    private final Map<String, String> settings = new HashMap<>();

    private GameConfig() {
        // Load settings from file
    }

    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    public String getSetting(String key) {
        return settings.get(key);
    }
}
```

**Where you see it in real software:** `Runtime.getRuntime()` in Java. Logging frameworks often use singletons. Database connection pools sometimes use this pattern.

**An honest warning:** Singleton is one of the most overused and criticized patterns in software. The problems are real:

- **Global state**: Any code anywhere can access and modify the singleton. This makes it hard to reason about what's happening.
- **Hidden dependencies**: When a class uses `GameConfig.getInstance()` internally, there's no way to see that dependency from the outside. Compare this to passing `GameConfig` through the constructor — that makes the dependency explicit.
- **Testing difficulty**: You can't easily substitute a test version. With constructor injection, you'd pass a mock. With a singleton, you're stuck with the real thing.
- **Thread safety**: The simple version above isn't thread-safe. Making it thread-safe adds complexity.

In most modern Java code, the problems Singleton solves are better handled by dependency injection (which we'll cover in section 6). Instead of having a class reach out to grab a global instance, you pass the instance through the constructor. This makes dependencies visible and testable.

Use Singleton sparingly, and only when you have a genuine need for exactly one instance with global access — not just because it's convenient.

---

### 2.5 Prototype

**The problem:** You need to create objects that are copies of existing, preconfigured objects. Creating them from scratch is either expensive or requires knowledge you don't want to spread around.

**How it works:** Define a `clone()` method that returns a copy of the object. Callers create new objects by cloning a prototype instead of calling a constructor.

**Java example:**

```java
public interface EnemyPrototype {
    EnemyPrototype clone();
    String name();
    int health();
}

public class Goblin implements EnemyPrototype {
    private final String name;
    private final int health;

    public Goblin(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public EnemyPrototype clone() {
        return new Goblin(this.name, this.health);
    }

    public String name()   { return name; }
    public int health()    { return health; }
}

// Usage: clone a preconfigured template
EnemyPrototype goblinTemplate = new Goblin("Forest Goblin", 30);
EnemyPrototype goblin1 = goblinTemplate.clone();
EnemyPrototype goblin2 = goblinTemplate.clone();
```

**Where you see it:** Java's `Cloneable` interface and `Object.clone()` are the language-level version of this pattern. In games, you often have a "template" for each enemy type, and you clone it to spawn new instances. In spreadsheet applications, copying a cell with its formatting uses the prototype concept.

**When to use it:** Prototype is useful when object creation is expensive (e.g., loading configuration from a file), or when you want to create variations of a preconfigured object without knowing its exact class.

---

### Creational Patterns Summary

| Pattern | Problem | Solution | In Our Project |
|---------|---------|----------|----------------|
| **Factory Method** | Who decides which class to create? | A method returns an interface type | `MyMaceFactory`, `TreasureFactory` |
| **Abstract Factory** | How to create families of related objects? | A factory interface with multiple creation methods | `BasicTreasureFactory` vs `RareTreasureFactory` |
| **Builder** | How to construct complex objects step by step? | Accumulate parts, then `build()` | `LocationMapBuilder`, `TreasureChestBuilder` |
| **Singleton** | How to ensure exactly one instance? | Private constructor + static accessor | Use sparingly; prefer DI |
| **Prototype** | How to create copies of preconfigured objects? | A `clone()` method on the object | Enemy templates, cell copying |

---

## 3. Structural Patterns

Structural patterns deal with how objects and classes are composed into larger structures. The core question: how do you combine simple objects into more complex ones without creating rigid, tightly-coupled hierarchies?

---

### 3.1 Adapter

**The problem:** You have two interfaces that don't match, but you need them to work together. One part of your system expects interface A, another part provides interface B. You can't (or don't want to) change either one.

**How it works:** Create a wrapper class that implements the target interface and delegates to the adaptee. The adapter translates between the two interfaces.

**Java example:**

```java
// The interface the game engine expects
public interface TextAppUser {
    void println(String text);
    String readLine(String prompt);
}

// The interface we already have
public interface TextUser {
    void put(String text);
    String get();
}

// Adapter: makes TextUser look like TextAppUser
public class TextAppUserImpl implements TextAppUser {
    private final TextUser user;

    public TextAppUserImpl(TextUser user) {
        this.user = user;
    }

    public void println(String text) {
        user.put(text);
        user.put(System.lineSeparator());
    }

    public String readLine(String prompt) {
        user.put(prompt);
        return user.get();
    }
}
```

**Where you've seen it:** `TextAppUserImpl` in our project is exactly an adapter. `TextUser` has low-level `put()` and `get()` methods. `TextAppUser` has higher-level `println()` and `readLine()` methods. The adapter bridges the gap. In the Java standard library, `Arrays.asList()` adapts an array to the `List` interface. `InputStreamReader` adapts a byte stream (`InputStream`) to a character stream (`Reader`).

**Connection to interface-first design:** The adapter pattern lets you design the interface you *wish* you had (TextAppUser), and then adapt whatever you *actually* have (TextUser) to fit it. This is wishful programming applied to integration.

---

### 3.2 Decorator

**The problem:** You want to add responsibilities to an object dynamically, without modifying its class or using inheritance. You might want to combine multiple enhancements in different ways.

**How it works:** Create a wrapper that implements the same interface as the object it wraps. The wrapper delegates to the wrapped object, adding behavior before or after.

**Java example (real-world):**

```java
// Java I/O is the classic example of the Decorator pattern
InputStream raw      = new FileInputStream("data.txt");          // Raw bytes
InputStream buffered = new BufferedInputStream(raw);              // Adds buffering
Reader      reader   = new InputStreamReader(buffered, "UTF-8");  // Adds character decoding
BufferedReader lines = new BufferedReader(reader);                 // Adds line reading

String line = lines.readLine();
```

Each layer wraps the previous one, adding one capability. `BufferedInputStream` doesn't know or care whether it's wrapping a `FileInputStream`, a network socket, or another decorator. It just adds buffering to whatever `InputStream` it wraps.

**A game-oriented example:**

```java
public interface Item {
    String name();
    int value();
    String description();
}

public class EnchantedItem implements Item {
    private final Item base;
    private final String enchantment;

    public EnchantedItem(Item base, String enchantment) {
        this.base = base;
        this.enchantment = enchantment;
    }

    public String name()        { return base.name() + " of " + enchantment; }
    public int value()          { return base.value() * 2; }
    public String description() { return base.description() + " It glows with " + enchantment + "."; }
}

// Usage:
Item sword = new BasicSword("Iron Sword", 20, "A sturdy iron sword.");
Item enchanted = new EnchantedItem(sword, "Fire");
// enchanted.name() -> "Iron Sword of Fire"
// enchanted.value() -> 40
```

**Where you see it:** Java I/O streams are the textbook example. Servlet filters in web applications wrap the request/response, adding logging, authentication, or compression. In games, item enchantments or buff effects are natural decorators.

**How it differs from Adapter:** An adapter changes the interface (TextUser becomes TextAppUser). A decorator keeps the same interface but adds behavior. Both use wrapping, but for different purposes.

---

### 3.3 Facade

**The problem:** A subsystem has many classes with complex interactions. Clients need to interact with the subsystem, but they shouldn't need to know about all the internal complexity.

**How it works:** Create a single class that provides a simplified interface to the subsystem. The facade delegates to the appropriate internal classes.

**Java example:**

```java
// Behind the scenes: many complex classes
public class Inventory { /* manages items */ }
public class QuestLog { /* tracks quests */ }
public class PlayerStats { /* health, XP, level */ }
public class AchievementTracker { /* unlocked achievements */ }
public class SaveFileWriter { /* binary serialization */ }

// Facade: one simple method for clients
public class SaveGameFacade {
    private final Inventory inventory;
    private final QuestLog quests;
    private final PlayerStats stats;
    private final AchievementTracker achievements;
    private final SaveFileWriter writer;

    public SaveGameFacade(Inventory inventory, QuestLog quests,
                          PlayerStats stats, AchievementTracker achievements,
                          SaveFileWriter writer) {
        this.inventory = inventory;
        this.quests = quests;
        this.stats = stats;
        this.achievements = achievements;
        this.writer = writer;
    }

    public void saveGame(String filename) {
        writer.beginFile(filename);
        writer.writeSection("inventory", inventory.serialize());
        writer.writeSection("quests", quests.serialize());
        writer.writeSection("stats", stats.serialize());
        writer.writeSection("achievements", achievements.serialize());
        writer.finalize();
    }
}
```

The caller just says `facade.saveGame("slot1.sav")`. It doesn't need to know about inventory serialization formats, quest state tracking, or binary file writing.

**Where you see it:** JDBC is a facade over the complexity of database communication. `javax.faces` (JSF) provides a facade over the request-response cycle. Any time you call a method like `sendEmail()` and it handles SMTP connections, authentication, encoding, and retries — that's a facade.

**When to use it:** Facade is useful when you have a complex subsystem that most clients don't need to understand in detail. It doesn't hide the internal classes — they're still accessible for advanced use — it just provides a simpler default entry point.

---

### 3.4 Composite

**The problem:** You have objects that naturally form tree structures — parts that contain other parts. You want to treat individual objects and groups of objects uniformly.

**How it works:** Define a common interface for both leaf nodes and composite nodes. Composites hold a collection of children (which can be either leaves or other composites) and delegate operations to them.

**Java example (file system):**

```java
public interface FileSystemNode {
    String name();
    long sizeInBytes();
    void print(String indent);
}

public class File implements FileSystemNode {
    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String name()        { return name; }
    public long sizeInBytes()   { return size; }
    public void print(String indent) {
        System.out.println(indent + name + " (" + size + " bytes)");
    }
}

public class Folder implements FileSystemNode {
    private final String name;
    private final List<FileSystemNode> children = new ArrayList<>();

    public Folder(String name) { this.name = name; }

    public void add(FileSystemNode node) { children.add(node); }

    public String name() { return name; }

    public long sizeInBytes() {
        return children.stream().mapToLong(FileSystemNode::sizeInBytes).sum();
    }

    public void print(String indent) {
        System.out.println(indent + name + "/");
        for (FileSystemNode child : children) {
            child.print(indent + "  ");
        }
    }
}
```

Usage:

```java
Folder root = new Folder("project");
root.add(new File("README.md", 1024));
Folder src = new Folder("src");
src.add(new File("Main.java", 2048));
src.add(new File("Game.java", 4096));
root.add(src);

root.print("");       // Prints the entire tree
root.sizeInBytes();   // Returns 7168 (sum of all files)
```

**Where you see it:** File systems are the canonical example. GUI component trees (a panel contains buttons and other panels). HTML/XML document trees. Organization charts. Menu systems where a menu item can be either a command or a submenu.

**Why Composite pairs naturally with Visitor:** Once you have a tree structure, you often want to perform different operations on it — calculate total size, search for a file, generate a report. The Visitor pattern (covered in section 4.8) provides a clean way to add new operations to a Composite tree without modifying the node classes. We'll revisit this connection there.

---

### 3.5 Proxy

**The problem:** You need to control access to an object — perhaps to delay its creation until it's actually needed, to check permissions before allowing access, or to add logging.

**How it works:** Create a class that implements the same interface as the real object. The proxy controls when and how the real object is accessed.

**Java example (lazy-loading):**

```java
public interface Image {
    void display();
    int width();
    int height();
}

public class HighResImage implements Image {
    private final byte[] data; // Expensive to load

    public HighResImage(String filename) {
        // Imagine this takes 2 seconds and 50MB of memory
        this.data = loadFromDisk(filename);
    }

    public void display() { /* render the image */ }
    public int width()    { return 1920; }
    public int height()   { return 1080; }
}

public class LazyImage implements Image {
    private final String filename;
    private HighResImage real; // Created only when needed

    public LazyImage(String filename) {
        this.filename = filename;
    }

    private HighResImage ensureLoaded() {
        if (real == null) {
            real = new HighResImage(filename);
        }
        return real;
    }

    public void display() { ensureLoaded().display(); }
    public int width()    { return ensureLoaded().width(); }
    public int height()   { return ensureLoaded().height(); }
}
```

**Where you see it:** Hibernate (an ORM framework) uses proxies to lazy-load database entities — the object looks like it's loaded, but the data is only fetched when you access a field. Java's `java.lang.reflect.Proxy` creates dynamic proxies at runtime. Virtual proxies for images in web browsers: a placeholder is shown while the real image loads.

**Proxy variations:**
- **Lazy proxy**: Delays creation of the real object (example above)
- **Protection proxy**: Checks permissions before delegating
- **Logging proxy**: Records method calls for debugging
- **Caching proxy**: Stores results to avoid redundant computation

---

### 3.6 Bridge

**The problem:** You have two independent dimensions of variation, and you don't want a combinatorial explosion of classes. For example, shapes (circle, square, triangle) and rendering methods (SVG, Canvas, PDF) — without Bridge, you'd need CircleSVG, CircleCanvas, CirclePDF, SquareSVG, SquareCanvas, SquarePDF...

**How it works:** Separate the two hierarchies into independent interfaces. One side holds a reference to the other. They vary independently.

**Java example:**

```java
// Rendering dimension
public interface Renderer {
    void renderCircle(double x, double y, double radius);
    void renderRectangle(double x, double y, double width, double height);
}

public class SVGRenderer implements Renderer {
    public void renderCircle(double x, double y, double radius) {
        System.out.println("<circle cx=\"" + x + "\" cy=\"" + y + "\" r=\"" + radius + "\"/>");
    }
    public void renderRectangle(double x, double y, double w, double h) {
        System.out.println("<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + w + "\" height=\"" + h + "\"/>");
    }
}

// Shape dimension
public abstract class Shape {
    protected final Renderer renderer;

    public Shape(Renderer renderer) { this.renderer = renderer; }

    public abstract void draw();
}

public class Circle extends Shape {
    private final double x, y, radius;

    public Circle(Renderer renderer, double x, double y, double radius) {
        super(renderer);
        this.x = x; this.y = y; this.radius = radius;
    }

    public void draw() { renderer.renderCircle(x, y, radius); }
}
```

**Where you see it:** JDBC is a bridge between your application code and database drivers — `Connection`, `Statement`, `ResultSet` are the abstraction side; MySQL/PostgreSQL/SQLite drivers are the implementation side. AWT in Java bridged platform-independent GUI code with platform-specific rendering.

**When to use it:** Bridge is useful when you have two or more independent dimensions that can vary. If you find yourself creating classes like `FastRedCar`, `FastBlueCar`, `SlowRedCar`, `SlowBlueCar` — you probably need a bridge separating "speed" from "color."

---

### 3.7 Null Object

**The problem:** Your code is littered with null checks. Every time you call a method that might return `null`, you need an `if` check. This clutters the code and is easy to forget, leading to `NullPointerException`s.

**How it works:** Instead of returning `null`, return a special object that implements the expected interface but does nothing (or returns sensible defaults). The caller doesn't need to check — it just calls methods normally.

**Java example (from our game):**

Right now, `Location.getNeighbour(Direction direction)` returns `null` when there's no neighbour in that direction. Every caller must check:

```java
// Current approach — null check required
Location next = current.getNeighbour(Direction.NORTH);
if (next == null) {
    user.println("You can't go that way.");
} else {
    actor.moveTo(next);
}
```

With a Null Object:

```java
public class NullLocation implements Location {
    private final Direction direction;

    public NullLocation(Direction direction) {
        this.direction = direction;
    }

    public String name()        { return "Nowhere"; }
    public String description() { return "There is no exit to the " + direction.name().toLowerCase() + "."; }
    public void addActor(Actor actor)    { /* Do nothing */ }
    public boolean removeActor(Actor actor) { return false; }
    public void addItem(Item item)       { /* Do nothing */ }
    public boolean removeItem(Item item) { return false; }
    public Set<Actor> getActors()        { return Set.of(); }
    public Set<Item> getItems()          { return Set.of(); }
    public Location getNeighbour(Direction d) { return this; } // Going nowhere leads nowhere
    public boolean isNowhere()           { return true; }
}
```

Now the movement code can be cleaner:

```java
Location next = current.getNeighbour(Direction.NORTH);
if (next.isNowhere()) {
    user.println(next.description()); // "There is no exit to the north."
} else {
    actor.moveTo(next);
}
```

Or even cleaner with a well-designed interface — the location itself handles the response, and `null` never appears.

**Where you see it:** Java's `Optional.empty()` is a form of null object. Collections.emptyList(), emptyMap(), emptySet() are null objects for collections. The "null" logger in logging frameworks silently discards all messages. In many games, an "empty tile" or "void location" is a null object for the map.

**Why it matters:** Null checks are a form of coupling — every caller must know that the method might return null and handle it. A null object moves that knowledge into one place: the null object itself.

---

### Structural Patterns Summary

| Pattern | Problem | Solution | Example |
|---------|---------|----------|---------|
| **Adapter** | Two interfaces don't match | Wrapper translates between them | `TextAppUserImpl` wraps `TextUser` |
| **Decorator** | Add behavior without modifying a class | Wrapper adds behavior, delegates the rest | Java I/O streams, item enchantments |
| **Facade** | Subsystem is too complex for most callers | Simplified interface delegates internally | `SaveGameFacade`, JDBC |
| **Composite** | Tree structures need uniform treatment | Common interface for leaves and composites | File systems, GUI component trees |
| **Proxy** | Control access to an object | Same interface, controls when/how to delegate | Lazy loading, access control |
| **Bridge** | Two dimensions vary independently | Separate hierarchies linked by reference | Shape/Renderer, JDBC/Drivers |
| **Null Object** | Null checks clutter calling code | Object that implements the interface but does nothing | `NullLocation`, `Optional.empty()` |

---

## 4. Behavioral Patterns

Behavioral patterns deal with how objects communicate and how responsibilities are distributed among them. The core question: which object does what, and how do they coordinate?

---

### 4.1 Strategy

**The problem:** You have an algorithm that varies. Different situations call for different algorithms, but the code that uses the algorithm shouldn't change.

**How it works:** Define an interface for the algorithm. Create different implementations. The client holds a reference to the interface and can swap implementations.

**Java example:**

```java
public interface ScoringStrategy {
    int calculateScore(Player player);
}

public class TimeBonusScoring implements ScoringStrategy {
    public int calculateScore(Player player) {
        int base = player.getCorrectAnswers() * 100;
        int timeBonus = Math.max(0, 300 - player.getTotalTimeSeconds());
        return base + timeBonus;
    }
}

public class StreakScoring implements ScoringStrategy {
    public int calculateScore(Player player) {
        int score = 0;
        int streak = 0;
        for (boolean correct : player.getAnswers()) {
            if (correct) {
                streak++;
                score += 100 * streak; // Streak multiplier
            } else {
                streak = 0;
            }
        }
        return score;
    }
}

// Usage: the quiz doesn't know which strategy is used
public class Quiz {
    private final ScoringStrategy scoring;

    public Quiz(ScoringStrategy scoring) {
        this.scoring = scoring;
    }

    public int finalScore(Player player) {
        return scoring.calculateScore(player);
    }
}
```

**Where you've seen it:** `ScoringStrategy` in the quiz-app example project uses this pattern. `Comparator` in Java is a strategy — `list.sort(Comparator.comparing(Student::name))` swaps the sorting algorithm without changing the sort method. Thread pool configurations, compression algorithms, and payment processing systems all use strategies.

**Connection to interface-first design:** Strategy is perhaps the purest expression of programming to interfaces. The algorithm is behind an interface, and the client depends only on that interface. Swap the implementation, and the client's behavior changes without touching its code.

---

### 4.2 Command

**The problem:** You have actions that need to be dispatched, queued, undone, or logged. If-else chains for dispatching are rigid and violate the Open-Closed Principle.

**How it works:** Encapsulate each action as an object that implements a common interface. An invoker looks up and executes commands without knowing what they do.

**Java example:**

```java
public interface Command {
    String keyword();
    void execute(List<String> commandWords);
}

public class MoveCommand implements Command {
    private final Actor actor;
    private final LocationMap map;

    public MoveCommand(Actor actor, LocationMap map) {
        this.actor = actor;
        this.map = map;
    }

    public String keyword() { return "move"; }

    public void execute(List<String> commandWords) {
        if (commandWords.size() < 2) {
            System.out.println("Move where?");
            return;
        }
        Direction dir = Direction.valueOf(commandWords.get(1).toUpperCase());
        map.move(actor, dir);
    }
}
```

**Where you've seen it:** `Command` and `CommandRegistry` in our game. `ReferenceCommandRegistry` maps keywords to `Command` objects. The game loop reads input, looks up the command, and calls `execute()`. In the real world: GUI button handlers, undo/redo stacks, macro recording, task queues, and URL routing in web frameworks all use the Command pattern.

**Connection to interface-first design:** The Command interface was born from wishful programming — you wrote the game loop first, wishing for a way to look up and execute commands by name. The interface emerged from the caller's needs, and each command implementation was trivial.

---

### 4.3 Observer

**The problem:** When one object changes state, other objects need to react — but you don't want to hard-code which objects are notified. The source shouldn't need to know about every listener.

**How it works:** The *subject* (the thing being watched) maintains a list of *observers* (the things that want to know). When the subject changes, it notifies all observers. Observers register and unregister themselves.

**Java example:**

```java
public interface GameEventListener {
    void onEvent(GameEvent event);
}

public record GameEvent(String type, String description) {}

public class EventBus {
    private final Map<String, List<GameEventListener>> listeners = new HashMap<>();

    public void subscribe(String eventType, GameEventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void publish(GameEvent event) {
        List<GameEventListener> subs = listeners.getOrDefault(event.type(), List.of());
        for (GameEventListener listener : subs) {
            listener.onEvent(event);
        }
    }
}

// Usage:
EventBus bus = new EventBus();
bus.subscribe("item_picked_up", event -> System.out.println("Log: " + event.description()));
bus.subscribe("item_picked_up", event -> checkAchievement(event));
bus.publish(new GameEvent("item_picked_up", "Player picked up the Golden Key"));
```

**Where you see it:** Swing's `ActionListener`, JavaFX's event system, JavaScript's `addEventListener()`, and Android's `BroadcastReceiver` all use Observer. In games, achievement systems, sound effects, and UI updates are often driven by event observation. Any "publish/subscribe" or "event bus" system is Observer.

**When to use it:** Observer is useful when a change in one object should trigger updates in others, and you don't know in advance how many others, or which ones. It decouples the source of the event from the consumers.

---

### 4.4 Iterator

**The problem:** You have a collection of objects, and you need to traverse them without exposing the collection's internal structure. The caller shouldn't need to know if it's an array, a linked list, a tree, or a database query.

**How it works:** Define a standard interface for traversal. The collection provides an iterator that knows how to walk through its elements. The caller uses the iterator, not the collection's internals.

**Java example:**

```java
// Java's Iterator interface (built-in)
public interface Iterator<E> {
    boolean hasNext();
    E next();
}

// Any class that implements Iterable can be used in a for-each loop
public class LocationMap implements Iterable<Location> {
    private final Map<LocationCoordinate, Location> locations;

    public Iterator<Location> iterator() {
        return locations.values().iterator();
    }
}

// Usage — the caller doesn't know the internal structure
for (Location loc : locationMap) {
    System.out.println(loc.name());
}
```

**Where you've seen it:** Every time you write a `for-each` loop in Java, you're using the Iterator pattern. `List`, `Set`, `Map.values()`, `Stream` — they all provide iterators. In our game, iterating over actors, items, and locations uses this pattern implicitly.

**Why it matters:** Iterator decouples the traversal algorithm from the data structure. You could switch from an `ArrayList` to a `LinkedList` to a `TreeSet` internally, and the for-each loop wouldn't change. The caller depends on the `Iterable`/`Iterator` interface, not on the specific collection type.

---

### 4.5 Template Method

**The problem:** You have an algorithm with a fixed structure, but some steps need to vary between implementations. You want to define the skeleton once and let subclasses fill in the details.

**How it works:** Define the algorithm's skeleton in a base class method. Some steps are concrete (shared by all); others are abstract (overridden by subclasses). The base class controls the flow; the subclasses provide the details.

**Java example:**

```java
public class SimpleGameLoop implements GameLoop {
    @Override
    public void run(Game game) {
        game.initialize();                              // Step 1: Set up
        while (!game.isGameOver()) {                    // Step 2: Main loop
            int turn = nextTurn();
            game.onTurnStart(turn);                     // Hook: start of turn
            List<Actor> actors = game.getTurnSortedActiveActors();
            for (Actor actor : actors) {
                actor.takeTurn();                        // Step 3: Each actor acts
            }
            game.onTurnEnd(turn);                       // Hook: end of turn
        }
    }
}
```

The structure is fixed: initialize, loop (onTurnStart, each actor takes a turn, onTurnEnd), check game over. But the `Game` implementation decides what happens at each step. `onTurnStart()` might display the turn number. `onTurnEnd()` might check for defeated actors. `isGameOver()` might check if the boss is dead.

**Where you've seen it:** `SimpleGameLoop.run()` in our game is a Template Method. The loop structure never changes; the `Game` implementation fills in the blanks. In the Java standard library, `AbstractList` provides template methods that concrete subclasses override. JUnit's test lifecycle (`@BeforeEach`, `@Test`, `@AfterEach`) follows the same structure.

**Template Method vs Strategy:** Both let you vary behavior. Template Method uses inheritance (subclass overrides steps). Strategy uses composition (inject a different strategy object). Strategy is generally more flexible because you can swap strategies at runtime without changing the class hierarchy.

---

### 4.6 State

**The problem:** An object's behavior changes depending on its internal state, and you're writing growing if-else or switch statements like `if (state == EXPLORING) ... else if (state == COMBAT) ... else if (state == DIALOGUE) ...`.

**How it works:** Create an interface for the state. Each concrete state implements the interface with behavior appropriate for that state. The context object holds a reference to the current state and delegates to it.

**Java example:**

```java
public interface GameState {
    void handleInput(GameContext context, String input);
    String getPrompt();
}

public class ExploringState implements GameState {
    public void handleInput(GameContext context, String input) {
        if ("fight".equalsIgnoreCase(input)) {
            context.setState(new CombatState(context.getCurrentEnemy()));
        } else if ("talk".equalsIgnoreCase(input)) {
            context.setState(new DialogueState(context.getCurrentNPC()));
        } else {
            // Handle movement, look, inventory, etc.
        }
    }

    public String getPrompt() { return "What do you do? > "; }
}

public class CombatState implements GameState {
    private final Actor enemy;

    public CombatState(Actor enemy) { this.enemy = enemy; }

    public void handleInput(GameContext context, String input) {
        if ("attack".equalsIgnoreCase(input)) {
            // Handle attack logic
        } else if ("flee".equalsIgnoreCase(input)) {
            context.setState(new ExploringState());
        }
    }

    public String getPrompt() { return "Combat! [attack/defend/flee] > "; }
}
```

**Where you see it:** TCP connection states (LISTEN, SYN_SENT, ESTABLISHED, CLOSE_WAIT). Vending machine states (idle, coin inserted, dispensing). Document workflow (draft, review, published). In games, player states (exploring, combat, dialogue, inventory, paused) are a natural fit.

**Why it beats switch statements:** With a switch, adding a new state means modifying the switch — and every place that switches on state. With the State pattern, adding a new state means adding a new class. Existing states don't change. This is the Open-Closed Principle again.

---

### 4.7 Chain of Responsibility

**The problem:** A request needs to be handled, but you don't know in advance which object should handle it. Multiple objects might be able to handle it, and you want to avoid hard-coding the decision.

**How it works:** Create a chain of handler objects. Each handler either processes the request or passes it to the next handler in the chain. The client sends the request to the first handler and doesn't know which one ultimately handles it.

**Java example:**

```java
public interface RequestHandler {
    void handle(Request request);
}

public abstract class BaseHandler implements RequestHandler {
    private RequestHandler next;

    public BaseHandler setNext(RequestHandler next) {
        this.next = next;
        return this;
    }

    protected void passToNext(Request request) {
        if (next != null) {
            next.handle(request);
        }
    }
}

public class AuthenticationHandler extends BaseHandler {
    public void handle(Request request) {
        if (!request.hasValidToken()) {
            request.setResponse(401, "Unauthorized");
            return; // Stop the chain
        }
        passToNext(request); // Authenticated — pass to next handler
    }
}

public class LoggingHandler extends BaseHandler {
    public void handle(Request request) {
        System.out.println("Request: " + request.getPath());
        passToNext(request); // Always pass through
    }
}

public class RouteHandler extends BaseHandler {
    public void handle(Request request) {
        // Actually handle the request
        request.setResponse(200, "OK");
    }
}

// Wiring the chain:
RequestHandler chain = new LoggingHandler();
chain.setNext(new AuthenticationHandler());
// ... authentication's next is set to RouteHandler
```

**Where you see it:** Servlet filters in Java web applications form a chain — each filter can modify the request/response and pass it to the next. Middleware pipelines in frameworks like Express.js or Spring work the same way. Exception handling in languages that support it is a chain — the exception propagates up the call stack until a catch block handles it.

**When to use it:** Chain of Responsibility is useful when multiple objects can handle a request, and you want the flexibility to reorder, add, or remove handlers without changing the client code.

---

### 4.8 Visitor

**The problem:** You have a structure of objects (often a Composite tree), and you need to perform different operations on the elements. But you don't want to add a new method to every element class each time you think of a new operation.

**How it works:** Define a Visitor interface with a `visit` method for each element type. Elements have an `accept(Visitor)` method that calls the appropriate `visit` method. New operations are new Visitor implementations — no changes to the element classes.

**Java example (paired with the file system Composite from section 3.4):**

```java
public interface FileSystemVisitor {
    void visitFile(File file);
    void visitFolder(Folder folder);
}

// The nodes accept visitors
public class File implements FileSystemNode {
    // ... fields and methods from before ...

    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }
}

public class Folder implements FileSystemNode {
    // ... fields and methods from before ...

    public void accept(FileSystemVisitor visitor) {
        visitor.visitFolder(this);
        for (FileSystemNode child : children) {
            child.accept(visitor);
        }
    }
}

// Operation 1: Count files
public class FileCounter implements FileSystemVisitor {
    private int count = 0;

    public void visitFile(File file)     { count++; }
    public void visitFolder(Folder folder) { /* folders are visited by recursion in accept */ }

    public int getCount() { return count; }
}

// Operation 2: Find large files
public class LargeFileFinder implements FileSystemVisitor {
    private final long threshold;
    private final List<String> results = new ArrayList<>();

    public LargeFileFinder(long threshold) { this.threshold = threshold; }

    public void visitFile(File file) {
        if (file.sizeInBytes() > threshold) {
            results.add(file.name());
        }
    }
    public void visitFolder(Folder folder) { /* nothing to do here */ }

    public List<String> getResults() { return results; }
}
```

Usage:

```java
FileCounter counter = new FileCounter();
root.accept(counter);
System.out.println("Total files: " + counter.getCount());

LargeFileFinder finder = new LargeFileFinder(10_000);
root.accept(finder);
System.out.println("Large files: " + finder.getResults());
```

**Why Composite + Visitor are a natural pair:** Composite gives you the tree structure. Visitor gives you a way to add operations to that tree without modifying the node classes. Need a new operation? Write a new Visitor. The tree structure doesn't change. This is why these two patterns are often discussed together.

**Where you see it:** Compilers use Visitor to traverse Abstract Syntax Trees (ASTs) — one visitor for type checking, another for code generation, another for optimization. XML/JSON processing tools use visitors to walk document trees. Static analysis tools visit code structures.

**Trade-off:** Visitor makes it easy to add new operations but hard to add new element types (because every visitor must be updated). Composite without Visitor makes it easy to add new element types but hard to add new operations. Choose based on which dimension changes more often.

---

### 4.9 Memento

**The problem:** You need to save and restore an object's state (e.g., undo, save/load game) without exposing its internal structure.

**How it works:** The object (the "originator") creates a memento containing a snapshot of its state. The memento is opaque — the code that stores it can't peek inside. Later, the originator can restore itself from the memento.

**Java example:**

```java
// The memento — an opaque snapshot
public record GameSnapshot(
    String playerLocation,
    List<String> inventoryItems,
    int health,
    int score,
    int turnNumber
) {}

// The originator — creates and restores from snapshots
public class GameSession {
    private Location currentLocation;
    private List<Item> inventory;
    private int health;
    private int score;
    private int turn;

    public GameSnapshot save() {
        return new GameSnapshot(
            currentLocation.name(),
            inventory.stream().map(Item::name).toList(),
            health,
            score,
            turn
        );
    }

    public void restore(GameSnapshot snapshot) {
        // Restore state from snapshot...
        this.health = snapshot.health();
        this.score = snapshot.score();
        this.turn = snapshot.turnNumber();
        // Restore location and inventory by looking up names...
    }
}

// The caretaker — stores snapshots without looking inside them
public class SaveSlotManager {
    private final Map<String, GameSnapshot> slots = new HashMap<>();

    public void saveToSlot(String name, GameSnapshot snapshot) {
        slots.put(name, snapshot);
    }

    public GameSnapshot loadFromSlot(String name) {
        return slots.get(name);
    }
}
```

**Where you see it:** Undo/redo in text editors. Save/load systems in games. Browser back/forward navigation. Database transaction rollbacks. Java's `Serializable` interface is a language-level mechanism for creating mementos.

**Why use records for mementos?** Java records are immutable data carriers — exactly what a memento should be. The snapshot is taken at a point in time and should never change. Records give you immutability, `equals()`, `hashCode()`, and `toString()` for free.

---

### Behavioral Patterns Summary

| Pattern | Problem | Solution | Example |
|---------|---------|----------|---------|
| **Strategy** | Algorithm varies | Interface for the algorithm, swap implementations | `ScoringStrategy`, `Comparator` |
| **Command** | Actions need to be dispatched, queued, undone | Encapsulate each action as an object | `Command` + `CommandRegistry` |
| **Observer** | One change should notify many objects | Subject maintains list of observers | Event bus, GUI listeners |
| **Iterator** | Traverse a collection without exposing internals | Standard traversal interface | `for-each` loop, `Iterable` |
| **Template Method** | Fixed algorithm skeleton, varying steps | Base class defines skeleton, subclasses fill in | `SimpleGameLoop.run()` |
| **State** | Behavior changes with state, growing switch/if-else | State interface, each state is a class | Game states: exploring, combat |
| **Chain of Responsibility** | Request handled by one of many handlers | Chain of handlers, each passes or handles | Servlet filters, middleware |
| **Visitor** | New operations on a structure without modifying it | Visitor interface + `accept()` on elements | AST processing, file tree operations |
| **Memento** | Save and restore object state | Opaque snapshot object | Save/load game, undo/redo |

---

## 5. Architectural Patterns

Architectural patterns operate at a higher level than the GoF patterns. They describe how an entire application (or major subsystem) is structured, not just how a few objects interact.

---

### 5.1 Model-View-Controller (MVC)

**The problem:** Your application has data (the model), a user interface (the view), and logic that connects them (the controller). If these are tangled together, changing the UI breaks the data layer, and changing the data model breaks the UI.

**How it works:** Separate the application into three parts:

- **Model**: The data and business logic. Knows nothing about how it's displayed.
- **View**: The user interface. Displays data from the model but doesn't contain business logic.
- **Controller**: Handles user input, updates the model, and selects which view to display.

**Java example:**

```java
// Model — pure data and logic, no UI knowledge
public class QuizModel {
    private final List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;

    public Question currentQuestion()  { return questions.get(currentIndex); }
    public void answerCorrectly()      { score++; currentIndex++; }
    public void answerIncorrectly()    { currentIndex++; }
    public boolean isFinished()        { return currentIndex >= questions.size(); }
    public int getScore()              { return score; }
}

// View — displays data, no logic
public class QuizView {
    private final TextAppUser user;

    public void showQuestion(Question q) {
        user.println(q.text());
        for (int i = 0; i < q.options().size(); i++) {
            user.println((i + 1) + ". " + q.options().get(i));
        }
    }

    public int getAnswer() {
        return user.readInt("Your answer: ") - 1;
    }

    public void showScore(int score) {
        user.println("Final score: " + score);
    }
}

// Controller — connects model and view
public class QuizController {
    private final QuizModel model;
    private final QuizView view;

    public void runQuiz() {
        while (!model.isFinished()) {
            view.showQuestion(model.currentQuestion());
            int answer = view.getAnswer();
            if (answer == model.currentQuestion().correctIndex()) {
                model.answerCorrectly();
            } else {
                model.answerIncorrectly();
            }
        }
        view.showScore(model.getScore());
    }
}
```

**Where you've seen it:** The quiz-app example project uses MVC. Web frameworks like Spring MVC, Django, and Ruby on Rails are all built around this pattern. The "model" is usually a database layer, the "view" is HTML templates, and the "controller" is the Java/Python/Ruby code that handles HTTP requests.

**Why MVC matters:** You can replace the view (text UI with a GUI, or a web interface) without changing the model or controller. You can test the model in isolation. You can have multiple views for the same model (e.g., a desktop app and a mobile app sharing the same backend model).

---

### 5.2 Repository

**The problem:** Your application needs to store and retrieve objects (players, items, quests), but you don't want the rest of the application to know *how* they're stored. Today it's an in-memory list; tomorrow it might be a database; next month it might be a REST API.

**How it works:** Define an interface for data access operations (find, save, delete). Implement it with whatever storage mechanism you need. The rest of the application depends on the interface, not the storage.

**Java example:**

```java
public interface QuestionRepository {
    List<Question> findAll();
    Question findById(String id);
    void save(Question question);
    void delete(String id);
}

// In-memory implementation (for development and testing)
public class InMemoryQuestionRepository implements QuestionRepository {
    private final Map<String, Question> store = new HashMap<>();

    public List<Question> findAll()              { return new ArrayList<>(store.values()); }
    public Question findById(String id)          { return store.get(id); }
    public void save(Question question)          { store.put(question.id(), question); }
    public void delete(String id)                { store.remove(id); }
}

// Later: a database implementation, same interface
public class DatabaseQuestionRepository implements QuestionRepository {
    private final Connection db;

    public List<Question> findAll() {
        // SQL query to load all questions
    }
    // ... etc.
}
```

**Where you've seen it:** The quiz-app example project uses a repository for questions. Spring Data JPA generates repository implementations automatically from an interface. Any app that separates "how data is accessed" from "how data is used" is applying this pattern.

**Connection to interface-first design:** Repository is a textbook case of interface-first design. You define what the caller needs (find, save, delete), and the implementation details (HashMap, SQL, file I/O) are hidden behind the interface. You can swap storage mechanisms without changing a single line of application code.

---

## 6. Dependency Injection

This isn't a GoF pattern — it's a principle that you've been practicing all course, probably without knowing it had a name.

**The problem:** An object needs collaborators (dependencies) to do its work. If it creates them itself, it's tightly coupled to specific implementations. You can't test it in isolation, and you can't swap implementations.

**The principle:** Don't create your dependencies — receive them. Pass them through the constructor (or a method), typically as interface types.

**You've been doing this since Week 1:**

```java
// Dependency injection: the player receives its dependencies
public class HumanPlayer implements Player {
    private final TextAppUser user;
    private final CommandRegistry commands;

    public HumanPlayer(TextAppUser user, CommandRegistry commands) {
        this.user = user;
        this.commands = commands;
    }
}
```

Compare this to the alternative:

```java
// NOT dependency injection: the player creates its own dependencies
public class HumanPlayer implements Player {
    private final TextAppUser user = new TerminalUser();
    private final CommandRegistry commands = new ReferenceCommandRegistry();

    // Now this class is locked to TerminalUser and ReferenceCommandRegistry.
    // You can't test it with a mock user. You can't swap the command registry.
}
```

**The three forms of dependency injection:**

| Form | How | Example |
|------|-----|---------|
| **Constructor injection** | Pass dependencies in the constructor | `new HumanPlayer(user, commands)` |
| **Method injection** | Pass dependencies as method parameters | `game.run(gameLoop)` |
| **Setter injection** | Set dependencies after construction | `player.setCommandRegistry(commands)` — less common, less safe |

Constructor injection is the most common and most recommended. It makes dependencies explicit (they're right there in the constructor signature), it guarantees the object is fully initialized, and it works naturally with `final` fields.

**Where you've seen it everywhere in this course:**

- `TextAppUserImpl` receives a `TextUser` through its constructor
- `SimpleGameLoop.run()` receives a `Game` through a method parameter
- `Command` implementations receive `Actor` and `LocationMap` through their constructors
- Factories receive their configuration through constructors

**Dependency Injection frameworks:** In larger applications, wiring dozens of objects together by hand becomes tedious. Frameworks like Spring, Guice, and Dagger automate this — you annotate your classes, and the framework creates and wires them. But the principle is the same one you've been practicing by hand.

**Why it matters:** Dependency injection is the practical expression of "depend on abstractions, not on concrete implementations." It's what makes testing possible (inject mocks), what makes flexibility possible (inject different implementations), and what keeps your code clean (each class declares exactly what it needs).

---

## 7. Other Patterns Worth Exploring

The following patterns are less commonly used in everyday code, but worth knowing about. We'll keep these brief.

---

### 7.1 Flyweight

**The problem:** You have a huge number of similar objects and they're consuming too much memory. Many of them share the same data.

**The idea:** Share the common (intrinsic) state between objects. Only store the unique (extrinsic) state per instance.

**Classic example:** A text editor doesn't create a separate `Character` object for every letter in a document. Instead, it shares glyph objects for 'a', 'b', 'c', etc. The *intrinsic* state (what 'a' looks like) is shared. The *extrinsic* state (position on the page) is stored separately.

**In games:** A forest with 10,000 trees doesn't need 10,000 copies of the tree mesh and texture. Each tree shares the same model data and only stores its own position and scale.

---

### 7.2 Mediator

**The problem:** Multiple objects need to communicate with each other, and direct connections between all of them create a tangled web of dependencies.

**The idea:** Introduce a mediator object that each participant talks to. Instead of A knowing about B, C, and D, everyone just knows the mediator. The mediator coordinates the interactions.

**Classic example:** An air traffic control tower is a mediator. Planes don't communicate directly with each other — they all talk to the tower, and the tower coordinates.

**In software:** Chat room servers, GUI dialog boxes where changing one control affects others, and event dispatchers are all mediators.

---

### 7.3 Interpreter

**The problem:** You have a language or set of rules that needs to be evaluated. The rules are expressed in some structured format, and you need to process them.

**The idea:** Represent the grammar of the language as a class hierarchy. Each rule is a class with an `interpret()` method. Composite rules contain sub-rules.

**Classic example:** A mathematical expression like `(3 + 5) * 2` can be represented as a tree of `Expression` objects: `Multiply(Add(Literal(3), Literal(5)), Literal(2))`. Calling `interpret()` on the root evaluates the expression.

**In practice:** Regular expression engines, SQL parsers, and template engines use interpreter-like structures. This pattern is rarely implemented from scratch in application code — parser generators and existing libraries handle most cases.

---

### 7.4 Object Pool

**The problem:** Creating and destroying objects is expensive (e.g., database connections, threads, large buffers), and you need many of them over time.

**The idea:** Maintain a pool of reusable objects. When you need one, take it from the pool. When you're done, return it to the pool instead of destroying it.

**Classic example:** A database connection pool creates 10 connections at startup. When your code needs a connection, it borrows one from the pool. When the transaction is done, the connection goes back. This avoids the overhead of opening and closing connections constantly.

**In practice:** `ThreadPoolExecutor` in Java, `HikariCP` (a database connection pool), and game engines that pool frequently created/destroyed objects (bullets, particles) all use this pattern.

---

## 8. Summary and Further Reading

### The Patterns at a Glance

| Category | Pattern | One-Sentence Summary |
|----------|---------|---------------------|
| **Creational** | Factory Method | A method decides which class to instantiate |
| | Abstract Factory | A family of factory methods that produce consistent objects |
| | Builder | Accumulate parts step by step, then assemble |
| | Singleton | One instance, globally accessible (use sparingly) |
| | Prototype | Create new objects by cloning a template |
| **Structural** | Adapter | Translate one interface to another |
| | Decorator | Add behavior by wrapping an object |
| | Facade | Simplify a complex subsystem behind one class |
| | Composite | Treat individual objects and groups uniformly |
| | Proxy | Control access to an object through a stand-in |
| | Bridge | Separate two independent dimensions of variation |
| | Null Object | Replace null with an object that does nothing |
| **Behavioral** | Strategy | Swap algorithms via an interface |
| | Command | Encapsulate actions as objects |
| | Observer | Notify interested parties when something changes |
| | Iterator | Traverse a collection without knowing its internals |
| | Template Method | Fixed skeleton, variable steps |
| | State | Behavior changes as internal state changes |
| | Chain of Responsibility | Pass a request along a chain of handlers |
| | Visitor | Add operations to a structure without modifying it |
| | Memento | Save and restore state without exposing internals |
| **Architectural** | MVC | Separate data, display, and control |
| | Repository | Hide storage behind a data access interface |
| **Principle** | Dependency Injection | Receive dependencies, don't create them |

### A Few Honest Notes

**Don't force patterns.** The biggest mistake with design patterns is seeing them as a goal rather than a tool. If your code is simple and clear without a pattern, leave it alone. A pattern should simplify, not complicate.

**Patterns emerge from good design.** When you design interfaces from the caller's perspective and defer implementation — the approach we've practiced all course — patterns often emerge naturally. The Command pattern fell out of wishful programming. The Builder pattern fell out of the LocationMap design. You didn't set out to "apply the Builder pattern." You designed a good interface, and it turned out to have a name.

**Learn the vocabulary.** Even if you never consciously "apply" a pattern, knowing the names matters. When someone says "we need an observer here" or "this is a strategy," you'll know what they mean. The shared vocabulary is perhaps the most practical benefit of learning design patterns.

**Start with the problem, not the pattern.** When you face a design challenge, think about the problem first. What's the tension? What changes independently? What should be hidden? Then check if a pattern addresses that specific tension. Don't browse the pattern catalog looking for something to apply.

### Further Reading

If you want to go deeper, these resources are excellent:

- **"Design Patterns: Elements of Reusable Object-Oriented Software"** by Gamma, Helm, Johnson, Vlissides (1994) — The original GoF book. Dense but definitive. Worth reading after you've built some intuition from practice.

- **"Head First Design Patterns"** by Freeman and Robson — More approachable than the GoF book, with visual explanations and humor. Good for a first pass through the patterns.

- **Refactoring Guru**: https://refactoring.guru/design-patterns — Clear visual explanations of every GoF pattern, with code examples in Java and other languages. This is the best free online resource.

- **"Effective Java"** by Joshua Bloch — Not a patterns book, but Items 1 (static factories), 2 (builders), and 18 (composition over inheritance) directly relate to patterns covered here.

- **"Patterns of Enterprise Application Architecture"** by Martin Fowler — Covers architectural patterns like Repository, Unit of Work, and Active Record in depth. Relevant if you go into web development.

- **Source Making**: https://sourcemaking.com/design_patterns — Another good online reference with examples and anti-patterns.

---

*This document covers a lot of ground. You don't need to memorize all 23+ patterns. Focus on recognizing the problems they solve, and the patterns will come naturally when you need them. As you continue building the text adventure, you'll encounter situations where a pattern fits — and now you'll have a name for it.*
