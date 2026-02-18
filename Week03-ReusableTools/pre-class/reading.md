# From Specific to Reusable

## Part 1: What TextIn Already Taught You

Look at this interface from our text adventure:

```java
public interface TextIn
{
    String ask(String question);
}
```

Simple, right? One method. But think about what it gave us.

We built two implementations:

- **`ScannerTextIn`** — reads from the keyboard, for real gameplay
- **`ScriptedTextIn`** — reads from a pre-written array, for testing

The `Player` class doesn't know or care which one it's using:

```java
public class Player
{
    private TextIn textIn;

    public Player(TextIn textIn, Room start)
    {
        this.textIn = textIn;
        this.currentRoom = start;
    }

    public void moveInMace()
    {
        // ...
        String move = textIn.ask("Where do you want to go?");
        // ...
    }
}
```

`Player` works the same whether a human is typing or a script is feeding answers. That's the power of an interface — it defines **what** you need, not **how** it's provided.

You've already been doing interface-first design. The question is: where else could we use this trick?

## Part 2: The Missing Half — Output

Look at `Player.moveInMace()` more carefully. Here it is, condensed:

```java
public void moveInMace()
{
    boolean exit = false;
    while (!exit)
    {
        IO.println(currentRoom.getRoomDescription());
        while (true)
        {
            String move = textIn.ask("Where do you want to go?");
            if ("North".equalsIgnoreCase(move))
            {
                if (currentRoom.getNorth() == null)
                {
                    IO.println("There is no exit to the north!");
                    continue;
                }
                currentRoom = currentRoom.getNorth();
                break;
            }
            // ... more directions ...
            else
            {
                IO.println("Unknown command:" + move);
            }
        }
    }
    IO.println("Goodbye!");
}
```

Notice something?

**Input** goes through an interface: `textIn.ask(...)`. We can swap it. We can test it. We control it.

**Output** goes directly to `IO.println(...)`. It's hardcoded. We can't swap it. We can't test it. We don't control it.

Think about what this means:

- **Can you test what the game prints?** Not easily. `IO.println` writes to the console and the output is gone. You'd have to stare at the screen.
- **Can you redirect the output to a file?** No. It's hardcoded.
- **Can you use Player in a GUI application?** No. It always prints to the console.

We solved the input problem with `TextIn`. We haven't solved the output problem yet.

What if output had an interface too?

```java
public interface TextOut
{
    void send(String message);
}
```

Then we could have:
- **`ConsoleTextOut`** — prints to the console, for real gameplay
- **`ListTextOut`** — stores messages in a list, for testing

Sound familiar? It's exactly the same pattern as `TextIn` / `ScannerTextIn` / `ScriptedTextIn`.

## Part 3: Thinking Bigger — Reusable Tools

Here's where it gets interesting.

Once you have a `TextIn` (input) and a `TextOut` (output), you could combine them. Imagine a `UserInterface` that has both:

```java
// Imagine this exists...
public interface UserInterface
{
    String ask(String question);
    void send(String message);
}
```

Now imagine building tools ON TOP of this:

- **A menu**: show numbered options, get the user's choice, validate it
- **Integer input**: ask for a number, reject non-numbers, retry automatically
- **A command system**: map command names to actions, handle "unknown command"

These tools would work with ANY `UserInterface` implementation — console, scripted, GUI, network. Write them once, use them everywhere.

Look at the if-else chain in `Player.moveInMace()`. Five directions, each with the same pattern. What if you had a command system that mapped "north" to an action, "east" to an action, and so on? The method would shrink dramatically.

**This is what we'll build in class.**

## Reflection

Before you move on to the exercises, look at `Player.moveInMace()` one more time and think about these questions. You don't need to write answers — just think.

1. If you wanted to add a "look" command that describes the current room again, how many places in the code would you need to change? Is it obvious where to add it?

2. Every direction (north, east, south, west) follows the same pattern: check if the exit exists, print an error or move. What's the problem with repeating this pattern four times?

3. How would you write an automated test that checks: "when the player types 'north' but there's no exit north, the game prints 'There is no exit to the north!'"? What makes this hard right now?

Keep these questions in mind. They'll come up in class.
