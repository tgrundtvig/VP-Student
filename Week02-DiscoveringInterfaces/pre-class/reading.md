# What We Built and What Comes Next

## Part 1: The Text Adventure So Far

In Week 01 we built a playable text adventure game. Here is a recap of the code — if you missed class, this is your chance to catch up.

### Room

A `Room` has a name, a description, and connections to neighboring rooms in four directions:

```java
public class Room
{
    private String name;
    private String description;
    private Room n;
    private Room s;
    private Room e;
    private Room w;

    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public void connectNorth(Room otherRoom)
    {
        this.n = otherRoom;
        otherRoom.s = this;  // bidirectional
    }

    public void connectEast(Room otherRoom)
    {
        this.e = otherRoom;
        otherRoom.w = this;
    }

    // Getters: getNorth(), getEast(), getSouth(), getWest()
    // getRoomDescription() builds a string with name, description, and exits
}
```

Notice the bidirectional connections — when you connect room A north to room B, room B automatically connects south to room A. This is the "objects linking to objects" pattern from the pre-class exercise.

### Mace (Maze) and Factory

A `Mace` is just a wrapper that holds the starting room:

```java
public class Mace
{
    private Room start;

    public Mace(Room start)
    {
        this.start = start;
    }

    public Room getStart()
    {
        return start;
    }
}
```

`MyMaceFactory` creates all the rooms and connects them into a grid:

```java
public class MyMaceFactory
{
    public Mace createMyMace()
    {
        Room r2_0 = new Room("Start", "It looks like a normal living room!");
        Room r0_1 = new Room("The blue room", "This room has blue walls");
        Room r1_1 = new Room("The green room", "This room has green walls");
        // ... more rooms ...

        r2_0.connectNorth(r2_1);
        r0_1.connectEast(r1_1);
        // ... more connections ...

        return new Mace(r2_0);
    }
}
```

### Player

The `Player` holds the current room and runs the game loop. Here is the full method:

```java
public class Player
{
    private Room currentRoom;

    public Player(Room start)
    {
        this.currentRoom = start;
    }

    public void moveInMace()
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit)
        {
            IO.println(currentRoom.getRoomDescription());
            while (true)
            {
                IO.print("Where do you want to go?>");
                String move = scanner.nextLine();
                if ("North".equalsIgnoreCase(move))
                {
                    if (currentRoom.getNorth() == null)
                    {
                        IO.println("There is no exit to the north!");
                        continue;
                    }
                    currentRoom = currentRoom.getNorth();
                    break;
                }
                else if ("East".equalsIgnoreCase(move))
                {
                    // same pattern: check, print error or move
                }
                // ... South, West, Quit ...
                else
                {
                    IO.println("Unknown command:" + move);
                }
            }
        }
        IO.println("Goodbye!");
    }
}
```

**Important:** This is the Week 01 version of Player — it creates a `Scanner` directly inside the method. We will change this in class.

### Main

The entry point ties everything together:

```java
public static void main(String[] args)
{
    MyMaceFactory factory = new MyMaceFactory();
    Mace myMace = factory.createMyMace();
    Room start = myMace.getStart();
    Player me = new Player(start);
    me.moveInMace();
}
```

That's the complete game. Rooms, a factory, a player, a game loop.

## Part 2: The Problem — Can You Test This?

The game works. You can run it, type commands, explore the maze. But now imagine you want to write an automated test.

You want to test: *"When the player is in the Start room and types 'north', they should end up in The red room."*

How would you do it?

Look at `moveInMace()` again. It calls `scanner.nextLine()` — that means **it waits for a human to type**. Your test would hang forever.

Even if you could somehow feed input, there's another problem: `IO.println(...)` writes to the console. **The output disappears.** Your test can't check what was printed.

So the Player class has two problems:

1. **Input is hardcoded** — it always reads from `Scanner(System.in)`, which needs a human
2. **Output is hardcoded** — it always writes to `IO.println`, which prints to the console

The class *works* for playing the game. But it is **impossible to test automatically**.

## Part 3: What If We Could Swap the Tools?

Here's a thought experiment. What if the Player didn't create the Scanner itself? What if, instead, someone *gave* the Player an object that provides input?

For real gameplay, you'd give it something that reads from the keyboard.
For testing, you'd give it something that reads from a pre-written script.

The Player wouldn't know the difference. It would just call a method like `ask("Where do you want to go?")` and get a string back. Where that string comes from — keyboard, script, network, file — doesn't matter.

**This is the core idea.** Separate *what you need* (a way to get input) from *how it's provided* (keyboard, script, etc.).

## Part 4: The Pattern — Defining "What" Without "How"

Java has a feature for exactly this: **interfaces**.

An interface defines *what* an object can do, without saying *how* it does it. Let's see this with a simple example — not the text adventure yet, but something smaller.

Imagine a receptionist at a hotel. Their job is to greet visitors. But different hotels greet visitors differently:

```java
public interface Greeting
{
    String greet(String name);
}
```

This says: "A Greeting is anything that can take a name and produce a greeting message." It says nothing about *what* the greeting looks like.

Now we can write two different implementations. Two new Java keywords appear here:

- **`implements Greeting`** means "this class promises to provide every method that the `Greeting` interface declares." If you forget a method, the compiler will tell you.
- **`@Override`** is a safety annotation. It tells the compiler: "I intend to implement a method from the interface." If you misspell the method name or get the parameters wrong, the compiler catches the mistake immediately instead of silently creating a wrong method.

(For a fuller explanation, see the [Java for Processing Programmers](../../quick-reference/java-for-processing-programmers.md) guide.)

```java
public class FormalGreeting implements Greeting
{
    @Override
    public String greet(String name)
    {
        return "Good day, " + name + ".";
    }
}

public class CasualGreeting implements Greeting
{
    @Override
    public String greet(String name)
    {
        return "Hey " + name + "!";
    }
}
```

Two classes, same interface. Now the receptionist:

```java
public class Receptionist
{
    private Greeting greeting;

    public Receptionist(Greeting greeting)
    {
        this.greeting = greeting;
    }

    public String welcome(String visitor)
    {
        return greeting.greet(visitor);
    }
}
```

The Receptionist doesn't know if it's formal or casual. It just calls `greeting.greet(...)`. You decide when you create the Receptionist:

```java
Receptionist formal = new Receptionist(new FormalGreeting());
formal.welcome("Alice");   // "Good day, Alice."

Receptionist casual = new Receptionist(new CasualGreeting());
casual.welcome("Alice");   // "Hey Alice!"
```

**This is the same pattern we need for the Player.** Instead of creating a Scanner inside the method, the Player would receive a "text input" object through the constructor — just like the Receptionist receives a Greeting. For real games: give it a keyboard reader. For tests: give it a scripted reader.

This pattern is called **dependency injection**: instead of creating your dependencies yourself, you receive them from the outside.

**This is what we'll build in class** — applied to the text adventure.

## Reflection

Think about these questions (you do not need to write answers):

- In the Receptionist example, what would you need to change in the Receptionist class to add a *third* greeting style (say, a SilentGreeting that returns an empty string)? Would you need to change Receptionist at all?

- In the Week 01 Player, the Scanner is created *inside* `moveInMace()`. Why does this make the Player hard to reuse in different contexts (testing, GUI, network)?

- If you wanted to test that `Receptionist.welcome("Bob")` returns the right string, is that easy or hard? Compare this with testing `Player.moveInMace()`. What's different?
