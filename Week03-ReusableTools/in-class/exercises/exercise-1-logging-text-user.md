# Exercise 1: LoggingTextUser — The Decorator Pattern

## Goal

Create a `TextUser` implementation that **wraps another `TextUser`** and silently records everything that passes through it. The wrapped `TextUser` still does all the real work — your class just keeps a transcript on the side.

This is the **Decorator Pattern**: adding behavior to an object without changing its interface.

## What You Will Learn

- **Decorator pattern**: Wrapping an object to add functionality without modifying the original
- **Programming against interfaces**: Your `LoggingTextUser` works with *any* `TextUser`, not just `TerminalUser`
- **Separation of concerns**: The logging responsibility is isolated in its own class
- **Composition over inheritance**: You achieve new behavior by wrapping, not by extending a class

## Context

Look at the code we just wrote together:

- `TextUser` — the interface with `put(String text)` and `String get()`
- `TerminalUser` — the console implementation
- `TextAppUserImpl` — the adapter that wraps a `TextUser`

Your `LoggingTextUser` will sit **between** `TextAppUserImpl` and `TerminalUser`, invisibly recording all interaction.

## Instructions

### Step 1: Create the class

Create a new class `LoggingTextUser` in the package `dk.ek.evu.vpf26.txtadv.user.impl`.

It must:
- Implement `TextUser`
- Take a `TextUser` in its constructor (the wrapped user)
- Have a `List<String>` field called `log` to store all interactions

### Step 2: Implement `put(String text)`

This method should:
1. Add an entry to the log, e.g. `"OUT: " + text`
2. Forward the call to the wrapped `TextUser`

### Step 3: Implement `get()`

This method should:
1. Call `get()` on the wrapped `TextUser` to get the actual input
2. Add an entry to the log, e.g. `"IN: " + result`
3. Return the result

### Step 4: Add a method to retrieve the log

Add a public method:

```java
public List<String> getLog()
```

This returns the list of all logged entries.

### Step 5: Try it out

Create a `main` method (in `LoggingTextUser` itself or in a separate demo class) that does this:

```java
TerminalUser terminal = new TerminalUser();
LoggingTextUser logger = new LoggingTextUser(terminal);
TextAppUser appUser = new TextAppUserImpl(logger);
```

Now use `appUser` to ask the user a few questions (name, age, a menu choice — similar to `TextAppUserDemo`). After the interaction, print the full log:

```java
for (String entry : logger.getLog()) {
    System.out.println(entry);
}
```

## Expected Output Example

After a short interaction, the log might look like:

```
OUT: Enter your name:
IN: Alice
OUT: Enter your age:
IN: 25
OUT: 1 -> Blue
OUT: 2 -> Red
OUT: 3 -> Green
OUT:
OUT: What is your favorite color:
IN: 2
```

Notice how every `put()` and `get()` call is captured — including the ones that `TextAppUserImpl` generates internally (like printing the menu).

## Think About This

- You did **not** change `TerminalUser` or `TextAppUserImpl` at all. The logging was added purely by wrapping.
- You could stack multiple decorators: a `LoggingTextUser` wrapping another `LoggingTextUser` wrapping a `TerminalUser`. Each layer adds behavior.
- If you later write a `FileTextUser` that reads/writes to files, your `LoggingTextUser` would work with that too — because it depends on the `TextUser` interface, not on any specific class.

## Bonus Challenge

If you finish early: Add a `printLog()` method that formats the log nicely, with line numbers and visual distinction between input and output (e.g. `>>>` for output, `<<<` for input).
