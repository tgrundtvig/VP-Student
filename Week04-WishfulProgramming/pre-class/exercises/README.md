# Pre-Class Exercises: Wishful Programming in Action

These exercises teach you to design top-down using "wishful programming" - writing code as if everything you need already exists, then implementing what's missing.

## Setup

1. Ensure you have Java 21 and Maven installed (see [setup-guide.md](../../../setup-guide.md))
2. Navigate to this directory in your terminal
3. Run `mvn clean test` to see which tests fail

## Exercises

Complete the TODO sections in these files:

### Part 1: The Reward System

The `Reward` interface is already defined. Your task is to implement different reward types.

#### 1. **Reward.java** - The Contract (Already provided)
Read this interface. It defines what ALL rewards can do.

#### 2. **GoldReward.java** - Simple Reward
Implement a reward that gives gold to the player.
- Constructor takes the amount of gold
- `grantTo(Player)` adds gold to the player
- `getDescription()` returns something like "50 gold"

#### 3. **ExperienceReward.java** - Another Simple Reward
Implement a reward that gives experience points.
- Same pattern as GoldReward
- Uses `player.addExperience(amount)`

#### 4. **HealingReward.java** - Reward with Logic
Implement a reward that heals the player.
- Heals by a percentage of max health
- Can't exceed max health

#### 5. **CompositeReward.java** - Combining Rewards
Implement a reward that contains multiple other rewards.
- Demonstrates the power of interfaces
- All sub-rewards granted when this one is granted

### Part 2: The Quest Objective System

#### 6. **QuestObjective.java** - The Contract (Already provided)
Read this interface for quest objectives.

#### 7. **KillCountObjective.java** - Tracking Progress
Implement an objective that tracks monster kills.
- Tracks current count vs required count
- `progress(String event, int amount)` updates when monsters are killed
- `isComplete()` returns true when enough monsters killed

#### 8. **CollectGoldObjective.java** - Another Objective Type
Implement an objective that tracks gold collection.
- Similar pattern to KillCountObjective
- Uses different event type

## Running Tests

```bash
# Run all tests
mvn test

# Run reward tests only
mvn test -Dtest=RewardTest

# Run objective tests only
mvn test -Dtest=QuestObjectiveTest

# Compile without testing
mvn compile
```

## Success Criteria

- [ ] All tests pass
- [ ] No compilation errors
- [ ] You understand how interfaces enable flexible design

## Key Concepts

1. **Design from usage**: The interfaces were designed by thinking "how do I want to USE rewards?" not "how do I implement rewards?"

2. **Records vs Interfaces**:
   - `Player` is a class with behavior (methods that do things)
   - Interfaces define contracts for behavior
   - Use records for pure data without behavior

3. **Composite Pattern**: `CompositeReward` shows how one implementation can contain others

4. **Open for Extension**: Adding new reward types requires NO changes to existing code

## The Wishful Programming Process

1. Write how you WANT to use something
2. Let the compiler tell you what's missing
3. Define interfaces based on what you needed
4. Implement until code compiles
5. Repeat at lower levels

## When You're Done

All tests passing? You've successfully implemented a flexible reward and objective system using wishful programming principles.

Check [verification.md](../verification.md) for reflection questions.

## Hints

- Start with `GoldReward` - it's the simplest
- `CompositeReward` should use a `List<Reward>` internally
- For objectives, the `progress` method receives an event type and amount
- Check the test file to understand exact expectations

## Looking Ahead

In the post-class exercises, you'll design a complete Quest system using wishful programming, defining interfaces yourself before implementing them.
