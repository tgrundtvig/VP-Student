package dk.viprogram.week08.memory;

import java.util.List;

/**
 * The View interface for a Memory card game.
 *
 * The View is responsible for:
 * - Displaying the game grid
 * - Showing game state (moves, matches, time)
 * - Handling user interaction (card clicks)
 * - Displaying messages
 *
 * Notice: The View knows NOTHING about game logic.
 * It only knows how to display things and report user actions.
 *
 * This interface can be implemented by:
 * - ConsoleMemoryGameView (text-based)
 * - JavaFXMemoryGameView (graphical)
 * - MockMemoryGameView (for testing)
 */
public interface MemoryGameView {

    // ==================== Display Methods ====================

    /**
     * Displays the game grid.
     * Called whenever the grid needs to be refreshed.
     *
     * @param grid the current grid state (rows x cols of Cards)
     */
    void displayGrid(List<List<Card>> grid);

    /**
     * Updates the game statistics display.
     *
     * @param moves number of moves made
     * @param matches number of pairs found
     * @param totalPairs total pairs in the game
     * @param elapsedSeconds time elapsed since game start
     */
    void displayStats(int moves, int matches, int totalPairs, long elapsedSeconds);

    /**
     * Displays a message to the user.
     *
     * @param message the message to display
     */
    void displayMessage(String message);

    /**
     * Displays the game over screen.
     *
     * @param moves total moves made
     * @param elapsedSeconds total time taken
     */
    void displayGameOver(int moves, long elapsedSeconds);

    // ==================== Event Handling ====================

    /**
     * Handler for card click events.
     */
    interface CardClickHandler {
        /**
         * Called when the user clicks on a card.
         *
         * @param pos the position of the clicked card
         */
        void onCardClick(Position pos);
    }

    /**
     * Handler for new game requests.
     */
    interface NewGameHandler {
        /**
         * Called when the user wants to start a new game.
         *
         * @param rows desired number of rows
         * @param cols desired number of columns
         */
        void onNewGame(int rows, int cols);
    }

    /**
     * Sets the handler for card click events.
     *
     * @param handler the handler to call when a card is clicked
     */
    void setCardClickHandler(CardClickHandler handler);

    /**
     * Sets the handler for new game requests.
     *
     * @param handler the handler to call when a new game is requested
     */
    void setNewGameHandler(NewGameHandler handler);

    // ==================== UI Control ====================

    /**
     * Starts the view (begins accepting user input).
     * For console views, this might start a game loop.
     * For GUI views, this might show the window.
     */
    void start();

    /**
     * Schedules an action to run after a delay.
     * Used for pausing to show card faces before flipping back.
     *
     * @param delayMs delay in milliseconds
     * @param action the action to run
     */
    void scheduleAction(long delayMs, Runnable action);
}
