package dk.viprogram.quizapp.model;

import java.util.List;
import java.util.UUID;

/**
 * A multiple choice question with several options.
 *
 * Implemented as a record because it's immutable data.
 */
public record MultipleChoiceQuestion(
        String id,
        String text,
        String category,
        int points,
        List<String> options,
        int correctOptionIndex,
        String hint
) implements Question {

    /**
     * Creates a new multiple choice question.
     */
    public static MultipleChoiceQuestion create(
            String text,
            String category,
            int points,
            List<String> options,
            int correctOptionIndex,
            String hint) {
        return new MultipleChoiceQuestion(
                UUID.randomUUID().toString().substring(0, 8),
                text,
                category,
                points,
                List.copyOf(options), // Defensive copy
                correctOptionIndex,
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
        try {
            int selectedIndex = Integer.parseInt(answer.trim()) - 1; // 1-based input
            return selectedIndex == correctOptionIndex;
        } catch (NumberFormatException e) {
            // Try matching by text
            return options.get(correctOptionIndex).equalsIgnoreCase(answer.trim());
        }
    }

    @Override
    public String getHint() {
        return hint;
    }

    @Override
    public String getCorrectAnswer() {
        return options.get(correctOptionIndex);
    }

    /**
     * Returns the options formatted for display.
     */
    public String getFormattedOptions() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            sb.append(String.format("  %d. %s%n", i + 1, options.get(i)));
        }
        return sb.toString();
    }
}
