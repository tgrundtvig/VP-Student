# Post-Class Exercises: Redemption Through Interfaces

Remember the pain of Week 2? Now we rebuild EVERYTHING with interfaces and experience the joy of clean design!

## The Transformation

You'll implement the same features from Week 2:
1. Guild System - No more circular dependencies!
2. Quest System - Fully testable!
3. Crafting System - Clean separation of concerns!
4. NPC System - No more if-else chains!
5. Difficulty System - Strategy pattern with interfaces!

## Exercise Structure

Each exercise includes:
- Interface definitions (contracts)
- Record definitions (data)
- Implementation tasks
- Test requirements

## Exercise 1: Guild System (No Circular Dependencies!)

```java
// Define the contracts
public interface IGuild {
    String getName();
    int getLevel();
    void addMember(IPlayer player);
    void removeMember(IPlayer player);
    int getMemberCount();
    int getAttackBonus();
    int getDefenseBonus();
}

public interface IPlayer {
    String getName();
    int getLevel();
    void joinGuild(IGuild guild);
    void leaveGuild();
    IGuild getGuild();
    PlayerStats getStats();
}

// Data as records
public record PlayerStats(int health, int attack, int defense) {}
public record GuildInfo(String name, int level, int memberCount) {}
```

**Your Task:**
1. Implement `BasicGuild` class
2. Implement `GuildPlayer` class
3. Write tests showing no circular dependency
4. Create mock implementations for testing

## Exercise 2: Quest System (Fully Testable!)

```java
public interface IQuest {
    String getName();
    String getDescription();
    boolean checkCompletion(IPlayer player);
    void complete(IPlayer player);
    QuestRewards getRewards();
}

public interface IQuestObjective {
    String getDescription();
    boolean isComplete(IPlayer player);
    void updateProgress(String event, int amount);
}

// Data records
public record QuestRewards(int gold, int experience, List<String> items) {}
public record QuestProgress(String questId, int current, int required) {}
```

**Your Task:**
1. Implement `KillQuest` (kill X monsters)
2. Implement `CollectionQuest` (collect X items)
3. Create `MockPlayer` for testing quests
4. Write isolated quest tests

## Exercise 3: Crafting System (Clean Separation!)

```java
public interface ICraftingRecipe {
    String getResultItem();
    Map<String, Integer> getRequiredMaterials();
    boolean canCraft(IInventory inventory);
    void craft(IInventory inventory);
}

public interface IInventory {
    void addItem(String item, int quantity);
    void removeItem(String item, int quantity);
    int getQuantity(String item);
    boolean hasItems(Map<String, Integer> items);
}

// Data records
public record CraftingResult(boolean success, String item, String message) {}
public record InventorySlot(String item, int quantity) {}
```

**Your Task:**
1. Implement `SimpleCraftingRecipe`
2. Implement `BasicInventory`
3. Create crafting system that uses interfaces
4. Test crafting without full game setup

## Exercise 4: NPC System (No If-Else Chains!)

```java
public interface INPC {
    String getName();
    void interact(IPlayer player);
    NPCType getType();
}

public interface IMerchant extends INPC {
    List<ShopItem> getInventory();
    void buyFrom(IPlayer player, String item);
    void sellTo(IPlayer player, String item);
}

public interface IQuestGiver extends INPC {
    List<IQuest> getAvailableQuests(IPlayer player);
    void giveQuest(IPlayer player, IQuest quest);
}

// Data records
public record ShopItem(String name, int buyPrice, int sellPrice) {}
public record NPCInfo(String name, NPCType type, String description) {}

public enum NPCType {
    MERCHANT, QUEST_GIVER, TRAINER, INNKEEPER
}
```

**Your Task:**
1. Implement different NPC types
2. No if-else checking NPC type!
3. Use polymorphism for different behaviors
4. Test each NPC type independently

## Exercise 5: Difficulty System (Strategy Pattern!)

```java
public interface IDifficultyStrategy {
    String getName();
    DifficultyModifiers getModifiers();
    int adjustEnemyHealth(int baseHealth);
    int adjustEnemyDamage(int baseDamage);
    int adjustRewardGold(int baseGold);
    int adjustRewardXP(int baseXP);
}

// Data record for modifiers
public record DifficultyModifiers(
    double healthMultiplier,
    double damageMultiplier,
    double goldMultiplier,
    double xpMultiplier
) {}
```

**Your Task:**
1. Implement `EasyDifficulty`, `NormalDifficulty`, `HardDifficulty`
2. Create `DifficultyManager` that uses strategy pattern
3. No if-else checking difficulty level!
4. Test each difficulty independently

## Running the Exercises

```bash
# Compile your code
mvn clean compile

# Run tests
mvn test

# Run specific test
mvn test -Dtest=GuildSystemTest
```

## Comparison with Week 2

For each feature, document:

1. **Lines of Code**: How much shorter is the interface version?
2. **Number of Dependencies**: How many fewer dependencies?
3. **Test Setup**: How much simpler is test setup?
4. **Adding Features**: How easy to add new implementations?
5. **Understanding**: How much clearer is the code?

## The Joy of Clean Design

As you implement these, notice:
- ✅ No circular dependencies
- ✅ Easy to test in isolation
- ✅ No cascading changes
- ✅ No if-else explosions
- ✅ Clear separation of concerns
- ✅ Easy to add new features

## Celebrate Your Success!

After completing these exercises, you've transformed from someone who writes code to someone who designs systems. The difference between Week 2 and Week 3 implementations shows the power of good design!

Compare your Week 2 attempts with these clean interface-based solutions. The difference should be dramatic!