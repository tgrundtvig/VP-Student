# Week 13 Post-Class: Write Tests and Mock Implementations

## Overview

This is the most hands-on post-class exercise of Phase 3. You will write actual JUnit test
classes for your project's interfaces, create mock implementations for your View, and create
in-memory implementations for your Repository.

**Important:** Your tests may not compile yet because the real interfaces and records do not
exist as Java code. That is okay! The goal is to write the test logic. In Week 14 you will
set up the actual project and move these tests into it.

**Time estimate: 2-3 hours**

## What You Will Create

A `TestSuiteReport.java` file documenting your actual test code:

1. **Test code snippets** for your model, repository, and controller tests
2. **Mock implementation** sketches showing the complete MockView
3. **InMemoryRepository implementation** sketches
4. **Reflection** on what tests revealed about your design

## Instructions

1. Open `src/main/java/dk/viprogram/week13/TestSuiteReport.java`
2. Write actual JUnit test code (as strings) for each category
3. Write mock and in-memory implementation code
4. Document any design changes the tests prompted
5. Run tests to verify completeness

## Why Write Tests as Strings?

In your actual exam project (set up in Week 14), these will be real Java files. For now, we
capture them as strings in this exercise so that:
- You think through the test logic
- You have a reference when setting up the real project
- The meta-tests can verify you have done the work

## What to Write

### Model Tests
Write at least 2 test methods testing your records:
- Factory method creates with generated ID
- Modification method returns new instance
- toString produces readable output

### Repository Tests
Write at least 3 test methods testing your InMemoryRepository:
- Save and findById
- FindAll with items
- Domain-specific query (findByCategory, searchByTitle, etc.)

### Controller Tests
Write at least 3 test methods testing your Controller with mocks:
- Successful add operation
- Cancelled operation (no change)
- List/display operation

### Mock View Implementation
Write the complete MockView class code, showing:
- All internal queues and lists
- All interface method implementations
- Programming and verification helper methods

### InMemoryRepository Implementation
Write the complete InMemoryRepository class code, showing:
- The HashMap storage
- All interface method implementations

## Running the Tests

```bash
mvn test
```

Tests verify:
- You have written model test code
- You have written repository test code
- You have written controller test code
- Your mock implementation is described
- Your InMemoryRepository implementation is described
- You have reflected on design changes

## Tips

1. **Start with the Repository tests** --- they are the simplest
2. **Use the Arrange-Act-Assert pattern** consistently
3. **Give tests descriptive names** using @DisplayName
4. **Write the mock first**, then the tests that use it
5. **If a test is hard to write, your design may need adjustment** --- document this
