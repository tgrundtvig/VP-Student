# Exercise: Notification System

## Goal

Experience the "implement last" step of wishful programming. The high-level code (`NotificationService`) was written first, using an interface (`NotificationSender`) and a record (`Notification`) that define the contract. Your job is to implement the leaf nodes that make it all work.

## What's Provided

- **`NotificationSender.java`** — the interface (complete, don't modify)
- **`Notification.java`** — the record (complete, don't modify)
- **`NotificationService.java`** — the service, written wishfully (complete, don't modify)
- **`EmailSender.java`** — skeleton with a TODO
- **`ListSender.java`** — skeleton with a TODO
- **`NotificationTest.java`** — tests (complete, don't modify)

## What You Need to Do

### 1. Implement `EmailSender.send()`

Open `EmailSender.java` and fill in the `send` method. It should print a formatted message to the console:

```
[EMAIL] To: alice@example.com | Subject: Hello | Message: Welcome!
```

Use `System.out.println` with string concatenation. The format is:
```
[EMAIL] To: {recipient} | Subject: {subject} | Message: {message}
```

### 2. Implement `ListSender.send()`

Open `ListSender.java` and fill in the `send` method. It should add the notification to the `notifications` list.

This is the "test" implementation — like `ListTextOut` from Week 03.

## Running the Tests

```bash
mvn test
```

Before you implement anything, the tests will **fail** (but the code will **compile**). After implementing both TODOs, all 11 tests should pass.

## Think About It

After completing the exercise, notice:

- `NotificationService` was written before `EmailSender` or `ListSender` existed. The service doesn't know how notifications are delivered — it just calls `sender.send()`. This is wishful programming.
- The tests for `NotificationService` use `ListSender`. Why is that useful? (Hint: same reason `ListTextOut` was useful for testing in Week 03.)
- You implemented the *leaf nodes* — the simplest, most concrete parts of the system. The design and workflow were already done. This is what "implement last" feels like.
