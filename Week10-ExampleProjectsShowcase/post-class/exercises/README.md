# Week 10 Post-Class: Project Proposal

## Overview

This week's post-class work is different from previous weeks. Instead of coding exercises, you'll create your **exam project proposal** - the initial design document for the system you'll build during the 2-week implementation period.

## What You'll Create

A `ProjectProposal.java` file containing:
1. Project overview and motivation
2. Main entities (what data will you manage?)
3. Core interfaces (what contracts define your system?)
4. User operations (what can users do?)
5. Architecture sketch (how do layers connect?)

## Getting Started

1. Copy the template from `src/main/java/dk/viprogram/week10/ProjectProposal.java`
2. Fill in each section with your project's design
3. Run tests to verify completeness
4. Refine based on feedback

## Project Ideas

If you need inspiration, consider these domains:

### Inventory Management
- Track products, categories, stock levels
- Support adding, updating, selling items
- Generate low-stock alerts

### Recipe Manager
- Store recipes with ingredients and steps
- Search by ingredient or category
- Scale recipes for different servings

### Personal Finance Tracker
- Record income and expenses
- Categorize transactions
- Generate reports and summaries

### Workout Logger
- Track exercises, sets, reps, weights
- Plan workout routines
- View progress over time

### Simple Booking System
- Manage resources (rooms, equipment, etc.)
- Handle time-slot bookings
- Prevent double-booking

## Proposal Requirements

Your proposal must include:

### 1. Clear Problem Statement
- What problem does your application solve?
- Who is the target user?
- What value does it provide?

### 2. Entity Definitions
- What are the main data objects?
- What properties do they have?
- How are they related?

### 3. Interface Specifications
- What interfaces define the system contracts?
- What methods does each interface have?
- Include View, Repository, and any Strategy interfaces

### 4. User Stories
- What can a user do with the application?
- List at least 5 operations
- Describe the flow for each

### 5. Architecture Diagram (text-based)
- Show the layers
- Show how they connect
- Identify what is interface vs implementation

## Scope Guidelines

### Too Small
- Single entity with basic CRUD
- No interesting business logic
- Could be completed in one day

### Just Right
- 2-4 related entities
- Multiple operations beyond basic CRUD
- Clear interface hierarchy
- Room for different implementations
- Can be completed in 2 weeks

### Too Large
- 5+ complex entities
- External integrations
- Complex algorithms
- Would take a month to implement

## Success Criteria

Your proposal is ready when:
- [ ] Someone else could implement your system from the proposal
- [ ] Interfaces are specific enough to write tests against
- [ ] Entities are well-defined with clear relationships
- [ ] Operations cover the main user needs
- [ ] Architecture follows MVC + Repository patterns

## Running Tests

```bash
mvn test
```

Tests verify that all proposal sections are filled in with sufficient detail.

## Timeline

- **Week 10:** Create initial proposal (this week)
- **Week 11:** Architecture Workshop - refine with feedback
- **Week 12:** Design Deep Dive - finalize interfaces
- **Week 13:** Testing Strategy - plan your tests
- **Week 14:** Final Review - ensure design is implementation-ready
- **2-week gap:** Implement your designed system

## Tips

1. **Start simple** - You can always add complexity later
2. **Be specific** - Vague interfaces lead to implementation confusion
3. **Think about testing** - How will you test each component?
4. **Consider the demo** - What will you show at the exam?
5. **Learn from examples** - Apply patterns from Task Manager and Quiz App
