package dk.viprogram.quizapp.view;

import dk.viprogram.quizapp.model.*;

import java.util.List;
import java.util.Scanner;

/**
 * Console-based implementation of QuizView.
 */
public class ConsoleQuizView implements QuizView {

    private final Scanner scanner;

    public ConsoleQuizView() {
        this.scanner = new Scanner(System.in);
    }

    public ConsoleQuizView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showWelcome() {
        System.out.println("========================================");
        System.out.println("         QUIZ APPLICATION");
        System.out.println("========================================");
        System.out.println();
    }

    @Override
    public int showMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Take a quiz");
        System.out.println("2. View high scores");
        System.out.println("3. Exit");
        System.out.print("\nChoice: ");

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public void showQuizList(List<Quiz> quizzes) {
        System.out.println("\n--- Available Quizzes ---");
        if (quizzes.isEmpty()) {
            System.out.println("(No quizzes available)");
            return;
        }

        for (int i = 0; i < quizzes.size(); i++) {
            Quiz quiz = quizzes.get(i);
            System.out.printf("%d. %s (%d questions, %d points)%n",
                    i + 1, quiz.title(), quiz.getQuestionCount(), quiz.getTotalPoints());
            System.out.printf("   %s%n", quiz.description());
        }
    }

    @Override
    public void showQuestion(Question question, int questionNumber, int totalQuestions) {
        System.out.printf("%n=== Question %d of %d ===%n", questionNumber, totalQuestions);
        System.out.printf("[%s] %d points%n%n", question.getCategory(), question.getPoints());
        System.out.println(question.getText());

        if (question instanceof MultipleChoiceQuestion mcq) {
            System.out.println(mcq.getFormattedOptions());
        } else if (question instanceof TrueFalseQuestion) {
            System.out.println("  Enter: True or False");
        }
    }

    @Override
    public void showAnswerFeedback(boolean correct, String correctAnswer, int pointsEarned) {
        if (correct) {
            System.out.printf("CORRECT! +%d points%n", pointsEarned);
        } else {
            if (pointsEarned < 0) {
                System.out.printf("INCORRECT. %d points. The answer was: %s%n",
                        pointsEarned, correctAnswer);
            } else {
                System.out.printf("INCORRECT. The answer was: %s%n", correctAnswer);
            }
        }
    }

    @Override
    public void showQuizResult(QuizResult result) {
        System.out.println("\n========================================");
        System.out.println("           QUIZ COMPLETE!");
        System.out.println("========================================");
        System.out.printf("Player: %s%n", result.playerName());
        System.out.printf("Score: %d / %d (%.1f%%)%n",
                result.score(), result.totalPossible(), result.getPercentage());
        System.out.printf("Correct: %d / %d%n",
                result.correctAnswers(), result.totalQuestions());
        System.out.printf("Grade: %s%n", result.getGrade());
        System.out.println("========================================");
    }

    @Override
    public void showHighScores(List<QuizResult> results, String quizTitle) {
        System.out.printf("%n--- High Scores: %s ---%n", quizTitle);
        if (results.isEmpty()) {
            System.out.println("(No scores yet)");
            return;
        }

        for (int i = 0; i < results.size(); i++) {
            QuizResult r = results.get(i);
            System.out.printf("%d. %s - %d points (%.1f%%) - %s%n",
                    i + 1, r.playerName(), r.score(), r.getPercentage(), r.getGrade());
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String error) {
        System.out.println("ERROR: " + error);
    }

    @Override
    public String promptPlayerName() {
        System.out.print("Enter your name: ");
        return scanner.nextLine().trim();
    }

    @Override
    public Quiz promptSelectQuiz(List<Quiz> quizzes) {
        if (quizzes.isEmpty()) {
            return null;
        }

        showQuizList(quizzes);
        System.out.print("\nSelect quiz (0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice > 0 && choice <= quizzes.size()) {
                return quizzes.get(choice - 1);
            }
        } catch (NumberFormatException e) {
            // Cancelled
        }
        return null;
    }

    @Override
    public String promptAnswer() {
        System.out.print("Your answer: ");
        return scanner.nextLine().trim();
    }

    @Override
    public boolean promptUseHint() {
        System.out.print("Need a hint? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }

    @Override
    public boolean promptConfirm(String message) {
        System.out.print(message + " (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
}
