# Week 12: Project Design Deep Dive

## Overview

Last week you created the big picture: your project's interface hierarchy, records, and
architecture layers. This week you go deeper. You will finalize every method signature, define
complete JavaDoc for each interface, specify exact parameter and return types, and design the
records that flow between layers.

By the end of this week, your interfaces should be precise enough to write tests against ---
which is exactly what you will do in Week 13.

## Learning Objectives

By the end of this week, you should be able to:

1. **Write** complete interface definitions with precise method signatures
2. **Design** records as data transfer objects with appropriate fields and types
3. **Document** interfaces with JavaDoc that explains the contract, not the implementation
4. **Verify** that interfaces are testable by reasoning about mock implementations

## Pre-Class Preparation

**Time estimate: 90-120 minutes**

1. **Read** the [pre-class reading](pre-class/reading.md) on designing complete interfaces
2. **Complete** the exercises in [pre-class/exercises](pre-class/exercises):
   - Create an `InterfaceSpecification.java` with your project's interface definitions
   - Include full method signatures with types, parameters, and return values
   - Add JavaDoc descriptions for each interface and method
3. **Verify** your preparation with the [verification checklist](pre-class/verification.md)

## Post-Class Work

**Time estimate: 2-3 hours**

After class, you will finalize your interface design:

1. **Complete** JavaDoc for every interface and method
2. **Create** workflow pseudo-code showing how interfaces interact
3. **Verify** each interface can be mocked for testing
4. **Update** your architecture document from Week 11

See [post-class/exercises](post-class/exercises) for detailed instructions.

## What Happens in Class

### First Half (17:10-18:20)
- Live coding: designing a complete interface with JavaDoc (20 min)
- Demonstration: how precise method signatures enable testing (15 min)
- Workshop: students refine their own interface signatures (25 min)

### Second Half (18:30-19:45)
- Peer review: swap interface specifications with a partner (20 min)
  - Can your partner understand every method from the JavaDoc alone?
  - Can they write a mock implementation?
- Incorporate feedback and refine (15 min)
- Start post-class exercises together (20 min)
- Individual help from teacher (20 min)

## Connection to Previous Weeks

| Week | Concept | How It Applies This Week |
|------|---------|--------------------------|
| Week 3 | Interface contracts | Now you write complete, precise contracts |
| Week 4 | Wishful programming | Interfaces define what you wish existed |
| Week 8 | MVC View interface | Your View interface gets fully specified |
| Week 9 | Repository interface | Your Repository gets domain-specific queries |
| Week 10 | Example projects | Reference their JavaDoc and method signatures |
| Week 11 | Architecture workshop | You refine the hierarchy you created last week |

## Looking Ahead

- **Week 13:** Write tests against your finalized interfaces (test-driven design)
- **Week 14:** Set up your Maven project and begin implementation preparation
- **2-week gap:** Implement the system you have designed

## Key Principles for This Week

### 1. Specificity Over Generality
```java
// BAD: too generic
void display(Object data);
String getInput();

// GOOD: specific types tell you exactly what happens
void showRecipeList(List<Recipe> recipes);
Recipe promptForNewRecipe(List<Category> availableCategories);
```

### 2. JavaDoc Describes the Contract
```java
/**
 * Displays a list of recipes and allows the user to browse them.
 * The view may display the list in any format (table, list, cards).
 * If the list is empty, an appropriate message should be shown.
 *
 * @param recipes the recipes to display, may be empty but not null
 */
void showRecipeList(List<Recipe> recipes);
```

### 3. Return Types Communicate Intent
```java
// Returns Optional when the item might not exist
Optional<Recipe> findById(String id);

// Returns a List when there might be multiple results
List<Recipe> findByCategory(String categoryId);

// Returns void when the method is a command (do something)
void save(Recipe recipe);

// Returns the entity when the user provides input
Recipe promptForNewRecipe(List<Category> categories);
```

## Verification Checklist

Before class, ensure you:
- [ ] Have complete method signatures for all interfaces
- [ ] Each method has parameter types and return types
- [ ] You have written JavaDoc for each interface
- [ ] You can explain what each method does without referencing implementation
- [ ] Your records have all necessary fields with types
- [ ] Pre-class exercises compile and tests pass

## Getting Help

- Review Task Manager and Quiz App interfaces from Week 10 examples
- Compare your interfaces with a classmate
- Ask: "Could someone implement this interface from the JavaDoc alone?"
- Bring specific interface design questions to class

---

**This week's mantra:** "If you cannot describe the contract precisely, you do not understand it yet."
