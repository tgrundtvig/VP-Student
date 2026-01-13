package dk.viprogram.week08.memory;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX version of the Memory Card Game.
 *
 * This demonstrates the View-as-interface pattern:
 * - Same Model and Controller work with JavaFX View
 * - View is just a different implementation of the same interface
 *
 * Run with:
 *   mvn javafx:run -Dexec.mainClass="dk.viprogram.week08.memory.MemoryApp"
 *
 * Or use the MemoryLauncher class from IntelliJ.
 */
public class MemoryApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create Model, View, Controller
        MemoryGameModel model = new SimpleMemoryGameModel();
        JavaFXMemoryGameView view = new JavaFXMemoryGameView(primaryStage);
        MemoryGameController controller = new MemoryGameController(model, view);

        // Start with a default game size
        controller.startGame(4, 4);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
