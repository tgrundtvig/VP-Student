# Week 10: Example Projects Showcase

## Overview

This week marks the transition from learning patterns to applying them. You've now learned all the core patterns:
- **Interfaces as contracts** (Week 3)
- **Wishful programming** (Week 4)
- **Data structures** (Weeks 5-7)
- **MVC pattern** (Week 8)
- **Repository pattern** (Week 9)

Now it's time to see how these patterns combine into complete, well-designed applications.

## Learning Objectives

By the end of this week, you should be able to:
1. **Analyze** a complete project's interface hierarchy
2. **Identify** how MVC and Repository patterns work together
3. **Evaluate** design decisions in existing projects
4. **Draft** an initial proposal for your exam project

## This Week is Different

Unlike previous weeks where you implemented specific patterns, this week focuses on:
- **Studying** complete example projects
- **Understanding** how all patterns integrate
- **Preparing** for your own exam project design

There is less coding and more analysis/planning.

## Example Projects

We provide two complete example projects for you to study:

### 1. Task Manager
A personal task management application demonstrating:
- Complete MVC architecture
- Repository pattern for persistence
- Clean interface hierarchy
- Comprehensive test suite

**Location:** [example-projects/task-manager](../example-projects/task-manager)

### 2. Quiz Application
An interactive quiz system demonstrating:
- Question and answer domain modeling
- Score tracking and persistence
- Multiple quiz types through interfaces
- Strategy pattern for scoring

**Location:** [example-projects/quiz-app](../example-projects/quiz-app)

## Pre-Class Preparation

**Time estimate: 90-120 minutes**

This week's preparation is different - you'll study existing code rather than write new code.

1. **Read** the [pre-class reading](pre-class/reading.md) on analyzing project architecture
2. **Study** both example projects:
   - Read the README files
   - Examine the interface hierarchies
   - Trace the data flow through MVC layers
   - Look at how tests are organized
3. **Complete** the analysis exercises in [pre-class/exercises](pre-class/exercises)
4. **Come prepared** with questions about design decisions

## Post-Class Work

**Time estimate: 2-3 hours**

After class, you'll start planning your exam project:

1. **Review** the project proposal template
2. **Brainstorm** project ideas
3. **Draft** your initial interface sketch
4. **Write** your project proposal

See [post-class/exercises](post-class/exercises) for detailed instructions.

## Key Questions to Consider

As you study the example projects, ask yourself:

### Architecture Questions
- Where are the interfaces defined?
- How do the layers communicate?
- What would change if we swapped the View implementation?
- What would change if we swapped the Repository implementation?

### Design Decision Questions
- Why did we choose these interface methods?
- Why are entities implemented as records?
- Where is business logic located (and why)?
- How do tests verify behavior without touching persistence?

### Application Questions
- How could I apply this structure to my project idea?
- What interfaces would my project need?
- What entities (records) would I define?
- What would my Repository interface look like?

## Connection to Previous Weeks

| Week | Pattern | How It Appears in Examples |
|------|---------|---------------------------|
| Week 3 | Interfaces | Core interfaces define all contracts |
| Week 4 | Wishful programming | High-level code written before low-level |
| Week 5-6 | Data structures | Lists and trees for organizing data |
| Week 7 | Maps/Strategies | Configuration and behavior selection |
| Week 8 | MVC | View as interface, Controller coordinates |
| Week 9 | Repository | All persistence through Repository interface |

## Looking Ahead

- **Weeks 11-14:** Design your exam project in detail
  - Week 11: Project Architecture Workshop
  - Week 12: Project Design Deep Dive
  - Week 13: Project Testing Strategy
  - Week 14: Final Review and Launch Preparation
- **2-week gap:** Implement your complete designed system

## What Makes a Good Exam Project?

As you think about your project, consider:

### Scope
- **Too small:** Simple calculator, basic to-do list
- **Just right:** Task manager with categories, quiz system with scores, library system
- **Too large:** Full e-commerce platform, social media clone

### Demonstrable Skills
Your project should demonstrate:
- [ ] Interface-first design (multiple interfaces)
- [ ] MVC pattern (separable view)
- [ ] Repository pattern (swappable persistence)
- [ ] Comprehensive tests (against interfaces)
- [ ] Clean separation of concerns

### Achievable in 2 Weeks
Remember: You have 2 weeks to implement what you design. A well-designed smaller project is better than an ambitious project you can't finish.

## Verification Checklist

Before class, ensure you:
- [ ] Have studied both example projects
- [ ] Can explain the interface hierarchy of Task Manager
- [ ] Can trace a user action through the MVC layers
- [ ] Can explain why tests use InMemoryRepository
- [ ] Have at least one project idea to discuss

## Getting Help

- Study the example projects thoroughly - they answer many questions
- Compare patterns to Weeks 8 and 9 materials
- Come to class with specific questions about design decisions
- Discuss project ideas with classmates

---

**This week's mantra:** "Good design makes implementation straightforward."
