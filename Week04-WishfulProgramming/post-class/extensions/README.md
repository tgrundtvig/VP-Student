# Week 4: Extension Challenges

These extensions are optional challenges for students who finish early or want extra practice.

---

## Extension 1: TimedObjective

Create a `TimedObjective` that must be completed within a time limit.

```java
public class TimedObjective implements QuestObjective {
    // Must be fulfilled before deadline
    // getDescription() shows remaining time
    // isFulfilled() returns false if time expired
}
```

**Hints:**
- Use `java.time.Instant` for timestamps
- Consider how to check "current time" vs "deadline"
- What happens if the objective is fulfilled but time ran out?

---

## Extension 2: Quest Chains

Create a system where completing one quest unlocks another.

```java
public class QuestChain {
    // A sequence of quests
    // Only the current quest is "active"
    // Completing one unlocks the next
}
```

**Design questions:**
- How do you represent the chain?
- What happens when a chain quest is completed?
- Can chains branch (multiple next quests)?

---

## Extension 3: Smart Event Filtering

Improve the `QuestEventHandler` to be more efficient:

```java
public class SmartQuestEventHandler {
    // Register which objectives care about which event types
    // Only notify relevant objectives when events occur
    // Use a Map<Class<? extends QuestEvent>, List<QuestObjective>>
}
```

This is an example of the **Observer pattern** optimization.

---

## Extension 4: Quest UI

Create a simple text-based UI for the quest system:

```java
public class QuestDisplay {
    public String formatQuestLog(QuestLog log) {
        // Return a nicely formatted string showing:
        // - Active quests with their objectives
        // - Progress bars for each objective
        // - Completed quests
    }
}
```

Example output:
```
=== QUEST LOG ===

ACTIVE QUESTS:
[!] Goblin Slayer
    Defeat the goblin threat in the forest
    - Kill 5 Goblins [████░░░░░░] 2/5
    - Collect 100 gold [██████░░░░] 60/100
    Reward: 200 gold, 100 XP

[!] Dragon's Bane
    Slay the dragon terrorizing the village
    - Kill 1 Dragon [░░░░░░░░░░] 0/1
    Reward: 500 gold, Restore 50 HP

COMPLETED (2):
[✓] Tutorial Quest
[✓] First Blood
```

---

## Extension 5: Saveable Quest State

Design a system to save and load quest progress:

```java
public interface QuestSaveData {
    String toJson();
    static QuestLog fromJson(String json);
}
```

**Considerations:**
- What data needs to be saved?
- How do you handle objective progress?
- What about completed vs active quests?

You can use simple string manipulation or explore libraries like Jackson or Gson.

---

## Submission

If you complete any extensions:
1. Add them to your project
2. Write tests for your extensions
3. Note which extensions you completed in your self-assessment
4. Be prepared to explain your design decisions in class
