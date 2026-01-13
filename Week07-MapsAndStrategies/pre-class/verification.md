# Week 7 Pre-Class Verification

Complete these questions to verify your understanding before class. Write your answers down -
you'll discuss them during the session.

## Part 1: Maps as Contracts

### Question 1: Interface vs Implementation
Why is this code better:
```java
Map<String, Player> players = new HashMap<>();
```

Than this code:
```java
HashMap<String, Player> players = new HashMap<>();
```

What flexibility do we gain?

### Question 2: Map Operations
Given this code:
```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 100);
scores.put("Bob", 85);
scores.put("Alice", 95);  // What happens here?

System.out.println(scores.size());         // What prints?
System.out.println(scores.get("Alice"));   // What prints?
System.out.println(scores.get("Charlie")); // What prints?
```

Predict the output without running the code.

### Question 3: Choosing Implementations
Match each scenario to the best Map implementation:

| Scenario | HashMap | TreeMap | LinkedHashMap |
|----------|---------|---------|---------------|
| Need fast lookup, order doesn't matter | | | |
| Need keys in alphabetical order | | | |
| Need to iterate in insertion order | | | |

## Part 2: The Strategy Pattern

### Question 4: Identifying Strategies
Which of these could benefit from the Strategy pattern? Explain why.

1. A game that needs to calculate damage differently for magic vs physical attacks
2. A counter that increments by 1
3. A report generator that can output PDF, HTML, or plain text
4. A method that returns the current date

### Question 5: Writing Comparators
Write a `Comparator<String>` that sorts strings by their length (shortest first):

```java
Comparator<String> byLength = // Your code here
```

### Question 6: Strategy Benefits
What are two benefits of using the Strategy pattern instead of hardcoding algorithms?

## Part 3: Combining Concepts

### Question 7: Design Exercise
You're building a game inventory system. Items can be sorted by:
- Name (alphabetically)
- Value (highest first)
- Weight (lightest first)
- Rarity (legendary > epic > rare > common)

Sketch out the interfaces you would need. Don't implement - just define the contracts.

### Question 8: Reflection
Think of a situation in code you've written (or could write) where:
1. A Map would be useful
2. The Strategy pattern would reduce code duplication

## Verification Checklist

Before class, confirm:
- [ ] I can explain why Map is an interface, not just HashMap
- [ ] I understand that maps associate keys with values (no duplicate keys)
- [ ] I can describe the Strategy pattern's purpose
- [ ] I can write a simple Comparator using a lambda
- [ ] I ran all pre-class exercise tests and they pass
- [ ] I have questions ready for class (if any concepts are unclear)

## Notes for Class

Write down any questions or concepts you'd like to discuss:

1. _________________________________

2. _________________________________

3. _________________________________
