# The Wiring Problem

## Part 1: We Have All the Pieces

Over the last five weeks, we've built a lot of separate pieces:

- **User I/O**: `TextUser`, `TextAppUser`, `TerminalUser` — reading input and printing output
- **Commands**: `Command`, `CommandRegistry`, `ReferenceCommandRegistry` — dispatching typed commands
- **Engine**: `Game`, `GameLoop`, `Actor`, `Location`, `Item`, `LocationMap` — the core game loop and world model
- **Implementations**: `SimpleGameLoop`, `SimpleLocation`, `SimpleLocationMap`, `SimpleLocationMapBuilder`

Each piece works on its own. `SimpleGameLoop` can loop over actors and call `takeTurn()`. `ReferenceCommandRegistry` can look up and execute commands. `SimpleLocationMap` can move actors between locations.

But nothing connects them. If you tried to run the game right now, `takeTurn()` would do nothing — there's no code that reads a command from the player and executes it. The pieces are all there, but they're not wired together.

This is the **wiring problem**: who creates what, who knows about whom, and how do the pieces connect at runtime?

## Part 2: Following the Call Chain

Let's trace what needs to happen when it's a human player's turn. Start from `SimpleGameLoop`:

```java
// Inside SimpleGameLoop.run():
for (Actor actor : actors)
{
    actor.takeTurn();
}
```

The game loop calls `actor.takeTurn()`. That's all it does. It doesn't know if the actor is a human or an NPC. It doesn't know about `TextAppUser` or `CommandRegistry`. It just calls `takeTurn()`.

So what happens inside `takeTurn()` for a human player? Something like this:

```java
// Hypothetical code inside a human player's takeTurn():
// 1. Show the player where they are
// 2. Read a command from the player
// 3. Execute the command
```

Step 1 needs the player's current location (which `Actor` already provides via `getCurrentLocation()`).

Step 2 needs a `TextAppUser` to read input from the terminal.

Step 3 needs a `CommandRegistry` to look up and execute the command.

This means the actor — or at least, a human player actor — needs access to a `TextAppUser` and a `CommandRegistry`. The question is: how does it get them?

## Part 3: Three Approaches

There's no single right answer. Here are three approaches, each with trade-offs.

### Approach A: Pass dependencies through the constructor

```java
public class HumanPlayer implements Actor
{
    private final TextAppUser user;
    private final CommandRegistry commands;
    // ... other fields ...

    public HumanPlayer(TextAppUser user, CommandRegistry commands, ...)
    {
        this.user = user;
        this.commands = commands;
    }

    @Override
    public void takeTurn()
    {
        // Use this.user to print and read
        // Use this.commands to dispatch
    }
}
```

**Pro**: Simple. The player has everything it needs. No changes to the `Actor` interface.
**Con**: The player knows about both user I/O *and* commands. That's a lot of responsibilities for one class.

### Approach B: Change takeTurn() to accept parameters

```java
public interface Actor
{
    void takeTurn(TextAppUser user, CommandRegistry commands);
}
```

**Pro**: Every actor receives the tools it needs.
**Con**: NPCs don't need `TextAppUser` or `CommandRegistry`. We'd be forcing every actor to accept dependencies it might not use. And it changes the `Actor` interface — which means changing `SimpleGameLoop` too.

### Approach C: Create a Player-specific interface

```java
public interface Player extends Actor
{
    // Player-specific methods, if any
}

public class HumanPlayer implements Player
{
    // Has TextAppUser and CommandRegistry internally
}

public class SimpleNPC implements Actor
{
    // Has AI logic internally, no TextAppUser needed
}
```

**Pro**: Human players and NPCs are clearly different. Each has only the dependencies it needs.
**Con**: If `Player` doesn't add any methods beyond `Actor`, the empty interface might seem unnecessary. (But we discussed this in Week 05 — sometimes a marker interface has value.)

Think about which approach you prefer. There are other options too — these three aren't exhaustive. What matters is that you have a reason for your choice.

## Part 4: Who Creates What?

Even after choosing an approach, there's another question: who creates all these objects and connects them?

Someone needs to:
1. Create a `TextAppUser` (e.g., `TerminalUser`)
2. Create a `LocationMap` using the builder
3. Create actors and place them in the world
4. Create a `CommandRegistry` and register commands
5. Create a `Game` that manages all of this
6. Create a `GameLoop` and call `run(game)`

This setup code needs to know about *everything* — every concrete class, every dependency, every connection. That's a lot of knowledge concentrated in one place.

Is that a problem? Not necessarily. Think about a factory assembling a car. The factory floor knows about every part: the engine, the wheels, the seats, the electronics. But each part doesn't know about the others. The steering wheel doesn't know how the engine works. The engine doesn't know what colour the seats are.

The setup code is our "factory floor." It wires everything together so that the individual pieces can stay ignorant of each other. The interfaces ensure that each piece only knows about its own dependencies, not the whole system.

## Part 5: The Game Interface

Look at `Game` again:

```java
public interface Game
{
    void initialize();
    void onTurnStart(int turn);
    boolean isGameOver();
    List<Actor> getTurnSortedActiveActors();
    void onTurnEnd(int turn);
    List<Player> getPlayers();
    List<NPC> getNPCs();
}
```

The `Game` implementation is a natural place for much of the wiring. It could:
- Own the `LocationMap` and build the world in `initialize()`
- Create actors and place them at starting locations
- Create the `CommandRegistry` and register commands
- Return actors in turn order via `getTurnSortedActiveActors()`
- Track game-over conditions

Think about `Game` as the "orchestrator" that knows how the pieces fit together, while each piece only knows its own role.

## Reflection

Before you move on to the piece inventory, think about:

1. In our text adventure, `SimpleGameLoop` calls `actor.takeTurn()` with no arguments. That simplicity was a deliberate design choice. What would we lose if `takeTurn()` needed five parameters?

2. The wiring code (the setup that connects everything) is typically the least reusable part of a system. The interfaces and implementations can be reused in different games. But the specific "create these locations, these actors, these commands" is unique to one game. Is that a problem?

3. When you use a real application — a web browser, a game, a text editor — you don't see the wiring. You just see the pieces working together seamlessly. Someone wrote the wiring code. What's the equivalent of our `Game.initialize()` in those applications?
