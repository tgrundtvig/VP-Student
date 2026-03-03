# Letting the Code Tell You What's Missing

## Part 1: The Caller Knows

Last week you learned wishful programming: write the code you wish you had, then make it real. This week we flip that around. We already *have* interfaces — but are they complete?

Here's the trick: **look at the code that uses the interface.** The caller knows what it needs.

Consider our `SimpleGameLoop`:

```java
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
```

This code tells us exactly what `Game` must be able to do: initialize, check if the game is over, manage turn lifecycle, and provide a sorted list of actors. It also tells us what `Actor` must do: take a turn.

But now ask yourself: what happens *inside* `actor.takeTurn()`?

If the actor is a human player, `takeTurn()` probably needs to:
1. Show the player where they are (Location needs a name and description?)
2. Show what exits are available (Location needs to know its connections?)
3. Read a command from the player (Actor needs access to TextAppUser?)
4. Execute the command (Actor needs access to CommandRegistry?)

We didn't write this code yet. But by *imagining* it, we just discovered four things that might be missing from our interfaces. The caller (the code inside `takeTurn()`) is telling us what the interfaces need.

## Part 2: Try Writing a Workflow

The most reliable way to find gaps is to actually write code that uses the interfaces. You don't need to compile it — just write it on paper or in a text file.

Try writing a simple scenario: a player enters a room, looks around, picks up a sword, and moves north. Just the workflow — the code that calls methods on your interfaces.

```java
// Imaginary code inside a player's takeTurn()
Location here = ???;  // How does the actor know where it is?
IO.println(???);      // How do we get the location's name?
IO.println(???);      // How do we get the location's description?

// What items are here?
List<Item> items = here.getItems();
for (Item item : items)
{
    IO.println(???);  // How do we get the item's name?
}

// Player types "take sword"
// ... how does the actor pick up an item?

// Player types "go north"
// ... how does the actor move? Where is "north"?
```

Every `???` is a gap in the current design. Every place you get stuck is a missing method or a missing concept. You didn't guess — you *discovered* what's missing by trying to use what you have.

This is wishful programming applied to evaluation. The workflow is the test. If you can't write it, the interfaces are incomplete.

## Part 3: One Interface or Two?

When you find something missing, you face a choice: add it to an existing interface, or create a new one?

Here's a simple rule: **if every implementation would need it, it probably belongs on the existing interface. If only some implementations need it, it might be a new interface.**

For example, every `Location` in every theme needs a name. Whether it's a pirate tavern, a space station, or a dungeon room — it has a name. So `name()` belongs on `Location`.

But what about a method like `getTideLevel()`? Only pirate locations care about tides. Space stations don't have tides. That's theme-specific, not engine-level. It doesn't belong on the engine's `Location` interface.

The same question applies to bigger decisions. Right now we have `Player extends Actor` and `NPC extends Actor`, both empty. Do they earn their own interfaces? That depends: can you name a method that goes on `Player` but not `NPC`? If you can, they deserve to be separate. If you can't, maybe `Actor` is all you need.

There's no formula for this. It's a judgement call, and reasonable people can disagree. The important thing is to have a reason for your choice.

## Reflection

Before you move on to the engine walkthrough, think about:

1. When you wrote code in earlier weeks, did you ever get stuck because an interface was missing a method you needed? That feeling of "I need *this* but it's not there" — that's exactly what you should be looking for now.

2. What's the difference between adding a method because you need it *now* and adding a method because you *might* need it someday? Which is safer?

3. The engine interfaces should work for a pirate game, a space game, or a D&D game — all without changes. How does that constraint help you decide what belongs in the engine?

Now go read the engine code and find out what's missing.
