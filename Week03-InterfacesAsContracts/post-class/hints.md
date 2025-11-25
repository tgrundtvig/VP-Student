# Hints for Post-Class Exercises

## General Interface Guidelines

### Golden Rules
1. **Declare variables as interface types**, not concrete types
2. **Keep interfaces small and focused** (Interface Segregation Principle)
3. **Use records for data**, interfaces for behavior
4. **Program to interfaces**, not implementations
5. **Create mock implementations** for testing

## Exercise 1: Guild System Hints

### Breaking the Circular Dependency
```java
// Both classes depend on INTERFACES, not each other!
public class BasicGuild implements IGuild {
    private List<IPlayer> members;  // Interface type!

    public void addMember(IPlayer player) {
        members.add(player);
        // No circular dependency!
    }
}

public class GuildPlayer implements IPlayer {
    private IGuild guild;  // Interface type!

    public void joinGuild(IGuild guild) {
        this.guild = guild;
        guild.addMember(this);
    }
}
```

### Testing in Isolation
```java
// Create a mock player for testing guilds
class MockPlayer implements IPlayer {
    private String name;
    private IGuild guild;

    public MockPlayer(String name) {
        this.name = name;
    }

    // Simple implementations for testing
}

// Now test guild without real players!
@Test
public void testGuild() {
    IGuild guild = new BasicGuild("Warriors");
    IPlayer mockPlayer = new MockPlayer("Test");
    guild.addMember(mockPlayer);
    assertEquals(1, guild.getMemberCount());
}
```

## Exercise 2: Quest System Hints

### Designing Quest Objectives
```java
public class KillObjective implements IQuestObjective {
    private String targetType;
    private int required;
    private int current;

    public boolean isComplete(IPlayer player) {
        return current >= required;
    }

    public void updateProgress(String event, int amount) {
        if (event.equals("KILL_" + targetType)) {
            current += amount;
        }
    }
}
```

### Composing Complex Quests
```java
public class ComplexQuest implements IQuest {
    private List<IQuestObjective> objectives;

    public boolean checkCompletion(IPlayer player) {
        return objectives.stream()
            .allMatch(obj -> obj.isComplete(player));
    }
}
```

### Testing Quests Without Game
```java
@Test
public void testQuestCompletion() {
    IQuest quest = new KillQuest("Goblin Slayer", "goblin", 5);
    IPlayer mockPlayer = new MockPlayer();

    // Simulate progress
    quest.updateProgress("KILL_goblin", 5);

    assertTrue(quest.checkCompletion(mockPlayer));
}
```

## Exercise 3: Crafting System Hints

### Clean Inventory Interface
```java
public class BasicInventory implements IInventory {
    private Map<String, Integer> items = new HashMap<>();

    public boolean hasItems(Map<String, Integer> required) {
        return required.entrySet().stream()
            .allMatch(entry ->
                getQuantity(entry.getKey()) >= entry.getValue()
            );
    }

    public void removeItems(Map<String, Integer> items) {
        items.forEach((item, quantity) ->
            removeItem(item, quantity)
        );
    }
}
```

### Crafting Recipe Pattern
```java
public class SimpleCraftingRecipe implements ICraftingRecipe {
    private String result;
    private Map<String, Integer> materials;

    public void craft(IInventory inventory) {
        if (canCraft(inventory)) {
            // Remove materials
            inventory.removeItems(materials);
            // Add result
            inventory.addItem(result, 1);
        }
    }

    public boolean canCraft(IInventory inventory) {
        return inventory.hasItems(materials);
    }
}
```

## Exercise 4: NPC System Hints

### Polymorphic NPC Behavior
```java
// No if-else needed!
public class Game {
    private List<INPC> npcs;

    public void interactWithNPC(IPlayer player, int npcIndex) {
        INPC npc = npcs.get(npcIndex);
        npc.interact(player);  // Polymorphism handles the right behavior!
    }
}
```

### Specific NPC Implementations
```java
public class MerchantNPC implements IMerchant {
    private List<ShopItem> inventory;

    @Override
    public void interact(IPlayer player) {
        // Show shop interface
        System.out.println("Welcome to my shop!");
        // Merchant-specific interaction
    }

    @Override
    public void buyFrom(IPlayer player, String item) {
        // Merchant-specific buying logic
    }
}

public class QuestGiverNPC implements IQuestGiver {
    private List<IQuest> quests;

    @Override
    public void interact(IPlayer player) {
        // Show available quests
        System.out.println("I have quests for you!");
        // Quest giver-specific interaction
    }
}
```

### Type-Safe NPC Handling
```java
// If you need specific NPC type behavior
if (npc instanceof IMerchant merchant) {
    merchant.buyFrom(player, "sword");
} else if (npc instanceof IQuestGiver questGiver) {
    questGiver.giveQuest(player, quest);
}
// But usually, polymorphism is enough!
```

## Exercise 5: Difficulty System Hints

### Strategy Pattern Implementation
```java
public class EasyDifficulty implements IDifficultyStrategy {
    private static final DifficultyModifiers MODIFIERS =
        new DifficultyModifiers(0.5, 0.5, 2.0, 1.5);

    @Override
    public int adjustEnemyHealth(int baseHealth) {
        return (int)(baseHealth * MODIFIERS.healthMultiplier());
    }

    @Override
    public int adjustRewardGold(int baseGold) {
        return (int)(baseGold * MODIFIERS.goldMultiplier());
    }
}
```

### Using the Strategy
```java
public class Game {
    private IDifficultyStrategy difficulty;

    public void setDifficulty(IDifficultyStrategy difficulty) {
        this.difficulty = difficulty;  // Can change at runtime!
    }

    public Enemy createEnemy(String type, int baseHealth, int baseDamage) {
        int adjustedHealth = difficulty.adjustEnemyHealth(baseHealth);
        int adjustedDamage = difficulty.adjustEnemyDamage(baseDamage);
        return new Enemy(type, adjustedHealth, adjustedDamage);
    }
}
```

### Testing Different Difficulties
```java
@Test
public void testDifficultyStrategies() {
    IDifficultyStrategy easy = new EasyDifficulty();
    IDifficultyStrategy hard = new HardDifficulty();

    assertEquals(50, easy.adjustEnemyHealth(100));   // 50% health
    assertEquals(150, hard.adjustEnemyHealth(100));  // 150% health

    assertEquals(200, easy.adjustRewardGold(100));   // 2x gold
    assertEquals(100, hard.adjustRewardGold(100));   // normal gold
}
```

## Common Interface Pitfalls to Avoid

### 1. Making Interfaces Too Large
```java
// BAD - Interface doing too much
public interface IGameEntity {
    void move();
    void attack();
    void defend();
    void trade();
    void talk();
    // Too many responsibilities!
}

// GOOD - Focused interfaces
public interface IMovable {
    void move();
}
public interface ICombatant {
    void attack();
    void defend();
}
```

### 2. Exposing Implementation Details
```java
// BAD - Exposes ArrayList specifically
public interface IBadGuild {
    ArrayList<IPlayer> getMembers();  // Don't specify ArrayList!
}

// GOOD - Uses interface type
public interface IGoodGuild {
    List<IPlayer> getMembers();  // List interface!
}
```

### 3. Not Using Interface Types
```java
// BAD - Using concrete type
BasicGuild guild = new BasicGuild();

// GOOD - Using interface type
IGuild guild = new BasicGuild();
```

## Testing Tips

### Create Test Doubles
```java
// Stub - Returns predefined values
class StubPlayer implements IPlayer {
    public String getName() { return "TestPlayer"; }
    // Minimal implementation
}

// Mock - Tracks interactions
class MockPlayer implements IPlayer {
    private int attackCount = 0;

    public void attack(ICombatant target) {
        attackCount++;
    }

    public int getAttackCount() { return attackCount; }
}
```

### Test One Thing at a Time
```java
// Test JUST the guild logic
@Test
public void testGuildBonus() {
    IGuild guild = new BasicGuild("Test", 5);
    assertEquals(5, guild.getAttackBonus());
    // Don't test players, combat, etc. here!
}
```

## Remember the Transformation

As you work through these exercises, keep comparing to Week 2:
- How much easier is testing now?
- How much cleaner is the code?
- How much more flexible is the design?
- How confident are you in making changes?

The difference should be dramatic!