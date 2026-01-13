# Week 8: GUI as Interfaces

## Overview
This week we apply the interface-first methodology to graphical user interfaces. You'll learn
that the **View** in MVC (Model-View-Controller) should be an interface, enabling testable,
flexible UI architectures. The same principles that made our data structures and algorithms
flexible now make our user interfaces flexible.

## Learning Objectives
By the end of this week, you will:
- Understand the MVC pattern through an interface lens
- Design View interfaces that abstract away UI details
- Implement both console and JavaFX views for the same application
- Write tests for UI logic without needing actual GUI components
- Apply dependency injection to swap UI implementations

## The Big Idea

> "A View is not a JavaFX window. A View is a **contract** for displaying information and
> capturing user input."

When you define your View as an interface:
- You can test your Controller without launching a GUI
- You can have multiple implementations (console, desktop, web)
- Your business logic stays completely separated from UI code

## Pre-Class Preparation

### Reading
Complete `pre-class/reading.md` which covers:
- The MVC pattern explained
- Why View should be an interface
- Introduction to JavaFX concepts
- Testing strategies for GUI applications

### Exercises
The pre-class exercises ask you to:
1. Study a `GameView` interface
2. Implement a `ConsoleGameView`
3. Understand how Controller uses View without knowing the implementation

**Time estimate:** 60-90 minutes

## This Week's Focus: Testable User Interfaces

We'll build a simple game with swappable views:

```java
// The "wish" - how we WANT to use views:
public interface GameView {
    void displayMessage(String message);
    void displayBoard(Board board);
    void displayScore(int score);
    String getPlayerInput(String prompt);
    void showGameOver(String result);
}

// Controller doesn't know or care what kind of view it has
public class GameController {
    private final GameView view;
    private final GameModel model;

    public GameController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }

    public void startGame() {
        view.displayMessage("Welcome to the game!");
        view.displayBoard(model.getBoard());
        // ... game logic using view interface
    }
}

// Console implementation
GameView consoleView = new ConsoleGameView();
GameController controller = new GameController(consoleView, model);

// JavaFX implementation - SAME controller!
GameView fxView = new JavaFXGameView(stage);
GameController controller = new GameController(fxView, model);

// Test implementation - no GUI needed!
GameView testView = new MockGameView();
GameController controller = new GameController(testView, model);
```

## Key Concepts

### MVC Pattern

```
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│    ┌─────────┐      ┌──────────────┐      ┌─────────┐      │
│    │  Model  │◄─────│  Controller  │─────►│  View   │      │
│    │ (Data)  │      │   (Logic)    │      │  (UI)   │      │
│    └─────────┘      └──────────────┘      └─────────┘      │
│         │                  ▲                   │            │
│         │                  │                   │            │
│         └──────────────────┴───────────────────┘            │
│                     User Events                             │
└─────────────────────────────────────────────────────────────┘
```

- **Model**: Data and business logic (no UI knowledge)
- **View**: Displays data and captures input (interface!)
- **Controller**: Coordinates Model and View

### View as Interface

The key insight is making View an **interface**:

```java
// View CONTRACT - what can the UI do?
public interface GameView {
    void displayMessage(String message);
    void displayBoard(Board board);
    String getPlayerInput(String prompt);
}

// Console IMPLEMENTATION
public class ConsoleGameView implements GameView {
    @Override
    public void displayBoard(Board board) {
        System.out.println(board.toAsciiArt());
    }
}

// JavaFX IMPLEMENTATION
public class JavaFXGameView implements GameView {
    @Override
    public void displayBoard(Board board) {
        boardPane.getChildren().clear();
        // ... create JavaFX nodes
    }
}
```

### Testing Without GUI

With View as an interface, testing becomes trivial:

```java
public class MockGameView implements GameView {
    private final List<String> displayedMessages = new ArrayList<>();
    private final Queue<String> preparedInputs = new LinkedList<>();

    @Override
    public void displayMessage(String message) {
        displayedMessages.add(message);  // Just record it
    }

    @Override
    public String getPlayerInput(String prompt) {
        return preparedInputs.poll();  // Return prepared answer
    }

    // Test helpers
    public List<String> getDisplayedMessages() {
        return displayedMessages;
    }

    public void prepareInput(String input) {
        preparedInputs.add(input);
    }
}

@Test
void controllerDisplaysWelcomeMessage() {
    MockGameView mockView = new MockGameView();
    GameController controller = new GameController(mockView, model);

    controller.startGame();

    assertTrue(mockView.getDisplayedMessages().contains("Welcome!"));
}
```

## Post-Class Work

### Exercises
After class, complete the GUI exercises:
- Implement a `JavaFXGameView` for the game
- Connect JavaFX events to Controller methods
- Write tests using a mock view

### Homework: Add GUI to Existing Project
Take the search engine from Week 7 and add a GUI:
- Design a `SearchView` interface
- Implement both `ConsoleSearchView` and `JavaFXSearchView`
- The `SearchController` should work with either view

## Connection to Previous Weeks

| Week | Focus | Connection |
|------|-------|------------|
| Week 3 | Interfaces as contracts | View is another contract |
| Week 4 | Wishful programming | Design how the view SHOULD work |
| Week 7 | Strategies | Event handlers are strategies |
| **Week 8** | **GUI as Interfaces** | **Apply interfaces to user interfaces** |

## Looking Ahead
- Week 9: Persistence - Repository pattern (interfaces for storage)
- Week 10: Example projects with complete MVC + Repository architecture
- Weeks 11-14: Design your exam project using all these patterns
- The 2-week gap: Implement your complete designed system

## Verification Checklist
Before class, ensure you can:
- [ ] Explain the three components of MVC
- [ ] Describe why View should be an interface
- [ ] Implement a simple console view from an interface
- [ ] Explain how mock views enable testing

## Getting Help
- Review Week 7 materials on strategies (event handlers are similar)
- Check the pre-class reading for MVC diagrams
- Come to class with specific questions
