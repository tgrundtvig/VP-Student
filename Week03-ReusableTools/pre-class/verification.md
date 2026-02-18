# Verification Checklist

Use this to confirm you're ready for class.

## Reading Comprehension

- [ ] I can explain why `TextIn` is useful (swap implementations, test without keyboard)
- [ ] I can explain the problem with `IO.println` in `Player` (hardcoded, can't test, can't redirect)
- [ ] I understand that `TextOut` solves the output problem the same way `TextIn` solved the input problem
- [ ] I can see how combining input and output interfaces could enable reusable tools (menus, commands, etc.)

## Exercises

- [ ] I implemented `ConsoleTextOut.send()` using `System.out.println`
- [ ] I implemented `ListTextOut.send()` to store messages in the list
- [ ] All tests pass when I run `mvn test`

## Self-Test Questions

**Q1: Why is `ListTextOut` more useful for testing than `ConsoleTextOut`?**

<details>
<summary>Answer</summary>

`ListTextOut` stores every message in a list. After running some code, you can call `getMessages()` and check exactly what was sent. With `ConsoleTextOut`, the output goes to the screen and is gone — there's nothing to check programmatically.

This mirrors `ScriptedTextIn` vs `ScannerTextIn`: the "fake" implementation is the one that's useful for automated testing.
</details>

**Q2: What pattern do `TextIn`/`TextOut` have in common?**

<details>
<summary>Answer</summary>

Both define a simple interface (one method), then provide two implementations: one for real use (console/keyboard) and one for testing (scripted/list). The code that uses the interface doesn't know which implementation it has.
</details>

**Q3: If `Player` used a `TextOut` instead of `IO.println`, what would change?**

<details>
<summary>Answer</summary>

`Player` would receive a `TextOut` in its constructor (just like it receives a `TextIn`). Instead of `IO.println("Goodbye!")`, it would call `textOut.send("Goodbye!")`. This means you could test what the player prints by passing a `ListTextOut` and checking its messages.
</details>

## Ready for Class?

If you checked all the boxes above and could answer the self-test questions, you're ready. In class, we'll build on this foundation to create a complete set of reusable user interface tools.
