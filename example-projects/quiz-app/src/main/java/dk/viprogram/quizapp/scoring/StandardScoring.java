package dk.viprogram.quizapp.scoring;

import dk.viprogram.quizapp.model.Question;

/**
 * Standard scoring: full points for correct answers, 0 for incorrect.
 */
public class StandardScoring implements ScoringStrategy {

    @Override
    public int calculateScore(Question question, String answer, boolean correct) {
        return correct ? question.getPoints() : 0;
    }

    @Override
    public String getName() {
        return "Standard Scoring";
    }

    @Override
    public String getDescription() {
        return "Full points for correct answers, 0 for incorrect answers.";
    }
}
