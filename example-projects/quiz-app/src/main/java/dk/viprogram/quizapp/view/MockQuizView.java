package dk.viprogram.quizapp.view;

import dk.viprogram.quizapp.model.Question;
import dk.viprogram.quizapp.model.Quiz;
import dk.viprogram.quizapp.model.QuizResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Mock implementation of QuizView for testing.
 */
public class MockQuizView implements QuizView {

    private final List<String> displayedMessages = new ArrayList<>();
    private final Queue<Integer> menuChoices = new LinkedList<>();
    private final Queue<String> playerNames = new LinkedList<>();
    private final Queue<Quiz> quizSelections = new LinkedList<>();
    private final Queue<String> answers = new LinkedList<>();
    private final Queue<Boolean> hintChoices = new LinkedList<>();
    private final Queue<Boolean> confirmations = new LinkedList<>();

    private QuizResult lastDisplayedResult;

    // ==================== Programming Methods ====================

    public void queueMenuChoice(int choice) {
        menuChoices.add(choice);
    }

    public void queuePlayerName(String name) {
        playerNames.add(name);
    }

    public void queueQuizSelection(Quiz quiz) {
        quizSelections.add(quiz);
    }

    public void queueAnswer(String answer) {
        answers.add(answer);
    }

    public void queueHintChoice(boolean useHint) {
        hintChoices.add(useHint);
    }

    public void queueConfirmation(boolean confirm) {
        confirmations.add(confirm);
    }

    // ==================== Inspection Methods ====================

    public List<String> getDisplayedMessages() {
        return new ArrayList<>(displayedMessages);
    }

    public boolean hasMessage(String message) {
        return displayedMessages.stream()
                .anyMatch(m -> m.contains(message));
    }

    public QuizResult getLastDisplayedResult() {
        return lastDisplayedResult;
    }

    // ==================== QuizView Implementation ====================

    @Override
    public void showWelcome() {
        displayedMessages.add("WELCOME");
    }

    @Override
    public int showMainMenu() {
        displayedMessages.add("MENU");
        return menuChoices.isEmpty() ? 3 : menuChoices.poll();
    }

    @Override
    public void showQuizList(List<Quiz> quizzes) {
        displayedMessages.add("QUIZ_LIST: " + quizzes.size());
    }

    @Override
    public void showQuestion(Question question, int questionNumber, int totalQuestions) {
        displayedMessages.add(String.format("QUESTION %d/%d: %s",
                questionNumber, totalQuestions, question.getText()));
    }

    @Override
    public void showAnswerFeedback(boolean correct, String correctAnswer, int pointsEarned) {
        displayedMessages.add(String.format("FEEDBACK: %s, %d points",
                correct ? "CORRECT" : "INCORRECT", pointsEarned));
    }

    @Override
    public void showQuizResult(QuizResult result) {
        displayedMessages.add("RESULT: " + result.score() + "/" + result.totalPossible());
        lastDisplayedResult = result;
    }

    @Override
    public void showHighScores(List<QuizResult> results, String quizTitle) {
        displayedMessages.add("HIGH_SCORES: " + quizTitle + " (" + results.size() + ")");
    }

    @Override
    public void showMessage(String message) {
        displayedMessages.add("MSG: " + message);
    }

    @Override
    public void showError(String error) {
        displayedMessages.add("ERROR: " + error);
    }

    @Override
    public String promptPlayerName() {
        displayedMessages.add("PROMPT_NAME");
        return playerNames.isEmpty() ? "TestPlayer" : playerNames.poll();
    }

    @Override
    public Quiz promptSelectQuiz(List<Quiz> quizzes) {
        displayedMessages.add("PROMPT_SELECT_QUIZ");
        return quizSelections.poll();
    }

    @Override
    public String promptAnswer() {
        displayedMessages.add("PROMPT_ANSWER");
        return answers.isEmpty() ? "" : answers.poll();
    }

    @Override
    public boolean promptUseHint() {
        displayedMessages.add("PROMPT_HINT");
        return hintChoices.isEmpty() ? false : hintChoices.poll();
    }

    @Override
    public boolean promptConfirm(String message) {
        displayedMessages.add("CONFIRM: " + message);
        return confirmations.isEmpty() ? false : confirmations.poll();
    }
}
