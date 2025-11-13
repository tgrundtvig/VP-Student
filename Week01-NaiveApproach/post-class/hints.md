# Post-Class Exercise Hints

Stuck on the exercises? Here are some hints to help you progress without giving away complete solutions.

## General Strategy

1. **Read the tests first** - They tell you exactly what's expected
2. **Start simple** - Get basic functionality working before adding complexity
3. **One method at a time** - Don't try to implement everything at once
4. **Test frequently** - Run tests after each small change

## Player Inventory System

### Challenge: How do I store items?

**Hint**: Use the `Inventory` class you created in pre-class exercises! The Player should *have* an Inventory.

```java
public class Player {
    private Inventory inventory;

    public Player(String name) {
        this.inventory = new Inventory();
    }
}
```

### Challenge: How do weapons affect attack power?

**Hint**: Keep a base attack power and an equipped weapon bonus:

```java
public int getAttackPower() {
    int base = this.baseAttackPower;
    int weaponBonus = // get from equipped weapon
    return base + weaponBonus;
}
```

### Challenge: How do I track equipped items?

**Hint**: Add a field for the currently equipped weapon:

```java
private String equippedWeapon;  // null if no weapon equipped
```

## Item System

### Challenge: How do I represent different item types?

**Hint**: Items can have a type (POTION, WEAPON, ARMOR) and an effect value:

```java
public enum ItemType {
    POTION,   // Restores health
    WEAPON,   // Increases attack
    ARMOR     // Reduces damage (future feature)
}
```

### Challenge: How do I make items do different things?

**Hint**: Use the item type to determine behavior:

```java
public void useItem(Item item, Player player) {
    switch (item.getType()) {
        case POTION -> player.heal(item.getValue());
        case WEAPON -> player.equipWeapon(item);
        // ...
    }
}
```

## Monster Enhancements

### Challenge: How do I make different monster types?

**Hint**: Pass different stats to the Monster constructor:

```java
Monster goblin = new Monster("Goblin", 30, 5, 10);  // name, health, attack, gold
Monster orc = new Monster("Orc", 60, 12, 25);
Monster dragon = new Monster("Dragon", 200, 30, 100);
```

Or create a factory method:

```java
public static Monster createGoblin() {
    return new Monster("Goblin", 30, 5, 10);
}
```

### Challenge: How do I handle gold drops?

**Hint**: Add a gold field to Monster, and when defeated, transfer to Player:

```java
if (!monster.isAlive()) {
    player.addGold(monster.getGoldDrop());
}
```

## Game Loop Enhancements

### Challenge: How do I implement a shop?

**Hint**: Create a method that displays items and handles purchases:

```java
public void visitShop() {
    while (true) {
        displayShopItems();
        String choice = getUserChoice();
        if (choice.equals("leave")) break;
        processPurchase(choice);
    }
}
```

### Challenge: How do I save/load game state?

**Hint**: For now, keep it simple - just save basic stats to a file:

```java
// Save
String saveData = player.getName() + "," + player.getHealth() + "," + player.getGold();
Files.writeString(Path.of("savegame.txt"), saveData);

// Load
String[] data = Files.readString(Path.of("savegame.txt")).split(",");
Player player = new Player(data[0]);
player.setHealth(Integer.parseInt(data[1]));
// ...
```

### Challenge: How do I handle multiple encounters?

**Hint**: Use a loop with a list of monsters:

```java
List<Monster> encounters = List.of(
    Monster.createGoblin(),
    Monster.createOrc(),
    Monster.createGoblin()
);

for (Monster monster : encounters) {
    if (!combat(player, monster)) {
        break;  // Player died
    }
}
```

## Common Pitfalls

### Problem: NullPointerException

**Common cause**: Forgetting to initialize objects in constructor

**Fix**: Always initialize fields:
```java
public Player(String name) {
    this.name = name;
    this.inventory = new Inventory();  // Don't forget!
    this.health = 100;
}
```

### Problem: Tests fail with "expected X but was Y"

**Common cause**: Off-by-one errors, wrong calculation

**Fix**:
1. Read the test carefully
2. Print intermediate values to see what's happening
3. Check your math

### Problem: Scanner issues (skipping input)

**Common cause**: Mixing `nextInt()` and `nextLine()`

**Fix**: Always call `nextLine()` after `nextInt()`:
```java
int choice = scanner.nextInt();
scanner.nextLine();  // Consume the newline
String name = scanner.nextLine();
```

### Problem: Can't figure out test expectations

**Fix**: Read the test method name and assertions:
```java
@Test
void shouldHealPlayerWhenUsingPotion() {
    // Test name tells you what should happen
    player.setHealth(50);
    player.usePotion();
    assertEquals(70, player.getHealth());  // Potion heals 20
}
```

## Still Stuck?

1. **Check the test code** - It shows exactly how your code should behave
2. **Print debug statements** - See what your code is actually doing
3. **Try a simpler version first** - Get something working, then improve it
4. **Ask for help** - In class, on communication channel, or during office hours
5. **Look at rubric** - Make sure you understand what's required

## Remember

- There's no single "right" way to solve these
- Your solution might look different from others'
- Getting it working is more important than perfect design (for now!)
- Week 3 will teach better design approaches

The goal this week is to get comfortable with Java basics and experience building something complete, even if the design isn't perfect.

Keep going! You've got this! 💪
