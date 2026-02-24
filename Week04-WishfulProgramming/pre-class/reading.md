# Wishful Programming

## Part 1: The Idea

Imagine you're building a house. You don't start by mixing concrete — you start with a blueprint. The blueprint says "there will be a kitchen here" without specifying the brand of tiles or the colour of the cabinets. Those details come later.

**Wishful programming** is the same idea applied to code. You write the code you *wish* you had, using interfaces and method signatures that don't exist yet. Then you make them real.

Here's a concrete example. Suppose you're building a notification system. You might start by writing this:

```java
public class NotificationService
{
    private final NotificationSender sender;

    public NotificationService(NotificationSender sender)
    {
        this.sender = sender;
    }

    public void notifyUser(String recipient, String subject, String message)
    {
        Notification notification = new Notification(recipient, subject, message);
        sender.send(notification);
    }
}
```

When you write this code, `NotificationSender` might not exist yet. `Notification` might not exist yet. You're writing **wishfully** — describing what you need, not what you have.

And that's fine. In fact, it's the whole point.

## Part 2: Why This Works

When you write the high-level code first, you discover what the lower levels need to do. The `NotificationService` above tells us:

1. We need a `Notification` that holds a recipient, subject, and message → that's a **record**
2. We need a `NotificationSender` that can `send(Notification)` → that's an **interface**

The high-level code *designed* the lower-level contracts. We didn't guess what interfaces we might need — the code told us.

Compare this to the opposite approach: "Let me build an EmailSender class first, then figure out how to use it." That's bottom-up, and it often leads to interfaces that don't quite fit what the caller actually needs.

**Top-down design with wishful programming:**
1. Write the code you wish you had (high-level logic)
2. Discover what interfaces and records you need
3. Define those interfaces and records
4. Repeat for the next layer down
5. Implement only when you reach trivial leaf nodes

## Part 3: Connection to What You Already Know

You've been doing this without realizing it.

In Week 2, `TextIn` was designed because `Player` *needed* a way to get input. The interface wasn't designed in isolation — it was shaped by what the caller required.

In Week 3, `TextOut` mirrored the same idea for output. And `TextAppUser` was designed because we *wished* for a richer interface with `readInt()`, `choose()`, and `println()`.

Each time, the pattern was the same:
1. The calling code tells us what it needs
2. We define an interface for that need
3. We implement the interface (sometimes multiple ways)

Wishful programming is just this pattern done *deliberately* and *from the start*.

## Part 4: The Payoff — Adding Without Modifying

Look at `Player.moveInMace()` one more time:

```java
if ("North".equalsIgnoreCase(move))
{
    // ... handle north
}
else if ("East".equalsIgnoreCase(move))
{
    // ... handle east
}
else if ("Quit".equalsIgnoreCase(move))
{
    // ... handle quit
}
else
{
    IO.println("Unknown command:" + move);
}
```

What happens when you want to add a "look" command? You add another `else if` block. What about "help"? Another one. "inventory"? Another. The method grows and grows.

Now imagine instead you had:

```java
Map<String, Command> commands = new HashMap<>();
commands.put("north", northCommand);
commands.put("east", eastCommand);
commands.put("quit", quitCommand);
```

Adding "look" means adding one line: `commands.put("look", lookCommand)`. No `else if`. No modifying existing code. Just *extending* it.

This is what we'll build in class. The key insight: **the Command interface is designed wishfully**. We write the loop code first (the code that looks up and executes commands), and that tells us what the `Command` interface needs to look like.

## Reflection

Before you move on to the exercises, think about:

1. When you wrote the Week 03 exercises (implementing `ConsoleTextOut` and `ListTextOut`), the `TextOut` interface was already defined. Who decided what that interface should look like? Was it the implementations, or was it the code that *uses* the interface?

2. In the notification example above, `NotificationService` was written first. It doesn't know if notifications go by email, SMS, or carrier pigeon. What's the advantage of that ignorance?

3. How is wishful programming different from "just making things up"? What keeps it grounded?

Keep these questions in mind. The exercises will make this concrete.
