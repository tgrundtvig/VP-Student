# Week 13: Project Testing Strategy

## Overview

You have spent three weeks designing your project: architecture (Week 11), interface details
(Week 12), and now testing (Week 13). This week you close the design loop by writing tests
against your interfaces before writing any implementation code.

This is test-driven design in action: by writing tests first, you validate that your interfaces
are complete, testable, and well-defined. If you cannot write a test, something is missing in
your design.

## Learning Objectives

By the end of this week, you should be able to:

1. **Write** JUnit 5 tests against interfaces (not implementations)
2. **Create** mock implementations for testing View interfaces
3. **Create** in-memory implementations for testing Repository interfaces
4. **Design** a comprehensive test plan that covers normal and edge cases

## Pre-Class Preparation

**Time estimate: 90-120 minutes**

1. **Read** the [pre-class reading](pre-class/reading.md) on testing against interfaces
2. **Complete** the exercises in [pre-class/exercises](pre-class/exercises):
   - Create a `TestPlanDocument.java` documenting your test strategy
   - List test categories, test cases per interface, and mock approach
3. **Verify** your preparation with the [verification checklist](pre-class/verification.md)

## Post-Class Work

**Time estimate: 2-3 hours**

After class, you will write actual test code:

1. **Write** JUnit test classes for your project's interfaces
2. **Create** mock implementations (MockView, InMemoryRepository)
3. **Run** the tests to verify they compile and express your intentions
4. **Review** whether tests reveal gaps in your interface design

See [post-class/exercises](post-class/exercises) for detailed instructions.

## What Happens in Class

### First Half (17:10-18:20)
- Live coding: writing tests against an interface before implementation (20 min)
- Demonstration: MockView and InMemoryRepository patterns (15 min)
- Workshop: students write tests for their own interfaces (25 min)

### Second Half (18:30-19:45)
- Continue writing tests individually (20 min)
- Peer review: does your partner's test suite cover the key scenarios? (15 min)
- Discussion: what did tests reveal about your design? (10 min)
- Start post-class exercises together (20 min)
- Individual help (10 min)

## Connection to Previous Weeks

| Week | Concept | How It Applies This Week |
|------|---------|--------------------------|
| Week 3 | Interface contracts | Tests verify the contracts you defined |
| Week 4 | Wishful programming | Tests describe behavior you wish existed |
| Week 8 | MVC pattern | MockView enables testing without real UI |
| Week 9 | Repository pattern | InMemoryRepository enables testing without files |
| Week 11 | Architecture | Your hierarchy determines what to test |
| Week 12 | JavaDoc and signatures | JavaDoc becomes test case descriptions |

## Looking Ahead

- **Week 14:** Final review, project setup, launch preparation
- **2-week gap:** Implement your system (tests guide the implementation!)

## Why Test Before Implementing?

### 1. Tests Validate Your Design
If a test is hard to write, the interface might be poorly designed. Better to discover this
now than during implementation.

### 2. Tests Are a Specification
A passing test says: "This behavior is required." Your test suite becomes a living specification
of what your system must do.

### 3. Tests Guide Implementation
When you start implementing, you have a clear target: make the tests pass. No guessing about
what the code should do.

### 4. Tests Catch Regressions
As you implement, tests catch unintended changes. Implement one feature, run tests, see if
anything broke.

## The Testing Pyramid for Your Project

```
         /\
        /  \         Integration Tests
       /    \        (Controller + MockView + InMemoryRepo)
      /------\
     /        \      Unit Tests
    /   Repo   \     (InMemoryRepository methods)
   /   Tests    \
  /--------------\
 /    Model       \   Unit Tests
/    Tests         \  (Record factory methods, equals, toString)
/------------------\
```

- **Model tests:** Verify records, factory methods, modification methods
- **Repository tests:** Verify CRUD and queries using InMemoryRepository
- **Integration tests:** Verify Controller coordinates View and Repository correctly

## Verification Checklist

Before class, ensure you:
- [ ] Have read the pre-class reading on testing against interfaces
- [ ] Have completed the TestPlanDocument with test categories and cases
- [ ] Can explain why MockView is needed
- [ ] Can explain why InMemoryRepository is used instead of file-based
- [ ] All pre-class tests pass
- [ ] Have your Week 12 design document available for reference

## Getting Help

- Review how the example projects' tests are structured
- Look at the MockTaskView and InMemoryTaskRepository patterns
- Bring specific interface methods you are struggling to test to class
- Discuss test strategies with classmates

---

**This week's mantra:** "If you cannot test it, you cannot trust it."
