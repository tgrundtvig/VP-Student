# Week 7: Maps and Strategies

## Overview
This week we continue applying the interface-first methodology to two powerful concepts: **Maps**
(key-value associations) and the **Strategy pattern** (pluggable algorithms). You'll learn that
`Map` is a contract just like `List`, and that algorithms can be interfaces too.

## Learning Objectives
By the end of this week, you will:
- Understand Map as an interface (contract), not just HashMap
- Design key-value associations using interface-first thinking
- Apply the Strategy pattern to make algorithms pluggable
- Use Comparators as strategy objects for sorting
- Design flexible, extensible systems using strategies

## The Big Idea

> "A Map is not a HashMap. A Comparator is not a sorting algorithm. Both are **contracts** that
> enable flexibility."

When you design with interfaces, you separate **what** from **how**:
- A Map promises key-value lookup - HashMap, TreeMap, and LinkedHashMap are just different **how**s
- A sorting strategy promises comparison - ascending, descending, by name, by date are different **how**s

## Pre-Class Preparation

### Reading
Complete `pre-class/reading.md` which covers:
- Map as an interface (the contract for key-value lookup)
- Why Map matters (beyond just HashMap)
- Introduction to the Strategy pattern
- Comparators as pluggable sorting strategies

### Exercises
The pre-class exercises ask you to:
1. Study the `KeyValueStore<K,V>` interface (simplified Map)
2. Implement a basic `SimpleKeyValueStore<K,V>` class
3. Study and use `SortingStrategy<T>` for pluggable sorting

**Time estimate:** 60-90 minutes

## This Week's Focus: Flexibility Through Interfaces

We'll build systems that can change behavior without changing code:

```java
// The "wish" - how we WANT to use a key-value store:
KeyValueStore<String, Player> players = new SimpleKeyValueStore<>();

players.put("player1", new Player("Alice", 100));
players.put("player2", new Player("Bob", 85));

Player alice = players.get("player1").orElseThrow();
boolean hasPlayer3 = players.containsKey("player3");

// The "wish" - how we WANT to use strategies:
List<Player> roster = new ArrayList<>(players.values());

SortingStrategy<Player> byScore = (p1, p2) -> p2.score() - p1.score();
SortingStrategy<Player> byName = (p1, p2) -> p1.name().compareTo(p2.name());

// Same list, different sorting - just swap the strategy!
roster.sort(byScore);  // Alice first (higher score)
roster.sort(byName);   // Alice first (alphabetically)
```

This reveals what interfaces we need:
- `KeyValueStore<K,V>` - associates keys with values
- `SortingStrategy<T>` - defines how to compare two elements

## Key Concepts

### Maps as Contracts
A Map is a **promise** about key-value associations:
- Each key maps to at most one value
- Keys are unique
- Lookup by key is efficient

The interface doesn't specify:
- How values are stored (array, tree, hash table)
- What order keys are stored in
- How duplicates are detected

### The Strategy Pattern
The Strategy pattern captures the idea that **algorithms are objects**:

```java
// Without Strategy: hardcoded algorithm
public void sortByName(List<Player> players) {
    // sorting logic here
}

public void sortByScore(List<Player> players) {
    // different sorting logic here
}

// With Strategy: pluggable algorithm
public void sort(List<Player> players, SortingStrategy<Player> strategy) {
    // Uses the strategy to determine order
}
```

Benefits:
- Add new strategies without changing existing code
- Test strategies in isolation
- Configure behavior at runtime

### Comparators: Java's Built-in Strategy
Java's `Comparator<T>` is the Strategy pattern in the standard library:

```java
Comparator<Player> byScore = (p1, p2) -> p2.score() - p1.score();
Comparator<Player> byName = Comparator.comparing(Player::name);

players.sort(byScore);
players.sort(byName);
```

The sorting algorithm stays the same - only the comparison strategy changes.

## Post-Class Work

### Exercises
After class, complete the strategy exercises:
- Implement a `SearchEngine<T>` with pluggable search strategies
- Create multiple search strategies (exact match, prefix, fuzzy)
- Build a filter system with strategy-based filtering

### Homework: Flexible Search System
Design and implement a search system where:
- Search behavior is defined by a `SearchStrategy<T>` interface
- Multiple implementations provide different search approaches
- The system can combine multiple strategies

Focus on designing the interface first, then implementing.

## Connection to Previous Weeks

| Week | Focus | Connection |
|------|-------|------------|
| Week 3 | Interfaces as contracts | Map and Strategy are both contracts |
| Week 4 | Wishful programming | Design search usage first |
| Week 5 | Data structures | Map is another data structure abstraction |
| Week 6 | Trees | Maps can be implemented as trees (TreeMap) |
| **Week 7** | **Maps and Strategies** | **Combine data contracts with algorithm contracts** |

## Looking Ahead
Maps and strategies appear everywhere:
- Week 8: GUI frameworks use strategies for event handling
- Week 9: Persistence uses strategies for serialization
- Week 10: Example projects combine multiple patterns

## Verification Checklist
Before class, ensure you can:
- [ ] Explain why Map is an interface, not just HashMap
- [ ] Describe the Strategy pattern in your own words
- [ ] Write a simple Comparator for sorting
- [ ] Give two examples where pluggable algorithms would help

## Getting Help
- Review Week 5 materials on data structures as abstractions
- Check the pre-class reading for examples
- Come to class with specific questions
