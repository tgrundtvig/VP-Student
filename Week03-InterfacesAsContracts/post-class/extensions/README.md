# Extension Challenges: Mastering Interfaces

These optional challenges deepen your understanding of interface-based design.

## ⭐ Basic Extensions (30-60 minutes each)

### 1. Comparison Documentation

Create a detailed comparison between your Week 2 and Week 3 implementations:

```markdown
# Week 2 vs Week 3 Comparison

## Guild System
### Week 2 Problems:
- Circular dependency (Player ← → Guild)
- Couldn't test without full setup
- 150 lines of tangled code

### Week 3 Solution:
- Clean interfaces (IPlayer, IGuild)
- Test with mocks
- 75 lines of clean code
- 50% code reduction!

[Continue for each feature...]
```

### 2. Interface Hierarchy Design

Design an interface hierarchy for game entities:

```java
// Base interface
public interface IEntity {
    String getId();
    Position getPosition();
}

// Specialized interfaces
public interface ILiving extends IEntity {
    int getHealth();
    boolean isAlive();
}

public interface IMovable extends IEntity {
    void move(Position newPos);
    int getSpeed();
}

public interface ICombatant extends ILiving {
    void attack(ICombatant target);
    void defend(int damage);
}

// Composite interfaces
public interface IPlayer extends ICombatant, IMovable {
    // Player-specific methods
}
```

### 3. Mock Framework

Create a reusable mock framework:

```java
public class MockFactory {
    public static IPlayer createMockPlayer(String name, int level) {
        return new MockPlayer(name, level);
    }

    public static IGuild createMockGuild(String name) {
        return new MockGuild(name);
    }

    public static IQuest createMockQuest(String name, boolean completed) {
        return new MockQuest(name, completed);
    }
}
```

## ⭐⭐ Intermediate Extensions (1-2 hours each)

### 1. Decorator Pattern with Interfaces

Implement power-ups using the decorator pattern:

```java
public interface IWeapon {
    int getDamage();
    String getDescription();
}

public class BasicSword implements IWeapon {
    public int getDamage() { return 10; }
    public String getDescription() { return "Basic Sword"; }
}

public abstract class WeaponDecorator implements IWeapon {
    protected IWeapon weapon;

    public WeaponDecorator(IWeapon weapon) {
        this.weapon = weapon;
    }
}

public class FlamingWeapon extends WeaponDecorator {
    public FlamingWeapon(IWeapon weapon) {
        super(weapon);
    }

    public int getDamage() {
        return weapon.getDamage() + 5;  // +5 fire damage
    }

    public String getDescription() {
        return "Flaming " + weapon.getDescription();
    }
}

// Usage:
IWeapon sword = new BasicSword();
sword = new FlamingWeapon(sword);
sword = new EnchantedWeapon(sword);
// Now it's a Flaming Enchanted Basic Sword!
```

### 2. Event System with Interfaces

Create an event system using interfaces:

```java
public interface IEventListener {
    void handleEvent(GameEvent event);
}

public interface IEventDispatcher {
    void registerListener(String eventType, IEventListener listener);
    void dispatch(GameEvent event);
}

public class QuestCompleteListener implements IEventListener {
    public void handleEvent(GameEvent event) {
        if (event instanceof QuestCompleteEvent qce) {
            // Handle quest completion
        }
    }
}
```

### 3. Plugin System

Design a plugin system using interfaces:

```java
public interface IGamePlugin {
    String getName();
    String getVersion();
    void initialize(IGameContext context);
    void shutdown();
}

public interface IGameContext {
    IPlayer getCurrentPlayer();
    IWorld getWorld();
    void registerCommand(String command, ICommand handler);
}

public class ChatPlugin implements IGamePlugin {
    public void initialize(IGameContext context) {
        context.registerCommand("/chat", new ChatCommand());
    }
}
```

## ⭐⭐⭐ Advanced Extensions (3+ hours)

### 1. Complete Game Architecture

Design a complete game architecture using interfaces:

```java
// Core game loop
public interface IGameEngine {
    void initialize();
    void update(double deltaTime);
    void render();
    void shutdown();
}

// Systems
public interface ISystem {
    void update(double deltaTime);
}

public interface IRenderSystem extends ISystem {
    void render(IGraphics graphics);
}

public interface IPhysicsSystem extends ISystem {
    void checkCollisions();
    void applyForces();
}

// Entity Component System
public interface IComponent {
    String getType();
}

public interface IEntity {
    void addComponent(IComponent component);
    <T extends IComponent> T getComponent(Class<T> type);
}
```

### 2. Dependency Injection Container

Build a simple DI container:

```java
public interface IServiceContainer {
    <T> void register(Class<T> interfaceType, Class<? extends T> implementation);
    <T> void registerSingleton(Class<T> interfaceType, T instance);
    <T> T resolve(Class<T> interfaceType);
}

public class SimpleContainer implements IServiceContainer {
    private Map<Class<?>, Class<?>> mappings = new HashMap<>();
    private Map<Class<?>, Object> singletons = new HashMap<>();

    public <T> T resolve(Class<T> interfaceType) {
        // Resolution logic
    }
}

// Usage:
container.register(IPlayer.class, WarriorPlayer.class);
container.registerSingleton(IGameWorld.class, new GameWorld());
IPlayer player = container.resolve(IPlayer.class);
```

### 3. Comprehensive Testing Suite

Create a testing framework that validates interface contracts:

```java
public abstract class InterfaceContractTest<T> {
    protected abstract T createInstance();
    protected abstract T createDifferentInstance();

    @Test
    public void testEquals() {
        T instance1 = createInstance();
        T instance2 = createInstance();
        T instance3 = createDifferentInstance();

        // Verify equals contract
        assertEquals(instance1, instance2);
        assertNotEquals(instance1, instance3);
    }

    @Test
    public void testHashCode() {
        T instance1 = createInstance();
        T instance2 = createInstance();

        if (instance1.equals(instance2)) {
            assertEquals(instance1.hashCode(), instance2.hashCode());
        }
    }
}

// Use for any interface implementation
public class PlayerContractTest extends InterfaceContractTest<IPlayer> {
    protected IPlayer createInstance() {
        return new WarriorPlayer("Test", 1);
    }

    protected IPlayer createDifferentInstance() {
        return new MagePlayer("Different", 1);
    }
}
```

## Research Challenges

### 1. SOLID Principles Analysis

Research and document how interfaces enable SOLID principles:

```markdown
# SOLID Principles with Interfaces

## Single Responsibility Principle
Interfaces naturally encourage single responsibility by defining focused contracts.
Example: IMovable only handles movement, ICombatant only handles combat.

## Open/Closed Principle
Classes are open for extension (new implementations) but closed for modification.
Example: Add new IGameMode implementations without changing Game class.

## Liskov Substitution Principle
Any implementation can replace the interface without breaking code.
Example: MockPlayer can replace WarriorPlayer anywhere IPlayer is used.

## Interface Segregation Principle
Many small interfaces better than one large interface.
Example: IMovable, ICombatant, ITradeable vs one huge IGameEntity.

## Dependency Inversion Principle
Depend on abstractions (interfaces) not concretions (classes).
Example: Game depends on IPlayer, not WarriorPlayer.
```

### 2. Design Patterns Catalog

Document how interfaces enable classic design patterns:

- **Strategy Pattern**: Different algorithms behind same interface
- **Observer Pattern**: Listeners implement IEventListener
- **Factory Pattern**: Return interface types, hide implementations
- **Adapter Pattern**: Adapt incompatible interfaces
- **Composite Pattern**: Treat individual and composite objects uniformly

### 3. Performance Analysis

Compare performance implications:

```java
// Measure interface vs direct calls
long startTime = System.nanoTime();
for (int i = 0; i < 1000000; i++) {
    combatant.attack(target);  // Interface call
}
long interfaceTime = System.nanoTime() - startTime;

startTime = System.nanoTime();
for (int i = 0; i < 1000000; i++) {
    warrior.attack(monster);  // Direct call
}
long directTime = System.nanoTime() - startTime;

// Compare results (usually negligible difference)
```

## Real-World Application

### Build a Complete Feature

Choose ONE feature and implement it completely with interfaces:

1. **Achievement System**
   - IAchievement interface
   - Different achievement types
   - Progress tracking
   - Unlock conditions

2. **Skill System**
   - ISkill interface
   - Skill trees
   - Prerequisites
   - Effects

3. **Save System**
   - ISaveable interface
   - Different save formats (JSON, Binary, XML)
   - Version compatibility
   - Cloud saves

## Reflection Questions

After completing extensions, answer:

1. **What patterns emerged from using interfaces consistently?**
2. **How would you explain interfaces to someone struggling with Week 2?**
3. **What's the most powerful aspect of interface-based design?**
4. **When might interfaces be overkill?**
5. **How do interfaces affect team collaboration?**

## Share Your Learning

Consider:
- Creating a blog post about your Week 2 to Week 3 transformation
- Making a presentation for classmates
- Contributing examples to the course repository
- Helping classmates who are struggling

## The Journey Continues

You've transformed from writing procedural code to designing with contracts. This is the beginning of professional software design. Next week (Week 4), you'll learn "Wishful Programming" - designing top-down with interfaces, assuming implementations exist before writing them!

Remember: **Interfaces are not just a Java feature - they're a way of thinking about software design.**