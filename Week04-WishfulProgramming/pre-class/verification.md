# Verification Checklist

Use this to confirm you're ready for class.

## Reading Comprehension

- [ ] I can explain what "wishful programming" means (writing code against interfaces that don't exist yet)
- [ ] I understand why top-down design discovers better interfaces than bottom-up design
- [ ] I can see how `TextIn`, `TextOut`, and `TextAppUser` were all designed top-down (shaped by what the caller needed)
- [ ] I understand why the if-else chain in `Player.moveInMace()` is painful to extend

## Exercises

- [ ] I implemented `EmailSender.send()` with the correct format
- [ ] I implemented `ListSender.send()` to store notifications in the list
- [ ] All 11 tests pass when I run `mvn test`

## Self-Test Questions

**Q1: In the notification exercise, which class was written first — `NotificationService` or `EmailSender`?**

<details>
<summary>Answer</summary>

`NotificationService` was written first. It was designed using wishful programming — it calls `sender.send()` on a `NotificationSender` interface, without knowing (or caring) what implementation will be used. `EmailSender` and `ListSender` were implemented later to satisfy the contract.
</details>

**Q2: Why do the `NotificationService` tests use `ListSender` instead of `EmailSender`?**

<details>
<summary>Answer</summary>

`ListSender` stores all sent notifications in a list, so you can inspect them after the test runs. `EmailSender` prints to the console — the output is gone and hard to verify. This is the same pattern as `ScriptedTextIn` vs `ScannerTextIn`, and `ListTextOut` vs `ConsoleTextOut`: the "fake" implementation is the one that's useful for automated testing.
</details>

**Q3: What would it take to add SMS notification support to the system?**

<details>
<summary>Answer</summary>

Create a new class `SmsSender implements NotificationSender` and implement the `send` method. You don't need to change `NotificationService`, `Notification`, or any existing sender. The system is designed to be extended without modification.
</details>

## Ready for Class?

If you checked all the boxes above and could answer the self-test questions, you're ready. In class, we'll apply this same top-down thinking to refactor `Player.moveInMace()` using the Command pattern.
