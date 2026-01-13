package dk.viprogram.quizapp.repository;

import dk.viprogram.quizapp.model.QuizResult;

import java.util.List;

/**
 * Repository for quiz results.
 */
public interface ResultRepository extends Repository<QuizResult, String> {

    /**
     * Finds all results for a specific quiz.
     */
    List<QuizResult> findByQuizId(String quizId);

    /**
     * Finds all results for a specific player.
     */
    List<QuizResult> findByPlayerName(String playerName);

    /**
     * Returns top scores for a quiz, limited to n results.
     */
    List<QuizResult> getTopScores(String quizId, int limit);
}
