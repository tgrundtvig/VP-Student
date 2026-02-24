# Exercise 2: Maze Explorer

## Goal

Design and build your own small maze of at least 5 rooms, then navigate it automatically using `ScriptedTextIn`.

**Estimated time: 20 minutes.**

---

## Background

In class, `MyMaceFactory` created a fixed maze. In this exercise, you build your own maze from scratch. This reinforces the core pattern: create `Room` objects, connect them with `connectNorth`/`connectEast`/etc., wrap them in a `Mace`, and run the game.

---

## Tasks

### 1. Design your maze on paper

Before writing code, sketch a map. Use at least 5 rooms. Make sure the player can reach every room from the start. Give each room a distinct name and description.

Example (you should create your own):

```
  [Library] -- [Garden]
      |
  [Hallway] -- [Kitchen]
      |
  [Cellar]
```

### 2. Create a factory

Write a class (e.g., `MyCustomMaceFactory`) with a method that:

1. Creates all your rooms
2. Connects them using `connectNorth`, `connectEast`, etc.
3. Returns a `Mace` with the starting room

### 3. Navigate with scripted input

Write a `main` method that:

1. Creates your maze using your factory
2. Creates a `ScriptedTextIn` with a sequence of directions that walks through several rooms
3. Creates a `Player` with that `ScriptedTextIn`
4. Runs the game

For example, if your start room connects north to the Hallway, which connects east to the Kitchen:

```java
ScriptedTextIn script = new ScriptedTextIn("north", "east", "quit");
```

Run the program and verify the output matches your map.

---

## Hints

- Look at `MyMaceFactory` from class as a template for your factory.
- `ScriptedTextIn` feeds pre-written strings to the player, so no typing needed.
- If you get "There is no exit to the north!" — check your connections against your map.
- End your script with `"quit"` so the game exits cleanly.

---

## Extension (Optional)

- Create a maze where the player must visit every room to "win."
- Add a dead-end room with only one exit — the player must go back the way they came.
- Try making a loop: Room A connects to B, B to C, C back to A. What happens when you navigate it?
