package dk.viprogram.quizapp;

import dk.viprogram.quizapp.controller.QuizController;
import dk.viprogram.quizapp.model.*;
import dk.viprogram.quizapp.repository.InMemoryQuizRepository;
import dk.viprogram.quizapp.repository.InMemoryResultRepository;
import dk.viprogram.quizapp.scoring.StandardScoring;
import dk.viprogram.quizapp.view.MockQuizView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for QuizController.
 */
class QuizControllerTest {

    private MockQuizView view;
    private InMemoryQuizRepository quizRepository;
    private InMemoryResultRepository resultRepository;
    private QuizController controller;
    private Quiz sampleQuiz;

    @BeforeEach
    void setUp() {
        view = new MockQuizView();
        quizRepository = new InMemoryQuizRepository();
        resultRepository = new InMemoryResultRepository();
        controller = new QuizController(
                view,
                quizRepository,
                resultRepository,
                new StandardScoring()
        );

        // Create a sample quiz
        sampleQuiz = Quiz.create(
                "Test Quiz",
                "A quiz for testing",
                List.of(
                        MultipleChoiceQuestion.create(
                                "What is 2+2?",
                                "Math",
                                10,
                                List.of("3", "4", "5", "6"),
                                1, // "4" is correct
                                "Basic addition"
                        ),
                        TrueFalseQuestion.create(
                                "The sky is blue.",
                                "Science",
                                5,
                                true,
                                "Look outside"
                        )
                )
        );
        quizRepository.save(sampleQuiz);
    }

    @Test
    @DisplayName("Play quiz with all correct answers gets full score")
    void playQuizAllCorrect() {
        // Arrange
        view.queueAnswer("2");    // Correct: option 2 is "4"
        view.queueHintChoice(false);
        view.queueAnswer("true"); // Correct
        view.queueHintChoice(false);

        // Act
        QuizResult result = controller.playQuiz(sampleQuiz, "TestPlayer");

        // Assert
        assertEquals(15, result.score()); // 10 + 5
        assertEquals(15, result.totalPossible());
        assertEquals(2, result.correctAnswers());
        assertEquals("A", result.getGrade());
    }

    @Test
    @DisplayName("Play quiz with all incorrect answers gets zero score")
    void playQuizAllIncorrect() {
        // Arrange
        view.queueAnswer("1");     // Incorrect: option 1 is "3"
        view.queueHintChoice(false);
        view.queueAnswer("false"); // Incorrect
        view.queueHintChoice(false);

        // Act
        QuizResult result = controller.playQuiz(sampleQuiz, "TestPlayer");

        // Assert
        assertEquals(0, result.score());
        assertEquals(0, result.correctAnswers());
        assertEquals("F", result.getGrade());
    }

    @Test
    @DisplayName("Play quiz with partial correct answers gets partial score")
    void playQuizPartialCorrect() {
        // Arrange
        view.queueAnswer("2");     // Correct
        view.queueHintChoice(false);
        view.queueAnswer("false"); // Incorrect
        view.queueHintChoice(false);

        // Act
        QuizResult result = controller.playQuiz(sampleQuiz, "TestPlayer");

        // Assert
        assertEquals(10, result.score()); // Only first question correct
        assertEquals(1, result.correctAnswers());
    }

    @Test
    @DisplayName("Result is saved to repository after quiz")
    void resultIsSavedToRepository() {
        // Arrange
        view.queueMenuChoice(1); // Take a quiz
        view.queueQuizSelection(sampleQuiz);
        view.queuePlayerName("Player1");
        view.queueAnswer("2");
        view.queueHintChoice(false);
        view.queueAnswer("true");
        view.queueHintChoice(false);
        view.queueMenuChoice(3); // Exit

        // Act
        controller.run();

        // Assert
        assertEquals(1, resultRepository.count());
        QuizResult saved = resultRepository.findAll().get(0);
        assertEquals("Player1", saved.playerName());
    }

    @Test
    @DisplayName("High scores shows top results")
    void highScoresShowsTopResults() {
        // Add some results
        QuizResult result1 = QuizResult.create(sampleQuiz.id(), "Player1", 15, 15, 2, 2, List.of());
        QuizResult result2 = QuizResult.create(sampleQuiz.id(), "Player2", 10, 15, 1, 2, List.of());
        resultRepository.save(result1);
        resultRepository.save(result2);

        // Arrange
        view.queueMenuChoice(2); // View high scores
        view.queueQuizSelection(sampleQuiz);
        view.queueMenuChoice(3); // Exit

        // Act
        controller.run();

        // Assert
        assertTrue(view.hasMessage("HIGH_SCORES"));
    }

    @Test
    @DisplayName("Invalid menu choice shows error")
    void invalidMenuChoiceShowsError() {
        // Act
        controller.handleMenuChoice(99);

        // Assert
        assertTrue(view.hasMessage("ERROR"));
    }

    @Test
    @DisplayName("Menu choice 3 stops controller")
    void menuChoice3StopsController() {
        // Act
        controller.handleMenuChoice(3);

        // Assert
        assertFalse(controller.isRunning());
    }

    @Test
    @DisplayName("Hint is displayed when requested")
    void hintIsDisplayedWhenRequested() {
        // Arrange
        view.queueAnswer("2");
        view.queueHintChoice(true); // Request hint
        view.queueAnswer("true");
        view.queueHintChoice(false);

        // Act
        controller.playQuiz(sampleQuiz, "TestPlayer");

        // Assert
        assertTrue(view.hasMessage("Basic addition")); // The hint text
    }
}
