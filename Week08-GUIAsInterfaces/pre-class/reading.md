# Week 8 Pre-Class Reading: GUI as Interfaces

## Introduction

In previous weeks, you learned that data structures (List, Map, Tree) and algorithms (Strategy,
Comparator) should be defined as interfaces. This week, we apply the same thinking to
**user interfaces**.

The key insight: **A View is not a window. A View is a contract.**

When your View is an interface, you can:
- Test your application logic without launching a GUI
- Swap between console, desktop, and web interfaces
- Keep business logic completely separated from UI code

## The MVC Pattern

MVC (Model-View-Controller) is an architectural pattern that separates an application into
three components:

### Model
The **Model** contains:
- Application data
- Business logic
- Rules and validation

The Model knows **nothing** about how data is displayed. It just manages data.

```java
public class GameModel {
    private int score;
    private Board board;

    public void makeMove(int x, int y) {
        // Update game state
        board.place(x, y);
        score += calculatePoints();
    }

    public int getScore() {
        return score;
    }

    public Board getBoard() {
        return board;
    }
}
```

### View
The **View** is responsible for:
- Displaying information to the user
- Capturing user input
- Visual presentation

The View knows **nothing** about business logic. It just shows things and gets input.

```java
public interface GameView {
    void displayMessage(String message);
    void displayBoard(Board board);
    void displayScore(int score);
    String getPlayerInput(String prompt);
    void showError(String error);
}
```

### Controller
The **Controller** coordinates:
- Receives user actions from the View
- Updates the Model based on actions
- Tells the View to refresh

The Controller is the "glue" between Model and View.

```java
public class GameController {
    private final GameModel model;
    private final GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void handleMove(int x, int y) {
        model.makeMove(x, y);
        view.displayBoard(model.getBoard());
        view.displayScore(model.getScore());
    }
}
```

## The Key Insight: View as Interface

The critical decision that makes MVC powerful is defining View as an **interface**.

### Without Interface (Bad)

```java
public class GameController {
    private final JavaFXGameView view;  // Concrete class!

    public GameController(JavaFXGameView view) {
        this.view = view;
    }
}
```

Problems:
- Cannot test without JavaFX runtime
- Cannot use console version
- Controller is tightly coupled to JavaFX

### With Interface (Good)

```java
public class GameController {
    private final GameView view;  // Interface!

    public GameController(GameView view) {
        this.view = view;
    }
}
```

Benefits:
- Test with mock view (no GUI needed)
- Use console view for quick testing
- Use JavaFX view for production
- Use web view if needed later

## Multiple View Implementations

With View as an interface, you can have multiple implementations:

### Console View

```java
public class ConsoleGameView implements GameView {
    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream out = System.out;

    @Override
    public void displayMessage(String message) {
        out.println(message);
    }

    @Override
    public void displayBoard(Board board) {
        out.println(board.toAsciiArt());
    }

    @Override
    public String getPlayerInput(String prompt) {
        out.print(prompt + ": ");
        return scanner.nextLine();
    }
}
```

### JavaFX View

```java
public class JavaFXGameView implements GameView {
    private final Label messageLabel;
    private final GridPane boardPane;
    private final TextField inputField;

    @Override
    public void displayMessage(String message) {
        Platform.runLater(() -> messageLabel.setText(message));
    }

    @Override
    public void displayBoard(Board board) {
        Platform.runLater(() -> {
            boardPane.getChildren().clear();
            // ... create JavaFX nodes for board
        });
    }

    // Note: JavaFX input is typically event-based, not blocking
    // We'll discuss this pattern in class
}
```

### Mock View for Testing

```java
public class MockGameView implements GameView {
    private final List<String> messages = new ArrayList<>();
    private final Queue<String> preparedInputs = new LinkedList<>();

    @Override
    public void displayMessage(String message) {
        messages.add(message);  // Just record, don't display
    }

    @Override
    public String getPlayerInput(String prompt) {
        return preparedInputs.poll();  // Return pre-set answer
    }

    // Test helpers
    public void prepareInput(String input) {
        preparedInputs.add(input);
    }

    public boolean wasMessageDisplayed(String message) {
        return messages.contains(message);
    }
}
```

## Testing with Mock Views

Mock views make testing easy:

```java
@Test
void gameDisplaysWelcomeMessage() {
    MockGameView mockView = new MockGameView();
    GameModel model = new GameModel();
    GameController controller = new GameController(model, mockView);

    controller.startGame();

    assertTrue(mockView.wasMessageDisplayed("Welcome to the game!"));
}

@Test
void gameAcceptsPlayerMove() {
    MockGameView mockView = new MockGameView();
    mockView.prepareInput("3");  // Player will enter "3"
    mockView.prepareInput("5");  // Then "5"

    GameModel model = new GameModel();
    GameController controller = new GameController(model, mockView);

    controller.handlePlayerTurn();

    assertEquals(3, model.getLastMoveX());
    assertEquals(5, model.getLastMoveY());
}
```

No JavaFX runtime needed! Tests run fast and reliably.

## JavaFX Basics

JavaFX is Java's modern GUI toolkit. Key concepts:

### Stage and Scene

```java
public class MyApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Stage = the window
        // Scene = the content

        VBox root = new VBox();
        root.getChildren().add(new Label("Hello!"));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My App");
        primaryStage.show();
    }
}
```

### Common Controls

```java
Label label = new Label("Text to display");
Button button = new Button("Click me");
TextField textField = new TextField();
TextArea textArea = new TextArea();
ListView<String> listView = new ListView<>();
```

### Layout Containers

```java
VBox vbox = new VBox();      // Vertical stack
HBox hbox = new HBox();      // Horizontal stack
GridPane grid = new GridPane();  // Grid layout
BorderPane border = new BorderPane();  // Top/bottom/left/right/center
```

### Event Handling

```java
button.setOnAction(event -> {
    // Handle button click
    controller.handleButtonClick();
});

textField.setOnAction(event -> {
    // Handle Enter key
    String input = textField.getText();
    controller.handleInput(input);
});
```

## The Event-Driven Challenge

Console applications are **synchronous**:
```java
String input = scanner.nextLine();  // Blocks until input
```

GUI applications are **event-driven**:
```java
button.setOnAction(event -> {
    // Called when button clicked - non-blocking
});
```

This requires a different design approach. Instead of:
```java
public interface GameView {
    String getPlayerInput(String prompt);  // Blocking - problematic for GUI
}
```

Consider:
```java
public interface GameView {
    void promptForInput(String prompt);  // Non-blocking
    void setInputHandler(Consumer<String> handler);  // Callback when input ready
}
```

We'll discuss this pattern in detail during class.

## Design Principles

### 1. View Knows Nothing About Business Logic

```java
// BAD - View making business decisions
public class BadView {
    public void displayScore(int score) {
        if (score > 100) {
            showCelebration();  // Business logic in view!
        }
    }
}

// GOOD - View just displays what it's told
public class GoodView {
    public void displayScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void showCelebration() {
        // Only called by controller when appropriate
    }
}
```

### 2. Controller Doesn't Know View Implementation

```java
// BAD - Controller using JavaFX types
public class BadController {
    public void update(Button button) {  // JavaFX in controller!
        button.setDisable(true);
    }
}

// GOOD - Controller uses interface
public class GoodController {
    private final GameView view;

    public void endTurn() {
        view.disableInput();  // Abstract method
    }
}
```

### 3. Model is Completely Independent

```java
// BAD - Model importing JavaFX
import javafx.scene.paint.Color;  // NO!

// GOOD - Model uses own types
public enum PlayerColor { RED, BLUE }  // Domain type
```

## Common Mistakes

### 1. Putting Business Logic in View

```java
// WRONG
public class GameView {
    public void onMoveClicked(int x, int y) {
        if (isValidMove(x, y)) {  // Business logic!
            board.place(x, y);
            score += 10;
        }
    }
}

// RIGHT
public class GameView {
    public void onMoveClicked(int x, int y) {
        controller.handleMove(x, y);  // Delegate to controller
    }
}
```

### 2. Controller Depending on Concrete View

```java
// WRONG
public class GameController {
    private final JavaFXGameView view;  // Concrete!
}

// RIGHT
public class GameController {
    private final GameView view;  // Interface!
}
```

### 3. Not Using Dependency Injection

```java
// WRONG
public class GameController {
    private final GameView view = new JavaFXGameView();  // Created internally!
}

// RIGHT
public class GameController {
    private final GameView view;

    public GameController(GameView view) {  // Injected!
        this.view = view;
    }
}
```

## Exercise Preview

In the pre-class exercises, you'll:
1. Study a `GameView` interface
2. Implement a `ConsoleGameView`
3. See how `GameController` uses the view interface
4. Write a simple test with a mock view

## Key Takeaways

1. **MVC separates concerns** - Model (data), View (display), Controller (coordination)
2. **View should be an interface** - Enables testing, flexibility, separation
3. **Mock views enable testing** - No GUI needed for unit tests
4. **Multiple implementations** - Console, JavaFX, web from same interface
5. **Dependency injection** - Controller receives view, doesn't create it

## Before Class

Make sure you can:
- Explain what Model, View, and Controller each do
- Describe why View should be an interface
- Write a simple interface for displaying messages
- Explain how a mock view enables testing
