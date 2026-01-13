# Week 9: Persistence as Interfaces

## Overview

This week we apply the interface-first methodology to **data persistence**. You'll learn that
storage (files, databases, memory) should be hidden behind interfaces, enabling testable,
flexible data access. The same principles that made our Views swappable now make our
storage layer swappable.

## Learning Objectives

By the end of this week, you will:
- Understand the Repository pattern as an interface for data access
- Design persistence interfaces that abstract away storage details
- Implement multiple storage backends (memory, file, JSON)
- Write tests using in-memory repositories (fast, no disk I/O)
- Apply dependency injection to swap storage implementations

## The Big Idea

> "A Repository is not a file or a database. A Repository is a **contract** for storing
> and retrieving objects."

When you define your persistence as an interface:
- You can test your business logic without touching the file system
- You can swap between file storage and database without changing application code
- Your domain logic stays completely separated from storage concerns

## Pre-Class Preparation

### Reading
Complete `pre-class/reading.md` which covers:
- The Repository pattern explained
- Why persistence should be an interface
- CRUD operations (Create, Read, Update, Delete)
- Testing strategies for persistence

### Exercises
The pre-class exercises ask you to:
1. Study a `Repository<T>` interface
2. Implement an `InMemoryRepository<T>`
3. Understand how services use repositories without knowing the storage type

**Time estimate:** 60-90 minutes

## This Week's Focus: The Repository Pattern

We'll build a storage system with swappable backends:

```java
// The "wish" - how we WANT to use storage:
public interface Repository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(T entity);
    void deleteById(ID id);
    boolean existsById(ID id);
    long count();
}

// Service doesn't know or care how data is stored
public class BookService {
    private final Repository<Book, String> bookRepository;

    public BookService(Repository<Book, String> bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(String isbn, String title, String author) {
        Book book = new Book(isbn, title, author);
        return bookRepository.save(book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }
}

// In-memory for testing - instant, no disk I/O
Repository<Book, String> testRepo = new InMemoryRepository<>();
BookService service = new BookService(testRepo);

// File-based for production - persists between runs
Repository<Book, String> fileRepo = new JsonFileRepository<>("books.json", Book.class);
BookService service = new BookService(fileRepo);

// Same service code works with either!
```

## Key Concepts

### The Repository Pattern

```
┌─────────────────────────────────────────────────────────────┐
│                      Application Layer                       │
│                                                              │
│    ┌─────────────┐         ┌─────────────────────────┐      │
│    │  Services   │────────►│  Repository<T, ID>      │      │
│    │ (Business   │         │  (Interface)            │      │
│    │  Logic)     │         │                         │      │
│    └─────────────┘         └─────────────────────────┘      │
│                                       △                      │
│                            ┌──────────┼──────────┐          │
│                            │          │          │          │
│                    ┌───────┴───┐ ┌────┴────┐ ┌───┴──────┐   │
│                    │ InMemory  │ │  JSON   │ │ Database │   │
│                    │ Repository│ │ File    │ │ (JDBC)   │   │
│                    └───────────┘ └─────────┘ └──────────┘   │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

- **Services** contain business logic, depend on Repository interface
- **Repository Interface** defines CRUD operations
- **Implementations** handle actual storage (memory, file, database)

### Why In-Memory Repositories Matter

```java
// Tests run in milliseconds, not seconds!
@Test
void canAddAndRetrieveBook() {
    Repository<Book, String> repo = new InMemoryRepository<>();
    BookService service = new BookService(repo);

    service.addBook("978-0-13-468599-1", "Clean Code", "Robert Martin");

    Optional<Book> found = service.findByIsbn("978-0-13-468599-1");
    assertTrue(found.isPresent());
    assertEquals("Clean Code", found.get().title());
}

// No files created, no cleanup needed, runs anywhere
```

### Entity Identity

Every entity needs an identifier. We use a simple interface:

```java
public interface Identifiable<ID> {
    ID getId();
}

public record Book(
    String isbn,    // This is the ID
    String title,
    String author
) implements Identifiable<String> {
    @Override
    public String getId() {
        return isbn;
    }
}
```

### Generic Repository

The power of generics lets us reuse the same pattern:

```java
// Works for ANY entity type!
public interface Repository<T extends Identifiable<ID>, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    // ...
}

// Same implementation works for books, users, orders...
Repository<Book, String> bookRepo = new InMemoryRepository<>();
Repository<User, UUID> userRepo = new InMemoryRepository<>();
Repository<Order, Long> orderRepo = new InMemoryRepository<>();
```

## Post-Class Work

### Exercises
After class, complete the persistence exercises:
- Implement a `JsonFileRepository<T>` that stores data in JSON files
- Create a library management system with swappable storage
- Write tests using the in-memory implementation

### Homework: Storage Layer for Library System
Design and implement a complete storage layer:
- Define entity records (Book, Member, Loan)
- Create repository interfaces with custom query methods
- Implement both InMemory and JsonFile versions
- Ensure all tests pass with InMemory repository

## Connection to Previous Weeks

| Week | Focus | Connection |
|------|-------|------------|
| Week 3 | Interfaces as contracts | Repository is another contract |
| Week 4 | Wishful programming | Design how storage SHOULD work |
| Week 7 | Maps | InMemory repository uses Map internally |
| Week 8 | GUI as Interfaces | View was an interface, now Repository is too |
| **Week 9** | **Persistence as Interfaces** | **Apply interfaces to data storage** |

## Looking Ahead
- Week 10: Example projects with complete architecture (MVC + Repository)
- Weeks 11-14: Your project will use these patterns
- The 2-week gap: Implement your designed storage layer

## Verification Checklist

Before class, ensure you can:
- [ ] Explain what the Repository pattern is and why it matters
- [ ] Describe the CRUD operations
- [ ] Implement a simple in-memory repository
- [ ] Explain why tests should use in-memory repositories

## Getting Help
- Review Week 8 materials on interfaces (Repository follows same principles)
- Check the pre-class reading for pattern diagrams
- Come to class with specific questions
