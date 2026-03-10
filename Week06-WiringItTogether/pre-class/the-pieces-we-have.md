# The Pieces We Have

This document is an inventory of everything we've built so far, followed by a hands-on exercise where you sketch how a player's turn works.

## Inventory

### User I/O Layer

| Type | Kind | What It Does |
|------|------|-------------|
| `TextUser` | Interface | Low-level text I/O: `put(String)`, `get()` |
| `TextAppUser` | Interface | Rich I/O: `println()`, `readLine()`, `readInt()`, `choose()`, `getCommand()` |
| `TerminalUser` | Implementation | `TextAppUser` backed by `Scanner` / `System.out` |
| `ScannerTextIn` | Implementation | `TextIn` backed by `Scanner` |
| `ScriptedTextIn` | Implementation | `TextIn` with pre-scripted responses (for testing) |

### Command Layer

| Type | Kind | What It Does |
|------|------|-------------|
| `Command` | Interface | `keyword()` + `execute(List<String> commandWords)` |
| `CommandRegistry` | Interface | Register, look up, and execute commands by keyword |
| `ReferenceCommandRegistry` | Implementation | HashMap-based command dispatch |

### Engine Layer — Interfaces

| Type | Kind | What It Does |
|------|------|-------------|
| `Actor` | Interface | `takeTurn()`, location, inventory |
| `Player` | Interface | Extends `Actor` (currently empty) |
| `NPC` | Interface | Extends `Actor` (currently empty) |
| `Location` | Interface | Name, description, actors, items, neighbours |
| `Item` | Interface | Currently empty — you're adding to it in post-class Exercise 2 |
| `Game` | Interface | Lifecycle: initialize, turn hooks, game-over, actor lists |
| `GameLoop` | Interface | `run(Game)` |
| `LocationMap` | Interface | `moveActor()`, `getLocation()` |
| `LocationMapBuilder` | Interface | `addLocation()`, `build()` |
| `Direction` | Enum | NORTH, EAST, SOUTH, WEST, UP, DOWN |
| `LocationCoordinate` | Record | x, y, z position in the grid |

### Engine Layer — Implementations

| Type | Kind | What It Does |
|------|------|-------------|
| `SimpleGameLoop` | Implementation | Basic turn loop: initialize, loop actors, check game-over |
| `SimpleLocation` | Implementation | Stores name, description, actors, items, neighbour array |
| `SimpleLocationMap` | Implementation | HashMap of locations, moveActor moves actors between locations |
| `SimpleLocationMapBuilder` | Implementation | Builds locations, auto-wires neighbours by coordinate |

### Not Yet Implemented

| Type | Status |
|------|--------|
| `Actor` implementation | No concrete class yet (post-class Exercise 1) |
| `Item` methods + implementation | Empty interface (post-class Exercise 2) |
| `Game` implementation | No concrete class yet |
| Movement commands | Not yet connected to engine |
| Look command | Not yet connected to engine |

## The Diagram

Here's how the pieces relate:

```
SimpleGameLoop
      |
      | calls run(game)
      v
    Game  ---- manages ---> Actor(s)
      |                       |
      | owns                  | calls takeTurn()
      v                       v
  LocationMap              ???
      |                  (What happens here?)
      | getLocation()
      | moveActor()
      v
   Location <--- getNeighbour() ---> Location
      |
      | contains
      v
    Item(s), Actor(s)
```

The `???` in the middle is the gap. When `takeTurn()` is called on a human player, *something* needs to happen that involves reading commands, looking at the location, and executing actions. But we haven't built that connection yet.

## Your Task: Sketch a Player's Turn

Before class, write out (on paper, in a text file, or as pseudo-code) exactly what should happen when a human player's turn begins. Be specific. Trace the execution step by step.

Here's a starting point:

```
1. The game loop calls actor.takeTurn()
2. The actor is a human player, so it needs to interact with the terminal
3. First, show the player where they are:
   a. Get current location via getCurrentLocation()
   b. Print the location name and description via ???
   c. Print items at the location via ???
   d. Print available exits via ???
4. Then, read a command:
   a. Prompt the player for input via ???
   b. Parse the input into command words via ???
5. Then, execute the command:
   a. Look up the command in ???
   b. Execute it via ???
6. The turn is over. Control returns to the game loop.
```

Fill in each `???`. For each one, identify:
- **Which object** provides this capability?
- **How does the player get access** to that object?

Don't worry about getting it "right." The goal is to think through the problem concretely so you come to class with specific ideas and questions.

## Design Questions for Class

Think about these before class:

### Question 1: Should takeTurn() show the location automatically?

When it's your turn, should the game automatically describe where you are? Or should the player have to type "look" every turn?

Both are valid. Automatic description means less typing but more text. Manual "look" means the player controls what they see. What do real text adventures do?

### Question 2: What commands do we need for a minimum viable game?

If we want to *play* the text adventure (even a simple version) by the end of class, what's the minimum set of commands?

Think about: what can a player do? What *must* a player be able to do for the game to be playable?

### Question 3: Where does game-over logic live?

`Game.isGameOver()` is checked by the game loop after every full round of turns. But what triggers game-over? Does a command set a flag? Does the Game check some condition? How does a "quit" command end the game?

### Question 4: Do we need to change any existing interfaces?

Look at the current `Actor`, `Game`, `Location`, and `Command` interfaces. Are they sufficient for wiring everything together? Or do you think something needs to change?

Remember: the interfaces are evolving. We change them when we have a good reason — when the code that uses them tells us something is missing.

## Come Prepared

Next week is about making the game *run*. The more concretely you've thought through the wiring, the faster we'll move in class. Bring your sketch, your opinions, and your questions.
