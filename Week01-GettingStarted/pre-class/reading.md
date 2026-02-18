# Getting Ready to Build

## Part 1: What This Course Is About

This is not a course about Java syntax. You already know enough Java to write classes, use loops, and call methods. This course is about **design** — how to structure programs so they are easy to understand, easy to test, and easy to change.

We learn design by **building something together**. Every week we work on the same project: a text adventure game. Each week the game grows, and each week we discover a new design idea that helps us manage the growing complexity.

There are no slides. There are no lectures about theory in isolation. We write code together, hit problems together, and solve them together.

## Part 2: The Game — A Text Adventure

A text adventure is a game where the player explores a world by typing commands. The computer describes where you are, and you decide where to go. Here is what a session might look like:

```
=== The Start ===
You are standing in a dimly lit corridor.
Exits: North, East

Where do you want to go?> north

=== The Blue Room ===
The walls glow faintly blue. It smells like the ocean.
Exits: South, East, West

Where do you want to go?> east

=== The Green Room ===
Vines cover every surface. You hear birds.
Exits: South, West

Where do you want to go?> quit
Goodbye!
```

The world is made of **rooms**. Each room has a name, a description, and **connections** to other rooms. The player is always in one room and moves by choosing a direction (north, east, south, west).

## Part 3: The Key Concept — Objects Linking to Objects

Here is the core idea we will use in class. Look at this simplified room:

```java
public class Room
{
    private String name;
    private Room north;
    private Room east;

    public Room(String name)
    {
        this.name = name;
    }

    public void connectNorth(Room other)
    {
        this.north = other;
        other.south = this;  // bidirectional!
    }
}
```

Notice that the `Room` class has fields of type `Room`. A room **holds references to other rooms**. When you connect two rooms, each one remembers the other. This forms a graph — a network of objects linked together.

This pattern is simple but powerful. You can build an entire world just by creating rooms and connecting them:

```java
Room start = new Room("The Start");
Room blue  = new Room("The Blue Room");
Room green = new Room("The Green Room");

start.connectNorth(blue);
blue.connectEast(green);
```

Now `start` knows that going north leads to `blue`, and `blue` knows that going south leads back to `start`. The `blue` room also connects east to `green`, and `green` connects west back to `blue`.

The player then just needs to follow these links:

```java
Room current = start;
// Player types "north"
current = current.getNorth();  // now in blue
// Player types "east"
current = current.getEast();   // now in green
```

**This is the pattern you will practice in the exercise below** — objects holding references to other objects, forming a chain.

## Part 4: What You'll Build in Class

In class we will take this idea and build a complete playable game:

- A `Room` class with connections in all four directions
- A factory class that creates a maze of interconnected rooms
- A `Player` class that reads commands from the keyboard and moves through the maze
- A `main` method that ties it all together

By the end of the session you will be able to run the game and explore the maze.

## Reflection

Think about these questions (you do not need to write answers):

- If you have 10 rooms and each can connect to 4 neighbors, how do you keep track of all those connections? What if you add an 11th room?
- What happens if the player types "north" but the current room has no room to the north?
- What is the difference between storing a `String` in a field and storing another `Room` in a field?
