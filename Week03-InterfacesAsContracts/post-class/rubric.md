# Week 3 Self-Assessment Rubric

This week's rubric focuses on proper interface usage and design quality. You're measuring success by how well you've applied interface principles.

**Total: 100 points**

## Part 1: Interface Design (30 points)

### Interface Quality (15 points)
- **Excellent (14-15)**: Interfaces are small, focused, with clear contracts
- **Good (11-13)**: Interfaces are mostly well-designed with minor issues
- **Satisfactory (8-10)**: Interfaces work but could be more focused
- **Needs Work (0-7)**: Interfaces are too large or poorly designed

Check these qualities:
- [ ] Each interface has a single, clear responsibility
- [ ] Method names clearly express the contract
- [ ] No implementation details leak into interfaces
- [ ] Interfaces are small (3-7 methods typically)
- [ ] Used interface types for parameters and returns

### Separation of Data and Behavior (15 points)
- **Excellent (14-15)**: Clear use of records for data, interfaces for behavior
- **Good (11-13)**: Mostly good separation with occasional mixing
- **Satisfactory (8-10)**: Some confusion between data and behavior
- **Needs Work (0-7)**: Poor separation, interfaces containing data

Check:
- [ ] Records used for immutable data
- [ ] Interfaces define behavior contracts
- [ ] No getter/setter interfaces
- [ ] Data flows through interfaces cleanly

## Part 2: Implementation Quality (30 points)

### Proper Implementation (15 points)
- **Excellent (14-15)**: All interfaces properly implemented with different behaviors
- **Good (11-13)**: Most implementations correct, minor issues
- **Satisfactory (8-10)**: Implementations work but miss some patterns
- **Needs Work (0-7)**: Poor implementations or missing key concepts

Verify:
- [ ] Multiple implementations per interface
- [ ] Each implementation behaves differently
- [ ] Proper use of `@Override`
- [ ] Default methods used appropriately
- [ ] No unnecessary coupling

### Variable Declaration (15 points)
- **Excellent (14-15)**: Always declare variables as interface types
- **Good (11-13)**: Usually use interface types, occasional concrete types
- **Satisfactory (8-10)**: Mix of interface and concrete types
- **Needs Work (0-7)**: Mostly using concrete types

Examples:
```java
// Excellent
IGuild guild = new BasicGuild();
List<IPlayer> players = new ArrayList<>();
IQuest quest = new KillQuest();

// Poor
BasicGuild guild = new BasicGuild();
ArrayList<Player> players = new ArrayList<>();
KillQuest quest = new KillQuest();
```

## Part 3: Problem Resolution (25 points)

### Circular Dependencies Eliminated (10 points)
- **Excellent (10)**: No circular dependencies, clean dependency graph
- **Good (8-9)**: Mostly eliminated, one minor circular reference
- **Satisfactory (6-7)**: Some circular dependencies remain
- **Needs Work (0-5)**: Still has major circular dependency issues

### Testing Improvements (10 points)
- **Excellent (10)**: Can test all components in isolation with mocks
- **Good (8-9)**: Most components testable, minor setup needed
- **Satisfactory (6-7)**: Better than Week 2 but still some coupling
- **Needs Work (0-5)**: Testing still requires complex setup

### Flexibility and Extension (5 points)
- **Excellent (5)**: Easy to add new implementations without changing existing code
- **Good (4)**: Can add implementations with minimal changes
- **Satisfactory (3)**: Some modification needed for new features
- **Needs Work (0-2)**: Still need significant changes for new features

## Part 4: Comparison with Week 2 (15 points)

### Documentation of Improvements (15 points)
- **Excellent (14-15)**: Clear comparison showing dramatic improvements
- **Good (11-13)**: Good comparison with specific examples
- **Satisfactory (8-10)**: Some comparison but lacks detail
- **Needs Work (0-7)**: No clear comparison or understanding of improvements

Document:
- [ ] Lines of code reduction
- [ ] Number of dependencies reduced
- [ ] Test setup simplification
- [ ] Ease of adding features
- [ ] Code clarity improvement

## Bonus Points (up to 10 extra)

### Advanced Patterns (5 bonus points)
- [ ] Used composition of interfaces effectively (+2)
- [ ] Created interface hierarchies appropriately (+2)
- [ ] Implemented strategy pattern beyond requirements (+1)

### Exceptional Testing (5 bonus points)
- [ ] Created comprehensive mock framework (+2)
- [ ] Achieved 100% test coverage of interfaces (+2)
- [ ] Demonstrated polymorphic testing (+1)

## Self-Evaluation Checklist

### Interface Mastery
- [ ] I can explain what an interface is and why it's useful
- [ ] I understand "program to interfaces, not implementations"
- [ ] I can create focused, single-responsibility interfaces
- [ ] I know when to use interfaces vs classes vs records
- [ ] I can create multiple implementations of the same interface

### Problem Solving
- [ ] I eliminated circular dependencies using interfaces
- [ ] I can test components in isolation
- [ ] I removed if-else chains using polymorphism
- [ ] I reduced coupling between classes
- [ ] I made the code more flexible and extensible

### Code Quality
- [ ] My interfaces have clear, descriptive names (I prefix)
- [ ] My implementations properly fulfill contracts
- [ ] I use interface types for variables
- [ ] I separate data (records) from behavior (interfaces)
- [ ] My code is easier to understand than Week 2

## Example of Excellence

```java
// Clean interface design
public interface IPlayer {
    String getName();
    PlayerStats getStats();
    void joinGuild(IGuild guild);
}

// Clear data separation
public record PlayerStats(int health, int attack, int defense) {}

// Proper implementation
public class WarriorPlayer implements IPlayer {
    private String name;
    private PlayerStats stats;
    private IGuild guild;

    @Override
    public void joinGuild(IGuild guild) {
        if (this.guild != null) {
            this.guild.removeMember(this);
        }
        this.guild = guild;
        guild.addMember(this);
    }
}

// Excellent testing
@Test
public void testGuildJoining() {
    IPlayer player = new WarriorPlayer("Test", new PlayerStats(100, 20, 10));
    IGuild guild = new MockGuild("TestGuild");

    player.joinGuild(guild);

    assertEquals(guild, player.getGuild());
    assertTrue(((MockGuild)guild).hasMember(player));
}
```

## Final Score Calculation

- Interface Design: ___/30
- Implementation Quality: ___/30
- Problem Resolution: ___/25
- Comparison with Week 2: ___/15
- **Base Total: ___/100**
- Bonus Points: ___/10
- **Final Score: ___**/100 (110 possible with bonus)

## Success Indicators

If you scored well, you should feel:
- ✅ "This is SO much better than Week 2!"
- ✅ "I can test everything easily now"
- ✅ "Adding features is straightforward"
- ✅ "The code is clean and understandable"
- ✅ "I never want to write Week 2 code again"

## Remember

High scores mean you've mastered the fundamental concept that separates amateur code from professional software design. Interfaces are the foundation of good object-oriented design!