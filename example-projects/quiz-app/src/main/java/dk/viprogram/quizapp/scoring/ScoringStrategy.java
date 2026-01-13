package dk.viprogram.quizapp.scoring;

import dk.viprogram.quizapp.model.Question;

/**
 * Strategy interface for calculating scores.
 *
 * This demonstrates the Strategy pattern - different scoring rules
 * can be swapped without changing the quiz logic.
 *
 * Examples:
 * - StandardScoring: Full points for correct, 0 for incorrect
 * - PartialCreditScoring: Partial points for "close" answers
 * - TimeBonusScoring: Extra points for fast answers
 * - PenaltyScoring: Negative points for wrong answers
 */
public interface ScoringStrategy {

    /**
     * Calculates the score for an answer.
     *
     * @param question the question being answered
     * @param answer   the given answer
     * @param correct  whether the answer was correct
     * @return the points earned (can be negative for penalty scoring)
     */
    int calculateScore(Question question, String answer, boolean correct);

    /**
     * Returns the name of this scoring strategy.
     */
    String getName();

    /**
     * Returns a description of how this strategy works.
     */
    String getDescription();
}
