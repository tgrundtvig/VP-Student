# Pre-Class Reading: Interfaces - The Game Changer

## Introduction

Last week, you struggled. You experienced tight coupling, circular dependencies, testing nightmares, and cascading changes. This week, we solve ALL of those problems with one elegant concept: **interfaces**.

An interface is a contract. It says WHAT something can do without saying HOW it does it. This simple separation changes everything.

## Part 1: What Is an Interface?

### The Contract Metaphor

Imagine hiring a plumber. You don't care HOW they fix your sink - you care that they CAN fix it. The contract might say:
- Must be able to fix leaks
- Must be able to unclog drains
- Must be able to install fixtures

Different plumbers might use different tools or techniques, but they all fulfill the same contract.

In Java, an interface is exactly this - a contract:

```java
public interface IPlumber {
    void fixLeak(Pipe pipe);
    void unclogDrain(Drain drain);
    void installFixture(Fixture fixture);
}
```

Any class that implements this interface MUST provide these capabilities:

```java
public class MasterPlumber implements IPlumber {
    public void fixLeak(Pipe pipe) {
        // One way to fix a leak
    }
    // ... other methods
}

public class ApprentricePlumber implements IPlumber {
    public void fixLeak(Pipe pipe) {
        // Different way to fix a leak
    }
    // ... other methods
}
```

## Part 2: How Interfaces Solve Week 2's Problems

### Problem 1: Tight Coupling
**Week 2 Problem:**
```java
public class Player {
    private Guild guild;  // Player depends on concrete Guild class

    public Player(Guild guild) {
        this.guild = guild;
    }
}
```

**Week 3 Solution:**
```java
public interface IGuild {
    String getName();
    int getBonus();
}

public class Player {
    private IGuild guild;  // Player depends on IGuild interface

    public Player(IGuild guild) {
        this.guild = guild;
    }
}
```

Now Player doesn't care WHAT kind of guild it is, just that it fulfills the IGuild contract!

### Problem 2: Circular Dependencies
**Week 2 Problem:**
```java
public class Player {
    private Guild guild;  // Player needs Guild
}

public class Guild {
    private List<Player> members;  // Guild needs Player - CIRCULAR!
}
```

**Week 3 Solution:**
```java
public interface IPlayer {
    String getName();
    int getLevel();
}

public interface IGuild {
    void addMember(IPlayer player);
    List<IPlayer> getMembers();
}

public class Player implements IPlayer {
    private IGuild guild;  // Depends on interface
}

public class Guild implements IGuild {
    private List<IPlayer> members;  // Depends on interface
}
```

No more circular dependency! Both depend on interfaces, not each other.

### Problem 3: Testing Nightmares
**Week 2 Problem:**
```java
// To test combat, need real Player, real Monster, real everything!
@Test
public void testCombat() {
    Player player = new Player("Test", 100, 10);  // Need real player
    Monster monster = new Monster("Goblin", 50, 5);  // Need real monster
    // Can't test combat logic in isolation
}
```

**Week 3 Solution:**
```java
public interface ICombatant {
    int getHealth();
    int getAttackPower();
    void takeDamage(int amount);
}

// Create mock for testing
public class MockCombatant implements ICombatant {
    private int health;
    private int attackPower;

    public MockCombatant(int health, int attackPower) {
        this.health = health;
        this.attackPower = attackPower;
    }
    // Simple implementation for testing
}

@Test
public void testCombat() {
    ICombatant attacker = new MockCombatant(100, 10);
    ICombatant defender = new MockCombatant(50, 5);
    // Test combat logic in complete isolation!
}
```

### Problem 4: If-Else Explosions
**Week 2 Problem:**
```java
public void playGameMode(String mode) {
    if (mode.equals("STORY")) {
        // Story logic
    } else if (mode.equals("SURVIVAL")) {
        // Survival logic
    } else if (mode.equals("ARENA")) {
        // Arena logic
    }
    // Adding new mode = modifying this method
}
```

**Week 3 Solution:**
```java
public interface IGameMode {
    void initialize();
    void playRound();
    boolean isComplete();
    String getResult();
}

public class StoryMode implements IGameMode {
    public void playRound() {
        // Story-specific logic
    }
}

public class SurvivalMode implements IGameMode {
    public void playRound() {
        // Survival-specific logic
    }
}

// No if-else needed!
public void playGame(IGameMode mode) {
    mode.initialize();
    while (!mode.isComplete()) {
        mode.playRound();
    }
    System.out.println(mode.getResult());
}
```

## Part 3: Records vs Interfaces - A Critical Distinction

### Records: For Data Without Behavior

Records are perfect for immutable data containers:

```java
// Data about a player - no behavior
public record PlayerData(
    String name,
    int level,
    int experience,
    int health,
    int maxHealth
) {}

// Data about a quest - no behavior
public record QuestData(
    String name,
    String description,
    int goldReward,
    int xpReward
) {}
```

### Interfaces: For Behavior Contracts

Interfaces define what something can DO:

```java
// Behavior contract for a player
public interface IPlayer {
    void attack(ICombatant target);
    void useAbility(IAbility ability);
    void completeQuest(IQuest quest);
    PlayerData getData();  // Can return record for data
}

// Behavior contract for a quest
public interface IQuest {
    boolean checkCompletion(IPlayer player);
    void grantRewards(IPlayer player);
    QuestData getData();  // Can return record for data
}
```

### The Power of Combining Both

```java
public class Player implements IPlayer {
    private PlayerData data;  // Record holds the data
    private IInventory inventory;  // Interface for inventory behavior

    public void attack(ICombatant target) {
        // Implementation using data and other interfaces
        int damage = data.level() * 10;  // Use record data
        target.takeDamage(damage);
    }

    public PlayerData getData() {
        return data;  // Return immutable data
    }
}
```

## Part 4: Programming to Interfaces, Not Implementations

### The Golden Rule

**Always declare variables as interfaces when possible:**

```java
// YES - Programming to interface
IPlayer player = new WarriorPlayer();
IInventory inventory = new BasicInventory();
List<IQuest> quests = new ArrayList<>();

// NO - Programming to implementation
WarriorPlayer player = new WarriorPlayer();
BasicInventory inventory = new BasicInventory();
ArrayList<IQuest> quests = new ArrayList<>();
```

### Why This Matters

When you program to interfaces, you can change implementations without changing code:

```java
public class Game {
    private IPlayer player;
    private IGameMode mode;

    public Game(IPlayer player, IGameMode mode) {
        this.player = player;
        this.mode = mode;
    }
}

// Can use ANY implementation
Game game1 = new Game(new WarriorPlayer(), new StoryMode());
Game game2 = new Game(new MagePlayer(), new SurvivalMode());
Game testGame = new Game(new MockPlayer(), new TestMode());
```

## Part 5: The Interface Segregation Principle

### Keep Interfaces Small and Focused

Instead of one giant interface:

```java
// BAD - Too many responsibilities
public interface IGameEntity {
    void move();
    void attack();
    void takeDamage();
    void buy();
    void sell();
    void talk();
    void giveQuest();
}
```

Create multiple focused interfaces:

```java
// GOOD - Focused interfaces
public interface IMovable {
    void move(Position newPosition);
}

public interface ICombatant {
    void attack(ICombatant target);
    void takeDamage(int amount);
}

public interface IMerchant {
    void buy(IItem item, IPlayer buyer);
    void sell(IItem item, IPlayer seller);
}

// Classes implement only what they need
public class Player implements IMovable, ICombatant {
    // Player can move and fight
}

public class ShopKeeper implements IMerchant {
    // ShopKeeper can only trade
}

public class BossMonster implements IMovable, ICombatant, IMerchant {
    // Boss can do everything!
}
```

## Part 6: The Power of Multiple Implementations

One interface can have many implementations:

```java
public interface ISaveSystem {
    void save(GameState state);
    GameState load(String saveId);
}

// Different ways to save
public class FileSaveSystem implements ISaveSystem {
    public void save(GameState state) {
        // Save to file
    }
}

public class DatabaseSaveSystem implements ISaveSystem {
    public void save(GameState state) {
        // Save to database
    }
}

public class CloudSaveSystem implements ISaveSystem {
    public void save(GameState state) {
        // Save to cloud
    }
}

// Game doesn't care which one!
public class Game {
    private ISaveSystem saveSystem;

    public Game(ISaveSystem saveSystem) {
        this.saveSystem = saveSystem;  // Any implementation works
    }
}
```

## Part 7: Default Methods in Interfaces

Java allows default implementations in interfaces:

```java
public interface ICombatant {
    int getHealth();
    int getAttackPower();

    // Default implementation
    default boolean isAlive() {
        return getHealth() > 0;
    }

    // Default implementation using other methods
    default void attack(ICombatant target) {
        target.takeDamage(getAttackPower());
    }

    void takeDamage(int amount);  // No default - must implement
}
```

## Key Takeaways

1. **Interfaces are contracts** - They define WHAT, not HOW
2. **Interfaces break coupling** - Classes depend on abstractions
3. **Interfaces enable testing** - Easy to create mocks
4. **Interfaces eliminate if-else** - Polymorphism through different implementations
5. **Records for data, Interfaces for behavior** - Clear separation
6. **Program to interfaces** - Declare variables as interface types
7. **Keep interfaces focused** - Single responsibility
8. **Multiple implementations** - Flexibility and extensibility

## Preparing for Class

In class, we'll rebuild the Week 2 game using interfaces. You'll see:
- How quickly we can add features
- How easy testing becomes
- How clean the code structure is
- How flexible the system becomes

## The Revelation

After this reading, you might already be thinking: "This solves everything!"

You're right. It does.

Welcome to professional software design!