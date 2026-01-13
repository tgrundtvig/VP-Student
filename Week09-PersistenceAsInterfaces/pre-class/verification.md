# Week 9 Pre-Class Verification

Use this checklist to verify you've completed the pre-class work.

## Reading Completed

- [ ] I understand what the Repository pattern is
- [ ] I can explain why persistence should be behind an interface
- [ ] I know what CRUD stands for (Create, Read, Update, Delete)
- [ ] I understand why in-memory repositories are useful for testing

## Exercises Completed

Run the tests to verify your implementation:

```bash
cd pre-class/exercises
mvn test
```

### All Tests Pass

- [ ] `InMemoryRepositoryTest` - All tests pass

### Understanding Check

Can you answer these questions?

1. **Why use `Optional<T>` for `findById()` instead of returning `null`?**
   - Answer: Optional makes it explicit that the value might not exist, forcing
     callers to handle both cases. It prevents NullPointerException.

2. **Why does `save()` work for both create and update?**
   - Answer: Using the entity's ID as the map key means putting a new entity
     with the same ID automatically replaces the old one. This simplifies the API.

3. **Why return a new ArrayList in `findAll()` instead of the map's values directly?**
   - Answer: Returning `new ArrayList<>(storage.values())` creates a defensive copy.
     External code can't modify the internal storage by accident.

4. **What happens if you call `deleteById()` with an ID that doesn't exist?**
   - Answer: Nothing happens - `Map.remove()` on a nonexistent key simply returns
     null and doesn't throw an exception. This is intentional "idempotent" behavior.

## Ready for Class

You're ready for class if you can:

- [ ] Explain the Repository pattern to a classmate
- [ ] Implement a simple in-memory repository from scratch
- [ ] Write a service that uses a repository through dependency injection
- [ ] Explain why this makes testing easier

## Common Issues

### Tests won't run
Make sure you have Java 21 and Maven installed:
```bash
java -version   # Should show 21
mvn -version    # Should show Maven 3.x
```

### "UnsupportedOperationException" in tests
You haven't implemented the TODO methods yet. Replace the `throw` statements
with actual implementations.

### Type errors with generics
Make sure your `InMemoryRepository` class declaration matches:
```java
public class InMemoryRepository<T extends Identifiable<ID>, ID>
        implements Repository<T, ID> {
```
