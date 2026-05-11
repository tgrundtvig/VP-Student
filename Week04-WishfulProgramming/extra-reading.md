# Extra Reading: Wishful Programming As Methodology

> Companion to [Week 04's README](README.md).

## What "wishful" really means

You sit down to write code. You need to load a config file, validate it, and
start a server.

Wrong instinct: "Let me figure out how to parse YAML."

Right instinct: "Let me write the code I *wish* I had."

```java
Config config = Config.loadFrom("server.yaml");
ValidationResult result = config.validate();
if (result.hasErrors()) {
    result.errors().forEach(System.err::println);
    return;
}
Server server = Server.fromConfig(config);
server.start();
```

That's six lines. Nothing exists yet — no `Config`, no `Server`, no
`ValidationResult`. **That's the point.** You're describing the shape of
the solution before committing to its details.

Now the question becomes: *what do these types need to be?*

- `Config` is a thing with `validate()` and a static `loadFrom(...)`. It
  doesn't need to know how YAML works to exist as an interface.
- `ValidationResult` has `hasErrors()` and `errors()`. It's a record.
- `Server` has `fromConfig(...)` and `start()`. Probably an interface.

The shape of the API drops out of the wishful code. You haven't read a
single YAML library doc yet — and you already know what your design needs
to look like.

## The recursion

Wishful programming is a recursive process:

1. Write the **workflow** you wish you had, using made-up types.
2. Promote those types to **interfaces and records**.
3. For each interface, ask: how would you *use* this? Write that workflow.
4. Repeat until you reach trivial leaves.
5. Implement.

This is exactly how we built the text adventure engine in class:
- Top wish: "play a game".
- That needs a `Game` with a `start()` method.
- `Game` needs to run a `GameLoop`.
- `GameLoop` ticks `Actor`s.
- Each `Actor` `takeTurn()`s.
- A `Player` is an `Actor` that asks a `TextAppUser` for a command.
- `TextAppUser` is a `TextUser` adapter (we already had that from Week 3!)

Five recursion steps, and we never wrote one line of implementation.

## Why "wishful" beats "bottom-up"

The alternative is to start at the bottom: build a `Scanner` wrapper, then
build a `Room` class, then build a `Player`, then figure out how they
connect. By the time you reach the top, you've made a hundred small
decisions about types and signatures that don't fit your eventual use case.

Wishful programming flips the dependency: the bottom is built to serve the
top. The top is built to serve the user.

## The Command Pattern Falls Out

Look at what wishful programming produced this week for our engine:

```java
Command cmd = commandRegistry.lookup(userInput);
if (cmd == null) {
    user.println("Unknown command: " + userInput);
} else {
    cmd.execute(player);
}
```

That's a `Command` interface and a `CommandRegistry` that maps strings to
commands. The **Command pattern** wasn't designed in — it emerged from
asking "what would I want here?"

That's the deeper lesson. Many of the GoF patterns aren't things you decide
to use — they're shapes that *appear* when you do interface-first design.
Wishful programming is a shape-finder.

## Pitfalls

- **Don't keep wishing forever.** If your wishes get vaguer (`magicallyDo()`,
  `handleEverything()`), you've stopped designing. Force yourself to write
  a concrete workflow at each layer.
- **Don't wish for the impossible.** If your wish requires reading the
  user's mind or predicting the future, you've over-abstracted. Go up a
  level and reconsider.
- **Don't avoid implementation forever.** Eventually you have to implement
  the leaves. If you can't, your leaves are still too coarse — recurse one
  more step.

## For The Exam

- Define wishful programming.
- Walk through the engine design (`Game` → `GameLoop` → `Actor` →
  `takeTurn` → `Command`) as an example of the recursion.
- Argue why this beats bottom-up design.
- Apply the methodology to a new problem on the spot: pick any domain
  (chat app, todo list, recipe finder) and write the top-level wish.
