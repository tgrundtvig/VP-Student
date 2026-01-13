# Week 9 Pre-Class Reading: The Repository Pattern

## Introduction

Every application needs to store data somewhere. Whether it's user accounts, product catalogs,
or game save files - persistence is fundamental. But how we handle persistence makes a huge
difference in code quality, testability, and flexibility.

This week, we apply the same interface-first thinking to data storage that we applied to
user interfaces in Week 8.

## The Problem: Tightly Coupled Storage

Consider this typical code:

```java
public class BookService {
    private final String filePath = "books.json";

    public void addBook(Book book) {
        // Read existing books from file
        List<Book> books = readBooksFromFile();
        books.add(book);
        // Write back to file
        writeBooksToFile(books);
    }

    public Book findBook(String isbn) {
        List<Book> books = readBooksFromFile();
        return books.stream()
            .filter(b -> b.isbn().equals(isbn))
            .findFirst()
            .orElse(null);
    }

    private List<Book> readBooksFromFile() {
        // JSON parsing, file handling, error handling...
        // 20+ lines of infrastructure code
    }

    private void writeBooksToFile(List<Book> books) {
        // JSON serialization, file writing, error handling...
        // 20+ lines of infrastructure code
    }
}
```

### What's Wrong Here?

1. **Hard to test**: Tests create real files, are slow, need cleanup
2. **Business logic mixed with storage**: Can't see the actual service logic
3. **Inflexible**: Want to use a database? Rewrite everything
4. **Duplicated**: Every service has similar file-handling code

## The Solution: Repository Pattern

The Repository pattern puts an **interface** between your business logic and storage:

```java
// The interface - what we WISH we could do
public interface BookRepository {
    Book save(Book book);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findAll();
    void delete(Book book);
}

// The service - clean, focused on business logic
public class BookService {
    private final BookRepository repository;  // Injected!

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(String isbn, String title, String author) {
        Book book = new Book(isbn, title, author);
        return repository.save(book);
    }

    public Optional<Book> findBook(String isbn) {
        return repository.findByIsbn(isbn);
    }
}
```

Now the `BookService`:
- Contains only business logic
- Knows nothing about files, databases, or JSON
- Can be tested with a simple in-memory repository
- Works with any storage implementation

## CRUD Operations

Repository interfaces typically support these basic operations:

| Operation | Method | Description |
|-----------|--------|-------------|
| **C**reate | `save(entity)` | Store a new entity |
| **R**ead | `findById(id)`, `findAll()` | Retrieve entities |
| **U**pdate | `save(entity)` | Update existing entity (same as create) |
| **D**elete | `delete(entity)`, `deleteById(id)` | Remove entity |

Additional useful methods:
- `existsById(id)` - Check if entity exists
- `count()` - Get total number of entities

## Generic Repository Interface

We can make our repository work for any entity type using generics:

```java
/**
 * Generic repository interface for any entity type.
 *
 * @param <T> the entity type (must have an ID)
 * @param <ID> the type of the entity's identifier
 */
public interface Repository<T extends Identifiable<ID>, ID> {

    /**
     * Saves an entity. If the entity already exists (same ID), it's updated.
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Finds an entity by its ID.
     * @return Optional containing the entity, or empty if not found
     */
    Optional<T> findById(ID id);

    /**
     * Returns all entities.
     */
    List<T> findAll();

    /**
     * Deletes the given entity.
     */
    void delete(T entity);

    /**
     * Deletes an entity by its ID.
     */
    void deleteById(ID id);

    /**
     * Checks if an entity with the given ID exists.
     */
    boolean existsById(ID id);

    /**
     * Returns the total number of entities.
     */
    long count();
}
```

### The Identifiable Interface

For the generic repository to work, entities need to provide their ID:

```java
/**
 * Interface for entities that have an identifier.
 */
public interface Identifiable<ID> {
    ID getId();
}
```

### Example Entity

```java
public record Book(
    String isbn,
    String title,
    String author,
    int year
) implements Identifiable<String> {

    @Override
    public String getId() {
        return isbn;  // ISBN is the unique identifier
    }
}
```

## In-Memory Repository

The simplest implementation uses a `Map`:

```java
public class InMemoryRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {

    private final Map<ID, T> storage = new HashMap<>();

    @Override
    public T save(T entity) {
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(T entity) {
        storage.remove(entity.getId());
    }

    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(ID id) {
        return storage.containsKey(id);
    }

    @Override
    public long count() {
        return storage.size();
    }
}
```

This implementation:
- Is incredibly fast (all operations are O(1))
- Requires no external dependencies
- Perfect for testing
- Data is lost when the program ends (which is fine for tests!)

## Why This Pattern Matters

### 1. Testability

```java
@Test
void canFindBookByIsbn() {
    // Arrange - in-memory repository, instant setup
    Repository<Book, String> repo = new InMemoryRepository<>();
    BookService service = new BookService(repo);

    // Act
    service.addBook("123", "Test Book", "Author");
    Optional<Book> found = service.findBook("123");

    // Assert
    assertTrue(found.isPresent());
    assertEquals("Test Book", found.get().title());
}
// No files created, no cleanup needed, runs in milliseconds
```

### 2. Flexibility

```java
// Development: use in-memory (fast iteration)
Repository<Book, String> devRepo = new InMemoryRepository<>();

// Testing: use in-memory (fast, isolated tests)
Repository<Book, String> testRepo = new InMemoryRepository<>();

// Production: use file-based (data persists)
Repository<Book, String> prodRepo = new JsonFileRepository<>("books.json");

// Future: use database (scale to millions of records)
Repository<Book, String> dbRepo = new JdbcBookRepository(dataSource);

// ALL use the SAME service code!
BookService service = new BookService(repo);
```

### 3. Separation of Concerns

Your business logic doesn't know or care about:
- File formats (JSON, XML, CSV)
- Database queries (SQL, NoSQL)
- Network calls (REST APIs, remote storage)
- Caching strategies

It just calls `repository.save()` and `repository.findById()`.

## Custom Query Methods

Sometimes basic CRUD isn't enough. You can extend the generic interface:

```java
public interface BookRepository extends Repository<Book, String> {

    // Custom queries specific to books
    List<Book> findByAuthor(String author);
    List<Book> findByYearRange(int fromYear, int toYear);
    List<Book> findByTitleContaining(String keyword);
}
```

Each implementation then provides these methods:

```java
public class InMemoryBookRepository
        extends InMemoryRepository<Book, String>
        implements BookRepository {

    @Override
    public List<Book> findByAuthor(String author) {
        return findAll().stream()
            .filter(book -> book.author().equals(author))
            .toList();
    }

    @Override
    public List<Book> findByYearRange(int fromYear, int toYear) {
        return findAll().stream()
            .filter(book -> book.year() >= fromYear && book.year() <= toYear)
            .toList();
    }

    @Override
    public List<Book> findByTitleContaining(String keyword) {
        return findAll().stream()
            .filter(book -> book.title().toLowerCase()
                .contains(keyword.toLowerCase()))
            .toList();
    }
}
```

## File-Based Repository (Preview)

For data that needs to survive program restarts:

```java
public class JsonFileRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {

    private final Path filePath;
    private final Class<T> entityClass;
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonFileRepository(String filename, Class<T> entityClass) {
        this.filePath = Path.of(filename);
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        List<T> all = new ArrayList<>(findAll());
        // Remove existing if present
        all.removeIf(e -> e.getId().equals(entity.getId()));
        all.add(entity);
        writeToFile(all);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return findAll().stream()
            .filter(e -> e.getId().equals(id))
            .findFirst();
    }

    // ... other methods read/write JSON file
}
```

We'll implement this together in class.

## The Pattern in Context

```
┌─────────────────────────────────────────────────────────────┐
│                       Your Application                       │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌──────────────┐                                           │
│  │    View      │◄──── Interface (Week 8)                   │
│  │  (JavaFX/    │                                           │
│  │   Console)   │                                           │
│  └──────┬───────┘                                           │
│         │                                                    │
│         ▼                                                    │
│  ┌──────────────┐                                           │
│  │  Controller  │      Business Logic                        │
│  │              │                                           │
│  └──────┬───────┘                                           │
│         │                                                    │
│         ▼                                                    │
│  ┌──────────────┐                                           │
│  │   Service    │      Domain Logic                          │
│  │              │                                           │
│  └──────┬───────┘                                           │
│         │                                                    │
│         ▼                                                    │
│  ┌──────────────┐                                           │
│  │  Repository  │◄──── Interface (Week 9)                   │
│  │  (Memory/    │                                           │
│  │   File/DB)   │                                           │
│  └──────────────┘                                           │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

Both View and Repository are interfaces - the application core (Controller, Service)
depends only on interfaces, never on specific implementations.

## Summary

| Concept | Description |
|---------|-------------|
| Repository Pattern | Interface between business logic and storage |
| CRUD | Create, Read, Update, Delete - basic operations |
| Generic Repository | One interface works for all entity types |
| In-Memory Repository | Fast, test-friendly, uses Map internally |
| File Repository | Persists data to disk (JSON, etc.) |
| Dependency Injection | Service receives repository, doesn't create it |

## Pre-Class Exercises

After reading this material, complete the exercises in `pre-class/exercises/`:

1. Study the `Identifiable<ID>` and `Repository<T, ID>` interfaces
2. Implement `InMemoryRepository<T, ID>`
3. Write a simple service that uses the repository

## Questions to Consider

1. How is the Repository pattern similar to the View pattern from Week 8?
2. Why is it important that `save()` works for both create and update?
3. What would happen if your service created its own repository instead of receiving one?
4. How would you test a service that uses a database repository?
