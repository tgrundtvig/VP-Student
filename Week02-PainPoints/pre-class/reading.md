# Pre-Class Reading: When Good Code Goes Bad

## Introduction

Last week, you successfully built a working text-based RPG game. It runs, it's playable, and you should be proud of it! This week, we're going to extend that game with new features. But as we do, we'll discover that working code isn't always good code, and "good enough for now" can become "not good enough for later."

This reading introduces concepts that will help you recognize problems in code structure. Don't worry if these problems seem abstract now - you'll experience them firsthand in this week's exercises.

## Part 1: The Hidden Cost of "It Works"

Consider this scenario: You've built a house. It has walls, a roof, windows, and doors. People can live in it. It works! But then:
- You want to add a second floor, but the foundation can't support it
- You want to move a window, but it's load-bearing somehow
- You want to upgrade the plumbing, but it's tangled with the electrical system
- You want to add a room, but you'd have to rebuild half the house

Software can have the same problems. Code that works today might become a nightmare to modify tomorrow.

## Part 2: Code Smells - Warning Signs in Your Code

"Code smells" are patterns that suggest deeper problems. They don't mean your code is broken, but they indicate it might be hard to maintain or extend. Here are the most relevant ones for our RPG game:

### 1. Tight Coupling

**What it is:** When classes depend heavily on each other's internal details.

**Example from our game:**
```java
public class Player {
    private ArrayList<Monster> monstersKilled = new ArrayList<>();

    public void fightMonster(Monster monster) {
        // Player directly manipulates Monster's internal state
        monster.health -= this.attackPower;
        if (monster.health <= 0) {
            this.gold += monster.goldDrop;
            monster.isDead = true;
            monstersKilled.add(monster);
        }
    }
}
```

**The problem:** Player knows too much about Monster's internals. What if we want to add armor to monsters? Or different types of damage? We'd have to change Player code.

### 2. Code Duplication

**What it is:** The same logic appearing in multiple places.

**Example from our game:**
```java
// In Player class
public void healWithPotion() {
    if (inventory.containsKey("Potion") && inventory.get("Potion") > 0) {
        health += 50;
        inventory.put("Potion", inventory.get("Potion") - 1);
    }
}

// In Monster class (if we want monsters to use potions too)
public void healWithPotion() {
    if (inventory.containsKey("Potion") && inventory.get("Potion") > 0) {
        health += 50;
        inventory.put("Potion", inventory.get("Potion") - 1);
    }
}
```

**The problem:** If we want to change how potions work, we need to remember to change it everywhere.

### 3. Long Methods

**What it is:** Methods that do too many things.

**Example from our game:**
```java
public void shopMenu(Scanner scanner) {
    System.out.println("Welcome to the shop!");
    System.out.println("Your gold: " + player.getGold());
    System.out.println("1. Potion - 10 gold");
    System.out.println("2. Sword upgrade - 50 gold");
    System.out.println("3. Exit shop");

    int choice = scanner.nextInt();
    scanner.nextLine();

    if (choice == 1) {
        if (player.getGold() >= 10) {
            player.setGold(player.getGold() - 10);
            // Add potion to inventory...
            System.out.println("You bought a potion!");
        } else {
            System.out.println("Not enough gold!");
        }
    } else if (choice == 2) {
        if (player.getGold() >= 50) {
            player.setGold(player.getGold() - 50);
            player.setAttackPower(player.getAttackPower() + 10);
            System.out.println("Sword upgraded!");
        } else {
            System.out.println("Not enough gold!");
        }
    }
    // And more...
}
```

**The problem:** This method displays the menu, processes input, handles purchases, manages inventory, and updates player stats. How do we test just the purchase logic?

### 4. Feature Envy

**What it is:** When a class uses another class's data more than its own.

**Example from our game:**
```java
public class BattleCalculator {
    public int calculateDamage(Player player, Monster monster) {
        int damage = player.getAttackPower();
        damage += player.getWeapon().getDamage();
        damage -= monster.getArmor();
        if (monster.getType().equals("Ghost")) {
            damage = damage / 2;  // Ghosts take half damage
        }
        return damage;
    }
}
```

**The problem:** BattleCalculator is reaching deep into Player and Monster. Maybe this logic belongs elsewhere?

## Part 3: The Testing Nightmare

Testing becomes difficult when:

### 1. Everything Depends on Everything

If your Player class directly creates Monsters, how do you test Player without also testing Monster?

```java
public class Player {
    public void startBattle() {
        Monster monster = new Monster("Goblin", 100, 10);  // Creates its own monster
        // How do we test with different monsters?
    }
}
```

### 2. Hidden Dependencies

When classes use System.out.println or Scanner directly:

```java
public class Game {
    public void play() {
        Scanner scanner = new Scanner(System.in);  // Hidden dependency
        System.out.println("Enter your name:");    // Hidden dependency
        // How do we test this without human input?
    }
}
```

### 3. State Changes Everywhere

When methods change multiple objects:

```java
public void completeQuest(Quest quest, Player player) {
    quest.setCompleted(true);
    player.addExperience(quest.getXpReward());
    player.addGold(quest.getGoldReward());
    player.getCompletedQuests().add(quest);
    // Four different objects changed! Hard to verify everything.
}
```

## Part 4: The Cascade Effect

One of the most frustrating problems is when a small change requires updates throughout your codebase.

**Scenario:** You want to add a "mana" resource for magic spells.

**What might need to change:**
1. Player class - add mana field
2. Monster class - monsters might have mana too
3. Battle logic - check mana before casting spells
4. Shop system - sell mana potions
5. Save/Load system - save and load mana values
6. Display methods - show mana in UI
7. All existing tests - update to include mana

This is exhausting and error-prone!

## Part 5: Cohesion - Things That Belong Together

**High Cohesion** means that related things are grouped together.
**Low Cohesion** means that a class does many unrelated things.

**Low Cohesion Example:**
```java
public class Player {
    // Player attributes
    private String name;
    private int health;

    // Battle system
    public void attack(Monster monster) { }

    // Shop system
    public void buyItem(String item) { }

    // Save system
    public void saveToFile(String filename) { }

    // Display system
    public void printStats() { }
}
```

The Player class is trying to be everything! It's a fighter, a shopper, a file manager, and a display system.

## Part 6: Why This Matters

You might think: "But my code works! Why should I care about these 'smells'?"

### The Cost of Change

- **Week 1:** Adding a feature takes 10 minutes
- **Week 4:** Adding a feature takes 1 hour (you need to remember how everything connects)
- **Week 8:** Adding a feature takes 4 hours (you break things and hunt bugs)
- **Week 12:** You're afraid to touch the code at all

### The Testing Problem

- Without good structure, tests become:
  - Hard to write
  - Fragile (break when unrelated things change)
  - Slow (test everything at once)
  - Unreliable (depend on external factors)

### The Collaboration Problem

When code is tightly coupled:
- Two people can't work on different features without conflicts
- New team members need to understand everything before changing anything
- Code reviews become: "I don't know, does this break something?"

## Part 7: What's Coming Next

This week, you'll experience these problems firsthand. It will be frustrating - that's the point!

Next week, we'll introduce **interfaces** - contracts that define what classes can do without specifying how they do it. This simple concept will solve many of the problems you're about to face.

But first, you need to feel the pain. Otherwise, the solution won't make sense.

## Key Takeaways

1. **Working code isn't always good code** - "It works" is just the minimum bar
2. **Code smells are warning signs** - They predict future problems
3. **Tight coupling makes change hard** - When everything depends on everything
4. **Testing reveals design problems** - If it's hard to test, it's probably poorly designed
5. **Pain is a teacher** - You'll appreciate good design after experiencing bad design

## Reflection Questions

As you work through this week's exercises, ask yourself:

1. How many files do I need to change to add one feature?
2. Can I test one part without testing everything?
3. If I wanted to completely change how combat works, what would break?
4. Am I copying and pasting code? Why?
5. Do my classes have a single, clear responsibility?

## Preparing for Class

Before class, try the pre-class exercises where you'll:
1. Extend your Character class with special abilities
2. Create different game modes
3. Write tests for complex scenarios

You'll quickly discover that what seemed simple in Week 1 becomes complicated when you need to extend it!

Remember: Struggling with these exercises is expected and valuable. Document your struggles - they're learning opportunities!