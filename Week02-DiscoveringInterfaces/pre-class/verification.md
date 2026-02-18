# Verification Checklist

Use this to confirm you're ready for class.

## Reading Comprehension

- [ ] I can describe the code we built in Week 01 (Room, Mace, MyMaceFactory, Player)
- [ ] I understand why the Week 01 Player is impossible to test automatically
- [ ] I can explain what an interface does (defines "what" without "how")
- [ ] I understand how the Receptionist receives its Greeting through the constructor (dependency injection)

## Exercises

- [ ] I implemented `FormalGreeting` and `CasualGreeting` to return the correct strings
- [ ] I implemented `Receptionist` to store a Greeting and delegate to it in `welcome()`
- [ ] Running `mvn test` shows all tests passing

## Self-Test Questions

**What does `implements Greeting` mean on a class declaration?**

<details><summary>Answer</summary>

It means the class promises to provide all the methods defined in the `Greeting` interface. In this case, it must have a `public String greet(String name)` method. The compiler will reject the class if it doesn't. This is how Java enforces the contract.

</details>

**Why does Receptionist store a `Greeting` (the interface) instead of a `FormalGreeting` (a specific class)?**

<details><summary>Answer</summary>

Because storing the interface type means the Receptionist works with *any* implementation — FormalGreeting, CasualGreeting, or one that doesn't even exist yet. If it stored `FormalGreeting`, you'd have to change the Receptionist class to use a different greeting style. By depending on the interface, the Receptionist is flexible and reusable. This is the same reason we want Player to depend on a "text input" interface instead of directly on Scanner.

</details>

**Could you test the Receptionist without running the program manually?**

<details><summary>Answer</summary>

Yes, easily. You create a Receptionist with a specific Greeting (e.g., `new FormalGreeting()`), call `welcome("Alice")`, and check that it returns `"Good day, Alice."`. No keyboard input, no console output, no human needed. Compare this with testing the Week 01 Player, which hangs waiting for Scanner input — that's the problem interfaces solve.

</details>

## Ready for Class?

If you completed the reading and all tests pass, you are ready. In class we will apply this exact pattern to the text adventure — extracting a `TextIn` interface, building two implementations, and refactoring Player to use it.
