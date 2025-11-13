# Pre-Class Reading: Java Fundamentals Review

This reading refreshes essential Java concepts needed for Week 1. If you're comfortable with basic Java, this should be a quick review.

## Classes and Objects

### What is a Class?

A class is a blueprint for creating objects. It defines:
- **Fields** (data the object holds)
- **Methods** (actions the object can perform)
- **Constructors** (how to create the object)

```java
public class Player {
    // Fields - data
    private String name;
    private int health;
    private int attackPower;

    // Constructor - how to create a Player
    public Player(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    // Methods - actions
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
```

### Creating and Using Objects

```java
// Create a Player object
Player hero = new Player("Aragorn", 100, 15);

// Use the object
System.out.println(hero.getName() + " has " + hero.getHealth() + " health");

hero.takeDamage(25);
System.out.println("After damage: " + hero.getHealth() + " health");

if (hero.isAlive()) {
    System.out.println(hero.getName() + " is still fighting!");
}
```

## Encapsulation

Keep fields `private` and access them through `public` methods (getters/setters). This protects data integrity.

**Why?**
```java
// Bad - direct field access
player.health = -50;  // Invalid state!

// Good - method with validation
public void takeDamage(int damage) {
    this.health -= damage;
    if (this.health < 0) {
        this.health = 0;  // Prevent negative health
    }
}
```

## Collections

### ArrayList

A resizable array for storing multiple objects.

```java
import java.util.ArrayList;
import java.util.List;

// Create a list of monsters
List<Monster> monsters = new ArrayList<>();

// Add monsters
monsters.add(new Monster("Goblin", 30, 5));
monsters.add(new Monster("Orc", 50, 10));
monsters.add(new Monster("Dragon", 200, 25));

// Access by index
Monster firstMonster = monsters.get(0);

// Iterate over all
for (Monster monster : monsters) {
    System.out.println(monster.getName());
}

// Remove monsters
monsters.remove(0);  // Remove first
monsters.removeIf(m -> !m.isAlive());  // Remove dead monsters

// Size
int count = monsters.size();
```

### HashMap

Store key-value pairs for quick lookups.

```java
import java.util.HashMap;
import java.util.Map;

// Create an inventory (item name → quantity)
Map<String, Integer> inventory = new HashMap<>();

// Add items
inventory.put("Health Potion", 3);
inventory.put("Sword", 1);
inventory.put("Gold", 50);

// Get items
int potions = inventory.get("Health Potion");  // 3
int swords = inventory.getOrDefault("Shield", 0);  // 0 (not found)

// Check if exists
if (inventory.containsKey("Sword")) {
    System.out.println("You have a sword!");
}

// Update
inventory.put("Gold", inventory.get("Gold") + 25);  // Add 25 gold

// Iterate
for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

## Methods

### Basic Method Structure

```java
public returnType methodName(parameters) {
    // code
    return value;  // if not void
}
```

### Examples

```java
// Method with no parameters, returns int
public int calculateTotalHealth() {
    return baseHealth + bonusHealth;
}

// Method with parameters, returns boolean
public boolean canAfford(String item, int price) {
    return gold >= price;
}

// Method that returns nothing (void)
public void displayStatus() {
    System.out.println("Health: " + health);
    System.out.println("Attack: " + attackPower);
}

// Method that modifies state
public void heal(int amount) {
    health += amount;
    if (health > maxHealth) {
        health = maxHealth;
    }
}
```

## Control Flow

### if-else

```java
if (health <= 0) {
    System.out.println("You died!");
} else if (health < 20) {
    System.out.println("You're badly wounded!");
} else {
    System.out.println("You're healthy.");
}
```

### while loops

```java
while (player.isAlive() && monster.isAlive()) {
    // Combat continues
    player.attack(monster);
    if (monster.isAlive()) {
        monster.attack(player);
    }
}
```

### for loops

```java
// Traditional for loop
for (int i = 0; i < monsters.size(); i++) {
    Monster monster = monsters.get(i);
    System.out.println(monster.getName());
}

// Enhanced for loop (foreach)
for (Monster monster : monsters) {
    System.out.println(monster.getName());
}
```

## User Input

### Using Scanner

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);

// Read a line of text
System.out.print("Enter your name: ");
String name = scanner.nextLine();

// Read an integer
System.out.print("Choose action (1-3): ");
int choice = scanner.nextInt();
scanner.nextLine();  // Consume newline

// Read with validation
int health = -1;
while (health < 0 || health > 100) {
    System.out.print("Enter health (0-100): ");
    if (scanner.hasNextInt()) {
        health = scanner.nextInt();
    } else {
        scanner.next();  // Clear invalid input
    }
}

scanner.close();
```

## Simple Game Design

### Game Loop Pattern

Most games follow this pattern:

```java
public void runGame() {
    // 1. Initialize
    setupGame();

    // 2. Game loop
    while (!isGameOver()) {
        // a. Display current state
        displayStatus();

        // b. Get player input
        String action = getPlayerAction();

        // c. Process action
        processAction(action);

        // d. Update game state
        updateGameState();

        // e. Check win/loss conditions
        checkGameOver();
    }

    // 3. End game
    displayResults();
}
```

### Example: Simple Combat

```java
public class SimpleCombatGame {
    private Player player;
    private Monster monster;
    private Scanner scanner;

    public SimpleCombatGame() {
        player = new Player("Hero", 100, 15);
        monster = new Monster("Goblin", 30, 5);
        scanner = new Scanner(System.in);
    }

    public void play() {
        System.out.println("A " + monster.getName() + " appears!");

        while (player.isAlive() && monster.isAlive()) {
            // Show status
            System.out.println("\nYour health: " + player.getHealth());
            System.out.println(monster.getName() + " health: " + monster.getHealth());

            // Get action
            System.out.print("(A)ttack or (R)un? ");
            String action = scanner.nextLine().toLowerCase();

            // Process
            if (action.equals("a")) {
                // Player attacks
                int damage = player.getAttackPower();
                monster.takeDamage(damage);
                System.out.println("You deal " + damage + " damage!");

                // Monster counter-attacks
                if (monster.isAlive()) {
                    damage = monster.getAttackPower();
                    player.takeDamage(damage);
                    System.out.println(monster.getName() + " deals " + damage + " damage!");
                }
            } else if (action.equals("r")) {
                System.out.println("You ran away!");
                break;
            }
        }

        // Game over
        if (!player.isAlive()) {
            System.out.println("You died!");
        } else if (!monster.isAlive()) {
            System.out.println("Victory! You defeated the " + monster.getName());
        }
    }
}
```

## Key Takeaways

Before class, make sure you understand:

1. **Classes define blueprints**, objects are instances
2. **Encapsulation** protects data with private fields and public methods
3. **ArrayList** stores ordered collections, **HashMap** stores key-value pairs
4. **Methods** define behavior and can return values or modify state
5. **Game loops** repeatedly get input, update state, check conditions
6. **Scanner** reads user input from console

## What's Next?

Complete the pre-class exercises to practice these concepts. The exercises have automated tests - when they all pass (green ✅), you're ready for class!

In class, we'll use these fundamentals to build a more complex game together.

## Additional Resources

If you need more review:

- [Official Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [Java Collections Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)
- [Scanner Documentation](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Scanner.html)

Don't get overwhelmed - the exercises will reinforce these concepts!
