# Week 4: Wishful Programming

## Overview
This week we learn "wishful programming" - a powerful technique where you write code 
as if everything you need already exists, then make it exist. This is where the 
interface-first methodology truly clicks.

## Learning Objectives
By the end of this week, you will:
- Apply wishful programming to implement systems top-down
- Write high-level workflow code using only interfaces
- Understand when and why to create new abstractions
- Experience the power of interface-driven development

## The Big Idea

> "Wishful programming: solve problems by pretending they're already solved."

Instead of starting with details and building up, we:
1. Start at the highest level
2. Write code using interfaces/methods that don't exist yet
3. Let the compiler tell us what's missing
4. Define those interfaces
5. Implement using MORE interfaces if needed
6. Repeat until implementation is trivial

## Pre-Class Preparation

### Reading
Complete `pre-class/reading.md` which covers:
- The wishful programming mindset
- Top-down vs bottom-up development
- Recognizing when to create an interface

### Exercises
The pre-class exercises ask you to:
1. Analyze a "wish" code sample and identify needed interfaces
2. Implement a simple reward system using given interfaces
3. Design an objective system following the wishful programming pattern

**Time estimate:** 60-90 minutes

## This Week's Game Extension: Quest System

We're adding a Quest system to our RPG that demonstrates wishful programming:

```java
// The "wish" - how we WANT the code to look:
Quest activeQuest = questLog.getActiveQuest();
System.out.println("Quest: " + activeQuest.getDescription());

while (!activeQuest.isComplete()) {
    QuestObjective objective = activeQuest.getCurrentObjective();
    // ... game actions ...
    activeQuest.progress(new MonsterKilledEvent(monster));
}

activeQuest.getReward().grantTo(player);
```

This "wish code" tells us what interfaces we need:
- `Quest` - represents a quest with objectives and rewards
- `QuestObjective` - a single goal within a quest
- `Reward` - something granted upon completion
- `QuestLog` - tracks active and completed quests
- `QuestEvent` - data about game events (record!)

## Key Concepts

### When to Create an Interface
Create an interface when you find yourself wishing a method existed:
- "I wish I could call `quest.isComplete()`" → Create `Quest` interface
- "I wish I could grant any kind of reward" → Create `Reward` interface

### When Implementation is "Done"
Implementation is trivial (stop recursing) when:
- It's just a few lines of straightforward code
- No complex decisions remain
- You're just wiring things together

### Records vs Interfaces
- **Records:** For data that just holds values (QuestEvent, MonsterStats)
- **Interfaces:** For things that DO something (Quest, Reward, Combatant)

## Post-Class Work

### Exercises
After class, complete the Achievement system exercise:
- Design interfaces for an achievement tracker
- Implement several concrete achievements
- Integrate with the game's event system

### Homework: Library System Design
Design (interfaces only, NO implementation) a library management system:
- Books, Members, Loans
- Search functionality
- Late fees calculation
- Reservation system

This is the final exercise of Phase 1 (Discovery). Focus on clear, 
well-designed interfaces that another developer could implement.

## Connection to Previous Weeks

| Week | Focus | Connection |
|------|-------|------------|
| Week 1 | Built game naively | We could add quests now - it would be painful |
| Week 2 | Felt the pain | Adding features required changes everywhere |
| Week 3 | Introduced interfaces | Defined contracts for Combatant, Combat |
| **Week 4** | **Wishful programming** | **Use interfaces to build features top-down** |

## Looking Ahead
After this week, you'll have the core skill needed for the rest of the course.
Weeks 5-10 apply this same technique to:
- Data structures (Lists, Trees, Maps)
- GUI development
- Persistence and databases

The methodology stays the same - only the domain changes.

## Verification Checklist
Before class, ensure you can:
- [ ] Explain wishful programming in your own words
- [ ] Identify interfaces needed from a "wish code" sample
- [ ] Implement a simple interface correctly
- [ ] Distinguish when to use records vs interfaces

## Getting Help
- Review Week 3 materials on interfaces
- Check the pre-class reading for examples
- Ask questions in the class Discord
- Come to class with specific questions
