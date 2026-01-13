# Week 9 Self-Assessment Rubric

Use this rubric to evaluate your own work before class.

## Pre-Class Exercises

### InMemoryRepository Implementation

| Criteria | Not Yet | Developing | Proficient | Exemplary |
|----------|---------|------------|------------|-----------|
| **save()** | Not implemented | Stores but doesn't return | Stores and returns entity | Handles updates correctly |
| **findById()** | Not implemented | Returns value but not Optional | Returns Optional correctly | Handles null gracefully |
| **findAll()** | Not implemented | Returns internal collection | Returns defensive copy | Returns new ArrayList |
| **delete()** | Not implemented | Sometimes works | Works correctly | Handles missing entity |
| **count()** | Not implemented | Returns wrong value | Returns correct count | Always accurate |

**All tests pass:** Run `mvn test` and verify all tests in `InMemoryRepositoryTest` pass.

## Post-Class Exercises

### JsonFileRepository Implementation

| Criteria | Not Yet | Developing | Proficient | Exemplary |
|----------|---------|------------|------------|-----------|
| **save()** | Not implemented | Creates file but wrong content | Saves correctly | Updates existing |
| **findById()** | Not implemented | Returns wrong type | Returns Optional correctly | Efficient implementation |
| **findAll()** | Not implemented | Throws on missing file | Returns empty list when no file | Handles all edge cases |
| **delete()** | Not implemented | Sometimes leaves orphans | Removes correctly | Handles missing entity |

**All tests pass:** Run `mvn test -Dtest=JsonFileRepositoryTest` and verify all tests pass.

### LibraryService Implementation

| Criteria | Not Yet | Developing | Proficient | Exemplary |
|----------|---------|------------|------------|-----------|
| **borrowBook()** | Not implemented | Missing validations | All validations present | Clear error messages |
| **returnBook()** | Not implemented | Doesn't update book | Updates loan and book | Handles all edge cases |
| **Error handling** | No exceptions | Wrong exception types | Correct exceptions | Informative messages |

**All tests pass:** Run `mvn test -Dtest=LibraryServiceTest` and verify all tests pass.

## Understanding Check

Can you answer these questions?

### Conceptual Understanding

- [ ] I can explain why Repository should be an interface
- [ ] I can describe how in-memory repositories help testing
- [ ] I understand the difference between file and memory persistence
- [ ] I can explain what "swappable backends" means

### Practical Skills

- [ ] I can implement a basic repository from the interface
- [ ] I can use `Optional` correctly for nullable returns
- [ ] I can read and write JSON files using Gson
- [ ] I can use streams for filtering collections

## Common Mistakes to Check

1. **Modifying returned collections**
   - `findAll()` should return a *copy*, not the internal collection

2. **Not handling missing file**
   - `findAll()` should return empty list, not throw exception

3. **Wrong exception types**
   - Use `IllegalArgumentException` for invalid input
   - Use `IllegalStateException` for invalid state

4. **Not updating both loan and book**
   - `borrowBook()` must save both the loan AND the updated book
   - `returnBook()` must update both as well

## Ready for Class?

You're ready if you can:

- [ ] Explain the Repository pattern
- [ ] Implement a repository for any entity type
- [ ] Write services that use repositories through interfaces
- [ ] Test with in-memory repositories

## Scoring

| Level | Pre-Class | Post-Class |
|-------|-----------|------------|
| **Not Yet** | < 50% tests pass | < 50% tests pass |
| **Developing** | 50-75% tests pass | 50-75% tests pass |
| **Proficient** | 75-90% tests pass | 75-90% tests pass |
| **Exemplary** | 100% tests pass + understanding | 100% tests + extensions |
