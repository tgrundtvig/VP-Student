# Post-Class Exercises: Extending the RPG

Now that you've built a simple text-based RPG in class, it's time to extend it with new features!

## Overview

You have a working game from class with:
- Player with health and attack
- Monster enemies
- Basic combat system

Your task: **Add new features** to make the game more interesting!

## Setup

1. Navigate to this directory
2. Run `mvn test` to see which features need implementation
3. Run `mvn exec:java` to play the game

## Exercises

Complete these TODOs in order:

### 1. **Player.java** - Add Inventory System
- Add an inventory field
- Implement methods to add/use items
- Equip weapons that modify attack power

### 2. **Item.java** - Create Item Class
- Represent items (potions, weapons, armor)
- Different item types have different effects

### 3. **Monster.java** - Enhanced Monsters
- Add monster types with different stats
- Add gold drops when defeated

### 4. **Game.java** - Enhanced Game Loop
- Add shop system
- Save/load game state
- Multiple combat encounters

## Running the Game

```bash
# Run tests to check progress
mvn test

# Play the game
mvn exec:java

# Compile only
mvn compile
```

## Success Criteria

✅ All tests pass
✅ Game runs without errors
✅ All TODO features implemented
✅ Code is clean and readable

## Hints Available

Stuck? Check [hints.md](../hints.md) for guidance on:
- How to structure the inventory
- How to implement the shop
- How to make different monster types
- Common pitfalls

## Self-Assessment

Use [rubric.md](../rubric.md) to evaluate your implementation.

## Optional Extensions

Finished early? Try the [extensions](../extensions/) for extra challenges:
- Magic system
- Character classes (warrior, mage, rogue)
- Random dungeon generation
- Boss battles

## Time Estimate

- Basic features: 2-3 hours
- With extensions: 4-5 hours

## Tips

- Implement one feature at a time
- Test frequently
- Commit to Git after each working feature
- Don't be afraid to refactor
- Have fun experimenting!

Good luck! 🎮
