# Exercise 2: Spot the Dependency

## Goal

Practice recognizing hardcoded dependencies in code snippets. For each one, identify what's locked in and sketch what interface could fix it.

**Estimated time: 25 minutes.**

This is a design exercise — no coding required. Pen and paper (or a text file) is all you need.

---

## Background

The refactoring we did in class — replacing `Scanner` with `TextIn` — follows a pattern:

1. Find the hardcoded dependency (the concrete class created inside the method)
2. Define an interface for what you actually need
3. Accept the interface as a parameter instead

This exercise trains you to see step 1 in unfamiliar code.

---

## Snippets

### Snippet A: Weather Reporter

```java
public class WeatherReporter {
    public String getReport() {
        HttpClient client = new HttpClient();
        String data = client.get("https://api.weather.com/today");
        return "Today's weather: " + data;
    }
}
```

**Questions:**
1. What is the hardcoded dependency?
2. Why is this class hard to test?
3. What interface would you define to fix it? (Just the interface — name and method signatures.)

### Snippet B: Order Processor

```java
public class OrderProcessor {
    public void process(Order order) {
        EmailService email = new EmailService();
        email.send(order.getCustomerEmail(), "Your order has been placed!");
        System.out.println("Order processed: " + order.getId());
    }
}
```

**Questions:**
1. There are **two** hardcoded dependencies here. What are they?
2. If you wanted to test that `process` sends the right email to the right address, what's stopping you?
3. Sketch interfaces for both dependencies.

### Snippet C: ScoreCalculator

```java
public class ScoreCalculator {
    public int calculateFinal(int studentId) {
        Database db = new Database("jdbc:mysql://localhost/grades");
        List<Integer> scores = db.query("SELECT score FROM exams WHERE student = " + studentId);
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total / scores.size();
    }
}
```

**Questions:**
1. What is the hardcoded dependency?
2. Can you run this test without a running MySQL database? Why or why not?
3. What interface would let you test the calculation logic independently?

### Snippet D: Game (Tricky)

```java
public class Game {
    private Player player;
    private TextIn textIn;

    public Game(Player player, TextIn textIn) {
        this.player = player;
        this.textIn = textIn;
    }

    public void run() {
        while (true) {
            String command = textIn.nextLine("Enter command: ");
            if ("quit".equalsIgnoreCase(command)) break;
            player.handleCommand(command);
        }
    }
}
```

**Questions:**
1. Does this class have any hardcoded dependencies?
2. Is this class easy or hard to test? Why?
3. What makes this different from Snippets A-C?

---

## What to Look For

When spotting hardcoded dependencies, watch for:

- **`new` inside a method** (not in a factory) — this usually means a dependency is being created instead of injected
- **Static method calls** like `System.out.println()` — these can't be swapped
- **Concrete class types** in fields or local variables where an interface would work

The fix is always the same pattern:
1. Extract an interface
2. Accept it through the constructor (or method parameter)
3. Let the caller decide which implementation to use

---

## Self-Check

- For Snippets A-C, you identified the hardcoded dependency (`new Something()` inside the method)
- For each, you can name a reasonable interface (e.g., `WeatherSource`, `NotificationSender`, `ScoreRepository`)
- For Snippet D, you recognized that it already follows the pattern — dependencies are injected
