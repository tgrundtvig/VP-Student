# Week 2 Self-Assessment Rubric

This week's rubric is different from Week 1. We're not measuring success by whether your code works - we're measuring learning by how well you recognize and document problems.

**Total: 100 points**

## Part 1: Problem Recognition (40 points)

### Code Smells Identified (20 points)
- **Excellent (18-20)**: Identified 5+ different code smells with specific examples from your code
- **Good (14-17)**: Identified 3-4 code smells with examples
- **Satisfactory (10-13)**: Identified 2 code smells with some examples
- **Needs Work (0-9)**: Identified fewer than 2 code smells or no examples

Examples of code smells to identify:
- [ ] Long methods (methods doing too many things)
- [ ] Duplicate code (same logic in multiple places)
- [ ] Feature envy (classes using other classes' data excessively)
- [ ] Tight coupling (classes depending on internal details of others)
- [ ] God class (one class doing everything)
- [ ] Primitive obsession (using strings/ints instead of objects)

### Coupling Issues Documented (20 points)
- **Excellent (18-20)**: Documented 5+ specific coupling problems with clear explanations
- **Good (14-17)**: Documented 3-4 coupling problems
- **Satisfactory (10-13)**: Documented 2 coupling problems
- **Needs Work (0-9)**: Documented fewer than 2 coupling problems

Examples to document:
- [ ] Classes that can't be used without other classes
- [ ] Circular dependencies (A needs B, B needs A)
- [ ] Changes that cascade through multiple files
- [ ] Hidden dependencies (global state, static methods)

## Part 2: Testing Challenges (30 points)

### Testing Difficulties Encountered (15 points)
- **Excellent (14-15)**: Documented 4+ specific testing challenges with attempts to solve them
- **Good (11-13)**: Documented 3 testing challenges with some attempts
- **Satisfactory (8-10)**: Documented 2 testing challenges
- **Needs Work (0-7)**: Documented fewer than 2 testing challenges

Testing problems to document:
- [ ] Can't test one class without creating others
- [ ] Need to set up entire game state for simple tests
- [ ] Can't mock or substitute dependencies
- [ ] Tests break when unrelated code changes
- [ ] Can't test individual features in isolation

### Test Coverage Analysis (15 points)
- **Excellent (14-15)**: Identified which parts of code are untestable and explained why
- **Good (11-13)**: Identified some untestable code with partial explanations
- **Satisfactory (8-10)**: Identified untestable code but limited explanation
- **Needs Work (0-7)**: Little or no analysis of testability

## Part 3: Implementation Attempts (20 points)

### Feature Implementation Effort (20 points)

For each feature you attempted, score 4 points (max 20):
- [ ] Guild System attempted
- [ ] Quest System attempted
- [ ] Crafting System attempted
- [ ] NPC System attempted
- [ ] Difficulty Levels attempted

**Note**: "Attempted" means you tried to implement it and documented the problems. Complete implementation is NOT required!

## Part 4: Reflection and Learning (10 points)

### Problem Documentation Quality (5 points)
- **Excellent (5)**: Clear, detailed notes about each problem with specific code examples
- **Good (4)**: Good notes with some code examples
- **Satisfactory (3)**: Basic notes about problems
- **Needs Work (0-2)**: Minimal or unclear documentation

### Learning Insights (5 points)
- **Excellent (5)**: Clear understanding of WHY these problems occur and impact on software development
- **Good (4)**: Good understanding of problems and some insight into causes
- **Satisfactory (3)**: Basic understanding of problems
- **Needs Work (0-2)**: Limited understanding or reflection

## Bonus Points (up to 10 extra)

### Going Deeper (5 bonus points)
- [ ] Created diagrams showing class dependencies (+2)
- [ ] Calculated metrics (lines changed, files affected) (+2)
- [ ] Documented time spent on each feature (+1)

### Research (5 bonus points)
- [ ] Looked up and explained SOLID principles (+3)
- [ ] Found real-world examples of similar problems (+2)

## Self-Evaluation Checklist

### Did you experience these problems?
- [ ] Had to modify multiple files for one feature
- [ ] Copied and pasted code
- [ ] Created circular dependencies
- [ ] Couldn't test features in isolation
- [ ] Methods became very long
- [ ] Classes took on multiple responsibilities
- [ ] Used lots of if-else chains
- [ ] Needed global state
- [ ] Broke existing features when adding new ones
- [ ] Felt afraid to refactor

### What did you learn?
- [ ] Working code isn't necessarily good code
- [ ] Poor structure makes extension difficult
- [ ] Tight coupling creates cascading changes
- [ ] Testing reveals design problems
- [ ] Classes should have single responsibilities
- [ ] Dependencies should be managed carefully

## Example Problem Documentation

Here's what good documentation looks like:

```
PROBLEM: Adding Guild Bonuses to Combat
FILES AFFECTED: Player.java, Guild.java, Monster.java, Battle.java, Game.java
ISSUE: To add guild attack bonuses, I had to:
1. Modify Player.getAttackPower() to check for guild
2. Add Guild reference to Player (circular dependency!)
3. Update Battle calculations to include guild bonuses
4. Modify save/load to handle guild state
5. Change all tests that create Players (now need Guilds too)

COUPLING: Player now depends on Guild, but Guild has ArrayList<Player>.
Cannot create Player for testing without Guild.
Cannot test combat without both Player AND Guild.

TIME WASTED: 45 minutes just getting it to compile
BUGS INTRODUCED: Save/load broken, tests failing
```

## Final Score Calculation

- Problem Recognition: ___/40
- Testing Challenges: ___/30
- Implementation Attempts: ___/20
- Reflection: ___/10
- **Base Total: ___/100**
- Bonus Points: ___/10
- **Final Score: ___**/100 (110 possible with bonus)

## Remember

A high score this week means you struggled and learned from it! If everything was easy, you didn't experience the problems this week was designed to teach.

The frustration you felt is the motivation for next week's solution: **Interfaces**!