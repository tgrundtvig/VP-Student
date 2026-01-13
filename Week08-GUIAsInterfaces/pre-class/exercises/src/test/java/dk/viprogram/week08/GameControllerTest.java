package dk.viprogram.week08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for GameController using MockGameView.
 *
 * Notice: These tests run WITHOUT any GUI or console interaction.
 * The mock view captures what would be displayed and provides
 * pre-programmed inputs.
 */
@DisplayName("GameController with MockGameView")
class GameControllerTest {

    private MockGameView mockView;

    @BeforeEach
    void setUp() {
        mockView = new MockGameView();
    }

    @Nested
    @DisplayName("Game Start")
    class GameStartTests {

        @Test
        @DisplayName("displays welcome message")
        void displaysWelcomeMessage() {
            GameModel model = new GameModel(50, 5);  // Secret = 50, max 5 attempts
            mockView.prepareInput("50");  // Correct guess immediately

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertTrue(mockView.wasMessageDisplayed("Welcome"));
        }

        @Test
        @DisplayName("displays max attempts at start")
        void displaysMaxAttempts() {
            GameModel model = new GameModel(50, 7);
            mockView.prepareInput("50");

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertTrue(mockView.wasMessageDisplayed("7"));
        }
    }

    @Nested
    @DisplayName("Guessing")
    class GuessingTests {

        @Test
        @DisplayName("shows too low hint for low guess")
        void showsTooLowHint() {
            GameModel model = new GameModel(50, 5);
            mockView.prepareInputs("25", "50");  // First low, then correct

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertTrue(mockView.wasHintDisplayed("low") ||
                    mockView.wasHintDisplayed("Low"));
        }

        @Test
        @DisplayName("shows too high hint for high guess")
        void showsTooHighHint() {
            GameModel model = new GameModel(50, 5);
            mockView.prepareInputs("75", "50");  // First high, then correct

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertTrue(mockView.wasHintDisplayed("high") ||
                    mockView.wasHintDisplayed("High"));
        }

        @Test
        @DisplayName("shows correct hint for correct guess")
        void showsCorrectHint() {
            GameModel model = new GameModel(50, 5);
            mockView.prepareInput("50");

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertTrue(mockView.wasHintDisplayed("Correct") ||
                    mockView.wasHintDisplayed("correct"));
        }
    }

    @Nested
    @DisplayName("Invalid Input")
    class InvalidInputTests {

        @Test
        @DisplayName("shows error for non-numeric input")
        void showsErrorForNonNumericInput() {
            GameModel model = new GameModel(50, 5);
            mockView.prepareInputs("abc", "50");  // Invalid, then correct

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertTrue(mockView.wasErrorDisplayed("number") ||
                    mockView.wasErrorDisplayed("valid") ||
                    mockView.getErrors().size() > 0);
        }
    }

    @Nested
    @DisplayName("Game Over")
    class GameOverTests {

        @Test
        @DisplayName("displays game over when won")
        void displaysGameOverWhenWon() {
            GameModel model = new GameModel(50, 5);
            mockView.prepareInput("50");

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertTrue(mockView.wasGameOverDisplayed());
            assertTrue(mockView.didPlayerWin());
        }

        @Test
        @DisplayName("displays game over when lost")
        void displaysGameOverWhenLost() {
            GameModel model = new GameModel(50, 3);  // Only 3 attempts
            mockView.prepareInputs("10", "20", "30");  // All wrong

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertTrue(mockView.wasGameOverDisplayed());
            assertFalse(mockView.didPlayerWin());
            assertEquals(50, mockView.getLastGameSecret());
        }

        @Test
        @DisplayName("reports correct attempt count")
        void reportsCorrectAttemptCount() {
            GameModel model = new GameModel(50, 5);
            mockView.prepareInputs("25", "75", "50");  // 3 attempts to win

            GameController controller = new GameController(model, mockView);
            controller.startGame();

            assertEquals(3, mockView.getLastGameAttempts());
        }
    }

    @Nested
    @DisplayName("Single Turn (processGuess)")
    class ProcessGuessTests {

        @Test
        @DisplayName("processGuess updates view for low guess")
        void processGuessLow() {
            GameModel model = new GameModel(50, 5);
            GameController controller = new GameController(model, mockView);

            controller.processGuess(25);

            assertTrue(mockView.wasHintDisplayed("low") ||
                    mockView.wasHintDisplayed("Low"));
        }

        @Test
        @DisplayName("processGuess updates view for high guess")
        void processGuessHigh() {
            GameModel model = new GameModel(50, 5);
            GameController controller = new GameController(model, mockView);

            controller.processGuess(75);

            assertTrue(mockView.wasHintDisplayed("high") ||
                    mockView.wasHintDisplayed("High"));
        }
    }
}
