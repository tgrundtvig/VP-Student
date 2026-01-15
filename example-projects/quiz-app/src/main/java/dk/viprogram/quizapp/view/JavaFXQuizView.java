package dk.viprogram.quizapp.view;

import dk.viprogram.quizapp.model.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JavaFX implementation of QuizView.
 *
 * This demonstrates that the SAME controller can work with completely
 * different UI implementations - console or GUI.
 */
public class JavaFXQuizView implements QuizView {

    private final Stage stage;
    private final VBox root;
    private final Label titleLabel;
    private final Label questionLabel;
    private final VBox answerBox;
    private final Label scoreLabel;
    private final TextArea feedbackArea;
    private final HBox buttonBox;

    private final AtomicInteger menuChoice = new AtomicInteger(-1);
    private CountDownLatch menuLatch;
    private CompletableFuture<String> answerFuture;

    public JavaFXQuizView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(15);
        this.root.setPadding(new Insets(20));
        this.root.setAlignment(Pos.TOP_CENTER);

        // Title
        titleLabel = new Label("Quiz Application");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 28));

        // Question area
        questionLabel = new Label("");
        questionLabel.setFont(Font.font("System", 18));
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(600);

        // Answer options
        answerBox = new VBox(10);
        answerBox.setAlignment(Pos.CENTER_LEFT);
        answerBox.setPadding(new Insets(10));

        // Score display
        scoreLabel = new Label("");
        scoreLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

        // Feedback area
        feedbackArea = new TextArea();
        feedbackArea.setPrefRowCount(4);
        feedbackArea.setEditable(false);
        feedbackArea.setWrapText(true);

        // Buttons
        buttonBox = createButtonBox();

        root.getChildren().addAll(titleLabel, questionLabel, answerBox, scoreLabel, feedbackArea, buttonBox);
    }

    private HBox createButtonBox() {
        Button takeQuizBtn = new Button("Take a Quiz");
        takeQuizBtn.setOnAction(e -> setMenuChoice(1));

        Button highScoresBtn = new Button("High Scores");
        highScoresBtn.setOnAction(e -> setMenuChoice(2));

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> setMenuChoice(3));

        HBox box = new HBox(15);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(takeQuizBtn, highScoresBtn, exitBtn);
        return box;
    }

    public VBox getRoot() {
        return root;
    }

    private void setMenuChoice(int choice) {
        menuChoice.set(choice);
        if (menuLatch != null) {
            menuLatch.countDown();
        }
    }

    // ==================== QuizView Implementation ====================

    @Override
    public void showWelcome() {
        Platform.runLater(() -> {
            feedbackArea.setText("Welcome to the Quiz Application!\n\nSelect 'Take a Quiz' to begin.");
        });
    }

    @Override
    public int showMainMenu() {
        menuChoice.set(-1);
        menuLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            questionLabel.setText("Select an option:");
            answerBox.getChildren().clear();
            buttonBox.setVisible(true);
        });

        try {
            menuLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 3; // Exit
        }
        return menuChoice.get();
    }

    @Override
    public void showQuizList(List<Quiz> quizzes) {
        Platform.runLater(() -> {
            StringBuilder sb = new StringBuilder("Available Quizzes:\n\n");
            for (int i = 0; i < quizzes.size(); i++) {
                Quiz q = quizzes.get(i);
                sb.append(String.format("%d. %s (%d questions, %d points)\n   %s\n\n",
                        i + 1, q.title(), q.getQuestionCount(), q.getTotalPoints(), q.description()));
            }
            feedbackArea.setText(sb.toString());
        });
    }

    @Override
    public void showQuestion(Question question, int questionNumber, int totalQuestions) {
        Platform.runLater(() -> {
            buttonBox.setVisible(false);

            String header = String.format("Question %d of %d [%s - %d points]",
                    questionNumber, totalQuestions, question.getCategory(), question.getPoints());
            questionLabel.setText(header + "\n\n" + question.getText());

            answerBox.getChildren().clear();

            if (question instanceof MultipleChoiceQuestion mcq) {
                ToggleGroup group = new ToggleGroup();
                List<String> options = mcq.options();
                for (int i = 0; i < options.size(); i++) {
                    RadioButton rb = new RadioButton((i + 1) + ". " + options.get(i));
                    rb.setToggleGroup(group);
                    rb.setFont(Font.font("System", 14));
                    final int index = i + 1;
                    rb.setOnAction(e -> {
                        if (answerFuture != null) {
                            answerFuture.complete(String.valueOf(index));
                        }
                    });
                    answerBox.getChildren().add(rb);
                }
            } else if (question instanceof TrueFalseQuestion) {
                ToggleGroup group = new ToggleGroup();
                RadioButton trueBtn = new RadioButton("True");
                trueBtn.setToggleGroup(group);
                trueBtn.setFont(Font.font("System", 14));
                trueBtn.setOnAction(e -> {
                    if (answerFuture != null) {
                        answerFuture.complete("true");
                    }
                });

                RadioButton falseBtn = new RadioButton("False");
                falseBtn.setToggleGroup(group);
                falseBtn.setFont(Font.font("System", 14));
                falseBtn.setOnAction(e -> {
                    if (answerFuture != null) {
                        answerFuture.complete("false");
                    }
                });

                answerBox.getChildren().addAll(trueBtn, falseBtn);
            }
        });
    }

    @Override
    public void showAnswerFeedback(boolean correct, String correctAnswer, int pointsEarned) {
        Platform.runLater(() -> {
            String feedback;
            if (correct) {
                feedback = String.format("CORRECT! +%d points", pointsEarned);
                feedbackArea.setStyle("-fx-text-fill: green;");
            } else {
                feedback = String.format("INCORRECT. The answer was: %s", correctAnswer);
                if (pointsEarned < 0) {
                    feedback += String.format(" (%d points)", pointsEarned);
                }
                feedbackArea.setStyle("-fx-text-fill: red;");
            }
            feedbackArea.setText(feedback);
        });

        // Brief pause to show feedback
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Platform.runLater(() -> feedbackArea.setStyle(""));
    }

    @Override
    public void showQuizResult(QuizResult result) {
        Platform.runLater(() -> {
            questionLabel.setText("Quiz Complete!");

            answerBox.getChildren().clear();

            String resultText = String.format(
                    "Player: %s\n" +
                    "Score: %d / %d (%.1f%%)\n" +
                    "Correct: %d / %d\n" +
                    "Grade: %s",
                    result.playerName(),
                    result.score(), result.totalPossible(), result.getPercentage(),
                    result.correctAnswers(), result.totalQuestions(),
                    result.getGrade()
            );

            Label resultLabel = new Label(resultText);
            resultLabel.setFont(Font.font("System", 16));
            answerBox.getChildren().add(resultLabel);

            scoreLabel.setText("Final Grade: " + result.getGrade());

            feedbackArea.setText("Press any menu button to continue.");
            buttonBox.setVisible(true);
        });
    }

    @Override
    public void showHighScores(List<QuizResult> results, String quizTitle) {
        Platform.runLater(() -> {
            questionLabel.setText("High Scores: " + quizTitle);
            answerBox.getChildren().clear();

            if (results.isEmpty()) {
                feedbackArea.setText("No scores yet for this quiz.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < results.size(); i++) {
                    QuizResult r = results.get(i);
                    sb.append(String.format("%d. %s - %d points (%.1f%%) - Grade %s\n",
                            i + 1, r.playerName(), r.score(), r.getPercentage(), r.getGrade()));
                }
                feedbackArea.setText(sb.toString());
            }
            buttonBox.setVisible(true);
        });
    }

    @Override
    public void showMessage(String message) {
        Platform.runLater(() -> feedbackArea.appendText(message + "\n"));
    }

    @Override
    public void showError(String error) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(error);
            alert.showAndWait();
        });
    }

    @Override
    public String promptPlayerName() {
        CompletableFuture<String> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Player Name");
            dialog.setHeaderText("Enter your name");
            dialog.setContentText("Name:");

            Optional<String> result = dialog.showAndWait();
            future.complete(result.orElse("Anonymous"));
        });

        try {
            return future.get();
        } catch (Exception e) {
            return "Anonymous";
        }
    }

    @Override
    public Quiz promptSelectQuiz(List<Quiz> quizzes) {
        if (quizzes.isEmpty()) {
            return null;
        }

        CompletableFuture<Quiz> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            ChoiceDialog<Quiz> dialog = new ChoiceDialog<>(quizzes.get(0), quizzes);
            dialog.setTitle("Select Quiz");
            dialog.setHeaderText("Choose a quiz to take");
            dialog.setContentText("Quiz:");

            Optional<Quiz> result = dialog.showAndWait();
            future.complete(result.orElse(null));
        });

        try {
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String promptAnswer() {
        answerFuture = new CompletableFuture<>();
        try {
            return answerFuture.get();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean promptUseHint() {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hint");
            alert.setHeaderText(null);
            alert.setContentText("Would you like a hint?");

            Optional<ButtonType> result = alert.showAndWait();
            future.complete(result.isPresent() && result.get() == ButtonType.OK);
        });

        try {
            return future.get();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean promptConfirm(String message) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText(null);
            alert.setContentText(message);

            Optional<ButtonType> result = alert.showAndWait();
            future.complete(result.isPresent() && result.get() == ButtonType.OK);
        });

        try {
            return future.get();
        } catch (Exception e) {
            return false;
        }
    }
}
