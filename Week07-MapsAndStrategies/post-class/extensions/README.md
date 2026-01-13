# Week 7 Extensions

These optional challenges extend your understanding of Maps and Strategies.
Only attempt these after completing all regular exercises.

## Extension 1: Weighted Search Scoring

Instead of boolean matching, create a search system that assigns **scores** to matches.

### Interface Design

```java
/**
 * A strategy that scores how well an item matches a query.
 * Higher scores = better matches.
 */
public interface ScoringStrategy<T> {
    /**
     * Returns a score for how well the item matches the query.
     *
     * @return score >= 0, where 0 means no match, higher is better
     */
    double score(T item, String query);
}
```

### Implementation Ideas

- Exact match: 100 points
- Starts with query: 75 points
- Contains query: 50 points
- Fuzzy match (Levenshtein distance): variable points

### Challenge

Modify SearchEngine to use ScoringStrategy and return results sorted by score.

## Extension 2: Multi-Field Search

Create a search system that can search across multiple fields with different weights.

```java
record SearchField<T>(
    String fieldName,
    Function<T, String> extractor,  // How to get the field value
    double weight                    // How important this field is
) {}

public class MultiFieldSearchStrategy<T> implements SearchStrategy<T> {
    private final List<SearchField<T>> fields;
    // ...
}
```

## Extension 3: Caching Search Results

Implement a caching layer for search results:

```java
public class CachingSearchEngine<K, T> {
    private final SearchEngine<K, T> delegate;
    private final Map<String, List<T>> cache;
    private final int maxCacheSize;

    // Cache search results, evict when full
}
```

Consider:
- When to invalidate the cache (item added/removed, strategy changed)
- LRU (Least Recently Used) eviction
- Cache size limits

## Extension 4: Observable Search Engine

Make the search engine notify observers when items change:

```java
public interface SearchEngineObserver<T> {
    void onItemAdded(T item);
    void onItemRemoved(T item);
    void onSearchPerformed(String query, List<T> results);
}

public class ObservableSearchEngine<K, T> extends SearchEngine<K, T> {
    private final List<SearchEngineObserver<T>> observers;

    public void addObserver(SearchEngineObserver<T> observer) { ... }
    public void removeObserver(SearchEngineObserver<T> observer) { ... }
}
```

## Extension 5: Fuzzy Matching Strategy

Implement a fuzzy search using Levenshtein distance:

```java
public class FuzzySearchStrategy implements SearchStrategy<Product> {
    private final int maxDistance;  // Maximum allowed edit distance

    @Override
    public boolean matches(Product product, String query) {
        int distance = levenshteinDistance(product.name(), query);
        return distance <= maxDistance;
    }

    private int levenshteinDistance(String s1, String s2) {
        // Implement the algorithm
        // See: https://en.wikipedia.org/wiki/Levenshtein_distance
    }
}
```

## Extension 6: Strategy Builder with Fluent API

Create a builder for complex strategies:

```java
SearchStrategy<Product> strategy = SearchStrategyBuilder.forProducts()
    .searchInName()
    .searchInDescription()
    .caseInsensitive()
    .withMinScore(0.5)
    .build();

FilterStrategy<Product> filter = FilterStrategyBuilder.forProducts()
    .inCategory("Electronics")
    .priceBelow(10000)
    .excludeOutOfStock()
    .build();
```

## Extension 7: Persistence Strategy

Design interfaces for persisting the search engine's data:

```java
public interface SearchEngineRepository<K, T> {
    void save(Map<K, T> items);
    Map<K, T> load();
}

// Implementations:
// - JsonSearchEngineRepository (saves to JSON file)
// - InMemorySearchEngineRepository (for testing)
// - DatabaseSearchEngineRepository (for production)
```

## Reflection Questions

After completing an extension:

1. How does this extension demonstrate the Strategy pattern?
2. What interfaces did you design?
3. How would you test this extension?
4. What trade-offs did you make in your design?

## Submission (Optional)

If you complete any extensions and want feedback:
1. Create a new package `dk.viprogram.week07.extensions`
2. Include tests for your implementation
3. Bring to class or submit via GitHub
