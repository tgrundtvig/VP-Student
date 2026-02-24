# Week 1 Reading: The Patterns Behind What We Built

In class you built rooms, connected them into a maze, and moved a player through it. That code wasn't random — it follows patterns that appear everywhere in software. This reading gives those patterns names so you can recognize them in the future.

**Reading time: 15-20 minutes.**

---

## 1. The Object Graph

When you wrote this in class:

```java
Room start = new Room("Start", "A dimly lit corridor.");
Room blue  = new Room("The Blue Room", "The walls glow faintly blue.");
start.connectNorth(blue);
```

you created an **object graph** — a network of objects connected by references. Each `Room` object holds references to its neighbors. The rooms aren't stored in a list or an array. Instead, they point to each other directly, forming a web of relationships.

Linked structures like this appear constantly in software:

- Web pages link to other web pages (the World Wide Web is a graph)
- File systems have folders that contain other folders
- Social networks have users who follow other users
- Game worlds have locations connected by paths

The key property: you navigate the structure by following references from one object to the next, not by looking things up in a table.

---

## 2. Reference Semantics

When a `Room` field holds another `Room`, it holds a **reference** — an arrow pointing to the other object — not a copy of it.

```java
Room blue = new Room("The Blue Room", "Blue walls.");
Room start = new Room("Start", "A corridor.");
start.connectNorth(blue);
```

After `connectNorth`, the `start` object's `north` field and the local variable `blue` both point to the **same** `Room` object in memory. There is only one Blue Room. If you changed the Blue Room's description, both `blue.getDescription()` and `start.getNorth().getDescription()` would return the new description — because they're looking at the same object.

This is different from primitive types like `int`. When you write `int a = 5; int b = a;`, you get two independent copies of the number 5. Objects work differently: you get two references to the same object.

---

## 3. Bidirectional Linking

Notice what `connectNorth` does:

```java
public void connectNorth(Room otherRoom) {
    this.north = otherRoom;
    otherRoom.south = this;   // bidirectional!
}
```

One method call creates **two** links: `start → blue` (north) and `blue → start` (south). This is a bidirectional link — both objects know about each other.

Why bother? Because the player needs to go back. If rooms only linked one way, you could walk north from Start to Blue, but you could never return south. Bidirectional links make navigation natural.

The cost is that you must keep both links consistent. If you connect A north to B, you must also connect B south to A. The `connectNorth` method handles this automatically — a good example of a method that maintains an **invariant** (a rule that must always be true).

---

## 4. Null as "No Connection"

When you create a new `Room`, its directional fields (`north`, `south`, `east`, `west`) start as `null`. This means "there is no room in that direction."

```java
Room start = new Room("Start", "A corridor.");
start.getNorth();   // returns null — nowhere to go
```

The game loop checks for null before moving:

```java
if (currentRoom.getNorth() == null) {
    IO.println("There is no exit to the north!");
} else {
    currentRoom = currentRoom.getNorth();
}
```

Using `null` to mean "absent" is simple and common in Java, but it requires discipline. If you forget to check, you get a `NullPointerException` at runtime. Later in the course, we'll explore other ways to represent optional values.

---

## 5. The Factory Pattern (Briefly)

`MyMaceFactory.createMyMace()` is responsible for creating all the rooms and wiring them together. The rest of the code — `Player`, `Main` — doesn't know how the maze is built. It just receives a `Mace` with a start room and works with it.

Separating **creation** from **use** is a recurring theme. The factory encapsulates the messy wiring so the game logic stays clean. We'll see more sophisticated versions of this idea later.

---

## Looking Ahead

The game works, but there's a hidden problem in the `Player` class. It creates a `Scanner` directly inside `moveInMace()`, which means:

- You can't test it automatically (the test would need a human to type)
- You can't reuse it in a different context (GUI, network, file input)

Next week, we'll discover how **interfaces** solve this problem — cleanly and elegantly.

---

## Further Reading

- **"Objects linking to objects"** — this is sometimes called the **Object Graph** or **Reference Graph** in textbooks. Any introduction to Java objects covers this.
- **Reference semantics vs value semantics** — search for "Java pass by value vs pass by reference" for the detailed (and slightly surprising) explanation.
- **Null references** — Tony Hoare, who invented null, called it his "billion dollar mistake." If you're curious: https://www.infoq.com/presentations/Null-References-The-Billion-Dollar-Mistake-Tony-Hoare/
