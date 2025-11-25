# Hints for Post-Class Exercises

## General Advice

Before diving into specific hints, remember:
- It's OK if your solutions feel messy - that's the point!
- Document every problem you encounter
- Don't try to make it perfect - experience the pain

## Exercise 1: Guild System Hints

### Creating the Guild Class
- Start by making Guild its own class (don't add everything to Player)
- You'll need to modify Player to track guild membership
- Consider: Should a Player know about their Guild, or should Guild track Players?

### Managing Guild Benefits
```java
// You might need something like this in Player:
public int getAttackPower() {
    int basePower = this.attackPower;
    if (this.guild != null) {
        basePower += guild.getAttackBonus();  // But now Player depends on Guild!
    }
    return basePower;
}
```

### The Cascade Problem
- Notice how adding guilds affects:
  - Combat calculations (guild bonuses)
  - Gold management (personal vs guild bank)
  - Save/Load system (need to save guild data)
  - Display methods (show guild info)

### Common Issues You'll Hit
- Circular dependencies (Player knows Guild, Guild knows Players)
- Where does guild battle logic go? In Player? Guild? Game?
- How do you test a Player without creating a Guild?

## Exercise 2: Quest System Hints

### Tracking Quest Progress
The naive approach might be:
```java
// In Quest class
public void checkProgress(String action, String target) {
    for (QuestObjective objective : objectives) {
        if (objective.getType().equals(action) &&
            objective.getTarget().equals(target)) {
            objective.increment();
        }
    }
}

// But now EVERY action in your game needs to notify quests!
```

### The Notification Problem
- When a monster dies, who tells the quest system?
- When an item is picked up, how do quests know?
- You'll end up adding quest checks everywhere:
  ```java
  // In combat code:
  if (monster.getHealth() <= 0) {
      player.addGold(monster.getGoldDrop());
      player.checkQuests("KILL", monster.getType());  // Added line
      // What if we have multiple quest types?
  }
  ```

### Testing Nightmares
- How do you test quest completion without running the entire game?
- How do you test complex objectives without setting up the whole world?

## Exercise 3: Crafting System Hints

### Where Does Crafting Logic Live?
Consider these options (all problematic):

1. **In Player class**: Player becomes even more bloated
2. **In Inventory class**: Inventory shouldn't know about crafting
3. **In new CraftingSystem class**: How does it access inventory?
4. **In Game class**: Game class becomes enormous

### The Inventory Access Problem
```java
// You might try:
public class CraftingSystem {
    public boolean craft(Player player, String recipeName) {
        // Need to check player's inventory
        HashMap<String, Integer> inventory = player.getInventory();
        // Now CraftingSystem is tightly coupled to Player!
    }
}
```

### Recipe Management
- Where do you store all recipes?
- How do you check if player knows a recipe?
- What if recipes have skill requirements?

## Exercise 4: NPC System Hints

### The Dialogue Challenge
Simple approach that quickly gets complex:
```java
public void talkToNPC(NPC npc, Scanner scanner) {
    System.out.println(npc.getGreeting());

    // Show options based on... everything!
    if (player.hasQuest(npc.getQuestName())) {
        System.out.println("1. Turn in quest");
    }
    if (npc.hasNewQuest() && player.meetsRequirements(npc.getQuestReqs())) {
        System.out.println("2. Accept new quest");
    }
    if (npc.hasShop()) {
        System.out.println("3. Browse shop");
    }
    // This method will grow forever!
}
```

### NPC Types Problem
- Different NPCs do different things
- You'll likely end up with lots of if-else:
  ```java
  if (npc.getType().equals("MERCHANT")) {
      // Shop logic
  } else if (npc.getType().equals("QUEST_GIVER")) {
      // Quest logic
  } else if (npc.getType().equals("TRAINER")) {
      // Training logic
  }
  ```

### State Management
- NPCs might have different dialogue based on quest progress
- How do you track what the player has already said?
- Where do you store NPC relationship/reputation?

## Exercise 5: Difficulty System Hints

### The Global State Problem
You'll probably need:
```java
public class Game {
    private static Difficulty currentDifficulty = Difficulty.NORMAL;

    public static Difficulty getDifficulty() {
        return currentDifficulty;
    }
}
```

### Modifications Everywhere
Look how many places need to check difficulty:
```java
// In Monster class:
public int getHealth() {
    int baseHealth = this.health;
    if (Game.getDifficulty() == Difficulty.EASY) {
        return baseHealth / 2;
    } else if (Game.getDifficulty() == Difficulty.HARD) {
        return (int)(baseHealth * 1.5);
    }
    // More conditions...
}

// In Player class:
public void addGold(int amount) {
    if (Game.getDifficulty() == Difficulty.EASY) {
        amount *= 2;
    } else if (Game.getDifficulty() == Difficulty.NIGHTMARE) {
        amount /= 2;
    }
    this.gold += amount;
}

// In Save system:
public void saveGame() {
    if (Game.getDifficulty() == Difficulty.NIGHTMARE) {
        System.out.println("Cannot save on Nightmare difficulty!");
        return;
    }
    // Save logic...
}
```

### Testing Different Difficulties
- How do you test that Easy mode gives 2x gold?
- How do you test monster health scaling?
- You need to set global state for every test!

## Common Problems Across All Exercises

### The Dependency Web
- Guild depends on Player
- Player depends on Guild
- Quest depends on Player, Monster, Item
- CraftingSystem depends on Inventory
- Everything depends on Game
- Game depends on everything

### The Testing Impossibility
- Can't test Guild without Player
- Can't test Quest without entire game state
- Can't test Crafting without Inventory
- Can't test NPC without Player and Quest
- Can't test anything in isolation!

### The Modification Cascade
- Add one feature, modify 10 files
- Change one rule, update 20 locations
- Fix one bug, create three more
- Refactor one class, break everything

## Remember the Goal

The frustration you're feeling is intentional! These hints aren't meant to solve the problems - they're meant to help you experience them fully.

Next week, when we introduce interfaces, you'll see how they solve these exact problems:
- Decouple dependencies
- Enable testing in isolation
- Localize changes
- Separate concerns

Keep pushing through, and document everything! Your struggles today are tomorrow's learning.