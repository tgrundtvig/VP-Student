# Design Pattern Exercises

These exercises give you hands-on practice with every major design pattern. Each exercise is self-contained — you build a small system from scratch. No starter code, no dependencies on other exercises.

Every exercise follows the **interface-first approach** you've been learning:

1. Define the interface(s) first
2. Write client/workflow code that *uses* the interfaces — before any implementation exists
3. Implement the interfaces
4. Run and verify

This is intentional. When you write the client code before the implementations exist, you discover what the interface *should* look like from the caller's perspective. The implementation follows naturally.

**Estimated time per exercise: 20-30 minutes.**

Pick the patterns that interest you most, or work through them in order. You don't need to finish all of them in one sitting — this is a reference you can come back to.

---

## Table of Contents

### Creational Patterns
1. [Factory Method — Notification System](#1-factory-method)
2. [Abstract Factory — UI Theme System](#2-abstract-factory)
3. [Builder — Meal Ordering System](#3-builder)
4. [Singleton — Configuration Manager (and Why Not To)](#4-singleton)
5. [Prototype — Shape Drawing](#5-prototype)

### Structural Patterns
6. [Adapter — Temperature Conversion](#6-adapter)
7. [Decorator — Coffee Ordering](#7-decorator)
8. [Facade — Home Theater](#8-facade)
9. [Composite + Visitor — File System with Operations](#9-composite--visitor)
10. [Proxy — Caching Weather Service](#10-proxy)
11. [Bridge — Remote Controls and Devices](#11-bridge)
12. [Null Object — Logging](#12-null-object)

### Behavioral Patterns
13. [Strategy — Shipping Cost Calculator](#13-strategy)
14. [Command — Calculator with Undo](#14-command)
15. [Observer — Weather Station](#15-observer)
16. [Iterator — Number Range](#16-iterator)
17. [Template Method — Report Generator](#17-template-method)
18. [State — Vending Machine](#18-state)
19. [Chain of Responsibility — Purchase Approval](#19-chain-of-responsibility)
20. [Memento — Text Editor with Undo](#20-memento)

### Architectural Patterns
21. [MVC — Student Grade Tracker](#21-mvc)
22. [Repository — Book Library](#22-repository)
23. [Dependency Injection — Refactoring Exercise](#23-dependency-injection)

---

## Creational Patterns

Creational patterns deal with object creation. The core problem: if every piece of code creates its own objects using `new`, you end up tightly coupled to concrete classes everywhere. Creational patterns put creation behind methods or interfaces so the caller says *what* it needs without knowing *how* it's made.

---

## 1. Factory Method

**Pattern**: Define a creation method that returns an interface type. Different factories produce different concrete objects, but the caller only sees the interface.

**Scenario**: You're building a notification system. The application needs to send notifications, but different parts of the system use different channels — email, SMS, or push notifications. The sending code shouldn't know which channel it's using. A factory decides which type of notification to create.

### Step 1: Define the interfaces

Create two interfaces — one for the notification itself, and one for the factory that creates notifications.

```java
public interface Notification {
    void send(String message);
}

public interface NotificationFactory {
    Notification createNotification();
}
```

Notice that `Notification` has a single method — it sends a message. The factory has a single method — it creates a notification. The caller never needs to know whether it gets an email, an SMS, or a push notification.

### Step 2: Write the client code

Before implementing anything, write the code that *uses* these interfaces. This is the key step — you're designing from the caller's perspective.

```java
public class NotificationService {

    public static void notifyUser(NotificationFactory factory, String message) {
        Notification notification = factory.createNotification();
        notification.send(message);
    }

    public static void main(String[] args) {
        // We'll plug in real factories in Step 3
        // For now, notice: this method works with ANY factory.
        // It doesn't know or care what kind of notification it sends.
    }
}
```

Look at `notifyUser` — it takes a `NotificationFactory` and a message. It creates a notification and sends it. That's it. No `if` statements, no checking which type, no `instanceof`. The factory handles the decision.

### Step 3: Implement

Now create the concrete notifications and factories.

**Three notification implementations:**

- `EmailNotification` — its `send()` method prints something like: `"Sending EMAIL: <message>"`
- `SMSNotification` — prints: `"Sending SMS: <message>"`
- `PushNotification` — prints: `"Sending PUSH: <message>"`

**Two factories:**

- `EmailFactory` — `createNotification()` returns a new `EmailNotification`
- `SMSFactory` — `createNotification()` returns a new `SMSNotification`

Now complete your `main` method:

```java
public static void main(String[] args) {
    NotificationFactory emailFactory = new EmailFactory();
    NotificationFactory smsFactory = new SMSFactory();

    notifyUser(emailFactory, "Your order has shipped!");
    notifyUser(smsFactory, "Your verification code is 4829");
}
```

### Step 4: Verify

Run the program. You should see:

```
Sending EMAIL: Your order has shipped!
Sending SMS: Your verification code is 4829
```

Now add a `PushFactory` and call `notifyUser` with it. Notice that `notifyUser` didn't change at all — you only added a new factory class.

### What you learned

- The Factory Method pattern puts object creation behind an interface, so the caller never knows which concrete class is created
- Adding a new notification type means adding a new class and a new factory — zero changes to existing code
- This is the same principle you saw with `TreasureFactory` in the in-class exercises: the caller works with the interface, the factory makes the decision

---

## 2. Abstract Factory

**Pattern**: A factory interface that creates *families* of related objects. Each concrete factory produces a consistent set of products that belong together.

**Scenario**: You're building a UI toolkit that supports theming. A form needs a button, a text field, and a label — but they all need to match the same visual style. A light theme factory produces light-styled components; a dark theme factory produces dark-styled components. You never want a dark button next to a light text field.

### Step 1: Define the interfaces

You need interfaces for the three UI component types and for the factory that creates them.

```java
public interface Button {
    String render();
}

public interface TextField {
    String render();
}

public interface Label {
    String render();
}

public interface ThemeFactory {
    Button createButton(String text);
    TextField createTextField(String placeholder);
    Label createLabel(String text);
}
```

Each component has a `render()` method that returns a string representation of how it would look. The factory creates all three component types — this is the key difference from a simple factory method. The factory guarantees that all components come from the same family.

### Step 2: Write the client code

Write a method that builds a login form using whatever theme factory it receives.

```java
public class Application {

    public static String buildLoginForm(ThemeFactory theme) {
        Label titleLabel = theme.createLabel("Login");
        TextField usernameField = theme.createTextField("Enter username");
        TextField passwordField = theme.createTextField("Enter password");
        Button loginButton = theme.createButton("Sign In");

        return String.join("\n",
            titleLabel.render(),
            usernameField.render(),
            passwordField.render(),
            loginButton.render()
        );
    }

    public static void main(String[] args) {
        // We'll plug in real theme factories in Step 3
    }
}
```

Notice: `buildLoginForm` doesn't know which theme it's building for. It creates four components and renders them. The theme factory guarantees they're all visually consistent.

### Step 3: Implement

Create two theme factories with their component implementations.

**LightThemeFactory** and its components:
- `LightButton` — `render()` returns something like `"[ Sign In ]"` (plain style)
- `LightTextField` — returns `"| Enter username |"` (simple border)
- `LightLabel` — returns `"Login"` (plain text)

**DarkThemeFactory** and its components:
- `DarkButton` — returns `"[[ SIGN IN ]]"` (bold/emphasized style)
- `DarkTextField` — returns `"[# Enter username #]"` (heavy border)
- `DarkLabel` — returns `"*** Login ***"` (decorated text)

Use whatever visual style you like — the point is that all components from one factory should look like they belong together, and the two families should look distinctly different.

Now complete your `main` method:

```java
public static void main(String[] args) {
    System.out.println("=== Light Theme ===");
    System.out.println(buildLoginForm(new LightThemeFactory()));

    System.out.println();

    System.out.println("=== Dark Theme ===");
    System.out.println(buildLoginForm(new DarkThemeFactory()));
}
```

### Step 4: Verify

Run the program. You should see two visually distinct login forms — same structure, different styling. The `buildLoginForm` method was called twice with different factories, producing different results without any `if` statements.

Try adding a `HighContrastThemeFactory` for accessibility. What code changes? (Only the new factory and its components. The `buildLoginForm` method stays untouched.)

### What you learned

- Abstract Factory ensures that a family of related objects is created consistently — you never get a dark button mixed with light text fields
- The client code (`buildLoginForm`) is completely independent of which theme it's building for
- Adding a new theme is a matter of creating a new factory class and its component classes — no existing code changes

---

## 3. Builder

**Pattern**: Assemble a complex object step by step. The `build()` method does real work — computing, validating, and assembling the final product from the accumulated parts.

**Scenario**: You're building a meal ordering system. A meal is composed of a drink, a main course, a side, and a dessert — but not all are required. The `build()` method computes the total price and total calorie count from whatever items were added. This is the GoF Builder pattern: the builder accumulates parts, then `build()` processes them into a finished object.

### Step 1: Define the interface

First, define what a finished meal looks like.

```java
public interface Meal {
    List<String> items();
    double totalPrice();
    int totalCalories();
    String description();
}
```

A `Meal` knows its items, total price, total calories, and can describe itself. Notice that `totalPrice` and `totalCalories` are *computed* values — the caller never sets them directly. The builder will calculate them from the individual parts.

### Step 2: Write the client code

Write code that builds meals using a builder — before the builder exists.

```java
public class Restaurant {

    public static void main(String[] args) {
        Meal lunch = new MealBuilder()
            .addDrink("Cola", 2.50, 140)
            .addMainCourse("Burger", 8.99, 550)
            .addSide("Fries", 3.49, 320)
            .build();

        Meal lightLunch = new MealBuilder()
            .addDrink("Water", 0.00, 0)
            .addMainCourse("Grilled Chicken", 10.99, 350)
            .build();

        Meal fullDinner = new MealBuilder()
            .addDrink("Red Wine", 7.50, 125)
            .addMainCourse("Steak", 24.99, 700)
            .addSide("Baked Potato", 4.99, 220)
            .addDessert("Chocolate Cake", 6.99, 450)
            .build();

        printMeal("Lunch", lunch);
        printMeal("Light Lunch", lightLunch);
        printMeal("Full Dinner", fullDinner);
    }

    static void printMeal(String name, Meal meal) {
        System.out.println("--- " + name + " ---");
        System.out.println("Items: " + String.join(", ", meal.items()));
        System.out.println("Total Price: $" + String.format("%.2f", meal.totalPrice()));
        System.out.println("Total Calories: " + meal.totalCalories());
        System.out.println("Description: " + meal.description());
        System.out.println();
    }
}
```

Notice: the builder methods take a name, price, and calorie count for each item. The `build()` method will sum up the prices and calories. The caller never computes totals — the builder handles it.

### Step 3: Implement

Create `MealBuilder` and a concrete `Meal` implementation.

**MealBuilder:**
- Has internal lists/fields to accumulate the parts (drink name/price/calories, main course, etc.)
- Each `add` method stores the item data and returns `this` (for method chaining)
- `build()` does the real work:
  - Collects all item names into a list
  - **Sums** all prices to compute `totalPrice`
  - **Sums** all calorie counts to compute `totalCalories`
  - Creates a description like `"Burger with Fries, Cola, and Chocolate Cake"`
  - Validates that at least a main course was added (throw `IllegalStateException` if not — a meal without a main course makes no sense)
  - Returns an immutable `Meal` object

For the `Meal` implementation, a record works well since it's an immutable data carrier:

```java
record CompleteMeal(List<String> items, double totalPrice,
                    int totalCalories, String description) implements Meal {}
```

### Step 4: Verify

Run the program. You should see three meals with correct totals:

```
--- Lunch ---
Items: Cola, Burger, Fries
Total Price: $14.98
Total Calories: 1010
Description: Burger with Fries and Cola

--- Light Lunch ---
Items: Water, Grilled Chicken
Total Price: $10.99
Total Calories: 350
Description: Grilled Chicken with Water
...
```

The totals are computed by `build()`, not by the caller. Try removing the main course from a meal and verify that `build()` throws an exception.

### What you learned

- The Builder pattern separates *accumulating configuration* from *constructing the result* — `build()` does computation, validation, and assembly
- This is the same pattern as `LocationMapBuilder`: add parts step by step, then `build()` wires everything together
- The caller never computes totals or validates — the builder guarantees a correct, complete object

---

## 4. Singleton

**Pattern**: Ensure a class has exactly one instance with a global access point. **Then discover why this is usually a bad idea and refactor to dependency injection.**

**Scenario**: You're building a game that needs a configuration manager — difficulty level, sound volume, player name. The Singleton pattern seems perfect: there's only one configuration, and everything needs access to it. But then you try to test your code and discover the problem.

This exercise is different from the others. You'll implement the pattern, experience its downsides firsthand, and then fix it.

### Step 1: Implement the Singleton

Create a `GameConfig` class with private constructor and `getInstance()`:

```java
public class GameConfig {
    private static GameConfig instance;

    private String difficulty = "normal";
    private int soundVolume = 50;
    private String playerName = "Player1";

    private GameConfig() {}

    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public int getSoundVolume() { return soundVolume; }
    public void setSoundVolume(int volume) { this.soundVolume = volume; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String name) { this.playerName = name; }
}
```

Now create a `DamageCalculator` that uses the singleton:

```java
public class DamageCalculator {

    public int calculateDamage(int baseDamage) {
        String difficulty = GameConfig.getInstance().getDifficulty();
        return switch (difficulty) {
            case "easy" -> baseDamage / 2;
            case "hard" -> baseDamage * 2;
            default -> baseDamage;
        };
    }
}
```

### Step 2: Feel the pain

Write a `main` method that uses `DamageCalculator`:

```java
public static void main(String[] args) {
    DamageCalculator calc = new DamageCalculator();

    GameConfig.getInstance().setDifficulty("easy");
    System.out.println("Easy damage: " + calc.calculateDamage(100));

    GameConfig.getInstance().setDifficulty("hard");
    System.out.println("Hard damage: " + calc.calculateDamage(100));
}
```

This works. But now try to answer these questions:

1. **Testing**: How would you test `DamageCalculator` with different difficulty settings *independently*? If one test sets difficulty to "hard" and another expects "normal", they interfere with each other because they share the same singleton.
2. **Hidden dependencies**: Look at `DamageCalculator`'s constructor — it takes no arguments. Can you tell from the outside that it depends on `GameConfig`? No. The dependency is hidden inside the method.
3. **Flexibility**: What if you wanted two game sessions running simultaneously with different configurations? The singleton makes this impossible.

### Step 3: Refactor to Dependency Injection

Now fix it. Extract an interface and inject the dependency through the constructor.

```java
public interface GameSettings {
    String getDifficulty();
    int getSoundVolume();
    String getPlayerName();
}
```

Create a simple implementation:

```java
public class SimpleGameSettings implements GameSettings {
    private String difficulty;
    private int soundVolume;
    private String playerName;

    public SimpleGameSettings(String difficulty, int soundVolume, String playerName) {
        this.difficulty = difficulty;
        this.soundVolume = soundVolume;
        this.playerName = playerName;
    }

    // implement getter methods from the interface
    // add setters if needed
}
```

Refactor `DamageCalculator` to receive its dependency:

```java
public class DamageCalculator {
    private final GameSettings settings;

    public DamageCalculator(GameSettings settings) {
        this.settings = settings;
    }

    public int calculateDamage(int baseDamage) {
        return switch (settings.getDifficulty()) {
            case "easy" -> baseDamage / 2;
            case "hard" -> baseDamage * 2;
            default -> baseDamage;
        };
    }
}
```

### Step 4: Verify

```java
public static void main(String[] args) {
    GameSettings easy = new SimpleGameSettings("easy", 50, "Alice");
    GameSettings hard = new SimpleGameSettings("hard", 50, "Bob");

    DamageCalculator easyCalc = new DamageCalculator(easy);
    DamageCalculator hardCalc = new DamageCalculator(hard);

    System.out.println("Easy damage: " + easyCalc.calculateDamage(100));  // 50
    System.out.println("Hard damage: " + hardCalc.calculateDamage(100));  // 200
}
```

Now you can have two calculators with different settings, running simultaneously, completely independent. And testing is trivial — just create a `SimpleGameSettings` with whatever values you need.

### What you learned

- Singleton provides global access to a single instance — but global state creates hidden dependencies and makes testing painful
- The refactored version using constructor injection is more flexible, more testable, and makes dependencies explicit
- **Key takeaway**: If you think you need a Singleton, you almost certainly need dependency injection instead. Create the object once and pass it to everything that needs it.

---

## 5. Prototype

**Pattern**: Create new objects by cloning existing ones. Useful when object creation is expensive or when you want to create variations of a template.

**Scenario**: You're building a drawing application. You have template shapes — a unit circle, a standard rectangle, a default triangle. Instead of configuring new shapes from scratch each time, you clone a template and adjust its position. The original templates remain unchanged.

### Step 1: Define the interface

```java
public interface Shape {
    void draw();
    Shape clone();
    Shape moveTo(int x, int y);
    String description();
}
```

Notice that `clone()` returns a `Shape` — the caller gets a copy without knowing which concrete class it is. `moveTo` returns a new `Shape` at the given position (keeping shapes immutable).

### Step 2: Write the client code

```java
public class Canvas {

    public static void drawFromTemplates(List<Shape> templates) {
        System.out.println("--- Drawing from templates ---");
        for (Shape template : templates) {
            // Clone the template and place copies at different positions
            Shape copy1 = template.clone().moveTo(10, 20);
            Shape copy2 = template.clone().moveTo(50, 50);
            Shape copy3 = template.clone().moveTo(100, 0);

            copy1.draw();
            copy2.draw();
            copy3.draw();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // We'll create template shapes in Step 3
    }
}
```

The `drawFromTemplates` method takes a list of template shapes. It doesn't know if they're circles, rectangles, or triangles. It clones each one three times, moves the clones to different positions, and draws them. The templates are never modified.

### Step 3: Implement

Create three shape implementations. Each shape stores its type-specific data plus a position (x, y).

**Circle** — has a radius. `description()` returns something like `"Circle(r=25) at (10, 20)"`. `clone()` returns a new `Circle` with the same radius and position. `moveTo(x, y)` returns a new `Circle` at the new position.

**Rectangle** — has width and height. `description()` returns `"Rectangle(40x20) at (50, 50)"`.

**Triangle** — has a base and height. `description()` returns `"Triangle(base=30, h=15) at (0, 0)"`.

For `draw()`, simply print the description.

Create templates in your `main`:

```java
public static void main(String[] args) {
    List<Shape> templates = List.of(
        new Circle(25, 0, 0),
        new Rectangle(40, 20, 0, 0),
        new Triangle(30, 15, 0, 0)
    );

    drawFromTemplates(templates);
}
```

### Step 4: Verify

Run the program. You should see 9 shapes drawn (3 templates x 3 copies each), each at a different position. The templates at (0,0) are never drawn directly — only their clones at new positions.

### What you learned

- The Prototype pattern creates objects by cloning existing ones rather than constructing from scratch
- The client code works with the `Shape` interface and never needs to know which concrete shape it's cloning
- This pattern is especially useful when objects are expensive to create, or when you want to create many variations of a base configuration

---

## Structural Patterns

Structural patterns deal with how objects are composed into larger structures. The core problem: how do you combine objects and classes into bigger structures while keeping things flexible and efficient?

---

## 6. Adapter

**Pattern**: Convert one interface into another that a client expects. Lets classes work together that couldn't otherwise because of incompatible interfaces.

**Scenario**: You're building a weather monitoring system. Your application works with temperatures in Celsius — every display, every calculation, every threshold check uses `TemperatureReader` which returns Celsius. But you've been given a legacy sensor from an American supplier that only reports in Fahrenheit. You can't change the sensor (it's a third-party library), and you don't want to change your entire application. You need an adapter.

### Step 1: Define the interface your system expects

```java
public interface TemperatureReader {
    double readCelsius();
}
```

Your entire system depends on this interface. Displays, alerts, logging — everything calls `readCelsius()`.

### Step 2: Write the client code

```java
public class WeatherDisplay {

    public static void showTemperature(TemperatureReader reader) {
        double celsius = reader.readCelsius();
        System.out.printf("Current temperature: %.1f°C%n", celsius);

        if (celsius > 35.0) {
            System.out.println("WARNING: Extreme heat!");
        } else if (celsius < 0.0) {
            System.out.println("WARNING: Below freezing!");
        }
    }

    public static void main(String[] args) {
        // We'll plug in real readers in Step 3
    }
}
```

This code works with any `TemperatureReader`. It doesn't know or care where the temperature comes from.

### Step 3: Implement

First, here's the "legacy" sensor you can't change. Create this class as-is — pretend it came from a third-party library:

```java
public class FahrenheitSensor {
    private final double fahrenheit;

    public FahrenheitSensor(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public double readFahrenheit() {
        return fahrenheit;
    }
}
```

Notice: it has `readFahrenheit()`, not `readCelsius()`. It doesn't implement `TemperatureReader`. You can't modify it.

Now create the adapter:

```java
public class FahrenheitToCelsiusAdapter implements TemperatureReader {
    private final FahrenheitSensor sensor;

    public FahrenheitToCelsiusAdapter(FahrenheitSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double readCelsius() {
        // Convert: C = (F - 32) * 5/9
        return (sensor.readFahrenheit() - 32.0) * 5.0 / 9.0;
    }
}
```

The adapter *wraps* the legacy sensor and *translates* its output. It implements the interface your system expects while delegating to the class you can't change.

Now complete `main`:

```java
public static void main(String[] args) {
    // A sensor reporting 98.6°F (normal body temperature)
    FahrenheitSensor legacySensor = new FahrenheitSensor(98.6);
    TemperatureReader adapted = new FahrenheitToCelsiusAdapter(legacySensor);

    showTemperature(adapted);

    // A sensor reporting 14°F (very cold)
    FahrenheitSensor coldSensor = new FahrenheitSensor(14.0);
    showTemperature(new FahrenheitToCelsiusAdapter(coldSensor));
}
```

### Step 4: Verify

Run the program. You should see temperatures displayed in Celsius:

```
Current temperature: 37.0°C
WARNING: Extreme heat!
Current temperature: -10.0°C
WARNING: Below freezing!
```

The `WeatherDisplay` has no idea that the original data was in Fahrenheit. The adapter made the incompatible sensor compatible.

### What you learned

- The Adapter pattern wraps an existing class with a new interface without modifying the original class
- The client code depends only on the interface it expects — it never knows an adapter is involved
- This pattern is essential when integrating third-party libraries or legacy code that you can't change

---

## 7. Decorator

**Pattern**: Attach additional responsibilities to an object dynamically. Decorators wrap an object and add behavior, without changing the object's interface.

**Scenario**: You're building a coffee shop ordering system. A basic coffee costs $2.00. But customers can add extras: milk (+$0.50), sugar (+$0.25), whipped cream (+$0.75). Each extra adds to both the cost and the description. A customer might want milk and sugar, or double whipped cream, or just a plain coffee. You need to support *any* combination without creating a separate class for each one.

### Step 1: Define the interface

```java
public interface Coffee {
    String description();
    double cost();
}
```

Simple. A coffee has a description and a cost.

### Step 2: Write the client code

```java
public class CoffeeShop {

    public static void printOrder(Coffee coffee) {
        System.out.printf("%s — $%.2f%n", coffee.description(), coffee.cost());
    }

    public static void main(String[] args) {
        // Order 1: Plain coffee
        Coffee plain = new SimpleCoffee();
        printOrder(plain);

        // Order 2: Coffee with milk
        Coffee withMilk = new MilkDecorator(new SimpleCoffee());
        printOrder(withMilk);

        // Order 3: Coffee with milk, sugar, and whipped cream
        Coffee fancy = new WhippedCreamDecorator(
                            new SugarDecorator(
                                new MilkDecorator(
                                    new SimpleCoffee())));
        printOrder(fancy);

        // Order 4: Double milk coffee
        Coffee doubleMilk = new MilkDecorator(
                                new MilkDecorator(
                                    new SimpleCoffee()));
        printOrder(doubleMilk);
    }
}
```

Look at the structure: each decorator wraps a `Coffee` and is itself a `Coffee`. You can stack them in any order, any number of times. The `printOrder` method doesn't know how many decorators are applied — it just sees a `Coffee`.

### Step 3: Implement

**SimpleCoffee** — the base:
- `description()` returns `"Simple coffee"`
- `cost()` returns `2.00`

**Each decorator** wraps an existing `Coffee` and adds to it. Here's the general structure:

```java
public class MilkDecorator implements Coffee {
    private final Coffee wrapped;

    public MilkDecorator(Coffee wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String description() {
        return wrapped.description() + ", milk";
    }

    @Override
    public double cost() {
        return wrapped.cost() + 0.50;
    }
}
```

Create three decorators following this pattern:
- **MilkDecorator** — adds `", milk"` to description and `0.50` to cost
- **SugarDecorator** — adds `", sugar"` and `0.25`
- **WhippedCreamDecorator** — adds `", whipped cream"` and `0.75`

### Step 4: Verify

Run the program. You should see:

```
Simple coffee — $2.00
Simple coffee, milk — $2.50
Simple coffee, milk, sugar, whipped cream — $3.50
Simple coffee, milk, milk — $3.00
```

Notice that double milk works automatically — each `MilkDecorator` adds $0.50. No special case needed.

### What you learned

- The Decorator pattern adds behavior by wrapping objects, not by subclassing — this allows any combination of extras
- Every decorator implements the same interface as the object it wraps, so they can be stacked freely
- The client code (like `printOrder`) sees a single `Coffee` regardless of how many decorators are applied — it doesn't need to know about the wrapping at all

---

## 8. Facade

**Pattern**: Provide a simplified interface to a complex subsystem. The facade coordinates multiple components behind a single, easy-to-use method.

**Scenario**: You're building a home theater system. It has multiple components — a DVD player, a projector, an amplifier, and lights. Each has its own interface with multiple methods. Watching a movie requires a specific sequence: dim the lights, turn on the projector, set the amplifier volume, load the DVD, and press play. Nobody wants to remember all those steps. A facade wraps them all behind `watchMovie()` and `endMovie()`.

### Step 1: Define the subsystem interfaces

These are the individual components. Each is simple on its own, but coordinating them all is complex.

```java
public interface DVDPlayer {
    void load(String title);
    void play();
    void stop();
    void eject();
}

public interface Projector {
    void turnOn();
    void turnOff();
    void setInput(String source);
}

public interface Amplifier {
    void turnOn();
    void turnOff();
    void setVolume(int level);
}

public interface Lights {
    void dim(int level);
    void on();
}
```

### Step 2: Write the client code

Write the facade interface and the client code that uses it.

```java
public class HomeTheaterFacade {
    private final DVDPlayer dvd;
    private final Projector projector;
    private final Amplifier amplifier;
    private final Lights lights;

    public HomeTheaterFacade(DVDPlayer dvd, Projector projector,
                             Amplifier amplifier, Lights lights) {
        this.dvd = dvd;
        this.projector = projector;
        this.amplifier = amplifier;
        this.lights = lights;
    }

    public void watchMovie(String title) {
        System.out.println("Get ready to watch " + title + "...");
        lights.dim(20);
        projector.turnOn();
        projector.setInput("DVD");
        amplifier.turnOn();
        amplifier.setVolume(7);
        dvd.load(title);
        dvd.play();
    }

    public void endMovie() {
        System.out.println("Shutting down movie theater...");
        dvd.stop();
        dvd.eject();
        amplifier.turnOff();
        projector.turnOff();
        lights.on();
    }
}
```

And the client:

```java
public class MovieNight {
    public static void main(String[] args) {
        // We'll create the subsystem components in Step 3
        // Then:
        // facade.watchMovie("Star Wars");
        // facade.endMovie();
    }
}
```

### Step 3: Implement

Create simple implementations of each subsystem component. They don't need to do anything complex — just print what they're doing:

- `SimpleDVDPlayer` — prints `"DVD: Loading Star Wars"`, `"DVD: Playing"`, `"DVD: Stopped"`, `"DVD: Ejected"`
- `SimpleProjector` — prints `"Projector: On"`, `"Projector: Off"`, `"Projector: Input set to DVD"`
- `SimpleAmplifier` — prints `"Amplifier: On"`, `"Amplifier: Off"`, `"Amplifier: Volume set to 7"`
- `SimpleLights` — prints `"Lights: Dimmed to 20%"`, `"Lights: On"`

Wire everything together in `main`:

```java
public static void main(String[] args) {
    HomeTheaterFacade theater = new HomeTheaterFacade(
        new SimpleDVDPlayer(),
        new SimpleProjector(),
        new SimpleAmplifier(),
        new SimpleLights()
    );

    theater.watchMovie("Star Wars");
    System.out.println();
    theater.endMovie();
}
```

### Step 4: Verify

Run the program. You should see the full sequence:

```
Get ready to watch Star Wars...
Lights: Dimmed to 20%
Projector: On
Projector: Input set to DVD
Amplifier: On
Amplifier: Volume set to 7
DVD: Loading Star Wars
DVD: Playing

Shutting down movie theater...
DVD: Stopped
DVD: Ejected
Amplifier: Off
Projector: Off
Lights: On
```

The caller just said `watchMovie("Star Wars")` — one method call that coordinated six operations across four subsystems.

### What you learned

- The Facade pattern simplifies a complex subsystem by providing a higher-level interface
- The subsystem components still exist and can be used directly if needed — the facade doesn't hide them, it just provides a convenient shortcut
- Notice how the facade takes its dependencies through the constructor (dependency injection) — making it testable and flexible

---

## 9. Composite + Visitor

**Pattern**: **Composite** lets you treat individual objects and groups of objects uniformly through a tree structure. **Visitor** lets you add new operations to the tree without modifying the tree classes.

**Scenario**: You're modeling a file system. A file has a name and a size. A folder has a name and contains children — which can be files *or* other folders. You need to perform operations on this tree: calculate total size, find files by extension. Instead of adding these operations as methods on `File` and `Folder` (which would require changing them every time you add an operation), you use the Visitor pattern to keep operations separate.

### Step 1: Define the interfaces

```java
public interface FileSystemNode {
    String name();
    void accept(FileSystemVisitor visitor);
}

public interface FileSystemVisitor {
    void visitFile(File file);
    void visitFolder(Folder folder);
}
```

The `accept` method is the key to the Visitor pattern: each node calls the appropriate `visit` method on the visitor. This is called "double dispatch" — the node tells the visitor what type it is, and the visitor performs the right action.

### Step 2: Write the client code

Write the code that builds a tree and runs visitors on it — before implementing anything.

```java
public class FileSystemDemo {

    public static void main(String[] args) {
        // Build a file tree:
        // root/
        //   readme.txt (100 bytes)
        //   src/
        //     Main.java (500 bytes)
        //     Utils.java (300 bytes)
        //   docs/
        //     guide.md (200 bytes)
        //     api.html (1500 bytes)

        Folder root = new Folder("root");
        root.add(new File("readme.txt", 100));

        Folder src = new Folder("src");
        src.add(new File("Main.java", 500));
        src.add(new File("Utils.java", 300));
        root.add(src);

        Folder docs = new Folder("docs");
        docs.add(new File("guide.md", 200));
        docs.add(new File("api.html", 1500));
        root.add(docs);

        // Operation 1: Calculate total size
        SizeCalculator sizeCalc = new SizeCalculator();
        root.accept(sizeCalc);
        System.out.println("Total size: " + sizeCalc.getTotalSize() + " bytes");

        // Operation 2: Find all .java files
        ExtensionFinder javaFinder = new ExtensionFinder(".java");
        root.accept(javaFinder);
        System.out.println("Java files: " + javaFinder.getFoundFiles());
    }
}
```

Notice: the tree is built using `Folder.add()`, which accepts any `FileSystemNode` — both files and folders. The visitors are separate objects that operate on the tree without the tree knowing anything about them.

### Step 3: Implement

**File** (leaf node):

```java
public class File implements FileSystemNode {
    private final String name;
    private final int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String name() { return name; }
    public int size() { return size; }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }
}
```

**Folder** (composite node) — contains a list of `FileSystemNode` children:
- Has an `add(FileSystemNode child)` method
- `accept()` calls `visitor.visitFolder(this)` — the visitor then decides whether to recurse into children
- Provide a `children()` method that returns the list (so visitors can iterate)

**SizeCalculator** visitor:
- Keeps a running `totalSize` counter
- `visitFile(File file)` — adds `file.size()` to the total
- `visitFolder(Folder folder)` — iterates over `folder.children()` and calls `child.accept(this)` on each
- Has a `getTotalSize()` method to retrieve the result

**ExtensionFinder** visitor:
- Takes the extension to search for in its constructor (e.g., `".java"`)
- Keeps a list of found file names
- `visitFile(File file)` — if `file.name()` ends with the extension, add it to the list
- `visitFolder(Folder folder)` — recurse into children
- Has a `getFoundFiles()` method to retrieve the results

### Step 4: Verify

Run the program. You should see:

```
Total size: 2600 bytes
Java files: [Main.java, Utils.java]
```

Now here's the power of this combination: add a third visitor — `LargeFileFinder` that finds files larger than a given threshold. You only create a new class. You don't change `File`, `Folder`, or any existing visitor. The tree structure is completely untouched.

### What you learned

- **Composite** lets you treat files and folders uniformly through `FileSystemNode` — you can build trees of arbitrary depth
- **Visitor** lets you add new operations to the tree without modifying `File` or `Folder` — each operation is a separate class
- Together, they give you a tree structure that's open to new operations: adding a new operation = adding a new visitor class, zero changes to the tree

---

## 10. Proxy

**Pattern**: Provide a stand-in for another object. The proxy controls access to the real object, adding behavior like caching, logging, or access control.

**Scenario**: You have a weather service that fetches temperature data from a remote API. Each call is slow and expensive. But the temperature doesn't change every second — if someone asks for the same city's temperature twice in a row, you shouldn't call the remote service again. A caching proxy sits in front of the real service, serves cached results when available, and only calls the real service on a cache miss.

### Step 1: Define the interface

```java
public interface WeatherService {
    double getTemperature(String city);
}
```

Simple. Give it a city, get a temperature.

### Step 2: Write the client code

```java
public class WeatherApp {

    public static void checkWeather(WeatherService service, String city) {
        System.out.printf("Temperature in %s: %.1f°C%n", city, service.getTemperature(city));
    }

    public static void main(String[] args) {
        // We'll create the service in Step 3
        // Then:
        // checkWeather(service, "Copenhagen");  // First call — fetches from API
        // checkWeather(service, "Copenhagen");  // Second call — should be cached
        // checkWeather(service, "London");      // Different city — fetches from API
        // checkWeather(service, "Copenhagen");  // Still cached
    }
}
```

The `checkWeather` method doesn't know if it's talking to a real service or a caching proxy. It just calls `getTemperature`.

### Step 3: Implement

**RealWeatherService** — simulates a slow API call:

```java
public class RealWeatherService implements WeatherService {
    @Override
    public double getTemperature(String city) {
        System.out.println("  [Fetching temperature from remote API for " + city + "...]");
        // Simulate different temperatures for different cities
        return switch (city) {
            case "Copenhagen" -> 8.5;
            case "London" -> 12.0;
            case "Tokyo" -> 22.3;
            default -> 20.0;
        };
    }
}
```

**CachingWeatherProxy** — wraps a `WeatherService` and caches results in a `HashMap`:

- Takes a `WeatherService` in its constructor
- Maintains a `Map<String, Double>` cache
- `getTemperature(String city)`:
  - If the city is in the cache, return the cached value (print something like `"  [Cache hit for Copenhagen]"`)
  - If not, call the wrapped service, store the result in the cache, and return it

Complete your `main`:

```java
public static void main(String[] args) {
    WeatherService realService = new RealWeatherService();
    WeatherService cachedService = new CachingWeatherProxy(realService);

    checkWeather(cachedService, "Copenhagen");
    checkWeather(cachedService, "Copenhagen");
    checkWeather(cachedService, "London");
    checkWeather(cachedService, "Copenhagen");
}
```

### Step 4: Verify

Run the program. You should see:

```
  [Fetching temperature from remote API for Copenhagen...]
Temperature in Copenhagen: 8.5°C
  [Cache hit for Copenhagen]
Temperature in Copenhagen: 8.5°C
  [Fetching temperature from remote API for London...]
Temperature in London: 12.0°C
  [Cache hit for Copenhagen]
Temperature in Copenhagen: 8.5°C
```

The API was called only twice — once for Copenhagen and once for London. The second and third Copenhagen lookups were served from the cache.

### What you learned

- The Proxy pattern wraps an object and controls access to it — the client doesn't know a proxy is involved
- Caching is just one use case; proxies can also add logging, access control, lazy initialization, or rate limiting
- The proxy implements the same interface as the real service, so it can be swapped in transparently

---

## 11. Bridge

**Pattern**: Separate an abstraction from its implementation so the two can vary independently. Avoids a "combinatorial explosion" of subclasses.

**Scenario**: You're building a remote control system for electronic devices. You have two types of remotes (basic and advanced) and two types of devices (TV and radio). Without the Bridge pattern, you'd need four classes: BasicTVRemote, BasicRadioRemote, AdvancedTVRemote, AdvancedRadioRemote. Add a third device? Six classes. A third remote type? Nine classes. The Bridge pattern lets remotes and devices vary independently.

### Step 1: Define the interfaces

```java
public interface Device {
    void turnOn();
    void turnOff();
    boolean isOn();
    int getVolume();
    void setVolume(int volume);
}
```

The `Device` interface represents the implementation side of the bridge.

### Step 2: Write the client code

Define the remote control abstraction and write client code that combines remotes with devices.

```java
public class Remote {
    protected final Device device;

    public Remote(Device device) {
        this.device = device;
    }

    public void power() {
        if (device.isOn()) {
            device.turnOff();
        } else {
            device.turnOn();
        }
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }
}

public class AdvancedRemote extends Remote {

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
    }
}
```

Client code:

```java
public class RemoteDemo {
    public static void main(String[] args) {
        // We'll create devices in Step 3, then:
        // Device tv = new TV();
        // Remote basicRemote = new Remote(tv);
        // basicRemote.power();
        // basicRemote.volumeUp();
        //
        // Device radio = new Radio();
        // AdvancedRemote advancedRemote = new AdvancedRemote(radio);
        // advancedRemote.power();
        // advancedRemote.volumeUp();
        // advancedRemote.mute();
    }
}
```

Notice: any remote works with any device. You don't need `TVRemote` and `RadioRemote` — you just pass the device to the remote's constructor.

### Step 3: Implement

Create two device implementations.

**TV:**
- Starts off, volume at 30
- `turnOn()` prints `"TV: Turning on"`, `turnOff()` prints `"TV: Turning off"`
- `setVolume(int v)` clamps to 0-100 and prints `"TV: Volume set to X"`

**Radio:**
- Starts off, volume at 20
- Similar print messages but prefixed with `"Radio:"`

Complete `main`:

```java
public static void main(String[] args) {
    System.out.println("--- Basic Remote + TV ---");
    Device tv = new TV();
    Remote tvRemote = new Remote(tv);
    tvRemote.power();
    tvRemote.volumeUp();
    tvRemote.volumeUp();

    System.out.println();

    System.out.println("--- Advanced Remote + Radio ---");
    Device radio = new Radio();
    AdvancedRemote radioRemote = new AdvancedRemote(radio);
    radioRemote.power();
    radioRemote.volumeUp();
    radioRemote.mute();

    System.out.println();

    System.out.println("--- Advanced Remote + TV ---");
    AdvancedRemote tvAdvanced = new AdvancedRemote(tv);
    tvAdvanced.mute();
}
```

### Step 4: Verify

Run the program. You should see each remote controlling its device correctly. The advanced remote can mute. Any remote works with any device — 2 remote types and 2 device types give you 4 combinations, but you only wrote 4 classes (2 remotes + 2 devices), not 4 combination classes.

Try adding a `SmartSpeaker` device. You write one new class. It immediately works with both `Remote` and `AdvancedRemote` — no new remote classes needed.

### What you learned

- The Bridge pattern separates *what* something does (remote) from *how* it's done (device), so the two hierarchies can grow independently
- Without Bridge, adding a device multiplies the number of remote classes. With Bridge, you just add one class.
- The "bridge" is the `Device` reference inside `Remote` — it connects the two sides

---

## 12. Null Object

**Pattern**: Instead of using `null` to represent "nothing," use an object that implements the expected interface but does nothing. Eliminates null checks throughout your code.

**Scenario**: You have an application with optional logging. Sometimes you want to log to the console, sometimes you want no logging at all. Without the Null Object pattern, you'd need `if (logger != null)` checks everywhere. With it, you use a `NullLogger` that implements `Logger` but does nothing — the calling code never checks for null.

### Step 1: Define the interface

```java
public interface Logger {
    void log(String message);
}
```

One method. That's all logging needs.

### Step 2: Write the client code

Write an application class that uses a logger — but design it so that logging is always safe, never requiring null checks.

```java
public class OrderProcessor {
    private final Logger logger;

    public OrderProcessor(Logger logger) {
        this.logger = logger;
    }

    public void processOrder(String orderId, double amount) {
        logger.log("Processing order: " + orderId);

        // Business logic
        if (amount > 1000) {
            logger.log("Large order detected — applying discount");
            amount *= 0.95;
        }

        logger.log("Order " + orderId + " processed. Final amount: $" +
                    String.format("%.2f", amount));
    }

    public static void main(String[] args) {
        // We'll create loggers in Step 3
    }
}
```

Look at the `processOrder` method: it calls `logger.log()` three times without ever checking if the logger is null. It assumes logging always works — because it does, even when logging is "off."

### Step 3: Implement

**ConsoleLogger** — the real logger:

```java
public class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
```

**NullLogger** — the "silent" logger:

```java
public class NullLogger implements Logger {
    @Override
    public void log(String message) {
        // Intentionally empty — this is the whole point
    }
}
```

Complete `main`:

```java
public static void main(String[] args) {
    System.out.println("=== With logging ===");
    OrderProcessor withLogging = new OrderProcessor(new ConsoleLogger());
    withLogging.processOrder("ORD-001", 500.00);
    withLogging.processOrder("ORD-002", 1500.00);

    System.out.println();

    System.out.println("=== Without logging ===");
    OrderProcessor silent = new OrderProcessor(new NullLogger());
    silent.processOrder("ORD-003", 750.00);
    silent.processOrder("ORD-004", 2000.00);
}
```

### Step 4: Verify

Run the program. With `ConsoleLogger`, you see all the log messages. With `NullLogger`, you see nothing from the silent processor — no output, no errors, no null pointer exceptions.

```
=== With logging ===
[LOG] Processing order: ORD-001
[LOG] Order ORD-001 processed. Final amount: $500.00
[LOG] Processing order: ORD-002
[LOG] Large order detected — applying discount
[LOG] Order ORD-002 processed. Final amount: $1425.00

=== Without logging ===
```

The `OrderProcessor` code is identical in both cases. No `if (logger != null)` anywhere.

### What you learned

- The Null Object pattern eliminates null checks by providing a "do nothing" implementation of an interface
- The calling code is simpler and safer — it never needs to worry about null references
- Toggling behavior on/off is just a matter of swapping which implementation you inject — same principle as every other pattern in this course

---

## Behavioral Patterns

Behavioral patterns deal with communication between objects. The core problem: how do objects divide work among themselves and communicate without being tightly coupled?

---

## 13. Strategy

**Pattern**: Define a family of algorithms, encapsulate each one behind an interface, and make them interchangeable. The client picks which strategy to use.

**Scenario**: You're building an online shop. Shipping costs depend on the strategy: standard shipping charges by weight, express shipping doubles the rate, and orders over a threshold get free shipping. The shop shouldn't have `if/else` chains for shipping logic — it should just delegate to whatever strategy is configured.

### Step 1: Define the interface

```java
public interface ShippingStrategy {
    double calculateCost(double orderTotal, double weightKg);
    String name();
}
```

Each strategy takes the order total and package weight, and returns the shipping cost. The `name()` method is for display purposes.

### Step 2: Write the client code

```java
public class Shop {
    private final ShippingStrategy shippingStrategy;

    public Shop(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public void checkout(String item, double price, double weightKg) {
        double shippingCost = shippingStrategy.calculateCost(price, weightKg);
        double total = price + shippingCost;

        System.out.println("Item: " + item);
        System.out.println("Price: $" + String.format("%.2f", price));
        System.out.println("Shipping (" + shippingStrategy.name() + "): $" +
                           String.format("%.2f", shippingCost));
        System.out.println("Total: $" + String.format("%.2f", total));
        System.out.println();
    }

    public static void main(String[] args) {
        // We'll plug in real strategies in Step 3
    }
}
```

The `Shop` doesn't know how shipping is calculated. It asks the strategy and uses the result. No `if/else`, no `switch` — just delegation.

### Step 3: Implement

Create three strategies:

**StandardShipping** — charges $0.50 per kg, minimum $3.00:
- `calculateCost(total, weight)` returns `Math.max(3.00, weight * 0.50)`

**ExpressShipping** — charges $1.00 per kg, minimum $6.00 (double the standard rate):
- `calculateCost(total, weight)` returns `Math.max(6.00, weight * 1.00)`

**FreeShippingAboveThreshold** — free if order total exceeds a threshold (e.g., $50), otherwise standard rate:
- Takes the threshold as a constructor parameter
- `calculateCost(total, weight)` returns `0.0` if `total >= threshold`, otherwise uses standard rate

Complete `main`:

```java
public static void main(String[] args) {
    Shop standardShop = new Shop(new StandardShipping());
    Shop expressShop = new Shop(new ExpressShipping());
    Shop freeAbove50 = new Shop(new FreeShippingAboveThreshold(50.00));

    System.out.println("=== Standard Shipping ===");
    standardShop.checkout("Book", 15.99, 0.8);

    System.out.println("=== Express Shipping ===");
    expressShop.checkout("Book", 15.99, 0.8);

    System.out.println("=== Free Shipping (order > $50) ===");
    freeAbove50.checkout("Book", 15.99, 0.8);
    freeAbove50.checkout("Laptop", 899.99, 3.5);
}
```

### Step 4: Verify

Run the program. The same item gets different shipping costs depending on the strategy. The laptop gets free shipping because it's over $50.

Try adding a `PickupInStore` strategy where the cost is always $0.00. How much code changes? (One new class. Zero changes elsewhere.)

### What you learned

- The Strategy pattern lets you swap algorithms without changing the code that uses them
- Each strategy is a separate class implementing the same interface — no `if/else` chains in the client
- This is the same principle as every interface-first design: the client depends on the abstraction, and the concrete strategy can be swapped at runtime

---

## 14. Command

**Pattern**: Encapsulate a request as an object. This lets you parameterize actions, queue them, log them, and — most importantly — undo them.

**Scenario**: You're building a calculator that supports undo. Each operation (add, subtract, multiply) is a command object that knows how to execute itself and how to reverse itself. A history stack tracks every command, letting you undo operations one by one.

### Step 1: Define the interface

```java
public interface Command {
    void execute();
    void undo();
}
```

Every command can be executed and undone. That's the contract.

### Step 2: Write the client code

```java
public class Calculator {
    private double currentValue = 0;

    public double getCurrentValue() { return currentValue; }
    public void setValue(double value) { this.currentValue = value; }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        CommandHistory history = new CommandHistory();

        System.out.println("Start: " + calc.getCurrentValue());

        // Execute commands
        Command add10 = new AddCommand(calc, 10);
        add10.execute();
        history.push(add10);
        System.out.println("After +10: " + calc.getCurrentValue());

        Command multiply3 = new MultiplyCommand(calc, 3);
        multiply3.execute();
        history.push(multiply3);
        System.out.println("After *3: " + calc.getCurrentValue());

        Command subtract5 = new SubtractCommand(calc, 5);
        subtract5.execute();
        history.push(subtract5);
        System.out.println("After -5: " + calc.getCurrentValue());

        // Undo everything
        System.out.println("\n--- Undoing ---");
        while (history.hasCommands()) {
            history.pop().undo();
            System.out.println("After undo: " + calc.getCurrentValue());
        }
    }
}
```

The pattern is: create a command, execute it, push it onto the history. To undo, pop from the history and call `undo()`. The calculator's value goes back to where it started.

### Step 3: Implement

**CommandHistory** — a simple stack wrapper:
- Uses a `Stack<Command>` (or `Deque<Command>`) internally
- `push(Command cmd)` — adds to the stack
- `pop()` — removes and returns the most recent command
- `hasCommands()` — returns `true` if the stack is not empty

**AddCommand:**

```java
public class AddCommand implements Command {
    private final Calculator calculator;
    private final double operand;

    public AddCommand(Calculator calculator, double operand) {
        this.calculator = calculator;
        this.operand = operand;
    }

    @Override
    public void execute() {
        calculator.setValue(calculator.getCurrentValue() + operand);
    }

    @Override
    public void undo() {
        calculator.setValue(calculator.getCurrentValue() - operand);
    }
}
```

`execute()` adds; `undo()` subtracts. They're exact inverses.

Create similarly:
- **SubtractCommand** — `execute()` subtracts, `undo()` adds
- **MultiplyCommand** — `execute()` multiplies, `undo()` divides (store the operand so you can reverse it)

### Step 4: Verify

Run the program. You should see:

```
Start: 0.0
After +10: 10.0
After *3: 30.0
After -5: 25.0

--- Undoing ---
After undo: 30.0
After undo: 10.0
After undo: 0.0
```

The value returns to 0.0 after all three undos — each command perfectly reverses itself.

### What you learned

- The Command pattern turns operations into objects that can be executed, undone, stored, and replayed
- You've used this pattern before in the text adventure — `Command.execute()` in the command registry is exactly this idea
- The history stack enables undo with no special logic in the calculator — the commands track their own inverse operations

---

## 15. Observer

**Pattern**: Define a one-to-many dependency between objects. When one object (the subject) changes state, all its dependents (observers) are notified automatically.

**Scenario**: You're building a weather station. When the temperature or humidity changes, multiple displays need to update: one shows current conditions, another tracks statistics (min/max/average). Adding a new display shouldn't require changing the weather station.

### Step 1: Define the interfaces

```java
public interface WeatherObserver {
    void update(double temperature, double humidity);
}

public interface WeatherSubject {
    void registerObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}
```

The subject manages a list of observers and notifies them when state changes. Observers receive the new data through `update()`.

### Step 2: Write the client code

```java
public class WeatherStationDemo {

    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statsDisplay = new StatisticsDisplay();

        station.registerObserver(currentDisplay);
        station.registerObserver(statsDisplay);

        // Simulate weather changes
        station.setMeasurements(22.5, 65.0);
        station.setMeasurements(24.1, 70.2);
        station.setMeasurements(19.8, 55.0);

        // Remove one display and change weather again
        System.out.println("--- Removing current conditions display ---");
        station.removeObserver(currentDisplay);
        station.setMeasurements(28.3, 80.0);
    }
}
```

Each call to `setMeasurements` triggers all registered observers to update. After removing an observer, only the remaining ones are notified.

### Step 3: Implement

**WeatherStation** implements `WeatherSubject`:
- Keeps a `List<WeatherObserver>` of registered observers
- `setMeasurements(double temp, double humidity)` stores the new values and calls `notifyObservers()`
- `notifyObservers()` iterates over all observers and calls `update(temperature, humidity)` on each

**CurrentConditionsDisplay** implements `WeatherObserver`:
- `update()` simply prints the current temperature and humidity:
  `"Current conditions: 22.5°C and 65.0% humidity"`

**StatisticsDisplay** implements `WeatherObserver`:
- Tracks all received temperatures in a list
- `update()` adds the temperature, then prints min, max, and average:
  `"Statistics — Min: 19.8°C, Max: 24.1°C, Avg: 22.1°C"`

### Step 4: Verify

Run the program. You should see both displays updating after each measurement change. After removing the current conditions display, only the statistics display updates.

```
Current conditions: 22.5°C and 65.0% humidity
Statistics — Min: 22.5°C, Max: 22.5°C, Avg: 22.5°C
Current conditions: 24.1°C and 70.2% humidity
Statistics — Min: 22.5°C, Max: 24.1°C, Avg: 23.3°C
Current conditions: 19.8°C and 55.0% humidity
Statistics — Min: 19.8°C, Max: 24.1°C, Avg: 22.1°C
--- Removing current conditions display ---
Statistics — Min: 19.8°C, Max: 28.3°C, Avg: 23.7°C
```

Try adding a `ForecastDisplay` that predicts "improving" or "worsening" based on humidity trends. How much existing code changes? (None — you add a new class and register it.)

### What you learned

- The Observer pattern decouples the subject from its observers — the weather station doesn't know what the displays do with the data
- Adding or removing observers at runtime requires zero changes to the subject
- This pattern is everywhere: GUI event listeners, publish-subscribe systems, reactive programming

---

## 16. Iterator

**Pattern**: Provide a way to access elements of a collection sequentially without exposing the underlying structure. In Java, this means implementing `Iterable` to work with for-each loops.

**Scenario**: You want a `NumberRange` class that generates a sequence of numbers with a given start, end, and step. It should work in for-each loops just like any other Java collection, even though it doesn't store the numbers in memory — it generates them on the fly.

### Step 1: Define the interface

Java already provides the interfaces you need:

```java
// These are built into Java — you don't create them:
// Iterable<T> — has iterator() method
// Iterator<T> — has hasNext() and next() methods
```

Your `NumberRange` will implement `Iterable<Integer>`, and you'll create a `NumberRangeIterator` that implements `Iterator<Integer>`.

### Step 2: Write the client code

```java
public class RangeDemo {

    public static void main(String[] args) {
        // Count from 0 to 20 by 3s
        System.out.println("Counting by 3s:");
        for (int n : new NumberRange(0, 20, 3)) {
            System.out.print(n + " ");
        }
        System.out.println();

        // Count from 10 down to 1 by -1
        System.out.println("Countdown:");
        for (int n : new NumberRange(10, 0, -1)) {
            System.out.print(n + " ");
        }
        System.out.println();

        // Even numbers from 2 to 20
        System.out.println("Even numbers:");
        for (int n : new NumberRange(2, 20, 2)) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
```

Notice: `NumberRange` works in a standard for-each loop, just like `List` or `Set`. The caller doesn't know that the numbers are generated on the fly — it looks like any other collection.

### Step 3: Implement

**NumberRange** implements `Iterable<Integer>`:

```java
public class NumberRange implements Iterable<Integer> {
    private final int from;
    private final int to;
    private final int step;

    public NumberRange(int from, int to, int step) {
        if (step == 0) throw new IllegalArgumentException("Step cannot be zero");
        this.from = from;
        this.to = to;
        this.step = step;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new NumberRangeIterator();
    }

    private class NumberRangeIterator implements Iterator<Integer> {
        // Your implementation here:
        // - Track the current value (starts at 'from')
        // - hasNext(): return true if current hasn't passed 'to'
        //   (careful: handle both positive and negative steps!)
        // - next(): return current value and advance by step
    }
}
```

The key logic is in `hasNext()`:
- If step is positive, there's a next value while `current <= to`
- If step is negative, there's a next value while `current >= to`

`next()` should return the current value and then advance it by `step`.

### Step 4: Verify

Run the program. You should see:

```
Counting by 3s:
0 3 6 9 12 15 18
Countdown:
10 9 8 7 6 5 4 3 2 1
Even numbers:
2 4 6 8 10 12 14 16 18 20
```

The ranges work in for-each loops, but no array or list stores the numbers — they're generated one at a time. Try using your `NumberRange` with Java streams: `StreamSupport.stream(new NumberRange(1, 100, 1).spliterator(), false)`.

### What you learned

- The Iterator pattern provides sequential access to elements without exposing the underlying data structure — in this case, there's no data structure at all
- Implementing `Iterable<T>` lets your class work with Java's for-each loops, streams, and any code that expects an iterable
- This is the same principle behind `List.iterator()`, `Set.iterator()`, and every other Java collection — they all implement the Iterator pattern

---

## 17. Template Method

**Pattern**: Define the skeleton of an algorithm in a base class, deferring some steps to subclasses. The overall structure stays the same, but subclasses customize specific steps.

**Scenario**: You're building a report generator. Every report follows the same process: gather data, format a header, format the body, format a footer, and combine everything. But a CSV report formats things differently from an HTML report. The algorithm is the same; the formatting steps vary.

### Step 1: Define the abstract class

This pattern uses an abstract class (not an interface) because the template method provides a concrete algorithm that calls abstract steps.

```java
public abstract class ReportGenerator {

    // This is the template method — it defines the algorithm
    public final String generate() {
        String data = gatherData();
        String header = formatHeader(data);
        String body = formatBody(data);
        String footer = formatFooter();
        return header + "\n" + body + "\n" + footer;
    }

    // These are the steps that subclasses customize
    protected abstract String gatherData();
    protected abstract String formatHeader(String data);
    protected abstract String formatBody(String data);
    protected abstract String formatFooter();
}
```

The `generate()` method is `final` — subclasses cannot change the algorithm. They can only fill in the steps. This is the "template" — a fixed structure with replaceable parts.

### Step 2: Write the client code

```java
public class ReportDemo {

    public static void main(String[] args) {
        ReportGenerator csvReport = new CSVReportGenerator();
        ReportGenerator htmlReport = new HTMLReportGenerator();

        System.out.println("=== CSV Report ===");
        System.out.println(csvReport.generate());

        System.out.println();

        System.out.println("=== HTML Report ===");
        System.out.println(htmlReport.generate());
    }
}
```

Same `generate()` call, different output formats. The algorithm is identical; the steps produce different results.

### Step 3: Implement

Create two subclasses of `ReportGenerator`.

**CSVReportGenerator:**
- `gatherData()` — returns some sample data (e.g., `"Alice,90;Bob,85;Charlie,92"`)
- `formatHeader(data)` — returns `"Name,Grade"`
- `formatBody(data)` — parses the data string and returns CSV rows like `"Alice,90\nBob,85\nCharlie,92"`
- `formatFooter()` — returns `"Generated: CSV Format"`

**HTMLReportGenerator:**
- `gatherData()` — returns the same sample data
- `formatHeader(data)` — returns `"<h1>Grade Report</h1><table><tr><th>Name</th><th>Grade</th></tr>"`
- `formatBody(data)` — returns HTML table rows like `"<tr><td>Alice</td><td>90</td></tr>..."`
- `formatFooter()` — returns `"</table><p>Generated: HTML Format</p>"`

Don't worry about making the HTML perfect — the point is that the same algorithm produces structurally different outputs.

### Step 4: Verify

Run the program. You should see a CSV-formatted report and an HTML-formatted report, both generated by the same `generate()` method calling different step implementations.

Try adding a `MarkdownReportGenerator`. You only override the four abstract methods — the algorithm in `generate()` stays exactly the same.

### What you learned

- The Template Method pattern defines "the algorithm" in the base class and lets subclasses fill in specific steps
- The `final` keyword on `generate()` ensures nobody can change the algorithm's structure — only the individual steps
- This is one of the few patterns where abstract classes are more natural than interfaces, because the pattern requires a *concrete* method (the template) alongside *abstract* methods (the steps)

---

## 18. State

**Pattern**: Allow an object to change its behavior when its internal state changes. The object appears to change its class.

**Scenario**: You're building a vending machine. It has three states: idle (waiting for a coin), has-coin (waiting for product selection), and dispensing (delivering the product). Each state handles user actions differently — inserting a coin in the idle state transitions to has-coin, but inserting a coin in the has-coin state should return the extra coin. Instead of a big `switch` statement, each state is a separate class that handles its own behavior.

### Step 1: Define the interface

```java
public interface VendingState {
    void insertCoin(VendingMachine machine);
    void selectProduct(VendingMachine machine);
    void dispense(VendingMachine machine);
}
```

Each state handles all three user actions. The `VendingMachine` is passed as a parameter so the state can trigger state transitions.

### Step 2: Write the client code

First, the vending machine that delegates to its current state:

```java
public class VendingMachine {
    private VendingState currentState;

    public VendingMachine() {
        this.currentState = new IdleState(); // Start idle
    }

    public void setState(VendingState state) {
        this.currentState = state;
    }

    public void insertCoin() {
        currentState.insertCoin(this);
    }

    public void selectProduct() {
        currentState.selectProduct(this);
    }

    public void dispense() {
        currentState.dispense(this);
    }
}
```

Then the usage:

```java
public class VendingMachineDemo {

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // Happy path: insert coin, select product, dispense
        System.out.println("=== Normal Purchase ===");
        machine.insertCoin();
        machine.selectProduct();
        machine.dispense();

        // Error cases
        System.out.println("\n=== Error Cases ===");
        machine.selectProduct();   // No coin inserted — should show error
        machine.dispense();        // Nothing to dispense — should show error

        // Another successful purchase
        System.out.println("\n=== Second Purchase ===");
        machine.insertCoin();
        machine.insertCoin();      // Already has a coin — should return it
        machine.selectProduct();
        machine.dispense();
    }
}
```

### Step 3: Implement

Create three state implementations:

**IdleState:**
- `insertCoin()` — prints `"Coin inserted"`, transitions to `HasCoinState`: `machine.setState(new HasCoinState())`
- `selectProduct()` — prints `"Please insert a coin first"`
- `dispense()` — prints `"Please insert a coin and select a product"`

**HasCoinState:**
- `insertCoin()` — prints `"Returning extra coin — already has a coin"`
- `selectProduct()` — prints `"Product selected"`, transitions to `DispensingState`
- `dispense()` — prints `"Please select a product first"`

**DispensingState:**
- `insertCoin()` — prints `"Please wait, dispensing in progress"`
- `selectProduct()` — prints `"Already dispensing, please wait"`
- `dispense()` — prints `"Product dispensed. Thank you!"`, transitions back to `IdleState`

### Step 4: Verify

Run the program. You should see the machine moving through states correctly:

```
=== Normal Purchase ===
Coin inserted
Product selected
Product dispensed. Thank you!

=== Error Cases ===
Please insert a coin first
Please insert a coin and select a product

=== Second Purchase ===
Coin inserted
Returning extra coin — already has a coin
Product selected
Product dispensed. Thank you!
```

Each state handles the same method calls differently. No `if/else` or `switch` in `VendingMachine` — just delegation to the current state.

### What you learned

- The State pattern eliminates complex conditionals by distributing behavior across state classes
- State transitions are explicit — each state knows which state comes next
- The `VendingMachine` doesn't know what its states are — it just delegates, and the current state object determines the behavior

---

## 19. Chain of Responsibility

**Pattern**: Pass a request along a chain of handlers. Each handler either processes the request or passes it to the next handler in the chain.

**Scenario**: Your company has a purchase approval process. A manager can approve purchases up to $1,000. A director can approve up to $10,000. The VP can approve up to $100,000. Anything above that is rejected. Instead of a single method with if/else chains, each approver handles what it can and passes the rest up.

### Step 1: Define the interface

```java
public interface Approver {
    void setNext(Approver next);
    void approve(PurchaseRequest request);
}

public record PurchaseRequest(String description, double amount) {}
```

Each approver has a `setNext` to link to the next handler, and `approve` to handle (or forward) the request.

### Step 2: Write the client code

```java
public class PurchaseSystem {

    public static void main(String[] args) {
        // Build the chain: Manager -> Director -> VP
        Approver manager = new Manager();
        Approver director = new Director();
        Approver vp = new VicePresident();

        manager.setNext(director);
        director.setNext(vp);

        // Submit requests of increasing amounts
        manager.approve(new PurchaseRequest("Office supplies", 500));
        manager.approve(new PurchaseRequest("New laptop", 4500));
        manager.approve(new PurchaseRequest("Server upgrade", 50000));
        manager.approve(new PurchaseRequest("Building renovation", 200000));
    }
}
```

Every request starts at the manager. If the manager can't handle it, it passes to the director. If the director can't handle it, it passes to the VP. If nobody can handle it, it's rejected.

### Step 3: Implement

Create a base structure that all approvers share. One approach is an abstract class:

```java
public abstract class BaseApprover implements Approver {
    private Approver next;

    @Override
    public void setNext(Approver next) {
        this.next = next;
    }

    protected void passToNext(PurchaseRequest request) {
        if (next != null) {
            next.approve(request);
        } else {
            System.out.println("REJECTED: No one can approve $" +
                String.format("%.2f", request.amount()) +
                " for " + request.description());
        }
    }
}
```

Now create three concrete approvers:

**Manager** extends `BaseApprover`:
- Can approve up to $1,000
- `approve()`: if `amount <= 1000`, print `"Manager approved: <description> ($<amount>)"`. Otherwise, call `passToNext(request)`

**Director** extends `BaseApprover`:
- Can approve up to $10,000
- Same structure, higher limit

**VicePresident** extends `BaseApprover`:
- Can approve up to $100,000
- Same structure, highest limit

### Step 4: Verify

Run the program. You should see:

```
Manager approved: Office supplies ($500.00)
Director approved: New laptop ($4500.00)
VP approved: Server upgrade ($50000.00)
REJECTED: No one can approve $200000.00 for Building renovation
```

Each request was handled by the appropriate authority level. Try inserting a new `TeamLead` that approves up to $200, added before the Manager in the chain. Only one new class, one new link — existing code doesn't change.

### What you learned

- The Chain of Responsibility pattern decouples the sender of a request from its handler — the sender doesn't know who will handle it
- The chain can be reconfigured at runtime by changing the linking
- This pattern eliminates complex if/else chains by distributing the decision logic across handler objects

---

## 20. Memento

**Pattern**: Capture an object's internal state so it can be restored later, without exposing the object's implementation details.

**Scenario**: You're building a text editor with undo support. The editor can save snapshots of its state at any time. An undo operation restores the most recent snapshot. The key insight: the snapshot (memento) is a simple data object — it doesn't know how to restore anything. Only the editor knows how to use it.

### Step 1: Define the snapshot

Use a record for the memento — it's an immutable data carrier.

```java
public record EditorSnapshot(String content, int cursorPosition) {}
```

This is the memento. It captures the editor's state — content and cursor position — without exposing any editor behavior.

### Step 2: Write the client code

```java
public class TextEditorDemo {

    public static void main(String[] args) {
        Editor editor = new Editor();
        EditorHistory history = new EditorHistory();

        // Type and save
        editor.type("Hello");
        history.push(editor.save());
        System.out.println("Saved: \"" + editor.content() + "\"");

        editor.type(" World");
        history.push(editor.save());
        System.out.println("Saved: \"" + editor.content() + "\"");

        editor.type("!!!");
        System.out.println("Current: \"" + editor.content() + "\"");

        // Undo
        System.out.println("\n--- Undoing ---");
        editor.restore(history.pop());
        System.out.println("After undo: \"" + editor.content() + "\"");

        editor.restore(history.pop());
        System.out.println("After undo: \"" + editor.content() + "\"");
    }
}
```

The pattern: make changes, save snapshots to history, then restore from history to undo. The editor creates and consumes snapshots; the history just stores them.

### Step 3: Implement

**Editor:**
- Has a `content` field (String, starts empty) and a `cursorPosition` field (int, starts at 0)
- `type(String text)` — appends text to content and moves cursor to the end
- `content()` — returns the current content
- `save()` — returns a new `EditorSnapshot` capturing the current state
- `restore(EditorSnapshot snapshot)` — sets content and cursor position from the snapshot

**EditorHistory:**
- Uses a `Stack<EditorSnapshot>` (or `Deque<EditorSnapshot>`)
- `push(EditorSnapshot snapshot)` — saves a snapshot
- `pop()` — returns and removes the most recent snapshot
- `hasSnapshots()` — returns true if there are saved snapshots

### Step 4: Verify

Run the program. You should see:

```
Saved: "Hello"
Saved: "Hello World"
Current: "Hello World!!!"

--- Undoing ---
After undo: "Hello World"
After undo: "Hello"
```

The editor restored to its previous states perfectly. The snapshots are immutable records — they can't be modified after creation, which prevents accidental corruption of the undo history.

### What you learned

- The Memento pattern separates state capture from state restoration — the snapshot (record) holds the data, the editor knows how to use it
- Records are perfect for mementos — they're immutable, compact, and auto-generate `equals()`, `hashCode()`, and `toString()`
- The history is just a stack — the pattern doesn't prescribe how snapshots are stored, only that they capture enough state to restore later

---

## Architectural Patterns

Architectural patterns operate at a higher level than the GoF patterns. They organize entire applications into layers or components with clear responsibilities.

---

## 21. MVC

**Pattern**: Separate an application into three parts: **Model** (data and logic), **View** (display), and **Controller** (handles input and coordinates model and view).

**Scenario**: You're building a student grade tracker. The model stores students and their grades. The view displays the information. The controller handles adding students and grades, and coordinates between model and view. The power is in the separation: you could swap the console view for a GUI without changing the model or controller logic.

### Step 1: Define the interfaces

```java
public interface GradeView {
    void showStudents(List<String> students);
    void showAverage(String student, double average);
    void showMessage(String message);
}
```

The model doesn't need an interface for this exercise — it's a concrete class. But the view is an interface because we want it to be swappable.

### Step 2: Write the client code

Write the controller — it coordinates model and view without being tightly coupled to a specific view implementation.

```java
public class GradeController {
    private final GradeBook model;
    private final GradeView view;

    public GradeController(GradeBook model, GradeView view) {
        this.model = model;
        this.view = view;
    }

    public void addStudent(String name) {
        model.addStudent(name);
        view.showMessage("Added student: " + name);
    }

    public void addGrade(String student, double grade) {
        model.addGrade(student, grade);
        view.showMessage("Added grade " + grade + " for " + student);
    }

    public void showReport() {
        view.showStudents(model.getStudents());
        for (String student : model.getStudents()) {
            view.showAverage(student, model.getAverage(student));
        }
    }
}
```

And the main:

```java
public class GradeTrackerApp {

    public static void main(String[] args) {
        GradeBook model = new GradeBook();
        GradeView view = new ConsoleGradeView();
        GradeController controller = new GradeController(model, view);

        controller.addStudent("Alice");
        controller.addStudent("Bob");
        controller.addGrade("Alice", 92.0);
        controller.addGrade("Alice", 88.0);
        controller.addGrade("Bob", 75.0);
        controller.addGrade("Bob", 82.0);
        controller.addGrade("Bob", 91.0);

        System.out.println();
        controller.showReport();
    }
}
```

### Step 3: Implement

**GradeBook (the model):**
- Stores students and their grades (e.g., `Map<String, List<Double>>`)
- `addStudent(String name)` — adds a student with an empty grade list
- `addGrade(String student, double grade)` — adds a grade for the student
- `getStudents()` — returns a `List<String>` of student names
- `getAverage(String student)` — computes and returns the average grade

**ConsoleGradeView (the view):**
- `showStudents(List<String> students)` — prints `"Students: Alice, Bob"`
- `showAverage(String student, double average)` — prints `"Alice: average 90.0"`
- `showMessage(String message)` — prints the message

### Step 4: Verify

Run the program. You should see the messages from adding students and grades, followed by a report showing each student's average.

Think about this: to make this a GUI application, you'd create a `SwingGradeView` or `JavaFXGradeView` implementing the same `GradeView` interface. The controller and model stay identical. That's the power of MVC.

### What you learned

- MVC separates data (model), display (view), and coordination (controller) so each can change independently
- The view is an interface — swapping from console to GUI requires only a new view implementation
- The controller never formats output directly; the model never prints anything — each part has exactly one responsibility

---

## 22. Repository

**Pattern**: Encapsulate data access behind a collection-like interface. The rest of the application works with objects, not with database queries or file I/O.

**Scenario**: You're building a book library system. You need to store books, find them by ID, search by author, and delete them. The Repository pattern hides *how* books are stored (in memory, in a file, in a database) behind a clean interface. The application code works with `BookRepository` and never knows where the data lives.

### Step 1: Define the interface

```java
public record Book(String id, String title, String author, int year) {}

public interface BookRepository {
    void save(Book book);
    Book findById(String id);
    List<Book> findAll();
    List<Book> findByAuthor(String author);
    void delete(String id);
}
```

This looks like a collection with extra query methods. That's the point — the repository makes data storage look like working with objects.

### Step 2: Write the client code

Write a library service that uses the repository — it doesn't know or care about the storage mechanism.

```java
public class LibraryService {
    private final BookRepository repository;

    public LibraryService(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(Book book) {
        repository.save(book);
        System.out.println("Added: " + book.title());
    }

    public void showAllBooks() {
        List<Book> books = repository.findAll();
        System.out.println("All books (" + books.size() + "):");
        for (Book book : books) {
            System.out.println("  " + book.title() + " by " + book.author() +
                             " (" + book.year() + ")");
        }
    }

    public void showBooksByAuthor(String author) {
        List<Book> books = repository.findByAuthor(author);
        System.out.println("Books by " + author + ":");
        for (Book book : books) {
            System.out.println("  " + book.title() + " (" + book.year() + ")");
        }
    }

    public static void main(String[] args) {
        // We'll create the repository in Step 3
    }
}
```

### Step 3: Implement

**InMemoryBookRepository:**
- Uses a `Map<String, Book>` internally (id -> book)
- `save(Book book)` — puts the book into the map
- `findById(String id)` — gets from the map (return `null` or throw if not found — your choice)
- `findAll()` — returns all values as a new `List`
- `findByAuthor(String author)` — filters the values and returns matching books
- `delete(String id)` — removes from the map

Complete `main`:

```java
public static void main(String[] args) {
    BookRepository repository = new InMemoryBookRepository();
    LibraryService library = new LibraryService(repository);

    library.addBook(new Book("1", "Clean Code", "Robert Martin", 2008));
    library.addBook(new Book("2", "Refactoring", "Martin Fowler", 1999));
    library.addBook(new Book("3", "Design Patterns", "Gang of Four", 1994));
    library.addBook(new Book("4", "Clean Architecture", "Robert Martin", 2017));

    System.out.println();
    library.showAllBooks();

    System.out.println();
    library.showBooksByAuthor("Robert Martin");
}
```

**Bonus sketch**: Think about what a `FileBookRepository` would look like. It would implement the same `BookRepository` interface, but `save()` would write to a file and `findAll()` would read from a file. The `LibraryService` would work identically with either implementation — you'd just pass a different repository to its constructor. You don't need to implement this, but sketch the idea in comments.

### Step 4: Verify

Run the program. You should see:

```
Added: Clean Code
Added: Refactoring
Added: Design Patterns
Added: Clean Architecture

All books (4):
  Clean Code by Robert Martin (2008)
  Refactoring by Martin Fowler (1999)
  Design Patterns by Gang of Four (1994)
  Clean Architecture by Robert Martin (2017)

Books by Robert Martin:
  Clean Code (2008)
  Clean Architecture (2017)
```

The `LibraryService` has no idea that books are stored in a `HashMap`. If you later switch to a file-based or database-backed repository, the service code doesn't change at all.

### What you learned

- The Repository pattern abstracts data storage behind a collection-like interface — the application works with objects, not storage details
- Switching storage mechanisms (memory, file, database) requires only a new repository implementation — no changes to business logic
- This is interface-first design applied to data access: define what you need, implement how it works separately

---

## 23. Dependency Injection

**Pattern**: Instead of an object creating its own dependencies, receive them from the outside (typically through the constructor). This makes dependencies explicit, swappable, and testable.

**Scenario**: You have an `OrderProcessor` that sends confirmation emails, checks inventory, and logs activity. The current implementation creates all its dependencies internally — making it impossible to test, impossible to swap email providers, and impossible to turn off logging. You'll refactor it step by step.

### Step 1: The tightly-coupled version (the problem)

Start by creating this tightly-coupled class:

```java
public class OrderProcessor {

    public void processOrder(String orderId, String product, int quantity) {
        // Check inventory — tightly coupled to concrete class
        InMemoryInventory inventory = new InMemoryInventory();
        if (!inventory.isAvailable(product, quantity)) {
            System.out.println("Out of stock: " + product);
            return;
        }
        inventory.reserve(product, quantity);

        // Send confirmation — tightly coupled to concrete class
        EmailService emailService = new EmailService();
        emailService.send("customer@example.com",
            "Order " + orderId + ": " + quantity + "x " + product + " confirmed!");

        // Log the order — tightly coupled to concrete class
        ConsoleLogger logger = new ConsoleLogger();
        logger.log("Processed order " + orderId);
    }
}
```

Also create the three dependency classes with simple implementations:
- `InMemoryInventory` — has `isAvailable(String product, int qty)` and `reserve(String product, int qty)` (can use a `HashMap<String, Integer>` for stock levels)
- `EmailService` — has `send(String to, String message)` that prints the email
- `ConsoleLogger` — has `log(String message)` that prints to console

Write a `main` that uses `OrderProcessor`. It works, but now try to answer:
- How do you test `processOrder` without actually sending emails?
- How do you use a different logging system?
- How do you check if the right email was sent?

You can't. Every dependency is created internally. You have no control.

### Step 2: Extract interfaces

Define interfaces for each dependency:

```java
public interface Inventory {
    boolean isAvailable(String product, int quantity);
    void reserve(String product, int quantity);
}

public interface NotificationService {
    void send(String to, String message);
}

public interface Logger {
    void log(String message);
}
```

Make your existing concrete classes implement these interfaces:
- `InMemoryInventory implements Inventory`
- `EmailService implements NotificationService`
- `ConsoleLogger implements Logger`

### Step 3: Refactor to constructor injection

Rewrite `OrderProcessor` to receive its dependencies through the constructor:

```java
public class OrderProcessor {
    private final Inventory inventory;
    private final NotificationService notificationService;
    private final Logger logger;

    public OrderProcessor(Inventory inventory, NotificationService notificationService,
                          Logger logger) {
        this.inventory = inventory;
        this.notificationService = notificationService;
        this.logger = logger;
    }

    public void processOrder(String orderId, String product, int quantity) {
        if (!inventory.isAvailable(product, quantity)) {
            logger.log("Out of stock: " + product);
            return;
        }
        inventory.reserve(product, quantity);

        notificationService.send("customer@example.com",
            "Order " + orderId + ": " + quantity + "x " + product + " confirmed!");

        logger.log("Processed order " + orderId);
    }
}
```

Update `main`:

```java
public static void main(String[] args) {
    Inventory inventory = new InMemoryInventory();
    // Pre-stock the inventory
    // (add some products to your InMemoryInventory)

    NotificationService notifications = new EmailService();
    Logger logger = new ConsoleLogger();

    OrderProcessor processor = new OrderProcessor(inventory, notifications, logger);
    processor.processOrder("ORD-001", "Widget", 5);
    processor.processOrder("ORD-002", "Gadget", 2);
}
```

### Step 4: Verify

Run both versions — the output should be identical. The behavior hasn't changed. But now compare:

**Before (tightly coupled):**
- Dependencies are hidden inside the method
- Can't test without real email sending
- Can't swap implementations
- Can't tell from the outside what `OrderProcessor` needs

**After (dependency injection):**
- Dependencies are declared in the constructor — you can see exactly what the class needs
- Testing: pass a `FakeNotificationService` that records messages instead of sending emails
- Flexibility: swap `ConsoleLogger` for `NullLogger` to silence logging, or `FileLogger` to log to a file
- Same behavior, completely different flexibility

To really feel the difference, create a `FakeNotificationService` that stores messages in a list instead of printing them. Imagine writing a test that checks: "after processing order ORD-001, the notification service received exactly one message containing 'Widget'." With DI, this is trivial. Without DI, it's impossible.

### What you learned

- Dependency Injection makes dependencies explicit, visible, and swappable
- The refactoring is simple: extract interfaces, move `new` calls to the constructor parameters, pass implementations from outside
- This is the foundation of testable, flexible software — and it's the same interface-first principle applied to object wiring: depend on abstractions, construct the concretes elsewhere

---

## Summary

You've now worked through every major design pattern. Here's the big picture:

| Category | Patterns | Core Idea |
|----------|----------|-----------|
| **Creational** | Factory Method, Abstract Factory, Builder, Singleton, Prototype | How objects are created — hide the `new` |
| **Structural** | Adapter, Decorator, Facade, Composite, Proxy, Bridge, Null Object | How objects are composed — flexible structure |
| **Behavioral** | Strategy, Command, Observer, Iterator, Template Method, State, Chain of Responsibility, Memento | How objects communicate — flexible behavior |
| **Architectural** | MVC, Repository, Dependency Injection | How systems are organized — flexible wiring |

Every pattern in this list shares the same underlying principle you've been learning all course: **depend on abstractions, not on concrete implementations**. When you define the interface first, write the client code against the interface, and only then implement — the patterns emerge naturally.

You don't need to memorize all of these. You need to recognize the *problems* they solve. When you find yourself writing a big `switch` statement to handle different types, think Strategy or State. When you need to add behavior to objects flexibly, think Decorator. When you need to decouple a sender from a receiver, think Observer or Command.

The patterns are tools. The interface-first mindset is what tells you when to reach for them.
