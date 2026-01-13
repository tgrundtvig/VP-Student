package dk.viprogram.week08.memory;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

/**
 * A JavaFX-based implementation of the Memory game view.
 *
 * This view displays the game graphically with:
 * - A grid of clickable card buttons showing emojis
 * - Game statistics in a header bar
 * - A "New Game" button with size selection
 *
 * The cards are displayed as large buttons with emoji symbols.
 */
public class JavaFXMemoryGameView implements MemoryGameView {

    private static final int CARD_SIZE = 90;  // Size of each card button

    private static final String CARD_STYLE_FACE_DOWN =
            "-fx-font-size: 32px; " +
            "-fx-min-width: " + CARD_SIZE + "px; " +
            "-fx-min-height: " + CARD_SIZE + "px; " +
            "-fx-max-width: " + CARD_SIZE + "px; " +
            "-fx-max-height: " + CARD_SIZE + "px; " +
            "-fx-background-color: #2196F3; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 8px;";

    private static final String CARD_STYLE_FACE_UP =
            "-fx-font-size: 28px; " +
            "-fx-font-weight: bold; " +
            "-fx-min-width: " + CARD_SIZE + "px; " +
            "-fx-min-height: " + CARD_SIZE + "px; " +
            "-fx-max-width: " + CARD_SIZE + "px; " +
            "-fx-max-height: " + CARD_SIZE + "px; " +
            "-fx-background-color: #FFFFFF; " +
            "-fx-text-fill: #000000; " +
            "-fx-background-radius: 8px; " +
            "-fx-border-color: #2196F3; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 8px;";

    private static final String CARD_STYLE_MATCHED =
            "-fx-font-size: 28px; " +
            "-fx-font-weight: bold; " +
            "-fx-min-width: " + CARD_SIZE + "px; " +
            "-fx-min-height: " + CARD_SIZE + "px; " +
            "-fx-max-width: " + CARD_SIZE + "px; " +
            "-fx-max-height: " + CARD_SIZE + "px; " +
            "-fx-background-color: #C8E6C9; " +
            "-fx-text-fill: #388E3C; " +
            "-fx-background-radius: 8px; " +
            "-fx-border-color: #388E3C; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 8px;";

    private final Stage stage;
    private GridPane cardGrid;
    private Label statsLabel;
    private Label messageLabel;
    private ComboBox<String> sizeSelector;
    private Button newGameButton;

    private CardClickHandler cardClickHandler;
    private NewGameHandler newGameHandler;
    private Button[][] cardButtons;
    private int currentGridRows = 0;
    private int currentGridCols = 0;

    /**
     * Creates a new JavaFX view.
     *
     * @param stage the primary stage to use
     */
    public JavaFXMemoryGameView(Stage stage) {
        this.stage = stage;
        setupUI();
    }

    private void setupUI() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Top: Stats and controls
        HBox topBar = createTopBar();
        root.setTop(topBar);

        // Center: Card grid (will be populated by displayGrid)
        cardGrid = new GridPane();
        cardGrid.setAlignment(Pos.CENTER);
        cardGrid.setHgap(5);
        cardGrid.setVgap(5);
        cardGrid.setPadding(new Insets(20));
        root.setCenter(cardGrid);

        // Bottom: Message area
        messageLabel = new Label("");
        messageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #666;");
        HBox bottomBar = new HBox(messageLabel);
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setPadding(new Insets(10));
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 600, 700);
        stage.setScene(scene);
        stage.setTitle("🎴 Memory Card Game");
    }

    private HBox createTopBar() {
        // Stats label
        statsLabel = new Label("Moves: 0 | Matches: 0/0 | Time: 0:00");
        statsLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Size selector
        sizeSelector = new ComboBox<>();
        sizeSelector.getItems().addAll(
                "2x2 (Easy)",
                "4x4 (Medium)",
                "6x6 (Hard)",
                "4x6 (Wide)",
                "8x8 (Expert)"
        );
        sizeSelector.setValue("4x4 (Medium)");

        // New Game button
        newGameButton = new Button("New Game");
        newGameButton.setStyle("-fx-font-size: 14px;");
        newGameButton.setOnAction(e -> handleNewGame());

        HBox topBar = new HBox(20, statsLabel, sizeSelector, newGameButton);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(10));

        return topBar;
    }

    private void handleNewGame() {
        String selected = sizeSelector.getValue();
        int rows, cols;

        if (selected.startsWith("2x2")) {
            rows = 2; cols = 2;
        } else if (selected.startsWith("4x4")) {
            rows = 4; cols = 4;
        } else if (selected.startsWith("6x6")) {
            rows = 6; cols = 6;
        } else if (selected.startsWith("4x6")) {
            rows = 4; cols = 6;
        } else if (selected.startsWith("8x8")) {
            rows = 8; cols = 8;
        } else {
            rows = 4; cols = 4;
        }

        if (newGameHandler != null) {
            newGameHandler.onNewGame(rows, cols);
        }
    }

    @Override
    public void displayGrid(List<List<Card>> grid) {
        Platform.runLater(() -> {
            cardGrid.getChildren().clear();

            int rows = grid.size();
            int cols = grid.get(0).size();
            cardButtons = new Button[rows][cols];

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    Card card = grid.get(row).get(col);
                    Button btn = createCardButton(card, row, col);
                    cardButtons[row][col] = btn;
                    cardGrid.add(btn, col, row);
                }
            }

            // Only resize window when grid size changes (new game)
            if (rows != currentGridRows || cols != currentGridCols) {
                currentGridRows = rows;
                currentGridCols = cols;
                stage.setWidth(Math.max(500, cols * (CARD_SIZE + 10) + 100));
                stage.setHeight(Math.max(500, rows * (CARD_SIZE + 10) + 220));
            }
        });
    }

    /**
     * Creates a button for a single card.
     *
     * @param card the card to display
     * @param row the row position
     * @param col the column position
     * @return a configured Button
     */
    private Button createCardButton(Card card, int row, int col) {
        // TODO: Implement this method!
        //
        // Create and configure a Button for the given card:
        //
        // 1. Create a new Button with card.display() as its text
        //
        // 2. Set the button's style based on the card state:
        //    - If card.matched() is true: use CARD_STYLE_MATCHED
        //    - Else if card.faceUp() is true: use CARD_STYLE_FACE_UP
        //    - Else: use CARD_STYLE_FACE_DOWN
        //
        // 3. Set the button's onAction to call cardClickHandler:
        //    button.setOnAction(event -> {
        //        if (cardClickHandler != null) {
        //            cardClickHandler.onCardClick(new Position(row, col));
        //        }
        //    });
        //
        // 4. Disable the button if card is matched or face up:
        //    button.setDisable(card.matched() || card.faceUp());
        //
        // 5. Return the button
        //
        // Hint: Use btn.setText(), btn.setStyle(), btn.setOnAction(), btn.setDisable()

        throw new UnsupportedOperationException("TODO: Implement createCardButton()");
    }

    @Override
    public void displayStats(int moves, int matches, int totalPairs, long elapsedSeconds) {
        Platform.runLater(() -> {
            long minutes = elapsedSeconds / 60;
            long seconds = elapsedSeconds % 60;
            statsLabel.setText(String.format(
                    "Moves: %d | Matches: %d/%d | Time: %d:%02d",
                    moves, matches, totalPairs, minutes, seconds));
        });
    }

    @Override
    public void displayMessage(String message) {
        Platform.runLater(() -> messageLabel.setText(message));
    }

    @Override
    public void displayGameOver(int moves, long elapsedSeconds) {
        Platform.runLater(() -> {
            long minutes = elapsedSeconds / 60;
            long seconds = elapsedSeconds % 60;

            VBox content = new VBox(20);
            content.setAlignment(Pos.CENTER);
            content.setPadding(new Insets(30));

            Label titleLabel = new Label("🎉 Congratulations! 🎉");
            titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

            Label statsLabel = new Label(String.format(
                    "You won in %d moves!\nTime: %d:%02d",
                    moves, minutes, seconds));
            statsLabel.setStyle("-fx-font-size: 18px; -fx-text-alignment: center;");

            Button playAgainBtn = new Button("Play Again");
            playAgainBtn.setStyle("-fx-font-size: 16px;");
            playAgainBtn.setOnAction(e -> handleNewGame());

            content.getChildren().addAll(titleLabel, statsLabel, playAgainBtn);

            // Show in the card grid area
            cardGrid.getChildren().clear();
            cardGrid.add(content, 0, 0);
        });
    }

    @Override
    public void setCardClickHandler(CardClickHandler handler) {
        this.cardClickHandler = handler;
    }

    @Override
    public void setNewGameHandler(NewGameHandler handler) {
        this.newGameHandler = handler;
    }

    @Override
    public void start() {
        Platform.runLater(() -> stage.show());
    }

    @Override
    public void scheduleAction(long delayMs, Runnable action) {
        Platform.runLater(() -> {
            PauseTransition pause = new PauseTransition(Duration.millis(delayMs));
            pause.setOnFinished(e -> action.run());
            pause.play();
        });
    }
}
