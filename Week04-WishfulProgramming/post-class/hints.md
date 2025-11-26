# Week 4: Post-Class Exercise Hints

Use these hints if you get stuck. Try to solve the exercises on your own first!

---

## Exercise 1: Quest Class

### Hint 1: Fields
Think about what a Quest needs to store:
- Name and description (Strings)
- A list of objectives (what type?)
- A reward (what type?)

### Hint 2: isComplete()
A quest is complete when ALL objectives are fulfilled. You can use:
- A for-loop checking each objective
- Or streams: `objectives.stream().allMatch(QuestObjective::isFulfilled)`

### Hint 3: complete() method
```java
public boolean complete(Player player) {
    if (isComplete()) {
        reward.grantTo(player);
        return true;
    }
    return false;
}
```

---

## Exercise 2: QuestLog Class

### Hint 1: Data Structure
Use two `ArrayList<Quest>`:
- `activeQuests` - quests the player is working on
- `completedQuests` - quests the player has finished

### Hint 2: checkAndCompleteQuests()
Be careful! You can't modify a list while iterating over it with a for-each loop.

**Option A**: Collect first, then modify
```java
List<Quest> toComplete = new ArrayList<>();
for (Quest quest : activeQuests) {
    if (quest.isComplete()) {
        toComplete.add(quest);
    }
}
// Now move them
for (Quest quest : toComplete) {
    quest.complete(player);
    activeQuests.remove(quest);
    completedQuests.add(quest);
}
return toComplete;
```

**Option B**: Use removeIf with a side effect (advanced)
```java
List<Quest> completed = new ArrayList<>();
activeQuests.removeIf(quest -> {
    if (quest.isComplete()) {
        quest.complete(player);
        completedQuests.add(quest);
        completed.add(quest);
        return true;
    }
    return false;
});
return completed;
```

---

## Exercise 3: QuestEvent System

### Hint 1: Event Records
Create simple records for each event type:
```java
public record MonsterKilledEvent(String monsterType) implements QuestEvent {
    @Override
    public String getDescription() {
        return "Killed a " + monsterType;
    }
}

public record GoldCollectedEvent(int amount) implements QuestEvent {
    @Override
    public String getDescription() {
        return "Collected " + amount + " gold";
    }
}
```

### Hint 2: Matching Events to Objectives
You need a way to know if an objective cares about a specific event. Options:

**Option A**: Add a method to objectives
```java
// In KillCountObjective
public boolean matches(QuestEvent event) {
    return event instanceof MonsterKilledEvent mke
        && mke.monsterType().equals(targetName);
}
```

**Option B**: Handle matching in the handler
```java
if (event instanceof MonsterKilledEvent mke) {
    for (Quest quest : questLog.getActiveQuests()) {
        for (QuestObjective obj : quest.getObjectives()) {
            if (obj instanceof KillCountObjective kco
                && kco.getTargetName().equals(mke.monsterType())) {
                kco.recordProgress(1);
            }
        }
    }
}
```

### Hint 3: Pattern Matching with instanceof
Java 21 supports pattern matching:
```java
if (event instanceof MonsterKilledEvent mke) {
    // mke is already cast to MonsterKilledEvent
    String type = mke.monsterType();
}
```

---

## General Tips

1. **Start with interfaces**: Look at what the tests expect
2. **Implement one method at a time**: Get each test passing before moving on
3. **Use the debugger**: Step through your code to understand flow
4. **Read the error messages**: They usually tell you exactly what's wrong

Remember: The goal is to understand how wishful programming helps you design clean systems. The quest system naturally emerged from thinking "what would I wish I had?"
