# Week 5: Data Structures as Abstractions

## Overview
This week we apply the interface-first methodology to data structures. You'll learn
that List, Stack, and Queue are not just classes - they are **contracts** that can have
multiple implementations. This is where the power of abstraction becomes concrete.

## Learning Objectives
By the end of this week, you will:
- Design data structure interfaces before implementation
- Understand that a "List" is a contract, not a specific implementation
- Implement multiple versions of the same interface (ArrayList vs LinkedList)
- Apply wishful programming to data structure design
- Appreciate why Java's Collections Framework uses interfaces

## The Big Idea

> "A List is not ArrayList. A List is a promise about what operations are available."

When you write `List<String> names`, you're saying "I need something that acts like a list."
You're NOT saying "I need an ArrayList" or "I need a LinkedList." This distinction is
the heart of interface-first design.

## Pre-Class Preparation

### Reading
Complete `pre-class/reading.md` which covers:
- Data structures as abstractions
- The List interface contract
- Stack and Queue as behavioral contracts
- Choosing implementations based on needs

### Exercises
The pre-class exercises ask you to:
1. Define a simple `SimpleList` interface from scratch
2. Implement a basic `ArraySimpleList`
3. Design a `Stack` interface using wishful programming

**Time estimate:** 60-90 minutes

## This Week's Focus: Collection Interfaces

We'll build our own simplified collection framework:

```java
// The "wish" - how we WANT to use a list:
SimpleList<Quest> activeQuests = new ArraySimpleList<>();
activeQuests.add(slayDragonQuest);
activeQuests.add(collectHerbsQuest);

for (int i = 0; i < activeQuests.size(); i++) {
    Quest quest = activeQuests.get(i);
    System.out.println(quest.getName());
}

// Later, we could swap to LinkedSimpleList without changing this code!
SimpleList<Quest> activeQuests = new LinkedSimpleList<>();
// Everything else stays the same...
```

This reveals what interfaces we need:
- `SimpleList<T>` - ordered collection with index access
- `SimpleStack<T>` - LIFO (Last In, First Out) behavior
- `SimpleQueue<T>` - FIFO (First In, First Out) behavior

## Key Concepts

### Why Multiple Implementations?
Different implementations have different trade-offs:
- **ArrayList**: Fast random access, slow insertions in middle
- **LinkedList**: Slow random access, fast insertions anywhere
- **ArrayStack vs LinkedStack**: Memory vs flexibility trade-offs

### The Power of Programming to Interfaces
```java
// This code works with ANY SimpleList implementation
public int countCompleted(SimpleList<Quest> quests) {
    int count = 0;
    for (int i = 0; i < quests.size(); i++) {
        if (quests.get(i).isComplete()) {
            count++;
        }
    }
    return count;
}
```

### Records for Elements, Interfaces for Containers
- **Record**: The data you store (`Quest`, `Item`, `Player`)
- **Interface**: The container that holds the data (`SimpleList`, `SimpleStack`)

## Post-Class Work

### Exercises
After class, complete the custom collection exercise:
- Implement `LinkedSimpleList<T>` using linked nodes
- Create a `SimpleQueue<T>` interface and implementation
- Build a `PrioritySimpleList<T>` that keeps elements sorted

### Homework: Custom Collection for Your Domain
Design and implement a custom collection that makes sense for a domain of your choice:
- A `Playlist` that supports shuffle and repeat modes
- A `TaskQueue` with priorities
- An `InventoryBag` with weight limits

Focus on designing the interface first, then implementing.

## Connection to Previous Weeks

| Week | Focus | Connection |
|------|-------|------------|
| Week 1 | Built game naively | Used arrays directly, no abstraction |
| Week 2 | Felt the pain | Changing data structure required changes everywhere |
| Week 3 | Introduced interfaces | Defined contracts for behavior |
| Week 4 | Wishful programming | Used interfaces to build features top-down |
| **Week 5** | **Data structures** | **Apply interfaces to HOW we store data** |

## Looking Ahead
This is the start of Phase 2 (Mastery). You'll apply the same interface-first
technique to increasingly complex domains:
- Week 6: Trees and hierarchies
- Week 7: Maps and strategies
- Week 8: GUI layers

The methodology stays the same - only the domain changes.

## Verification Checklist
Before class, ensure you can:
- [ ] Explain why `List` is an interface, not a class
- [ ] Define a simple collection interface from a usage example
- [ ] Implement an interface with an array-backed storage
- [ ] Explain trade-offs between ArrayList and LinkedList

## Getting Help
- Review Week 4 materials on wishful programming
- Check the pre-class reading for examples
- Come to class with specific questions
