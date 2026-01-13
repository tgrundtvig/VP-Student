package dk.viprogram.week08;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * A JavaFX-based implementation of SearchView.
 *
 * Your task: Implement all the TODO methods in this class.
 *
 * This view creates a GUI with:
 * - A search field and button at the top
 * - A list view for results in the center
 * - A status bar at the bottom
 */
public class JavaFXSearchView implements SearchView {

    private final BorderPane root;
    private final TextField searchField;
    private final Button searchButton;
    private final ListView<String> resultsList;
    private final Label statusLabel;
    private final ProgressIndicator loadingIndicator;

    private SearchHandler searchHandler;

    /**
     * Creates the JavaFX view with all UI components.
     */
    public JavaFXSearchView() {
        // Create components
        searchField = new TextField();
        searchField.setPromptText("Enter search query...");
        searchField.setPrefWidth(300);

        searchButton = new Button("Search");

        loadingIndicator = new ProgressIndicator();
        loadingIndicator.setVisible(false);
        loadingIndicator.setPrefSize(20, 20);

        resultsList = new ListView<>();
        resultsList.setPrefHeight(400);

        statusLabel = new Label("Ready");

        // Layout - Top: search bar
        HBox searchBar = new HBox(10);
        searchBar.setPadding(new Insets(10));
        searchBar.getChildren().addAll(searchField, searchButton, loadingIndicator);

        // Layout - Center: results
        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(0, 10, 10, 10));
        centerBox.getChildren().addAll(new Label("Results:"), resultsList);

        // Layout - Bottom: status
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        statusBar.setStyle("-fx-background-color: #f0f0f0;");
        statusBar.getChildren().add(statusLabel);

        // Root layout
        root = new BorderPane();
        root.setTop(searchBar);
        root.setCenter(centerBox);
        root.setBottom(statusBar);

        // Wire up events
        setupEventHandlers();
    }

    /**
     * Sets up event handlers for the UI components.
     */
    private void setupEventHandlers() {
        // TODO: Implement event handlers
        // 1. When searchButton is clicked, call the searchHandler with the query
        // 2. When Enter is pressed in searchField, also trigger search
        //
        // Hint:
        // searchButton.setOnAction(event -> { ... });
        // searchField.setOnAction(event -> { ... });

        searchButton.setOnAction(event -> {
            // TODO: Get text from searchField and call searchHandler.onSearch(query)
            throw new UnsupportedOperationException("Implement search button handler");
        });

        searchField.setOnAction(event -> {
            // TODO: Same as button - trigger search
            throw new UnsupportedOperationException("Implement search field handler");
        });
    }

    /**
     * Returns the root pane for adding to a Scene.
     */
    public BorderPane getRoot() {
        return root;
    }

    @Override
    public <T> void displayResults(List<T> results) {
        // TODO: Implement this method
        // 1. Clear the resultsList
        // 2. Add each result's toString() to the list
        //
        // Remember to use Platform.runLater() if called from a non-UI thread!
        //
        // Hint:
        // Platform.runLater(() -> {
        //     resultsList.getItems().clear();
        //     for (T result : results) {
        //         resultsList.getItems().add(result.toString());
        //     }
        // });
        throw new UnsupportedOperationException("Implement displayResults()");
    }

    @Override
    public void displayNoResults() {
        // TODO: Implement this method
        // Clear results and show a message indicating no results
        throw new UnsupportedOperationException("Implement displayNoResults()");
    }

    @Override
    public void displayResultCount(int count) {
        // TODO: Implement this method
        // Update the statusLabel with the count
        throw new UnsupportedOperationException("Implement displayResultCount()");
    }

    @Override
    public void displayMessage(String message) {
        // TODO: Implement this method
        // Update the statusLabel with the message
        throw new UnsupportedOperationException("Implement displayMessage()");
    }

    @Override
    public void displayError(String error) {
        // TODO: Implement this method
        // Show an error alert or update status with error
        //
        // Hint for alert:
        // Alert alert = new Alert(Alert.AlertType.ERROR);
        // alert.setTitle("Error");
        // alert.setContentText(error);
        // alert.showAndWait();
        throw new UnsupportedOperationException("Implement displayError()");
    }

    @Override
    public void showLoading(boolean loading) {
        // TODO: Implement this method
        // Show or hide the loadingIndicator
        // Also disable/enable the searchButton while loading
        throw new UnsupportedOperationException("Implement showLoading()");
    }

    @Override
    public void setSearchHandler(SearchHandler handler) {
        this.searchHandler = handler;
    }
}
