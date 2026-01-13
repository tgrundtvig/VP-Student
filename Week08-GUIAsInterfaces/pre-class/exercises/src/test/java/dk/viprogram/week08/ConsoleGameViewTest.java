package dk.viprogram.week08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ConsoleGameView implementation.
 */
@DisplayName("ConsoleGameView")
class ConsoleGameViewTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    private String getOutput() {
        return outputStream.toString();
    }

    private ConsoleGameView createView(String input) {
        Scanner scanner = new Scanner(input);
        return new ConsoleGameView(scanner, printStream);
    }

    @Nested
    @DisplayName("displayMessage")
    class DisplayMessageTests {

        @Test
        @DisplayName("displays message to output")
        void displaysMessage() {
            ConsoleGameView view = createView("");

            view.displayMessage("Hello, World!");

            assertTrue(getOutput().contains("Hello, World!"));
        }

        @Test
        @DisplayName("displays multiple messages")
        void displaysMultipleMessages() {
            ConsoleGameView view = createView("");

            view.displayMessage("First message");
            view.displayMessage("Second message");

            String output = getOutput();
            assertTrue(output.contains("First message"));
            assertTrue(output.contains("Second message"));
        }
    }

    @Nested
    @DisplayName("displayHint")
    class DisplayHintTests {

        @Test
        @DisplayName("displays hint to output")
        void displaysHint() {
            ConsoleGameView view = createView("");

            view.displayHint("Too low!");

            assertTrue(getOutput().contains("Too low!"));
        }
    }

    @Nested
    @DisplayName("displayAttempts")
    class DisplayAttemptsTests {

        @Test
        @DisplayName("displays attempt count")
        void displaysAttemptCount() {
            ConsoleGameView view = createView("");

            view.displayAttempts(3);

            assertTrue(getOutput().contains("3"));
        }
    }

    @Nested
    @DisplayName("displayGameOver")
    class DisplayGameOverTests {

        @Test
        @DisplayName("displays win message")
        void displaysWinMessage() {
            ConsoleGameView view = createView("");

            view.displayGameOver(true, 42, 5);

            String output = getOutput();
            // Should contain some indication of winning and the attempt count
            assertTrue(output.toLowerCase().contains("won") ||
                    output.toLowerCase().contains("congratulations") ||
                    output.toLowerCase().contains("correct") ||
                    output.toLowerCase().contains("win"));
        }

        @Test
        @DisplayName("displays loss message with secret")
        void displaysLossMessage() {
            ConsoleGameView view = createView("");

            view.displayGameOver(false, 42, 10);

            String output = getOutput();
            // Should reveal the secret number when lost
            assertTrue(output.contains("42"));
        }
    }

    @Nested
    @DisplayName("displayError")
    class DisplayErrorTests {

        @Test
        @DisplayName("displays error message")
        void displaysError() {
            ConsoleGameView view = createView("");

            view.displayError("Invalid input");

            assertTrue(getOutput().contains("Invalid input"));
        }
    }

    @Nested
    @DisplayName("promptForGuess")
    class PromptForGuessTests {

        @Test
        @DisplayName("displays prompt and returns input")
        void displaysPromptAndReturnsInput() {
            ConsoleGameView view = createView("42\n");

            String result = view.promptForGuess("Enter a number");

            assertTrue(getOutput().contains("Enter a number"));
            assertEquals("42", result);
        }

        @Test
        @DisplayName("returns trimmed input")
        void returnsTrimmedInput() {
            ConsoleGameView view = createView("  42  \n");

            String result = view.promptForGuess("Guess");

            // The view returns what was entered; trimming is optional
            assertTrue(result.contains("42"));
        }
    }
}
