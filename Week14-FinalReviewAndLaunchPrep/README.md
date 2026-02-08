# Week 14: Final Review and Launch Preparation

## Overview

This is the final class before the 2-week implementation period. Everything you have designed
over the past four weeks --- architecture, interfaces, records, tests --- comes together into
a real Maven project that you will implement during the break.

By the end of tonight, you will have:
- A reviewed and finalized design
- A working Maven project with package structure
- Interfaces and records as actual Java code
- Tests that compile and run (and currently fail, awaiting implementation)
- A clear implementation roadmap

**After tonight, the only thing left is to write the implementation code.**

## Learning Objectives

By the end of this week, you should be able to:

1. **Set up** a complete Maven project for your exam project
2. **Create** the package structure matching your architecture layers
3. **Translate** your design documents into actual Java interfaces, records, and tests
4. **Plan** the implementation order starting from leaf nodes
5. **Use** git to manage your project's version history

## Pre-Class Preparation

**Time estimate: 90-120 minutes**

1. **Read** the [pre-class reading](pre-class/reading.md) on going from design to implementation
2. **Complete** the exercises in [pre-class/exercises](pre-class/exercises):
   - Create an `ImplementationRoadmap.java` with phases, tasks, and priorities
3. **Verify** your preparation with the [verification checklist](pre-class/verification.md)
4. **Bring** all your design documents from Weeks 11-13

## Post-Class Work

**Time estimate: 2-3 hours (then 2 weeks of implementation)**

After class, you will set up your actual exam project:

1. **Create** the Maven project
2. **Create** the package structure
3. **Copy** interfaces, records, and tests from your design documents
4. **Verify** the project compiles
5. **Begin** implementation following your roadmap

See [post-class/exercises](post-class/exercises) for detailed instructions.

## What Happens in Class

### First Half (17:10-18:20)
- **Final design review** in pairs (20 min):
  - Each student presents their complete design (architecture + interfaces + tests)
  - Partner checks: are interfaces complete? Are tests thorough? Is scope right?
- **Live coding: setting up a Maven project** (20 min):
  - Creating the project from scratch
  - Package structure matching architecture layers
  - Copying interfaces and records
- **Discussion: implementation strategies** (20 min):
  - Start with leaf nodes (records, then InMemoryRepository)
  - Work upward (Controller, then View)
  - Run tests at each step

### Second Half (18:30-19:45)
- **Set up your own project** (30 min):
  - Create Maven project
  - Create packages
  - Start copying interfaces and records
- **Verify project compiles** (10 min)
- **Implementation roadmap review** with teacher (15 min)
- **Final Q&A and individual help** (20 min)

## Connection to Previous Weeks

| Week | What You Created | What You Use It For Now |
|------|-----------------|------------------------|
| Week 10 | Project proposal | The original vision |
| Week 11 | Architecture document | Package structure and layers |
| Week 12 | Interface specs + JavaDoc | Actual Java interface files |
| Week 13 | Test suite + mocks | Actual Java test files |

## The Implementation Period

After tonight, you have **2 weeks** to implement your project.

### Recommended Timeline

**Week 1: Core Implementation**
- Day 1-2: Records, enums, and InMemoryRepository
- Day 3-4: Controller with basic operations
- Day 5-6: ConsoleView (text-based UI)
- Day 7: Run tests, fix issues

**Week 2: Polish and Extras**
- Day 1-2: Remaining operations and edge cases
- Day 3-4: JavaFX View (if planned) or file-based Repository
- Day 5: Sample data seeding and demo preparation
- Day 6: Final testing and code cleanup
- Day 7: Buffer for unexpected issues

### Implementation Order

Start from the bottom of your architecture and work up:

```
IMPLEMENT FIRST (no dependencies):
  1. Records (model layer)
  2. Enums
  3. InMemoryRepository implementations

IMPLEMENT SECOND (depends on model + repository):
  4. MockView
  5. Controller
  → Run controller tests here! ←

IMPLEMENT THIRD (depends on everything):
  6. ConsoleView
  7. Composition root (main class)
  → Full system works here! ←

IMPLEMENT LAST (optional polish):
  8. JavaFX View
  9. File-based Repository
  10. Sample data seeding
```

## Verification Checklist

Before class, ensure you:
- [ ] Have read the pre-class reading on implementation strategies
- [ ] Have completed the ImplementationRoadmap exercise
- [ ] Have all design documents from Weeks 11-13 ready
- [ ] Have IntelliJ IDEA ready
- [ ] Have Maven installed and working
- [ ] Have git installed and working
- [ ] Understand the implementation order (bottom-up)

## Getting Help During Implementation

During the 2-week implementation period:
- **Review your design documents** --- they have the answers
- **Run your tests frequently** --- they tell you if you are on track
- **Start simple** --- get basic functionality working first
- **Ask classmates** --- they are going through the same process
- **Contact the teacher** --- if you are truly stuck

## Final Advice

1. **Trust your design.** You have spent 4 weeks on it. Follow it.
2. **Implement bottom-up.** Records first, then Repository, then Controller, then View.
3. **Run tests after each class.** Green tests mean you are on track.
4. **Do not redesign mid-implementation.** Small adjustments are fine; major redesigns are not.
5. **Focus on MVP.** Get the core working before adding extras.
6. **Commit often.** Use git to save your progress frequently.

---

**This week's mantra:** "The design is done. Now make it real."
