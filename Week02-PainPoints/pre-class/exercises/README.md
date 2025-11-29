# Pre-Class Exercises: Experiencing the Pain

These exercises will expose the problems with "good enough" code design. You'll extend your Week 1 RPG game and discover why working code isn't always good code.

## Setup

1. Ensure you have Java 21 and Maven installed (see [setup-guide.md](../../../setup-guide.md))
2. Navigate to this directory in your terminal
3. Run `mvn clean test` to see which tests fail

## Exercises

Complete the TODO sections in these files:

### 1. **EnhancedCharacter.java** - Adding Special Abilities
Extend the Character class with special abilities, mana, and cooldowns.
- Notice how many places need to change
- Consider what happens when adding more abilities

### 2. **GameMode.java** - Multiple Game Modes
Implement different game modes (Story, Survival, Arena).
- Notice the if-else chains appearing everywhere
- Consider what happens when adding a fourth mode

### 3. **ComplexBattle.java** - Team Battle System
Create a battle system with multiple participants, damage types, and status effects.
- Notice how complexity explodes
- Consider how you would test this code

## Running Tests

```bash
# Run all tests
mvn test

# Run tests for specific class
mvn test -Dtest=EnhancedCharacterTest

# Compile without testing
mvn compile
```

## Success Criteria

The goal is NOT perfect code - it's to experience the problems!

- [ ] Implement all TODO sections
- [ ] Get as many tests passing as you can
- [ ] Document the difficulties you encounter
- [ ] Notice patterns of problems

## The Point

You WILL struggle with these exercises. That's intentional!

Document your frustrations:
1. How many files did you need to modify for one feature?
2. Where did you copy-paste code?
3. Which tests were hard to write?
4. What broke when you changed something?

## When You're Done

Whether all tests pass or not, you've learned something valuable.

Check [verification.md](../verification.md) for reflection questions.

## Preparing for Class

In class, we'll discuss:
- The problems you encountered
- Why these problems happen
- How interfaces solve these problems

Come prepared to share your struggles - they're learning opportunities!

## Hints

- Start with EnhancedCharacter - it's the "easiest"
- Read the test files to understand expectations
- Don't spend more than 30 minutes stuck on one problem
- It's OK if some tests fail - document why

Remember: The difficulty is the lesson!
