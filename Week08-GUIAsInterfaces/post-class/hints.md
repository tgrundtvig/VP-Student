# Week 8 Post-Class Hints

Use these hints if you get stuck. Try to solve problems yourself first!

## ConsoleGameView Implementation (Pre-class)

### displayMessage
```java
@Override
public void displayMessage(String message) {
    out.println(message);
}
```

### displayHint
```java
@Override
public void displayHint(String hint) {
    out.println(">>> " + hint);
}
```

### displayGameOver
```java
@Override
public void displayGameOver(boolean won, int secret, int attempts) {
    if (won) {
        out.println("Congratulations! You won in " + attempts + " attempts!");
    } else {
        out.println("Game over! The number was " + secret);
    }
}
```

### promptForGuess
```java
@Override
public String promptForGuess(String prompt) {
    out.print(prompt + ": ");
    return scanner.nextLine();
}
```

## JavaFXSearchView Implementation (Post-class)

### Event Handlers
```java
private void setupEventHandlers() {
    searchButton.setOnAction(event -> {
        if (searchHandler != null) {
            String query = searchField.getText();
            searchHandler.onSearch(query);
        }
    });

    searchField.setOnAction(event -> {
        if (searchHandler != null) {
            String query = searchField.getText();
            searchHandler.onSearch(query);
        }
    });
}
```

### displayResults
```java
@Override
public <T> void displayResults(List<T> results) {
    Platform.runLater(() -> {
        resultsList.getItems().clear();
        for (T result : results) {
            resultsList.getItems().add(result.toString());
        }
    });
}
```

### displayNoResults
```java
@Override
public void displayNoResults() {
    Platform.runLater(() -> {
        resultsList.getItems().clear();
        resultsList.getItems().add("No results found");
    });
}
```

### displayResultCount
```java
@Override
public void displayResultCount(int count) {
    Platform.runLater(() -> {
        statusLabel.setText("Found " + count + " item(s)");
    });
}
```

### displayMessage
```java
@Override
public void displayMessage(String message) {
    Platform.runLater(() -> {
        statusLabel.setText(message);
    });
}
```

### displayError
```java
@Override
public void displayError(String error) {
    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    });
}
```

### showLoading
```java
@Override
public void showLoading(boolean loading) {
    Platform.runLater(() -> {
        loadingIndicator.setVisible(loading);
        searchButton.setDisable(loading);
        searchField.setDisable(loading);
    });
}
```

## Why Platform.runLater()?

JavaFX requires UI updates to happen on the "JavaFX Application Thread".
If the controller calls view methods from a different thread, you must wrap
the UI updates in `Platform.runLater()`.

```java
// This might crash if called from wrong thread:
statusLabel.setText("Hello");

// This is always safe:
Platform.runLater(() -> statusLabel.setText("Hello"));
```

For our simple application it's not strictly necessary, but it's good practice.

## Common Mistakes

### 1. Forgetting to check if handler is null
```java
// WRONG - crashes if handler not set
searchButton.setOnAction(e -> searchHandler.onSearch(query));

// RIGHT - check first
searchButton.setOnAction(e -> {
    if (searchHandler != null) {
        searchHandler.onSearch(searchField.getText());
    }
});
```

### 2. Not clearing previous results
```java
// WRONG - results accumulate
@Override
public <T> void displayResults(List<T> results) {
    for (T result : results) {
        resultsList.getItems().add(result.toString());
    }
}

// RIGHT - clear first
@Override
public <T> void displayResults(List<T> results) {
    resultsList.getItems().clear();  // Clear old results!
    for (T result : results) {
        resultsList.getItems().add(result.toString());
    }
}
```

### 3. Putting business logic in View
```java
// WRONG - View deciding what to show
if (results.isEmpty()) {
    showNoResultsMessage();  // Business decision!
}

// RIGHT - Controller decides, View just displays
// In Controller:
if (results.isEmpty()) {
    view.displayNoResults();
} else {
    view.displayResults(results);
}
```

## Running the Applications

### Console Version
```bash
mvn compile exec:java -Dexec.mainClass="dk.viprogram.week08.ConsoleSearchApp"
```

### JavaFX Version (Terminal)
```bash
mvn javafx:run
```

### JavaFX Version (IntelliJ)

If you want to run from IntelliJ instead of the terminal:

1. Run the `Launcher` class (not `SearchApp`)
2. To suppress warnings, add these VM options to your run configuration:
   - Go to Run → Edit Configurations
   - Select your Launcher configuration
   - In "VM options" add:
     ```
     --add-opens=javafx.graphics/com.sun.javafx.application=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --enable-native-access=ALL-UNNAMED
     ```

Note: The warnings about "Unsupported JavaFX configuration" are harmless - the app works fine.

---

# Memory Card Game Exercise

The Memory Card Game is a more complex example demonstrating the MVC pattern.
You need to implement 4 TODO methods spread across Model, View (Console and JavaFX), and Controller.

## Running the Memory Game

### Console Version
```bash
mvn compile exec:java -Dexec.mainClass="dk.viprogram.week08.memory.ConsoleMemoryApp"
```

### JavaFX Version
```bash
mvn javafx:run -Dexec.mainClass="dk.viprogram.week08.memory.MemoryApp"
```

Or from IntelliJ: Run the `MemoryLauncher` class.

## SimpleMemoryGameModel.checkMatch()

This method checks if the two flipped cards match:

```java
@Override
public MatchResult checkMatch() {
    // Need exactly 2 cards to check
    if (flippedCards.size() < 2) {
        return MatchResult.NEED_MORE_CARDS;
    }

    Position pos1 = flippedCards.get(0);
    Position pos2 = flippedCards.get(1);
    Card card1 = getCard(pos1);
    Card card2 = getCard(pos2);

    // Increment move counter
    moves++;

    // Check if symbols match
    if (card1.symbol().equals(card2.symbol())) {
        // Match! Mark both cards as matched
        setCard(pos1, card1.markMatched());
        setCard(pos2, card2.markMatched());
        matchesFound++;
        flippedCards.clear();
        return MatchResult.MATCH;
    } else {
        // No match - flip cards back down
        setCard(pos1, card1.flipDown());
        setCard(pos2, card2.flipDown());
        flippedCards.clear();
        return MatchResult.NO_MATCH;
    }
}
```

## ConsoleMemoryGameView.displayGrid()

This method prints the game grid to the console:

```java
@Override
public void displayGrid(List<List<Card>> grid) {
    int rows = grid.size();
    int cols = grid.get(0).size();

    out.println();

    // Print column headers
    out.print("    ");
    for (int c = 0; c < cols; c++) {
        out.printf("%2d ", c);
    }
    out.println();

    // Print separator line
    out.print("  +");
    for (int c = 0; c < cols; c++) {
        out.print("--+");
    }
    out.println();

    // Print each row
    for (int r = 0; r < rows; r++) {
        out.printf("%d |", r);
        for (int c = 0; c < cols; c++) {
            Card card = grid.get(r).get(c);
            out.print(card.display() + "|");
        }
        out.println();

        out.print("  +");
        for (int c = 0; c < cols; c++) {
            out.print("--+");
        }
        out.println();
    }
}
```

## JavaFXMemoryGameView.createCardButton()

This method creates a button for each card in the grid:

```java
private Button createCardButton(Card card, int row, int col) {
    Button button = new Button(card.display());

    // Set style based on card state
    if (card.matched()) {
        button.setStyle(CARD_STYLE_MATCHED);
    } else if (card.faceUp()) {
        button.setStyle(CARD_STYLE_FACE_UP);
    } else {
        button.setStyle(CARD_STYLE_FACE_DOWN);
    }

    // Set click handler
    button.setOnAction(event -> {
        if (cardClickHandler != null) {
            cardClickHandler.onCardClick(new Position(row, col));
        }
    });

    // Disable if matched or face up
    button.setDisable(card.matched() || card.faceUp());

    return button;
}
```

## MemoryGameController.handleCardClick() (two-card logic)

The part after two cards are flipped:

```java
if (model.getFlippedCards().size() == 2) {
    waitingForFlipBack = true;

    view.scheduleAction(CARD_SHOW_DELAY_MS, () -> {
        MemoryGameModel.MatchResult result = model.checkMatch();
        waitingForFlipBack = false;

        refreshView();

        switch (result) {
            case MATCH:
                view.displayMessage("Match found! 🎉");
                break;
            case NO_MATCH:
                view.displayMessage("No match. Try again!");
                break;
            default:
                break;
        }

        if (model.isGameOver()) {
            statsTimer.cancel();
            handleGameOver();
        }
    });
}
```

## Memory Game Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    MemoryGameController                     │
│  - handleCardClick(pos)  - handleNewGame(rows, cols)        │
│  - refreshView()         - handleGameOver()                 │
└─────────────────────────────────────────────────────────────┘
           │                                │
           │ Uses                          │ Uses
           ▼                               ▼
┌─────────────────────┐         ┌─────────────────────────────┐
│  MemoryGameModel    │         │    MemoryGameView           │
│  (Interface)        │         │    (Interface)              │
├─────────────────────┤         ├─────────────────────────────┤
│ - newGame()         │         │ - displayGrid()             │
│ - flipCard()        │         │ - displayStats()            │
│ - checkMatch()      │         │ - displayMessage()          │
│ - getGrid()         │         │ - displayGameOver()         │
│ - isGameOver()      │         │ - setCardClickHandler()     │
└─────────────────────┘         └─────────────────────────────┘
           △                               △
           │                     ┌─────────┴─────────┐
           │                     │                   │
┌─────────────────────┐  ┌───────────────┐  ┌───────────────┐
│SimpleMemoryGameModel│  │ConsoleMemory  │  │JavaFXMemory   │
│                     │  │GameView       │  │GameView       │
└─────────────────────┘  └───────────────┘  └───────────────┘
```

## Still Stuck?

- Review the ConsoleSearchView as a working example
- Make sure JavaFX is properly installed
- Check the test output for specific failures
- Come to class with specific questions
