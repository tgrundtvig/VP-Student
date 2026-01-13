package dk.viprogram.quizapp.view;

import dk.viprogram.quizapp.model.Question;
import dk.viprogram.quizapp.model.Quiz;
import dk.viprogram.quizapp.model.QuizResult;

import java.util.List;

/**
 * View interface for the Quiz Application.
 */
public interface QuizView {

    // ==================== Display Methods ====================

    void showWelcome();

    int showMainMenu();

    void showQuizList(List<Quiz> quizzes);

    void showQuestion(Question question, int questionNumber, int totalQuestions);

    void showAnswerFeedback(boolean correct, String correctAnswer, int pointsEarned);

    void showQuizResult(QuizResult result);

    void showHighScores(List<QuizResult> results, String quizTitle);

    void showMessage(String message);

    void showError(String error);

    // ==================== Input Methods ====================

    String promptPlayerName();

    Quiz promptSelectQuiz(List<Quiz> quizzes);

    String promptAnswer();

    boolean promptUseHint();

    boolean promptConfirm(String message);
}
