# Interface-First Design Checklist

Use this checklist whenever you design a new module or system. This is the core methodology of the course.

## Phase 1: Understand the Problem

- [ ] What is the high-level goal?
- [ ] What are the main responsibilities?
- [ ] What are the inputs and outputs?
- [ ] Who will use this module?

## Phase 2: Design Top-Level Interfaces

### For Each Module

- [ ] **Name the interface** - What does it do? (e.g., `GameEngine`, `PlayerRepository`, `ScoreCalculator`)
- [ ] **Define methods** - What operations does it provide?
- [ ] **Identify inputs** - What does each method need?
  - [ ] Is it data? → Use a **record**
  - [ ] Does it have behavior? → Use an **interface**
- [ ] **Identify outputs** - What does each method return?
  - [ ] Is it data? → Use a **record**
  - [ ] Does it have behavior? → Use an **interface**
  - [ ] Can it fail? → Consider `Optional<T>` or exceptions

### Example

```java
// Top-level interface
public interface GameEngine {
    GameState startNewGame(GameConfig config);
    GameState processAction(GameState currentState, PlayerAction action);
    boolean isGameOver(GameState state);
    GameResult calculateResult(GameState state);
}

// Input as record (data)
public record GameConfig(int playerCount, Difficulty difficulty, GameMode mode) {}

// Input as interface (behavior)
public interface PlayerAction {
    ActionType getType();
    boolean isValid(GameState state);
    void execute(GameState state);
}

// Output as record (data)
public record GameResult(Player winner, int finalScore, Duration playTime) {}
```

## Phase 3: Design Workflows

- [ ] **Create workflow classes** that use only interfaces and records
- [ ] **Use wishful programming** - assume interfaces exist and work perfectly
- [ ] **Don't implement yet** - just show how interfaces are used together

### Example Workflow

```java
public class GameController {
    private final GameEngine engine;
    private final PlayerInput input;
    private final GameDisplay display;

    // Constructor takes interfaces (dependency injection)
    public GameController(GameEngine engine, PlayerInput input, GameDisplay display) {
        this.engine = engine;
        this.input = input;
        this.display = display;
    }

    public void runGame() {
        GameConfig config = input.getGameConfig();
        GameState state = engine.startNewGame(config);

        while (!engine.isGameOver(state)) {
            display.show(state);
            PlayerAction action = input.getNextAction();
            state = engine.processAction(state, action);
        }

        GameResult result = engine.calculateResult(state);
        display.showResult(result);
    }
}
```

## Phase 4: Recursively Design Next Layer

For each interface you defined, repeat this process:

- [ ] Does this interface need to be broken down further?
- [ ] What sub-interfaces does it need?
- [ ] What data structures does it need?
- [ ] Can I implement this with existing Java libraries?

### When to Stop Recursing

Stop when:
- ✅ Implementation is trivial (< 10 lines)
- ✅ Can use existing Java classes (`ArrayList`, `HashMap`, etc.)
- ✅ Logic is straightforward and obvious
- ✅ No further abstraction provides value

## Phase 5: Write Tests (Before Implementation!)

- [ ] **Test against interfaces**, not implementations
- [ ] **Create mock implementations** for testing workflows
- [ ] **Define expected behavior** through tests
- [ ] **Tests guide implementation**

### Example Tests

```java
@Test
void shouldProcessValidAction() {
    // Arrange - create mocks/test implementations
    GameEngine engine = new TestGameEngine();
    GameState initialState = new GameState(/* ... */);
    PlayerAction validAction = new TestPlayerAction(true);

    // Act - use interface methods
    GameState newState = engine.processAction(initialState, validAction);

    // Assert - verify behavior
    assertNotNull(newState);
    assertNotEquals(initialState, newState);
}
```

## Phase 6: Implement Leaf Nodes

Only now do you write actual implementation:

- [ ] Start with the simplest, deepest interfaces
- [ ] Implement one at a time
- [ ] Run tests after each implementation
- [ ] Work your way up the abstraction tree

## Common Mistakes to Avoid

### ❌ Implementing too early
**Wrong:**
```java
// Don't write implementation before designing all interfaces
public class GameEngine {
    public void startGame() {
        // 200 lines of implementation details...
    }
}
```

**Right:**
```java
// Design interface first
public interface GameEngine {
    GameState startNewGame(GameConfig config);
}
// Implement later, after all interfaces are designed
```

### ❌ Mixing data and behavior
**Wrong:**
```java
// Player has both data and behavior - unclear responsibility
public class Player {
    private String name;
    private int score;

    public void attack(Monster monster) { /* ... */ }
    public void heal() { /* ... */ }
    public boolean isAlive() { /* ... */ }
}
```

**Right:**
```java
// Separate data from behavior
public record PlayerData(String name, int health, int score) {}

public interface PlayerActions {
    AttackResult attack(PlayerData player, MonsterData monster);
    PlayerData heal(PlayerData player);
    boolean isAlive(PlayerData player);
}
```

### ❌ Concrete dependencies
**Wrong:**
```java
// GameController depends on concrete ArrayList
public class GameController {
    private ArrayList<Player> players;
}
```

**Right:**
```java
// GameController depends on interface
public class GameController {
    private List<Player> players;  // or PlayerRepository interface
}
```

## Decision Tree: Record or Interface?

```
Does it have behavior (methods that DO things)?
├─ YES → Use an INTERFACE
│   Examples: Calculator, Repository, Engine, Strategy
│
└─ NO → Is it just data?
    └─ YES → Use a RECORD
        Examples: Point, Config, Result, Event
```

## Key Principles

1. **Interfaces define contracts** - what, not how
2. **Records are immutable data** - no behavior
3. **Design top-down** - high-level first, details last
4. **Test through interfaces** - enables flexibility
5. **Implement bottom-up** - leaves first, roots last
6. **Trust the process** - design feels slow but implementation flies

## Questions to Ask Yourself

Before writing any implementation:

- [ ] Have I designed all interfaces at this abstraction level?
- [ ] Are inputs/outputs clearly defined (records or interfaces)?
- [ ] Can I write workflow code using these interfaces?
- [ ] Can I write tests for these interfaces?
- [ ] Is each interface focused on one responsibility?
- [ ] Are dependencies injected through constructors?

If you can answer "yes" to all these, you're ready to move to the next layer!

## Remember

**The goal isn't to write code quickly. The goal is to design code that's easy to implement, test, and maintain.**

Interface-first design may feel slow at first, but it pays off when:
- Requirements change (swap implementations)
- Testing is needed (mock interfaces)
- Complexity grows (clear boundaries)
- Others read your code (obvious structure)

Trust the methodology. It works! 🎯
