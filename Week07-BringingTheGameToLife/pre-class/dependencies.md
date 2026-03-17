# Exercise: Mapping Dependencies

**Estimated time: ~15 minutes**

---

## What You'll Do

For each missing piece from the inventory, figure out what it **depends on**. What does it need to receive through its constructor? What does it need to do? This is the step between "I know what's missing" and "I can implement it."

---

## The Missing Pieces

For each of the following, write down:
- **What interfaces does it depend on?** (These become constructor parameters.)
- **What does it need to do?** (One or two sentences.)

### 1. HumanPlayer (implements Player, which extends Actor)

Think about what happens inside `takeTurn()`. The game loop calls it with no arguments, so the player must already have everything it needs.

- **Depends on**: ___
- **What it does**: ___

Hints: The player needs to read input from the user and dispatch commands. What two objects provide those capabilities? Also, like any `Actor`, it needs to track its current location and inventory.

### 2. LookCommand (implements Command)

This is the simplest useful command. The player types "look" and sees where they are.

- **Depends on**: ___
- **What it does**: ___

Hints: To describe a location, you need the location itself (from the actor) and a way to print text (the `TextAppUser`). What about the exits — how do you find which directions have neighbours?

### 3. MoveCommand (implements Command)

The player types "move north" (or "go north", or just "north" — your choice). They move to the neighbouring location.

- **Depends on**: ___
- **What it does**: ___

Hints: The command receives `commandWords` as a `List<String>`. The second word is the direction. You need the actor (to get and set the current location) and a way to print feedback (success or "you can't go that way"). Does it need the `LocationMap`, or can it work with just `Location.getNeighbour()`?

### 4. QuitCommand (implements Command)

The player types "quit". The game ends.

- **Depends on**: ___
- **What it does**: ___

Hints: The game loop checks `game.isGameOver()` after each round. How does a command tell the game to stop? It needs some way to signal "the game is over." Think about what object tracks that state.

### 5. SimpleGame (implements Game)

The orchestrator. It builds the world, creates the player, and manages the game lifecycle.

- **Depends on**: ___
- **What it does in `initialize()`**: ___

Hints: The game needs to build locations (requires a `LocationMapBuilder`), create commands (requires `TextAppUser` and the actor — but the actor doesn't exist until `initialize()` creates it), register commands (requires `CommandRegistry`), and create the player (requires `TextAppUser` and `CommandRegistry`).

### 6. The main() method

This is the outermost wiring. It creates the objects that don't belong to any specific game.

- **Creates**: ___
- **Then calls**: ___

---

## The Creation Order

Now comes the important part. Write down the order in which objects must be created. Some objects depend on others, so they must be created later.

Think about it: you can't create a `HumanPlayer` before you have a `TextAppUser` and a `CommandRegistry`. You can't register a `MoveCommand` before you have a player (because the command needs the actor to move). You can't call `gameLoop.run(game)` before the game exists.

Write the creation order as a numbered list. For example:

```
1. Create ___
2. Create ___
3. Create ___ (needs #1 and #2)
4. ...
```

Draw arrows if that helps — "A must exist before B" means there's an arrow from A to B.

---

## A Subtle Question

Look at your dependency list for `LookCommand` and `MoveCommand`. They both need the `Actor` — the player whose turn it is. But the actor doesn't exist until `Game.initialize()` creates it, and the commands are also created in `Game.initialize()`.

So: commands need the actor, and the actor needs commands (via the `CommandRegistry`). This is a **circular dependency** at creation time.

How would you solve this? There are several valid approaches:
- Create the actor first with an empty registry, then create commands, then register them
- Create commands that receive the actor later (via a setter or a different approach)
- Have commands look up the "current actor" at execution time rather than storing it

Don't worry about finding the "right" answer. Just think about the problem. We'll solve it together in class.

---

## Checkpoint

When you're done, you should have:
- Dependencies listed for each missing piece
- A creation order showing what must exist before what
- An awareness of the circular dependency between commands and actors
