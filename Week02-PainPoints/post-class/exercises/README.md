# Post-Class Exercises: The Breaking Point

These exercises will push your Week 1 game design to its breaking point. You'll implement features that seem simple but will reveal deep structural problems in the code.

## Setup

1. Copy your complete Week 1 game code into this project
2. Try to add each feature below
3. Document the problems you encounter
4. Notice how changes cascade through your codebase

## Exercise 1: Guild System

Add a guild system where players can form teams:

```java
public class Guild {
    private String name;
    private ArrayList<Player> members;
    private Player leader;
    private int guildLevel;
    private int guildBank;  // Shared gold

    // TODO: Implement guild functionality
    // - Players can create/join/leave guilds
    // - Guild members share certain benefits
    // - Guild bank for shared resources
    // - Guild-wide buffs/bonuses
}
```

**Challenges you'll face:**
- How does the guild interact with existing Player class?
- How do you handle the guild bank vs player gold?
- How do guild buffs affect combat calculations?
- How do you save/load guild data?

## Exercise 2: Quest System

Implement a quest system with multiple objectives:

```java
public class Quest {
    private String name;
    private String description;
    private ArrayList<QuestObjective> objectives;
    private int goldReward;
    private int xpReward;
    private boolean completed;

    // Different types of objectives:
    // - Kill X monsters of type Y
    // - Collect X items
    // - Reach location Z
    // - Talk to NPC
}
```

**Challenges you'll face:**
- How do you track quest progress?
- How do different game systems update quest objectives?
- How do you handle multiple active quests?
- How do you test quest completion logic?

## Exercise 3: Crafting System

Add crafting that uses inventory items:

```java
public class CraftingRecipe {
    private String resultItem;
    private HashMap<String, Integer> requiredItems;
    private int craftingLevel;

    // TODO: Implement crafting
    // - Check if player has required items
    // - Remove items from inventory
    // - Add crafted item
    // - Handle crafting failures
}
```

**Challenges you'll face:**
- How does crafting interact with the inventory system?
- How do you handle partial crafting (not enough materials)?
- How do you manage recipe discovery?
- Where does crafting logic belong?

## Exercise 4: NPC System with Dialogue

Add NPCs that players can interact with:

```java
public class NPC {
    private String name;
    private ArrayList<String> dialogue;
    private HashMap<String, Integer> shopInventory;
    private ArrayList<Quest> questsToGive;

    // TODO: Implement NPC interactions
    // - Dialogue trees
    // - Shop functionality
    // - Quest giving
    // - Different NPC types (merchant, quest giver, etc.)
}
```

**Challenges you'll face:**
- How do NPCs interact with the existing shop system?
- How do you handle branching dialogue?
- How do NPCs know about player quest progress?
- How do you test NPC interactions?

## Exercise 5: Difficulty Levels

Implement different difficulty settings:

```java
public enum Difficulty {
    EASY,     // Enemies have 50% health, player gets 2x gold
    NORMAL,   // Standard gameplay
    HARD,     // Enemies have 150% health, player gets normal gold
    NIGHTMARE // Enemies have 200% health, player gets 50% gold, no saves
}
```

**Challenges you'll face:**
- How many places need to check difficulty?
- How do you modify enemy stats based on difficulty?
- How do you handle difficulty-specific features (like no saves)?
- Can you change difficulty mid-game?

## Running the Exercises

```bash
# Compile your code
mvn clean compile

# Run tests (most will fail - that's OK!)
mvn test

# Try to run your game with new features
mvn exec:java -Dexec.mainClass="dk.viprogram.week02.Game"
```

## Document Your Pain Points

For each exercise, document:

1. **Files Modified**: How many files did you need to change?
2. **Code Duplication**: Where did you copy-paste code?
3. **Cascading Changes**: What broke when you made changes?
4. **Testing Difficulties**: Which tests were impossible to write?
5. **Coupling Issues**: Where are classes too dependent on each other?

## The Point

Remember: The difficulty you're experiencing is the entire point of this week. You're discovering why professional software development requires better design patterns than "just make it work."

Next week, we'll rebuild this same functionality using interfaces, and you'll be amazed at how much easier it becomes!