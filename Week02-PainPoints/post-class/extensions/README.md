# Extension Challenges: Deeper Analysis

These optional challenges help you think more deeply about the problems you've encountered.

## ⭐ Basic Extensions (30-60 minutes each)

### 1. Problem Pattern Documentation

Create a document that categorizes the problems you encountered:

```markdown
# Problem Patterns in My Code

## Dependency Problems
- Class A depends on Class B which depends on Class A
- Example: [specific code example]

## Responsibility Problems
- Classes doing too many things
- Example: [specific code example]

## Testing Problems
- Can't test without full setup
- Example: [specific test that's hard to write]
```

### 2. Refactoring Attempt

Try to fix ONE of the problems without using interfaces:
- Pick the smallest problem you found
- Try to refactor it
- Document what worked and what didn't
- Notice: Even small fixes are hard!

### 3. Metrics Collection

Count and document:
- Lines of code in your largest class
- Number of files modified for each feature
- Number of if-else chains in your code
- Number of static methods/variables
- Average method length

## ⭐⭐ Intermediate Extensions (1-2 hours each)

### 1. Dependency Diagram

Create a visual diagram of your class dependencies:

```
Example:
Player ←→ Guild (circular!)
  ↓        ↓
Combat   Quest
  ↓        ↓
Monster  Item
  ↓        ↓
  Game (everything depends on Game!)
```

Tools you can use:
- Paper and pencil (photograph it)
- draw.io (free online)
- PlantUML (if you want to learn it)

### 2. Alternative Design Brainstorm

Without knowing about interfaces yet, brainstorm solutions:
- How could you reduce coupling?
- How could you make testing easier?
- How could you avoid code duplication?

Write your ideas, even if they seem impractical!

### 3. Code Smell Catalog

Create a catalog of code smells in your project:

```java
// SMELL: Long Method
// Location: Game.java, line 145-320
// Problem: Method does 10 different things
public void gameLoop() {
    // 175 lines of code...
}

// SMELL: Duplicate Code
// Location: Player.java line 88, Monster.java line 45
// Problem: Same damage calculation in two places
```

## ⭐⭐⭐ Advanced Extensions (3+ hours)

### 1. Research: SOLID Principles

Research the SOLID principles and identify which ones your code violates:

- **S**ingle Responsibility Principle
  - Find 3 classes that violate this
  - Explain how they violate it

- **O**pen/Closed Principle
  - Find code that needs modification to extend
  - Explain why it's not "open for extension"

- **L**iskov Substitution Principle
  - (This one might not apply yet)

- **I**nterface Segregation Principle
  - Find "fat" classes that do too much
  - Explain what's wrong

- **D**ependency Inversion Principle
  - Find concrete dependencies
  - Explain the problems they cause

### 2. Testing Strategy Document

Write a document explaining how you WOULD test your code if you could:

```markdown
# Ideal Testing Strategy

## What I Want to Test
- Combat damage calculations
- Quest completion logic
- Inventory management

## Why I Can't Test It Well
- Combat requires Player, Monster, Items, Game state
- Quests require entire game world
- Inventory tied to Player class

## What Would Need to Change
- [Your ideas here]
```

### 3. Complete Rewrite Planning

Plan (but don't implement) a complete rewrite:
- What would you do differently?
- How would you structure the classes?
- What would you separate?
- What would you combine?

This is great preparation for next week!

### 4. Performance Analysis

Analyze the performance implications:
- How many objects are created unnecessarily?
- Where is memory wasted?
- What calculations are repeated?
- Document with specific examples

## Research Questions

Answer these questions based on your experience:

1. **Why does coupling matter?**
   - Use examples from your code
   - Explain the real impact

2. **Why is testability important?**
   - What bugs could you have caught with better tests?
   - What confidence would better tests give you?

3. **What is technical debt?**
   - How much "debt" did you accumulate this week?
   - What would it cost to "pay it back"?

4. **Why do design patterns exist?**
   - What problems are they solving?
   - Could patterns help with your code?

## Submission (Optional)

If you complete any extensions, you can:
1. Add them to your Git repository
2. Share with classmates for discussion
3. Bring questions to next week's class

## Looking Ahead

Next week, you'll learn about **interfaces** - contracts that define what classes can do without specifying how they do it.

As you work through these extensions, try to imagine:
- What if you could test a Player without creating a real Guild?
- What if you could add new monster types without changing Combat?
- What if you could swap out save systems without changing Game?

Interfaces make all of this possible!