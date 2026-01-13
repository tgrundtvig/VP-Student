package dk.viprogram.week08.memory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The Controller for a Memory card game.
 *
 * The Controller connects Model and View:
 * - Handles user input from the View
 * - Updates the Model based on game rules
 * - Tells the View what to display
 *
 * Notice: The Controller contains the game flow logic,
 * but delegates data storage to Model and display to View.
 */
public class MemoryGameController {

    private static final long CARD_SHOW_DELAY_MS = 1000;  // Time to show cards before flipping back
    private static final long STATS_UPDATE_INTERVAL_MS = 1000;  // Update time display every second

    private final MemoryGameModel model;
    private final MemoryGameView view;
    private Timer statsTimer;
    private boolean waitingForFlipBack;

    /**
     * Creates a new controller connecting the given model and view.
     *
     * @param model the game model
     * @param view the game view
     */
    public MemoryGameController(MemoryGameModel model, MemoryGameView view) {
        this.model = model;
        this.view = view;
        this.waitingForFlipBack = false;

        // Connect view events to controller handlers
        view.setCardClickHandler(this::handleCardClick);
        view.setNewGameHandler(this::handleNewGame);
    }

    /**
     * Starts the game with the specified grid size.
     *
     * @param rows number of rows
     * @param cols number of columns
     */
    public void startGame(int rows, int cols) {
        handleNewGame(rows, cols);
        view.start();
    }

    /**
     * Handles a request to start a new game.
     */
    private void handleNewGame(int rows, int cols) {
        // Stop any existing timer
        if (statsTimer != null) {
            statsTimer.cancel();
        }

        // Initialize new game
        model.newGame(rows, cols);
        waitingForFlipBack = false;

        // Update view
        refreshView();
        view.displayMessage("Find all the matching pairs!");

        // Start stats update timer
        startStatsTimer();
    }

    /**
     * Handles a card click event from the view.
     *
     * @param pos the position of the clicked card
     */
    private void handleCardClick(Position pos) {
        // Ignore clicks while waiting for cards to flip back
        if (waitingForFlipBack) {
            return;
        }

        // Try to flip the card
        boolean flipped = model.flipCard(pos);
        if (!flipped) {
            view.displayMessage("Can't flip that card!");
            return;
        }

        // Update the display to show the flipped card
        refreshView();

        // Check if we have two cards flipped
        if (model.getFlippedCards().size() == 2) {
            // TODO: Implement what happens after two cards are flipped!
            //
            // Steps:
            // 1. Set waitingForFlipBack = true (to prevent more clicks)
            //
            // 2. Schedule an action after CARD_SHOW_DELAY_MS to:
            //    a. Call model.checkMatch() and store the result
            //    b. Set waitingForFlipBack = false
            //    c. Refresh the view using refreshView()
            //    d. Display an appropriate message based on result:
            //       - MATCH: "Match found! 🎉"
            //       - NO_MATCH: "No match. Try again!"
            //    e. Check if game is over using model.isGameOver()
            //       If so, stop the stats timer and call handleGameOver()
            //
            // Use: view.scheduleAction(delayMs, () -> { ... });
            //
            // Example of scheduleAction:
            //   view.scheduleAction(1000, () -> {
            //       System.out.println("This runs after 1 second");
            //   });

            throw new UnsupportedOperationException("TODO: Implement card matching logic");
        }
    }

    /**
     * Updates the view with current game state.
     */
    private void refreshView() {
        view.displayGrid(model.getGrid());
        view.displayStats(
                model.getMoves(),
                model.getMatchesFound(),
                model.getTotalPairs(),
                model.getElapsedTimeSeconds()
        );
    }

    /**
     * Handles the end of the game.
     */
    private void handleGameOver() {
        view.displayGameOver(model.getMoves(), model.getElapsedTimeSeconds());
    }

    /**
     * Starts the timer that updates the time display.
     */
    private void startStatsTimer() {
        statsTimer = new Timer();
        statsTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!model.isGameOver()) {
                    view.displayStats(
                            model.getMoves(),
                            model.getMatchesFound(),
                            model.getTotalPairs(),
                            model.getElapsedTimeSeconds()
                    );
                }
            }
        }, STATS_UPDATE_INTERVAL_MS, STATS_UPDATE_INTERVAL_MS);
    }

    /**
     * Stops the controller and cleans up resources.
     */
    public void stop() {
        if (statsTimer != null) {
            statsTimer.cancel();
            statsTimer = null;
        }
    }
}
