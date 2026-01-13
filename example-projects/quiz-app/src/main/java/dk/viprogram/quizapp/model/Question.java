package dk.viprogram.quizapp.model;

/**
 * Interface for quiz questions.
 *
 * This interface demonstrates polymorphism - different question types
 * (multiple choice, true/false, fill-in-blank) all implement this interface.
 *
 * The interface focuses on WHAT a question can do, not HOW it does it.
 */
public interface Question {

    /**
     * Returns the unique identifier for this question.
     */
    String getId();

    /**
     * Returns the question text to display.
     */
    String getText();

    /**
     * Returns the category/topic of this question.
     */
    String getCategory();

    /**
     * Returns the point value for this question.
     */
    int getPoints();

    /**
     * Checks if the given answer is correct.
     *
     * @param answer the user's answer
     * @return true if correct, false otherwise
     */
    boolean checkAnswer(String answer);

    /**
     * Returns a hint for this question.
     */
    String getHint();

    /**
     * Returns the correct answer (for display after answering).
     */
    String getCorrectAnswer();
}
