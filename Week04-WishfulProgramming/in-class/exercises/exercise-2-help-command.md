# Exercise 2: HelpCommand

## Goal

Add a "help" command that lists all available commands. This exercise has a design decision: what does the help command need access to?

## Time Estimate

20-25 minutes.

## What You Will Learn

- **Least privilege**: Give a class only the information it actually needs
- **Design trade-offs**: There's more than one valid solution
- **Chicken-and-egg problems**: The command map contains the help command, but the help command needs the command map

## Context

You want `help` to print something like:

```
Available commands: east, exits, help, look, north, quit, south, west
```

The command names are the keys of the command map. But the help command is itself *inside* that map. How do you give it access?

## Instructions

### Step 1: Think about the design

There are several options:

**Option A**: Pass the entire `Map<String, Command>` to `HelpCommand`'s constructor. The help command can then iterate over `map.keySet()`. But this means help has access to the whole map, not just the names.

**Option B**: Pass a `Set<String>` of command names. Simpler, but the set must be constructed before the help command is added to it — chicken-and-egg problem.

**Option C**: Pass a `Supplier<Set<String>>` or just ask the map at execution time (store a reference to the map). The map is populated by the time `execute()` is called, even if it wasn't complete when the constructor ran.

Pick one. There is no single "right" answer, but think about which approach gives the help command only what it needs.

### Step 2: Create `HelpCommand`

Implement the command. The output should list all command names, sorted alphabetically.

### Step 3: Register it

Add the help command to the map. If you chose Option A or C, you can register it after all other commands are added.

### Step 4: Test it

Run the game and type "help". Verify that all commands (including "help" itself) appear in the list.

## Think About This

- The chicken-and-egg problem (help is in the map, but needs the map) is a real design challenge. It appears in many systems where components need to know about each other.
- Option C is the most common real-world solution: store a reference, and use it lazily (at execution time, not construction time).
- This exercise forced you to think about dependencies *before* writing code. That's the design-first mindset.
