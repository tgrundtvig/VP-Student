package dk.viprogram.quizapp;

import dk.viprogram.quizapp.controller.QuizController;
import dk.viprogram.quizapp.repository.InMemoryQuizRepository;
import dk.viprogram.quizapp.repository.InMemoryResultRepository;
import dk.viprogram.quizapp.scoring.StandardScoring;
import dk.viprogram.quizapp.view.JavaFXQuizView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX GUI launcher for the Quiz Application.
 *
 * This demonstrates the power of interface-based design:
 * - Same QuizController
 * - Same Repositories
 * - Different View implementation (JavaFX instead of Console)
 *
 * Run with: mvn javafx:run
 */
public class QuizAppGUI extends Application {

    private QuizController controller;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quiz Application");

        // Create repositories (same as console version)
        var quizRepository = new InMemoryQuizRepository();
        var resultRepository = new InMemoryResultRepository();

        // Create JavaFX view instead of Console view
        var view = new JavaFXQuizView(primaryStage);

        // Create controller (same as console version)
        // Using StandardScoring strategy - could be configured differently
        var scoringStrategy = new StandardScoring();
        controller = new QuizController(view, quizRepository, resultRepository, scoringStrategy);

        // Set up the scene
        Scene scene = new Scene(view.getRoot(), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> {
            controller.stop();
            Platform.exit();
        });

        primaryStage.show();

        // Run controller in background thread (it has blocking calls)
        Thread controllerThread = new Thread(() -> {
            controller.run();
            Platform.exit();
        });
        controllerThread.setDaemon(true);
        controllerThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
