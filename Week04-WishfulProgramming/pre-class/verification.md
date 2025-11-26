# Week 4 Pre-Class Verification

Complete these checks before coming to class.

## Conceptual Understanding

### 1. Wishful Programming Definition
Can you explain wishful programming in one sentence?

**Your answer:** _________________________________

**Check:** Does your answer mention writing code before implementation exists?

### 2. Top-Down vs Bottom-Up
Which approach is this?
```java
// First write:
public class GoldReward {
    private int amount;
    public void give(Player p) { p.addGold(amount); }
}
// Then figure out how to use it...
```

- [ ] Top-down (wishful programming)
- [ ] Bottom-up (traditional)

### 3. Interface Identification
Given this wish code, what interfaces are needed?

```java
public void battle(Arena arena) {
    Combatant fighter1 = arena.getChallenger();
    Combatant fighter2 = arena.getDefender();
    
    BattleResult result = arena.fight(fighter1, fighter2);
    
    result.getWinner().receiveReward(arena.getPrize());
}
```

Interfaces needed:
- [ ] Arena
- [ ] Combatant  
- [ ] BattleResult
- [ ] Reward/Prize
- [ ] Something else: _____________

### 4. Record vs Interface
For each, choose record or interface:

| Concept | Record or Interface? |
|---------|---------------------|
| Player position (x, y coordinates) | |
| A reward that grants something to player | |
| Monster stats (health, attack, defense) | |
| A quest that can be completed | |
| Event data (type, timestamp, details) | |

## Code Verification

### 5. Exercise Completion
Have you completed the pre-class exercises?

- [ ] Exercise 1: Identified interfaces from wish code
- [ ] Exercise 2: Implemented Reward classes
- [ ] Exercise 3: Designed objective interfaces

### 6. Tests Pass
Do all pre-class exercise tests pass?

```bash
cd pre-class/exercises
mvn test
```

- [ ] All tests pass
- [ ] Some tests fail: ____________

## Reflection Questions

### 7. The "Why"
Why does wishful programming lead to better interfaces than designing interfaces first?

**Your answer:** _________________________________

### 8. When to Stop
How do you know when to stop creating interfaces and just implement?

**Your answer:** _________________________________

## Answers

### Question 2
**Bottom-up** - Starting with implementation details and building up.

### Question 3
All four interfaces are needed:
- `Arena` - manages the battle location/rules
- `Combatant` - already exists from Week 3
- `BattleResult` - outcome of the fight
- Something for prizes/rewards

### Question 4
| Concept | Answer |
|---------|--------|
| Player position | **Record** (just data) |
| Reward | **Interface** (has behavior: grantTo) |
| Monster stats | **Record** (just data) |
| Quest | **Interface** (has behavior: progress, complete) |
| Event data | **Record** (just data) |

### Question 7
Wishful programming creates interfaces based on ACTUAL NEED. You see what methods 
you wish existed, then create exactly those. Traditional interface-first design 
often guesses what might be needed.

### Question 8
Stop when implementation is trivial - just a few lines of obvious code with no 
complex decisions. If implementation feels hard, you probably need another interface.
