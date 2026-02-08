# Week 13 Pre-Class Exercises: Test Plan Document

## Overview

Before class, you need to plan your testing strategy. This exercise asks you to think through
what you will test, how you will test it, and what test doubles (mocks, in-memory implementations)
you need to create.

**Time estimate: 60-90 minutes**

## Instructions

1. Open `src/main/java/dk/viprogram/week13/TestPlanDocument.java`
2. Fill in each section with your project's test strategy
3. Run the tests to verify completeness

## What to Fill In

### Test Categories
List the categories of tests you plan to write:
- Model tests (records, factory methods, modification methods)
- Repository tests (CRUD, queries)
- Controller tests (integration with MockView + InMemoryRepository)

### Test Cases Per Interface
For each interface, list the specific test cases you will write. Each test case should have:
- A descriptive name
- What scenario it tests
- What the expected outcome is

### Mock Strategy
Describe how you will create test doubles:
- What queues/lists does your MockView need?
- How will your InMemoryRepository work?
- Are there other interfaces that need mocks?

### Test Case Count
Estimate how many tests you will write per category. A good target:
- 3-5 model tests
- 5-8 repository tests
- 5-10 controller tests

## Running the Tests

```bash
mvn test
```

Tests verify:
- You have defined at least 3 test categories
- Each category has at least 2 test cases
- Your mock strategy is documented
- The total test case count is reasonable (at least 10)

## Tips

- Reference your Week 12 design document for interface methods
- Each interface method should have at least one test case
- Think about normal cases AND edge cases (empty, missing, cancel)
- Group test cases logically (all repository tests together, etc.)
