# Week 9 Extension Challenges

Completed the main exercises? Try these extensions to deepen your understanding.

## Extension 1: Custom Query Methods

Create a `BookRepository` interface that extends `Repository` with custom queries:

```java
public interface BookRepository extends Repository<Book, String> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitleContaining(String keyword);
    List<Book> findAvailable();
}
```

Implement this interface in both:
- `InMemoryBookRepository`
- `JsonFileBookRepository`

**Learning goal:** Understand how to extend the generic pattern with domain-specific queries.

## Extension 2: Member Loan History

Add methods to `LibraryService`:

```java
public List<Loan> getLoanHistory(String memberId);  // All loans (including returned)
public int getActiveLoansCount(String memberId);    // Current borrows
public boolean canBorrow(String memberId);          // Max 3 active loans
```

Update `borrowBook()` to check `canBorrow()` before allowing a new loan.

**Learning goal:** Practice building business rules on top of repository queries.

## Extension 3: Reservation System

Add a new entity and repository for reservations:

```java
public record Reservation(
    String id,
    String bookIsbn,
    String memberId,
    LocalDate reservedDate,
    boolean fulfilled
) implements Identifiable<String> { ... }
```

Implement:
- `reserveBook(isbn, memberId)` - Reserve an unavailable book
- `fulfillReservation(reservationId)` - When book becomes available
- Automatic reservation fulfillment when a book is returned

**Learning goal:** Practice designing new entities and their repository interactions.

## Extension 4: Database Repository

Create a `JdbcBookRepository` that stores books in an SQLite database:

```java
public class JdbcBookRepository implements Repository<Book, String> {
    private final Connection connection;

    // Implement all Repository methods using JDBC
}
```

You'll need:
- SQLite JDBC driver dependency
- SQL for CREATE TABLE, INSERT, SELECT, UPDATE, DELETE
- ResultSet to Book mapping

**Learning goal:** See how the same interface can wrap completely different storage technologies.

## Extension 5: Caching Repository

Create a decorator that caches reads:

```java
public class CachingRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {

    private final Repository<T, ID> delegate;
    private final Map<ID, T> cache = new HashMap<>();

    // Implement methods that check cache first
}
```

Use it like:
```java
Repository<Book, String> fileRepo = new JsonFileRepository<>(...);
Repository<Book, String> cachedRepo = new CachingRepository<>(fileRepo);
// Now reads are fast after first access
```

**Learning goal:** Understand the Decorator pattern and how repositories can be composed.

## Extension 6: Repository Events

Add events for repository changes:

```java
public interface RepositoryListener<T> {
    void onSaved(T entity);
    void onDeleted(T entity);
}

public interface ObservableRepository<T, ID> extends Repository<T, ID> {
    void addListener(RepositoryListener<T> listener);
    void removeListener(RepositoryListener<T> listener);
}
```

Use this to automatically log all changes or update other systems.

**Learning goal:** Practice the Observer pattern combined with Repository.

## Tips for Extensions

1. **Start simple** - Get basic functionality working first
2. **Write tests** - Each extension should have tests
3. **Use the patterns** - Apply interface-first thinking
4. **Keep services clean** - Business logic in services, not repositories

## Sharing Your Work

If you complete an extension, consider:
- Showing it in class
- Explaining your design decisions
- Discussing alternative approaches

Extensions are optional but excellent exam preparation!
