# Week 3: Interfaces as Contracts - The Solution

## Overview

Welcome to the turning point of this course! Remember all those problems from Week 2? The circular dependencies, the testing nightmares, the cascading changes? This week, we solve ALL of them with one elegant concept: **interfaces**.

An interface is a contract that defines WHAT something can do without specifying HOW it does it. This simple idea transforms everything.

## The Transformation

Last week you experienced:
- 🔥 Tight coupling between classes
- 🔥 Impossible testing scenarios
- 🔥 Cascading changes everywhere
- 🔥 Circular dependencies
- 🔥 Code duplication

This week you'll learn:
- ✅ How interfaces break dependencies
- ✅ How to test with mock implementations
- ✅ How to isolate changes
- ✅ How to eliminate circular dependencies
- ✅ How to share behavior through contracts

## Learning Objectives

By the end of this week, you will:

1. **Understand interfaces as contracts** - Define what classes must do, not how they do it
2. **Break tight coupling** - Depend on abstractions, not concrete classes
3. **Enable testing in isolation** - Test components independently with mock implementations
4. **Eliminate circular dependencies** - Both classes depend on interfaces, not each other
5. **Design for change** - Add new implementations without modifying existing code
6. **Separate data from behavior** - Use records for data, interfaces for behavior

## Time Commitment

- **Pre-Class Preparation:** 2-2.5 hours
  - Reading: 60-75 minutes (This is crucial reading!)
  - Exercises: 60-75 minutes

- **In-Class Session:** 3 hours (17:00-20:00)
  - Live redesign of Week 2's game with interfaces
  - Experience the dramatic improvement
  - See testing become trivial

- **Post-Class Work:** 2-3 hours
  - Rebuild ALL Week 2 features with interfaces
  - Compare the difference
  - Celebrate the improvement!

## Pre-Class Preparation

### Reading Assignment

Read through `pre-class/reading.md` to learn:
- What interfaces are and why they matter
- The difference between interfaces and classes
- How interfaces solve Week 2's problems
- Records vs Interfaces: When to use each
- Programming to interfaces, not implementations

### Pre-Class Exercises

Complete the exercises in `pre-class/exercises/`:
- Define your first interfaces
- Separate contracts from implementations
- Create multiple implementations of the same interface
- Write tests using mock implementations

Run tests with: `mvn test`

### Verification

Check your understanding with `pre-class/verification.md` before class.

## During Class (17:00-20:00)

We'll rebuild the Week 2 game features using interfaces:
- Design interfaces for Player, Monster, Combat
- Create interfaces for game modes (no more if-else!)
- Break the circular dependency with Guild interfaces
- Write clean, isolated tests
- Add new features without breaking anything

**The contrast with Week 2 will be dramatic!**

## Post-Class Assignments

### Main Exercises

In `post-class/exercises/`, you'll rebuild everything from Week 2:
1. **Guild System** - With clean interfaces, no circular dependencies
2. **Quest System** - Testable, extensible quest interfaces
3. **Crafting System** - Craft interface with multiple implementations
4. **NPC System** - NPC interfaces for different NPC types
5. **Difficulty System** - Strategy pattern with difficulty interfaces

### The Amazing Difference

What took hours of frustration in Week 2 will now be:
- Clean and organized
- Fully testable
- Easy to extend
- Understandable

### Success Criteria

Use `post-class/rubric.md` to evaluate your work:
- Proper use of interfaces
- Clean separation of concerns
- Testability of components
- Ease of adding new features

### Getting Help

Check `post-class/hints.md` for guidance on:
- Designing good interfaces
- Avoiding interface pitfalls
- Testing strategies
- Implementation patterns

### Extension Challenges

For those who want to go deeper, see `post-class/extensions/`:
- ⭐ **Compare**: Document the differences between Week 2 and Week 3 solutions
- ⭐⭐ **Extend**: Add features that would have been impossible in Week 2
- ⭐⭐⭐ **Design**: Create your own interface hierarchy for a new game system

## Important Notes

### The "Aha!" Moment

This week is designed to be a revelation. You'll likely experience:
- "Why didn't we start with this?"
- "This makes everything so much easier!"
- "I can finally test things properly!"
- "I never want to write Week 2 code again!"

### Key Concepts to Master

1. **Interface as Contract**: Defines capabilities without implementation
2. **Multiple Implementations**: One interface, many ways to fulfill it
3. **Dependency Inversion**: Depend on abstractions, not concretions
4. **Testing with Mocks**: Create test implementations easily
5. **Open/Closed Principle**: Open for extension, closed for modification

### Records vs Interfaces

**Use Records for Data:**
```java
public record PlayerData(String name, int level, int health) {}
```

**Use Interfaces for Behavior:**
```java
public interface IPlayer {
    void attack(IMonster target);
    void takeDamage(int amount);
}
```

## Looking Ahead

After this week, you'll never want to code without interfaces again. Week 4 will teach you "Wishful Programming" - designing with interfaces from the top down, assuming implementations exist before writing them.

## Key Takeaways

- **Interfaces solve the coupling problem** - Depend on contracts, not implementations
- **Testing becomes trivial** - Mock implementations for isolated testing
- **Change becomes safe** - Add new implementations without breaking existing code
- **Design becomes clear** - Interfaces document what components do
- **Collaboration becomes easier** - Teams can work on different implementations

## Remember

The struggle of Week 2 was necessary to appreciate this week's solution. The pain you felt is what motivates better design. Now you understand WHY interfaces exist and WHEN to use them.

This week transforms you from someone who writes code to someone who designs systems.

Welcome to professional software development!