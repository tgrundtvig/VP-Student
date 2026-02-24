# Exercise 2: ScriptedTextUser — Automated Testing Without a Framework

## Goal

Create a `TextUser` implementation that requires **no keyboard input at all**. Instead, it takes a list of pre-scripted responses and plays them back one by one when `get()` is called. It also captures all output from `put()` into a list.

This gives you a fully automated "fake user" that you can use to test any code built on `TextAppUser`.

## What You Will Learn

- **Test doubles**: Creating fake implementations to test code without manual interaction
- **How interfaces enable testability**: Because `TextAppUserImpl` depends on the `TextUser` *interface*, you can swap in any implementation — including a fake one
- **The real power of interface-first design**: Your production code and test code use the *same* interface, just different implementations

## Context

Right now, to test `TextAppUserDemo`, you have to run it and type responses manually. That is slow and unrepeatable. With a `ScriptedTextUser`, you can run the same scenario automatically every time.

## Instructions

### Step 1: Create the class

Create a new class `ScriptedTextUser` in the package `dk.ek.evu.vpf26.txtadv.user.impl`.

It must:
- Implement `TextUser`
- Take a `String[]` (the scripted responses) in its constructor
- Have an `int` field to track which response to return next
- Have a `List<String>` field called `output` to capture everything sent to `put()`

### Step 2: Implement `put(String text)`

Simply add the text to the `output` list. No printing to the console.

### Step 3: Implement `get()`

Return the next scripted response from the array and advance the index.

If the script runs out of responses, return a sensible default (e.g. `"quit"` or throw an `IllegalStateException` with a message like `"Script exhausted — no more responses"`).

### Step 4: Add methods to inspect results

Add these public methods:

```java
public List<String> getOutput()
```

Returns everything that was sent to `put()`.

```java
public boolean isExhausted()
```

Returns `true` if all scripted responses have been used.

### Step 5: Test the TextAppUserDemo scenario

Create a `main` method that automates the exact scenario from `TextAppUserDemo`:

```java
// These are the "answers" the fake user will give:
String[] script = {"Alice", "25", "1.75", "42", "2"};

ScriptedTextUser scripted = new ScriptedTextUser(script);
TextAppUser appUser = new TextAppUserImpl(scripted);

// Run the same code as TextAppUserDemo:
String name = appUser.readLine("Enter your name: ");
int age = appUser.readInt("Enter your age: ");
float height = appUser.readFloat("Enter your height: ", 0.2f, 3.0f);
int number = appUser.readInt("Enter a number between 1 and 100: ", 1, 100);
String[] colors = {"Blue", "Red", "Green", "Yellow"};
int choice = appUser.choose(colors, "What is your favorite color: ");
```

After running this, print the captured output:

```java
System.out.println("=== Captured output ===");
for (String line : scripted.getOutput()) {
    System.out.print(line);
}

System.out.println("\n=== Script exhausted: " + scripted.isExhausted() + " ===");
```

### Step 6: Test with invalid input

Create a second test scenario where the script includes invalid input to verify that `TextAppUserImpl`'s validation and retry logic works:

```java
// "abc" is not a valid integer — TextAppUserImpl should ask again
String[] scriptWithErrors = {"Bob", "abc", "30", "1.75", "50", "1"};

ScriptedTextUser scripted2 = new ScriptedTextUser(scriptWithErrors);
TextAppUser appUser2 = new TextAppUserImpl(scripted2);

// Run the same scenario — this time "abc" will fail and "30" will be used
String name = appUser2.readLine("Enter your name: ");
int age = appUser2.readInt("Enter your age: ");
// ... etc.
```

Check the captured output — you should see the error message `"You must enter an integer! Please try again."` appear in the output list.

## Expected Behavior

When you run Step 5, the program should complete instantly with no keyboard interaction. The captured output list should contain all the prompts and formatted results that `TextAppUserImpl` generated.

## Think About This

- You now have a way to test *any* code that uses `TextAppUser` — without touching the keyboard. This is the foundation of automated testing.
- The production code (`TextAppUserImpl`) has **no idea** it's talking to a fake. It calls `put()` and `get()` on the `TextUser` interface, and the interface contract is fulfilled.
- If you combine this with Exercise 1 (`LoggingTextUser`), you could wrap a `ScriptedTextUser` in a `LoggingTextUser` — scripted input with full logging. The decorators stack because they all speak the same interface.

## Bonus Challenge

If you finish early: Make the `ScriptedTextUser` also work as a verifier. Add a method `verifyOutputContains(String expected)` that checks if any entry in the output list contains the expected string. This is a tiny step toward writing real assertions in tests.
