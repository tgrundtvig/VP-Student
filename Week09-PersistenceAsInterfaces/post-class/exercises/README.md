# Library Management System Exercise

## Overview

In this exercise, you'll complete a library management system that demonstrates the **Repository pattern**. The system supports books, members, and loans, with swappable storage backends.

This exercise shows the power of persistence-as-interface:
- **Tests run instantly** using in-memory repositories
- **Same code works** with memory, file, or database storage
- **Business logic is separate** from storage concerns

## Learning Objectives

By completing this exercise, you will:
- Implement a file-based repository using JSON
- Complete service layer methods that use repositories
- See how the same tests work regardless of storage backend
- Understand the value of repository abstraction

## Your Tasks

You need to implement **3 TODO areas** across different layers:

### 1. JsonFileRepository (7 methods)

**File:** `src/main/java/dk/viprogram/week09/library/JsonFileRepository.java`

Implement the CRUD operations to store entities in a JSON file:

| Method | Purpose |
|--------|---------|
| `save(entity)` | Store entity, update if exists |
| `findById(id)` | Find entity by ID |
| `findAll()` | Return all entities |
| `delete(entity)` | Remove entity |
| `deleteById(id)` | Remove entity by ID |
| `existsById(id)` | Check if entity exists |
| `count()` | Count entities |

The helper methods `readFromFile()` and `writeToFile()` are provided.

### 2. LibraryService.borrowBook()

**File:** `src/main/java/dk/viprogram/week09/library/LibraryService.java`

Implement the logic to borrow a book:
1. Verify book exists and is available
2. Verify member exists
3. Create and save a loan record
4. Mark the book as unavailable

### 3. LibraryService.returnBook()

**File:** `src/main/java/dk/viprogram/week09/library/LibraryService.java`

Implement the logic to return a book:
1. Verify loan exists and isn't already returned
2. Mark the loan as returned
3. Mark the book as available

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                       LibraryService                         │
│  (Business Logic - knows nothing about storage details)      │
└─────────────────────────────────────────────────────────────┘
        │                    │                    │
        ▼                    ▼                    ▼
┌──────────────┐    ┌──────────────┐    ┌──────────────┐
│ Repository   │    │ Repository   │    │ Repository   │
│ <Book>       │    │ <Member>     │    │ <Loan>       │
└──────────────┘    └──────────────┘    └──────────────┘
        △                    △                    △
        │                    │                    │
   ┌────┴────┐          ┌────┴────┐          ┌────┴────┐
   │         │          │         │          │         │
┌──────┐ ┌──────┐   ┌──────┐ ┌──────┐   ┌──────┐ ┌──────┐
│Memory│ │JSON  │   │Memory│ │JSON  │   │Memory│ │JSON  │
│Repo  │ │File  │   │Repo  │ │File  │   │Repo  │ │File  │
└──────┘ └──────┘   └──────┘ └──────┘   └──────┘ └──────┘
```

## Entity Records

The system uses three entity types (already implemented):

### Book
```java
record Book(String isbn, String title, String author, boolean available)
```
- ISBN is the unique identifier
- `available` tracks if book can be borrowed
- `borrow()` and `returnBook()` create copies with updated availability

### Member
```java
record Member(String id, String name, String email)
```
- ID is auto-generated when registering
- Simple data holder for library members

### Loan
```java
record Loan(String id, String bookIsbn, String memberId,
            LocalDate borrowDate, LocalDate dueDate, boolean returned)
```
- Tracks who borrowed which book and when
- `markReturned()` creates a copy marked as returned
- `isOverdue()` checks if past due date

## Running the Tests

### Run all tests
```bash
mvn test
```

### Run specific test class
```bash
mvn test -Dtest=LibraryServiceTest
mvn test -Dtest=JsonFileRepositoryTest
```

## Key Files

| File | Role | Your Task |
|------|------|-----------|
| `Identifiable.java` | Interface for entities with ID | Read only |
| `Repository.java` | Generic repository interface | Read only |
| `Book.java` | Book entity record | Read only |
| `Member.java` | Member entity record | Read only |
| `Loan.java` | Loan entity record | Read only |
| `InMemoryRepository.java` | In-memory implementation | Read only (provided) |
| `JsonFileRepository.java` | JSON file implementation | **TODO: 7 methods** |
| `LibraryService.java` | Business logic service | **TODO: 2 methods** |
| `LibraryServiceTest.java` | Service tests (in-memory) | Run to verify |
| `JsonFileRepositoryTest.java` | File repository tests | Run to verify |

## Suggested Order

1. **Start with JsonFileRepository** - Implement the basic CRUD methods
2. **Run JsonFileRepositoryTest** - Verify file storage works
3. **Then LibraryService.borrowBook()** - Use the repositories
4. **Then LibraryService.returnBook()** - Complete the loan cycle
5. **Run LibraryServiceTest** - Verify all business logic

## Common Issues

### JSON file not created
Check that `writeToFile()` is being called in your `save()` method.

### Tests fail with "already returned"
Make sure `borrowBook()` creates a new loan (not returned), and that
you're checking `loan.returned()` correctly in `returnBook()`.

### Gson serialization errors
Make sure all entities are records with public accessors. Gson handles
records automatically.

### Tests interfere with each other
Each test should start clean. The `@BeforeEach` and `@AfterEach` methods
handle cleanup by deleting the test file.

## What You'll Learn

After completing this exercise, you'll understand:

1. **Repository pattern** - Abstract storage behind an interface
2. **Swappable backends** - Same service works with any repository
3. **Test isolation** - In-memory repos make tests fast and reliable
4. **Separation of concerns** - Business logic doesn't know about files

## Extension Challenges

If you finish early, try:

1. **Add findByAuthor()** - Custom query method in a BookRepository interface
2. **Add loan history** - Track all loans (not just active ones) for a member
3. **Add reservations** - Let members reserve books that are currently borrowed
4. **Add late fees** - Calculate fees for overdue returns

## Need Help?

- Check `../hints.md` for solution code
- Review the InMemoryRepository as a working example
- The TODO comments have step-by-step guidance
- Run tests frequently to check progress
