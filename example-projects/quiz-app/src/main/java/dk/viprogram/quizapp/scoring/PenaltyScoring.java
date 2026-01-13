package dk.viprogram.quizapp.scoring;

import dk.viprogram.quizapp.model.Question;

/**
 * Penalty scoring: full points for correct, negative for incorrect.
 *
 * This discourages guessing.
 */
public class PenaltyScoring implements ScoringStrategy {

    private final double penaltyFraction;

    /**
     * Creates penalty scoring with specified penalty fraction.
     *
     * @param penaltyFraction fraction of points to deduct (e.g., 0.25 for 25%)
     */
    public PenaltyScoring(double penaltyFraction) {
        this.penaltyFraction = penaltyFraction;
    }

    /**
     * Creates penalty scoring with default 25% penalty.
     */
    public PenaltyScoring() {
        this(0.25);
    }

    @Override
    public int calculateScore(Question question, String answer, boolean correct) {
        if (correct) {
            return question.getPoints();
        } else {
            return (int) (-question.getPoints() * penaltyFraction);
        }
    }

    @Override
    public String getName() {
        return "Penalty Scoring";
    }

    @Override
    public String getDescription() {
        return String.format(
                "Full points for correct answers, -%d%% for incorrect answers.",
                (int) (penaltyFraction * 100)
        );
    }
}
