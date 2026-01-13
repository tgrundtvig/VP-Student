package dk.viprogram.quizapp;

import dk.viprogram.quizapp.model.MultipleChoiceQuestion;
import dk.viprogram.quizapp.model.Question;
import dk.viprogram.quizapp.scoring.PenaltyScoring;
import dk.viprogram.quizapp.scoring.ScoringStrategy;
import dk.viprogram.quizapp.scoring.StandardScoring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for scoring strategies.
 */
class ScoringStrategyTest {

    private final Question sampleQuestion = MultipleChoiceQuestion.create(
            "Test question",
            "Test",
            10,
            List.of("A", "B", "C"),
            0,
            "Hint"
    );

    // ==================== StandardScoring Tests ====================

    @Test
    @DisplayName("Standard scoring gives full points for correct answer")
    void standardScoringCorrect() {
        ScoringStrategy strategy = new StandardScoring();

        int score = strategy.calculateScore(sampleQuestion, "1", true);

        assertEquals(10, score);
    }

    @Test
    @DisplayName("Standard scoring gives zero for incorrect answer")
    void standardScoringIncorrect() {
        ScoringStrategy strategy = new StandardScoring();

        int score = strategy.calculateScore(sampleQuestion, "2", false);

        assertEquals(0, score);
    }

    // ==================== PenaltyScoring Tests ====================

    @Test
    @DisplayName("Penalty scoring gives full points for correct answer")
    void penaltyScoringCorrect() {
        ScoringStrategy strategy = new PenaltyScoring(0.25);

        int score = strategy.calculateScore(sampleQuestion, "1", true);

        assertEquals(10, score);
    }

    @Test
    @DisplayName("Penalty scoring deducts points for incorrect answer")
    void penaltyScoringIncorrect() {
        ScoringStrategy strategy = new PenaltyScoring(0.25);

        int score = strategy.calculateScore(sampleQuestion, "2", false);

        assertEquals(-2, score); // -25% of 10 = -2.5, truncated to -2
    }

    @Test
    @DisplayName("Penalty scoring with different penalty fraction")
    void penaltyScoringCustomFraction() {
        ScoringStrategy strategy = new PenaltyScoring(0.5); // 50% penalty

        int score = strategy.calculateScore(sampleQuestion, "2", false);

        assertEquals(-5, score); // -50% of 10 = -5
    }

    // ==================== Strategy Pattern Tests ====================

    @Test
    @DisplayName("Different strategies can be swapped")
    void strategiesAreInterchangeable() {
        ScoringStrategy standard = new StandardScoring();
        ScoringStrategy penalty = new PenaltyScoring();

        // Same question, same wrong answer, different strategies
        int standardScore = standard.calculateScore(sampleQuestion, "2", false);
        int penaltyScore = penalty.calculateScore(sampleQuestion, "2", false);

        assertEquals(0, standardScore);
        assertTrue(penaltyScore < 0);
    }
}
