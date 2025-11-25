# Pre-Class Verification Checklist

Before coming to class, make sure you understand these concepts:

## Interface Basics

- [ ] I understand that an interface is a contract defining WHAT, not HOW
- [ ] I know the difference between an interface and a class
- [ ] I can write an interface with method signatures
- [ ] I understand default methods in interfaces
- [ ] I know that a class can implement multiple interfaces

## Implementation

- [ ] I can implement an interface in a class
- [ ] I understand the `@Override` annotation
- [ ] I know that I must implement all non-default methods
- [ ] I can create multiple implementations of the same interface
- [ ] I understand that different implementations can behave differently

## Problem Solving

- [ ] I see how interfaces solve the circular dependency problem
- [ ] I understand how interfaces enable testing with mocks
- [ ] I see how interfaces eliminate if-else chains
- [ ] I understand how interfaces reduce coupling
- [ ] I can explain why "program to interfaces, not implementations"

## Practical Application

- [ ] I completed the SimpleWarrior implementation
- [ ] I completed the SimpleMage implementation (with different behavior)
- [ ] I understood how MockCombatant helps with testing
- [ ] I see how IGameMode eliminates Week 2's if-else problem
- [ ] I can declare variables using interface types

## Testing Benefits

- [ ] I see how interfaces make testing easier
- [ ] I understand how to create mock implementations
- [ ] I can test components in isolation
- [ ] I see how one test can work with multiple implementations
- [ ] I understand polymorphic testing

## Conceptual Understanding

- [ ] I understand interfaces as contracts between components
- [ ] I see how interfaces enable polymorphism
- [ ] I know when to use records (data) vs interfaces (behavior)
- [ ] I understand dependency inversion (depend on abstractions)
- [ ] I see how interfaces make code more flexible

## The "Aha!" Moments

Have you experienced these realizations?

- [ ] "This solves the circular dependency from Week 2!"
- [ ] "I can test things without setting up everything!"
- [ ] "No more if-else chains for different types!"
- [ ] "I can add new implementations without changing existing code!"
- [ ] "The same variable can hold different implementations!"

## Questions to Bring to Class

Write down any questions you have:

1. _________________________________________________
2. _________________________________________________
3. _________________________________________________

## Common Confusions (It's OK if you have these!)

**"When do I use an interface vs a class?"**
- Use interfaces when you want to define a contract
- Use classes when you want to provide implementation
- Often you'll have both: interface for contract, classes for implementations

**"Can interfaces have any implementation?"**
- Yes! Default methods can have implementation
- Static methods can also be in interfaces
- But regular methods are just signatures

**"What if two interfaces have the same method?"**
- If signatures match, one implementation satisfies both
- If signatures conflict, you'll get a compilation error

**"Why declare variables as interface types?"**
- Flexibility: can change implementation without changing code
- Testability: can use mocks
- Clarity: shows you depend on contract, not specific implementation

## Self-Test

Can you answer these questions?

1. What problem from Week 2 does `ICombatant` solve?
   - Answer: Tight coupling between Player and Monster classes

2. How does `IGameMode` eliminate if-else chains?
   - Answer: Each mode is a different implementation, polymorphism handles dispatch

3. Why is `MockCombatant` useful for testing?
   - Answer: Can test combat logic without real Player/Monster classes

4. What's the difference between:
   ```java
   SimpleWarrior warrior = new SimpleWarrior(...);  // Concrete type
   ICombatant warrior = new SimpleWarrior(...);     // Interface type
   ```
   - Answer: Interface type is more flexible, can be reassigned to any ICombatant

## Ready for Class?

If you can check most boxes and understand the concepts, you're ready!

Class will show you how to apply these concepts to completely transform the Week 2 game. The difference will be dramatic!

Remember: This week is the turning point where everything clicks together!