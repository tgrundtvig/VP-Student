# Post-Class Exercises: Building a Complete Quest System

Now that you understand wishful programming, it's time to build a complete quest system from scratch. You'll use the reward and objective implementations from pre-class to create a full-featured quest management system.

## Setup

1. Navigate to this directory in your terminal
2. Run `mvn clean test` to see which tests fail
3. This project includes your pre-class implementations

## Exercises

### Exercise 1: Quest Class

Implement the `Quest` class that represents a complete quest:
- Name and description
- List of objectives (using the `QuestObjective` interface)
- Reward to grant on completion (using the `Reward` interface)

**File:** `Quest.java`

**Key Methods:**
- `isComplete()` - returns true only when ALL objectives are fulfilled
- `complete(Player)` - grants the reward if quest is complete

### Exercise 2: QuestLog Class

Implement the `QuestLog` class that manages a player's quests:
- Track active and completed quests
- Check and auto-complete ready quests
- Move quests from active to completed

**File:** `QuestLog.java`

**Key Methods:**
- `addQuest(Quest)` - add a new active quest
- `checkAndCompleteQuests(Player)` - check all active quests, complete ready ones

### Exercise 3: QuestEventHandler (Optional Challenge)

If you finish early, implement the `QuestEventHandler` that connects game events to quest progress:

**File:** `QuestEventHandler.java`

This demonstrates how events flow through the system:
1. Player kills a monster
2. Event handler receives the event
3. Handler notifies relevant quest objectives
4. Objectives update their progress

## Running Tests

```bash
# Run all tests
mvn test

# Run Quest tests only
mvn test -Dtest=QuestTest

# Run QuestLog tests only
mvn test -Dtest=QuestLogTest

# Compile without testing
mvn compile
```

## Success Criteria

- [ ] All QuestTest tests pass
- [ ] All QuestLogTest tests pass
- [ ] Code compiles without errors
- [ ] You understand how interfaces enable the design

## Design Observations

As you implement, notice:

1. **Interface Reuse**: `Quest` uses `QuestObjective` and `Reward` interfaces you already implemented
2. **Loose Coupling**: `QuestLog` doesn't know about specific quest types
3. **Easy Testing**: Tests use simple implementations without complex setup
4. **Open/Closed**: Adding new objective or reward types requires NO changes to `Quest` or `QuestLog`

## Hints

### For Quest:
- Store objectives as `List<QuestObjective>`
- `isComplete()` can use a stream: `objectives.stream().allMatch(QuestObjective::isFulfilled)`
- Don't grant rewards if quest isn't complete

### For QuestLog:
- Use `ArrayList` for both lists
- When checking quests, be careful not to modify a list while iterating
- Collect completed quests first, then move them

### For QuestEventHandler (if attempting):
- The handler needs access to the quest log
- Route events to appropriate objectives based on event type

## Self-Assessment

When complete, ask yourself:

1. How many lines of code did you write vs. how much functionality you got?
2. If you needed a new objective type (e.g., "Visit Location"), what would you change?
3. How easy would it be to add quest chains (quests that unlock other quests)?
4. Could you test the Quest class without a real Player? How?

## Extension Ideas

Finished everything? Try these challenges:

1. **Quest Chains**: Quests that unlock when another quest completes
2. **Time-Limited Quests**: Quests that expire after a certain time
3. **Repeatable Quests**: Quests that can be completed multiple times
4. **Quest Categories**: Group quests by type (main story, side quest, daily)

## The Big Picture

This quest system demonstrates the full power of interface-first design:

```
                    Quest System
                         │
        ┌────────────────┼────────────────┐
        │                │                │
   QuestLog          Quest           QuestEventHandler
        │                │                │
        │       ┌────────┴────────┐       │
        │       │                 │       │
   List<Quest>  QuestObjective   Reward   │
                     │             │       │
              ┌──────┴──────┐   ┌──┴──┐   │
              │             │   │     │   │
        KillCount    CollectGold Gold XP Healing
```

Each component is:
- Independent and testable
- Replaceable without affecting others
- Extensible without modification

This is professional software design!
