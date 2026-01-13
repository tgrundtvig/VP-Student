package dk.viprogram.week08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A mock implementation of GameView for testing.
 *
 * Instead of displaying to screen or reading from keyboard,
 * this mock:
 * - Records all messages that would be displayed
 * - Returns pre-programmed inputs when asked
 *
 * This allows testing the Controller without any actual UI.
 */
public class MockGameView implements GameView {

    private final List<String> messages = new ArrayList<>();
    private final List<String> hints = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();
    private final List<Integer> displayedAttempts = new ArrayList<>();
    private final Queue<String> preparedInputs = new LinkedList<>();

    private boolean gameOverDisplayed = false;
    private boolean lastGameWon = false;
    private int lastGameSecret = 0;
    private int lastGameAttempts = 0;

    // --- GameView implementation ---

    @Override
    public void displayMessage(String message) {
        messages.add(message);
    }

    @Override
    public void displayHint(String hint) {
        hints.add(hint);
    }

    @Override
    public void displayAttempts(int attempts) {
        displayedAttempts.add(attempts);
    }

    @Override
    public void displayGameOver(boolean won, int secret, int attempts) {
        gameOverDisplayed = true;
        lastGameWon = won;
        lastGameSecret = secret;
        lastGameAttempts = attempts;
    }

    @Override
    public void displayError(String error) {
        errors.add(error);
    }

    @Override
    public String promptForGuess(String prompt) {
        messages.add(prompt);
        if (preparedInputs.isEmpty()) {
            throw new IllegalStateException(
                    "No prepared input available. Call prepareInput() before running the test.");
        }
        return preparedInputs.poll();
    }

    // --- Test helper methods ---

    /**
     * Prepares an input that will be returned when promptForGuess is called.
     * Call this multiple times to queue up multiple inputs.
     *
     * @param input the input to return
     */
    public void prepareInput(String input) {
        preparedInputs.add(input);
    }

    /**
     * Prepares multiple inputs at once.
     *
     * @param inputs the inputs to queue
     */
    public void prepareInputs(String... inputs) {
        for (String input : inputs) {
            preparedInputs.add(input);
        }
    }

    /**
     * Returns true if the given message was displayed.
     */
    public boolean wasMessageDisplayed(String message) {
        return messages.stream().anyMatch(m -> m.contains(message));
    }

    /**
     * Returns all displayed messages.
     */
    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    /**
     * Returns true if the given hint was displayed.
     */
    public boolean wasHintDisplayed(String hint) {
        return hints.stream().anyMatch(h -> h.contains(hint));
    }

    /**
     * Returns all displayed hints.
     */
    public List<String> getHints() {
        return new ArrayList<>(hints);
    }

    /**
     * Returns true if the given error was displayed.
     */
    public boolean wasErrorDisplayed(String error) {
        return errors.stream().anyMatch(e -> e.contains(error));
    }

    /**
     * Returns all displayed errors.
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    /**
     * Returns true if game over was displayed.
     */
    public boolean wasGameOverDisplayed() {
        return gameOverDisplayed;
    }

    /**
     * Returns true if the last game over message indicated a win.
     */
    public boolean didPlayerWin() {
        return lastGameWon;
    }

    /**
     * Returns the secret number from the last game over display.
     */
    public int getLastGameSecret() {
        return lastGameSecret;
    }

    /**
     * Returns the attempts from the last game over display.
     */
    public int getLastGameAttempts() {
        return lastGameAttempts;
    }

    /**
     * Clears all recorded data. Useful between tests.
     */
    public void reset() {
        messages.clear();
        hints.clear();
        errors.clear();
        displayedAttempts.clear();
        preparedInputs.clear();
        gameOverDisplayed = false;
        lastGameWon = false;
        lastGameSecret = 0;
        lastGameAttempts = 0;
    }
}
