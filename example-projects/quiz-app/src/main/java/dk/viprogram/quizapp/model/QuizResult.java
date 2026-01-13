package dk.viprogram.quizapp.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The result of completing a quiz.
 *
 * Records which answers were given and the final score.
 */
public record QuizResult(
        String id,
        String quizId,
        String playerName,
        int score,
        int totalPossible,
        int correctAnswers,
        int totalQuestions,
        LocalDateTime completedAt,
        List<AnswerRecord> answers
) {

    /**
     * Creates a new quiz result.
     */
    public static QuizResult create(
            String quizId,
            String playerName,
            int score,
            int totalPossible,
            int correctAnswers,
            int totalQuestions,
            List<AnswerRecord> answers) {
        return new QuizResult(
                UUID.randomUUID().toString().substring(0, 8),
                quizId,
                playerName,
                score,
                totalPossible,
                correctAnswers,
                totalQuestions,
                LocalDateTime.now(),
                List.copyOf(answers)
        );
    }

    /**
     * Returns the percentage score (0-100).
     */
    public double getPercentage() {
        if (totalPossible == 0) return 0;
        return (score * 100.0) / totalPossible;
    }

    /**
     * Returns a grade based on percentage.
     */
    public String getGrade() {
        double pct = getPercentage();
        if (pct >= 90) return "A";
        if (pct >= 80) return "B";
        if (pct >= 70) return "C";
        if (pct >= 60) return "D";
        return "F";
    }

    /**
     * Record of a single answer given.
     */
    public record AnswerRecord(
            String questionId,
            String givenAnswer,
            boolean correct,
            int pointsEarned
    ) {}
}
