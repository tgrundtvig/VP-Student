# Week 2: Pain Points - When Code Fights Back

## Overview

This week, we take the text-based RPG game from Week 1 and try to extend it with new features. As we do this, we'll discover that our "naive approach" creates significant challenges. This is intentional - experiencing these pain points firsthand will help you understand why better design approaches exist.

**Remember:** The struggles you face this week are normal and expected. Every programmer has written code like this. The important part is recognizing the problems and being ready to learn better approaches.

## Learning Objectives

By the end of this week, you will:

1. **Experience the challenges of tightly coupled code** - Understand how changes in one place require changes in many others
2. **Discover the difficulty of testing complex interactions** - See why testing becomes hard when everything depends on everything else
3. **Feel the pain of adding new features to rigid designs** - Experience how simple additions become complex modifications
4. **Recognize common code smells** - Identify problematic patterns in your own code
5. **Understand why "it works" isn't enough** - Appreciate that working code can still be problematic code

## Time Commitment

- **Pre-Class Preparation:** 1.5-2 hours
  - Reading: 45-60 minutes
  - Exercises: 45-60 minutes

- **In-Class Session:** 3 hours (17:00-20:00)
  - Live coding and discussion of extending the game
  - Experiencing problems together
  - Group troubleshooting

- **Post-Class Work:** 2-3 hours
  - Extending the game with challenging new features
  - Documenting the problems you encounter
  - Self-assessment and reflection

## Pre-Class Preparation

### Reading Assignment

Read through `pre-class/reading.md` to learn about:
- Common code smells and why they matter
- The concept of coupling and cohesion
- Why testing can become difficult
- Signs that code needs refactoring

### Pre-Class Exercises

Complete the exercises in `pre-class/exercises/`:
- Try to extend your Week 1 Character class with new abilities
- Attempt to create different game modes using your existing code
- Write tests for complex game scenarios

Run tests with: `mvn test`

### Verification

Check your understanding with `pre-class/verification.md` before class.

## During Class (17:00-20:00)

We'll work together to add major new features to our RPG game:
- Multiplayer support (multiple players taking turns)
- Different game modes (survival, story, arena)
- Save/load system that works with all new features
- Character classes with unique abilities

**Important:** We'll encounter many problems. This is the point! We'll discuss each challenge as it appears.

## Post-Class Assignments

### Main Exercises

In `post-class/exercises/`, you'll tackle even more challenging extensions:
1. Add a guild system where players can form teams
2. Implement a quest system with multiple objectives
3. Create a crafting system that uses inventory items
4. Add NPCs (Non-Player Characters) with dialogue
5. Implement different difficulty levels

### Success Criteria

Use `post-class/rubric.md` to evaluate your work. Pay special attention to:
- How many files did you have to modify for each feature?
- How much code did you have to duplicate?
- How confident are you that nothing broke?
- How would you test that everything still works?

### Getting Help

If you're stuck, check `post-class/hints.md` for guidance. Remember: struggling is part of the learning process this week!

### Extension Challenges

For those who want to go deeper, see `post-class/extensions/`:
- ⭐ **Reflection**: Document all the problems you encountered
- ⭐⭐ **Analysis**: Identify patterns in the problems
- ⭐⭐⭐ **Research**: Look up the "SOLID principles" and see which ones our code violates

## Important Notes

### This Week Is Different

Unlike Week 1 where everything felt smooth, this week will feel frustrating. This is by design. You're experiencing what happens when code grows without proper structure. These pain points are:

1. **Real** - Every developer has faced these problems
2. **Educational** - Feeling the pain makes the solution more meaningful
3. **Motivational** - You'll appreciate good design much more after this

### What You Might Experience

- **Cascading changes**: Changing one thing breaks three other things
- **Duplicate code**: Writing the same logic in multiple places
- **Testing nightmares**: Tests that are hard to write and harder to maintain
- **Mental overload**: Keeping track of all the dependencies in your head
- **Fear of change**: Being scared to modify working code

### Document Your Struggles

Keep notes about:
- What was hard to change and why
- Where you had to duplicate code
- What broke when you made changes
- Which tests were impossible to write

These notes will be valuable next week when we learn how to avoid these problems!

## Looking Ahead

Next week (Week 3), we'll introduce **interfaces** - a powerful tool that solves many of the problems you're experiencing this week. You'll redesign the same game using interfaces and experience the dramatic difference in flexibility and testability.

## Remember

- **Struggling is expected** - You're supposed to find this week challenging
- **Don't aim for perfect code** - Focus on experiencing the problems
- **Ask questions** - "Why is this so hard?" is exactly the right question
- **Share frustrations** - Discussing problems with classmates is encouraged

Good luck, and remember: the pain you feel this week is the motivation for everything we'll learn next!