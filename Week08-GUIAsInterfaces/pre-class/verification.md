# Week 8 Pre-Class Verification

Complete these questions to verify your understanding before class.

## Part 1: MVC Basics

### Question 1: Component Responsibilities
Match each responsibility to the correct MVC component:

| Responsibility | Model | View | Controller |
|----------------|-------|------|------------|
| Store the current score | | | |
| Display a "Game Over" message | | | |
| Decide when the game ends | | | |
| Show a text input field | | | |
| Validate that a move is legal | | | |
| Receive a button click event | | | |
| Calculate the winner | | | |

### Question 2: Why Interfaces?
Explain in your own words why View should be an interface rather than a concrete class.

_________________________________________________________________

_________________________________________________________________

### Question 3: Dependency Direction
In MVC, which statements are true?

- [ ] Model knows about View
- [ ] Model knows about Controller
- [ ] View knows about Model
- [ ] View knows about Controller
- [ ] Controller knows about Model
- [ ] Controller knows about View

## Part 2: View Interface Design

### Question 4: Design a View Interface
You're building a simple counter application. Design a `CounterView` interface with methods for:
- Displaying the current count
- Showing a message
- Getting notified when the user wants to increment
- Getting notified when the user wants to decrement

```java
public interface CounterView {
    // Your methods here



}
```

### Question 5: Multiple Implementations
For your `CounterView` interface above, describe how these implementations would differ:

**ConsoleCounterView:**

_________________________________________________________________

**JavaFXCounterView:**

_________________________________________________________________

## Part 3: Testing with Mocks

### Question 6: Mock View
Given this interface:

```java
public interface MessageView {
    void showMessage(String message);
    void showError(String error);
}
```

Complete this mock implementation:

```java
public class MockMessageView implements MessageView {
    // Add fields to track what was displayed



    @Override
    public void showMessage(String message) {
        // Record the message

    }

    @Override
    public void showError(String error) {
        // Record the error

    }

    // Add helper methods for tests


}
```

### Question 7: Writing a Test
Using your `MockMessageView`, write a test that verifies a controller shows "Welcome!" when started:

```java
@Test
void controllerShowsWelcomeMessage() {
    // Your test code here



}
```

## Part 4: JavaFX Basics

### Question 8: JavaFX Concepts
Match each JavaFX term with its description:

| Term | Description |
|------|-------------|
| Stage | |
| Scene | |
| VBox | |
| Label | |
| Button | |
| setOnAction | |

Options: Window, Content container, Vertical layout, Display text, Clickable control, Event handler

### Question 9: Event-Driven vs Synchronous
What's the key difference between console input and GUI input?

**Console:**

_________________________________________________________________

**GUI:**

_________________________________________________________________

## Verification Checklist

Before class, confirm:
- [ ] I can name the three MVC components and their responsibilities
- [ ] I understand why View should be an interface
- [ ] I can design a simple View interface
- [ ] I understand how mock views enable testing
- [ ] I ran all pre-class exercise tests and they pass
- [ ] I have questions ready for class (if any concepts are unclear)

## Notes for Class

Write down any questions or concepts you'd like to discuss:

1. _________________________________

2. _________________________________

3. _________________________________
