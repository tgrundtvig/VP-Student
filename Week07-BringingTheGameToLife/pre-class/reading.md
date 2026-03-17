# From Design to Implementation

**Reading time: ~15 minutes**

---

## Part 1: You've Designed the Whole System

Take a step back and look at what we've built over the last six weeks.

We have a **user I/O layer**: `TextUser` for raw terminal access, `TextAppUser` for rich input (prompts, menus, commands), `TerminalUser` and `TextAppUserImpl` as concrete implementations.

We have a **command layer**: `Command` as an interface with `keyword()` and `execute()`, `CommandRegistry` for dispatching, `ReferenceCommandRegistry` as a working implementation.

We have a **game engine**: `Game` for lifecycle management, `GameLoop` with `SimpleGameLoop`, `Actor`/`Player`/`NPC` for entities, `Location` with `SimpleLocation`, `LocationMap` with `SimpleLocationMap` and `SimpleLocationMapBuilder`, `Direction`, `LocationCoordinate`, and `Item`.

The architecture is solid. The interfaces define clear boundaries. Each subsystem works independently.

But you can't *play* anything yet. The engine doesn't have a `Game` implementation. There's no `Player` that reads commands from a human. There are no game commands — just the demo `WalkCommand`, `RunCommand`, and `JumpCommand` that print placeholder messages.

This week, we change that. We make the game run.

---

## Part 2: Implementation Strategy — Vertical Slices

There's a tempting approach to implementation: start at the bottom, implement every interface one by one, then wire them together at the end and hope it all works. This is bottom-up implementation, and it's risky. You don't find out if the pieces fit together until the very end, when everything has to work at once.

We're going to do the opposite. We'll implement in **vertical slices** — getting one thing working end-to-end before adding the next.

Here's the plan:

**Slice 1: The player can look around.**
Pick the simplest possible scenario. The player starts in a room, types "look", and sees the room description. To make this work, we need:
- A `Game` implementation that creates at least one location and one player
- A `HumanPlayer` that reads input and dispatches commands
- A `LookCommand` that describes the current location
- A `main()` method that creates everything and starts the game loop

That's it. No movement. No items. No NPCs. Just: start, look, see something.

We run it. We see it work. We know the wiring is correct.

**Slice 2: The player can move.**
Add a `MoveCommand`. Now the player can type "move north" and move to a neighbouring location. We need a few more locations in the world. We run it. We move around. We know movement works.

**Slice 3: The player can quit.**
Add a `QuitCommand`. Now the game loop can actually end. Without this, the only way to stop the game is to kill the process.

**Slice 4 and beyond: Add features.**
Each new command is a self-contained class. Inventory, pick up, drop, help — each one is an independent addition. The infrastructure is already in place.

This is the opposite of bottom-up. It's **top-down implementation**, guided by "what does the user experience first?" At every step, the game *runs*. It might not do much, but it runs.

---

## Part 3: Who Creates What?

Remember the "factory floor" concept from last week's reading. Someone has to create all the concrete objects and wire them together. The individual pieces don't know about each other — they work with interfaces. But *something* has to create the concrete `TerminalUser`, pass it to `TextAppUserImpl`, create the `ReferenceCommandRegistry`, create the commands and register them, build the location map, create the player, place the player in a starting location, create the `Game`, and hand it to the `GameLoop`.

This wiring code is typically split between two places:

**The `main()` method** creates the outermost objects — the ones that don't belong to any subsystem. It creates the `TextAppUser`, the `CommandRegistry`, and the `GameLoop`. Then it creates a `Game` and calls `gameLoop.run(game)`.

**The `Game.initialize()` method** creates the game-specific objects — the world, the actors, the commands. It uses the builder to construct locations, creates the player, and registers the commands the player can use.

Here's the key insight: this wiring code is the **only code in the system** that knows about concrete classes. It's the only place where you see `new TerminalUser()`, `new TextAppUserImpl(...)`, `new ReferenceCommandRegistry()`, `new HumanPlayer(...)`. Everywhere else, the code works with `TextAppUser`, `CommandRegistry`, `Actor` — interfaces only.

And that's OK. The wiring code is *supposed* to know about everything. It's the factory floor. Every part comes through the factory floor, but the parts themselves don't know about each other.

---

## Part 4: The Minimum Playable Game

Let's be concrete about what we need for the first playable version:

**A `Game` implementation** (let's call it `SimpleGame` or `AdventureGame`) that:
- Takes dependencies through its constructor (TextAppUser, CommandRegistry, LocationMapBuilder)
- In `initialize()`: builds a small world (3-5 locations), creates a HumanPlayer, registers commands, places the player at a starting location
- Returns the player from `getTurnSortedActiveActors()`
- Tracks whether the game is over (a boolean flag)

**A `HumanPlayer` implementation** that:
- Takes a `TextAppUser` and `CommandRegistry` through its constructor
- In `takeTurn()`: prompts the user for a command via `TextAppUser.getCommand()`, then dispatches it via `CommandRegistry.executeCommand()`
- Tracks current location and inventory (like any `Actor`)

**At least two commands:**
- `LookCommand`: gets the actor's current location, prints the name, description, and available exits
- `MoveCommand`: parses a direction from the command words, gets the neighbour in that direction, moves the actor

**And a `main()` method** that creates everything and starts the loop:

```
// Pseudocode — not the actual implementation
TextUser terminal = new TerminalUser();
TextAppUser user = new TextAppUserImpl(terminal);
CommandRegistry registry = new ReferenceCommandRegistry();
LocationMapBuilder builder = new SimpleLocationMapBuilder();
Game game = new AdventureGame(user, registry, builder);
GameLoop loop = new SimpleGameLoop();
loop.run(game);
```

That's it. That's the whole game. Everything else is adding commands and content.

---

## Part 5: Adding Commands Is Easy

Once the loop works and the wiring is in place, each new command is a self-contained class that implements `Command`. You write `keyword()`, you write `execute()`, you register it in the `CommandRegistry`, and it works.

Want a help command? Create `HelpCommand`, give it access to the `CommandRegistry` so it can list all registered keywords, register it. Done.

Want an inventory command? Create `InventoryCommand`, give it access to the `Actor`, have it list the actor's items. Done.

Want a pick-up command? Create `PickUpCommand`, give it access to the `Actor` and the current `Location`, have it transfer an item from the location to the actor. Done.

This is the **Command pattern paying off**. The investment we made in Week 04 — defining `Command` as an interface, building the `CommandRegistry`, separating dispatch from execution — all of that means that adding new functionality is just adding a new class. No changes to the game loop. No changes to the player. No changes to anything that already works.

This is what good interface design gives you: **new features are additions, not modifications**.

---

## What to Do Next

The reading is done. Now work through the three exercises:

1. **[The Implementation Inventory](inventory.md)** — verify what exists and what's missing
2. **[Mapping Dependencies](dependencies.md)** — figure out what each missing piece needs
3. **[Planning the First Playable](first-playable.md)** — plan the minimum viable game

Come to class ready to implement. We'll write the code together.
