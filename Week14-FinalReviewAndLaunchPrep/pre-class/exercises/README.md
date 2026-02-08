# Week 14 Pre-Class Exercises: Implementation Roadmap

## Overview

Before the final class, you need to plan exactly how you will spend your 2-week implementation
period. This exercise asks you to break your implementation into phases, tasks, and priorities
so you can start coding with confidence.

**Time estimate: 60-90 minutes**

## Instructions

1. Open `src/main/java/dk/viprogram/week14/ImplementationRoadmap.java`
2. Fill in each section with your implementation plan
3. Run the tests to verify completeness

## What to Fill In

### Implementation Phases
Break your 2-week implementation into phases (typically 4-6 phases):
- Phase 1: Model layer (records, enums)
- Phase 2: Repository layer (interfaces, InMemory implementations)
- Phase 3: Controller and MockView
- Phase 4: ConsoleView
- Phase 5: Composition root and sample data
- Phase 6: Polish and extras

### Task Breakdown
For each phase, list the specific tasks (files to create, classes to implement):
- Which Java files will you create?
- What methods need implementing?
- Which tests should pass after this phase?

### Priority Order
Rank your tasks by priority:
- P1 (Must have): Core MVP features
- P2 (Should have): Important but not essential for demo
- P3 (Nice to have): Stretch goals, optional features

### Time Estimates
Estimate how long each phase will take.

## Running the Tests

```bash
mvn test
```

Tests verify:
- You have defined at least 4 implementation phases
- Each phase has at least 2 tasks
- You have prioritized tasks (P1/P2/P3)
- Time estimates are provided
- The total estimated time is reasonable

## Tips

- Be realistic about time: implementation always takes longer than you think
- Front-load the critical path: records -> repository -> tests passing -> controller -> view
- Leave buffer time: at least 2-3 days should be unplanned for unexpected issues
- Plan for a demo: what will you show? Make sure those features are P1
