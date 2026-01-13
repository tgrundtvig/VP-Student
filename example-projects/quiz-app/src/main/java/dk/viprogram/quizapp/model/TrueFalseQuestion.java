package dk.viprogram.quizapp.model;

import java.util.UUID;

/**
 * A true/false question.
 *
 * Demonstrates another Question implementation.
 */
public record TrueFalseQuestion(
        String id,
        String text,
        String category,
        int points,
        boolean correctAnswer,
        String hint
) implements Question {

    /**
     * Creates a new true/false question.
     */
    public static TrueFalseQuestion create(
            String text,
            String category,
            int points,
            boolean correctAnswer,
            String hint) {
        return new TrueFalseQuestion(
                UUID.randomUUID().toString().substring(0, 8),
                text,
                category,
                points,
                correctAnswer,
                hint
        );
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public boolean checkAnswer(String answer) {
        String normalized = answer.trim().toLowerCase();
        boolean userAnswer;

        if (normalized.equals("true") || normalized.equals("t") ||
            normalized.equals("yes") || normalized.equals("y") ||
            normalized.equals("1")) {
            userAnswer = true;
        } else if (normalized.equals("false") || normalized.equals("f") ||
                   normalized.equals("no") || normalized.equals("n") ||
                   normalized.equals("0")) {
            userAnswer = false;
        } else {
            return false; // Invalid answer
        }

        return userAnswer == correctAnswer;
    }

    @Override
    public String getHint() {
        return hint;
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer ? "True" : "False";
    }
}
