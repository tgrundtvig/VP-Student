# Exercise 3: ExitsCommand

## Goal

Add an "exits" command that lists only the available exits from the current room. This teaches you to read game state and handle nullable values.

## Time Estimate

15-20 minutes.

## What You Will Learn

- **Reading game state**: The command needs to check which directions have connected rooms
- **Handling null**: A direction with no room means no exit in that direction
- **Formatting conditional output**: Only show directions that exist

## Context

When the player types "exits", they should see something like:

```
Available exits: North, East
```

Or if there's only one exit:

```
Available exits: South
```

The room already has `getNorth()`, `getEast()`, `getSouth()`, `getWest()` methods. Each returns `null` if there's no exit in that direction.

## Instructions

### Step 1: Create `ExitsCommand`

Create a new command class. It needs access to the player's current room (same approach as `LookCommand`).

### Step 2: Implement the logic

Check each direction. For each non-null exit, include the direction name in the output.

A simple approach:

```java
List<String> exits = new ArrayList<>();
if (room.getNorth() != null) exits.add("North");
if (room.getEast() != null) exits.add("East");
if (room.getSouth() != null) exits.add("South");
if (room.getWest() != null) exits.add("West");
```

Then join them into a single line:

```java
String result = String.join(", ", exits);
```

### Step 3: Handle the edge case

What if a room has no exits? (This shouldn't happen in a well-designed maze, but defensive code is good practice.) Print something appropriate:

```
There are no exits from this room!
```

### Step 4: Register and test

Add it to the command map and test it. Walk around the maze and type "exits" in different rooms. Verify that the listed exits match the room description.

## Think About This

- This command **reads** game state but doesn't **modify** it. Not all commands change things — some just provide information.
- The null checks feel repetitive. Later in the course, we might explore ways to make Room expose its exits more cleanly (e.g., a `Map<String, Room>` instead of four separate fields). But for now, working with what we have is fine.
- Notice that adding this command required zero changes to the game loop, the Command interface, or any other command. The pattern keeps working.
