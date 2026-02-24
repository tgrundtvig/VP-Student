# Exercise 2: Character Creator — Records Meet Interfaces

## Goal

Build a character creator for the text adventure. It asks the player for a name, lets them choose a character class, and distributes stat points — all through `TextAppUser`. The result is stored in a **record**: a plain, immutable data object.

This exercise practices the core design rule of this course:
- **Records** for data (the character sheet)
- **Interfaces** for behavior (the user interaction)

## Time Estimate

45-60 minutes.

## What You Will Learn

- **Records**: Java's way of creating immutable data objects
- **The records vs interfaces distinction**: Data goes in records, behavior goes in interfaces
- **Using `TextAppUser` for real features**: `readLine()`, `readInt()`, `choose()` working together
- **Method design**: Writing a method that takes an interface and returns a record

## Background: What is a Record?

If you haven't used records before, here's the idea. Instead of writing a class with fields, constructor, getters, `toString()`, `equals()`, and `hashCode()`, you write:

```java
public record PlayerCharacter(String name, String characterClass, int strength, int agility, int intelligence) {}
```

That single line gives you:
- A constructor: `new PlayerCharacter("Alice", "Warrior", 8, 4, 3)`
- Getters: `character.name()`, `character.strength()`, etc.
- A nice `toString()`: `PlayerCharacter[name=Alice, characterClass=Warrior, ...]`
- Correct `equals()` and `hashCode()`
- Immutability: the fields cannot be changed after creation

Records are perfect for data that you want to create, pass around, and inspect — but never modify.

## Instructions

### Step 1: Create the record

Create a new file `PlayerCharacter.java` in the package `dk.ek.evu.vpf26.txtadv`.

```java
public record PlayerCharacter(
    String name,
    String characterClass,
    int strength,
    int agility,
    int intelligence
) {}
```

That's the entire file (plus the package declaration). No methods needed — the record gives you everything.

### Step 2: Create the CharacterCreator class

Create a new class `CharacterCreator` in the same package (or in a subpackage if you prefer).

Write a static method with this signature:

```java
public static PlayerCharacter create(TextAppUser user)
```

This is the key design: the method receives a `TextAppUser` (interface) and returns a `PlayerCharacter` (record). Behavior in, data out.

### Step 3: Implement the creation flow

Inside `create()`, use `TextAppUser` methods to gather information:

**Name:**
```java
String name = user.readLine("Enter your character's name: ");
```

**Character class** (use `choose()`):
```java
String[] classes = {"Warrior", "Mage", "Rogue"};
int classChoice = user.choose(classes, "Choose your class: ");
String characterClass = classes[classChoice];
```

**Stats** (use `readInt()` with ranges):

The player has **15 stat points** to distribute across three stats: strength, agility, and intelligence. Each stat must be at least 1 and at most 10.

This is the tricky part. Here's one approach:
1. Ask for strength (min 1, max depends on remaining points)
2. Ask for agility (min 1, max depends on remaining points)
3. Intelligence gets whatever is left

Think about how to calculate the max for each stat. Remember: you need to leave at least 1 point for each remaining stat.

**Return the record:**
```java
return new PlayerCharacter(name, characterClass, strength, agility, intelligence);
```

### Step 4: Display the result

After calling `create()`, use `TextAppUser` to display the character sheet:

```java
user.println();
user.println("=== Your Character ===");
user.println("Name: " + character.name());
user.println("Class: " + character.characterClass());
user.println("Strength: " + character.strength());
user.println("Agility: " + character.agility());
user.println("Intelligence: " + character.intelligence());
```

Notice how you access record fields: `character.name()`, not `character.getName()`. Records use the field name directly as the getter.

### Step 5: Write a main method

```java
public static void main(String[] args) {
    TextUser textUser = new TerminalUser();
    TextAppUser appUser = new TextAppUserImpl(textUser);

    PlayerCharacter character = CharacterCreator.create(appUser);

    appUser.println();
    appUser.println("=== Your Character ===");
    appUser.println(character.toString());
}
```

### Step 6: Run it

Run the program and create a character. Verify that:
- The name is read correctly
- The class selection works via numbered menu
- Stat points are validated (can't go below 1, can't exceed total)
- The final character is displayed correctly

## Example Session

```
Enter your character's name: Aldric
1 -> Warrior
2 -> Mage
3 -> Rogue

Choose your class: 1

You have 15 points to distribute. Each stat must be at least 1.

Strength (1-10, 15 points remaining): 8
Agility (1-10, 6 points remaining): 5
Intelligence gets the remaining 2 points.

=== Your Character ===
Name: Aldric
Class: Warrior
Strength: 8
Agility: 5
Intelligence: 2
```

## Think About This

- `PlayerCharacter` is a **record** — it holds data. It has no behavior, no methods that do things. It's a data carrier.
- `CharacterCreator.create()` uses an **interface** (`TextAppUser`) for behavior. It doesn't know or care whether the user is typing on a keyboard, reading from a file, or running a test script.
- The method signature `PlayerCharacter create(TextAppUser user)` tells the whole story: behavior in, data out. This is the records-and-interfaces pattern you will use throughout this course.
- You could test this with `ScriptedTextUser` from the in-class exercises — script the answers and verify the returned record has the expected values.

## Bonus Challenge

Add a confirmation step. After displaying the character sheet, ask "Are you happy with this character?" using `choose()` with options "Yes" and "No, start over". If they choose no, loop back and let them create the character again. Return the final confirmed character.
