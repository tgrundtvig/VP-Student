# Pre-Class Exercises: Discovering the Power of Interfaces

These exercises introduce you to interfaces - the solution to Week 2's pain points. You'll implement interfaces that define contracts and see how they make code cleaner, testable, and extensible.

## Setup

1. Ensure you have Java 21 and Maven installed (see [setup-guide.md](../../../setup-guide.md))
2. Navigate to this directory in your terminal
3. Run `mvn clean test` to see which tests fail

## Exercises

Complete the TODO sections in these files:

### 1. **ICombatant.java** - Understanding the Contract (Already provided)
Read this interface carefully. It defines WHAT a combatant can do, not HOW.

### 2. **SimpleWarrior.java** - Implementing the Contract
Implement the `ICombatant` interface for a warrior character.
- Notice: You just need to fulfill the contract
- The implementation details are up to you

### 3. **SimpleMage.java** - Another Implementation
Implement the same `ICombatant` interface for a mage.
- Notice: Same interface, different behavior
- Both work with any code that uses `ICombatant`

### 4. **MockCombatant.java** - Testing Made Easy
Create a mock implementation for testing purposes.
- Notice: You can test combat logic without real game objects
- This was impossible in Week 2!

### 5. **IGameMode.java** - Defining Game Mode Contract (Already provided)
Read this interface. Compare to Week 2's if-else approach.

### 6. **SurvivalMode.java** - Implementing Game Mode
Implement the `IGameMode` interface for survival mode.
- Notice: No if-else chains needed
- Adding new modes = new class, no changes to existing code

## Running Tests

```bash
# Run all tests
mvn test

# Run tests for specific class
mvn test -Dtest=CombatantTest

# Compile without testing
mvn compile
```

## Success Criteria

- [ ] All tests pass
- [ ] No compilation errors
- [ ] You understand WHY interfaces solve Week 2's problems

## Key Concepts to Understand

1. **Interface = Contract**: It says WHAT, not HOW
2. **Multiple Implementations**: Same interface, different behaviors
3. **Programming to Interfaces**: Declare variables as interface types
4. **Testability**: Mock implementations make testing easy
5. **Extensibility**: Add new implementations without changing existing code

## Compare to Week 2

As you work, notice:
- No circular dependencies
- No if-else chains checking types
- Easy to test in isolation
- New implementations don't require changes elsewhere

## When You're Done

All tests passing? Great! You've experienced the power of interfaces.

Check [verification.md](../verification.md) for a final self-assessment.

## Hints

- Read the interface first - understand the contract
- Look at the tests to see how interfaces are used
- Start with SimpleWarrior - it's the most straightforward
- MockCombatant is simpler than you think - it just needs to track state

## The Revelation

If interfaces feel like "extra work" now, wait until you see how much easier they make everything else. Week 2's pain becomes Week 3's simplicity!
