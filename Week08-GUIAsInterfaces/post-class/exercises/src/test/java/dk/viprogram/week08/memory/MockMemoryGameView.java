package dk.viprogram.week08.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of MemoryGameView for testing.
 *
 * This demonstrates the power of interface-first design:
 * - We can test the Controller without a real GUI
 * - We can verify exactly what the View should display
 * - Tests run fast without UI overhead
 *
 * The mock records all method calls so tests can verify:
 * - What grids were displayed
 * - What messages were shown
 * - Whether game over was triggered correctly
 */
public class MockMemoryGameView implements MemoryGameView {

    // Recorded calls for verification
    private final List<List<List<Card>>> displayedGrids = new ArrayList<>();
    private final List<String> displayedMessages = new ArrayList<>();
    private final List<String> statsHistory = new ArrayList<>();
    private final List<ScheduledAction> scheduledActions = new ArrayList<>();
    private boolean gameOverDisplayed = false;
    private int gameOverMoves = 0;
    private long gameOverTime = 0;

    // Event handlers (set by controller)
    private CardClickHandler cardClickHandler;
    private NewGameHandler newGameHandler;

    // Control flags
    private boolean autoExecuteScheduledActions = false;

    // ==================== MemoryGameView Implementation ====================

    @Override
    public void displayGrid(List<List<Card>> grid) {
        // Deep copy the grid to preserve state at time of call
        List<List<Card>> copy = new ArrayList<>();
        for (List<Card> row : grid) {
            copy.add(new ArrayList<>(row));
        }
        displayedGrids.add(copy);
    }

    @Override
    public void displayStats(int moves, int matches, int totalPairs, long elapsedSeconds) {
        statsHistory.add(String.format("Moves: %d, Matches: %d/%d, Time: %ds",
                moves, matches, totalPairs, elapsedSeconds));
    }

    @Override
    public void displayMessage(String message) {
        displayedMessages.add(message);
    }

    @Override
    public void displayGameOver(int moves, long elapsedSeconds) {
        gameOverDisplayed = true;
        gameOverMoves = moves;
        gameOverTime = elapsedSeconds;
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
        // Mock does nothing on start
    }

    @Override
    public void scheduleAction(long delayMs, Runnable action) {
        ScheduledAction scheduled = new ScheduledAction(delayMs, action);
        scheduledActions.add(scheduled);

        if (autoExecuteScheduledActions) {
            action.run();
        }
    }

    // ==================== Test Utility Methods ====================

    /**
     * Simulates clicking a card at the given position.
     */
    public void simulateCardClick(Position pos) {
        if (cardClickHandler != null) {
            cardClickHandler.onCardClick(pos);
        }
    }

    /**
     * Simulates requesting a new game.
     */
    public void simulateNewGame(int rows, int cols) {
        if (newGameHandler != null) {
            newGameHandler.onNewGame(rows, cols);
        }
    }

    /**
     * Executes all pending scheduled actions immediately.
     */
    public void executeAllScheduledActions() {
        for (ScheduledAction action : new ArrayList<>(scheduledActions)) {
            action.action().run();
        }
        scheduledActions.clear();
    }

    /**
     * Sets whether scheduled actions should run immediately.
     */
    public void setAutoExecuteScheduledActions(boolean autoExecute) {
        this.autoExecuteScheduledActions = autoExecute;
    }

    // ==================== Verification Methods ====================

    /**
     * Returns all grids that were displayed.
     */
    public List<List<List<Card>>> getDisplayedGrids() {
        return new ArrayList<>(displayedGrids);
    }

    /**
     * Returns the most recently displayed grid.
     */
    public List<List<Card>> getLastDisplayedGrid() {
        if (displayedGrids.isEmpty()) {
            return null;
        }
        return displayedGrids.get(displayedGrids.size() - 1);
    }

    /**
     * Returns all messages that were displayed.
     */
    public List<String> getDisplayedMessages() {
        return new ArrayList<>(displayedMessages);
    }

    /**
     * Returns the last message displayed.
     */
    public String getLastDisplayedMessage() {
        if (displayedMessages.isEmpty()) {
            return null;
        }
        return displayedMessages.get(displayedMessages.size() - 1);
    }

    /**
     * Returns the history of stats updates.
     */
    public List<String> getStatsHistory() {
        return new ArrayList<>(statsHistory);
    }

    /**
     * Returns whether game over was displayed.
     */
    public boolean wasGameOverDisplayed() {
        return gameOverDisplayed;
    }

    /**
     * Returns the moves shown on game over.
     */
    public int getGameOverMoves() {
        return gameOverMoves;
    }

    /**
     * Returns the time shown on game over.
     */
    public long getGameOverTime() {
        return gameOverTime;
    }

    /**
     * Returns all scheduled actions.
     */
    public List<ScheduledAction> getScheduledActions() {
        return new ArrayList<>(scheduledActions);
    }

    /**
     * Clears all recorded data.
     */
    public void reset() {
        displayedGrids.clear();
        displayedMessages.clear();
        statsHistory.clear();
        scheduledActions.clear();
        gameOverDisplayed = false;
        gameOverMoves = 0;
        gameOverTime = 0;
    }

    // ==================== Helper Classes ====================

    /**
     * Represents a scheduled action.
     */
    public record ScheduledAction(long delayMs, Runnable action) {}
}
