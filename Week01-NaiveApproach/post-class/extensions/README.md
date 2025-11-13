# Extension Challenges

Finished the core exercises and want more? Try these optional challenges!

Each challenge is rated by difficulty and time commitment.

## ⭐ Basic Extensions (30-60 minutes each)

### 1. Multiple Save Slots
Allow players to save multiple games and choose which to load.

**Requirements**:
- Save files named `save1.txt`, `save2.txt`, etc.
- Menu to choose save slot
- Display save slot info (player name, level, etc.)

**Hint**: Use a naming pattern for save files.

### 2. Monster Abilities
Give monsters special abilities they use randomly.

**Requirements**:
- Goblin: Can poison (damage over time)
- Orc: Can stun (player loses turn)
- Dragon: Can breathe fire (area damage)

**Hint**: Add an `useAbility()` method with random chance.

### 3. Achievement System
Track player accomplishments.

**Requirements**:
- "First Blood": Defeat first monster
- "Wealthy": Collect 1000 gold
- "Survivor": Win with <10 health
- Display achievements in menu

**Hint**: Use a Set to store unlocked achievements.

## ⭐⭐ Intermediate Extensions (1-2 hours each)

### 4. Character Classes
Let players choose a class at game start.

**Requirements**:
- **Warrior**: High health, medium attack
- **Rogue**: Low health, high attack, can dodge
- **Mage**: Medium health, magic spells

**Hint**: Create subclasses or use composition.

### 5. Magic System
Add spells alongside physical attacks.

**Requirements**:
- Mana resource for casting spells
- Different spells (fireball, heal, shield)
- Spells in inventory like items
- Monsters can have magic resistance

**Hint**: Similar to items but consume mana.

### 6. Dungeon Levels
Multiple floors with increasing difficulty.

**Requirements**:
- Each floor has multiple encounters
- Monsters get stronger each floor
- Rest between floors
- Boss at end of each floor

**Hint**: Use nested loops for floors and encounters.

## ⭐⭐⭐ Advanced Extensions (3+ hours each)

### 7. Random Dungeon Generation
Generate random encounters and loot.

**Requirements**:
- Random monster selection
- Random loot drops
- Random events (traps, treasure chests)
- Each playthrough is different

**Hint**: Use `Random` class for all random elements.

### 8. Equipment System
Full equipment slots (weapon, armor, accessories).

**Requirements**:
- Multiple equipment slots
- Equipment has stats (attack, defense, speed)
- Can upgrade equipment
- Set bonuses for matching equipment

**Hint**: Create an Equipment class hierarchy.

### 9. Combat Mechanics
Make combat more strategic.

**Requirements**:
- Turn-based with action points
- Different attack types (light, heavy, special)
- Positioning matters (front/back)
- Status effects (poison, stun, buff)

**Hint**: This is complex - plan your classes first!

### 10. GUI Version
Convert the text game to a graphical interface.

**Requirements**:
- JavaFX or Swing GUI
- Graphical display of health bars
- Click buttons for actions
- Visual representation of player/monsters

**Hint**: This is a major undertaking. Consider it a multi-week project!

## Combining Extensions

Many of these work well together:
- Character Classes + Magic System
- Dungeon Levels + Random Generation
- Equipment System + Character Classes
- Monster Abilities + Combat Mechanics

## Tips for Extensions

1. **Plan first**: Sketch out what classes/methods you need
2. **Start small**: Get basic version working before adding complexity
3. **Test frequently**: Make sure each part works before adding more
4. **Commit often**: Use Git to save progress
5. **Document**: These are complex - add comments!

## Learning Goals

These extensions aren't just for fun (though they should be!). They help you:
- Practice class design
- Work with more complex data structures
- Think about user experience
- Handle increasing complexity

**Important note**: As these get more complex, you'll start feeling the pain of poor design. That's intentional! Week 3 will show you better approaches.

## Showcase Your Work

Built something cool? Share it! (optional)
- Push to GitHub
- Show in class
- Help inspire classmates

## Need Ideas?

Think about your favorite games:
- What features do they have?
- How could you implement something similar?
- Start simple, add complexity gradually

## Remember

- These are **optional** - don't feel pressured
- Quality over quantity - one good extension beats three rushed ones
- It's okay if code gets messy - we'll learn better ways soon
- Have fun and be creative! 🎮

Happy coding! 🚀
