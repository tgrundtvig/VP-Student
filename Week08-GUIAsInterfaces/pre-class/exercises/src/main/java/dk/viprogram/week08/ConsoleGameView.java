package dk.viprogram.week08;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * A console-based implementation of GameView.
 *
 * Your task: Implement all the methods in this class.
 * The tests will guide you - run them to see what's expected.
 *
 * This implementation:
 * - Displays messages to System.out (or a custom PrintStream)
 * - Reads input from System.in (or a custom Scanner)
 */
public class ConsoleGameView implements GameView {

    private final Scanner scanner;
    private final PrintStream out;

    /**
     * Creates a console view using System.in and System.out.
     */
    public ConsoleGameView() {
        this(new Scanner(System.in), System.out);
    }

    /**
     * Creates a console view with custom input/output.
     * Useful for testing.
     *
     * @param scanner the scanner to read input from
     * @param out     the print stream to write output to
     */
    public ConsoleGameView(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    @Override
    public void displayMessage(String message) {
        // TODO: Implement this method
        // Print the message to the output stream
        throw new UnsupportedOperationException("Implement displayMessage()");
    }

    @Override
    public void displayHint(String hint) {
        // TODO: Implement this method
        // Print the hint, perhaps with some formatting like ">>> hint"
        throw new UnsupportedOperationException("Implement displayHint()");
    }

    @Override
    public void displayAttempts(int attempts) {
        // TODO: Implement this method
        // Display something like "Attempts so far: 3"
        throw new UnsupportedOperationException("Implement displayAttempts()");
    }

    @Override
    public void displayGameOver(boolean won, int secret, int attempts) {
        // TODO: Implement this method
        // Display a game over message
        // If won: congratulate and show attempts
        // If lost: show the secret number
        throw new UnsupportedOperationException("Implement displayGameOver()");
    }

    @Override
    public void displayError(String error) {
        // TODO: Implement this method
        // Display the error, perhaps with "ERROR: " prefix
        throw new UnsupportedOperationException("Implement displayError()");
    }

    @Override
    public String promptForGuess(String prompt) {
        // TODO: Implement this method
        // 1. Print the prompt
        // 2. Read a line from the scanner
        // 3. Return the line
        throw new UnsupportedOperationException("Implement promptForGuess()");
    }
}
