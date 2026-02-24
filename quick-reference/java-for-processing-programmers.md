# Java for Processing Programmers

This is a quick reference for concepts that Java requires but Processing hides from you. Use it whenever you encounter unfamiliar syntax in the course code.

---

## Classes and Objects

In Processing, your code lives inside a sketch — you write functions like `setup()` and `draw()` and everything just works. Java has no sketch. Everything lives inside a **class**.

```java
public class Room {
    // fields (data this object holds)
    private String name;
    private Room north;

    // constructor (how you create a new Room)
    public Room(String name) {
        this.name = name;
    }

    // method (what this object can do)
    public String getName() {
        return name;
    }
}
```

You create objects from a class using `new`:

```java
Room kitchen = new Room("Kitchen");
```

---

## Constructors

A constructor is a special method that runs when you create a new object with `new`. It has the **same name as the class** and **no return type** (not even `void`).

```java
public class Player {
    private String name;
    private int health;

    // This is the constructor
    public Player(String name, int health) {
        this.name = name;
        this.health = health;
    }
}
```

```java
Player hero = new Player("Aria", 100);
// Java calls the constructor, which sets name = "Aria" and health = 100
```

If you don't write a constructor, Java gives you a default empty one. But the moment you write any constructor, the default disappears.

---

## Access Modifiers: `public` and `private`

Processing lets you access everything from everywhere. Java forces you to be explicit:

- **`public`** — anyone can access this (other classes, other packages)
- **`private`** — only this class can access this

```java
public class Room {
    private String name;          // only Room can read/write this directly

    public String getName() {     // anyone can call this method
        return name;
    }
}
```

**Why?** Private fields protect the object's internal state. If `name` were public, any code anywhere could change it to `null` and break your program. By making it private, you control how it's accessed.

**Rule of thumb:** fields are `private`, methods are `public` (unless they're internal helpers).

---

## The `this` Keyword

`this` refers to the current object — the one whose method is running right now. You need it when a parameter has the same name as a field:

```java
public Room(String name) {
    this.name = name;    // this.name = the field, name = the parameter
}
```

Without `this`, Java would think both `name` references mean the parameter, and the field would never get set.

You also see `this` when an object passes itself to another object:

```java
public void connectNorth(Room other) {
    this.north = other;
    other.south = this;   // "I am your south neighbor"
}
```

---

## `null`

`null` means "no object." A field that hasn't been assigned points to `null`.

```java
Room room = new Room("Start");
room.getNorth();    // returns null — no room is connected to the north
```

If you call a method on `null`, Java crashes with a `NullPointerException`:

```java
Room nowhere = null;
nowhere.getName();    // NullPointerException!
```

Always check before using a reference that might be null:

```java
if (room.getNorth() != null) {
    // safe to use room.getNorth()
}
```

---

## Packages

A package is a folder for organizing classes. The `package` declaration at the top of a file must match the folder path:

```
src/main/java/dk/ek/evu/vpf26/txtadv/Room.java
```

```java
package dk.ek.evu.vpf26.txtadv;

public class Room { ... }
```

If you want to use a class from another package, you `import` it:

```java
import dk.ek.evu.vpf26.txtadv.Room;
```

Classes in the same package can see each other without importing.

---

## Interfaces and `implements`

An interface defines **what** an object can do, without saying **how**:

```java
public interface TextIn {
    String nextLine(String prompt);
}
```

A class that `implements` an interface promises to provide all the methods the interface declares:

```java
public class ScannerTextIn implements TextIn {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
```

The power: any code that accepts a `TextIn` works with **any** implementation — keyboard, scripted, file, network — without changes.

---

## `@Override`

`@Override` is an annotation that tells the compiler: "I intend to implement/override a method from an interface or superclass." It's optional but highly recommended.

```java
@Override
public String nextLine(String prompt) { ... }
```

**Why use it?** If you misspell the method name or get the parameters wrong, the compiler catches it immediately:

```java
@Override
public String nextLne(String prompt) { ... }
// Compiler error: "method does not override or implement a method from a supertype"
```

Without `@Override`, Java would silently create a new method called `nextLne`, and your class would not actually implement the interface correctly. The error would show up later, in a much more confusing way.

---

## Records

A record is a compact way to create an immutable data class. Java generates the constructor, getters, `equals()`, `hashCode()`, and `toString()` for you.

```java
public record Coordinate(int x, int y) {}
```

This single line gives you:

```java
Coordinate pos = new Coordinate(3, 7);
pos.x();         // 3
pos.y();         // 7
pos.toString();  // "Coordinate[x=3, y=7]"
```

Use records for plain data. Use interfaces for behavior.

---

## Project Structure

Maven projects follow a standard folder layout:

```
VP_Project/
  pom.xml                          # Build configuration (dependencies, Java version)
  src/
    main/
      java/
        dk/ek/evu/vpf26/txtadv/   # Your package → your code
          Room.java
          Player.java
    test/
      java/                        # Test code mirrors the same package structure
```

- `pom.xml` — tells Maven which Java version to use, what libraries to include, and how to build
- `src/main/java/` — your source code
- `src/test/java/` — your test code
- The folder path under `java/` must match the `package` declaration in your files

To build and test from the terminal:

```bash
mvn clean compile    # compile your code
mvn test             # run all tests
```

---

## Quick Lookup Table

| Processing | Java equivalent |
|---|---|
| Everything in one sketch | Each class in its own `.java` file |
| Variables at top of sketch | `private` fields in a class |
| No constructor needed | Constructor required if you want to pass data in |
| Access anything from anywhere | `public` / `private` control access |
| `this` rarely needed | `this` distinguishes fields from parameters |
| No packages | `package` declaration + matching folders |
| No interfaces | `interface` + `implements` for swappable behavior |
| No annotations | `@Override` for safety |
