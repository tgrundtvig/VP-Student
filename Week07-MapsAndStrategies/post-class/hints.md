# Week 7 Post-Class Hints

Use these hints if you get stuck. Try to solve problems yourself first!

## SearchEngine Implementation

### Constructor Hint
```java
public SearchEngine(Map<K, T> items) {
    this.items = new HashMap<>(items);  // Defensive copy!
    this.searchStrategy = (item, query) -> true;  // Match all by default
    this.filterStrategy = item -> true;  // Accept all by default
    this.sortingStrategy = null;  // No sorting by default
}
```

### Search Method Hint

The search method combines all three strategies:

```java
public List<T> search(String query) {
    List<T> results = new ArrayList<>();

    for (T item : items.values()) {
        // Step 1: Check if item matches the search query
        if (searchStrategy.matches(item, query)) {
            // Step 2: Check if item passes the filter
            if (filterStrategy.accept(item)) {
                results.add(item);
            }
        }
    }

    // Step 3: Sort if we have a sorting strategy
    if (sortingStrategy != null) {
        results.sort(sortingStrategy);
    }

    return results;
}
```

### Using Streams (Alternative)

If you're comfortable with streams, you can write it more concisely:

```java
public List<T> search(String query) {
    Stream<T> stream = items.values().stream()
            .filter(item -> searchStrategy.matches(item, query))
            .filter(filterStrategy::accept);

    if (sortingStrategy != null) {
        stream = stream.sorted(sortingStrategy);
    }

    return stream.collect(Collectors.toList());
}
```

## Common Mistakes

### 1. Forgetting Defensive Copy
```java
// WRONG - allows external modification
this.items = items;

// RIGHT - creates independent copy
this.items = new HashMap<>(items);
```

### 2. Returning Internal Collection
```java
// WRONG - exposes internal state
public List<T> getAllItems() {
    return new ArrayList<>(items.values());  // OK - returns copy
}
```

### 3. NullPointerException on Sorting
```java
// WRONG - crashes if sortingStrategy is null
results.sort(sortingStrategy);

// RIGHT - check for null first
if (sortingStrategy != null) {
    results.sort(sortingStrategy);
}
```

## Strategy Combination Hints

### Combining Search Strategies
```java
// Search in name OR description
SearchStrategy<Product> nameSearch = ProductSearchStrategies.nameContains();
SearchStrategy<Product> descSearch = (p, q) ->
    p.description().toLowerCase().contains(q.toLowerCase());

SearchStrategy<Product> combined = nameSearch.or(descSearch);
```

### Combining Filter Strategies
```java
// Electronics under $100
FilterStrategy<Product> electronics = ProductFilterStrategies.category("Electronics");
FilterStrategy<Product> cheap = ProductFilterStrategies.maxPrice(10000);

FilterStrategy<Product> combined = electronics.and(cheap);
```

## Testing Your Implementation

Run tests frequently:
```bash
mvn test
```

Start with the basic tests and work your way up:
1. `BasicOperations` tests first
2. Then `SearchStrategyTests`
3. Then `FilterStrategyTests`
4. Then `SortingStrategyTests`
5. Finally `CombinedStrategies`

## Still Stuck?

- Review the pre-class reading on Maps and Strategies
- Look at the `ProductSearchStrategies` and `ProductFilterStrategies` classes for examples
- Come to class with specific questions
- Remember: the tests tell you exactly what's expected
