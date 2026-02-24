# Exercise 1: LookCommand

## Goal

Add a "look" command that reprints the current room description. This is the simplest possible command — it proves that you can extend the system without touching any existing code.

## Time Estimate

15-20 minutes.

## What You Will Learn

- **Adding to a system without modifying it**: One new class, one registration line
- **The power of the Command pattern**: The game loop doesn't change at all

## Context

After the live-coding session, you have a `Command` interface and a `Map<String, Command>` that dispatches commands. The game loop looks up a command by name and calls `execute()`. Your job is to add one more entry.

## Instructions

### Step 1: Create `LookCommand`

Create a new class that implements `Command`. It needs access to the player's current room (think about how to get it — the same way the direction commands do).

The `execute()` method should print the current room's description, the same way it's printed when you enter a new room.

### Step 2: Register it

Add one line to register the command:

```java
commands.put("look", new LookCommand(...));
```

### Step 3: Test it

Run the game. Walk to a room, then type "look". You should see the room description printed again.

## Think About This

- You did **not** modify the game loop, the `Command` interface, or any existing command class.
- You added one class and one line of registration code.
- Compare this to adding a new `else if` branch in the old `Player.moveInMace()`. Which approach scales better?
