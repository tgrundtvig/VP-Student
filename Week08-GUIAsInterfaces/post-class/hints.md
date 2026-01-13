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

### JavaFX Version
```bash
mvn javafx:run
```

## Still Stuck?

- Review the ConsoleSearchView as a working example
- Make sure JavaFX is properly installed
- Check the test output for specific failures
- Come to class with specific questions
