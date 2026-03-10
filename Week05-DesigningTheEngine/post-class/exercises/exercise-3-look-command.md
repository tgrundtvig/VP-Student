# Exercise 3: Design a LookCommand (Stretch)

## Goal

Design and implement a `LookCommand` that describes the player's current location — connecting the Command pattern from Week 04 to the engine interfaces from Week 05.

## Time Estimate

20-30 minutes.

## What You Will Learn

- **Connecting separate subsystems**: The command system (Week 04) meets the engine (Week 05)
- **Dependency thinking**: What does a command need access to in order to do its job?
- **The wiring question**: A preview of what we'll tackle in class next week

## Context

In Week 04, we built the Command pattern:

```java
public interface Command
{
    String keyword();
    void execute(List<String> commandWords);
}
```

In Week 05, we built locations with names, descriptions, items, and actors. A "look" command should show all of this to the player.

## Instructions

### Step 1: Think about what LookCommand needs

When a player types "look", the game should display something like:

```
The Rusty Anchor Tavern
A dimly lit tavern that smells of salt and rum.

Items here: rusty sword, gold coin
Also here: Long John Silver
Exits: north, east
```

To produce this output, `LookCommand` needs access to:
- The player's current location (to get name, description, items, actors)
- Some way to print text to the player (TextAppUser? IO.println? Something else?)
- The available exits (which directions have neighbours?)

Think about: how does the command get access to these things? Through its constructor? Through the actor? Through some other mechanism?

### Step 2: Implement it

Create `LookCommand` in a sensible package. Decide what dependencies it needs in its constructor.

The `execute()` method should:
1. Get the actor's current location
2. Print the location name and description
3. Print items at the location (if any)
4. Print other actors at the location (if any)
5. Print available exits (directions where `getNeighbour()` returns non-null)

For printing exits, you'll need to check each `Direction` value:

```java
for (Direction dir : Direction.values())
{
    Location neighbour = location.getNeighbour(dir);
    if (neighbour != null)
    {
        // This direction has an exit
    }
}
```

### Step 3: Think about the wiring problem

To create a `LookCommand`, you probably gave it an `Actor` (or `Player`) reference so it can call `getCurrentLocation()`. And maybe a `TextAppUser` to print output.

But who creates the `LookCommand`? Who passes in those dependencies? Where does this setup code live?

This is the **wiring problem** — and it's exactly what we'll work on in class next week. For now, just notice the question. You don't need to solve it yet.

### Step 4: Test it (optional)

If you want to test your `LookCommand`:
1. Build a small map using `SimpleLocationMapBuilder`
2. Create a `SimpleActor` at one of the locations
3. Add some items to the location
4. Create the `LookCommand` with the actor and some way to print output
5. Call `execute()` and check that the output makes sense

If you use `IO.println()` for output, the text just goes to the console. If you want something more testable, think about how `ScriptedTextIn` made input testable in earlier weeks — could you do the same for output?

## Think About This

- `LookCommand` needs an `Actor`, a way to print, and access to the location system. That's a lot of dependencies for one small command. Is there a simpler way to provide what it needs?

- Every command we build (look, take, drop, move, inventory) will face the same wiring question: how does the command get access to the actor, the location, and the user interface? Next week we'll find a clean answer.

- The "look" command is the foundation of any text adventure. It's the player's eyes into the game world. Getting it right means the location, item, and actor interfaces are working together correctly.
