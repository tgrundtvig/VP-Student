# The Engine So Far

In class we used wishful programming to design the skeleton of a turn-based game engine. We started from a wishful `Main`, followed the compiler errors, and ended up with 7 interfaces and 1 implementation.

This document walks through every file we created, explains how they connect, and then asks you to figure out what's missing.

## The Interfaces

### Actor

```java
public interface Actor
{
    void takeTurn();

    Location getCurrentLocation();

    List<Item> getItems();
}
```

An `Actor` is anything that takes turns in the game. It knows where it is and what items it carries. The `takeTurn()` method is where all the action happens — but notice it takes no parameters. The actor must already know everything it needs to perform its turn.

### Player

```java
public interface Player extends Actor
{
}
```

A `Player` is an `Actor` controlled by a human. Right now it adds nothing — it's an empty interface that just says "I am a Player."

### NPC

```java
public interface NPC extends Actor
{
}
```

An `NPC` is an `Actor` controlled by the computer. Also empty — just a label.

### Location

```java
public interface Location
{
    List<Actor> getActors();
    List<Item> getItems();
}
```

A `Location` is a place in the game world. It knows what actors are present and what items are lying around.

### Item

```java
public interface Item
{
}
```

An `Item` is... something. The interface is completely empty. It's a marker — it says "this thing is an Item" but doesn't require any specific behavior.

### Game

```java
public interface Game
{
    void initialize();

    void onTurnStart(int turn);

    boolean isGameOver();

    List<Actor> getTurnSortedActiveActors();

    void onTurnEnd(int turn);

    Player getCurrentPlayer();

    Player getWinner();

    List<Player> getPlayers();

    List<NPC> getNPCs();
}
```

`Game` is the big one. It manages the game's lifecycle: initialization, turn start/end hooks, game-over detection, and tracking all players and NPCs. It also provides the list of actors in turn order. The `GameLoop` drives the game by calling these methods.

### GameLoop

```java
public interface GameLoop
{
    void run(Game game);
}
```

A `GameLoop` takes a `Game` and runs it. That's it. This is the thinnest interface in the engine — and that's intentional. The game loop shouldn't know about locations, items, or players. It just calls `Game` methods in the right order.

## The Implementation

### SimpleGameLoop

```java
public class SimpleGameLoop implements GameLoop
{
    @Override
    public void run(Game game)
    {
        int turn = 0;
        game.initialize();
        while (!game.isGameOver())
        {
            ++turn;
            game.onTurnStart(turn);
            List<Actor> actors = game.getTurnSortedActiveActors();
            for (Actor actor : actors)
            {
                actor.takeTurn();
            }
            game.onTurnEnd(turn);
        }
    }
}
```

This is the only concrete class in the engine. Read it carefully — it's the heart of the system:

1. Initialize the game
2. Loop until the game is over:
   - Announce the turn
   - Get the actors who act this turn, in order
   - Let each actor take their turn
   - Wrap up the turn
3. Done

Notice what `SimpleGameLoop` does **not** know:
- What the game is about (pirates? space? dungeons?)
- How many players there are
- What locations exist
- What items exist
- How a turn works for a player vs. an NPC

It just calls `game.initialize()`, `game.isGameOver()`, `game.getTurnSortedActiveActors()`, and `actor.takeTurn()`. Everything else is someone else's problem.

## How They Fit Together

```
GameLoop
   |
   | calls methods on
   v
  Game
   |
   | provides and manages
   v
 Actor -----> Location
   |              |
   | carries      | contains
   v              v
  Item           Item
```

`GameLoop` only talks to `Game`. `Game` knows about `Actor`, `Player`, `NPC`, and provides them in turn order. `Actor` knows about `Location` and `Item`. `Location` also knows about `Actor` and `Item`.

## Your Task: What's Missing?

The design above compiles. But it's far from complete. If you tried to actually implement a game with these interfaces, you'd get stuck quickly.

Your job before class: **think about the following questions and write your proposed changes.** For each question, write the interface code you'd add or change, and a sentence explaining *why*.

You can write your proposals on paper, in a text file, or as Java code that doesn't compile — the format doesn't matter. What matters is that you've *thought* about it.

---

### Question 1: Do We Need Player and NPC?

`Player` and `NPC` both extend `Actor` but add zero methods. They're empty.

Think about this:
- Can you name a concrete method that belongs on `Player` but not on `NPC`?
- Can you name a concrete method that belongs on `NPC` but not on `Player`?
- If you can't name any, what value do `Player` and `NPC` add?
- If you remove them, what happens to `Game.getPlayers()` and `Game.getNPCs()`?

**Write your proposal:** Keep `Player` and `NPC` with new methods? Remove them and use only `Actor`? Something else? Explain your reasoning.

---

### Question 2: Location Can't Describe Itself

`Location` has `getActors()` and `getItems()`, but no way to tell the player what this place looks like.

Imagine a player enters a new location. The game should display something like:

```
The Rusty Anchor Tavern
A dimly lit tavern that smells of salt and rum.
A one-eyed parrot watches you from the bar.
```

What methods does `Location` need to support this?

And think further: is a location's description always the same, or might it change? For example, after a battle the tavern might be "a wrecked tavern with overturned tables." Does that affect your design?

**Write your proposal:** What methods would you add to `Location`? Why?

---

### Question 3: How Do You Move Between Locations?

There's no way for an actor to move. `Actor` has `getCurrentLocation()` but nothing to change it. And `Location` has no concept of connections or exits.

Think about these sub-questions:
- Should `Location` know its neighbors? If so, how? A list of exits? A map of direction names to destinations?
- Should there be an `Exit` concept (a record? an interface?) that connects two locations?
- Who is responsible for moving an actor — the `Actor` itself, the `Location`, or the `Game`?
- In the old `Room` class, directions were hardcoded as north/south/east/west. Our engine should work for any theme. A pirate game might have "port" and "starboard." A space game might have "airlock" and "bridge." How do you keep directions generic?

**Write your proposal:** How should movement work? Show the interface changes and explain your reasoning.

---

### Question 4: Item is Empty

`Item` has no methods at all. But the game needs to work with items in many ways:

- A player types `look` and sees "There is a rusty sword on the ground"
- A player types `take sword` and the game needs to find an item by name
- A player types `inventory` and sees a list of their items
- A player types `drop sword` and the sword moves from inventory to the location

What information must `Item` provide so these interactions can work?

Also consider: is `Item` data or behavior? Could it be a record instead of an interface? What would you gain or lose?

**Write your proposal:** What methods or fields should `Item` have? Interface or record? Why?

---

### Question 5: Where Does the Terminal Connect?

We built `TextAppUser` in previous weeks — it handles printing text and reading commands. We built `CommandRegistry` to manage typed commands. But the engine doesn't reference either of them.

When a human player takes their turn, they need to:
- See text output (location description, messages)
- Type commands (movement, actions)
- Have those commands dispatched through a `CommandRegistry`

Where should this connection happen? Some options to consider:
- Should `Actor` have a `getTextAppUser()` method? But then NPCs would need one too...
- Should only `Player` know about `TextAppUser`? But `Player` is currently empty...
- Should `Game` manage the I/O and pass it into `takeTurn()` somehow?
- Should `takeTurn()` take parameters? But `SimpleGameLoop` calls it with no arguments...

There's no single right answer. Think about which option keeps the engine generic while still allowing human players to interact.

**Write your proposal:** Where does `TextAppUser` and `CommandRegistry` connect? Show the interface changes.

---

### Question 6: How Does Multiplayer Work?

We want multiple human players at the same terminal, taking turns. Picture this:

```
=== Turn 3 ===

--- Captain Hook's turn ---
The Rusty Anchor Tavern
A dimly lit tavern. You see Long John Silver here.
Exits: dock, upstairs
> sail dock

--- Long John Silver's turn ---
The Dock
Three ships are moored here. Captain Hook just left.
Exits: tavern, ship
> board ship

--- Polly the Parrot (NPC) ---
Polly flies from the tavern to the dock.
```

Think about:
- All players share one terminal. How does the game know whose turn it is?
- When it's a player's turn, they get prompted for a command. When it's an NPC's turn, the NPC acts automatically with no prompt. Who controls this difference?
- Should each player have their own `CommandRegistry` (maybe different players have different abilities)?
- The turn announcement ("Captain Hook's turn") — who prints it? The engine? The `Game`? The `Actor`?

**Write your proposal:** How does the turn system handle the difference between players and NPCs? What changes are needed?

---

### Question 7: What About Game?

`Game` is the biggest interface. Look at it again:

```java
void initialize();
void onTurnStart(int turn);
boolean isGameOver();
List<Actor> getTurnSortedActiveActors();
void onTurnEnd(int turn);
Player getCurrentPlayer();
Player getWinner();
List<Player> getPlayers();
List<NPC> getNPCs();
```

Some things to consider:
- `getCurrentPlayer()` — this assumes there's a single "current" player. But `SimpleGameLoop` iterates over all actors. Does this method make sense?
- `getWinner()` returns a single `Player`. What if the game ends without a winner (everyone dies)? What if there are multiple winners (team game)?
- `getPlayers()` and `getNPCs()` return separate lists. Is this needed if the engine only ever works with `Actor`?
- Is there anything `Game` should know about that it currently doesn't? Should it know about `Location`s? Should it provide a way to find actors at a specific location?

**Write your proposal:** What would you add, remove, or change on `Game`? Why?

---

## Bringing It All Together

After thinking through all seven questions, step back and look at the big picture:

- Do your proposed changes work together, or do they conflict?
- Could someone implement a pirate game with your interfaces? A space game? Without changing the engine?
- Is there a concept you keep needing that doesn't have an interface yet?

Come to class ready to explain and defend your choices. The goal is not to have the "right" answer — it's to have *a* reasoned answer that we can discuss and improve together.
