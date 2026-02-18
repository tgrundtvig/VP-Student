# Exercise: Greeting and Receptionist

## Goal

Practice the interface and dependency injection pattern by implementing two greetings and a receptionist that works with either one. This is the same pattern we will use in class to make the Player testable — but in a simpler context first.

## What's Provided

- **`Greeting.java`** (complete interface — do not modify)
- **`FormalGreeting.java`** (skeleton with TODO)
- **`CasualGreeting.java`** (skeleton with TODO)
- **`Receptionist.java`** (skeleton with TODOs)
- **`GreetingTest.java`** (complete tests — do not modify)

## What You Need to Do

### 1. Implement `FormalGreeting`

This class implements the `Greeting` interface. The `greet` method should return a formal greeting:

- `greet("Alice")` returns `"Good day, Alice."`
- Pattern: `"Good day, " + name + "."`

### 2. Implement `CasualGreeting`

This class also implements the `Greeting` interface. The `greet` method should return a casual greeting:

- `greet("Alice")` returns `"Hey Alice!"`
- Pattern: `"Hey " + name + "!"`

### 3. Implement `Receptionist`

The Receptionist receives a `Greeting` through its constructor and uses it in the `welcome` method.

- The constructor takes a `Greeting` and stores it
- `welcome(String visitor)` calls `greet` on the stored greeting and returns the result

This is **dependency injection**: the Receptionist doesn't create its own Greeting — it receives one from the outside. In class, we will use this same pattern for the Player and its input source.

## Running the Tests

```bash
mvn test
```

Before you implement anything, all tests will **fail**. After implementing all three classes, all tests should **pass**.

## Think About It

- What would you need to do to add a third greeting style (e.g., `SilentGreeting` that returns `""`)? Would you need to change `Receptionist` at all?
- How is the relationship between `Greeting`/`FormalGreeting`/`CasualGreeting`/`Receptionist` similar to the relationship between a "text input" interface, a keyboard reader, a scripted reader, and the Player?
