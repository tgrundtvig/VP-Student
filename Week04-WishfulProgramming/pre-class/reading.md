# Week 4 Pre-Class Reading: Wishful Programming

## Introduction

Last week you learned to design interfaces - contracts that define WHAT something does 
without specifying HOW. This week, we learn to USE those interfaces with a technique 
called "wishful programming."

## What is Wishful Programming?

Wishful programming is writing code as if everything you need already exists.

Instead of:
1. Think about implementation details
2. Write low-level code
3. Build up to higher-level features

We do:
1. Write high-level code using things that don't exist
2. Let the compiler tell us what's missing
3. Define those abstractions
4. Implement (often using MORE abstractions)
5. Repeat until implementation is trivial

### A Simple Example

**Traditional approach:**
```java
// Start with details
public class GoldReward {
    private int amount;
    public GoldReward(int amount) { this.amount = amount; }
    public void give(Player p) { p.addGold(amount); }
}

// Then figure out how to use it
// Then realize we need XPReward too...
// Then create a common interface...
```

**Wishful programming approach:**
```java
// Start with how we WANT to use it
public void completeQuest(Quest quest, Player player) {
    Reward reward = quest.getReward();  // What's Reward? Don't care yet!
    reward.grantTo(player);             // It can grant itself to a player
}

// NOW define the interface based on what we needed
public interface Reward {
    void grantTo(Player player);
}

// Implementation is now obvious and simple
public class GoldReward implements Reward {
    private final int amount;
    public GoldReward(int amount) { this.amount = amount; }
    @Override public void grantTo(Player player) { player.addGold(amount); }
}
```

## The Process

### Step 1: Write the Wish Code

Start at the highest level. Write code that expresses WHAT you want to happen:

```java
public void playGame() {
    Quest quest = questLog.getActiveQuest();
    
    while (!quest.isComplete()) {
        showObjective(quest.getCurrentObjective());
        
        // ... player does stuff ...
        
        quest.progress(event);
    }
    
    celebrateCompletion(quest);
    quest.getReward().grantTo(player);
}
```

This code won't compile! That's okay. The red squiggles are telling us what we need.

### Step 2: Let Errors Guide You

The compiler says:
- `QuestLog` doesn't exist → Create `QuestLog` interface
- `Quest` doesn't exist → Create `Quest` interface
- `getActiveQuest()` doesn't exist → Add to `QuestLog`
- And so on...

Each error is the compiler HELPING you design.

### Step 3: Define Interfaces

Based on what you needed in Step 1:

```java
public interface Quest {
    String getName();
    QuestObjective getCurrentObjective();
    boolean isComplete();
    Reward getReward();
    void progress(QuestEvent event);
}

public interface QuestObjective {
    String getDescription();
    boolean isFulfilled();
}

public interface Reward {
    void grantTo(Player player);
}
```

Notice: We still haven't written any implementation!

### Step 4: Implement Top-Down

Now implement, but STILL use wishful thinking:

```java
public class SlayDragonQuest implements Quest {
    private QuestObjective objective = new KillMonsterObjective("Dragon", 1);
    private Reward reward = new CompositeReward(
        new GoldReward(500),
        new XPReward(1000)
    );
    
    @Override
    public boolean isComplete() {
        return objective.isFulfilled();
    }
    
    // ... etc
}
```

We used `KillMonsterObjective` and `CompositeReward` before implementing them!

### Step 5: Repeat Until Trivial

Eventually, implementations become trivial:

```java
public class GoldReward implements Reward {
    private final int amount;
    
    public GoldReward(int amount) {
        this.amount = amount;
    }
    
    @Override
    public void grantTo(Player player) {
        player.addGold(amount);
    }
}
```

When implementation is this simple, you've reached the bottom.

## Why This Works

### 1. Design Emerges from Need
You don't invent interfaces speculatively. Each one exists because your 
higher-level code NEEDED it.

### 2. Implementation Becomes Mechanical
Once interfaces are defined, implementation is almost boring. The hard 
thinking happened during design.

### 3. Changes Are Isolated
Need a new reward type? Just implement `Reward`. Nothing else changes.

### 4. Testing Is Easy
You can test each level with mock implementations of the interfaces below it.

## Recognizing When to Create an Interface

Create an interface when you find yourself:
- Wishing a method existed
- Wanting to swap implementations
- Seeing a "family" of similar things (different rewards, different objectives)
- Wanting to test without dependencies

Don't create an interface when:
- There's only ever one implementation (probably)
- It's just data with no behavior (use a record)
- You're creating it "just in case"

## Records vs Interfaces

**Use a record when:**
- It's pure data
- It has no behavior beyond getters
- Examples: `QuestEvent`, `Position`, `MonsterStats`

```java
public record QuestEvent(String type, Object subject) {}
```

**Use an interface when:**
- It has behavior
- Multiple implementations make sense
- Examples: `Quest`, `Reward`, `Combatant`

```java
public interface Reward {
    void grantTo(Player player);  // Behavior!
}
```

## Common Mistakes

### 1. Starting with Implementation
**Wrong:** "Let me implement GoldReward first, then figure out how to use it"
**Right:** "Let me write how I want to grant rewards, then create the interface"

### 2. Too Many Interfaces
**Wrong:** Creating interfaces for things you don't need yet
**Right:** Each interface exists because wish code needed it

### 3. Interfaces That Mirror Implementation
**Wrong:** `interface DatabaseBookRepository { void saveToDatabase(); }`
**Right:** `interface BookRepository { void save(Book book); }`

### 4. Forgetting Records
**Wrong:** `interface Position { int getX(); int getY(); }` (no behavior!)
**Right:** `record Position(int x, int y) {}`

## Exercises Preview

In the pre-class exercises, you'll:
1. Analyze wish code and identify needed interfaces
2. Implement a reward system following given interfaces
3. Design an objective system using wishful programming

## Key Takeaways

1. **Write code that doesn't compile** - The compiler guides your design
2. **Interfaces emerge from need** - Don't invent them speculatively
3. **Implementation becomes trivial** - If it's hard, you need more interfaces
4. **Records for data, interfaces for behavior** - Know which to use

## Before Class

Make sure you can:
- Explain wishful programming to a friend
- Look at "wish code" and identify what interfaces it needs
- Distinguish between when to use records vs interfaces
- Complete the pre-class exercises

The methodology should "click" this week. If interfaces felt like extra work 
before, wishful programming shows you WHY they matter.
