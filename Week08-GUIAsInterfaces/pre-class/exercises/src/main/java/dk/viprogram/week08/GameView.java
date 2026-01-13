package dk.viprogram.week08;

/**
 * The View interface for a number guessing game.
 *
 * This interface defines WHAT the view can do, not HOW it does it.
 * Different implementations (console, JavaFX, web) can provide
 * different ways to display information and get input.
 *
 * Notice: The Controller will use this interface without knowing
 * which implementation is actually being used.
 */
public interface GameView {

    /**
     * Displays a general message to the user.
     *
     * @param message the message to display
     */
    void displayMessage(String message);

    /**
     * Displays a hint about the guess (too high, too low, etc.).
     *
     * @param hint the hint to display
     */
    void displayHint(String hint);

    /**
     * Displays the current number of attempts.
     *
     * @param attempts the number of guesses made so far
     */
    void displayAttempts(int attempts);

    /**
     * Displays a game over message with the result.
     *
     * @param won      true if the player won, false otherwise
     * @param secret   the secret number
     * @param attempts the total number of attempts made
     */
    void displayGameOver(boolean won, int secret, int attempts);

    /**
     * Displays an error message (e.g., invalid input).
     *
     * @param error the error message
     */
    void displayError(String error);

    /**
     * Prompts the user for a guess and returns it.
     * For console: blocks until input is received.
     * For GUI: this pattern needs adaptation (see post-class).
     *
     * @param prompt the prompt to show
     * @return the user's input as a string
     */
    String promptForGuess(String prompt);
}
