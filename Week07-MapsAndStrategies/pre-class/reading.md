# Week 7 Pre-Class Reading: Maps and Strategies

## Introduction

In the previous weeks, you learned that data structures like List, Stack, Queue, and Tree are
**contracts** - interfaces that promise certain behavior without specifying implementation.
This week, we apply the same thinking to two new concepts:

1. **Maps** - data structures that associate keys with values
2. **Strategies** - algorithms encapsulated as objects

Both follow the same principle: **separate what from how**.

## Part 1: Maps as Contracts

### What is a Map?

A Map (also called Dictionary, Associative Array, or Hash in other languages) is a data
structure that associates **keys** with **values**:

```java
// Associate player IDs with player objects
Map<String, Player> players = new HashMap<>();
players.put("p001", new Player("Alice", 100));
players.put("p002", new Player("Bob", 85));

// Look up by key
Player alice = players.get("p001");  // Returns Alice's player object
```

### The Map Interface

Java's `Map<K,V>` interface defines the contract for key-value associations:

```java
public interface Map<K, V> {
    V put(K key, V value);           // Associate key with value
    V get(Object key);               // Get value for key (null if missing)
    boolean containsKey(Object key); // Check if key exists
    boolean containsValue(Object v); // Check if value exists
    V remove(Object key);            // Remove key-value pair
    int size();                      // Number of key-value pairs
    boolean isEmpty();               // True if no pairs
    Set<K> keySet();                 // All keys
    Collection<V> values();          // All values
    Set<Entry<K,V>> entrySet();      // All key-value pairs
}
```

### The Key Insight: Map is a Contract, Not a Class

When you write `Map<String, Player>`, you're making a **promise** about how you'll store and
retrieve data - not about HOW it's implemented.

```java
// All of these fulfill the same contract:
Map<String, Player> players1 = new HashMap<>();      // Fast lookup, unordered
Map<String, Player> players2 = new TreeMap<>();      // Sorted by key
Map<String, Player> players3 = new LinkedHashMap<>(); // Maintains insertion order
```

The code using `players` doesn't care which implementation you chose - it just knows it can
put and get values by key.

### Why This Matters

Consider a game that needs to look up items by ID:

```java
public class Inventory {
    private Map<String, Item> items;  // Interface type, not HashMap!

    public Inventory(Map<String, Item> items) {
        this.items = items;
    }

    public Optional<Item> findItem(String id) {
        return Optional.ofNullable(items.get(id));
    }
}
```

Benefits:
- **Testing**: Use a simple in-memory map for tests
- **Flexibility**: Switch implementations without changing code
- **Clarity**: The code says "I need key-value lookup" not "I need a hash table"

### Designing Your Own Map Interface

Let's design a simplified key-value store interface:

```java
/**
 * A simplified key-value store interface.
 * This is a teaching version of java.util.Map.
 */
public interface KeyValueStore<K, V> {

    /**
     * Associates the key with the value.
     * If the key already exists, replaces the old value.
     *
     * @return the previous value, or empty if this is a new key
     */
    Optional<V> put(K key, V value);

    /**
     * Gets the value associated with the key.
     *
     * @return the value, or empty if key doesn't exist
     */
    Optional<V> get(K key);

    /**
     * Checks if the store contains the key.
     */
    boolean containsKey(K key);

    /**
     * Removes the key-value pair.
     *
     * @return the removed value, or empty if key didn't exist
     */
    Optional<V> remove(K key);

    /**
     * Returns the number of key-value pairs.
     */
    int size();

    /**
     * Returns true if the store is empty.
     */
    boolean isEmpty();

    /**
     * Returns all keys in the store.
     */
    Set<K> keys();

    /**
     * Returns all values in the store.
     */
    Collection<V> values();
}
```

Notice how we use `Optional<V>` instead of returning `null` - this makes the contract clearer
and safer.

## Part 2: The Strategy Pattern

### The Problem: Hardcoded Algorithms

Imagine sorting a list of players. Without good design:

```java
public class PlayerSorter {

    public void sortByName(List<Player> players) {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (players.get(i).name().compareTo(players.get(j).name()) > 0) {
                    // swap
                    Player temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }
    }

    public void sortByScore(List<Player> players) {
        // Same sorting code, different comparison!
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (players.get(i).score() > players.get(j).score()) {
                    // swap
                    Player temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }
    }

    public void sortByLevel(List<Player> players) {
        // Same sorting code AGAIN, different comparison!
        // ...
    }
}
```

Problems:
- **Duplication**: Same sorting logic copied everywhere
- **Rigidity**: Adding a new sort criterion requires a new method
- **Testing**: Must test each method separately

### The Solution: Strategy Pattern

The Strategy pattern extracts the varying part (the comparison) into its own interface:

```java
/**
 * A strategy for comparing two elements.
 * Determines how elements should be ordered.
 */
public interface SortingStrategy<T> {
    /**
     * Compares two elements.
     *
     * @return negative if first < second, zero if equal, positive if first > second
     */
    int compare(T first, T second);
}
```

Now the sorter only needs ONE method:

```java
public class PlayerSorter {

    public void sort(List<Player> players, SortingStrategy<Player> strategy) {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (strategy.compare(players.get(i), players.get(j)) > 0) {
                    // swap
                    Player temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }
    }
}
```

And you can create as many strategies as you need:

```java
// Different strategies - same sorter
SortingStrategy<Player> byName = (p1, p2) -> p1.name().compareTo(p2.name());
SortingStrategy<Player> byScore = (p1, p2) -> p2.score() - p1.score();  // Descending
SortingStrategy<Player> byLevel = (p1, p2) -> p1.level() - p2.level();

sorter.sort(players, byName);   // Sort by name
sorter.sort(players, byScore);  // Sort by score
sorter.sort(players, byLevel);  // Sort by level
```

### Strategy Pattern Structure

```
      ┌─────────────────────┐
      │      Context        │
      │  (PlayerSorter)     │
      │                     │
      │ - strategy: Strategy│────────────┐
      │                     │            │
      │ + sort(items)       │            ▼
      └─────────────────────┘    ┌───────────────────┐
                                 │    <<interface>>   │
                                 │      Strategy      │
                                 │                    │
                                 │ + compare(a, b)    │
                                 └────────┬──────────┘
                                          │
              ┌───────────────────────────┼───────────────────────────┐
              │                           │                           │
              ▼                           ▼                           ▼
     ┌────────────────┐          ┌────────────────┐          ┌────────────────┐
     │  ByNameStrategy │          │ ByScoreStrategy│          │ByLevelStrategy │
     └────────────────┘          └────────────────┘          └────────────────┘
```

### Java's Built-in Strategy: Comparator

Java already has the Strategy pattern built into its standard library: `Comparator<T>`.

```java
// java.util.Comparator is exactly the Strategy pattern!
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

Java's `List.sort()` and `Collections.sort()` accept a Comparator:

```java
List<Player> players = new ArrayList<>();
// ... add players ...

// Using lambda expressions (compact strategy definition)
players.sort((p1, p2) -> p1.name().compareTo(p2.name()));

// Using Comparator factory methods (even more compact)
players.sort(Comparator.comparing(Player::name));

// Chained comparisons
players.sort(Comparator.comparing(Player::level)
                       .thenComparing(Player::score)
                       .reversed());
```

### When to Use the Strategy Pattern

Use Strategy when:
1. You have multiple algorithms that do the same job differently
2. You want to switch algorithms at runtime
3. You want to isolate algorithm code for testing
4. You're seeing similar code repeated with slight variations

Examples:
- **Sorting**: Different comparison criteria
- **Filtering**: Different filter conditions
- **Validation**: Different validation rules
- **Pricing**: Different discount strategies
- **Compression**: Different compression algorithms
- **Pathfinding**: Different route-finding algorithms

## Part 3: Combining Maps and Strategies

Maps and strategies often work together. Here's a search engine example:

```java
/**
 * Strategy for matching search queries to items.
 */
public interface SearchStrategy<T> {
    /**
     * Returns true if the item matches the query.
     */
    boolean matches(T item, String query);
}

/**
 * A search engine that uses strategies for flexible searching.
 */
public class SearchEngine<K, T> {
    private final Map<K, T> items;
    private SearchStrategy<T> strategy;

    public SearchEngine(Map<K, T> items, SearchStrategy<T> strategy) {
        this.items = items;
        this.strategy = strategy;
    }

    public void setStrategy(SearchStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public List<T> search(String query) {
        List<T> results = new ArrayList<>();
        for (T item : items.values()) {
            if (strategy.matches(item, query)) {
                results.add(item);
            }
        }
        return results;
    }
}
```

Usage:

```java
Map<String, Player> playerMap = new HashMap<>();
playerMap.put("p1", new Player("Alice", 100));
playerMap.put("p2", new Player("Bob", 85));
playerMap.put("p3", new Player("Charlie", 90));

// Different search strategies
SearchStrategy<Player> exactName = (p, q) -> p.name().equals(q);
SearchStrategy<Player> containsName = (p, q) -> p.name().toLowerCase().contains(q.toLowerCase());
SearchStrategy<Player> minScore = (p, q) -> p.score() >= Integer.parseInt(q);

SearchEngine<String, Player> engine = new SearchEngine<>(playerMap, containsName);

engine.search("ali");   // Finds Alice
engine.setStrategy(minScore);
engine.search("90");    // Finds Alice and Charlie
```

## Common Mistakes

### 1. Using HashMap Instead of Map

**Wrong:**
```java
public class GameState {
    private HashMap<String, Player> players = new HashMap<>();  // Concrete class!
}
```

**Right:**
```java
public class GameState {
    private Map<String, Player> players = new HashMap<>();  // Interface type!
}
```

### 2. Hardcoding Strategies Instead of Injecting Them

**Wrong:**
```java
public class Leaderboard {
    public List<Player> getTopPlayers(List<Player> players) {
        players.sort((p1, p2) -> p2.score() - p1.score());  // Hardcoded!
        return players.subList(0, 10);
    }
}
```

**Right:**
```java
public class Leaderboard {
    private final Comparator<Player> rankingStrategy;

    public Leaderboard(Comparator<Player> rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
    }

    public List<Player> getTopPlayers(List<Player> players) {
        List<Player> sorted = new ArrayList<>(players);
        sorted.sort(rankingStrategy);
        return sorted.subList(0, Math.min(10, sorted.size()));
    }
}
```

### 3. Mutating the Original Collection

**Wrong:**
```java
public List<Player> getSorted(List<Player> players, Comparator<Player> strategy) {
    players.sort(strategy);  // Mutates the original list!
    return players;
}
```

**Right:**
```java
public List<Player> getSorted(List<Player> players, Comparator<Player> strategy) {
    List<Player> copy = new ArrayList<>(players);
    copy.sort(strategy);  // Mutates the copy only
    return copy;
}
```

## Exercise Preview

In the pre-class exercises, you'll:
1. Study the `KeyValueStore<K,V>` interface
2. Implement a `SimpleKeyValueStore<K,V>` class
3. Study and use `SortingStrategy<T>` for sorting
4. Create your own sorting strategies

## Key Takeaways

1. **Map is a contract** - It promises key-value lookup, not a specific implementation
2. **Strategy encapsulates algorithms** - Extract varying behavior into strategy objects
3. **Comparator is a strategy** - Java's built-in pattern for sorting
4. **Interface-first applies to algorithms** - Not just data structures
5. **Flexibility through injection** - Pass strategies in, don't hardcode them

## Before Class

Make sure you can:
- Explain why `Map<K,V>` is better than `HashMap<K,V>` as a type
- Describe when you would use the Strategy pattern
- Write a simple `Comparator<T>` using a lambda expression
- Give an example of an algorithm that could be a strategy
