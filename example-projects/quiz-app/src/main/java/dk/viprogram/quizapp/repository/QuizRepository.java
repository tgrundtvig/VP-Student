package dk.viprogram.quizapp.repository;

import dk.viprogram.quizapp.model.Quiz;

import java.util.List;

/**
 * Repository for quizzes.
 */
public interface QuizRepository extends Repository<Quiz, String> {

    /**
     * Finds quizzes containing questions in a specific category.
     */
    List<Quiz> findByCategory(String category);
}
