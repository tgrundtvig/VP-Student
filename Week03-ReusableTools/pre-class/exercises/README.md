# Exercise: TextOut Warm-Up

## Goal

Implement a `TextOut` interface with two implementations, mirroring the `TextIn` pattern you already know from the text adventure.

## What's Provided

- **`TextOut.java`** — the interface (complete, don't modify)
- **`ConsoleTextOut.java`** — skeleton with a TODO
- **`ListTextOut.java`** — skeleton with a TODO
- **`TextOutTest.java`** — tests (complete, don't modify)

## What You Need to Do

### 1. Implement `ConsoleTextOut.send()`

Open `ConsoleTextOut.java` and fill in the `send` method. It should print the message to the console using `System.out.println`.

This is the "real" implementation — like `ScannerTextIn` is the "real" `TextIn`.

### 2. Implement `ListTextOut.send()`

Open `ListTextOut.java` and fill in the `send` method. It should add the message to the `messages` list.

This is the "test" implementation — like `ScriptedTextIn` is the "test" `TextIn`. It records everything that was sent so you can inspect it later.

## Running the Tests

```bash
mvn test
```

Before you implement anything, the tests will **fail** (but the code will **compile**). After implementing both TODOs, all tests should pass.

## Think About It

After completing the exercise, notice:
- `TextOutTest` mostly tests `ListTextOut`. Why is `ListTextOut` easier to test than `ConsoleTextOut`?
- The test `bothImplementations_areTextOut` creates both as `TextOut` variables. What does this demonstrate?
