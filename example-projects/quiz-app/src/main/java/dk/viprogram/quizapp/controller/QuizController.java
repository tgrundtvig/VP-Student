package dk.viprogram.quizapp.controller;

import dk.viprogram.quizapp.model.*;
import dk.viprogram.quizapp.repository.QuizRepository;
import dk.viprogram.quizapp.repository.ResultRepository;
import dk.viprogram.quizapp.scoring.ScoringStrategy;
import dk.viprogram.quizapp.view.QuizView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Quiz Application.
 *
 * Coordinates between View, Repositories, and Scoring Strategy.
 */
public class QuizController {

    private final QuizView view;
    private final QuizRepository quizRepository;
    private final ResultRepository resultRepository;
    private final ScoringStrategy scoringStrategy;
    private boolean running;

    public QuizController(
            QuizView view,
            QuizRepository quizRepository,
            ResultRepository resultRepository,
            ScoringStrategy scoringStrategy) {
        this.view = view;
        this.quizRepository = quizRepository;
        this.resultRepository = resultRepository;
        this.scoringStrategy = scoringStrategy;
        this.running = false;
    }

    /**
     * Starts the main application loop.
     */
    public void run() {
        running = true;
        view.showWelcome();

        while (running) {
            int choice = view.showMainMenu();
            handleMenuChoice(choice);
        }

        view.showMessage("Thanks for playing!");
    }

    /**
     * Handles a menu choice.
     */
    public void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> startQuiz();
            case 2 -> showHighScores();
            case 3 -> running = false;
            default -> view.showError("Invalid choice. Please try again.");
        }
    }

    /**
     * Starts a quiz session.
     */
    public void startQuiz() {
        List<Quiz> quizzes = quizRepository.findAll();
        Quiz selectedQuiz = view.promptSelectQuiz(quizzes);

        if (selectedQuiz == null) {
            return;
        }

        String playerName = view.promptPlayerName();
        if (playerName.isEmpty()) {
            playerName = "Anonymous";
        }

        QuizResult result = playQuiz(selectedQuiz, playerName);
        resultRepository.save(result);
        view.showQuizResult(result);
    }

    /**
     * Plays through a quiz and returns the result.
     * Public for testing purposes.
     */
    public QuizResult playQuiz(Quiz quiz, String playerName) {
        List<QuizResult.AnswerRecord> answers = new ArrayList<>();
        int totalScore = 0;
        int correctCount = 0;

        List<Question> questions = quiz.questions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            view.showQuestion(question, i + 1, questions.size());

            // Offer hint
            if (view.promptUseHint()) {
                view.showMessage("Hint: " + question.getHint());
            }

            // Get answer
            String answer = view.promptAnswer();
            boolean correct = question.checkAnswer(answer);
            int points = scoringStrategy.calculateScore(question, answer, correct);

            if (correct) {
                correctCount++;
            }
            totalScore += points;

            view.showAnswerFeedback(correct, question.getCorrectAnswer(), points);

            answers.add(new QuizResult.AnswerRecord(
                    question.getId(),
                    answer,
                    correct,
                    points
            ));
        }

        return QuizResult.create(
                quiz.id(),
                playerName,
                totalScore,
                quiz.getTotalPoints(),
                correctCount,
                questions.size(),
                answers
        );
    }

    /**
     * Shows high scores for a quiz.
     */
    public void showHighScores() {
        List<Quiz> quizzes = quizRepository.findAll();
        Quiz selectedQuiz = view.promptSelectQuiz(quizzes);

        if (selectedQuiz == null) {
            return;
        }

        List<QuizResult> topScores = resultRepository.getTopScores(selectedQuiz.id(), 10);
        view.showHighScores(topScores, selectedQuiz.title());
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
