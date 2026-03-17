# Exercise: Planning the First Playable

**Estimated time: ~10 minutes**

---

## What You'll Do

Plan the absolute minimum needed to run the game loop and have a player interact with the world. Not a fun game — just a *working* game.

---

## Task 1: List the Minimum Commands

What commands does the game need to be **functional** (not fun, just playable)?

Write your list here: ___

Think about:
- What can the player do to understand where they are?
- What can the player do to change where they are?
- How does the game end?

A suggested minimum is three commands: **look**, **move**, and **quit**.

Why is "quit" important? Without it, `game.isGameOver()` never returns `true`, and the game loop runs forever. The only way to stop would be to kill the process. That's not a game — that's a hostage situation.

---

## Task 2: Sketch the World

On paper (or in a text file), draw 3-5 locations with connections between them. For each location, write:
- A **name** (e.g., "Dark Forest", "Village Square", "Old Bridge")
- A **one-line description** (e.g., "Tall trees block most of the sunlight. A narrow path leads east.")
- Which **directions** connect to which other locations

Mark the **starting location** — where does the player begin?

Here's an example layout:

```
        [Tower]
           |
         SOUTH
           |
[Forest] --EAST-- [Village Square] --EAST-- [River Bank]
                          |
                        SOUTH
                          |
                      [Old Cave]
```

Your world doesn't need to be this big. Three locations connected in a line is enough for the first playable.

---

## Task 3: Write the Creation Order

In what order must objects be created in `main()` and `Game.initialize()`? Remember: you can't use something before it exists.

Fill in the blanks:

**In `main()`:**
```
1. Create ___ (raw terminal I/O)
2. Create ___ (wraps #1 with rich I/O)
3. Create ___ (command dispatch)
4. Create ___ (location building)
5. Create ___ (the game, receives #2, #3, #4)
6. Create ___ (the loop)
7. Call ___.run(___)
```

**In `Game.initialize()`:**
```
1. Build the ___ using the builder
2. Create the ___ (needs TextAppUser and CommandRegistry)
3. Create ___ commands (need the actor and TextAppUser)
4. Register each ___ in the CommandRegistry
5. Place the ___ at the starting Location
```

---

## Task 4: Trace One Turn

What happens from the moment the game loop calls `actor.takeTurn()` to the moment the turn ends? Trace through the interfaces, step by step.

```
1. SimpleGameLoop calls actor.takeTurn()
2. The actor is a HumanPlayer, so takeTurn() does:
   a. ___
   b. ___
   c. ___
3. If the command was "look":
   a. ___
   b. ___
4. If the command was "move north":
   a. ___
   b. ___
   c. ___
5. Control returns to SimpleGameLoop
6. SimpleGameLoop calls game.onTurnEnd(turn)
7. SimpleGameLoop checks game.isGameOver()
```

Fill in the blanks with specific method calls on specific objects. Don't just write "execute the command" — write which object calls which method.

---

## Task 5: Predict the Output

When you run the game with just "look", "move", and "quit" commands, what does a terminal session look like? Write 10 lines of expected output.

```
> ___
___
___
> ___
___
___
> ___
___
> ___
___
```

Think about: Does the game show the location automatically when you enter a room? Or only when you type "look"? Both are valid — what does your version do?

---

## Verification Checklist

Before class, confirm:

- [ ] I know which interfaces need implementations
- [ ] I know what each implementation depends on
- [ ] I have a sketch of my game world (3-5 locations)
- [ ] I have a creation order for main() and initialize()
- [ ] I can trace one complete turn through the interfaces
