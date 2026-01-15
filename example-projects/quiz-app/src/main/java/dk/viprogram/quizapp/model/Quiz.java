package dk.viprogram.quizapp.model;

import java.util.List;
import java.util.UUID;

/**
 * A quiz containing multiple questions.
 */
public record Quiz(
        String id,
        String title,
        String description,
        List<Question> questions
) {

    /**
     * Creates a new quiz.
     */
    public static Quiz create(String title, String description, List<Question> questions) {
        return new Quiz(
                UUID.randomUUID().toString().substring(0, 8),
                title,
                description,
                List.copyOf(questions)
        );
    }

    /**
     * Returns the total possible points for this quiz.
     */
    public int getTotalPoints() {
        return questions.stream()
                .mapToInt(Question::getPoints)
                .sum();
    }

    /**
     * Returns the number of questions in this quiz.
     */
    public int getQuestionCount() {
        return questions.size();
    }

    /**
     * Returns a short display string for use in dropdowns and lists.
     */
    @Override
    public String toString() {
        return String.format("%s (%d questions)", title, getQuestionCount());
    }
}
