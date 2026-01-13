# Week 8 Extensions

These optional challenges extend your understanding of GUI architecture.
Only attempt these after completing all regular exercises.

## Extension 1: Enhanced Search View

Add more features to the SearchView interface:

```java
public interface EnhancedSearchView extends SearchView {
    /**
     * Displays product details when selected.
     */
    void displayProductDetails(Product product);

    /**
     * Sets the handler for when a product is selected.
     */
    void setProductSelectionHandler(Consumer<Product> handler);

    /**
     * Shows filter options (categories, price range).
     */
    void displayFilterOptions(List<String> categories);

    /**
     * Sets the handler for filter changes.
     */
    void setFilterHandler(FilterHandler handler);

    interface FilterHandler {
        void onFilterChanged(String category, int minPrice, int maxPrice);
    }
}
```

Implement this interface in both ConsoleSearchView and JavaFXSearchView.

## Extension 2: Async Operations with Progress

Create a view that shows progress for long operations:

```java
public interface ProgressView {
    void showProgress(double percent, String message);
    void showIndeterminateProgress(String message);
    void hideProgress();
}
```

Simulate a slow search and show progress.

## Extension 3: Multiple Views Simultaneously

Create a system where multiple views show the same model:

```java
// One model, multiple views
SearchModel model = new SearchModel();
ConsoleSearchView consoleView = new ConsoleSearchView();
JavaFXSearchView guiView = new JavaFXSearchView();

// Both views should update when model changes
// Hint: Observer pattern!
```

Research and implement the Observer pattern to keep views synchronized.

## Extension 4: View Themes/Styles

Create a `ViewStyle` interface that can be applied to views:

```java
public interface ViewStyle {
    String getBackgroundColor();
    String getTextColor();
    String getFontFamily();
    int getFontSize();
}

public interface StyleableView {
    void applyStyle(ViewStyle style);
}
```

Create "Light" and "Dark" styles for your JavaFX view.

## Extension 5: Undo/Redo Support

Add undo/redo capability to the search controller:

```java
public interface UndoableController {
    void undo();
    void redo();
    boolean canUndo();
    boolean canRedo();
}

public interface UndoView {
    void updateUndoState(boolean canUndo, boolean canRedo);
    void setUndoHandler(Runnable onUndo);
    void setRedoHandler(Runnable onRedo);
}
```

Implement search history with undo/redo.

## Extension 6: Keyboard Shortcuts

Add keyboard shortcut support to the JavaFX view:

```java
// Ctrl+F: Focus search field
// Escape: Clear search
// Enter: Search
// Up/Down: Navigate results
// Enter on result: Show details
```

Use `scene.setOnKeyPressed()` to handle keyboard events.

## Extension 7: Testing JavaFX with TestFX

Learn to use TestFX library for GUI testing:

```xml
<dependency>
    <groupId>org.testfx</groupId>
    <artifactId>testfx-junit5</artifactId>
    <version>4.0.17</version>
    <scope>test</scope>
</dependency>
```

Write tests that actually click buttons and verify results.

## Extension 8: FXML Layout

Convert JavaFXSearchView to use FXML for layout:

1. Create `search-view.fxml` with the layout
2. Use `FXMLLoader` to load it
3. Use `@FXML` annotations for component injection

This separates layout (FXML) from logic (Java).

## Reflection Questions

After completing an extension:

1. How does this extension demonstrate the power of View interfaces?
2. What would change if you needed to support a web view?
3. How would you test this extension?
4. What design patterns did you use or discover?

## Submission (Optional)

If you complete any extensions and want feedback:
1. Create a new package `dk.viprogram.week08.extensions`
2. Include tests for your implementation
3. Bring to class or submit via GitHub
